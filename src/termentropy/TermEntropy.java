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

public class TermEntropy{
	
	public static void main(String[] args) {
		CSMetricEntropy oCS = new CSMetricEntropy();
		JavaMetricEntropy oJava = new JavaMetricEntropy();
		JSMetricEntropy oJS = new JSMetricEntropy();
		PyMetricEntropy oPy = new PyMetricEntropy();
		oCS.start();
		oJava.start();
		oJS.start();
		oPy.start();
	}

}

class CSMetricEntropy extends Thread{
	
	ICsvListReader listReader = null;
	ICsvListReader corpusReader = null;
//	ICsvListWriter csvWriter = null;
	ICsvListWriter csvWriterTextH = null;
	ICsvListWriter csvWriterTextL = null;
	ICsvListWriter csvWriterTextCodeH = null;
	ICsvListWriter csvWriterTextCodeL = null;
	Map<String, Integer> corpusWords = new HashMap<>();
	
	public void run() {

    try {
    	// Read the corpus and load into a Map
    	String corpusLocation = "E:/Projects/SOContentQualityResources/DataStore/CorpusWithFrequency/CorpusWithFrequencyCS.csv";
    	
    	corpusReader = new CsvListReader(new FileReader(corpusLocation), CsvPreference.STANDARD_PREFERENCE);
    	corpusReader.getHeader(true); // skip the header (can't be used with CsvListReader)
        final CellProcessor[] processorsCorpus = getProcessorsCorpus();               
        List<Object> corpusWordList;
        
        long totalWordFrequesncy = 0;
        
        
        
        while( (corpusWordList = corpusReader.read(processorsCorpus)) != null ) {
       	 
           	String word="";                          		// int
        	String frequency="-9999";
        	int freqCount = 0;

        	
        	try {
        		word = corpusWordList.get(0).toString();
			}catch (Exception e) {
				
			}
//        	
        	try {
        		frequency = corpusWordList.get(1).toString();
        		freqCount = Integer.parseInt(frequency);
        		totalWordFrequesncy = totalWordFrequesncy + freqCount;
			}catch (Exception e) {
				//e.printStackTrace();
			}
        	corpusWords.put(word, freqCount);
        }

    	System.out.println(totalWordFrequesncy);
    	

    	// For TextOnlyFile
    	
    	String fileLocation = "E:/Projects/SOContentQualityResources/CSVData/Java/QueryData/";
//    	String outFile = "E:/Projects/SOContentQualityResources/CSVData/Java/MetricResults/TagEntropy/TagEntropy.csv";
    	String outFileTextH = "E:/Projects/SOContentQualityResources/FeatureValues/TextOnly/C#/TermEntropy/TermEntropy_Text_GT_0.csv";
    	String outFileTextL = "E:/Projects/SOContentQualityResources/FeatureValues/TextOnly/C#/TermEntropy/TermEntropy_Text_LT_0.csv";
    	String outFileTextCodeH = "E:/Projects/SOContentQualityResources/FeatureValues/TextCode/C#/TermEntropy/TermEntropy_TextCode_GT_0.csv";
    	String outFileTextCodeL = "E:/Projects/SOContentQualityResources/FeatureValues/TextCode/C#/TermEntropy/TermEntropy_TextCode_LT_0.csv";

//    	csvWriter = new CsvListWriter(new FileWriter(outFile),CsvPreference.STANDARD_PREFERENCE);
    	csvWriterTextH = new CsvListWriter(new FileWriter(outFileTextH),CsvPreference.STANDARD_PREFERENCE);
    	csvWriterTextL = new CsvListWriter(new FileWriter(outFileTextL),CsvPreference.STANDARD_PREFERENCE);
    	csvWriterTextCodeH = new CsvListWriter(new FileWriter(outFileTextCodeH),CsvPreference.STANDARD_PREFERENCE);
    	csvWriterTextCodeL = new CsvListWriter(new FileWriter(outFileTextCodeL),CsvPreference.STANDARD_PREFERENCE);
    	
//    	csvWriter.write("Id","Score","TagEntropy","Category");
    	csvWriterTextH.write("Id","TermEntropy");
    	csvWriterTextL.write("Id","TermEntropy");
    	csvWriterTextCodeH.write("Id","TermEntropy");
    	csvWriterTextCodeL.write("Id","TermEntropy");
    	
    	int NO_OF_FILE = 13;
    	
    	for (int i = 1; i<= NO_OF_FILE; i++) {
	        listReader = new CsvListReader(new FileReader(fileLocation+"QueryResults_"+i+"_AnsCount1_Score_NE_0_2017.CSV"), CsvPreference.STANDARD_PREFERENCE);
	        System.out.println("C# File:"+i);
	        listReader.getHeader(true); // skip the header (can't be used with CsvListReader)
	        final CellProcessor[] processors = getProcessors();               
	        List<Object> questionList;
	        
	        while( (questionList = listReader.read(processors)) != null ) {
	        		        	 
	           	String id="";                          		// int
	        	String score="-9999";                       // int
	        	String tags="";                        		// nvarchar (250)
	        	String bodyText="";                        	// nvarchar (max)
	        	String bodyCode="";                        	// nvarchar (max)
	        	
	        	double termEntropy = 0.0;
	        	int bodyTextLen = 0 ;
	        	
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
	    			Elements contentText = doc.select("p");
	    			String finalString = contentText.text();
	    			finalString = finalString.replaceAll("[\\[,\\]]", " ");
	    			finalString = finalString.replaceAll("[-,.!?)(/'\"]", " ");
	    			finalString = StopWords.removeStemmedStopWords(finalString);
	    			finalString = StopWords.removeStopWords(finalString);
//	    			System.out.println(finalString);
	    			
	        		bodyText=finalString;
	        		
	        		Elements contentCode = doc.select("pre");
	        		bodyCode = contentCode.text();
	        			        		
	        		String[] words = bodyText.split("\\s+");

	        		Map<String, Integer> uniqueWordsAndCount = new HashMap<>();
	        		for (String word : words) {
	        			if(word.matches ("[a-zA-Z_]+")) {
		        		    if (uniqueWordsAndCount.containsKey(word)){ //If word is in our map already, increase count
		        		        uniqueWordsAndCount.put(word, uniqueWordsAndCount.get(word)+1);
		        		    }else{  //If not in our map, add it and set count to 1
		        		        uniqueWordsAndCount.put(word, 1);
		        		    }
	        			}
	        		}
	        		
	        		for (Map.Entry<String, Integer> entry : uniqueWordsAndCount.entrySet()) {
//	        			System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
	        			String w = entry.getKey();
	        			termEntropy = termEntropy + ((corpusWords.get(w)/(double)totalWordFrequesncy))*Math.log10((corpusWords.get(w)/(double)totalWordFrequesncy));
	        		}
	        		bodyTextLen = bodyText.length();
	        		if(bodyTextLen == 0) bodyTextLen = 1;
	        		termEntropy = -(termEntropy/(float)bodyTextLen);
	        		
	        		//Accessing the count of a word
//	        		System.out.println(uniqueWordsAndCount.get("c")); //This returns the count
	        		
	        		
				}catch (Exception e) {
					// TODO: handle exception
					//e.printStackTrace();
				}
	        	        		
	        	if(bodyCode != null && !bodyCode.isEmpty()){
		        	if(Integer.parseInt(score)>0) {
		        		csvWriterTextCodeH.write(id,termEntropy);
		        		
		        	}
		        	else {
//		        		csvWriter.write(id,score,totalEntropy,"L");
		        		csvWriterTextCodeL.write(id,termEntropy);
		        	}
	        	}
	        	else {
	        		if(Integer.parseInt(score)>0) {
	//	        		csvWriter.write(id,score,totalEntropy,"H");
		        		csvWriterTextH.write(id,termEntropy);
			        		
			        	}
		        	else {
		        		csvWriterTextL.write(id,termEntropy);
		        	}
	        	}
	        }
    	}
//    	csvWriter.close();
    	csvWriterTextH.close();
    	csvWriterTextL.close();
    	csvWriterTextCodeH.close();
    	csvWriterTextCodeL.close();
        

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

private static CellProcessor[] getProcessorsCorpus() {
    
    final CellProcessor[] processors = new CellProcessor[] {
    		new Optional(), // post Id not null
            new Optional()
            
    };
    
    return processors;
}	
	
}

class JavaMetricEntropy extends Thread{
	
