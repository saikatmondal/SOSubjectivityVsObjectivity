package termentropy;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

public class BuildSOCorpuswithWordFrequency {
	public static void main(String[] args) {
		CorpusBuilder obj = new CorpusBuilder();
		obj.start();
	}

}


class CorpusBuilder extends Thread{
	
	ICsvListReader listReader = null;
//	ICsvListWriter csvWriter = null;
	ICsvListWriter csvWriter = null;

	
	public void run() {
	    
		try {
				    	
	    	String fileLocation = "E:/Projects/SOContentQualityResources/CSVData/Corpus/";
	    	String outFile = "E:/Projects/SOContentQualityResources/DataStore/CorpusWithFrequency/CorpusWithFrequency.csv";

	    	csvWriter = new CsvListWriter(new FileWriter(outFile),CsvPreference.STANDARD_PREFERENCE);
	    	csvWriter.write("Word","Frequency");
	    	
	    	Map<String, Integer> uniqueWordsAndCount = new HashMap<>();
    		
	    	
	    	int NO_OF_FILE = 53;
	    	
	    	for (int i = 1; i<= NO_OF_FILE; i++) {
		        listReader = new CsvListReader(new FileReader(fileLocation+i+".CSV"), CsvPreference.STANDARD_PREFERENCE);
		        System.out.println("File:"+i);
		        listReader.getHeader(true); // skip the header (can't be used with CsvListReader)
		        final CellProcessor[] processors = getProcessors();               
		        List<Object> questionList;
		        
		        while( (questionList = listReader.read(processors)) != null ) {
		        		        	 
		        	String bodyText="";                        	// nvarchar (max)
		        			        	
		        	try {
		        		Document doc = Jsoup.parse(questionList.get(8).toString());
		    			Elements contentText = doc.select("p");
		    			String finalString = contentText.text();
		    			finalString = finalString.replaceAll("[\\[,\\]]", " ");
		    			finalString = finalString.replaceAll("[-,.!?)(/'\"]", " ");
		    			finalString = StopWords.removeStemmedStopWords(finalString);
		    			finalString = StopWords.removeStopWords(finalString);
//		    			System.out.println(finalString);
		    			
		        		bodyText=finalString;
		        		
		        		String[] words = bodyText.split("\\s+");
		        		
		        		for (String word : words) {
		        			if(word.matches ("[a-zA-Z_]+")) {
			        		    if (uniqueWordsAndCount.containsKey(word)){ //If word is in our map already, increase count
			        		        uniqueWordsAndCount.put(word, uniqueWordsAndCount.get(word)+1);
			        		    }else{  //If not in our map, add it and set count to 1
			        		        uniqueWordsAndCount.put(word, 1);
			        		    }
		        			}
		        		}
		        			        		
		        			
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	
		        }
		        
		        for (Map.Entry<String, Integer> entry : uniqueWordsAndCount.entrySet()) {
//        			System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
        			String word = entry.getKey();
        			int frequency = entry.getValue();
        			csvWriter.write(word,frequency);
        		}
		        
		        
	    	}
	    	csvWriter.close();
	    	
	    	
	    	
	    } catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    finally {
	    	
	        if( listReader != null ) {
	                try {
						listReader.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
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