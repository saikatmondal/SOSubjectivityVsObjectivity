package customdatareader;


import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvListReader;
import org.supercsv.io.CsvListWriter;
import org.supercsv.io.ICsvListReader;
import org.supercsv.io.ICsvListWriter;
import org.supercsv.prefs.CsvPreference;

public class CustomDataReader {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		readForRandomSample();

	}
	
private static void readForRandomSample() throws Exception {
	
	ICsvListReader idReader = null;
	ICsvListReader listReader = null;
	ICsvListWriter csvWriter = null;
	
    List<String> idList = new ArrayList<String>();
    
    try {
    	
    	idReader = new CsvListReader(new FileReader("E:/Projects/SOContentQualityResources/FeatureValues/Case-A(OnlyText)/CombinedLanguages/TitleQuality/Title_Quality_Score_GT_0.csv"), CsvPreference.STANDARD_PREFERENCE);
    	listReader = new CsvListReader(new FileReader("E:/Projects/SOContentQualityResources/FeatureValues/Case-C(Both)/Sentiment/Combined/Sentiment_Score_GT_0.csv"), CsvPreference.STANDARD_PREFERENCE);
        csvWriter = new CsvListWriter(new FileWriter("E:/Projects/SOContentQualityResources/FeatureValues/Case-A(OnlyText)/CombinedLanguages/Sentiment/Sentiment_Text_GT_0.csv"),CsvPreference.STANDARD_PREFERENCE);
        
        String firstColumnTitle = "Id";
		String secondColumnTitle = "Sentiment";
		csvWriter.write(firstColumnTitle, secondColumnTitle);
		
		idReader.getHeader(true);
		final CellProcessor[] idProcessors = getIdProcessors();               
        List<Object> questionIdList;
		
        while( (questionIdList = idReader.read(idProcessors)) != null ) {
       	 
            
        	String id="";                          		    // int     	
    
        	try {
        		id=questionIdList.get(0).toString();
			}catch (Exception e) {
				// TODO: handle exception
			}
        	
        	idList.add(id.trim());
        	        	       	
        }
		
        
        listReader.getHeader(true); // skip the header (can't be used with CsvListReader)
        final CellProcessor[] processors = getProcessors();               
        List<Object> questionList;
        
        while( (questionList = listReader.read(processors)) != null ) {
        	 
    
        	String id="";                          		    // int
            String score="";                  		    // float
        	
        	
        	try {
        		id=questionList.get(0).toString();
			}catch (Exception e) {
				// TODO: handle exception
			}
        	try {
        		score=questionList.get(1).toString();
			}catch (Exception e) {
				// TODO: handle exception
				//e.printStackTrace();
			}
        	
        	if(idList.contains(id.trim())) {
        		System.out.println(id);
        		csvWriter.write(id,score);
        	}
        	       	
        }
                
        csvWriter.close();
        System.out.println("Data Write in File Finished Successfully!!");
       
    }
    finally {
    	
        if( listReader != null ) {
                listReader.close();
                idReader.close();
        }
    }
}

private static CellProcessor[] getProcessors() {
	         
    final CellProcessor[] processors = new CellProcessor[] {
    		new Optional(), // post Id not null
    		new Optional()
            
    };
    
    return processors;
}

private static CellProcessor[] getIdProcessors() {
    
    final CellProcessor[] processors = new CellProcessor[] {
    		new Optional(), // post Id not null
            new Optional()
            
    };
    
    return processors;
}

}
