package soreader;

import java.io.FileReader;
import java.io.FileWriter;
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

public class SOCodeTextOnlyQuestionClassification {
	
	public static void main(String[] args){
		// TODO Auto-generated method stub
//		JavaIDReader jReader = new JavaIDReader();
		CSharpIDReader csharpReader = new CSharpIDReader();
		JavascriptIDReader jscriptReader = new JavascriptIDReader();
		PythonIDReader pyReader = new PythonIDReader();
		
//		jReader.start();
		csharpReader.start();
		jscriptReader.start();
		pyReader.start();

}
}


//Java CSV file reader 
class JavaIDReader extends Thread {
	
	public void run(){
	
		ICsvListReader listReader = null;
		ICsvListWriter csvWriterTextOnlyH = null;
		ICsvListWriter csvWriterTextOnlyL = null;
		
		ICsvListWriter csvWriterCodeH = null;
		ICsvListWriter csvWriterCodeL = null;

		final int NO_OF_FILES=15;
	
	    try {
	    	    	
	    	csvWriterTextOnlyH = new CsvListWriter(new FileWriter("E:/Projects/SOContentQualityResources/CSVData/Java/MetricResults/TextOnlyFile/TextReadability/Id_Have_Code_Score_GT_0.csv"),CsvPreference.STANDARD_PREFERENCE);
	    	csvWriterTextOnlyL = new CsvListWriter(new FileWriter("E:/Projects/SOContentQualityResources/CSVData/Java/MetricResults/TextOnlyFile/TextReadability/Id_Have_Code_Score_LT_0.csv"),CsvPreference.STANDARD_PREFERENCE);
	    	
	    	csvWriterCodeH = new CsvListWriter(new FileWriter("E:/Projects/SOContentQualityResources/CSVData/Java/MetricResults/TextCodeFile/CodeReadability/Id_Have_No_Code_Score_GT_0.csv"),CsvPreference.STANDARD_PREFERENCE);
	    	csvWriterCodeL = new CsvListWriter(new FileWriter("E:/Projects/SOContentQualityResources/CSVData/Java/MetricResults/TextCodeFile/CodeReadability/Id_Have_No_Code_Score_LT_0.csv"),CsvPreference.STANDARD_PREFERENCE);
	    		
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
		        		         	
		        	if((Integer.valueOf(score)> 0) && (bodyCode != null && !bodyCode.isEmpty())){
		        		// Text File Creation and Writing Text        		
		        		csvWriterTextOnlyH.write(id, 0);
		        	}
		        	
		        	else if((Integer.valueOf(score)< 0) && (bodyCode != null && !bodyCode.isEmpty())){
		        		// Text File Creation and Writing Text
		        		csvWriterTextOnlyL.write(id, 0);
		        	}
		        	else if((Integer.valueOf(score)> 0) && !(bodyCode != null && !bodyCode.isEmpty())){
		        		// Text File Creation and Writing Text
		        		csvWriterCodeH.write(id, 0);
		        	}
		        	else if((Integer.valueOf(score)< 0) && !(bodyCode != null && !bodyCode.isEmpty())){
		        		// Text File Creation and Writing Text
		        		csvWriterCodeL.write(id, 0);
		        	}
		        	
		        }
	    		
	    	}
	    	
	    	csvWriterTextOnlyH.close();
	    	csvWriterTextOnlyL.close();
	    	csvWriterCodeH.close();
	    	csvWriterCodeL.close();

	        System.out.println(" Java ID Read Finished Successfully!!");
	
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

class CSharpIDReader extends Thread {
	
