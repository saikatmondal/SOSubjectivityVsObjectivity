package soreader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvListReader;
import org.supercsv.io.CsvListWriter;
import org.supercsv.io.ICsvListReader;
import org.supercsv.io.ICsvListWriter;
import org.supercsv.prefs.CsvPreference;

public class TextOnlyCombinedResultReader {
	public static void main(String[] args) {
		CombinedResult reader = new CombinedResult();
		reader.start();
		
	}

}


//Java 

class CombinedResult extends Thread {
	
	public void run(){
	
		ICsvListReader listTQ = null;
		ICsvListReader listTR = null;
		ICsvListReader listTE = null;
		ICsvListReader listSM = null;
		ICsvListReader listSO = null;
		ICsvListReader listME = null;
		ICsvListReader listTL = null;

		ICsvListReader listTQL = null;
		ICsvListReader listTRL = null;
		ICsvListReader listTEL = null;
		ICsvListReader listSML = null;
		ICsvListReader listSOL = null;
		ICsvListReader listMEL = null;
		ICsvListReader listTLL = null;

		
		ICsvListWriter csvWriter = null;


	
	    try {
	    	    	
	    		csvWriter= new CsvListWriter(new FileWriter("E:/Projects/SOContentQualityResources/FeatureValues/Case-A(OnlyText)/CombinedFeatures/TextOnly_Combined_V11.csv"),CsvPreference.STANDARD_PREFERENCE);
	    		csvWriter.write("Id","TitleQuality", "TextReadability","TagEntropy","Sentiment","CodeReadability","ExpQuality","CodeTextRatio","Parsability","Understandability","MetricEntropy","TextLength","BodyLen","tagCount","titleLength","sentenceCount","wordCount","QuestionType");
		    	//Reading CSV File
		        listTQ = new CsvListReader(new FileReader("E:/Projects/SOContentQualityResources/FeatureValues/Case-A(OnlyText)/CombinedLanguages/TitleQuality/Title_Quality_Score_GT_0.csv"), CsvPreference.STANDARD_PREFERENCE);
		        listTR = new CsvListReader(new FileReader("E:/Projects/SOContentQualityResources/FeatureValues/Case-A(OnlyText)/CombinedLanguages/TextReadability/Text_Readability_Score_GT_0.csv"), CsvPreference.STANDARD_PREFERENCE);
		        listTE = new CsvListReader(new FileReader("E:/Projects/SOContentQualityResources/FeatureValues/Case-A(OnlyText)/CombinedLanguages/TagEntropy/TagEntropy_Text_GT_0.csv"), CsvPreference.STANDARD_PREFERENCE);        
		        listSO = new CsvListReader(new FileReader("E:/Projects/SOContentQualityResources/FeatureValues/Case-A(OnlyText)/CombinedLanguages/SOMetric/SOMetric_Score_GT_0.csv"), CsvPreference.STANDARD_PREFERENCE);        		        
		        listSM = new CsvListReader(new FileReader("E:/Projects/SOContentQualityResources/FeatureValues/Case-A(OnlyText)/CombinedLanguages/Sentiment/Sentiment_Text_GT_0.csv"), CsvPreference.STANDARD_PREFERENCE);   
		        listME = new CsvListReader(new FileReader("E:/Projects/SOContentQualityResources/FeatureValues/Case-A(OnlyText)/CombinedLanguages/MetricEntropy/MetricEntropy_Score_GT_0.csv"), CsvPreference.STANDARD_PREFERENCE);
		        listTL = new CsvListReader(new FileReader("E:/Projects/SOContentQualityResources/FeatureValues/Case-A(OnlyText)/CombinedLanguages/TextLength/TextLength_Score_GT_0.csv"), CsvPreference.STANDARD_PREFERENCE);
		        
	        
		        listTQ.getHeader(true); // skip the header (can't be used with CsvListReader)
		        listTR.getHeader(true);
		        listTE.getHeader(true);
		        listSO.getHeader(true);
		        listSM.getHeader(true);
		        listME.getHeader(true);
		        listTL.getHeader(true);

		        
		        final CellProcessor[] processorsTQ = getProcessorsTQ();               
		        List<Object> questionListTQ;
		        
		        final CellProcessor[] processorsTR = getProcessorsTR();               
		        List<Object> questionListTR;
		        
		        final CellProcessor[] processorsTE = getProcessorsTE();               
		        List<Object> questionListTE;
		        
		        final CellProcessor[] processorsSM = getProcessorsSM();               
		        List<Object> questionListSM;
		        
		        final CellProcessor[] processorsSO = getProcessorsSO();               
		        List<Object> questionListSO;
		        
		        final CellProcessor[] processorsME = getProcessorsME();               
		        List<Object> questionListME;
		        
		        final CellProcessor[] processorsTL = getProcessorsTL();               
		        List<Object> questionListTL;
		        
		        
		        //**************************** LOW *************************************//
		        
		        listTQL = new CsvListReader(new FileReader("E:/Projects/SOContentQualityResources/FeatureValues/Case-A(OnlyText)/CombinedLanguages/TitleQuality/Title_Quality_Score_LT_0.csv"), CsvPreference.STANDARD_PREFERENCE);
		        listTRL = new CsvListReader(new FileReader("E:/Projects/SOContentQualityResources/FeatureValues/Case-A(OnlyText)/CombinedLanguages/TextReadability/Text_Readability_Score_LT_0.csv"), CsvPreference.STANDARD_PREFERENCE);
		        listTEL = new CsvListReader(new FileReader("E:/Projects/SOContentQualityResources/FeatureValues/Case-A(OnlyText)/CombinedLanguages/TagEntropy/TagEntropy_Text_lT_0.csv"), CsvPreference.STANDARD_PREFERENCE);
		        
		        listSOL = new CsvListReader(new FileReader("E:/Projects/SOContentQualityResources/FeatureValues/Case-A(OnlyText)/CombinedLanguages/SOMetric/SOMetric_Score_LT_0.csv"), CsvPreference.STANDARD_PREFERENCE);
		        
		        listSML = new CsvListReader(new FileReader("E:/Projects/SOContentQualityResources/FeatureValues/Case-A(OnlyText)/CombinedLanguages/Sentiment/Sentiment_Text_LT_0.csv"), CsvPreference.STANDARD_PREFERENCE);
		        
		        listMEL = new CsvListReader(new FileReader("E:/Projects/SOContentQualityResources/FeatureValues/Case-A(OnlyText)/CombinedLanguages/MetricEntropy/MetricEntropy_Score_LT_0.csv"), CsvPreference.STANDARD_PREFERENCE);
		        listTLL = new CsvListReader(new FileReader("E:/Projects/SOContentQualityResources/FeatureValues/Case-A(OnlyText)/CombinedLanguages/TextLength/TextLength_Score_LT_0.csv"), CsvPreference.STANDARD_PREFERENCE);

		        
		        listTQL.getHeader(true); // skip the header (can't be used with CsvListReader)
		        listTRL.getHeader(true);
		        listTEL.getHeader(true);
		        listSML.getHeader(true);
		        listSOL.getHeader(true);
		        listMEL.getHeader(true);
		        listTLL.getHeader(true);
		        
		        final CellProcessor[] processorsTQL = getProcessorsTQL();               
		        List<Object> questionListTQL;
		        
		        final CellProcessor[] processorsTRL = getProcessorsTRL();               
		        List<Object> questionListTRL;
		        
		        final CellProcessor[] processorsTEL = getProcessorsTEL();               
		        List<Object> questionListTEL;
		        
		        final CellProcessor[] processorsSML = getProcessorsSML();               
		        List<Object> questionListSML;
		        
		        final CellProcessor[] processorsSOL = getProcessorsSOL();               
		        List<Object> questionListSOL;
		        
		        final CellProcessor[] processorsMEL = getProcessorsMEL();               
		        List<Object> questionListMEL;
		        
		        final CellProcessor[] processorsTLL = getProcessorsTLL();               
		        List<Object> questionListTLL;
		        
		        int count=0; float sum = 0.0f, avg = 0.0f; 
		        
		        while( ((questionListTQ = listTQ.read(processorsTQ)) != null) && ((questionListTR = listTR.read(processorsTR)) != null) && ((questionListTE = listTE.read(processorsTE)) != null) && ((questionListSO = listSO.read(processorsSO)) != null) && ((questionListSM = listSM.read(processorsSM)) != null) && ((questionListME = listME.read(processorsME)) != null)&& ((questionListTL = listTL.read(processorsTL)) != null) ) {
		        	 
		           	String id="";                          		// int
		        	String scoreTQ="-9999";                       // int
		        	String scoreTR="-9999";
		        	String scoreTE="-9999";
		        	String scoreSM="-9999";
		        	String scoreME="-9999";
		        	String scoreTL="-9999";
		        	String f1 ="";
		        	String f2 ="";
		         	String f3 ="";
		         	String f4 ="";
		         	String f5 ="";
		         	
		        	
       			        	
		        	try {
		        		id=questionListTQ.get(0).toString();
					}catch (Exception e) {
						// TODO: handle exception
					}	
		        	
		        	try {
		        		scoreTQ=questionListTQ.get(1).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	try {
		        		scoreTR=questionListTR.get(1).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	try {
		        		scoreTE=questionListTE.get(1).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	try {
		        		scoreSM=questionListSM.get(1).toString().trim();
		        		if(scoreSM.equals("NEU")) scoreSM = "0";
		        		else if(scoreSM.equals("POS")) scoreSM = "1";
		        		else if(scoreSM.equals("NEG")) scoreSM = "2";
		        		else if(scoreSM.equals("MIX")) scoreSM = "4";
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	try {
		        		scoreME=questionListME.get(1).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	try {
		        		scoreTL=questionListTL.get(1).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	try {
		        		f1=questionListSO.get(1).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	try {
		        		f2=questionListSO.get(5).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}	
		        	try {
		        		f3=questionListSO.get(7).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}	
		        	try {
		        		f4=questionListSO.get(12).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}	
		        	try {
		        		f5=questionListSO.get(13).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}	
		        			        	
		        	
//		        	if(Integer.valueOf(score)>10 && Integer.valueOf(score)<=100000000 &&  Float.valueOf(scoreTopic)>0) {
//		        		count++; sum = sum+Float.valueOf(scoreTopic);
//		        		System.out.println(count+" "+sum);
//		        		System.out.println(sum/(float)count);
//		        	}
//		        	
		        	csvWriter.write(id, scoreTQ, scoreTR, scoreTE,scoreSM,"0","0","0","0","0",scoreME,scoreTL,f1,f2,f3,f4,f5, 1);
		        	
//		        	count++;
//		        	if(count>500) break;
		        	
		        }
		        
//		        System.out.println(sum/(float)count);
		        
		        
		        // *************************************************** LOW ************************************//
		        
		        count=0;
		        sum = 0.0f;
		        
		        while( ((questionListTQL = listTQL.read(processorsTQL)) != null) && ((questionListTRL = listTRL.read(processorsTRL)) != null) && ((questionListTEL = listTEL.read(processorsTEL)) != null) && ((questionListSML = listSML.read(processorsSML)) != null)&& ((questionListSOL = listSOL.read(processorsSOL)) != null)&& ((questionListMEL = listMEL.read(processorsMEL)) != null)&& ((questionListTLL = listTLL.read(processorsTLL)) != null) ) {
		        	 
		           	String idL="";                          		// int
		        	String scoreTQL="-9999";                       // int
		        	String scoreTRL="-9999";
		        	String scoreTEL="-9999";
		        	String scoreSML="-9999";
		        	String scoreMEL="-9999";
		        	String scoreTLL="-9999";
		        	String f1 ="";
		        	String f2 ="";
		         	String f3 ="";
		         	String f4 ="";
		         	String f5 ="";
       			        	
		        	try {
		        		idL=questionListTQL.get(0).toString();
					}catch (Exception e) {
						// TODO: handle exception
					}	
		        	
		        	try {
		        		scoreTQL=questionListTQL.get(1).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	try {
		        		scoreTRL=questionListTRL.get(1).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	try {
		        		scoreTEL=questionListTEL.get(1).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	try {
		        		scoreSML=questionListSML.get(1).toString().trim();
		        		if(scoreSML.equals("NEU")) scoreSML = "0";
		        		else if(scoreSML.equals("POS")) scoreSML = "1";
		        		else if(scoreSML.equals("NEG")) scoreSML = "2";
		        		else if(scoreSML.equals("MIX")) scoreSML = "4";
		        		
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	try {
		        		scoreMEL=questionListMEL.get(1).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	try {
		        		scoreTLL=questionListTLL.get(1).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	try {
		        		f1=questionListSOL.get(1).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	try {
		        		f2=questionListSOL.get(5).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}	
		        	try {
		        		f3=questionListSOL.get(7).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}	
		        	try {
		        		f4=questionListSOL.get(12).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}	
		        	try {
		        		f5=questionListSOL.get(13).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}	
		        	
//		        	if(Integer.valueOf(scoreL)<0 && Integer.valueOf(scoreL)> -1000000 && Float.valueOf(scoreTopicL)>0) {
//		        		count++; sum = sum+Float.valueOf(scoreTopicL);
//		        		System.out.println(count+" "+sum);
//		        		System.out.println(sum/(float)count);
//		        	}
//		        	if(count>500) break;
		        	csvWriter.write(idL, scoreTQL, scoreTRL, scoreTEL, scoreSML,"0","0","0","0","0",scoreMEL,scoreTLL,f1,f2,f3,f4,f5,0);
		        	
		        }
//		        System.out.println(sum/(float)count);
		        
	    		csvWriter.close();
	    		System.out.println("Results Successfully Combilned!!");
	
		    }catch (Exception e) {
				// TODO: handle exception
			}
		    finally {
		    	
		    	try {
		    		if( listTQ != null ) {
		                listTQ.close();
		    		}
		    		if( listTR != null ) {
		                listTR.close();
		    		}
		    		if( listTE != null ) {
		                listTE.close();
		    		}
		    		if( listSM != null ) {
		                listSM.close();
		    		}
		    		if( listSO != null ) {
		                listSO.close();
		    		}
		    		if( listME != null ) {
		                listME.close();
		    		}
		    		if( listTL != null ) {
		                listTL.close();
		    		}
		    		if( listTQL != null ) {
		                listTQL.close();
		    		}
		    		if( listTRL != null ) {
		                listTRL.close();
		    		}
		    		if( listTEL != null ) {
		                listTEL.close();
		    		}
		    		if( listSML != null ) {
		                listSML.close();
		    		}
		    		if( listSOL != null ) {
		                listSOL.close();
		    		}
		    		if( listMEL != null ) {
		                listMEL.close();
		    		}
		    		if( listTLL != null ) {
		                listTLL.close();
		    		}
				} catch (Exception e2) {
					// TODO: handle exception
				}
		    }
	    

	}

	private static CellProcessor[] getProcessorsTQ() {
		         
	    final CellProcessor[] processors = new CellProcessor[] {
	    		new Optional(), // post Id not null
	            new Optional(),
	  
	    };
	    
	    return processors;
	}
	private static CellProcessor[] getProcessorsTR() {
        
	    final CellProcessor[] processors = new CellProcessor[] {
	    		new Optional(), // post Id not null
	            new Optional(),
	  
	    };
	    
	    return processors;
	}
	private static CellProcessor[] getProcessorsTE() {
	        
		    final CellProcessor[] processors = new CellProcessor[] {
		    		new Optional(), // post Id not null
		            new Optional(),
		  
		    };
		    
		    return processors;
		}
	private static CellProcessor[] getProcessorsSM() {
	    
	    final CellProcessor[] processors = new CellProcessor[] {
	    		new Optional(), // post Id not null
	            new Optional(),
	  
	    };
	    
	    return processors;
	}
	private static CellProcessor[] getProcessorsME() {
	    
	    final CellProcessor[] processors = new CellProcessor[] {
	    		new Optional(), // post Id not null
	            new Optional(),
	  
	    };
	    
	    return processors;
	}
	private static CellProcessor[] getProcessorsTL() {
	    
	    final CellProcessor[] processors = new CellProcessor[] {
	    		new Optional(), // post Id not null
	            new Optional(),
	  
	    };
	    
	    return processors;
	}
	private static CellProcessor[] getProcessorsSO() {
	    
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
	            new Optional()
	  
	    };
	    
	    return processors;
	}
	
	// ****************************** low **********************************//
	private static CellProcessor[] getProcessorsTQL() {
        
	    final CellProcessor[] processors = new CellProcessor[] {
	    		new Optional(), // post Id not null
	            new Optional(),
	  
	    };
	    
	    return processors;
	}
	private static CellProcessor[] getProcessorsTRL() {
        
	    final CellProcessor[] processors = new CellProcessor[] {
	    		new Optional(), // post Id not null
	            new Optional(),
	  
	    };
	    
	    return processors;
	}
	private static CellProcessor[] getProcessorsTEL() {
	        
		    final CellProcessor[] processors = new CellProcessor[] {
		    		new Optional(), // post Id not null
		            new Optional(),
		  
		    };
		    
		    return processors;
		}
	private static CellProcessor[] getProcessorsSML() {
	    
	    final CellProcessor[] processors = new CellProcessor[] {
	    		new Optional(), // post Id not null
	            new Optional(),
	  
	    };
	    
	    return processors;
	}
	private static CellProcessor[] getProcessorsMEL() {
	    
	    final CellProcessor[] processors = new CellProcessor[] {
	    		new Optional(), // post Id not null
	            new Optional(),
	  
	    };
	    
	    return processors;
	}
	private static CellProcessor[] getProcessorsTLL() {
	    
	    final CellProcessor[] processors = new CellProcessor[] {
	    		new Optional(), // post Id not null
	            new Optional(),
	  
	    };
	    
	    return processors;
	}
	private static CellProcessor[] getProcessorsSOL() {
	    
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
	            new Optional()
	  
	    };
	    
	    return processors;
	}


}
