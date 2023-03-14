
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

public class AddFeatureFromBaseLine {
	public static void main(String[] args) throws Exception {
		CombinerPlusPlus obj = new CombinerPlusPlus();
		obj.combineResults();
	}

}

class CombinerPlusPlus {
	
public void combineResults() throws Exception {
	

	ICsvListReader listReader = null;
	ICsvListWriter csvWriter = null;
	
	final int NO_OF_FILES = 2;
    
    try {
    	
    	
        csvWriter = new CsvListWriter(new FileWriter("E:/Projects/SOContentQualityResources/FeatureValues/Case-C(Both)/Both_Combined_V1.csv"),CsvPreference.STANDARD_PREFERENCE);

        csvWriter.write("Id","TitleQuality", "TextReadability","TagEntropy","WordCount", "Sentiment","CodeReadability","ExpQuality","CodeTextRatio","Parsability","Understandability","QuestionType");
    			
		
		listReader = new CsvListReader(new FileReader("E:/Projects/SOContentQualityResources/FeatureValues/Case-C(Both)/"+".csv"), CsvPreference.STANDARD_PREFERENCE);
		
        listReader.getHeader(true); // skip the header (can't be used with CsvListReader)
        final CellProcessor[] processors = getProcessors();               
        List<Object> questionList;
        
        while( (questionList = listReader.read(processors)) != null ) {
        	 
    
        	String id="";                          		    // int
            String TitleQuality="";                  		    // float
            String TextReadability="";
            String TagEntropy="";
            String WordCount="";
            String Sentiment="";
            String CodeReadability="";
            String ExpQuality="";
            String CodeTextRatio="";
            String Parsability="";
            String Understandability="";
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
        		WordCount=questionList.get(4).toString();
			}catch (Exception e) {
			}
        	try {
        		Sentiment=questionList.get(5).toString();
			}catch (Exception e) {
			}
        	try {
        		CodeReadability=questionList.get(6).toString();
			}catch (Exception e) {
			}
        	try {
        		ExpQuality=questionList.get(7).toString();
			}catch (Exception e) {
			}
        	try {
        		CodeTextRatio=questionList.get(7).toString();
			}catch (Exception e) {
			}
        	try {
        		Parsability=questionList.get(7).toString();
			}catch (Exception e) {
			}
        	try {
        		Understandability=questionList.get(10).toString();
			}catch (Exception e) {
			}
        	try {
        		QuestionType=questionList.get(11).toString();
			}catch (Exception e) {
			}
        	
        	csvWriter.write(id,TitleQuality,TextReadability,TagEntropy,WordCount, Sentiment,CodeReadability,ExpQuality,CodeTextRatio,Parsability,Understandability,QuestionType);
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
    		new Optional()
            
    };
    
    return processors;
}

}