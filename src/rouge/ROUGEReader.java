package rouge;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvListReader;
import org.supercsv.io.ICsvListReader;
import org.supercsv.prefs.CsvPreference;


public class ROUGEReader {

	public static void main(String[] args){

		JavaCodeReader jReader = new JavaCodeReader();
		CSharpCodeReader csharpReader = new CSharpCodeReader();
		JavascriptCodeReader jscriptReader = new JavascriptCodeReader();
		PythonCodeReader pyReader = new PythonCodeReader();
		
		jReader.start();
		csharpReader.start();
		jscriptReader.start();
		pyReader.start();
	}
}

//Java CSV file reader 
class JavaCodeReader extends Thread {
	
	public void run(){
	
		ICsvListReader listReader = null;
		final int NO_OF_FILES=15;
	
	    try {
	    	
	    	FileWriter writeFile;
	    	PrintWriter printInFile;
	        
	    	// For Sentiment Text
	    	String fileForRefH = "E:/Projects/SOContentQualityResources/DataStore/Java/TitleROUGE/Score_GT_0/Reference/";
	    	String fileForSysH = "E:/Projects/SOContentQualityResources/DataStore/Java/TitleROUGE/Score_GT_0/System/";	    	
	    	
	    	String fileForRefL = "E:/Projects/SOContentQualityResources/DataStore/Java/TitleROUGE/Score_LT_0/Reference/";
	    	String fileForSysL = "E:/Projects/SOContentQualityResources/DataStore/Java/TitleROUGE/Score_LT_0/System/";
	    	
	    	
	    	for (int i =1; i <= NO_OF_FILES; i++) {
	    		System.out.println("Java File No:"+i);
	    		
		    	//Reading CSV File
		        listReader = new CsvListReader(new FileReader("E:/Projects/SOContentQualityResources/CSVData/Java/QueryData/QueryResults_"+i+"_AnsCount1_Score_NE_0_2017.csv"), CsvPreference.STANDARD_PREFERENCE);          
		        listReader.getHeader(true); // skip the header (can't be used with CsvListReader)
		        final CellProcessor[] processors = getProcessors();               
		        List<Object> questionList;
		        
		        while( (questionList = listReader.read(processors)) != null ) {
		        	 
		           	String id="";                          		// int
		        	String score="-9999";                       // int
		        	String bodyText="";                        	// nvarchar (max)
		        	String bodyCode="";                        	// nvarchar (max)
		        	String title="";                      		// nvarchar (250)
		        	String tags="";
		        	
		        	
		        	try {
		        		id=questionList.get(0).toString();
					}catch (Exception e) {
						// TODO: handle exception
					}	
		        	
		        	try {
		        		score=questionList.get(6).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	
		        	try {
		        		Document doc = Jsoup.parse(questionList.get(8).toString());
		    			Elements contentText = doc.select("p");
		    			String finalString = contentText.text();
		    			finalString = finalString.replaceAll("[\\[,\\]]", "");
		        		bodyText=finalString;
		        		
		        		Elements contentCode = doc.select("pre");
		        		bodyCode = contentCode.text();
//		        		bodyCode = contentCode.text();
//	            		bodyCode = bodyCode.replaceAll("[( ) { } , + = - * ++ -- ]", " ");
//	            		bodyCode = bodyCode.replaceAll("[;]","");
		        		
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	
		        	try {
		        		title=questionList.get(15).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	
		        	try {
//	            		Document doc = Jsoup.parse(questionList.get(16).toString());
//	        			Elements tagText = doc.select("a");
	            		
		        		tags=questionList.get(16).toString();
		        		tags = tags.replaceAll("><", " ");
		        		tags = tags.replaceAll("[\\<,\\>]","");
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        		         	
		        	if((Integer.valueOf(score)> 0)){ // (bodyCode != null && !bodyCode.isEmpty())
		        		// Text File Creation and Writing Text
		        			        		
		        		writeFile = new FileWriter(fileForRefH+id+"_High.txt");
		        		printInFile = new PrintWriter(writeFile);
		        		printInFile.write(title);
		        		printInFile.close();
		        		
		        		writeFile = new FileWriter(fileForSysH+id+"_High.txt");
		        		printInFile = new PrintWriter(writeFile);
		        		printInFile.write(bodyText+"\n"+bodyCode+"\n"+tags);
		        		printInFile.close();
		        		
		        	}
		        	
		        	else if((Integer.valueOf(score)< 0)){
		        		// Text File Creation and Writing Text
		        			        		
		        		writeFile = new FileWriter(fileForRefL+id+"_Low.txt");
		        		printInFile = new PrintWriter(writeFile);
		        		printInFile.write(title);
		        		printInFile.close();
		        		
		        		writeFile = new FileWriter(fileForSysL+id+"_Low.txt");
		        		printInFile = new PrintWriter(writeFile);
		        		printInFile.write(bodyText+"\n"+bodyCode+"\n"+tags);
		        		printInFile.close();
		        	}
		        	
		        }
	    		
	    	}

	        System.out.println("Data Write in File Finished Successfully!!");
	
	    }catch (Exception e) {
			// TODO: handle exception
		}
	    finally {
	    	try {
	    		if( listReader != null ) {
	                listReader.close();
	        }	
			} catch (Exception e2) {
				// TODO: handle exception
			}
	    }
	}

	private static CellProcessor[] getProcessors() {
		         
	    final CellProcessor[] processors = new CellProcessor[] {
	    		new Optional(), // post Id not null
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional()
	    };
	    
	    return processors;
	}

}


//*******************************************************************************************************************//
// C# CSV file reader 
//*******************************************************************************************************************//

class CSharpCodeReader extends Thread {
	
	public void run(){
	
		ICsvListReader listReader = null;
		final int NO_OF_FILES=13;
	
	    try {
	    	
	    	FileWriter writeFile;
	    	PrintWriter printInFile;
	        
	    	// For Sentiment Text
	    	String fileForRefH = "E:/Projects/SOContentQualityResources/DataStore/C#/TitleROUGE/Score_GT_0/Reference/";
	    	String fileForSysH = "E:/Projects/SOContentQualityResources/DataStore/C#/TitleROUGE/Score_GT_0/System/";	    	
	    	
	    	String fileForRefL = "E:/Projects/SOContentQualityResources/DataStore/C#/TitleROUGE/Score_LT_0/Reference/";
	    	String fileForSysL = "E:/Projects/SOContentQualityResources/DataStore/C#/TitleROUGE/Score_LT_0/System/";
	    
	    	
	    	for (int i =1; i <= NO_OF_FILES; i++) {
	    		System.out.println("C_Sharp File No:"+i);
	    		
		    	//Reading CSV File
		        listReader = new CsvListReader(new FileReader("E:/Projects/SOContentQualityResources/CSVData/C_Sharp/QueryData/QueryResults_"+i+"_AnsCount1_Score_NE_0_2017.csv"), CsvPreference.STANDARD_PREFERENCE);          
		        listReader.getHeader(true); // skip the header (can't be used with CsvListReader)
		        final CellProcessor[] processors = getProcessors();               
		        List<Object> questionList;
		        
		        while( (questionList = listReader.read(processors)) != null ) {
		        	 
		           	String id="";                          		// int
		        	String score="-9999";                       // int
		        	String bodyText="";                        	// nvarchar (max)
		        	String bodyCode="";                        	// nvarchar (max)
		        	String title="";                      		// nvarchar (250)
		        	String tags="";
		        	
		        	
		        	try {
		        		id=questionList.get(0).toString();
					}catch (Exception e) {
						// TODO: handle exception
					}	
		        	
		        	try {
		        		score=questionList.get(6).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	
		        	try {
		        		Document doc = Jsoup.parse(questionList.get(8).toString());
		    			Elements contentText = doc.select("p");
		    			String finalString = contentText.text();
		    			finalString = finalString.replaceAll("[\\[,\\]]", "");
		        		bodyText=finalString;
		        		
		        		Elements contentCode = doc.select("pre");
		        		bodyCode = contentCode.text();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	
		        	try {
		        		title=questionList.get(15).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        		         	
		        	try {
//	            		Document doc = Jsoup.parse(questionList.get(16).toString());
//	        			Elements tagText = doc.select("a");
	            		
		        		tags=questionList.get(16).toString();
		        		tags = tags.replaceAll("><", " ");
		        		tags = tags.replaceAll("[\\<,\\>]","");
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        		         	
		        	if((Integer.valueOf(score)> 0)){
		        		// Text File Creation and Writing Text
		        			        		
		        		writeFile = new FileWriter(fileForRefH+id+"_High.txt");
		        		printInFile = new PrintWriter(writeFile);
		        		printInFile.write(title);
		        		printInFile.close();
		        		
		        		writeFile = new FileWriter(fileForSysH+id+"_High.txt");
		        		printInFile = new PrintWriter(writeFile);
		        		printInFile.write(bodyText+"\n"+bodyCode+"\n"+tags);
		        		printInFile.close();
		        		
		        	}
		        	
		        	else if((Integer.valueOf(score)< 0)){
		        		// Text File Creation and Writing Text
		        			        		
		        		writeFile = new FileWriter(fileForRefL+id+"_Low.txt");
		        		printInFile = new PrintWriter(writeFile);
		        		printInFile.write(title);
		        		printInFile.close();
		        		
		        		writeFile = new FileWriter(fileForSysL+id+"_Low.txt");
		        		printInFile = new PrintWriter(writeFile);
		        		printInFile.write(bodyText+"\n"+bodyCode+"\n"+tags);
		        		printInFile.close();
		        	}
		        	
		        }
	    		
	    	}

	        System.out.println("Data Write in File Finished Successfully!!");
	
	    }catch (Exception e) {
			// TODO: handle exception
		}
	    finally {
	    	try {
	    		if( listReader != null ) {
	                listReader.close();
	        }	
			} catch (Exception e2) {
				// TODO: handle exception
			}
	    }
	}

	private static CellProcessor[] getProcessors() {
		         
	    final CellProcessor[] processors = new CellProcessor[] {
	    		new Optional(), // post Id not null
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional()
	    };
	    
	    return processors;
	}

}

//**************************************************************************************//
//Javascript CSV file reader 
//**************************************************************************************//

class JavascriptCodeReader extends Thread {
	
	public void run(){
	
		ICsvListReader listReader = null;
		final int NO_OF_FILES=15;
	
	    try {
	    	
	    	FileWriter writeFile;
	    	PrintWriter printInFile;
	        
	    	// For Sentiment Text
	    	String fileForRefH = "E:/Projects/SOContentQualityResources/DataStore/JavaScript/TitleROUGE/Score_GT_0/Reference/";
	    	String fileForSysH = "E:/Projects/SOContentQualityResources/DataStore/JavaScript/TitleROUGE/Score_GT_0/System/";	    	
	    	
	    	String fileForRefL = "E:/Projects/SOContentQualityResources/DataStore/JavaScript/TitleROUGE/Score_LT_0/Reference/";
	    	String fileForSysL = "E:/Projects/SOContentQualityResources/DataStore/JavaScript/TitleROUGE/Score_LT_0/System/";
	    	
	    	for (int i =1; i <= NO_OF_FILES; i++) {
	    		System.out.println("Javascript File No:"+i);
	    		
		    	//Reading CSV File
		        listReader = new CsvListReader(new FileReader("E:/Projects/SOContentQualityResources/CSVData/Javascript/QueryData/QueryResults_"+i+"_AnsCount1_Score_NE_0_2017.csv"), CsvPreference.STANDARD_PREFERENCE);          
		        listReader.getHeader(true); // skip the header (can't be used with CsvListReader)
		        final CellProcessor[] processors = getProcessors();               
		        List<Object> questionList;
		        
		        while( (questionList = listReader.read(processors)) != null ) {
		        	 
		           	String id="";                          		// int
		        	String score="-9999";                       // int
		        	String bodyText="";                        	// nvarchar (max)
		        	String bodyCode="";                        	// nvarchar (max)
		        	String title="";                      		// nvarchar (250)
		        	String tags="";
		        	
		        	
		        	try {
		        		id=questionList.get(0).toString();
					}catch (Exception e) {
						// TODO: handle exception
					}	
		        	
		        	try {
		        		score=questionList.get(6).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	
		        	try {
		        		Document doc = Jsoup.parse(questionList.get(8).toString());
		    			Elements contentText = doc.select("p");
		    			String finalString = contentText.text();
		    			finalString = finalString.replaceAll("[\\[,\\]]", "");
		        		bodyText=finalString;
		        		
		        		Elements contentCode = doc.select("pre");
		        		bodyCode = contentCode.text();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	
		        	try {
		        		title=questionList.get(15).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        		         	
		        	try {
//	            		Document doc = Jsoup.parse(questionList.get(16).toString());
//	        			Elements tagText = doc.select("a");
	            		
		        		tags=questionList.get(16).toString();
		        		tags = tags.replaceAll("><", " ");
		        		tags = tags.replaceAll("[\\<,\\>]","");
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        		         	
		        	if((Integer.valueOf(score)> 0)){
		        		// Text File Creation and Writing Text
		        			        		
		        		writeFile = new FileWriter(fileForRefH+id+"_High.txt");
		        		printInFile = new PrintWriter(writeFile);
		        		printInFile.write(title);
		        		printInFile.close();
		        		
		        		writeFile = new FileWriter(fileForSysH+id+"_High.txt");
		        		printInFile = new PrintWriter(writeFile);
		        		printInFile.write(bodyText+"\n"+bodyCode+"\n"+tags);
		        		printInFile.close();
		        		
		        	}
		        	
		        	else if((Integer.valueOf(score)< 0)){
		        		// Text File Creation and Writing Text
		        			        		
		        		writeFile = new FileWriter(fileForRefL+id+"_Low.txt");
		        		printInFile = new PrintWriter(writeFile);
		        		printInFile.write(title);
		        		printInFile.close();
		        		
		        		writeFile = new FileWriter(fileForSysL+id+"_Low.txt");
		        		printInFile = new PrintWriter(writeFile);
		        		printInFile.write(bodyText+"\n"+bodyCode+"\n"+tags);
		        		printInFile.close();
		        	}
		        	
		        }
	    		
	    	}

	        System.out.println("Data Write in File Finished Successfully!!");
	
	    }catch (Exception e) {
			// TODO: handle exception
		}
	    finally {
	    	try {
	    		if( listReader != null ) {
	                listReader.close();
	        }	
			} catch (Exception e2) {
				// TODO: handle exception
			}
	    }
	}

	private static CellProcessor[] getProcessors() {
		         
	    final CellProcessor[] processors = new CellProcessor[] {
	    		new Optional(), // post Id not null
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional()
	    };
	    
	    return processors;
	}

}


//*******************************************************************************//
//Python CSV file reader
//*******************************************************************************//

class PythonCodeReader extends Thread {
	
	public void run(){
	
		ICsvListReader listReader = null;
		final int NO_OF_FILES=10;
	
	    try {
	    	
	    	FileWriter writeFile;
	    	PrintWriter printInFile;
	        
	    	// For Sentiment Text
	    	String fileForRefH = "E:/Projects/SOContentQualityResources/DataStore/Python/TitleROUGE/Score_GT_0/Reference/";
	    	String fileForSysH = "E:/Projects/SOContentQualityResources/DataStore/Python/TitleROUGE/Score_GT_0/System/";	    	
	    	
	    	String fileForRefL = "E:/Projects/SOContentQualityResources/DataStore/Python/TitleROUGE/Score_LT_0/Reference/";
	    	String fileForSysL = "E:/Projects/SOContentQualityResources/DataStore/Python/TitleROUGE/Score_LT_0/System/";
	    	
	    	for (int i =1; i <= NO_OF_FILES; i++) {
	    		System.out.println("Python File No:"+i);
	    		
		    	//Reading CSV File
		        listReader = new CsvListReader(new FileReader("E:/Projects/SOContentQualityResources/CSVData/Python/QueryData/QueryResults_"+i+"_AnsCount1_Score_NE_0_2017.csv"), CsvPreference.STANDARD_PREFERENCE);          
		        listReader.getHeader(true); // skip the header (can't be used with CsvListReader)
		        final CellProcessor[] processors = getProcessors();               
		        List<Object> questionList;
		        
		        while( (questionList = listReader.read(processors)) != null ) {
		        	 
		           	String id="";                          		// int
		        	String score="-9999";                       // int
		        	String bodyText="";                        	// nvarchar (max)
		        	String bodyCode="";                        	// nvarchar (max)
		        	String title="";                      		// nvarchar (250)
		        	String tags="";
		        	
		        	
		        	try {
		        		id=questionList.get(0).toString();
					}catch (Exception e) {
						// TODO: handle exception
					}	
		        	
		        	try {
		        		score=questionList.get(6).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	
		        	try {
		        		Document doc = Jsoup.parse(questionList.get(8).toString());
		    			Elements contentText = doc.select("p");
		    			String finalString = contentText.text();
		    			finalString = finalString.replaceAll("[\\[,\\]]", "");
		        		bodyText=finalString;
		        		
		        		Elements contentCode = doc.select("pre");
		        		bodyCode = contentCode.text();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	
		        	try {
		        		title=questionList.get(15).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        		         	
		        	try {
//	            		Document doc = Jsoup.parse(questionList.get(16).toString());
//	        			Elements tagText = doc.select("a");
	            		
		        		tags=questionList.get(16).toString();
		        		tags = tags.replaceAll("><", " ");
		        		tags = tags.replaceAll("[\\<,\\>]","");
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        		         	
		        	if((Integer.valueOf(score)> 0)){
		        		// Text File Creation and Writing Text
		        			        		
		        		writeFile = new FileWriter(fileForRefH+id+"_High.txt");
		        		printInFile = new PrintWriter(writeFile);
		        		printInFile.write(title);
		        		printInFile.close();
		        		
		        		writeFile = new FileWriter(fileForSysH+id+"_High.txt");
		        		printInFile = new PrintWriter(writeFile);
		        		printInFile.write(bodyText+"\n"+bodyCode+"\n"+tags);
		        		printInFile.close();
		        		
		        	}
		        	
		        	else if((Integer.valueOf(score)< 0)){
		        		// Text File Creation and Writing Text
		        			        		
		        		writeFile = new FileWriter(fileForRefL+id+"_Low.txt");
		        		printInFile = new PrintWriter(writeFile);
		        		printInFile.write(title);
		        		printInFile.close();
		        		
		        		writeFile = new FileWriter(fileForSysL+id+"_Low.txt");
		        		printInFile = new PrintWriter(writeFile);
		        		printInFile.write(bodyText+"\n"+bodyCode+"\n"+tags);
		        		printInFile.close();
		        	}
		        	
		        }
	    		
	    	}

	        System.out.println("Data Write in File Finished Successfully!!");
	
	    }catch (Exception e) {
			// TODO: handle exception
		}
	    finally {
	    	try {
	    		if( listReader != null ) {
	                listReader.close();
	        }	
			} catch (Exception e2) {
				// TODO: handle exception
			}
	    }
	}

	private static CellProcessor[] getProcessors() {
		         
	    final CellProcessor[] processors = new CellProcessor[] {
	    		new Optional(), // post Id not null
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional()
	    };
	    
	    return processors;
	}

}

