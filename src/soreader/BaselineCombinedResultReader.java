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

public class BaselineCombinedResultReader {
	public static void main(String[] args) {
		CombinedBaselineResults reader = new CombinedBaselineResults();
		reader.start();
		
	}

}

class CombinedBaselineResults extends Thread {
	
	public void run(){
	
		ICsvListReader listTQ = null;
		ICsvListReader listTR = null;
		ICsvListReader listTE = null;
		ICsvListReader listSM = null;
		ICsvListReader listME = null;

		ICsvListReader listTQL = null;
		ICsvListReader listTRL = null;
		ICsvListReader listTEL = null;
		ICsvListReader listSML = null;
		ICsvListReader listMEL = null;
		
		ICsvListWriter csvWriter = null;
			
	
	    try {
	    	    	
	    		csvWriter= new CsvListWriter(new FileWriter("E:/Projects/SOContentQualityResources/FeatureValues/Case-A(OnlyText)/CombinedFeatures/Baseline_TextCode_Combined_Features_V1.csv"),CsvPreference.STANDARD_PREFERENCE);
	    		csvWriter.write("Id","TitleQuality","GF","FK","ARI","SMOG","FR","CL","TermEntropy","MetricEntropy","bodyLen","emailCount","lcasePercent","spaceCount","tagCount","txtSpeakCount","titleLength","isCapTitle","ucasePercent","urlCount","locPercent","senteCount","wCount","QuestionType");
		    	//Reading CSV File
		        listTQ = new CsvListReader(new FileReader("E:/Projects/SOContentQualityResources/FeatureValues/Case-A(OnlyText)/CombinedLanguages/TitleQuality/Title_Quality_Score_GT_0.csv"), CsvPreference.STANDARD_PREFERENCE);
		        listTR = new CsvListReader(new FileReader("E:/Projects/SOContentQualityResources/FeatureValues/Case-A(OnlyText)/CombinedLanguages/BaselineTextReadability/Baseline_TextReadability_Score_GT_0.csv"), CsvPreference.STANDARD_PREFERENCE);
		        listTE = new CsvListReader(new FileReader("E:/Projects/SOContentQualityResources/FeatureValues/Case-A(OnlyText)/CombinedLanguages/TermEntropy/TermEntropy_Score_GT_0.csv"), CsvPreference.STANDARD_PREFERENCE);
		        listSM = new CsvListReader(new FileReader("E:/Projects/SOContentQualityResources/FeatureValues/Case-A(OnlyText)/CombinedLanguages/SOMetric/SOMetric_Score_GT_0.csv"), CsvPreference.STANDARD_PREFERENCE);
		        listME = new CsvListReader(new FileReader("E:/Projects/SOContentQualityResources/FeatureValues/Case-A(OnlyText)/CombinedLanguages/MetricEntropy/MetricEntropy_Score_GT_0.csv"), CsvPreference.STANDARD_PREFERENCE);
	        
		        listTQ.getHeader(true); // skip the header (can't be used with CsvListReader)
		        listTR.getHeader(true);
		        listTE.getHeader(true);
		        listSM.getHeader(true);
		        listME.getHeader(true);

		        
		        final CellProcessor[] processorsTQ = getProcessorsTQ();               
		        List<Object> questionListTQ;
		        
		        final CellProcessor[] processorsTR = getProcessorsTR();               
		        List<Object> questionListTR;
		        
		        final CellProcessor[] processorsTE = getProcessorsTE();               
		        List<Object> questionListTE;
		        
		        final CellProcessor[] processorsSM = getProcessorsSM();               
		        List<Object> questionListSM;
		        
		        final CellProcessor[] processorsME = getProcessorsME();               
		        List<Object> questionListME;
		        
		        
		        //**************************** LOW *************************************//
		        
		        listTQL = new CsvListReader(new FileReader("E:/Projects/SOContentQualityResources/FeatureValues/Case-A(OnlyText)/CombinedLanguages/TitleQuality/Title_Quality_Score_LT_0.csv"), CsvPreference.STANDARD_PREFERENCE);
		        listTRL = new CsvListReader(new FileReader("E:/Projects/SOContentQualityResources/FeatureValues/Case-A(OnlyText)/CombinedLanguages/BaselineTextReadability/Baseline_TextReadability_Score_LT_0.csv"), CsvPreference.STANDARD_PREFERENCE);
		        listTEL = new CsvListReader(new FileReader("E:/Projects/SOContentQualityResources/FeatureValues/Case-A(OnlyText)/CombinedLanguages/TermEntropy/TermEntropy_Score_LT_0.csv"), CsvPreference.STANDARD_PREFERENCE);
		        listSML = new CsvListReader(new FileReader("E:/Projects/SOContentQualityResources/FeatureValues/Case-A(OnlyText)/CombinedLanguages/SOMetric/SOMetric_Score_LT_0.csv"), CsvPreference.STANDARD_PREFERENCE);
		        listMEL = new CsvListReader(new FileReader("E:/Projects/SOContentQualityResources/FeatureValues/Case-A(OnlyText)/CombinedLanguages/MetricEntropy/MetricEntropy_Score_LT_0.csv"), CsvPreference.STANDARD_PREFERENCE);
	        
		        listTQL.getHeader(true); // skip the header (can't be used with CsvListReader)
		        listTRL.getHeader(true);
		        listTEL.getHeader(true);
		        listSML.getHeader(true);
		        listMEL.getHeader(true);
		        
		        final CellProcessor[] processorsTQL = getProcessorsTQL();               
		        List<Object> questionListTQL;
		        
		        final CellProcessor[] processorsTRL = getProcessorsTRL();               
		        List<Object> questionListTRL;
		        
		        final CellProcessor[] processorsTEL = getProcessorsTEL();               
		        List<Object> questionListTEL;
		        
		        final CellProcessor[] processorsSML = getProcessorsSML();               
		        List<Object> questionListSML;
		        
		        final CellProcessor[] processorsMEL = getProcessorsMEL();               
		        List<Object> questionListMEL;
		        
		        int count=0; float sum = 0.0f, avg = 0.0f; 
		        
		        while( ((questionListTQ = listTQ.read(processorsTQ)) != null) && ((questionListTR = listTR.read(processorsTR)) != null) && ((questionListTE = listTE.read(processorsTE)) != null) && ((questionListSM = listSM.read(processorsSM)) != null)&& ((questionListME = listME.read(processorsME))!= null) ) {
		        	 
		           	String id="";                          		// int
		        	String scoreTQ="-9999";                       // int
//		        	String scoreTR="-9999";
		        	String scoreTE="-9999";
		        	String scoreSM1="-9999";
		        	String scoreSM2="-9999";
		        	String scoreSM3="-9999";
		        	String scoreSM4="-9999";
		        	String scoreSM5="-9999";
		        	String scoreSM6="-9999";
		        	String scoreSM7="-9999";
		        	String scoreSM8="-9999";
		        	String scoreSM9="-9999";
		        	String scoreSM10="-9999";
		        	String scoreSM11="-9999";
		        	String scoreSM12="-9999";
		        	String scoreSM13="-9999";		        	
		        	String scoreME="-9999";
		        	
		        	String GF="";                          		    // int
		            String FK="";                  		    // float
		            String ARI="";
		            String SMOG="";
		            String FR="";
		            String CL="";
       			        	
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
		        		GF=questionListTR.get(1).toString();
					}catch (Exception e) {

					}
		        	try {
		        		FK=questionListTR.get(2).toString();
					}catch (Exception e) {

					}
		        	try {
		        		ARI=questionListTR.get(3).toString();
					}catch (Exception e) {

					}
		        	try {
		        		SMOG=questionListTR.get(4).toString();
					}catch (Exception e) {

					}
		        	try {
		        		FR=questionListTR.get(5).toString();
					}catch (Exception e) {

					}
		        	try {
		        		CL=questionListTR.get(6).toString();
					}catch (Exception e) {

					}
		        	try {
		        		scoreTE=questionListTE.get(1).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	try {
		        		scoreSM1=questionListSM.get(1).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	try {
		        		scoreSM2=questionListSM.get(2).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	try {
		        		scoreSM3=questionListSM.get(3).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	try {
		        		scoreSM4=questionListSM.get(4).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	try {
		        		scoreSM5=questionListSM.get(5).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	try {
		        		scoreSM6=questionListSM.get(6).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	try {
		        		scoreSM7=questionListSM.get(7).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	try {
		        		scoreSM8=questionListSM.get(8).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	try {
		        		scoreSM9=questionListSM.get(9).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	try {
		        		scoreSM10=questionListSM.get(10).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	try {
		        		scoreSM11=questionListSM.get(11).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	try {
		        		scoreSM12=questionListSM.get(12).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	try {
		        		scoreSM13=questionListSM.get(13).toString();
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
		        			        	
		        	
//		        	if(Integer.valueOf(score)>10 && Integer.valueOf(score)<=100000000 &&  Float.valueOf(scoreTopic)>0) {
//		        		count++; sum = sum+Float.valueOf(scoreTopic);
//		        		System.out.println(count+" "+sum);
//		        		System.out.println(sum/(float)count);
//		        	}
//		        	
		        	csvWriter.write(id, scoreTQ, GF,FK,ARI,SMOG,FR,CL, scoreTE, scoreSM1,scoreSM2,scoreSM3,scoreSM4,scoreSM5,scoreSM6,scoreSM7,scoreSM8,scoreSM9,scoreSM10,scoreSM11,scoreSM12,scoreSM13,scoreME, 1);
		        	
//		        	count++;
//		        	if(count>500) break;
		        	
		        }
		        
//		        System.out.println(sum/(float)count);
		        
		        
		        // *************************************************** LOW ************************************//
		        
		        count=0;
		        sum = 0.0f;
		        
		        while( ((questionListTQL = listTQL.read(processorsTQL)) != null) && ((questionListTRL = listTRL.read(processorsTRL)) != null) && ((questionListTEL = listTEL.read(processorsTEL)) != null) && ((questionListSML = listSML.read(processorsSML)) != null)&& ((questionListMEL = listMEL.read(processorsMEL)) != null) ) {
		        	 
		        	String id="";                          		// int
		        	String scoreTQ="-9999";                       // int
//		        	String scoreTR="-9999";
		        	String scoreTE="-9999";
		        	String scoreSM1="-9999";
		        	String scoreSM2="-9999";
		        	String scoreSM3="-9999";
		        	String scoreSM4="-9999";
		        	String scoreSM5="-9999";
		        	String scoreSM6="-9999";
		        	String scoreSM7="-9999";
		        	String scoreSM8="-9999";
		        	String scoreSM9="-9999";
		        	String scoreSM10="-9999";
		        	String scoreSM11="-9999";
		        	String scoreSM12="-9999";
		        	String scoreSM13="-9999";		        	
		        	String scoreME="-9999";
		        	String GF="";                          		    // int
		            String FK="";                  		    // float
		            String ARI="";
		            String SMOG="";
		            String FR="";
		            String CL="";
       			        	
		        	try {
		        		id=questionListTQL.get(0).toString();
					}catch (Exception e) {
						// TODO: handle exception
					}	
		        	
		        	try {
		        		scoreTQ=questionListTQL.get(1).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	try {
		        		GF=questionListTRL.get(1).toString();
					}catch (Exception e) {

					}
		        	try {
		        		FK=questionListTRL.get(2).toString();
					}catch (Exception e) {

					}
		        	try {
		        		ARI=questionListTRL.get(3).toString();
					}catch (Exception e) {

					}
		        	try {
		        		SMOG=questionListTRL.get(4).toString();
					}catch (Exception e) {

					}
		        	try {
		        		FR=questionListTRL.get(5).toString();
					}catch (Exception e) {

					}
		        	try {
		        		CL=questionListTRL.get(6).toString();
					}catch (Exception e) {

					}
		        	try {
		        		scoreTE=questionListTEL.get(1).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	try {
		        		scoreSM1=questionListSML.get(1).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	try {
		        		scoreSM2=questionListSML.get(2).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	try {
		        		scoreSM3=questionListSML.get(3).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	try {
		        		scoreSM4=questionListSML.get(4).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	try {
		        		scoreSM5=questionListSML.get(5).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	try {
		        		scoreSM6=questionListSML.get(6).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	try {
		        		scoreSM7=questionListSML.get(7).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	try {
		        		scoreSM8=questionListSML.get(8).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	try {
		        		scoreSM9=questionListSML.get(9).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	try {
		        		scoreSM10=questionListSML.get(10).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	try {
		        		scoreSM11=questionListSML.get(11).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	try {
		        		scoreSM12=questionListSML.get(12).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	try {
		        		scoreSM13=questionListSML.get(13).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	
		        	try {
		        		scoreME=questionListMEL.get(1).toString();
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
		        	csvWriter.write(id, scoreTQ, GF,FK,ARI,SMOG,FR,CL, scoreTE, scoreSM1,scoreSM2,scoreSM3,scoreSM4,scoreSM5,scoreSM6,scoreSM7,scoreSM8,scoreSM9,scoreSM10,scoreSM11,scoreSM12,scoreSM13,scoreME, 0);
		        	
//		        	count++;
//		        	if(count>500) break;
		        	
		        	
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
		    		if( listME != null ) {
		                listME.close();
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
		    		if( listMEL != null ) {
		                listMEL.close();
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
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional(),
	            new Optional()
	  
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
	
private static CellProcessor[] getProcessorsME() {
	    
	    final CellProcessor[] processors = new CellProcessor[] {
	    		new Optional(), // post Id not null
	            new Optional(),
	  
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
            new Optional(),
            new Optional(),
            new Optional(),
            new Optional(),
            new Optional()
  
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

private static CellProcessor[] getProcessorsMEL() {
    
    final CellProcessor[] processors = new CellProcessor[] {
    		new Optional(), // post Id not null
            new Optional(),
  
    };
    
    return processors;
}


}