	ICsvListReader listReader = null;
	ICsvListReader corpusReader = null;
//	ICsvListWriter csvWriter = null;
	ICsvListWriter csvWriterTextH = null;
	ICsvListWriter csvWriterTextL = null;
	ICsvListWriter csvWriterTextCodeH = null;
	ICsvListWriter csvWriterTextCodeL = null;
	Map<String, Integer> corpusWords = new HashMap<>();
	
	public void run() {

    try {
    	// Read the corpus and load into a Map
    	String corpusLocation = "E:/Projects/SOContentQualityResources/DataStore/CorpusWithFrequency/CorpusWithFrequencyJ.csv";
    	
    	corpusReader = new CsvListReader(new FileReader(corpusLocation), CsvPreference.STANDARD_PREFERENCE);
    	corpusReader.getHeader(true); // skip the header (can't be used with CsvListReader)
        final CellProcessor[] processorsCorpus = getProcessorsCorpus();               
        List<Object> corpusWordList;
        
        long totalWordFrequesncy = 0;
        
        
        
        while( (corpusWordList = corpusReader.read(processorsCorpus)) != null ) {
       	 
           	String word="";                          		// int
        	String frequency="-9999";
        	int freqCount = 0;

        	
        	try {
        		word = corpusWordList.get(0).toString();
			}catch (Exception e) {
				
			}
//        	
        	try {
        		frequency = corpusWordList.get(1).toString();
        		freqCount = Integer.parseInt(frequency);
        		totalWordFrequesncy = totalWordFrequesncy + freqCount;
			}catch (Exception e) {
				//e.printStackTrace();
			}
        	corpusWords.put(word, freqCount);
        }

    	
    	

    	// For TextOnlyFile
    	
    	String fileLocation = "E:/Projects/SOContentQualityResources/CSVData/Java/QueryData/";
//    	String outFile = "E:/Projects/SOContentQualityResources/CSVData/Java/MetricResults/TagEntropy/TagEntropy.csv";
    	String outFileTextH = "E:/Projects/SOContentQualityResources/FeatureValues/TextOnly/Java/TermEntropy/TermEntropy_Text_GT_0.csv";
    	String outFileTextL = "E:/Projects/SOContentQualityResources/FeatureValues/TextOnly/Java/TermEntropy/TermEntropy_Text_LT_0.csv";
    	String outFileTextCodeH = "E:/Projects/SOContentQualityResources/FeatureValues/TextCode/Java/TermEntropy/TermEntropy_TextCode_GT_0.csv";
    	String outFileTextCodeL = "E:/Projects/SOContentQualityResources/FeatureValues/TextCode/Java/TermEntropy/TermEntropy_TextCode_LT_0.csv";

//    	csvWriter = new CsvListWriter(new FileWriter(outFile),CsvPreference.STANDARD_PREFERENCE);
    	csvWriterTextH = new CsvListWriter(new FileWriter(outFileTextH),CsvPreference.STANDARD_PREFERENCE);
    	csvWriterTextL = new CsvListWriter(new FileWriter(outFileTextL),CsvPreference.STANDARD_PREFERENCE);
    	csvWriterTextCodeH = new CsvListWriter(new FileWriter(outFileTextCodeH),CsvPreference.STANDARD_PREFERENCE);
    	csvWriterTextCodeL = new CsvListWriter(new FileWriter(outFileTextCodeL),CsvPreference.STANDARD_PREFERENCE);
    	
//    	csvWriter.write("Id","Score","TagEntropy","Category");
    	csvWriterTextH.write("Id","TermEntropy");
    	csvWriterTextL.write("Id","TermEntropy");
    	csvWriterTextCodeH.write("Id","TermEntropy");
    	csvWriterTextCodeL.write("Id","TermEntropy");
    	
    	int NO_OF_FILE = 15;
    	
    	for (int i = 1; i<= NO_OF_FILE; i++) {
	        listReader = new CsvListReader(new FileReader(fileLocation+"QueryResults_"+i+"_AnsCount1_Score_NE_0_2017.CSV"), CsvPreference.STANDARD_PREFERENCE);
	        System.out.println("Java File:"+i);
	        listReader.getHeader(true); // skip the header (can't be used with CsvListReader)
	        final CellProcessor[] processors = getProcessors();               
	        List<Object> questionList;
	        
	        while( (questionList = listReader.read(processors)) != null ) {
	        		        	 
	           	String id="";                          		// int
	        	String score="-9999";                       // int
	        	String tags="";                        		// nvarchar (250)
	        	String bodyText="";                        	// nvarchar (max)
	        	String bodyCode="";                        	// nvarchar (max)
	        	
	        	double termEntropy = 0.0;
	        	int bodyTextLen = 0 ;
	        	
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
	    			Elements contentText = doc.select("p");
	    			String finalString = contentText.text();
	    			finalString = finalString.replaceAll("[\\[,\\]]", " ");
	    			finalString = finalString.replaceAll("[-,.!?)(/'\"]", " ");
	    			finalString = StopWords.removeStemmedStopWords(finalString);
	    			finalString = StopWords.removeStopWords(finalString);
//	    			System.out.println(finalString);
	    			
	        		bodyText=finalString;
	        		
	        		Elements contentCode = doc.select("pre");
	        		bodyCode = contentCode.text();
	        			        		
	        		String[] words = bodyText.split("\\s+");

	        		Map<String, Integer> uniqueWordsAndCount = new HashMap<>();
	        		for (String word : words) {
	        			if(word.matches ("[a-zA-Z_]+")) {
		        		    if (uniqueWordsAndCount.containsKey(word)){ //If word is in our map already, increase count
		        		        uniqueWordsAndCount.put(word, uniqueWordsAndCount.get(word)+1);
		        		    }else{  //If not in our map, add it and set count to 1
		        		        uniqueWordsAndCount.put(word, 1);
		        		    }
	        			}
	        		}
	        		
	        		for (Map.Entry<String, Integer> entry : uniqueWordsAndCount.entrySet()) {
//	        			System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
	        			String w = entry.getKey();
	        			termEntropy = termEntropy + ((corpusWords.get(w)/(double)totalWordFrequesncy))*Math.log10((corpusWords.get(w)/(double)totalWordFrequesncy));
	        		}
	        		bodyTextLen = bodyText.length();
	        		if(bodyTextLen == 0) bodyTextLen = 1;
	        		termEntropy = -(termEntropy/(float)bodyTextLen);
	        		
	        		//Accessing the count of a word
//	        		System.out.println(uniqueWordsAndCount.get("c")); //This returns the count
	        		
	        		
				}catch (Exception e) {
					// TODO: handle exception
					//e.printStackTrace();
				}
	        	        		
	        	if(bodyCode != null && !bodyCode.isEmpty()){
		        	if(Integer.parseInt(score)>0) {
		        		csvWriterTextCodeH.write(id,termEntropy);
		        		
		        	}
		        	else {
//		        		csvWriter.write(id,score,totalEntropy,"L");
		        		csvWriterTextCodeL.write(id,termEntropy);
		        	}
	        	}
	        	else {
	        		if(Integer.parseInt(score)>0) {
	//	        		csvWriter.write(id,score,totalEntropy,"H");
		        		csvWriterTextH.write(id,termEntropy);
			        		
			        	}
		        	else {
		        		csvWriterTextL.write(id,termEntropy);
		        	}
	        	}
	        }
    	}
//    	csvWriter.close();
    	csvWriterTextH.close();
    	csvWriterTextL.close();
    	csvWriterTextCodeH.close();
    	csvWriterTextCodeL.close();
        

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

private static CellProcessor[] getProcessorsCorpus() {
    
    final CellProcessor[] processors = new CellProcessor[] {
    		new Optional(), // post Id not null
            new Optional()
            
    };
    
    return processors;
}	
	
}

class JSMetricEntropy extends Thread{
	