	public void run(){
	
		ICsvListReader listReader = null;
		ICsvListWriter csvWriterTextOnlyH = null;
		ICsvListWriter csvWriterTextOnlyL = null;
		
		ICsvListWriter csvWriterCodeH = null;
		ICsvListWriter csvWriterCodeL = null;

		final int NO_OF_FILES=13;
	
	    try {
	    	    	
	    	csvWriterTextOnlyH = new CsvListWriter(new FileWriter("E:/Projects/SOContentQualityResources/CSVData/C_Sharp/MetricResults/TextOnlyFile/TextReadability/Id_Have_Code_Score_GT_0.csv"),CsvPreference.STANDARD_PREFERENCE);
	    	csvWriterTextOnlyL = new CsvListWriter(new FileWriter("E:/Projects/SOContentQualityResources/CSVData/C_Sharp/MetricResults/TextOnlyFile/TextReadability/Id_Have_Code_Score_LT_0.csv"),CsvPreference.STANDARD_PREFERENCE);
	    	
	    	csvWriterCodeH = new CsvListWriter(new FileWriter("E:/Projects/SOContentQualityResources/CSVData/C_Sharp/MetricResults/TextCodeFile/CodeReadability/Id_Have_No_Code_Score_GT_0.csv"),CsvPreference.STANDARD_PREFERENCE);
	    	csvWriterCodeL = new CsvListWriter(new FileWriter("E:/Projects/SOContentQualityResources/CSVData/C_Sharp/MetricResults/TextCodeFile/CodeReadability/Id_Have_No_Code_Score_LT_0.csv"),CsvPreference.STANDARD_PREFERENCE);
	    		
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
		        		         	
		        	if((Integer.valueOf(score)> 0) && (bodyCode != null && !bodyCode.isEmpty())){
		        		// Text File Creation and Writing Text        		
		        		csvWriterTextOnlyH.write(id, 0);
		        	}
		        	
		        	else if((Integer.valueOf(score)< 0) && (bodyCode != null && !bodyCode.isEmpty())){
		        		// Text File Creation and Writing Text
		        		csvWriterTextOnlyL.write(id, 0);
		        	}
		        	else if((Integer.valueOf(score)> 0) && !(bodyCode != null && !bodyCode.isEmpty())){
		        		// Text File Creation and Writing Text
		        		csvWriterCodeH.write(id, 0);
		        	}
		        	else if((Integer.valueOf(score)< 0) && !(bodyCode != null && !bodyCode.isEmpty())){
		        		// Text File Creation and Writing Text
		        		csvWriterCodeL.write(id, 0);
		        	}
		        	
		        }
	    		
	    	}
	    	
	    	csvWriterTextOnlyH.close();
	    	csvWriterTextOnlyL.close();
	    	csvWriterCodeH.close();
	    	csvWriterCodeL.close();

	        System.out.println("C# ID Read Finished Successfully!!");
	
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

class JavascriptIDReader extends Thread {
	public void run(){
		
		ICsvListReader listReader = null;
		ICsvListWriter csvWriterTextOnlyH = null;
		ICsvListWriter csvWriterTextOnlyL = null;
		
		ICsvListWriter csvWriterCodeH = null;
		ICsvListWriter csvWriterCodeL = null;

		final int NO_OF_FILES=15;
	
	    try {
	    	    	
	    	csvWriterTextOnlyH = new CsvListWriter(new FileWriter("E:/Projects/SOContentQualityResources/CSVData/Javascript/MetricResults/TextOnlyFile/TextReadability/Id_Have_Code_Score_GT_0.csv"),CsvPreference.STANDARD_PREFERENCE);
	    	csvWriterTextOnlyL = new CsvListWriter(new FileWriter("E:/Projects/SOContentQualityResources/CSVData/Javascript/MetricResults/TextOnlyFile/TextReadability/Id_Have_Code_Score_LT_0.csv"),CsvPreference.STANDARD_PREFERENCE);
	    	
	    	csvWriterCodeH = new CsvListWriter(new FileWriter("E:/Projects/SOContentQualityResources/CSVData/Javascript/MetricResults/TextCodeFile/CodeReadability/Id_Have_No_Code_Score_GT_0.csv"),CsvPreference.STANDARD_PREFERENCE);
	    	csvWriterCodeL = new CsvListWriter(new FileWriter("E:/Projects/SOContentQualityResources/CSVData/Javascript/MetricResults/TextCodeFile/CodeReadability/Id_Have_No_Code_Score_LT_0.csv"),CsvPreference.STANDARD_PREFERENCE);
	    	   	
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
		        		         	
		        	if((Integer.valueOf(score)> 0) && (bodyCode != null && !bodyCode.isEmpty())){
		        		// Text File Creation and Writing Text        		
		        		csvWriterTextOnlyH.write(id, 0);
		        	}
		        	
		        	else if((Integer.valueOf(score)< 0) && (bodyCode != null && !bodyCode.isEmpty())){
		        		// Text File Creation and Writing Text
		        		csvWriterTextOnlyL.write(id, 0);
		        	}
		        	else if((Integer.valueOf(score)> 0) && !(bodyCode != null && !bodyCode.isEmpty())){
		        		// Text File Creation and Writing Text
		        		csvWriterCodeH.write(id, 0);
		        	}
		        	else if((Integer.valueOf(score)< 0) && !(bodyCode != null && !bodyCode.isEmpty())){
		        		// Text File Creation and Writing Text
		        		csvWriterCodeL.write(id, 0);
		        	}
		        	
		        }
	    		
	    	}
	    	
