package soreader;

import java.io.FileReader;
import java.io.FileWriter;
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

public class TagWithEntropyReader {
	
	static Map<String,Double> tagEntropyMap = new HashMap<String,Double>();

	public static void main(String[] args) throws Exception {
		
		FindTagEntropy obj = new FindTagEntropy();
		tagEntropyMap = obj.readTagEntropy();

		readWithCsvListReader();
		
		System.out.println("Entropy Calculation done!!");

	}
	
private static void readWithCsvListReader() throws Exception {
        

	ICsvListReader listReader = null;
//	ICsvListWriter csvWriter = null;
	ICsvListWriter csvWriterTextH = null;
	ICsvListWriter csvWriterTextL = null;
	ICsvListWriter csvWriterTextCodeH = null;
	ICsvListWriter csvWriterTextCodeL = null;

    try {

        
    	// For TextOnlyFile
    	
    	String fileLocation = "E:/Projects/SOContentQualityResources/CSVData/Python/QueryData/";
//    	String outFile = "E:/Projects/SOContentQualityResources/CSVData/Java/MetricResults/TagEntropy/TagEntropy.csv";
    	String outFileTextH = "E:/Projects/SOContentQualityResources/FeatureValues/TextOnly/Python/TagEntropy/TagEntropy_Text_GT_0_v6.csv";
    	String outFileTextL = "E:/Projects/SOContentQualityResources/FeatureValues/TextOnly/Python/TagEntropy/TagEntropy_Text_LT_0_v6.csv";
    	String outFileTextCodeH = "E:/Projects/SOContentQualityResources/FeatureValues/TextCode/Python/TagEntropy/TagEntropy_TextCode_GT_0_v6.csv";
    	String outFileTextCodeL = "E:/Projects/SOContentQualityResources/FeatureValues/TextCode/Python/TagEntropy/TagEntropy_TextCode_LT_0_v6.csv";

//    	csvWriter = new CsvListWriter(new FileWriter(outFile),CsvPreference.STANDARD_PREFERENCE);
    	csvWriterTextH = new CsvListWriter(new FileWriter(outFileTextH),CsvPreference.STANDARD_PREFERENCE);
    	csvWriterTextL = new CsvListWriter(new FileWriter(outFileTextL),CsvPreference.STANDARD_PREFERENCE);
    	csvWriterTextCodeH = new CsvListWriter(new FileWriter(outFileTextCodeH),CsvPreference.STANDARD_PREFERENCE);
    	csvWriterTextCodeL = new CsvListWriter(new FileWriter(outFileTextCodeL),CsvPreference.STANDARD_PREFERENCE);
    	
//    	csvWriter.write("Id","Score","TagEntropy","Category");
    	csvWriterTextH.write("Id","Score","TagEntropy","QuestionType");
    	csvWriterTextL.write("Id","Score","TagEntropy","QuestionType");
    	csvWriterTextCodeH.write("Id","Score","TagEntropy","QuestionType");
    	csvWriterTextCodeL.write("Id","Score","TagEntropy","QuestionType");
    	
    	int NO_OF_FILE = 10;
    	int tagCount =0;
    	
    	for (int i = 1; i<= NO_OF_FILE; i++) {
    		System.out.println("File:"+i);
	        listReader = new CsvListReader(new FileReader(fileLocation+"QueryResults_"+i+"_AnsCount1_Score_NE_0_2017.CSV"), CsvPreference.STANDARD_PREFERENCE);          
	        listReader.getHeader(true); // skip the header (can't be used with CsvListReader)
	        final CellProcessor[] processors = getProcessors();               
	        List<Object> questionList;
	        
	        while( (questionList = listReader.read(processors)) != null ) {
	        		        	 
	           	String id="";                          		// int
	        	String score="-9999";                       // int
	        	String tags="";                        		// nvarchar (250)
	        	String bodyText="";                        	// nvarchar (max)
	        	String bodyCode="";                        	// nvarchar (max)
	        	
	        	double totalEntropy = 0.0;
	        	
	        	try {
	        		id=questionList.get(0).toString();
				}catch (Exception e) {
					
				}
//	        	
	        	try {
	        		score=questionList.get(6).toString();
				}catch (Exception e) {
					
					//e.printStackTrace();
				}
	        	
	        	try {
	        		Document doc = Jsoup.parse(questionList.get(8).toString());
//	    			Elements contentText = doc.select("p");
//	    			String finalString = contentText.text();
//	    			finalString = finalString.replaceAll("[\\[,\\]]", "");
//	        		bodyText=finalString;
	        		
	        		Elements contentCode = doc.select("pre");
	        		bodyCode = contentCode.text();
	        		
				}catch (Exception e) {
					// TODO: handle exception
					//e.printStackTrace();
				}
	        	
	        	try {
	        		tagCount =0;
	        		tags=questionList.get(16).toString();
	        		tags = tags.replaceAll("><", " ");
	        		tags = tags.replaceAll("[\\<,\\>]","");
	        		
	        		String[] tagList = tags.split(" ");
	        		
	        		for(int k=0; k<tagList.length; k++) {
	        			totalEntropy = totalEntropy + tagEntropyMap.get(tagList[k]);
	        			tagCount++;
	        		}
	        		
	        		totalEntropy = totalEntropy/tagCount;
//	        		totalEntropy = totalEntropy *1000.0;
	        		
				}catch (Exception e) {

					//e.printStackTrace();
				}
	        	
	        	if(bodyCode != null && !bodyCode.isEmpty()){
		        	if(Integer.parseInt(score)>0) {
		        		csvWriterTextCodeH.write(id,score,(totalEntropy/0.15044),1);
		        		
		        	}
		        	else {
//		        		csvWriter.write(id,score,totalEntropy,"L");
		        		csvWriterTextCodeL.write(id,score,(totalEntropy/0.14595),0);
		        	}
	        	}
	        	else {
	        		if(Integer.parseInt(score)>0) {
	//	        		csvWriter.write(id,score,totalEntropy,"H");
	        			csvWriterTextH.write(id,score,(totalEntropy/0.15044),1);
//	        			csvWriterTextH.write(id,score,totalEntropy,1);
			        		
			        	}
		        	else {
		        		csvWriterTextL.write(id,score,(totalEntropy/0.14595),0);
//		        		csvWriterTextL.write(id,score,totalEntropy,0);
		        	}
	        	}
	        }
    	}
//    	csvWriter.close();
    	csvWriterTextH.close();
    	csvWriterTextL.close();
    	csvWriterTextCodeH.close();
    	csvWriterTextCodeL.close();
        

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
            new Optional()
    };
    
    return processors;
}

}


