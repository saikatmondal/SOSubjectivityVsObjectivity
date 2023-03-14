
package datacombiner;

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

public class GrandCombiner {
	public static void main(String[] args) throws Exception {
		BothCombiner obj = new BothCombiner();
		obj.combineResults();
	}

}

class BothCombiner{
	
public void combineResults() throws Exception {
	

	ICsvListReader listReader = null;
	ICsvListReader listReaderBaseline = null;
	ICsvListWriter csvWriter = null;
	
	final int NO_OF_FILES = 2;
    
    try {
    	    	
        csvWriter = new CsvListWriter(new FileWriter("E:/Projects/SOContentQualityResources/FeatureValues/Case-C(Both)/Reduced_Combined_V12.csv"),CsvPreference.STANDARD_PREFERENCE);

//        csvWriter.write("Id","TitleQuality", "TextReadability","TagEntropy", "Sentiment","CodeReadability","ExpQuality","CodeTextRatio","Parsability","Understandability","MetricEntropy","TextLength","EmailCount","lcasePercent","spaceCount","tagCount","spkCount","titleLen","capTitle","ucasePercent","urlCount","QuestionType");
        csvWriter.write("Id","TitleQuality", "TextReadability","TagEntropy", "Sentiment","CodeReadability","ExpQuality","CodeTextRatio","Parsability","Understandability","MetricEntropy","TextLength","QuestionType");
    	int count1 =0, count2=0;		
		for(int i = 1; i<=NO_OF_FILES; i++){
		
			listReader = new CsvListReader(new FileReader("E:/Projects/SOContentQualityResources/FeatureValues/Case-C(Both)/11_"+i+".csv"), CsvPreference.STANDARD_PREFERENCE);
	        listReader.getHeader(true); // skip the header (can't be used with CsvListReader)
	        final CellProcessor[] processors = getProcessors();               
	        List<Object> questionList;
	        
//	        listReaderBaseline = new CsvListReader(new FileReader("E:/Projects/SOContentQualityResources/FeatureValues/Case-C(Both)/Baseline"+i+".csv"), CsvPreference.STANDARD_PREFERENCE);
//	        listReaderBaseline.getHeader(true); // skip the header (can't be used with CsvListReader)
//	        final CellProcessor[] processorsBL = getProcessorsBL();               
//	        List<Object> questionListBL;
	        
//	        while( (questionList = listReader.read(processors)) != null && (questionListBL = listReaderBaseline.read(processorsBL)) != null ) {
	        while( (questionList = listReader.read(processors)) != null) {
	        	 
	    
	        	String id="";                          		    // int
	            String TitleQuality="";                  		    // float
	            String TextReadability="";
	            String TagEntropy="";
	            String Sentiment="";
	            String CodeReadability="";
	            String ExpQuality="";
	            String CodeTextRatio="";
	            String Parsability="";
	            String Understandability="";
	            String MetricEntropy="";
	            String TextLength="";
	        	String f1 ="";
	        	String f2 ="";
	         	String f3 ="";
	         	String f4 ="";
	         	String f5 ="";
	     
	         	String EmailCount="";
	         	String lcasePercent="";
	         	String spaceCount="";
	         	String tagCount="";
	         	String spkCount="";
	         	String titleLen="";
	         	String capTitle="";
	         	String ucasePercent="";
	         	String urlCount="";
	         	
	        	String QuestionType="";
	        	
	        	try {
	        		id=questionList.get(0).toString();
				}catch (Exception e) {
					// TODO: handle exception
				}
	        	try {
	        		TitleQuality=questionList.get(1).toString();
				}catch (Exception e) {
				}
	        	try {
	        		TextReadability=questionList.get(2).toString();
				}catch (Exception e) {
				}
	        	try {
	        		TagEntropy=questionList.get(3).toString();
				}catch (Exception e) {
				}
	        	try {
	        		Sentiment=questionList.get(4).toString();
				}catch (Exception e) {
				}
	        	try {
	        		CodeReadability=questionList.get(5).toString();
				}catch (Exception e) {
				}
	        	try {
	        		ExpQuality=questionList.get(6).toString();
				}catch (Exception e) {
				}
	        	try {
	        		CodeTextRatio=questionList.get(7).toString();
				}catch (Exception e) {
				}
	        	try {
	        		Parsability=questionList.get(8).toString();
				}catch (Exception e) {
				}
	        	try {
	        		Understandability=questionList.get(9).toString();
				}catch (Exception e) {
				}
	        	try {
	        		MetricEntropy=questionList.get(10).toString();
				}catch (Exception e) {
				}
	        	
	        	try {
	        		TextLength=questionList.get(11).toString();
				}catch (Exception e) {
				}
//	        	try {
//	        		EmailCount=questionListBL.get(10).toString();
//				}catch (Exception e) {
//				}
//	        	try {
//	        		lcasePercent=questionListBL.get(11).toString();
//				}catch (Exception e) {
//				}
//	        	try {
//	        		spaceCount=questionListBL.get(12).toString();
//				}catch (Exception e) {
//				}
//	        	try {
//	        		tagCount=questionListBL.get(13).toString();
//				}catch (Exception e) {
//				}
//	        	try {
//	        		spkCount=questionListBL.get(14).toString();
//				}catch (Exception e) {
//				}
//	        	try {
//	        		titleLen=questionListBL.get(15).toString();
//				}catch (Exception e) {
//				}
//	        	try {
//	        		capTitle=questionListBL.get(16).toString();
//				}catch (Exception e) {
//				}
//	        	try {
//	        		ucasePercent=questionListBL.get(17).toString();
//				}catch (Exception e) {
//				}
//	        	try {
//	        		urlCount=questionListBL.get(18).toString();
//				}catch (Exception e) {
//				}
	        	
	        	try {
	        		QuestionType=questionList.get(17).toString();
				}catch (Exception e) {
				}
	        	
	        	if(QuestionType.equals("1") && count1 <30000) {
	        	if(Double.parseDouble(TagEntropy)>=0.05) {
	        	csvWriter.write(id,TitleQuality,TextReadability,TagEntropy, Sentiment,CodeReadability,ExpQuality,CodeTextRatio,Parsability,Understandability,MetricEntropy,TextLength,QuestionType);
	        	count1++;
	        	}
	        	}
	        	if(QuestionType.equals("0") && count2 <15000) {
		        	csvWriter.write(id,TitleQuality,TextReadability,TagEntropy, Sentiment,CodeReadability,ExpQuality,CodeTextRatio,Parsability,Understandability,MetricEntropy,TextLength,QuestionType);
		        	count2++;
	        	}
	        }
		}
                
        csvWriter.close();
        System.out.println("Data Write in File Finished Successfully!!");
       
    }
    finally {
    	
        if( listReader != null ) {
                listReader.close();
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
    		new Optional()   
    };
    
    return processors;
}
private static CellProcessor[] getProcessorsBL() {
    
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
    		new Optional(),
    		new Optional(),
    		new Optional()   
    };
    
    return processors;
}

}