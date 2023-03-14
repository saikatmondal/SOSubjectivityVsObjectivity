package sentiment;

import java.io.File;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.supercsv.io.CsvListWriter;
import org.supercsv.io.ICsvListWriter;
import org.supercsv.prefs.CsvPreference;

import readability.MetricType;
import readability.ReadabilityEndpoint;
import utility.ContentLoader;

public class SentimentMeasure {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JavaSentimentMeasure jSentiment = new JavaSentimentMeasure();
		CSharpSentimentMeasure cSentiment = new CSharpSentimentMeasure();
		JavascriptSentimentMeasure jScriptSentiment = new JavascriptSentimentMeasure();
		PySentimentMeasure pySentiment = new PySentimentMeasure();
		
		jSentiment.start();
		cSentiment.start();
		jScriptSentiment.start();
		pySentiment.start();

	}

}

//************************************* Java *******************************//

class JavaSentimentMeasure extends Thread{

	public void run()
	{
		ICsvListWriter csvWriter = null;
		
		//For Input File Location
		String fileLocation = "E:/Projects/SOContentQualityResources/DataStore/Java/SentimentFile/Sentiment_Score_GT_0/Output_Sentiment_Score_GT_0.txt";

		// For output file
		try {
			System.out.println("Satrt Java!!");
			
			csvWriter = new CsvListWriter(new FileWriter("E:/Projects/SOContentQualityResources/CSVData/Java/MetricResults/Sentiment/Sentiment_Score_GT_0.csv"),CsvPreference.STANDARD_PREFERENCE);
			String firstColumnTitle = "Id";
			String secondColumnTitle = "Sentiment_Polarity";
			csvWriter.write(firstColumnTitle, secondColumnTitle);
			
			int posiCount=0, negCount=0,neuCount=0,mixCount=0,totCount=0, p=0, n=0;
			File file = new File(fileLocation);
			Scanner myScanner = new Scanner(file);

			String[] tempString;

   			while(myScanner.hasNextLine()) {
			tempString = myScanner.nextLine().split(" ");
			
			p = Integer.parseInt(tempString[1].trim());
			n = Integer.parseInt(tempString[2].trim());
			
			totCount++;
			if(p == 1 && n == (-1)){
				neuCount++;
				csvWriter.write(tempString[0],"NEU");
			} 
			else if(p > 1 && n == (-1)){
				posiCount++;
				csvWriter.write(tempString[0],"POS");
			}
			else if(p == 1 && n < (-1)) {
				negCount++;
				csvWriter.write(tempString[0],"NEG");
			}
			else if(p > 1 && n < (-1)) {
				mixCount++;
				csvWriter.write(tempString[0],"MIX");
			}
						
			
			//System.out.println(tempString[0]+" "+sentimentScore);
			}
   			
   			System.out.println("Java:\n"+"Positive:"+ (posiCount/(float)(totCount))*100 + "Negative:"+ (negCount/(float)(totCount))*100 +"Mix:"+(mixCount/(float)(totCount))*100+"Neutral:"+ (neuCount/(float)totCount)*100);
   			
   			System.out.println("Java Sentiment Calculated Successfully!!!");
			myScanner.close();
		    csvWriter.close();
		    
		    
		    
	}catch (Exception e) {
			// TODO: handle exception
	}
	

	}

}

//************************************* C# *******************************//

class CSharpSentimentMeasure extends Thread{

	
	public void run()
	{
		ICsvListWriter csvWriter = null;
		
		//For Input File Location
		String fileLocation = "E:/Projects/SOContentQualityResources/DataStore/C_Sharp/SentimentFile/Sentiment_Score_GT_0/Output_Sentiment_Score_GT_0.txt";

		// For output file
		try {
			System.out.println("C_Sharp Java!!");
			
			csvWriter = new CsvListWriter(new FileWriter("E:/Projects/SOContentQualityResources/CSVData/C_Sharp/MetricResults/Sentiment/Sentiment_Score_GT_0.csv"),CsvPreference.STANDARD_PREFERENCE);
			String firstColumnTitle = "Id";
			String secondColumnTitle = "Sentiment_Polarity";
			csvWriter.write(firstColumnTitle, secondColumnTitle);
			
			int posiCount=0, negCount=0,neuCount=0,mixCount=0,totCount=0, p=0, n=0;
			File file = new File(fileLocation);
			Scanner myScanner = new Scanner(file);

			String[] tempString;

   			while(myScanner.hasNextLine()) {
			tempString = myScanner.nextLine().split(" ");
			
			p = Integer.parseInt(tempString[1].trim());
			n = Integer.parseInt(tempString[2].trim());
			
			totCount++;
			if(p == 1 && n == (-1)){
				neuCount++;
				csvWriter.write(tempString[0],"NEU");
			} 
			else if(p > 1 && n == (-1)){
				posiCount++;
				csvWriter.write(tempString[0],"POS");
			}
			else if(p == 1 && n < (-1)) {
				negCount++;
				csvWriter.write(tempString[0],"NEG");
			}
			else if(p > 1 && n < (-1)) {
				mixCount++;
				csvWriter.write(tempString[0],"MIX");
			}
						
			
			//System.out.println(tempString[0]+" "+sentimentScore);
			}
   			
   			System.out.println("C#:\n"+"Positive:"+ (posiCount/(float)(totCount))*100 + "Negative:"+ (negCount/(float)(totCount))*100 +"Mix:"+(mixCount/(float)(totCount))*100+"Neutral:"+ (neuCount/(float)totCount)*100);
   			

   			System.out.println("C_Sharp Sentiment Calculated Successfully!!!");
			myScanner.close();
		    csvWriter.close();
		    
		    
		    
	}catch (Exception e) {
			// TODO: handle exception
	}
	

	}

}

