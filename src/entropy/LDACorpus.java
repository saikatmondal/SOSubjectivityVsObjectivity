package entropy;


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


public class LDACorpus {
	public static void main(String[] args){
		// TODO Auto-generated method stub
		JavaTextReader jReader = new JavaTextReader();
		CSharpTextReader csharpReader = new CSharpTextReader();
		JavascriptTextReader jscriptReader = new JavascriptTextReader();
		PythonTextReader pyReader = new PythonTextReader();
		
		jReader.start();
		csharpReader.start();
		jscriptReader.start();
		pyReader.start();
	}

}


//Java CSV file reader for sentiment analysis
class JavaTextReader extends Thread {
	
	public void run(){
	
		ICsvListReader listReader = null;
		final int NO_OF_FILES=15;
	
	    try {
	    	
	    	FileWriter writeFile;
	    	PrintWriter printInFile;
	        
	    	// For Sentiment Text
	    	String fileForText = "C:/mallet/so-question-quality/java/";
	    	
	    	
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
		//        			content = doc.select(":not(code)");
		    			String finalString = contentText.text();
		    			finalString = finalString.replaceAll("[\\[,\\]]", "");
		        		bodyText=finalString;
		        		
		        		Elements contentCode = doc.select("code");
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
		        		         	
		        	if(Integer.valueOf(score)> 0){
		        		// Text File Creation and Writing Text
		        			        		
		        		writeFile = new FileWriter(fileForText+"_"+id+"_1_"+".txt");
		        		printInFile = new PrintWriter(writeFile);
		        		printInFile.write(title+" "+bodyText);
		        		printInFile.close();
		        		
		        	}
		        	
		        	else if(Integer.valueOf(score)< 0){
		        		// Text File Creation and Writing Text
		        			        		
		        		writeFile = new FileWriter(fileForText+"_"+id+"_2_"+".txt");
		        		printInFile = new PrintWriter(writeFile);
		        		printInFile.write(title+" "+bodyText);
		        		printInFile.close();
		        	}
		        	
		        }
	    		
	    	}

	        System.out.println("Java File Finished Successfully!!");
	
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
// Javascript CSV file reader for sentiment analysis
//*******************************************************************************************************************//

class CSharpTextReader extends Thread {
	
	public void run(){
	
		ICsvListReader listReader = null;
		final int NO_OF_FILES=13;
	
	    try {
	    	
	    	FileWriter writeFile;
	    	PrintWriter printInFile;
	        
	    	// For Sentiment Text
	    	String fileForText = "C:/mallet/so-question-quality/csharp/";
	    	
	    	
	    	for (int i =1; i <= NO_OF_FILES; i++) {
	    		System.out.println("C# File No:"+i);
	    		
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
		//        			content = doc.select(":not(code)");
		    			String finalString = contentText.text();
		    			finalString = finalString.replaceAll("[\\[,\\]]", "");
		        		bodyText=finalString;
		        		
		        		Elements contentCode = doc.select("code");
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
		        		         	
		        	if(Integer.valueOf(score)> 0){
		        		// Text File Creation and Writing Text
		        			        		
		        		writeFile = new FileWriter(fileForText+"_"+id+"_1_"+".txt");
		        		printInFile = new PrintWriter(writeFile);
		        		printInFile.write(title+" "+bodyText);
		        		printInFile.close();
		        		
		        	}
		        	
		        	else if(Integer.valueOf(score)< 0){
		        		// Text File Creation and Writing Text
		        			        		
		        		writeFile = new FileWriter(fileForText+"_"+id+"_2_"+".txt");
		        		printInFile = new PrintWriter(writeFile);
		        		printInFile.write(title+" "+bodyText);
		        		printInFile.close();
		        	}
		        	
		        }
	    		
	    	}

	        System.out.println("C# File Finished Successfully!!");
	
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
//Javascript CSV file reader for sentiment analysis
//**************************************************************************************//

class JavascriptTextReader extends Thread {
	
	public void run(){
	
		ICsvListReader listReader = null;
		final int NO_OF_FILES=15;
	
	    try {
	    	
	    	FileWriter writeFile;
	    	PrintWriter printInFile;
	        
	    	// For Sentiment Text
	    	String fileForText = "C:/mallet/so-question-quality/javascript/";
	    	
	    	
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
		//        			content = doc.select(":not(code)");
		    			String finalString = contentText.text();
		    			finalString = finalString.replaceAll("[\\[,\\]]", "");
		        		bodyText=finalString;
		        		
		        		Elements contentCode = doc.select("code");
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
		        		         	
		        	if(Integer.valueOf(score)> 0){
		        		// Text File Creation and Writing Text
		        			        		
		        		writeFile = new FileWriter(fileForText+"_"+id+"_1_"+".txt");
		        		printInFile = new PrintWriter(writeFile);
		        		printInFile.write(title+" "+bodyText);
		        		printInFile.close();
		        		
		        	}
		        	
		        	else if(Integer.valueOf(score)< 0){
		        		// Text File Creation and Writing Text
		        			        		
		        		writeFile = new FileWriter(fileForText+"_"+id+"_2_"+".txt");
		        		printInFile = new PrintWriter(writeFile);
		        		printInFile.write(title+" "+bodyText);
		        		printInFile.close();
		        	}
		        	
		        }
	    		
	    	}

	        System.out.println("Javascript File Finished Successfully!!");
	
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
//Python CSV file reader for sentiment analysis
//*******************************************************************************//

class PythonTextReader extends Thread {
	
	public void run(){
	
		ICsvListReader listReader = null;
		final int NO_OF_FILES=10;
	
	    try {
	    	
	    	FileWriter writeFile;
	    	PrintWriter printInFile;
	        
	    	// For Sentiment Text
	    	String fileForText = "C:/mallet/so-question-quality/python/";
	    	
	    	
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
		//        			content = doc.select(":not(code)");
		    			String finalString = contentText.text();
		    			finalString = finalString.replaceAll("[\\[,\\]]", "");
		        		bodyText=finalString;
		        		
		        		Elements contentCode = doc.select("code");
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
		        		         	
		        	if(Integer.valueOf(score)> 0){
		        		// Text File Creation and Writing Text
		        			        		
		        		writeFile = new FileWriter(fileForText+"_"+id+"_1_"+".txt");
		        		printInFile = new PrintWriter(writeFile);
		        		printInFile.write(title+" "+bodyText);
		        		printInFile.close();
		        		
		        	}
		        	
		        	else if(Integer.valueOf(score)< 0){
		        		// Text File Creation and Writing Text
		        			        		
		        		writeFile = new FileWriter(fileForText+"_"+id+"_2_"+".txt");
		        		printInFile = new PrintWriter(writeFile);
		        		printInFile.write(title+" "+bodyText);
		        		printInFile.close();
		        	}
		        	
		        }
	    		
	    	}

	        System.out.println("Python File Finished Successfully!!");
	
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