	ICsvListReader listReader = null;
	ICsvListReader corpusReader = null;
//	ICsvListWriter csvWriter = null;
	ICsvListWriter csvWriterTextH = null;
	ICsvListWriter csvWriterTextL = null;
	ICsvListWriter csvWriterTextCodeH = null;
	ICsvListWriter csvWriterTextCodeL = null;
	Map<String, Integer> corpusWords = new HashMap<>();
	
	public void run() {

    try {
    	// Read the corpus and load into a Map
    	String corpusLocation = "E:/Projects/SOContentQualityResources/DataStore/CorpusWithFrequency/CorpusWithFrequencyJS.csv";
    	
    	corpusReader = new CsvListReader(new FileReader(corpusLocation), CsvPreference.STANDARD_PREFERENCE);
    	corpusReader.getHeader(true); // skip the header (can't be used with CsvListReader)
        final CellProcessor[] processorsCorpus = getProcessorsCorpus();               
        List<Object> corpusWordList;
        
        long totalWordFrequesncy = 0;
        
        
        
        while( (corpusWordList = corpusReader.read(processorsCorpus)) != null ) {
       	 
           	String word="";                          		// int
        	String frequency="-9999";
        	int freqCount = 0;

        	
        	try {
        		word = corpusWordList.get(0).toString();
			}catch (Exception e) {
				
			}
//        	
        	try {
        		frequency = corpusWordList.get(1).toString();
        		freqCount = Integer.parseInt(frequency);
        		totalWordFrequesncy = totalWordFrequesncy + freqCount;
			}catch (Exception e) {
				//e.printStackTrace();
			}
        	corpusWords.put(word, freqCount);
        }

    	
    	

    	// For TextOnlyFile
    	
    	String fileLocation = "E:/Projects/SOContentQualityResources/CSVData/Java/QueryData/";
//    	String outFile = "E:/Projects/SOContentQualityResources/CSVData/Java/MetricResults/TagEntropy/TagEntropy.csv";
    	String outFileTextH = "E:/Projects/SOContentQualityResources/FeatureValues/TextOnly/JavaScript/TermEntropy/TermEntropy_Text_GT_0.csv";
    	String outFileTextL = "E:/Projects/SOContentQualityResources/FeatureValues/TextOnly/JavaScript/TermEntropy/TermEntropy_Text_LT_0.csv";
    	String outFileTextCodeH = "E:/Projects/SOContentQualityResources/FeatureValues/TextCode/JavaScript/TermEntropy/TermEntropy_TextCode_GT_0.csv";
    	String outFileTextCodeL = "E:/Projects/SOContentQualityResources/FeatureValues/TextCode/JavaScript/TermEntropy/TermEntropy_TextCode_LT_0.csv";

//    	csvWriter = new CsvListWriter(new FileWriter(outFile),CsvPreference.STANDARD_PREFERENCE);
    	csvWriterTextH = new CsvListWriter(new FileWriter(outFileTextH),CsvPreference.STANDARD_PREFERENCE);
    	csvWriterTextL = new CsvListWriter(new FileWriter(outFileTextL),CsvPreference.STANDARD_PREFERENCE);
    	csvWriterTextCodeH = new CsvListWriter(new FileWriter(outFileTextCodeH),CsvPreference.STANDARD_PREFERENCE);
    	csvWriterTextCodeL = new CsvListWriter(new FileWriter(outFileTextCodeL),CsvPreference.STANDARD_PREFERENCE);
    	
//    	csvWriter.write("Id","Score","TagEntropy","Category");
    	csvWriterTextH.write("Id","TermEntropy");
    	csvWriterTextL.write("Id","TermEntropy");
    	csvWriterTextCodeH.write("Id","TermEntropy");
    	csvWriterTextCodeL.write("Id","TermEntropy");
    	
    	int NO_OF_FILE = 15;
    	
    	for (int i = 1; i<= NO_OF_FILE; i++) {
	        listReader = new CsvListReader(new FileReader(fileLocation+"QueryResults_"+i+"_AnsCount1_Score_NE_0_2017.CSV"), CsvPreference.STANDARD_PREFERENCE);
	        System.out.println("JavaScript File:"+i);
	        listReader.getHeader(true); // skip the header (can't be used with CsvListReader)
	        final CellProcessor[] processors = getProcessors();               
	        List<Object> questionList;
	        
	        while( (questionList = listReader.read(processors)) != null ) {
	        		        	 
	           	String id="";                          		// int
	        	String score="-9999";                       // int
	        	String tags="";                        		// nvarchar (250)
	        	String bodyText="";                        	// nvarchar (max)
	        	String bodyCode="";                        	// nvarchar (max)
	        	
	        	double termEntropy = 0.0;
	        	int bodyTextLen = 0 ;
	        	
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
	    			Elements contentText = doc.select("p");
	    			String finalString = contentText.text();
	    			finalString = finalString.replaceAll("[\\[,\\]]", " ");
	    			finalString = finalString.replaceAll("[-,.!?)(/'\"]", " ");
	    			finalString = StopWords.removeStemmedStopWords(finalString);
	    			finalString = StopWords.removeStopWords(finalString);
//	    			System.out.println(finalString);
	    			
	        		bodyText=finalString;
	        		
	        		Elements contentCode = doc.select("pre");
	        		bodyCode = contentCode.text();
	        			        		
	        		String[] words = bodyText.split("\\s+");

	        		Map<String, Integer> uniqueWordsAndCount = new HashMap<>();
	        		for (String word : words) {
	        			if(word.matches ("[a-zA-Z_]+")) {
		        		    if (uniqueWordsAndCount.containsKey(word)){ //If word is in our map already, increase count
		        		        uniqueWordsAndCount.put(word, uniqueWordsAndCount.get(word)+1);
		        		    }else{  //If not in our map, add it and set count to 1
		        		        uniqueWordsAndCount.put(word, 1);
		        		    }
	        			}
	        		}
	        		
	        		for (Map.Entry<String, Integer> entry : uniqueWordsAndCount.entrySet()) {
//	        			System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
	        			String w = entry.getKey();
	        			termEntropy = termEntropy + ((corpusWords.get(w)/(double)totalWordFrequesncy))*Math.log10((corpusWords.get(w)/(double)totalWordFrequesncy));
	        		}
	        		bodyTextLen = bodyText.length();
	        		if(bodyTextLen == 0) bodyTextLen = 1;
	        		termEntropy = -(termEntropy/(float)bodyTextLen);
	        		
	        		//Accessing the count of a word
//	        		System.out.println(uniqueWordsAndCount.get("c")); //This returns the count
	        		
	        		
				}catch (Exception e) {
					// TODO: handle exception
					//e.printStackTrace();
				}
	        	        		
	        	if(bodyCode != null && !bodyCode.isEmpty()){
		        	if(Integer.parseInt(score)>0) {
		        		csvWriterTextCodeH.write(id,termEntropy);
		        		
		        	}
		        	else {
//		        		csvWriter.write(id,score,totalEntropy,"L");
		        		csvWriterTextCodeL.write(id,termEntropy);
		        	}
	        	}
	        	else {
	        		if(Integer.parseInt(score)>0) {
	//	        		csvWriter.write(id,score,totalEntropy,"H");
		        		csvWriterTextH.write(id,termEntropy);
			        		
			        	}
		        	else {
		        		csvWriterTextL.write(id,termEntropy);
		        	}
	        	}
	        }
    	}
//    	csvWriter.close();
    	csvWriterTextH.close();
    	csvWriterTextL.close();
    	csvWriterTextCodeH.close();
    	csvWriterTextCodeL.close();
        

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

private static CellProcessor[] getProcessorsCorpus() {
    
    final CellProcessor[] processors = new CellProcessor[] {
    		new Optional(), // post Id not null
            new Optional()
            
    };
    
    return processors;
}	
	
}


class PyMetricEntropy extends Thread{
	
