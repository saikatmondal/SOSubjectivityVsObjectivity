package readability;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sound.midi.Soundbank;

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

public class LixRixReadability {
	public static void main(String[] args) {
		Java jObj = new Java();
		jObj.start();
	}
}

class Java extends Thread {
	
	public void run(){
		
		String outLocationH = "E:/Projects/SOContentQualityResources/FeatureValues/H1.csv";
		String outLocationL = "E:/Projects/SOContentQualityResources/FeatureValues/L1.csv";
		
//		String daleChallWord = "E:/Eclipse/eclipse-workspace/P01_SOContentQuality/resources/DaleChallWord.txt";
//		List<String> dcWordList = new ArrayList<String>();
		
		
		ICsvListReader listReader = null;
		ICsvListWriter csvWriterH = null;
		ICsvListWriter csvWriterL = null;
		
		final int NO_OF_FILES=13;
	
	    try {
	    	
//	    	File file = new File(daleChallWord);
//			Scanner myScanner = new Scanner(file);
//			
//			while(myScanner.hasNextLine()) {
//				String[] dcWords = myScanner.nextLine().split("\\s+");
//				for(int j = 0; j<dcWords.length; j++) {
//					dcWordList.add(dcWords[j].trim());
//					System.out.println(dcWords[j]);
//				}
//			}
//			System.out.println(dcWordList.size());
//			
//			myScanner.close();
	    	
	    	csvWriterH = new CsvListWriter(new FileWriter(outLocationH),CsvPreference.STANDARD_PREFERENCE);
	    	csvWriterL = new CsvListWriter(new FileWriter(outLocationL),CsvPreference.STANDARD_PREFERENCE);
			csvWriterH.write("Id", "Score");			
			csvWriterL.write("Id", "Score");
	    	
	    	
	    	for (int i =1; i <= NO_OF_FILES; i++) {
	    		System.out.println("Java File No:"+i);
	    		
		    	//Reading CSV File
		        listReader = new CsvListReader(new FileReader("E:/Projects/SOContentQualityResources/CSVData/C#/QueryData/QueryResults_"+i+"_AnsCount1_Score_NE_0_2017.csv"), CsvPreference.STANDARD_PREFERENCE);          
		        listReader.getHeader(true); // skip the header (can't be used with CsvListReader)
		        final CellProcessor[] processors = getProcessors();               
		        List<Object> questionList;
		        int countH = 0;
		        int countL = 0;
		        while( (questionList = listReader.read(processors)) != null) {
		        	 
		           	String id="";                          		// int
		        	String score="-9999";                       // int
		        	String bodyText="";                        	// nvarchar (max)
		        	String bodyCode="";                        	// nvarchar (max)
		        	String title="";                      		// nvarchar (250)
		        	
		        	String contentWithTags = "";
		        	
		        	try {
		        		id=questionList.get(0).toString();
					}catch (Exception e) {
						
					}	
		        	
		        	try {
		        		score=questionList.get(6).toString();
					}catch (Exception e) {
						//e.printStackTrace();
					}
		        	
		        	try {
		        		Document doc = Jsoup.parse(questionList.get(8).toString());
		    			Elements contentText = doc.select("p");
		    			String finalString = contentText.text();
//		    			finalString = finalString.replaceAll("[\\[,\\]]", "");
		        		bodyText=finalString;
		        		
		        		contentWithTags = questionList.get(8).toString();
		        		
//		        		System.out.println(bodyText);
		        		
		        		Elements contentCode = doc.select("pre");
		        		bodyCode = contentCode.text();
		        		
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	
		        	try {
		        		title=questionList.get(15).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	
		        	double lixScore = 1.0;
		        	double rixScore = 1.0;
		        	double ndcScore = 1.0;
		        	double dcAdjustedScore = 0.0;
		        	String[] sentences = bodyText.split("[.?!]");
		        	int totalWords = 0;
		        	int longWords = 0;
		        	int difficultWords = 0;
		        	int totalLenLongWords = 0;
		        	for(int l=0; l< sentences.length && sentences.length > 0 ;l++) {
		        		String[] words = sentences[l].split(" ");
		        		
		        		for(int k = 0; k < words.length && words.length > 1; k++ ) {
		        			totalWords++;
		        			if(words[k].length()>6) {
		        				longWords++;
//		        				totalLenLongWords = totalLenLongWords+words[k].length();
		        			}
//		        			if(!dcWordList.contains(words[k])) difficultWords++;
		        		}
		        	}
//		        	System.out.println(bodyText);
//		        	System.out.println(sentences.length);
//		        	System.out.println(totalWords);
//		        	System.out.println(longWords);
		        			        	
//		        	int codeElementCount = 0; 
//					int codeSegmentCount = 0;
//					int netCodeElement = 0;
//					
//					Pattern codeElement = Pattern.compile("\\</code\\>");
//					Matcher matchCodeElement = codeElement.matcher(contentWithTags);
//					while(matchCodeElement.find()) {
//						codeElementCount++;
//					}
//					
//					Pattern codeSegment = Pattern.compile("\\</pre\\>");
//					Matcher matchCodeSegment = codeSegment.matcher(contentWithTags);
//					while(matchCodeSegment.find()) {
//						codeSegmentCount++;
//					}
//		        	netCodeElement = codeElementCount - codeSegmentCount;
//		        	if(netCodeElement<0) netCodeElement=0;
		        	
//		        	if(sentences.length>0 && totalWords>0) {
//	        		lixScore = (totalWords/(double)sentences.length) + 100.0 * (longWords/(double)totalWords);
//	        		}

		        	if((totalWords)>0) {
		        		rixScore = (longWords/(double)(totalWords))*100;
		        	}

//		        	if(bodyText.length()> 450) rixScore= rixScore-20;
//		        	if((difficultWords/(double)totalWords)*100 > 0.05) {
//		        		dcAdjustedScore = 3.6365;
//		        	}
//		        	ndcScore = 15.79*(difficultWords/(double)totalWords) + (0.0496 * (totalWords/(double)sentences.length));
		        	
//		        	System.out.println(lixScore);
		        	
		        	
		        	if((Integer.valueOf(score)> 0) && !(bodyCode != null && !bodyCode.isEmpty())) { //&& !(bodyCode != null && !bodyCode.isEmpty())
		        		csvWriterH.write(id,rixScore);
//		        		countH++;
		        	}

		        	else if((Integer.valueOf(score)< 0) && !(bodyCode != null && !bodyCode.isEmpty())){
		        		csvWriterL.write(id,rixScore);
//		        		countL++;
		        	}
//		        	count++;
		        }
		        
		      	    		
	    	}
	    	csvWriterH.close();
	    	csvWriterL.close();
	    	
	        System.out.println("Data Write in File Finished Successfully!!");
	
	    }catch (Exception e) {
			e.printStackTrace();
		}
	    finally {
	    	try {
	    		if( listReader != null ) {
	                listReader.close();
	        }	
			} catch (Exception e2) {
				
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