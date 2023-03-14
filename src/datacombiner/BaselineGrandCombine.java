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

public class BaselineGrandCombine {
	public static void main(String[] args) throws Exception {
		BaselineBothCombiner obj = new BaselineBothCombiner();
		obj.combineResults();
	}

}

class BaselineBothCombiner{
	
public void combineResults() throws Exception {
	

	ICsvListReader listReader = null;
	ICsvListWriter csvWriter = null;
	
	final int NO_OF_FILES = 2;
    
    try {
    	
    	
        csvWriter = new CsvListWriter(new FileWriter("E:/Projects/SOContentQualityResources/FeatureValues/Case-C(Both)/Baseline_Reduced_v6.csv"),CsvPreference.STANDARD_PREFERENCE);

        csvWriter.write("Id","TitleQuality","GF","FK","ARI","SMOG","FR","CL","TermEntropy","bodyLen","emailCount","lcasePercent","spaceCount","tagCount","txtSpeakCount","titleLength","isCapTitle","ucasePercent","urlCount","locPercent","senteCount","wCount","MetricEntropy","QuestionType");
    	int count1 =0, count2=0;
		for(int i = 1; i<=NO_OF_FILES; i++){
		
			listReader = new CsvListReader(new FileReader("E:/Projects/SOContentQualityResources/FeatureValues/Case-C(Both)/Baseline"+i+".csv"), CsvPreference.STANDARD_PREFERENCE);
	        listReader.getHeader(true); // skip the header (can't be used with CsvListReader)
	        final CellProcessor[] processors = getProcessors();               
	        List<Object> questionList;
	        
	        while( (questionList = listReader.read(processors)) != null ) {
	        	 
	    
	        	String id="";                          		    // int
	            String TitleQuality="";                  		    // float
	            String GF="";                          		    // int
	            String FK="";                  		    // float
	            String ARI="";
	            String SMOG="";
	            String FR="";
	            String CL="";
	            String TermEntropy="";
	            String MetricEntropy="";
	            String bodyLen="";
	            String emailCount="";
	            String lcasePercent="";
	            String spaceCount="";
	            String tagCount="";
	            String txtSpeakCount="";
	            String titleLength="";
	            String isCapTitle="";
	            String ucasePercent="";
	            String urlCount="";
	            String locPercent="";
	            String senteCount="";
	            String wCount="";
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
	        		GF=questionList.get(2).toString();
				}catch (Exception e) {
					// TODO: handle exception
				}
	        	try {
	        		FK=questionList.get(3).toString();
				}catch (Exception e) {

				}
	        	try {
	        		ARI=questionList.get(4).toString();
				}catch (Exception e) {

				}
	        	try {
	        		SMOG=questionList.get(5).toString();
				}catch (Exception e) {

				}
	        	try {
	        		FR=questionList.get(6).toString();
				}catch (Exception e) {

				}
	        	try {
	        		CL=questionList.get(7).toString();
				}catch (Exception e) {

				}

	        	try {
	        		TermEntropy=questionList.get(8).toString();
				}catch (Exception e) {
				}
	        	try {
	        		MetricEntropy=questionList.get(9).toString();
				}catch (Exception e) {
				}
	        	try {
	        		bodyLen=questionList.get(10).toString();
				}catch (Exception e) {
				}
	        	try {
	        		emailCount=questionList.get(11).toString();
				}catch (Exception e) {
				}
	        	try {
	        		lcasePercent=questionList.get(12).toString();
				}catch (Exception e) {
				}
	        	try {
	        		spaceCount=questionList.get(13).toString();
				}catch (Exception e) {
				}
	        	try {
	        		tagCount=questionList.get(14).toString();
				}catch (Exception e) {
				}
	        	try {
	        		txtSpeakCount=questionList.get(15).toString();
				}catch (Exception e) {
				}
	        		        	
	        	try {
	        		titleLength=questionList.get(16).toString();
				}catch (Exception e) {
				}
	        	try {
	        		isCapTitle=questionList.get(17).toString();
				}catch (Exception e) {
				}
	        	try {
	        		ucasePercent=questionList.get(18).toString();
				}catch (Exception e) {
				}
	        	try {
	        		urlCount=questionList.get(19).toString();
				}catch (Exception e) {
				}
	        	
	        	try {
	        		locPercent=questionList.get(20).toString();
				}catch (Exception e) {
				}
	        	try {
	        		senteCount=questionList.get(21).toString();
				}catch (Exception e) {
				}
	        	try {
	        		wCount=questionList.get(22).toString();
				}catch (Exception e) {
				}
	        	
	        	try {
	        		QuestionType=questionList.get(23).toString();
				}catch (Exception e) {
				}

	        	if(QuestionType.equals("1") && count1 <30000) {
	        		csvWriter.write(id,TitleQuality,GF,FK,ARI,SMOG,FR,CL,TermEntropy,MetricEntropy,bodyLen,emailCount,lcasePercent,spaceCount,tagCount,txtSpeakCount,titleLength,isCapTitle,ucasePercent,urlCount,locPercent,senteCount,wCount,QuestionType);
	        		count1++;
	        	}
	        	if(QuestionType.equals("0") && count2 <15000) {
		        	csvWriter.write(id,TitleQuality,GF,FK,ARI,SMOG,FR,CL,TermEntropy,MetricEntropy,bodyLen,emailCount,lcasePercent,spaceCount,tagCount,txtSpeakCount,titleLength,isCapTitle,ucasePercent,urlCount,locPercent,senteCount,wCount,QuestionType);
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