class FindTagEntropy {
	
	public Map<String,Double> readTagEntropy() throws Exception {
	        
	
		ICsvListReader listReader = null;
		Map<String,Double> tagEntropyMap = new HashMap<String,Double>();
		
	    try {
		    	
	    	String fileLocation = "E:/Projects/SOContentQualityResources/CSVData/Tags/TagsEntropy2.csv";

	        listReader = new CsvListReader(new FileReader(fileLocation), CsvPreference.STANDARD_PREFERENCE);          
	        listReader.getHeader(true); // skip the header (can't be used with CsvListReader)
	        final CellProcessor[] processors = getProcessors();               
	        List<Object> questionList;
	        
	        while( (questionList = listReader.read(processors)) != null ) {
	        	 
	        	String name="";                       // int
	        	String entropy="";                        	// nvarchar (250)

	        	try {
	        		name=questionList.get(1).toString();
				}catch (Exception e) {
					// TODO: handle exception
				}   
	        	try {
	        		entropy=questionList.get(3).toString();
				}catch (Exception e) {
					// TODO: handle exception
				}
	        	
	        	tagEntropyMap.put(name, Double.parseDouble(entropy));
	        		        	
	        }
	        
//	        System.out.println(tagEntropyMap.get("java"));
	        
	    }
	    finally {
	    	
	        if( listReader != null ) {
	                listReader.close();
	        }
	    }
	    
	    return tagEntropyMap;
	}
	
	private static CellProcessor[] getProcessors() {
		         
	    final CellProcessor[] processors = new CellProcessor[] {
	    		new Optional(), // post Id not null
	            new Optional(),
	            new Optional(),
	            new Optional()
	    };
	    
	    return processors;
	}

}