	    	csvWriterTextOnlyH.close();
	    	csvWriterTextOnlyL.close();
	    	csvWriterCodeH.close();
	    	csvWriterCodeL.close();

	        System.out.println("Javascript ID Read Finished Successfully!!");
	
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

class PythonIDReader extends Thread {
	public void run(){
		
		ICsvListReader listReader = null;
		ICsvListWriter csvWriterTextOnlyH = null;
		ICsvListWriter csvWriterTextOnlyL = null;
		
		ICsvListWriter csvWriterCodeH = null;
		ICsvListWriter csvWriterCodeL = null;

		final int NO_OF_FILES=15;
	
	    try {
	    	    	
	    	csvWriterTextOnlyH = new CsvListWriter(new FileWriter("E:/Projects/SOContentQualityResources/CSVData/Python/MetricResults/TextOnlyFile/TextReadability/Id_Have_Code_Score_GT_0.csv"),CsvPreference.STANDARD_PREFERENCE);
	    	csvWriterTextOnlyL = new CsvListWriter(new FileWriter("E:/Projects/SOContentQualityResources/CSVData/Python/MetricResults/TextOnlyFile/TextReadability/Id_Have_Code_Score_LT_0.csv"),CsvPreference.STANDARD_PREFERENCE);
	    	
	    	csvWriterCodeH = new CsvListWriter(new FileWriter("E:/Projects/SOContentQualityResources/CSVData/Python/MetricResults/TextCodeFile/CodeReadability/Id_Have_No_Code_Score_GT_0.csv"),CsvPreference.STANDARD_PREFERENCE);
	    	csvWriterCodeL = new CsvListWriter(new FileWriter("E:/Projects/SOContentQualityResources/CSVData/Python/MetricResults/TextCodeFile/CodeReadability/Id_Have_No_Code_Score_LT_0.csv"),CsvPreference.STANDARD_PREFERENCE);
	    	
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
		        		         	
		        	if((Integer.valueOf(score)> 0) && (bodyCode != null && !bodyCode.isEmpty())){
		        		// Text File Creation and Writing Text        		
		        		csvWriterTextOnlyH.write(id, 0);
		        	}
		        	
		        	else if((Integer.valueOf(score)< 0) && (bodyCode != null && !bodyCode.isEmpty())){
		        		// Text File Creation and Writing Text
		        		csvWriterTextOnlyL.write(id, 0);
		        	}
		        	else if((Integer.valueOf(score)> 0) && !(bodyCode != null && !bodyCode.isEmpty())){
		        		// Text File Creation and Writing Text
		        		csvWriterCodeH.write(id, 0);
		        	}
		        	else if((Integer.valueOf(score)< 0) && !(bodyCode != null && !bodyCode.isEmpty())){
		        		// Text File Creation and Writing Text
		        		csvWriterCodeL.write(id, 0);
		        	}
		        	
		        }
	    		
	    	}
	    	
	    	csvWriterTextOnlyH.close();
	    	csvWriterTextOnlyL.close();
	    	csvWriterCodeH.close();
	    	csvWriterCodeL.close();

	        System.out.println("Python ID Read Finished Successfully!!");
	
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
