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

public class TextCodeCombinedResultReader {
	public static void main(String[] args) {
		CombinedResultTC reader = new CombinedResultTC();
		reader.start();
		
	}

}


//Java 

class CombinedResultTC extends Thread {
	
	public void run(){
	
		ICsvListReader listTQ = null;
		ICsvListReader listCR = null;
		ICsvListReader listTE = null;
		ICsvListReader listSM = null;
		ICsvListReader listPA = null;
		ICsvListReader listEQ = null;
		ICsvListReader listTCR = null;
		ICsvListReader listUA = null;
		ICsvListReader listTR = null;
		ICsvListReader listSO = null;
		ICsvListReader listME = null;
		ICsvListReader listTL = null;
		

		ICsvListReader listTQL = null;
		ICsvListReader listCRL = null;
		ICsvListReader listTEL = null;
		ICsvListReader listSML = null;
		ICsvListReader listPAL = null;
		ICsvListReader listEQL = null;
		ICsvListReader listTCRL = null;
		ICsvListReader listUAL = null;
		ICsvListReader listTRL = null;
		ICsvListReader listSOL = null;
		ICsvListReader listMEL = null;
		ICsvListReader listTLL = null;
		
		ICsvListWriter csvWriter = null;
					
	    try {
	    	    	
	    		csvWriter= new CsvListWriter(new FileWriter("E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/CombinedFeatures/TextCode_Combined_V11.csv"),CsvPreference.STANDARD_PREFERENCE);
	    		csvWriter.write("Id","TitleQuality", "TextReadability","TagEntropy","Sentiment","CodeReadability","ExpQuality","CodeTextRatio","Parsability","Understandability","MetricEntropy","TextLength","BodyLen","tagCount","titleLength","sentenceCount","wordCount","QuestionType");
		    	//Reading CSV File
		        listTQ = new CsvListReader(new FileReader("E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/CombinedLanguages/TitleQuality/Title_Quality_Score_GT_0.csv"), CsvPreference.STANDARD_PREFERENCE);
		        listCR = new CsvListReader(new FileReader("E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/CombinedLanguages/CodeReadability/Code_Readability_Score_GT_0.csv"), CsvPreference.STANDARD_PREFERENCE);
		        listTE = new CsvListReader(new FileReader("E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/CombinedLanguages/TagEntropy/TagEntropy_TextCode_GT_0.csv"), CsvPreference.STANDARD_PREFERENCE);
		        listEQ = new CsvListReader(new FileReader("E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/CombinedLanguages/ROUGE/ROUGE_Score_GT_0.csv"), CsvPreference.STANDARD_PREFERENCE);
		        listTCR = new CsvListReader(new FileReader("E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/CombinedLanguages/TextCodeRatio/TCR_Score_GT_0.csv"), CsvPreference.STANDARD_PREFERENCE);
		        listPA = new CsvListReader(new FileReader("E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/CombinedLanguages/Parsability/Usability_Score_GT_0.csv"), CsvPreference.STANDARD_PREFERENCE);
		        listUA = new CsvListReader(new FileReader("E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/CombinedLanguages/Understandability/APICalls_Score_GT_0.csv"), CsvPreference.STANDARD_PREFERENCE);
		        listTR = new CsvListReader(new FileReader("E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/CombinedLanguages/TextReadability/TextReadability_Score_GT_0.csv"), CsvPreference.STANDARD_PREFERENCE);
		        listSO = new CsvListReader(new FileReader("E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/CombinedLanguages/SOMetric/SOMetric_Score_GT_0.csv"), CsvPreference.STANDARD_PREFERENCE);
		        listSM = new CsvListReader(new FileReader("E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/CombinedLanguages/Sentiment/Sentiment_TextCode_GT_0.csv"), CsvPreference.STANDARD_PREFERENCE);
		        listME = new CsvListReader(new FileReader("E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/CombinedLanguages/MetricEntropy/MetricEntropy_Score_GT_0.csv"), CsvPreference.STANDARD_PREFERENCE);
		        listTL = new CsvListReader(new FileReader("E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/CombinedLanguages/TextLength/TextLength_Score_GT_0.csv"), CsvPreference.STANDARD_PREFERENCE);
		        	        
		        listTQ.getHeader(true); // skip the header (can't be used with CsvListReader)
		        listCR.getHeader(true);
		        listTE.getHeader(true);
		        listEQ.getHeader(true);
		        listTCR.getHeader(true);
		        listPA.getHeader(true);
		        listUA.getHeader(true);       
		        listSM.getHeader(true);
		        listTR.getHeader(true);		        
		        listSO.getHeader(true);
		        listME.getHeader(true);
		        listTL.getHeader(true);

		        
		        final CellProcessor[] processorsTQ = getProcessorsTQ();               
		        List<Object> questionListTQ;
		        
		        final CellProcessor[] processorsCR = getProcessorsCR();               
		        List<Object> questionListCR;
		        
		        final CellProcessor[] processorsTE = getProcessorsTE();               
		        List<Object> questionListTE;
		        
		        final CellProcessor[] processorsSM = getProcessorsSM();               
		        List<Object> questionListSM;
		        
		        final CellProcessor[] processorsEQ = getProcessorsEQ();               
		        List<Object> questionListEQ;
		        
		        final CellProcessor[] processorsTCR = getProcessorsTCR();               
		        List<Object> questionListTCR;
		        
		        final CellProcessor[] processorsPA = getProcessorsPA();               
		        List<Object> questionListPA;
		        
		        final CellProcessor[] processorsUA = getProcessorsUA();               
		        List<Object> questionListUA;
		        
		        final CellProcessor[] processorsTR = getProcessorsTR();               
		        List<Object> questionListTR;
		        
		        final CellProcessor[] processorsSO = getProcessorsSO();               
		        List<Object> questionListSO;
		        
		        final CellProcessor[] processorsME = getProcessorsME();               
		        List<Object> questionListME;
		        
		        final CellProcessor[] processorsTL = getProcessorsTL();               
		        List<Object> questionListTL;
		        
		        
		        //**************************** LOW *************************************//
		        
		        listTQL = new CsvListReader(new FileReader("E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/CombinedLanguages/TitleQuality/Title_Quality_Score_LT_0.csv"), CsvPreference.STANDARD_PREFERENCE);
		        listCRL = new CsvListReader(new FileReader("E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/CombinedLanguages/CodeReadability/Code_Readability_Score_LT_0.csv"), CsvPreference.STANDARD_PREFERENCE);
		        listTEL = new CsvListReader(new FileReader("E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/CombinedLanguages/TagEntropy/TagEntropy_TextCode_LT_0.csv"), CsvPreference.STANDARD_PREFERENCE);
		        listEQL = new CsvListReader(new FileReader("E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/CombinedLanguages/ROUGE/ROUGE_Score_LT_0.csv"), CsvPreference.STANDARD_PREFERENCE);
		        listTCRL = new CsvListReader(new FileReader("E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/CombinedLanguages/TextCodeRatio/TCR_Score_LT_0.csv"), CsvPreference.STANDARD_PREFERENCE);
		        listPAL = new CsvListReader(new FileReader("E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/CombinedLanguages/Parsability/Usability_Score_LT_0.csv"), CsvPreference.STANDARD_PREFERENCE);
		        listUAL = new CsvListReader(new FileReader("E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/CombinedLanguages/Understandability/APICalls_Score_LT_0.csv"), CsvPreference.STANDARD_PREFERENCE);
		        listTRL = new CsvListReader(new FileReader("E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/CombinedLanguages/TextReadability/TextReadability_Score_LT_0.csv"), CsvPreference.STANDARD_PREFERENCE);
		        listSOL = new CsvListReader(new FileReader("E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/CombinedLanguages/SOMetric/SOMetric_Score_LT_0.csv"), CsvPreference.STANDARD_PREFERENCE);
		        listSML = new CsvListReader(new FileReader("E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/CombinedLanguages/Sentiment/Sentiment_TextCode_LT_0.csv"), CsvPreference.STANDARD_PREFERENCE);
		        listMEL = new CsvListReader(new FileReader("E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/CombinedLanguages/MetricEntropy/MetricEntropy_Score_LT_0.csv"), CsvPreference.STANDARD_PREFERENCE);
		        listTLL = new CsvListReader(new FileReader("E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/CombinedLanguages/TextLength/TextLength_Score_LT_0.csv"), CsvPreference.STANDARD_PREFERENCE);
		        
		        listTQL.getHeader(true); // skip the header (can't be used with CsvListReader)
		        listCRL.getHeader(true);
		        listTEL.getHeader(true);
		        listEQL.getHeader(true);
		        listTCRL.getHeader(true);
		        listPAL.getHeader(true);
		        listUAL.getHeader(true);       
		        listSML.getHeader(true);
		        listTRL.getHeader(true);
		        listSOL.getHeader(true);
		        listMEL.getHeader(true);
		        listTLL.getHeader(true);
		        
		        final CellProcessor[] processorsTQL = getProcessorsTQL();               
		        List<Object> questionListTQL;
		        
		        final CellProcessor[] processorsCRL = getProcessorsCRL();               
		        List<Object> questionListCRL;
		        
		        final CellProcessor[] processorsTEL = getProcessorsTEL();               
		        List<Object> questionListTEL;
		        
		        final CellProcessor[] processorsSML = getProcessorsSML();               
		        List<Object> questionListSML;
		        
		        final CellProcessor[] processorsEQL = getProcessorsEQL();               
		        List<Object> questionListEQL;
		        
		        final CellProcessor[] processorsTCRL = getProcessorsTCRL();               
		        List<Object> questionListTCRL;
		        
		        final CellProcessor[] processorsPAL = getProcessorsPAL();               
		        List<Object> questionListPAL;
		        
		        final CellProcessor[] processorsUAL = getProcessorsUAL();               
		        List<Object> questionListUAL;
		        
		        final CellProcessor[] processorsTRL = getProcessorsTRL();               
		        List<Object> questionListTRL;
		        
		        final CellProcessor[] processorsSOL = getProcessorsSOL();               
		        List<Object> questionListSOL;
		        
		        final CellProcessor[] processorsMEL = getProcessorsMEL();               
		        List<Object> questionListMEL;
		        
		        final CellProcessor[] processorsTLL = getProcessorsTLL();               
		        List<Object> questionListTLL;
		        
		        
		        int count=0; float sum = 0.0f, avg = 0.0f; 
		        
		        while( ((questionListTQ = listTQ.read(processorsTQ)) != null) && ((questionListCR = listCR.read(processorsCR)) != null) && ((questionListTE = listTE.read(processorsTE)) != null) && ((questionListSM = listSM.read(processorsSM)) != null)&& ((questionListEQ = listEQ.read(processorsEQ)) != null)&& ((questionListTCR = listTCR.read(processorsTCR)) != null)&& ((questionListPA = listPA.read(processorsPA)) != null)&& ((questionListUA = listUA.read(processorsUA)) != null)&& ((questionListTR = listTR.read(processorsTR)) != null) && ((questionListSO = listSO.read(processorsSO)) != null)&& ((questionListME = listME.read(processorsME)) != null)&& ((questionListTL = listTL.read(processorsTL)) != null) ) {
		        	 
		           	String id="";                          		// int
		        	String scoreTQ="-9999";                       // int
		        	String scoreCR="-9999";
		        	String scoreTE="-9999";
		        	String scoreSM="-9999";
		        	String scoreEQ="-9999";                       // int
		        	String scoreTCR="-9999";
		        	String scorePA="-9999";
		        	String scoreUA="-9999";
		        	String scoreTR="-9999";
		        	String scoreME="-9999";
		        	String scoreTL="-9999";
		        	
		        	String f1 ="";
		        	String f2 ="";
		         	String f3 ="";
		         	String f4 ="";
		         	String f5 ="";
		         	
		        	
		        	float scoreEQFloat = 0.0f;
       			        	
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
		        		scoreCR=questionListCR.get(1).toString();
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
		        		scoreEQFloat = 0.0f;
		        		scoreEQ=questionListEQ.get(1).toString();
		        		scoreEQFloat = Float.parseFloat(scoreEQ);
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	try {
		        		scoreTCR=questionListTCR.get(1).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	try {
		        		scorePA=questionListPA.get(1).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	try {
		        		scoreUA=questionListUA.get(1).toString();
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
//		        	"Id","TitleQuality", "CodeReadability","TagEntropy","ExpQuality","CodeTextRatio","Parsability","Understandability","Sentiment", "QuestionType"
		        	
		        	csvWriter.write(id, scoreTQ, scoreTR, scoreTE, scoreSM, scoreCR, scoreEQFloat, scoreTCR, scorePA, scoreUA,scoreME,scoreTL, f1,f2,f3,f4,f5,1);
		        	
//		        	count++;
//		        	if(count>500) break;
		        	
		        }
		        
//		        System.out.println(sum/(float)count);
		        
		        
		        // *************************************************** LOW ************************************//
		        
		        count=0;
		        sum = 0.0f;
		        
		        while( ((questionListTQL = listTQL.read(processorsTQL)) != null) && ((questionListCRL = listCRL.read(processorsCRL)) != null) && ((questionListTEL = listTEL.read(processorsTEL)) != null) && ((questionListSML = listSML.read(processorsSML)) != null)&& ((questionListEQL = listEQL.read(processorsEQL)) != null)&& ((questionListTCRL = listTCRL.read(processorsTCRL)) != null)&& ((questionListPAL = listPAL.read(processorsPAL)) != null)&& ((questionListUAL = listUAL.read(processorsUAL)) != null)&& ((questionListTRL = listTRL.read(processorsTRL)) != null)&& ((questionListSOL = listSOL.read(processorsSOL)) != null)&& ((questionListMEL = listMEL.read(processorsMEL)) != null)&& ((questionListTLL = listTLL.read(processorsTLL)) != null) ) {
		        	 
		        	String idL="";                          		// int
		        	String scoreTQL="-9999";                       // int
		        	String scoreCRL="-9999";
		        	String scoreTEL="-9999";
		        	String scoreSML="-9999";
		        	String scoreEQL="-9999";                       // int
		        	String scoreTCRL="-9999";
		        	String scorePAL="-9999";
		        	String scoreUAL="-9999";
		        	String scoreTRL="-9999";
		        	String scoreMEL="-9999";
		        	String scoreTLL="-9999";


		        	String f1 ="";
		        	String f2 ="";
		         	String f3 ="";
		         	String f4 ="";
		         	String f5 ="";
		        	
		        	float scoreEQLFloat = 0.0f;
       			        	
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
		        		scoreCRL=questionListCRL.get(1).toString();
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
		        		scoreEQLFloat = 0.0f;
		        		scoreEQL=questionListEQL.get(1).toString();
		        		scoreEQLFloat = Float.parseFloat(scoreEQL);
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	try {
		        		scoreTCRL=questionListTCRL.get(1).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	try {
		        		scorePAL=questionListPAL.get(1).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	try {
		        		scoreUAL=questionListUAL.get(1).toString();
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
		        	csvWriter.write(idL, scoreTQL, scoreTRL, scoreTEL, scoreSML, scoreCRL,  scoreEQLFloat, scoreTCRL, scorePAL, scoreUAL,scoreMEL,scoreTLL,f1,f2,f3,f4,f5, 0);
		        	
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
		    		if( listCR != null ) {
		                listCR.close();
		    		}
		    		if( listTE != null ) {
		                listTE.close();
		    		}
		    		if( listSM != null ) {
		                listSM.close();
		    		}
		    		if( listEQ != null ) {
		                listEQ.close();
		    		}
		    		if( listTCR != null ) {
		                listTCR.close();
		    		}
		    		if( listPA != null ) {
		                listPA.close();
		    		}
		    		if( listUA != null ) {
		                listUA.close();
		    		}
		    		if( listTR != null ) {
		                listTR.close();
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
		    		if( listCRL != null ) {
		                listCRL.close();
		    		}
		    		if( listTEL != null ) {
		                listTEL.close();
		    		}
		    		if( listSML != null ) {
		                listSML.close();
		    		}
		    		if( listEQL != null ) {
		                listEQL.close();
		    		}
		    		if( listTCRL != null ) {
		                listTCRL.close();
		    		}
		    		if( listPAL != null ) {
		                listPAL.close();
		    		}
		    		if( listUAL != null ) {
		                listUAL.close();
		    		}
		    		if( listTRL != null ) {
		                listTRL.close();
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
	            new Optional()
	  
	    };
	    
	    return processors;
	}
	private static CellProcessor[] getProcessorsCR() {
        
	    final CellProcessor[] processors = new CellProcessor[] {
	    		new Optional(), // post Id not null
	            new Optional()
	  
	    };
	    
	    return processors;
	}
	private static CellProcessor[] getProcessorsTE() {
	        
		    final CellProcessor[] processors = new CellProcessor[] {
		    		new Optional(), // post Id not null
		            new Optional()
		  
		    };
		    
		    return processors;
		}
	private static CellProcessor[] getProcessorsSM() {
	    
	    final CellProcessor[] processors = new CellProcessor[] {
	    		new Optional(), // post Id not null
	            new Optional()
	  
	    };
	    
	    return processors;
	}
	private static CellProcessor[] getProcessorsEQ() {
	    
	    final CellProcessor[] processors = new CellProcessor[] {
	    		new Optional(), // post Id not null
	            new Optional()
	  
	    };
	    
	    return processors;
	}
	private static CellProcessor[] getProcessorsTCR() {
	    
	    final CellProcessor[] processors = new CellProcessor[] {
	    		new Optional(), // post Id not null
	            new Optional()
	  
	    };
	    
	    return processors;
	}
	private static CellProcessor[] getProcessorsPA() {
	    
	    final CellProcessor[] processors = new CellProcessor[] {
	    		new Optional(), // post Id not null
	            new Optional()
	  
	    };
	    
	    return processors;
	}
	private static CellProcessor[] getProcessorsUA() {
	    
	    final CellProcessor[] processors = new CellProcessor[] {
	    		new Optional(), // post Id not null
	            new Optional()
	  
	    };
	    
	    return processors;
	}
	
	private static CellProcessor[] getProcessorsTR() {
	    
	    final CellProcessor[] processors = new CellProcessor[] {
	    		new Optional(), // post Id not null
	            new Optional()
	  
	    };
	    
	    return processors;
	}
	private static CellProcessor[] getProcessorsME() {
	    
	    final CellProcessor[] processors = new CellProcessor[] {
	    		new Optional(), // post Id not null
	            new Optional()
	  
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
	            new Optional()
	  
	    };
	    
	    return processors;
	}
	private static CellProcessor[] getProcessorsCRL() {
        
	    final CellProcessor[] processors = new CellProcessor[] {
	    		new Optional(), // post Id not null
	            new Optional()
	  
	    };
	    
	    return processors;
	}
	private static CellProcessor[] getProcessorsTEL() {
	        
		    final CellProcessor[] processors = new CellProcessor[] {
		    		new Optional(), // post Id not null
		            new Optional()
		  
		    };
		    
		    return processors;
		}
	private static CellProcessor[] getProcessorsSML() {
	    
	    final CellProcessor[] processors = new CellProcessor[] {
	    		new Optional(), // post Id not null
	            new Optional()
	  
	    };
	    
	    return processors;
	}
	private static CellProcessor[] getProcessorsEQL() {
	    
	    final CellProcessor[] processors = new CellProcessor[] {
	    		new Optional(), // post Id not null
	            new Optional()
	  
	    };
	    
	    return processors;
	}
	private static CellProcessor[] getProcessorsTCRL() {
	    
	    final CellProcessor[] processors = new CellProcessor[] {
	    		new Optional(), // post Id not null
	            new Optional()
	  
	    };
	    
	    return processors;
	}
	private static CellProcessor[] getProcessorsPAL() {
	    
	    final CellProcessor[] processors = new CellProcessor[] {
	    		new Optional(), // post Id not null
	            new Optional()
	  
	    };
	    
	    return processors;
	}
	private static CellProcessor[] getProcessorsUAL() {
	    
	    final CellProcessor[] processors = new CellProcessor[] {
	    		new Optional(), // post Id not null
	            new Optional()
	  
	    };
	    
	    return processors;
	}
	private static CellProcessor[] getProcessorsTRL() {
	    
	    final CellProcessor[] processors = new CellProcessor[] {
	    		new Optional(), // post Id not null
	            new Optional()
	  
	    };
	    
	    return processors;
	}
	private static CellProcessor[] getProcessorsMEL() {
	    
	    final CellProcessor[] processors = new CellProcessor[] {
	    		new Optional(), // post Id not null
	            new Optional()
	  
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
