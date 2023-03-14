package textlength;


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
import org.supercsv.io.CsvListWriter;
import org.supercsv.io.ICsvListReader;
import org.supercsv.io.ICsvListWriter;
import org.supercsv.prefs.CsvPreference;


public class TextLength {
	public static void main(String[] args){

		JavaRatio jReader = new JavaRatio();
		CSharpRatio csharpReader = new CSharpRatio();
		JavaScriptRatio jscriptReader = new JavaScriptRatio();
		PythonRatio pyReader = new PythonRatio();
		
		jReader.start();
		csharpReader.start();
		jscriptReader.start();
		pyReader.start();
	}

}

//Java CSV file reader 
class JavaRatio extends Thread {
	
	public void run(){
		
		String outLocationH = "E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/CombinedLanguages/H1.csv";
		String outLocationL = "E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/CombinedLanguages/L1.csv";
		
		
		ICsvListReader listReader = null;
		ICsvListWriter csvWriterH = null;
		ICsvListWriter csvWriterL = null;
		
		final int NO_OF_FILES=15;
	
	    try {
	    	
	    	csvWriterH = new CsvListWriter(new FileWriter(outLocationH),CsvPreference.STANDARD_PREFERENCE);
	    	csvWriterL = new CsvListWriter(new FileWriter(outLocationL),CsvPreference.STANDARD_PREFERENCE);
			String firstColumnTitle = "Id";
			String secondColumnTitle = "Length";
			csvWriterH.write(firstColumnTitle, secondColumnTitle);			
			csvWriterL.write(firstColumnTitle, secondColumnTitle);
	    	
	    	
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
		        	
		        	
		        	if((Integer.valueOf(score)> 0) && (bodyCode != null && !bodyCode.isEmpty())){
//		        	if((Integer.valueOf(score)> 0)){

		        		if(bodyText.length()>0) {
//		        			csvWriterH.write(id,((float)(bodyCode.length()))/(float)(bodyText.length()));
		        			csvWriterH.write(id,(double)(bodyText.length()+title.length()));
//		        			csvWriterH.write(id,((float)(bodyCode.length())));
		        		}
		        		else {
		        			csvWriterH.write(id,0.0);
		        		}
		        		
		        	}
		        	
		        	else if((Integer.valueOf(score)< 0) && (bodyCode != null && !bodyCode.isEmpty())){
//		        	if((Integer.valueOf(score)< 0)){
		        		
		        		if(bodyText.length()>0) {
//		        			csvWriterL.write(id,((float)(bodyCode.length()))/(float)(bodyText.length()));
		        			csvWriterL.write(id,(double)bodyText.length());
//		        			csvWriterL.write(id,((float)(bodyCode.length())));
		        		}
		        		else {
		        			csvWriterL.write(id,0.0);
		        		}
		        	}
		        	
		        }
	    		
	    	}
	    	csvWriterH.close();
	    	csvWriterL.close();
	    	
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
//C# CSV file reader 
//*******************************************************************************************************************//

class CSharpRatio extends Thread {
	
