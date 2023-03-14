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

public class MetricEntropy{
	
	public static void main(String[] args) {
		CSTermEntropy oCS = new CSTermEntropy();
		JavaTermEntropy oJava = new JavaTermEntropy();
		JSTermEntropy oJS = new JSTermEntropy();
		PyTermEntropy oPy = new PyTermEntropy();
		oCS.start();
		oJava.start();
		oJS.start();
		oPy.start();
	}

}

class CSTermEntropy extends Thread{
	
	ICsvListReader listReader = null;
//	ICsvListWriter csvWriter = null;
	ICsvListWriter csvWriterTextH = null;
	ICsvListWriter csvWriterTextL = null;
	ICsvListWriter csvWriterTextCodeH = null;
	ICsvListWriter csvWriterTextCodeL = null;
	
	public void run() {

    try {

    	// For TextOnlyFile
    	
    	String fileLocation = "E:/Projects/SOContentQualityResources/CSVData/Java/QueryData/";
//    	String outFile = "E:/Projects/SOContentQualityResources/CSVData/Java/MetricResults/TagEntropy/TagEntropy.csv";
    	String outFileTextH = "E:/Projects/SOContentQualityResources/FeatureValues/TextOnly/C#/MetricEntropy/MetricEntropy_Text_GT_0.csv";
    	String outFileTextL = "E:/Projects/SOContentQualityResources/FeatureValues/TextOnly/C#/MetricEntropy/MetricEntropy_Text_LT_0.csv";
    	String outFileTextCodeH = "E:/Projects/SOContentQualityResources/FeatureValues/TextCode/C#/MetricEntropy/MetricEntropy_TextCode_GT_0.csv";
    	String outFileTextCodeL = "E:/Projects/SOContentQualityResources/FeatureValues/TextCode/C#/MetricEntropy/MetricEntropy_TextCode_LT_0.csv";

//    	csvWriter = new CsvListWriter(new FileWriter(outFile),CsvPreference.STANDARD_PREFERENCE);
    	csvWriterTextH = new CsvListWriter(new FileWriter(outFileTextH),CsvPreference.STANDARD_PREFERENCE);
    	csvWriterTextL = new CsvListWriter(new FileWriter(outFileTextL),CsvPreference.STANDARD_PREFERENCE);
    	csvWriterTextCodeH = new CsvListWriter(new FileWriter(outFileTextCodeH),CsvPreference.STANDARD_PREFERENCE);
    	csvWriterTextCodeL = new CsvListWriter(new FileWriter(outFileTextCodeL),CsvPreference.STANDARD_PREFERENCE);
    	
//    	csvWriter.write("Id","Score","TagEntropy","Category");
    	csvWriterTextH.write("Id","MetricEntropy");
    	csvWriterTextL.write("Id","MetricEntropy");
    	csvWriterTextCodeH.write("Id","MetricEntropy");
    	csvWriterTextCodeL.write("Id","MetricEntropy");
    	
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
	        	
	        	double metricEntropy = 0.0;
	        	int mapLength = 0;
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
	        				mapLength++;
		        		    if (uniqueWordsAndCount.containsKey(word)){ //If word is in our map already, increase count
		        		        uniqueWordsAndCount.put(word, uniqueWordsAndCount.get(word)+1);
		        		    }else{  //If not in our map, add it and set count to 1
		        		        uniqueWordsAndCount.put(word, 1);
		        		    }
	        			}
	        		}
//	        		mapLength = uniqueWordsAndCount.size();
	        		for (Map.Entry<String, Integer> entry : uniqueWordsAndCount.entrySet()) {
//	        			System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
	        			metricEntropy = metricEntropy + ((entry.getValue()/(double)mapLength))*Math.log10((entry.getValue()/(double)mapLength));
	        		}
	        		bodyTextLen = bodyText.length();
	        		if(bodyTextLen == 0) bodyTextLen = 1;
	        		metricEntropy = -(metricEntropy/(float)bodyTextLen);
	        		