//************************************* JavaScript *******************************//

class JavascriptSentimentMeasure extends Thread{

	
	public void run()
	{
		ICsvListWriter csvWriter = null;		 
		
		//For Input File Location
		String fileLocation = "E:/Projects/SOContentQualityResources/DataStore/Javascript/SentimentFile/Sentiment_Score_GT_0/Output_Sentiment_Score_GT_0.txt";

		// For output file
		try {
			System.out.println("Javascript Java!!");
			
			csvWriter = new CsvListWriter(new FileWriter("E:/Projects/SOContentQualityResources/CSVData/Javascript/MetricResults/Sentiment/Sentiment_Score_GT_0.csv"),CsvPreference.STANDARD_PREFERENCE);
			String firstColumnTitle = "Id";
			String secondColumnTitle = "Sentiment_Polarity";
			csvWriter.write(firstColumnTitle, secondColumnTitle);
			
			int posiCount=0, negCount=0,neuCount=0,mixCount=0,totCount=0, p=0, n=0;
			File file = new File(fileLocation);
			Scanner myScanner = new Scanner(file);

			String[] tempString;

   			while(myScanner.hasNextLine()) {
			tempString = myScanner.nextLine().split(" ");
			
			p = Integer.parseInt(tempString[1].trim());
			n = Integer.parseInt(tempString[2].trim());
			
			totCount++;
			if(p == 1 && n == (-1)){
				neuCount++;
				csvWriter.write(tempString[0],"NEU");
			} 
			else if(p > 1 && n == (-1)){
				posiCount++;
				csvWriter.write(tempString[0],"POS");
			}
			else if(p == 1 && n < (-1)) {
				negCount++;
				csvWriter.write(tempString[0],"NEG");
			}
			else if(p > 1 && n < (-1)) {
				mixCount++;
				csvWriter.write(tempString[0],"MIX");
			}
						
			
			//System.out.println(tempString[0]+" "+sentimentScore);
			}
   			
   			System.out.println("Javascript:\n"+"Positive:"+ (posiCount/(float)(totCount))*100 + "Negative:"+ (negCount/(float)(totCount))*100 +"Mix:"+(mixCount/(float)(totCount))*100+"Neutral:"+ (neuCount/(float)totCount)*100);
   			

 			System.out.println("Javascript Sentiment Calculated Successfully!!!");
			myScanner.close();
		    csvWriter.close();
		    
		    
		    
	}catch (Exception e) {
			// TODO: handle exception
	}
	

	}

}

//************************************* Python *******************************//

class PySentimentMeasure extends Thread{

	
	public void run()
	{
		ICsvListWriter csvWriter = null;
 
		//For Input File Location
		String fileLocation = "E:/Projects/SOContentQualityResources/DataStore/Python/SentimentFile/Sentiment_Score_GT_0/Output_Sentiment_Score_GT_0.txt";

		// For output file
		try {
			System.out.println("Python Java!!");
			
			csvWriter = new CsvListWriter(new FileWriter("E:/Projects/SOContentQualityResources/CSVData/Python/MetricResults/Sentiment/Sentiment_Score_GT_0.csv"),CsvPreference.STANDARD_PREFERENCE);
			String firstColumnTitle = "Id";
			String secondColumnTitle = "Sentiment_Polarity";
			csvWriter.write(firstColumnTitle, secondColumnTitle);
			
			int posiCount=0, negCount=0,neuCount=0,mixCount=0,totCount=0, p=0, n=0;
			File file = new File(fileLocation);
			Scanner myScanner = new Scanner(file);

			String[] tempString;

   			while(myScanner.hasNextLine()) {
			tempString = myScanner.nextLine().split(" ");
			
			p = Integer.parseInt(tempString[1].trim());
			n = Integer.parseInt(tempString[2].trim());
			
			totCount++;
			if(p == 1 && n == (-1)){
				neuCount++;
				csvWriter.write(tempString[0],"NEU");
			} 
			else if(p > 1 && n == (-1)){
				posiCount++;
				csvWriter.write(tempString[0],"POS");
			}
			else if(p == 1 && n < (-1)) {
				negCount++;
				csvWriter.write(tempString[0],"NEG");
			}
			else if(p > 1 && n < (-1)) {
				mixCount++;
				csvWriter.write(tempString[0],"MIX");
			}			
			
			//System.out.println(tempString[0]+" "+sentimentScore);
			}
   			
   			System.out.println("Python:\n"+"Positive:"+ (posiCount/(float)(totCount))*100 + "Negative:"+ (negCount/(float)(totCount))*100 +"Mix:"+(mixCount/(float)(totCount))*100+"Neutral:"+ (neuCount/(float)totCount)*100);
   			

			System.out.println("Python Sentiment Calculated Successfully!!!");
			myScanner.close();
		    csvWriter.close();
		    
		    
		    
	}catch (Exception e) {
			// TODO: handle exception
	}
	

	}

}