	public void run(){
		
		String outLocationH = "E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/CombinedLanguages/H2.csv";
		String outLocationL = "E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/CombinedLanguages/L2.csv";
		
		
		ICsvListReader listReader = null;
		ICsvListWriter csvWriterH = null;
		ICsvListWriter csvWriterL = null;
		
		final int NO_OF_FILES=13;
	
	    try {
	    	
	    	csvWriterH = new CsvListWriter(new FileWriter(outLocationH),CsvPreference.STANDARD_PREFERENCE);
	    	csvWriterL = new CsvListWriter(new FileWriter(outLocationL),CsvPreference.STANDARD_PREFERENCE);
			String firstColumnTitle = "Id";
			String secondColumnTitle = "Length";
			csvWriterH.write(firstColumnTitle, secondColumnTitle);			
			csvWriterL.write(firstColumnTitle, secondColumnTitle);
	    	
	    	
	    	for (int i =1; i <= NO_OF_FILES; i++) {
	    		System.out.println("C# File No:"+i);
	    		
		    	//Reading CSV File
		        listReader = new CsvListReader(new FileReader("E:/Projects/SOContentQualityResources/CSVData/C#/QueryData/QueryResults_"+i+"_AnsCount1_Score_NE_0_2017.csv"), CsvPreference.STANDARD_PREFERENCE);          
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
		        	
		        	if((Integer.valueOf(score)> 0) && (bodyCode != null && !bodyCode.isEmpty())){
//			        	if((Integer.valueOf(score)> 0)){

			        		if(bodyText.length()>0) {
//			        			csvWriterH.write(id,((float)(bodyCode.length()))/(float)(bodyText.length()));
			        			csvWriterH.write(id,(double)(bodyText.length()+title.length()));
//			        			csvWriterH.write(id,((float)(bodyCode.length())));
			        		}
			        		else {
			        			csvWriterH.write(id,0.0);
			        		}
			        		
			        	}
			        	
		        	else if((Integer.valueOf(score)< 0) && (bodyCode != null && !bodyCode.isEmpty())){
//			        	if((Integer.valueOf(score)< 0)){
		        		
		        		if(bodyText.length()>0) {
//			        			csvWriterL.write(id,((float)(bodyCode.length()))/(float)(bodyText.length()));
		        			csvWriterL.write(id,(double)bodyText.length());
//			        			csvWriterL.write(id,((float)(bodyCode.length())));
		        		}
		        		else {
		        			csvWriterL.write(id,0.0);
		        		}
		        	}
		        			        	
		        }
	    		
	    	}
	    	csvWriterH.close();
	    	csvWriterL.close();
	    	
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

class JavaScriptRatio extends Thread {
	
	public void run(){
		
		String outLocationH = "E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/CombinedLanguages/H3.csv";
		String outLocationL = "E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/CombinedLanguages/L3.csv";
		
		
		ICsvListReader listReader = null;
		ICsvListWriter csvWriterH = null;
		ICsvListWriter csvWriterL = null;
		
		final int NO_OF_FILES=15;
	
	    try {
	    	
	    	csvWriterH = new CsvListWriter(new FileWriter(outLocationH),CsvPreference.STANDARD_PREFERENCE);
	    	csvWriterL = new CsvListWriter(new FileWriter(outLocationL),CsvPreference.STANDARD_PREFERENCE);
			String firstColumnTitle = "Id";
			String secondColumnTitle = "Length";
			csvWriterH.write(firstColumnTitle, secondColumnTitle);			
			csvWriterL.write(firstColumnTitle, secondColumnTitle);
	    	
	    	
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
		        	
		        	
		        	if((Integer.valueOf(score)> 0) && (bodyCode != null && !bodyCode.isEmpty())){
//			        	if((Integer.valueOf(score)> 0)){

			        		if(bodyText.length()>0) {
//			        			csvWriterH.write(id,((float)(bodyCode.length()))/(float)(bodyText.length()));
			        			csvWriterH.write(id,(double)(bodyText.length()+title.length()));
//			        			csvWriterH.write(id,((float)(bodyCode.length())));
			        		}
			        		else {
			        			csvWriterH.write(id,0.0);
			        		}
			        		
			        	}
			        	
		        	else if((Integer.valueOf(score)< 0) && (bodyCode != null && !bodyCode.isEmpty())){
//			        	if((Integer.valueOf(score)< 0)){
		        		
		        		if(bodyText.length()>0) {
//			        			csvWriterL.write(id,((float)(bodyCode.length()))/(float)(bodyText.length()));
		        			csvWriterL.write(id,(double)bodyText.length());
//			        			csvWriterL.write(id,((float)(bodyCode.length())));
		        		}
		        		else {
		        			csvWriterL.write(id,0.0);
		        		}
		        	}
		        	
		        }
	    		
	    	}
	    	csvWriterH.close();
	    	csvWriterL.close();
	    	
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

class PythonRatio extends Thread {
	
	public void run(){
		
		String outLocationH = "E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/CombinedLanguages/H4.csv";
		String outLocationL = "E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/CombinedLanguages/L4.csv";
		
		
		ICsvListReader listReader = null;
		ICsvListWriter csvWriterH = null;
		ICsvListWriter csvWriterL = null;
		
		final int NO_OF_FILES=10;
	
	    try {
	    	
	    	csvWriterH = new CsvListWriter(new FileWriter(outLocationH),CsvPreference.STANDARD_PREFERENCE);
	    	csvWriterL = new CsvListWriter(new FileWriter(outLocationL),CsvPreference.STANDARD_PREFERENCE);
			String firstColumnTitle = "Id";
			String secondColumnTitle = "Length";
			csvWriterH.write(firstColumnTitle, secondColumnTitle);			
			csvWriterL.write(firstColumnTitle, secondColumnTitle);
	    	
	    	
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
		        	
		        	
		        	if((Integer.valueOf(score)> 0) && (bodyCode != null && !bodyCode.isEmpty())){
//			        	if((Integer.valueOf(score)> 0)){

			        		if(bodyText.length()>0) {
//			        			csvWriterH.write(id,((float)(bodyCode.length()))/(float)(bodyText.length()));
			        			csvWriterH.write(id,(double)(bodyText.length()+title.length()));
//			        			csvWriterH.write(id,((float)(bodyCode.length())));
			        		}
			        		else {
			        			csvWriterH.write(id,0.0);
			        		}
			        		
			        	}
			        	
		        	else if((Integer.valueOf(score)< 0) && (bodyCode != null && !bodyCode.isEmpty())){
//			        	if((Integer.valueOf(score)< 0)){
		        		
		        		if(bodyText.length()>0) {
//			        			csvWriterL.write(id,((float)(bodyCode.length()))/(float)(bodyText.length()));
		        			csvWriterL.write(id,(double)bodyText.length());
//			        			csvWriterL.write(id,((float)(bodyCode.length())));
		        		}
		        		else {
		        			csvWriterL.write(id,0.0);
		        		}
		        	}
		        	
		        }
	    		
	    	}
	    	csvWriterH.close();
	    	csvWriterL.close();
	    	
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