	        		//Accessing the count of a word
//	        		System.out.println(uniqueWordsAndCount.get("c")); //This returns the count
	        		
	        		
				}catch (Exception e) {
					// TODO: handle exception
					//e.printStackTrace();
				}
	        	        		
	        	if(bodyCode != null && !bodyCode.isEmpty()){
		        	if(Integer.parseInt(score)>0) {
		        		csvWriterTextCodeH.write(id,metricEntropy);
		        		
		        	}
		        	else {
//		        		csvWriter.write(id,score,totalEntropy,"L");
		        		csvWriterTextCodeL.write(id,metricEntropy);
		        	}
	        	}
	        	else {
	        		if(Integer.parseInt(score)>0) {
	//	        		csvWriter.write(id,score,totalEntropy,"H");
		        		csvWriterTextH.write(id,metricEntropy);
			        		
			        	}
		        	else {
		        		csvWriterTextL.write(id,metricEntropy);
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
	
}



class JavaTermEntropy extends Thread{
	
	ICsvListReader listReader = null;
//	ICsvListWriter csvWriter = null;
	ICsvListWriter csvWriterTextH = null;
	ICsvListWriter csvWriterTextL = null;
	ICsvListWriter csvWriterTextCodeH = null;
	ICsvListWriter csvWriterTextCodeL = null;
	
	public void run() {

    try {

    	// For TextOnlyFile
    	
    	String fileLocation = "E:/Projects/SOContentQualityResources/CSVData/Java/QueryData/";
//    	String outFile = "E:/Projects/SOContentQualityResources/CSVData/Java/MetricResults/TagEntropy/TagEntropy.csv";
    	String outFileTextH = "E:/Projects/SOContentQualityResources/FeatureValues/TextOnly/Java/MetricEntropy/MetricEntropy_Text_GT_0.csv";
    	String outFileTextL = "E:/Projects/SOContentQualityResources/FeatureValues/TextOnly/Java/MetricEntropy/MetricEntropy_Text_LT_0.csv";
    	String outFileTextCodeH = "E:/Projects/SOContentQualityResources/FeatureValues/TextCode/Java/MetricEntropy/MetricEntropy_TextCode_GT_0.csv";
    	String outFileTextCodeL = "E:/Projects/SOContentQualityResources/FeatureValues/TextCode/Java/MetricEntropy/MetricEntropy_TextCode_LT_0.csv";

//    	csvWriter = new CsvListWriter(new FileWriter(outFile),CsvPreference.STANDARD_PREFERENCE);
    	csvWriterTextH = new CsvListWriter(new FileWriter(outFileTextH),CsvPreference.STANDARD_PREFERENCE);
    	csvWriterTextL = new CsvListWriter(new FileWriter(outFileTextL),CsvPreference.STANDARD_PREFERENCE);
    	csvWriterTextCodeH = new CsvListWriter(new FileWriter(outFileTextCodeH),CsvPreference.STANDARD_PREFERENCE);
    	csvWriterTextCodeL = new CsvListWriter(new FileWriter(outFileTextCodeL),CsvPreference.STANDARD_PREFERENCE);
    	
//    	csvWriter.write("Id","Score","TagEntropy","Category");
    	csvWriterTextH.write("Id","MetricEntropy");
    	csvWriterTextL.write("Id","MetricEntropy");
    	csvWriterTextCodeH.write("Id","MetricEntropy");
    	csvWriterTextCodeL.write("Id","MetricEntropy");
    	
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
	        	
	        	double metricEntropy = 0.0;
	        	int mapLength = 0;
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
	        				mapLength++;
		        		    if (uniqueWordsAndCount.containsKey(word)){ //If word is in our map already, increase count
		        		        uniqueWordsAndCount.put(word, uniqueWordsAndCount.get(word)+1);
		        		    }else{  //If not in our map, add it and set count to 1
		        		        uniqueWordsAndCount.put(word, 1);
		        		    }
	        			}
	        		}
//	        		mapLength = uniqueWordsAndCount.size();
	        		for (Map.Entry<String, Integer> entry : uniqueWordsAndCount.entrySet()) {
//	        			System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
	        			metricEntropy = metricEntropy + ((entry.getValue()/(double)mapLength))*Math.log10((entry.getValue()/(double)mapLength));
	        		}
	        		bodyTextLen = bodyText.length();
	        		if(bodyTextLen == 0) bodyTextLen = 1;
	        		metricEntropy = -(metricEntropy/(float)bodyTextLen);
	        		
	        		//Accessing the count of a word
//	        		System.out.println(uniqueWordsAndCount.get("c")); //This returns the count
	        		
	        		
				}catch (Exception e) {
					// TODO: handle exception
					//e.printStackTrace();
				}
	        	        		
	        	if(bodyCode != null && !bodyCode.isEmpty()){
		        	if(Integer.parseInt(score)>0) {
		        		csvWriterTextCodeH.write(id,metricEntropy);
		        		
		        	}
		        	else {
//		        		csvWriter.write(id,score,totalEntropy,"L");
		        		csvWriterTextCodeL.write(id,metricEntropy);
		        	}
	        	}
	        	else {
	        		if(Integer.parseInt(score)>0) {
	//	        		csvWriter.write(id,score,totalEntropy,"H");
		        		csvWriterTextH.write(id,metricEntropy);
			        		
			        	}
		        	else {
		        		csvWriterTextL.write(id,metricEntropy);
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
	
}

class JSTermEntropy extends Thread{
	
	ICsvListReader listReader = null;
//	ICsvListWriter csvWriter = null;
	ICsvListWriter csvWriterTextH = null;
	ICsvListWriter csvWriterTextL = null;
	ICsvListWriter csvWriterTextCodeH = null;
	ICsvListWriter csvWriterTextCodeL = null;
	
	public void run() {

    try {

    	// For TextOnlyFile
    	
    	String fileLocation = "E:/Projects/SOContentQualityResources/CSVData/Java/QueryData/";
//    	String outFile = "E:/Projects/SOContentQualityResources/CSVData/Java/MetricResults/TagEntropy/TagEntropy.csv";
    	String outFileTextH = "E:/Projects/SOContentQualityResources/FeatureValues/TextOnly/JavaScript/MetricEntropy/MetricEntropy_Text_GT_0.csv";
    	String outFileTextL = "E:/Projects/SOContentQualityResources/FeatureValues/TextOnly/JavaScript/MetricEntropy/MetricEntropy_Text_LT_0.csv";
    	String outFileTextCodeH = "E:/Projects/SOContentQualityResources/FeatureValues/TextCode/JavaScript/MetricEntropy/MetricEntropy_TextCode_GT_0.csv";
    	String outFileTextCodeL = "E:/Projects/SOContentQualityResources/FeatureValues/TextCode/JavaScript/MetricEntropy/MetricEntropy_TextCode_LT_0.csv";

//    	csvWriter = new CsvListWriter(new FileWriter(outFile),CsvPreference.STANDARD_PREFERENCE);
    	csvWriterTextH = new CsvListWriter(new FileWriter(outFileTextH),CsvPreference.STANDARD_PREFERENCE);
    	csvWriterTextL = new CsvListWriter(new FileWriter(outFileTextL),CsvPreference.STANDARD_PREFERENCE);
    	csvWriterTextCodeH = new CsvListWriter(new FileWriter(outFileTextCodeH),CsvPreference.STANDARD_PREFERENCE);
    	csvWriterTextCodeL = new CsvListWriter(new FileWriter(outFileTextCodeL),CsvPreference.STANDARD_PREFERENCE);
    	
//    	csvWriter.write("Id","Score","TagEntropy","Category");
    	csvWriterTextH.write("Id","MetricEntropy");
    	csvWriterTextL.write("Id","MetricEntropy");
    	csvWriterTextCodeH.write("Id","MetricEntropy");
    	csvWriterTextCodeL.write("Id","MetricEntropy");
    	
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
	        	
	        	double metricEntropy = 0.0;
	        	int mapLength = 0;
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
	        				mapLength++;
		        		    if (uniqueWordsAndCount.containsKey(word)){ //If word is in our map already, increase count
		        		        uniqueWordsAndCount.put(word, uniqueWordsAndCount.get(word)+1);
		        		    }else{  //If not in our map, add it and set count to 1
		        		        uniqueWordsAndCount.put(word, 1);
		        		    }
	        			}
	        		}
//	        		mapLength = uniqueWordsAndCount.size();
	        		for (Map.Entry<String, Integer> entry : uniqueWordsAndCount.entrySet()) {
//	        			System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
	        			metricEntropy = metricEntropy + ((entry.getValue()/(double)mapLength))*Math.log10((entry.getValue()/(double)mapLength));
	        		}
	        		bodyTextLen = bodyText.length();
	        		if(bodyTextLen == 0) bodyTextLen = 1;
	        		metricEntropy = -(metricEntropy/(float)bodyTextLen);
	        		
	        		//Accessing the count of a word
//	        		System.out.println(uniqueWordsAndCount.get("c")); //This returns the count
	        		
	        		
				}catch (Exception e) {
					// TODO: handle exception
					//e.printStackTrace();
				}
	        	        		
	        	if(bodyCode != null && !bodyCode.isEmpty()){
		        	if(Integer.parseInt(score)>0) {
		        		csvWriterTextCodeH.write(id,metricEntropy);
		        		
		        	}
		        	else {
//		        		csvWriter.write(id,score,totalEntropy,"L");
		        		csvWriterTextCodeL.write(id,metricEntropy);
		        	}
	        	}
	        	else {
	        		if(Integer.parseInt(score)>0) {
	//	        		csvWriter.write(id,score,totalEntropy,"H");
		        		csvWriterTextH.write(id,metricEntropy);
			        		
			        	}
		        	else {
		        		csvWriterTextL.write(id,metricEntropy);
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
	
}


class PyTermEntropy extends Thread{
	
	ICsvListReader listReader = null;
//	ICsvListWriter csvWriter = null;
	ICsvListWriter csvWriterTextH = null;
	ICsvListWriter csvWriterTextL = null;
	ICsvListWriter csvWriterTextCodeH = null;
	ICsvListWriter csvWriterTextCodeL = null;
	
	public void run() {

    try {

    	// For TextOnlyFile
    	
    	String fileLocation = "E:/Projects/SOContentQualityResources/CSVData/Java/QueryData/";
//    	String outFile = "E:/Projects/SOContentQualityResources/CSVData/Java/MetricResults/TagEntropy/TagEntropy.csv";
    	String outFileTextH = "E:/Projects/SOContentQualityResources/FeatureValues/TextOnly/Python/MetricEntropy/MetricEntropy_Text_GT_0.csv";
    	String outFileTextL = "E:/Projects/SOContentQualityResources/FeatureValues/TextOnly/Python/MetricEntropy/MetricEntropy_Text_LT_0.csv";
    	String outFileTextCodeH = "E:/Projects/SOContentQualityResources/FeatureValues/TextCode/Python/MetricEntropy/MetricEntropy_TextCode_GT_0.csv";
    	String outFileTextCodeL = "E:/Projects/SOContentQualityResources/FeatureValues/TextCode/Python/MetricEntropy/MetricEntropy_TextCode_LT_0.csv";

//    	csvWriter = new CsvListWriter(new FileWriter(outFile),CsvPreference.STANDARD_PREFERENCE);
    	csvWriterTextH = new CsvListWriter(new FileWriter(outFileTextH),CsvPreference.STANDARD_PREFERENCE);
    	csvWriterTextL = new CsvListWriter(new FileWriter(outFileTextL),CsvPreference.STANDARD_PREFERENCE);
    	csvWriterTextCodeH = new CsvListWriter(new FileWriter(outFileTextCodeH),CsvPreference.STANDARD_PREFERENCE);
    	csvWriterTextCodeL = new CsvListWriter(new FileWriter(outFileTextCodeL),CsvPreference.STANDARD_PREFERENCE);
    	
//    	csvWriter.write("Id","Score","TagEntropy","Category");
    	csvWriterTextH.write("Id","MetricEntropy");
    	csvWriterTextL.write("Id","MetricEntropy");
    	csvWriterTextCodeH.write("Id","MetricEntropy");
    	csvWriterTextCodeL.write("Id","MetricEntropy");
    	
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
	        	
	        	double metricEntropy = 0.0;
	        	int mapLength = 0;
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
	        				mapLength++;
		        		    if (uniqueWordsAndCount.containsKey(word)){ //If word is in our map already, increase count
		        		        uniqueWordsAndCount.put(word, uniqueWordsAndCount.get(word)+1);
		        		    }else{  //If not in our map, add it and set count to 1
		        		        uniqueWordsAndCount.put(word, 1);
		        		    }
	        			}
	        		}
//	        		mapLength = uniqueWordsAndCount.size();
	        		for (Map.Entry<String, Integer> entry : uniqueWordsAndCount.entrySet()) {
//	        			System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
	        			metricEntropy = metricEntropy + ((entry.getValue()/(double)mapLength))*Math.log10((entry.getValue()/(double)mapLength));
	        		}
	        		bodyTextLen = bodyText.length();
	        		if(bodyTextLen == 0) bodyTextLen = 1;
	        		metricEntropy = -(metricEntropy/(float)bodyTextLen);
	        		
	        		//Accessing the count of a word
//	        		System.out.println(uniqueWordsAndCount.get("c")); //This returns the count
	        		
	        		
				}catch (Exception e) {
					// TODO: handle exception
					//e.printStackTrace();
				}
	        	        		
	        	if(bodyCode != null && !bodyCode.isEmpty()){
		        	if(Integer.parseInt(score)>0) {
		        		csvWriterTextCodeH.write(id,metricEntropy);
		        		
		        	}
		        	else {
//		        		csvWriter.write(id,score,totalEntropy,"L");
		        		csvWriterTextCodeL.write(id,metricEntropy);
		        	}
	        	}
	        	else {
	        		if(Integer.parseInt(score)>0) {
	//	        		csvWriter.write(id,score,totalEntropy,"H");
		        		csvWriterTextH.write(id,metricEntropy);
			        		
			        	}
		        	else {
		        		csvWriterTextL.write(id,metricEntropy);
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
	
}