package soreader;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvListReader;
import org.supercsv.io.CsvListWriter;
import org.supercsv.io.ICsvListReader;
import org.supercsv.io.ICsvListWriter;
import org.supercsv.prefs.CsvPreference;

public class GrandReader {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GrandCombiner combine = new GrandCombiner();
		combine.start();

	}

}


class GrandCombiner extends Thread {
	
	public void run(){
	
		ICsvListReader listJavaReader = null;
		ICsvListReader listCSharpReader = null;
		ICsvListReader listJavascriptReader = null;
		ICsvListReader listPythonReader = null;
		
		ICsvListWriter csvWriter = null;


	
	    try {
	    	    	
	    		csvWriter= new CsvListWriter(new FileWriter("E:/Projects/SOContentQualityResources/CSVData/CombinedMetricResults/grand_combine.csv"),CsvPreference.STANDARD_PREFERENCE);
	    		csvWriter.write("Id", "TextReadability", "CodeReadability", "ROUGH", "TopicEntropy","Sentiment", "QuestionType");
		    	//Reading CSV File
	    		listJavaReader = new CsvListReader(new FileReader("E:/Projects/SOContentQualityResources/CSVData/CombinedMetricResults/Java/java_total.csv"), CsvPreference.STANDARD_PREFERENCE);
	    		listCSharpReader = new CsvListReader(new FileReader("E:/Projects/SOContentQualityResources/CSVData/CombinedMetricResults/C_Sharp/csharp_total.csv"), CsvPreference.STANDARD_PREFERENCE);
	    		listJavascriptReader = new CsvListReader(new FileReader("E:/Projects/SOContentQualityResources/CSVData/CombinedMetricResults/Javascript/javascript_total.csv"), CsvPreference.STANDARD_PREFERENCE);
	    		listPythonReader = new CsvListReader(new FileReader("E:/Projects/SOContentQualityResources/CSVData/CombinedMetricResults/Python/python_total.csv"), CsvPreference.STANDARD_PREFERENCE);
		        
	    		listJavaReader.getHeader(true); // skip the header (can't be used with CsvListReader)
	    		listCSharpReader.getHeader(true);
	    		listJavascriptReader.getHeader(true);
	    		listPythonReader.getHeader(true);

		        
		        final CellProcessor[] processorsJava = getProcessorsJava();               
		        List<Object> questionListJava;
		        final CellProcessor[] processorsCSharp = getProcessorsCSharp();               
		        List<Object> questionListCSharp;
		        final CellProcessor[] processorsJavascript = getProcessorsJavascript();               
		        List<Object> questionListJavascript;
		        final CellProcessor[] processorsPython = getProcessorsPython();               
		        List<Object> questionListPython;
  
		        int count =0;
		        
		        while( ((questionListJava = listJavaReader.read(processorsJava)) != null) ) {
		        	 
		           	String idText="";                          		// int
		        	String scoreText="-9999";                       // int
		        	String scoreCode="-9999";
		        	String scoreRough="-9999";
		        	String scoreTopic="-9999";
		        	String scoreSentiment="-9999";
		        	String qType="-9999";
		        	
		        	
		        	try {
		        		idText=questionListJava.get(0).toString();
					}catch (Exception e) {
						// TODO: handle exception
					}	
		        	
		        	try {
		        		scoreText=questionListJava.get(1).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	try {
		        		scoreCode=questionListJava.get(2).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	try {
		        		scoreRough=questionListJava.get(3).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	try {
		        		scoreTopic=questionListJava.get(4).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	try {
		        		scoreSentiment=questionListJava.get(5).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	
		        	try {
		        		qType=questionListJava.get(6).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	
		        	csvWriter.write(idText, scoreText, scoreCode, scoreRough, scoreTopic, scoreSentiment, qType);
		        	
//		        	count++;
//		        	if(count>76181) break;
		        	
		        }
		        
		        while( ((questionListCSharp = listCSharpReader.read(processorsCSharp)) != null) ) {
		        	 
		           	String idText="";                          		// int
		        	String scoreText="-9999";                       // int
		        	String scoreCode="-9999";
		        	String scoreRough="-9999";
		        	String scoreTopic="-9999";
		        	String scoreSentiment="-9999";
		        	String qType="-9999";
		        	
		        	
		        	try {
		        		idText=questionListCSharp.get(0).toString();
					}catch (Exception e) {
						// TODO: handle exception
					}	
		        	
		        	try {
		        		scoreText=questionListCSharp.get(1).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	try {
		        		scoreCode=questionListCSharp.get(2).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	try {
		        		scoreRough=questionListCSharp.get(3).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	try {
		        		scoreTopic=questionListCSharp.get(4).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	try {
		        		scoreSentiment=questionListCSharp.get(5).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	
		        	try {
		        		qType=questionListCSharp.get(6).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	
		        	csvWriter.write(idText, scoreText, scoreCode, scoreRough, scoreTopic, scoreSentiment, qType);
		        	
//		        	count++;
//		        	if(count>76181) break;
		        	
		        }
		        
		        while( ((questionListJavascript = listJavascriptReader.read(processorsJavascript)) != null) ) {
		        	 
		           	String idText="";                          		// int
		        	String scoreText="-9999";                       // int
		        	String scoreCode="-9999";
		        	String scoreRough="-9999";
		        	String scoreTopic="-9999";
		        	String scoreSentiment="-9999";
		        	String qType="-9999";
		        	
		        	
		        	try {
		        		idText=questionListJavascript.get(0).toString();
					}catch (Exception e) {
						// TODO: handle exception
					}	
		        	
		        	try {
		        		scoreText=questionListJavascript.get(1).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	try {
		        		scoreCode=questionListJavascript.get(2).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	try {
		        		scoreRough=questionListJavascript.get(3).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	try {
		        		scoreTopic=questionListJavascript.get(4).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	try {
		        		scoreSentiment=questionListJavascript.get(5).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	
		        	try {
		        		qType=questionListJavascript.get(6).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	
		        	csvWriter.write(idText, scoreText, scoreCode, scoreRough, scoreTopic, scoreSentiment, qType);
		        	
//		        	count++;
//		        	if(count>76181) break;
		        	
		        }
		        
	    
		        while( ((questionListPython = listPythonReader.read(processorsPython)) != null) ) {
		        	 
		           	String idText="";                          		// int
		        	String scoreText="-9999";                       // int
		        	String scoreCode="-9999";
		        	String scoreRough="-9999";
		        	String scoreTopic="-9999";
		        	String scoreSentiment="-9999";
		        	String qType="-9999";
		        	
		        	
		        	try {
		        		idText=questionListPython.get(0).toString();
					}catch (Exception e) {
						// TODO: handle exception
					}	
		        	
		        	try {
		        		scoreText=questionListPython.get(1).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	try {
		        		scoreCode=questionListPython.get(2).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	try {
		        		scoreRough=questionListPython.get(3).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	try {
		        		scoreTopic=questionListPython.get(4).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	try {
		        		scoreSentiment=questionListPython.get(5).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	
		        	try {
		        		qType=questionListPython.get(6).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	
		        	csvWriter.write(idText, scoreText, scoreCode, scoreRough, scoreTopic, scoreSentiment, qType);
		        	
//		        	count++;
//		        	if(count>76181) break;
		        	
		        }
		        
		        
		        
		        csvWriter.close();
		        
	    }catch (Exception e) {
			// TODO: handle exception
		}
	    finally {
	    	try {
	    		if( listJavaReader != null ) {
	                listJavaReader.close();
	    		}	
	    		if( listCSharpReader != null ) {
	    			listCSharpReader.close();
	    		}
	    		if( listJavascriptReader != null ) {
	    			listJavascriptReader.close();
	    		}
	    		if( listPythonReader != null ) {
	    			listPythonReader.close();
	    		}
				} catch (Exception e2) {
					// TODO: handle exception
				}
	    }
		        
}	  


	private static CellProcessor[] getProcessorsJava() {
		         
	    final CellProcessor[] processors = new CellProcessor[] {
	    		new Optional(), // post Id not null
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional()
	  
	    };
	    
	    return processors;
	}
	
	private static CellProcessor[] getProcessorsCSharp() {
        
	    final CellProcessor[] processors = new CellProcessor[] {
	    		new Optional(), // post Id not null
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional()
	  
	    };
	    
	    return processors;
	}
	
	private static CellProcessor[] getProcessorsJavascript() {
        
	    final CellProcessor[] processors = new CellProcessor[] {
	    		new Optional(), // post Id not null
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional()
	  
	    };
	    
	    return processors;
	}
	
	private static CellProcessor[] getProcessorsPython() {
        
	    final CellProcessor[] processors = new CellProcessor[] {
	    		new Optional(), // post Id not null
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
