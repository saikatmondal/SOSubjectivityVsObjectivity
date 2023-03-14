package randomsampler;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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

public class RandomSampler {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		readForRandomSample();

	}
	
private static void readForRandomSample() throws Exception {
	

	ICsvListReader listReader = null;
	ICsvListWriter csvWriter = null;
	
	final int SAMPLE_SIZE = 54541;
	
    Map<String, String> data = new HashMap<String,String>();
    
    try {
    	
    	listReader = new CsvListReader(new FileReader("E:/Projects/SOContentQualityResources/FeatureValues/TextCode/Python/TextReadability/Text_Readability_Score_GT_0.csv"), CsvPreference.STANDARD_PREFERENCE);
        csvWriter = new CsvListWriter(new FileWriter("E:/Projects/SOContentQualityResources/FeatureValues/TextCode/Python/TextReadability/Text_Readability_Score_RS_GT_0.csv"),CsvPreference.STANDARD_PREFERENCE);
        
        String firstColumnTitle = "Id";
		String secondColumnTitle = "Score";
		csvWriter.write(firstColumnTitle, secondColumnTitle);
		
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
        	
        	//System.out.println(id+" "+score);
        	data.put(id, score);
        	       	
        }
        
        Random random = new Random();
        List<String> keys = new ArrayList<String>(data.keySet());
        
        List<String> duplicateCheck = new ArrayList<String>();
        int i =0;
        
        while(i<SAMPLE_SIZE) {
        	String randomKey = keys.get(random.nextInt(keys.size()));
        	
        	if(duplicateCheck.contains(randomKey)) {
        		continue;
        	}
        	else {
        		duplicateCheck.add(randomKey);
        		String value = data.get(randomKey);
                csvWriter.write(randomKey,value);
                i++;
                System.out.println(i+">>"+randomKey+value);
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
            new Optional(), // 
            
    };
    
    return processors;
}

}