	ICsvListReader listReader = null;
	ICsvListReader corpusReader = null;
//	ICsvListWriter csvWriter = null;
	ICsvListWriter csvWriterTextH = null;
	ICsvListWriter csvWriterTextL = null;
	ICsvListWriter csvWriterTextCodeH = null;
	ICsvListWriter csvWriterTextCodeL = null;
	Map<String, Integer> corpusWords = new HashMap<>();
	
	public void run() {

    try {
    	// Read the corpus and load into a Map
    	String corpusLocation = "E:/Projects/SOContentQualityResources/DataStore/CorpusWithFrequency/CorpusWithFrequencyPy.csv";
    	
    	corpusReader = new CsvListReader(new FileReader(corpusLocation), CsvPreference.STANDARD_PREFERENCE);
    	corpusReader.getHeader(true); // skip the header (can't be used with CsvListReader)
        final CellProcessor[] processorsCorpus = getProcessorsCorpus();               
        List<Object> corpusWordList;
        
        long totalWordFrequesncy = 0;
        
        
        
        while( (corpusWordList = corpusReader.read(processorsCorpus)) != null ) {
       	 
           	String word="";                          		// int
        	String frequency="-9999";
        	int freqCount = 0;

        	
        	try {
        		word = corpusWordList.get(0).toString();
			}catch (Exception e) {
				
			}
//        	
        	try {
        		frequency = corpusWordList.get(1).toString();
        		freqCount = Integer.parseInt(frequency);
        		totalWordFrequesncy = totalWordFrequesncy + freqCount;
			}catch (Exception e) {
				//e.printStackTrace();
			}
        	corpusWords.put(word, freqCount);
        }

    	
    	

    	// For TextOnlyFile
    	
    	String fileLocation = "E:/Projects/SOContentQualityResources/CSVData/Java/QueryData/";
//    	String outFile = "E:/Projects/SOContentQualityResources/CSVData/Java/MetricResults/TagEntropy/TagEntropy.csv";
    	String outFileTextH = "E:/Projects/SOContentQualityResources/FeatureValues/TextOnly/Python/TermEntropy/TermEntropy_Text_GT_0.csv";
    	String outFileTextL = "E:/Projects/SOContentQualityResources/FeatureValues/TextOnly/Python/TermEntropy/TermEntropy_Text_LT_0.csv";
    	String outFileTextCodeH = "E:/Projects/SOContentQualityResources/FeatureValues/TextCode/Python/TermEntropy/TermEntropy_TextCode_GT_0.csv";
    	String outFileTextCodeL = "E:/Projects/SOContentQualityResources/FeatureValues/TextCode/Python/TermEntropy/TermEntropy_TextCode_LT_0.csv";

//    	csvWriter = new CsvListWriter(new FileWriter(outFile),CsvPreference.STANDARD_PREFERENCE);
    	csvWriterTextH = new CsvListWriter(new FileWriter(outFileTextH),CsvPreference.STANDARD_PREFERENCE);
    	csvWriterTextL = new CsvListWriter(new FileWriter(outFileTextL),CsvPreference.STANDARD_PREFERENCE);
    	csvWriterTextCodeH = new CsvListWriter(new FileWriter(outFileTextCodeH),CsvPreference.STANDARD_PREFERENCE);
    	csvWriterTextCodeL = new CsvListWriter(new FileWriter(outFileTextCodeL),CsvPreference.STANDARD_PREFERENCE);
    	
//    	csvWriter.write("Id","Score","TagEntropy","Category");
    	csvWriterTextH.write("Id","TermEntropy");
    	csvWriterTextL.write("Id","TermEntropy");
    	csvWriterTextCodeH.write("Id","TermEntropy");
    	csvWriterTextCodeL.write("Id","TermEntropy");
    	
    	int NO_OF_FILE = 10;
    	
    	for (int i = 1; i<= NO_OF_FILE; i++) {
	        listReader = new CsvListReader(new FileReader(fileLocation+"QueryResults_"+i+"_AnsCount1_Score_NE_0_2017.CSV"), CsvPreference.STANDARD_PREFERENCE);
	        System.out.println("Python File:"+i);
	        listReader.getHeader(true); // skip the header (can't be used with CsvListReader)
	        final CellProcessor[] processors = getProcessors();               
	        List<Object> questionList;
	        
	        while( (questionList = listReader.read(processors)) != null ) {
	        		        	 
	           	String id="";                          		// int
	        	String score="-9999";                       // int
	        	String tags="";                        		// nvarchar (250)
	        	String bodyText="";                        	// nvarchar (max)
	        	String bodyCode="";                        	// nvarchar (max)
	        	
	        	double termEntropy = 0.0;
	        	int bodyTextLen = 0 ;
	        	
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
	    			Elements contentText = doc.select("p");
	    			String finalString = contentText.text();
	    			finalString = finalString.replaceAll("[\\[,\\]]", " ");
	    			finalString = finalString.replaceAll("[-,.!?)(/'\"]", " ");
	    			finalString = StopWords.removeStemmedStopWords(finalString);
	    			finalString = StopWords.removeStopWords(finalString);
//	    			System.out.println(finalString);
	    			
	        		bodyText=finalString;
	        		
	        		Elements contentCode = doc.select("pre");
	        		bodyCode = contentCode.text();
	        			        		
	        		String[] words = bodyText.split("\\s+");

	        		Map<String, Integer> uniqueWordsAndCount = new HashMap<>();
	        		for (String word : words) {
	        			if(word.matches ("[a-zA-Z_]+")) {
		        		    if (uniqueWordsAndCount.containsKey(word)){ //If word is in our map already, increase count
		        		        uniqueWordsAndCount.put(word, uniqueWordsAndCount.get(word)+1);
		        		    }else{  //If not in our map, add it and set count to 1
		        		        uniqueWordsAndCount.put(word, 1);
		        		    }
	        			}
	        		}
	        		
	        		for (Map.Entry<String, Integer> entry : uniqueWordsAndCount.entrySet()) {
//	        			System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
	        			String w = entry.getKey();
	        			termEntropy = termEntropy + ((corpusWords.get(w)/(double)totalWordFrequesncy))*Math.log10((corpusWords.get(w)/(double)totalWordFrequesncy));
	        		}
	        		bodyTextLen = bodyText.length();
	        		if(bodyTextLen == 0) bodyTextLen = 1;
	        		termEntropy = -(termEntropy/(float)bodyTextLen);
	        		
	        		//Accessing the count of a word
//	        		System.out.println(uniqueWordsAndCount.get("c")); //This returns the count
	        		
	        		
				}catch (Exception e) {
					// TODO: handle exception
					//e.printStackTrace();
				}
	        	        		
	        	if(bodyCode != null && !bodyCode.isEmpty()){
		        	if(Integer.parseInt(score)>0) {
		        		csvWriterTextCodeH.write(id,termEntropy);
		        		
		        	}
		        	else {
//		        		csvWriter.write(id,score,totalEntropy,"L");
		        		csvWriterTextCodeL.write(id,termEntropy);
		        	}
	        	}
	        	else {
	        		if(Integer.parseInt(score)>0) {
	//	        		csvWriter.write(id,score,totalEntropy,"H");
		        		csvWriterTextH.write(id,termEntropy);
			        		
			        	}
		        	else {
		        		csvWriterTextL.write(id,termEntropy);
		        	}
	        	}
	        }
    	}
//    	csvWriter.close();
    	csvWriterTextH.close();
    	csvWriterTextL.close();
    	csvWriterTextCodeH.close();
    	csvWriterTextCodeL.close();
        

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

private static CellProcessor[] getProcessorsCorpus() {
    
    final CellProcessor[] processors = new CellProcessor[] {
    		new Optional(), // post Id not null
            new Optional()
            
    };
    
    return processors;
}	
	
}