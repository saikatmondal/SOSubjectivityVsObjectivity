package sometric;

import java.awt.image.TileObserver;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

public class SOMetric{
	
	public static void main(String[] args) {
		CSSOMetric oCS = new CSSOMetric();
		JavaSOMetric oJava = new JavaSOMetric();
		JSSOMetric oJS = new JSSOMetric();
		PySOMetric oPy = new PySOMetric();
		oCS.start();
		oJava.start();
		oJS.start();
		oPy.start();
	}

}

class CSSOMetric extends Thread{
	
	ICsvListReader listReader = null;
//	ICsvListWriter csvWriter = null;
	ICsvListWriter csvWriterTextH = null;
	ICsvListWriter csvWriterTextL = null;
	ICsvListWriter csvWriterTextCodeH = null;
	ICsvListWriter csvWriterTextCodeL = null;
	
	@SuppressWarnings("resource")
	public void run() {

    try {

    	// For TextOnlyFile
    	
    	String fileLocation = "E:/Projects/SOContentQualityResources/CSVData/C#/QueryData/";
    	
    	String filePathTS = "E:/Projects/SOContentQualityResources/DataStore/Presentation/TextSpeakStore.txt";
    	
//    	String outFile = "E:/Projects/SOContentQualityResources/CSVData/Java/MetricResults/TagEntropy/TagEntropy.csv";
    	String outFileTextH = "E:/Projects/SOContentQualityResources/FeatureValues/TextOnly/C#/SOMetric/SOMetric_Text_GT_0.csv";
    	String outFileTextL = "E:/Projects/SOContentQualityResources/FeatureValues/TextOnly/C#/SOMetric/SOMetric_Text_LT_0.csv";
    	String outFileTextCodeH = "E:/Projects/SOContentQualityResources/FeatureValues/TextCode/C#/SOMetric/SOMetric_TextCode_GT_0.csv";
    	String outFileTextCodeL = "E:/Projects/SOContentQualityResources/FeatureValues/TextCode/C#/SOMetric/SOMetric_TextCode_LT_0.csv";

//    	csvWriter = new CsvListWriter(new FileWriter(outFile),CsvPreference.STANDARD_PREFERENCE);
    	csvWriterTextH = new CsvListWriter(new FileWriter(outFileTextH),CsvPreference.STANDARD_PREFERENCE);
    	csvWriterTextL = new CsvListWriter(new FileWriter(outFileTextL),CsvPreference.STANDARD_PREFERENCE);
    	csvWriterTextCodeH = new CsvListWriter(new FileWriter(outFileTextCodeH),CsvPreference.STANDARD_PREFERENCE);
    	csvWriterTextCodeL = new CsvListWriter(new FileWriter(outFileTextCodeL),CsvPreference.STANDARD_PREFERENCE);
    	
//    	csvWriter.write("Id","Score","TagEntropy","Category");
    	csvWriterTextH.write("Id","bodyLength","emailCount","lowercasePercentage", "spaceCount", "tagCount", "txtSpeakCount","titleLength","isCapitalTitle","uppercasePercentage","urlCount","locPercentage","sentenceCount","wordCount");
    	csvWriterTextL.write("Id","bodyLength","emailCount","lowercasePercentage", "spaceCount", "tagCount", "txtSpeakCount","titleLength","isCapitalTitle","uppercasePercentage","urlCount","locPercentage","sentenceCount","wordCount");
    	csvWriterTextCodeH.write("Id","bodyLength","emailCount","lowercasePercentage", "spaceCount", "tagCount", "txtSpeakCount","titleLength","isCapitalTitle","uppercasePercentage","urlCount","locPercentage","sentenceCount","wordCount");
    	csvWriterTextCodeL.write("Id","bodyLength","emailCount","lowercasePercentage", "spaceCount", "tagCount", "txtSpeakCount","titleLength","isCapitalTitle","uppercasePercentage","urlCount","locPercentage","sentenceCount","wordCount");
    	
    	int NO_OF_FILE = 13;
    	
    	for (int i = 1; i<= NO_OF_FILE; i++) {
	        listReader = new CsvListReader(new FileReader(fileLocation+"QueryResults_"+i+"_AnsCount1_Score_NE_0_2017.CSV"), CsvPreference.STANDARD_PREFERENCE);
	        System.out.println("C# File:"+i);
	        listReader.getHeader(true); // skip the header (can't be used with CsvListReader)
	        final CellProcessor[] processors = getProcessors();               
	        List<Object> questionList;
	        
	        String textSpeakContent = "";
	        File txtSpeakFile = new File(filePathTS);
	        textSpeakContent = new Scanner(txtSpeakFile).useDelimiter("\\Z").next();	        
	        
	        while( (questionList = listReader.read(processors)) != null ) {
	        		        	 
	           	String id="";                          		// int
	        	String score="-9999";                       // int
	        	String tags="";                        		// nvarchar (250)
	        	String title = "";
	        	String bodyContent="";
	        	String bodyText="";                        	// nvarchar (max)
	        	String bodyCode="";                        	// nvarchar (max)
	        	
	        	
	       
	        	String titleNBody = "";
	        	int locCount = 0;
	        	
	        	int bodyLength = 0;
	        	int emailCount = 0;
	        	double lowercasePercentage = 0.0;
	        	int spaceCount = 0;
	        	int tagCount =0;
	        	int txtSpeakCount =0;
	        	int titleLength =0;
	        	int isCapitalTitle = 0;
	        	double uppercasePercentage =0.0;
	        	int urlCount =0;
	        	double locPercentage =0.0;
	        	int sentenceCount = 0;
	        	int wordCount = 0;

	        	
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
	        		bodyContent = doc.text().toString();
	        		bodyLength = bodyContent.length(); // Calculate body length
	        			        		
	    			Elements contentText = doc.select("p");
	    			String finalString = contentText.text();
	    			finalString = finalString.replaceAll("[\\[,\\]]", " ");
//	    			finalString = finalString.replaceAll("[-,.!?)(/'\"]", " ");
	    			
	    			// Sentence, word, space count
	    			
	    			String line; 
	    			Reader inputString = new StringReader(finalString);
	    			BufferedReader reader = new BufferedReader(inputString);
	    			
	    	        while((line = reader.readLine()) != null) 
	    	        { 
	    	            
	    	            if(!(line.equals(""))) 
	    	            {     	                  
	    	                // \\s+ is the space delimiter in java 
	    	                String[] wordList = line.split("\\s+"); 
	    	                wordCount += wordList.length; 
	    	                spaceCount += wordCount -1; 
	    	                // [!?.:]+ is the sentence delimiter in java 
	    	                String[] sentenceList = line.split("[!?.:]+"); 
	    	                sentenceCount += sentenceList.length;
	    	            } 
	    	        }
	    				    			
//	        		bodyText=finalString;	
	        		Elements contentCode = doc.select("pre");
	        		bodyCode = contentCode.text();
	        		
	        		//Line of code percentage
	        		
	        		String lineOfCode; 
	    			Reader inputStringCode = new StringReader(bodyCode);
	    			BufferedReader readerCode = new BufferedReader(inputStringCode);
	    			
	    	        while((lineOfCode = readerCode.readLine()) != null) 
	    	        { 
	    	            
	    	            if(!(lineOfCode.equals(""))) 
	    	            {     	                  
	    	            	locCount++;
	    	            } 
	    	        }
	        		if((locCount+sentenceCount)== 0 ) sentenceCount = 1;
	        		locPercentage = locCount / (double)(locCount+sentenceCount);
	        			        		
//	        		String[] words = bodyText.split("\\s+");
	        		
	        		// Text Speak Count
	        	 			        	
	        	 	String[] txtspk = textSpeakContent.split(" ");
	        	 	String [] post = finalString.split("\\s+");
	        		
	        		for(String p: post) {
	        			for(String tsc: txtspk) {
	        				if(p.equals(tsc))
	        					txtSpeakCount++;
//	        				System.out.println("++" +p + ">> " + tsc);
	        			}
	        		}
	        		
	        		//URL count
	        		urlCount = doc.select("a").size();
	        		
	        		// Email Count
	        		Matcher m = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+").matcher(contentText.toString());
	        	    while(m.find()) {
	        	    	emailCount++;
//	        	     email= m.group();              
//	        	     System.out.println(email);
	        	    }
	        			        		
	        		//Accessing the count of a word
//	        		System.out.println(uniqueWordsAndCount.get("c")); //This returns the count
	        		
	        		
				}catch (Exception e) {
					// TODO: handle exception
					//e.printStackTrace();
				}
	        	
	        	try {
	        		title=questionList.get(15).toString();
	        		titleLength = title.length(); // Calculate title length
	        		isCapitalTitle = 0;
	        		if (Character.isUpperCase(title.charAt(0)))
	        			isCapitalTitle = 1; // Capital title count
	        		
				}catch (Exception e) {
					// TODO: handle exception
					//e.printStackTrace();
				}
	        	
	        	try {
	        		tags=questionList.get(16).toString();
	        		tags = tags.replaceAll("><", " ");
	        		tags = tags.replaceAll("[\\<,\\>]","");
	        		
	        		String[] tagList = tags.split(" ");
	        		
	        		for(int k=0; k<tagList.length; k++) {
	        			
	        			tagCount++; // number of tag count
	        		}
	        		
				}catch (Exception e) {

					//e.printStackTrace();
				}
	        	    
	        	// lower case, uppercase percentage
	        	titleNBody = bodyText+title;
	        	int lowerCaseCount = 0;
	        	int upperCaseCount = 0;	        	
	        	for(int j =0; j <titleNBody.length(); j ++) {
	    			if (Character.isLowerCase(titleNBody.charAt(j ))) lowerCaseCount++;
	    			else if (Character.isUpperCase(titleNBody.charAt(j ))) upperCaseCount++;
	    		}
	        	lowercasePercentage = lowerCaseCount/(double)titleNBody.length();
	        	uppercasePercentage = upperCaseCount/(double)titleNBody.length();        	
	        	
	        	
	        	
	        	if(bodyCode != null && !bodyCode.isEmpty()){
		        	if(Integer.parseInt(score)>0) {
		        		csvWriterTextCodeH.write(id,bodyLength,emailCount,lowercasePercentage,spaceCount,tagCount,txtSpeakCount,titleLength,isCapitalTitle,uppercasePercentage,urlCount,locPercentage,sentenceCount,wordCount);
		        		
		        	}
		        	else {
//		        		csvWriter.write(id,score,totalEntropy,"L");
		        		csvWriterTextCodeL.write(id,bodyLength,emailCount,lowercasePercentage,spaceCount,tagCount,txtSpeakCount,titleLength,isCapitalTitle,uppercasePercentage,urlCount,locPercentage,sentenceCount,wordCount);
		        	}
	        	}
	        	else {
	        		if(Integer.parseInt(score)>0) {
	//	        		csvWriter.write(id,score,totalEntropy,"H");
		        		csvWriterTextH.write(id,bodyLength,emailCount,lowercasePercentage,spaceCount,tagCount,txtSpeakCount,titleLength,isCapitalTitle,uppercasePercentage,urlCount,locPercentage,sentenceCount,wordCount);
			        		
			        	}
		        	else {
		        		csvWriterTextL.write(id,bodyLength,emailCount,lowercasePercentage,spaceCount,tagCount,txtSpeakCount,titleLength,isCapitalTitle,uppercasePercentage,urlCount,locPercentage,sentenceCount,wordCount);
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

class JavaSOMetric extends Thread{
	
	ICsvListReader listReader = null;
//	ICsvListWriter csvWriter = null;
	ICsvListWriter csvWriterTextH = null;
	ICsvListWriter csvWriterTextL = null;
	ICsvListWriter csvWriterTextCodeH = null;
	ICsvListWriter csvWriterTextCodeL = null;
	
	@SuppressWarnings("resource")
	public void run() {

    try {

    	// For TextOnlyFile
    	
    	String fileLocation = "E:/Projects/SOContentQualityResources/CSVData/Java/QueryData/";
    	
    	String filePathTS = "E:/Projects/SOContentQualityResources/DataStore/Presentation/TextSpeakStore.txt";
    	
//    	String outFile = "E:/Projects/SOContentQualityResources/CSVData/Java/MetricResults/TagEntropy/TagEntropy.csv";
    	String outFileTextH = "E:/Projects/SOContentQualityResources/FeatureValues/TextOnly/Java/SOMetric/SOMetric_Text_GT_0.csv";
    	String outFileTextL = "E:/Projects/SOContentQualityResources/FeatureValues/TextOnly/Java/SOMetric/SOMetric_Text_LT_0.csv";
    	String outFileTextCodeH = "E:/Projects/SOContentQualityResources/FeatureValues/TextCode/Java/SOMetric/SOMetric_TextCode_GT_0.csv";
    	String outFileTextCodeL = "E:/Projects/SOContentQualityResources/FeatureValues/TextCode/Java/SOMetric/SOMetric_TextCode_LT_0.csv";

//    	csvWriter = new CsvListWriter(new FileWriter(outFile),CsvPreference.STANDARD_PREFERENCE);
    	csvWriterTextH = new CsvListWriter(new FileWriter(outFileTextH),CsvPreference.STANDARD_PREFERENCE);
    	csvWriterTextL = new CsvListWriter(new FileWriter(outFileTextL),CsvPreference.STANDARD_PREFERENCE);
    	csvWriterTextCodeH = new CsvListWriter(new FileWriter(outFileTextCodeH),CsvPreference.STANDARD_PREFERENCE);
    	csvWriterTextCodeL = new CsvListWriter(new FileWriter(outFileTextCodeL),CsvPreference.STANDARD_PREFERENCE);
    	
//    	csvWriter.write("Id","Score","TagEntropy","Category");
    	csvWriterTextH.write("Id","bodyLength","emailCount","lowercasePercentage", "spaceCount", "tagCount", "txtSpeakCount","titleLength","isCapitalTitle","uppercasePercentage","urlCount","locPercentage","sentenceCount","wordCount");
    	csvWriterTextL.write("Id","bodyLength","emailCount","lowercasePercentage", "spaceCount", "tagCount", "txtSpeakCount","titleLength","isCapitalTitle","uppercasePercentage","urlCount","locPercentage","sentenceCount","wordCount");
    	csvWriterTextCodeH.write("Id","bodyLength","emailCount","lowercasePercentage", "spaceCount", "tagCount", "txtSpeakCount","titleLength","isCapitalTitle","uppercasePercentage","urlCount","locPercentage","sentenceCount","wordCount");
    	csvWriterTextCodeL.write("Id","bodyLength","emailCount","lowercasePercentage", "spaceCount", "tagCount", "txtSpeakCount","titleLength","isCapitalTitle","uppercasePercentage","urlCount","locPercentage","sentenceCount","wordCount");
    	
    	int NO_OF_FILE = 15;
    	
    	for (int i = 1; i<= NO_OF_FILE; i++) {
	        listReader = new CsvListReader(new FileReader(fileLocation+"QueryResults_"+i+"_AnsCount1_Score_NE_0_2017.CSV"), CsvPreference.STANDARD_PREFERENCE);
	        System.out.println("Java File:"+i);
	        listReader.getHeader(true); // skip the header (can't be used with CsvListReader)
	        final CellProcessor[] processors = getProcessors();               
	        List<Object> questionList;
	        
	        String textSpeakContent = "";
	        File txtSpeakFile = new File(filePathTS);
	        textSpeakContent = new Scanner(txtSpeakFile).useDelimiter("\\Z").next();	        
	        
	        while( (questionList = listReader.read(processors)) != null ) {
	        		        	 
	           	String id="";                          		// int
	        	String score="-9999";                       // int
	        	String tags="";                        		// nvarchar (250)
	        	String title = "";
	        	String bodyContent="";
	        	String bodyText="";                        	// nvarchar (max)
	        	String bodyCode="";                        	// nvarchar (max)
	        	
	        	
	       
	        	String titleNBody = "";
	        	int locCount = 0;
	        	
	        	int bodyLength = 0;
	        	int emailCount = 0;
	        	double lowercasePercentage = 0.0;
	        	int spaceCount = 0;
	        	int tagCount =0;
	        	int txtSpeakCount =0;
	        	int titleLength =0;
	        	int isCapitalTitle = 0;
	        	double uppercasePercentage =0.0;
	        	int urlCount =0;
	        	double locPercentage =0.0;
	        	int sentenceCount = 0;
	        	int wordCount = 0;

	        	
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
	        		bodyContent = doc.text().toString();
	        		bodyLength = bodyContent.length(); // Calculate body length
	        			        		
	    			Elements contentText = doc.select("p");
	    			String finalString = contentText.text();
	    			finalString = finalString.replaceAll("[\\[,\\]]", " ");
//	    			finalString = finalString.replaceAll("[-,.!?)(/'\"]", " ");
	    			
	    			// Sentence, word, space count
	    			
	    			String line; 
	    			Reader inputString = new StringReader(finalString);
	    			BufferedReader reader = new BufferedReader(inputString);
	    			
	    	        while((line = reader.readLine()) != null) 
	    	        { 
	    	            
	    	            if(!(line.equals(""))) 
	    	            {     	                  
	    	                // \\s+ is the space delimiter in java 
	    	                String[] wordList = line.split("\\s+"); 
	    	                wordCount += wordList.length; 
	    	                spaceCount += wordCount -1; 
	    	                // [!?.:]+ is the sentence delimiter in java 
	    	                String[] sentenceList = line.split("[!?.:]+"); 
	    	                sentenceCount += sentenceList.length;
	    	            } 
	    	        }
	    				    			
//	        		bodyText=finalString;	
	        		Elements contentCode = doc.select("pre");
	        		bodyCode = contentCode.text();
	        		
	        		//Line of code percentage
	        		
	        		String lineOfCode; 
	    			Reader inputStringCode = new StringReader(bodyCode);
	    			BufferedReader readerCode = new BufferedReader(inputStringCode);
	    			
	    	        while((lineOfCode = readerCode.readLine()) != null) 
	    	        { 
	    	            
	    	            if(!(lineOfCode.equals(""))) 
	    	            {     	                  
	    	            	locCount++;
	    	            } 
	    	        }
	        		if((locCount+sentenceCount)== 0 ) sentenceCount = 1;
	        		locPercentage = locCount / (double)(locCount+sentenceCount);
	        			        		
//	        		String[] words = bodyText.split("\\s+");
	        		
	        		// Text Speak Count
	        	 			        	
	        	 	String[] txtspk = textSpeakContent.split(" ");
	        	 	String [] post = finalString.split("\\s+");
	        		
	        		for(String p: post) {
	        			for(String tsc: txtspk) {
	        				if(p.equals(tsc))
	        					txtSpeakCount++;
//	        				System.out.println("++" +p + ">> " + tsc);
	        			}
	        		}
	        		
	        		//URL count
	        		urlCount = doc.select("a").size();
	        		
	        		// Email Count
	        		Matcher m = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+").matcher(contentText.toString());
	        	    while(m.find()) {
	        	    	emailCount++;
//	        	     email= m.group();              
//	        	     System.out.println(email);
	        	    }
	        			        		
	        		//Accessing the count of a word
//	        		System.out.println(uniqueWordsAndCount.get("c")); //This returns the count
	        		
	        		
				}catch (Exception e) {
					// TODO: handle exception
					//e.printStackTrace();
				}
	        	
	        	try {
	        		title=questionList.get(15).toString();
	        		titleLength = title.length(); // Calculate title length
	        		isCapitalTitle = 0;
	        		if (Character.isUpperCase(title.charAt(0)))
	        			isCapitalTitle = 1; // Capital title count
	        		
				}catch (Exception e) {
					// TODO: handle exception
					//e.printStackTrace();
				}
	        	
	        	try {
	        		tags=questionList.get(16).toString();
	        		tags = tags.replaceAll("><", " ");
	        		tags = tags.replaceAll("[\\<,\\>]","");
	        		
	        		String[] tagList = tags.split(" ");
	        		
	        		for(int k=0; k<tagList.length; k++) {
	        			
	        			tagCount++; // number of tag count
	        		}
	        		
				}catch (Exception e) {

					//e.printStackTrace();
				}
	        	    
	        	// lower case, uppercase percentage
	        	titleNBody = bodyText+title;
	        	int lowerCaseCount = 0;
	        	int upperCaseCount = 0;	        	
	        	for(int j =0; j <titleNBody.length(); j ++) {
	    			if (Character.isLowerCase(titleNBody.charAt(j ))) lowerCaseCount++;
	    			else if (Character.isUpperCase(titleNBody.charAt(j ))) upperCaseCount++;
	    		}
	        	lowercasePercentage = lowerCaseCount/(double)titleNBody.length();
	        	uppercasePercentage = upperCaseCount/(double)titleNBody.length();        	
	        	
	        	
	        	
	        	if(bodyCode != null && !bodyCode.isEmpty()){
		        	if(Integer.parseInt(score)>0) {
		        		csvWriterTextCodeH.write(id,bodyLength,emailCount,lowercasePercentage,spaceCount,tagCount,txtSpeakCount,titleLength,isCapitalTitle,uppercasePercentage,urlCount,locPercentage,sentenceCount,wordCount);
		        		
		        	}
		        	else {
//		        		csvWriter.write(id,score,totalEntropy,"L");
		        		csvWriterTextCodeL.write(id,bodyLength,emailCount,lowercasePercentage,spaceCount,tagCount,txtSpeakCount,titleLength,isCapitalTitle,uppercasePercentage,urlCount,locPercentage,sentenceCount,wordCount);
		        	}
	        	}
	        	else {
	        		if(Integer.parseInt(score)>0) {
	//	        		csvWriter.write(id,score,totalEntropy,"H");
		        		csvWriterTextH.write(id,bodyLength,emailCount,lowercasePercentage,spaceCount,tagCount,txtSpeakCount,titleLength,isCapitalTitle,uppercasePercentage,urlCount,locPercentage,sentenceCount,wordCount);
			        		
			        	}
		        	else {
		        		csvWriterTextL.write(id,bodyLength,emailCount,lowercasePercentage,spaceCount,tagCount,txtSpeakCount,titleLength,isCapitalTitle,uppercasePercentage,urlCount,locPercentage,sentenceCount,wordCount);
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

class JSSOMetric extends Thread{
	
	ICsvListReader listReader = null;
//	ICsvListWriter csvWriter = null;
	ICsvListWriter csvWriterTextH = null;
	ICsvListWriter csvWriterTextL = null;
	ICsvListWriter csvWriterTextCodeH = null;
	ICsvListWriter csvWriterTextCodeL = null;
	
	@SuppressWarnings("resource")
	public void run() {

    try {

    	// For TextOnlyFile
    	
    	String fileLocation = "E:/Projects/SOContentQualityResources/CSVData/JavaScript/QueryData/";
    	
    	String filePathTS = "E:/Projects/SOContentQualityResources/DataStore/Presentation/TextSpeakStore.txt";
    	
//    	String outFile = "E:/Projects/SOContentQualityResources/CSVData/Java/MetricResults/TagEntropy/TagEntropy.csv";
    	String outFileTextH = "E:/Projects/SOContentQualityResources/FeatureValues/TextOnly/JavaScript/SOMetric/SOMetric_Text_GT_0.csv";
    	String outFileTextL = "E:/Projects/SOContentQualityResources/FeatureValues/TextOnly/JavaScript/SOMetric/SOMetric_Text_LT_0.csv";
    	String outFileTextCodeH = "E:/Projects/SOContentQualityResources/FeatureValues/TextCode/JavaScript/SOMetric/SOMetric_TextCode_GT_0.csv";
    	String outFileTextCodeL = "E:/Projects/SOContentQualityResources/FeatureValues/TextCode/JavaScript/SOMetric/SOMetric_TextCode_LT_0.csv";

//    	csvWriter = new CsvListWriter(new FileWriter(outFile),CsvPreference.STANDARD_PREFERENCE);
    	csvWriterTextH = new CsvListWriter(new FileWriter(outFileTextH),CsvPreference.STANDARD_PREFERENCE);
    	csvWriterTextL = new CsvListWriter(new FileWriter(outFileTextL),CsvPreference.STANDARD_PREFERENCE);
    	csvWriterTextCodeH = new CsvListWriter(new FileWriter(outFileTextCodeH),CsvPreference.STANDARD_PREFERENCE);
    	csvWriterTextCodeL = new CsvListWriter(new FileWriter(outFileTextCodeL),CsvPreference.STANDARD_PREFERENCE);
    	
//    	csvWriter.write("Id","Score","TagEntropy","Category");
    	csvWriterTextH.write("Id","bodyLength","emailCount","lowercasePercentage", "spaceCount", "tagCount", "txtSpeakCount","titleLength","isCapitalTitle","uppercasePercentage","urlCount","locPercentage","sentenceCount","wordCount");
    	csvWriterTextL.write("Id","bodyLength","emailCount","lowercasePercentage", "spaceCount", "tagCount", "txtSpeakCount","titleLength","isCapitalTitle","uppercasePercentage","urlCount","locPercentage","sentenceCount","wordCount");
    	csvWriterTextCodeH.write("Id","bodyLength","emailCount","lowercasePercentage", "spaceCount", "tagCount", "txtSpeakCount","titleLength","isCapitalTitle","uppercasePercentage","urlCount","locPercentage","sentenceCount","wordCount");
    	csvWriterTextCodeL.write("Id","bodyLength","emailCount","lowercasePercentage", "spaceCount", "tagCount", "txtSpeakCount","titleLength","isCapitalTitle","uppercasePercentage","urlCount","locPercentage","sentenceCount","wordCount");
    	
    	int NO_OF_FILE = 15;
    	
    	for (int i = 1; i<= NO_OF_FILE; i++) {
	        listReader = new CsvListReader(new FileReader(fileLocation+"QueryResults_"+i+"_AnsCount1_Score_NE_0_2017.CSV"), CsvPreference.STANDARD_PREFERENCE);
	        System.out.println("JavaScript File:"+i);
	        listReader.getHeader(true); // skip the header (can't be used with CsvListReader)
	        final CellProcessor[] processors = getProcessors();               
	        List<Object> questionList;
	        
	        String textSpeakContent = "";
	        File txtSpeakFile = new File(filePathTS);
	        textSpeakContent = new Scanner(txtSpeakFile).useDelimiter("\\Z").next();	        
	        
	        while( (questionList = listReader.read(processors)) != null ) {
	        		        	 
	           	String id="";                          		// int
	        	String score="-9999";                       // int
	        	String tags="";                        		// nvarchar (250)
	        	String title = "";
	        	String bodyContent="";
	        	String bodyText="";                        	// nvarchar (max)
	        	String bodyCode="";                        	// nvarchar (max)
	        	
	        	
	       
	        	String titleNBody = "";
	        	int locCount = 0;
	        	
	        	int bodyLength = 0;
	        	int emailCount = 0;
	        	double lowercasePercentage = 0.0;
	        	int spaceCount = 0;
	        	int tagCount =0;
	        	int txtSpeakCount =0;
	        	int titleLength =0;
	        	int isCapitalTitle = 0;
	        	double uppercasePercentage =0.0;
	        	int urlCount =0;
	        	double locPercentage =0.0;
	        	int sentenceCount = 0;
	        	int wordCount = 0;

	        	
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
	        		bodyContent = doc.text().toString();
	        		bodyLength = bodyContent.length(); // Calculate body length
	        			        		
	    			Elements contentText = doc.select("p");
	    			String finalString = contentText.text();
	    			finalString = finalString.replaceAll("[\\[,\\]]", " ");
//	    			finalString = finalString.replaceAll("[-,.!?)(/'\"]", " ");
	    			
	    			// Sentence, word, space count
	    			
	    			String line; 
	    			Reader inputString = new StringReader(finalString);
	    			BufferedReader reader = new BufferedReader(inputString);
	    			
	    	        while((line = reader.readLine()) != null) 
	    	        { 
	    	            
	    	            if(!(line.equals(""))) 
	    	            {     	                  
	    	                // \\s+ is the space delimiter in java 
	    	                String[] wordList = line.split("\\s+"); 
	    	                wordCount += wordList.length; 
	    	                spaceCount += wordCount -1; 
	    	                // [!?.:]+ is the sentence delimiter in java 
	    	                String[] sentenceList = line.split("[!?.:]+"); 
	    	                sentenceCount += sentenceList.length;
	    	            } 
	    	        }
	    				    			
//	        		bodyText=finalString;	
	        		Elements contentCode = doc.select("pre");
	        		bodyCode = contentCode.text();
	        		
	        		//Line of code percentage
	        		
	        		String lineOfCode; 
	    			Reader inputStringCode = new StringReader(bodyCode);
	    			BufferedReader readerCode = new BufferedReader(inputStringCode);
	    			
	    	        while((lineOfCode = readerCode.readLine()) != null) 
	    	        { 
	    	            
	    	            if(!(lineOfCode.equals(""))) 
	    	            {     	                  
	    	            	locCount++;
	    	            } 
	    	        }
	        		if((locCount+sentenceCount)== 0 ) sentenceCount = 1;
	        		locPercentage = locCount / (double)(locCount+sentenceCount);
	        			        		
//	        		String[] words = bodyText.split("\\s+");
	        		
	        		// Text Speak Count
	        	 			        	
	        	 	String[] txtspk = textSpeakContent.split(" ");
	        	 	String [] post = finalString.split("\\s+");
	        		
	        		for(String p: post) {
	        			for(String tsc: txtspk) {
	        				if(p.equals(tsc))
	        					txtSpeakCount++;
//	        				System.out.println("++" +p + ">> " + tsc);
	        			}
	        		}
	        		
	        		//URL count
	        		urlCount = doc.select("a").size();
	        		
	        		// Email Count
	        		Matcher m = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+").matcher(contentText.toString());
	        	    while(m.find()) {
	        	    	emailCount++;
//	        	     email= m.group();              
//	        	     System.out.println(email);
	        	    }
	        			        		
	        		//Accessing the count of a word
//	        		System.out.println(uniqueWordsAndCount.get("c")); //This returns the count
	        		
	        		
				}catch (Exception e) {
					// TODO: handle exception
					//e.printStackTrace();
				}
	        	
	        	try {
	        		title=questionList.get(15).toString();
	        		titleLength = title.length(); // Calculate title length
	        		isCapitalTitle = 0;
	        		if (Character.isUpperCase(title.charAt(0)))
	        			isCapitalTitle = 1; // Capital title count
	        		
				}catch (Exception e) {
					// TODO: handle exception
					//e.printStackTrace();
				}
	        	
	        	try {
	        		tags=questionList.get(16).toString();
	        		tags = tags.replaceAll("><", " ");
	        		tags = tags.replaceAll("[\\<,\\>]","");
	        		
	        		String[] tagList = tags.split(" ");
	        		
	        		for(int k=0; k<tagList.length; k++) {
	        			
	        			tagCount++; // number of tag count
	        		}
	        		
				}catch (Exception e) {

					//e.printStackTrace();
				}
	        	    
	        	// lower case, uppercase percentage
	        	titleNBody = bodyText+title;
	        	int lowerCaseCount = 0;
	        	int upperCaseCount = 0;	        	
	        	for(int j =0; j <titleNBody.length(); j ++) {
	    			if (Character.isLowerCase(titleNBody.charAt(j ))) lowerCaseCount++;
	    			else if (Character.isUpperCase(titleNBody.charAt(j ))) upperCaseCount++;
	    		}
	        	lowercasePercentage = lowerCaseCount/(double)titleNBody.length();
	        	uppercasePercentage = upperCaseCount/(double)titleNBody.length();        	
	        	
	        	
	        	
	        	if(bodyCode != null && !bodyCode.isEmpty()){
		        	if(Integer.parseInt(score)>0) {
		        		csvWriterTextCodeH.write(id,bodyLength,emailCount,lowercasePercentage,spaceCount,tagCount,txtSpeakCount,titleLength,isCapitalTitle,uppercasePercentage,urlCount,locPercentage,sentenceCount,wordCount);
		        		
		        	}
		        	else {
//		        		csvWriter.write(id,score,totalEntropy,"L");
		        		csvWriterTextCodeL.write(id,bodyLength,emailCount,lowercasePercentage,spaceCount,tagCount,txtSpeakCount,titleLength,isCapitalTitle,uppercasePercentage,urlCount,locPercentage,sentenceCount,wordCount);
		        	}
	        	}
	        	else {
	        		if(Integer.parseInt(score)>0) {
	//	        		csvWriter.write(id,score,totalEntropy,"H");
		        		csvWriterTextH.write(id,bodyLength,emailCount,lowercasePercentage,spaceCount,tagCount,txtSpeakCount,titleLength,isCapitalTitle,uppercasePercentage,urlCount,locPercentage,sentenceCount,wordCount);
			        		
			        	}
		        	else {
		        		csvWriterTextL.write(id,bodyLength,emailCount,lowercasePercentage,spaceCount,tagCount,txtSpeakCount,titleLength,isCapitalTitle,uppercasePercentage,urlCount,locPercentage,sentenceCount,wordCount);
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

class PySOMetric extends Thread{
	
	ICsvListReader listReader = null;
//	ICsvListWriter csvWriter = null;
	ICsvListWriter csvWriterTextH = null;
	ICsvListWriter csvWriterTextL = null;
	ICsvListWriter csvWriterTextCodeH = null;
	ICsvListWriter csvWriterTextCodeL = null;
	
	@SuppressWarnings("resource")
	public void run() {

    try {

    	// For TextOnlyFile
    	
    	String fileLocation = "E:/Projects/SOContentQualityResources/CSVData/Python/QueryData/";
    	
    	String filePathTS = "E:/Projects/SOContentQualityResources/DataStore/Presentation/TextSpeakStore.txt";
    	
//    	String outFile = "E:/Projects/SOContentQualityResources/CSVData/Java/MetricResults/TagEntropy/TagEntropy.csv";
    	String outFileTextH = "E:/Projects/SOContentQualityResources/FeatureValues/TextOnly/Python/SOMetric/SOMetric_Text_GT_0.csv";
    	String outFileTextL = "E:/Projects/SOContentQualityResources/FeatureValues/TextOnly/Python/SOMetric/SOMetric_Text_LT_0.csv";
    	String outFileTextCodeH = "E:/Projects/SOContentQualityResources/FeatureValues/TextCode/Python/SOMetric/SOMetric_TextCode_GT_0.csv";
    	String outFileTextCodeL = "E:/Projects/SOContentQualityResources/FeatureValues/TextCode/Python/SOMetric/SOMetric_TextCode_LT_0.csv";

//    	csvWriter = new CsvListWriter(new FileWriter(outFile),CsvPreference.STANDARD_PREFERENCE);
    	csvWriterTextH = new CsvListWriter(new FileWriter(outFileTextH),CsvPreference.STANDARD_PREFERENCE);
    	csvWriterTextL = new CsvListWriter(new FileWriter(outFileTextL),CsvPreference.STANDARD_PREFERENCE);
    	csvWriterTextCodeH = new CsvListWriter(new FileWriter(outFileTextCodeH),CsvPreference.STANDARD_PREFERENCE);
    	csvWriterTextCodeL = new CsvListWriter(new FileWriter(outFileTextCodeL),CsvPreference.STANDARD_PREFERENCE);
    	
//    	csvWriter.write("Id","Score","TagEntropy","Category");
    	csvWriterTextH.write("Id","bodyLength","emailCount","lowercasePercentage", "spaceCount", "tagCount", "txtSpeakCount","titleLength","isCapitalTitle","uppercasePercentage","urlCount","locPercentage","sentenceCount","wordCount");
    	csvWriterTextL.write("Id","bodyLength","emailCount","lowercasePercentage", "spaceCount", "tagCount", "txtSpeakCount","titleLength","isCapitalTitle","uppercasePercentage","urlCount","locPercentage","sentenceCount","wordCount");
    	csvWriterTextCodeH.write("Id","bodyLength","emailCount","lowercasePercentage", "spaceCount", "tagCount", "txtSpeakCount","titleLength","isCapitalTitle","uppercasePercentage","urlCount","locPercentage","sentenceCount","wordCount");
    	csvWriterTextCodeL.write("Id","bodyLength","emailCount","lowercasePercentage", "spaceCount", "tagCount", "txtSpeakCount","titleLength","isCapitalTitle","uppercasePercentage","urlCount","locPercentage","sentenceCount","wordCount");
    	
    	int NO_OF_FILE = 10;
    	
    	for (int i = 1; i<= NO_OF_FILE; i++) {
	        listReader = new CsvListReader(new FileReader(fileLocation+"QueryResults_"+i+"_AnsCount1_Score_NE_0_2017.CSV"), CsvPreference.STANDARD_PREFERENCE);
	        System.out.println("Python File:"+i);
	        listReader.getHeader(true); // skip the header (can't be used with CsvListReader)
	        final CellProcessor[] processors = getProcessors();               
	        List<Object> questionList;
	        
	        String textSpeakContent = "";
	        File txtSpeakFile = new File(filePathTS);
	        textSpeakContent = new Scanner(txtSpeakFile).useDelimiter("\\Z").next();	        
	        
	        while( (questionList = listReader.read(processors)) != null ) {
	        		        	 
	           	String id="";                          		// int
	        	String score="-9999";                       // int
	        	String tags="";                        		// nvarchar (250)
	        	String title = "";
	        	String bodyContent="";
	        	String bodyText="";                        	// nvarchar (max)
	        	String bodyCode="";                        	// nvarchar (max)
	        	
	        	
	       
	        	String titleNBody = "";
	        	int locCount = 0;
	        	
	        	int bodyLength = 0;
	        	int emailCount = 0;
	        	double lowercasePercentage = 0.0;
	        	int spaceCount = 0;
	        	int tagCount =0;
	        	int txtSpeakCount =0;
	        	int titleLength =0;
	        	int isCapitalTitle = 0;
	        	double uppercasePercentage =0.0;
	        	int urlCount =0;
	        	double locPercentage =0.0;
	        	int sentenceCount = 0;
	        	int wordCount = 0;

	        	
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
	        		bodyContent = doc.text().toString();
	        		bodyLength = bodyContent.length(); // Calculate body length
	        			        		
	    			Elements contentText = doc.select("p");
	    			String finalString = contentText.text();
	    			finalString = finalString.replaceAll("[\\[,\\]]", " ");
//	    			finalString = finalString.replaceAll("[-,.!?)(/'\"]", " ");
	    			
	    			// Sentence, word, space count
	    			
	    			String line; 
	    			Reader inputString = new StringReader(finalString);
	    			BufferedReader reader = new BufferedReader(inputString);
	    			
	    	        while((line = reader.readLine()) != null) 
	    	        { 
	    	            
	    	            if(!(line.equals(""))) 
	    	            {     	                  
	    	                // \\s+ is the space delimiter in java 
	    	                String[] wordList = line.split("\\s+"); 
	    	                wordCount += wordList.length; 
	    	                spaceCount += wordCount -1; 
	    	                // [!?.:]+ is the sentence delimiter in java 
	    	                String[] sentenceList = line.split("[!?.:]+"); 
	    	                sentenceCount += sentenceList.length;
	    	            } 
	    	        }
	    				    			
//	        		bodyText=finalString;	
	        		Elements contentCode = doc.select("pre");
	        		bodyCode = contentCode.text();
	        		
	        		//Line of code percentage
	        		
	        		String lineOfCode; 
	    			Reader inputStringCode = new StringReader(bodyCode);
	    			BufferedReader readerCode = new BufferedReader(inputStringCode);
	    			
	    	        while((lineOfCode = readerCode.readLine()) != null) 
	    	        { 
	    	            
	    	            if(!(lineOfCode.equals(""))) 
	    	            {     	                  
	    	            	locCount++;
	    	            } 
	    	        }
	        		if((locCount+sentenceCount)== 0 ) sentenceCount = 1;
	        		locPercentage = locCount / (double)(locCount+sentenceCount);
	        			        		
//	        		String[] words = bodyText.split("\\s+");
	        		
	        		// Text Speak Count
	        	 			        	
	        	 	String[] txtspk = textSpeakContent.split(" ");
	        	 	String [] post = finalString.split("\\s+");
	        		
	        		for(String p: post) {
	        			for(String tsc: txtspk) {
	        				if(p.equals(tsc))
	        					txtSpeakCount++;
//	        				System.out.println("++" +p + ">> " + tsc);
	        			}
	        		}
	        		
	        		//URL count
	        		urlCount = doc.select("a").size();
	        		
	        		// Email Count
	        		Matcher m = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+").matcher(contentText.toString());
	        	    while(m.find()) {
	        	    	emailCount++;
//	        	     email= m.group();              
//	        	     System.out.println(email);
	        	    }
	        			        		
	        		//Accessing the count of a word
//	        		System.out.println(uniqueWordsAndCount.get("c")); //This returns the count
	        		
	        		
				}catch (Exception e) {
					// TODO: handle exception
					//e.printStackTrace();
				}
	        	
	        	try {
	        		title=questionList.get(15).toString();
	        		titleLength = title.length(); // Calculate title length
	        		isCapitalTitle = 0;
	        		if (Character.isUpperCase(title.charAt(0)))
	        			isCapitalTitle = 1; // Capital title count
	        		
				}catch (Exception e) {
					// TODO: handle exception
					//e.printStackTrace();
				}
	        	
	        	try {
	        		tags=questionList.get(16).toString();
	        		tags = tags.replaceAll("><", " ");
	        		tags = tags.replaceAll("[\\<,\\>]","");
	        		
	        		String[] tagList = tags.split(" ");
	        		
	        		for(int k=0; k<tagList.length; k++) {
	        			
	        			tagCount++; // number of tag count
	        		}
	        		
				}catch (Exception e) {

					//e.printStackTrace();
				}
	        	    
	        	// lower case, uppercase percentage
	        	titleNBody = bodyText+title;
	        	int lowerCaseCount = 0;
	        	int upperCaseCount = 0;	        	
	        	for(int j =0; j <titleNBody.length(); j ++) {
	    			if (Character.isLowerCase(titleNBody.charAt(j ))) lowerCaseCount++;
	    			else if (Character.isUpperCase(titleNBody.charAt(j ))) upperCaseCount++;
	    		}
	        	lowercasePercentage = lowerCaseCount/(double)titleNBody.length();
	        	uppercasePercentage = upperCaseCount/(double)titleNBody.length();        	
	        	
	        	
	        	
	        	if(bodyCode != null && !bodyCode.isEmpty()){
		        	if(Integer.parseInt(score)>0) {
		        		csvWriterTextCodeH.write(id,bodyLength,emailCount,lowercasePercentage,spaceCount,tagCount,txtSpeakCount,titleLength,isCapitalTitle,uppercasePercentage,urlCount,locPercentage,sentenceCount,wordCount);
		        		
		        	}
		        	else {
//		        		csvWriter.write(id,score,totalEntropy,"L");
		        		csvWriterTextCodeL.write(id,bodyLength,emailCount,lowercasePercentage,spaceCount,tagCount,txtSpeakCount,titleLength,isCapitalTitle,uppercasePercentage,urlCount,locPercentage,sentenceCount,wordCount);
		        	}
	        	}
	        	else {
	        		if(Integer.parseInt(score)>0) {
	//	        		csvWriter.write(id,score,totalEntropy,"H");
		        		csvWriterTextH.write(id,bodyLength,emailCount,lowercasePercentage,spaceCount,tagCount,txtSpeakCount,titleLength,isCapitalTitle,uppercasePercentage,urlCount,locPercentage,sentenceCount,wordCount);
			        		
			        	}
		        	else {
		        		csvWriterTextL.write(id,bodyLength,emailCount,lowercasePercentage,spaceCount,tagCount,txtSpeakCount,titleLength,isCapitalTitle,uppercasePercentage,urlCount,locPercentage,sentenceCount,wordCount);
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

