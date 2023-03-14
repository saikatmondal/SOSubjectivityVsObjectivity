package codeunderstandability;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.supercsv.io.CsvListWriter;
import org.supercsv.io.ICsvListWriter;
import org.supercsv.prefs.CsvPreference;


public class APICalls {
	public static void main(String[] args) throws IOException {
		JavaCallAPI java = new JavaCallAPI();
		CSCallAPI cs = new CSCallAPI();
		JSCallAPI js = new JSCallAPI();
		PyCallAPI py = new PyCallAPI();
		
		java.start();
		cs.start();
		js.start();
		py.start();
	}
}

class JavaCallAPI extends Thread{
	
	public void run(){
		
		ICsvListWriter csvWriter = null;
		
		try {
			csvWriter = new CsvListWriter(new FileWriter("E:/Projects/SOContentQualityResources/FeatureValues/TextCode/Java/CodeUnderstandability/APICalls_Score_LT_0.csv"),CsvPreference.STANDARD_PREFERENCE);
	
			String folderLocation = "E:/Projects/SOContentQualityResources/DataStore/Java/CodeReadability/Code_Score_LT_0/";
			String firstColumnL = "Id";
			String secondColumnL = "NoOfAPICalls";
			csvWriter.write(firstColumnL, secondColumnL);
			
			File folder = new File(folderLocation);
			File[] listOfFiles = folder.listFiles();
			
			
			int count = 0;
			
			String pattern = "\\.[a-zA-Z0-9_]+\\(";
			Pattern api = Pattern.compile(pattern);
			
			for (int j = 0; j < listOfFiles.length; j++) {
	//			StringBuilder buildString = new StringBuilder();
				Scanner myScanner = null;
				String [] fileparts = null;
				String filename = null;
				String srcFile = null;
				String fileContent = null;
				count = 0;
		
				if (listOfFiles[j].isFile()) {
			    	  srcFile=listOfFiles[j].getName();
			    	  fileparts = srcFile.split("\\.");
				      filename = fileparts[0];
				      
				      myScanner = new Scanner(new File(folderLocation+srcFile));
				      fileContent = myScanner.useDelimiter("\\A").next();
				      
	//			  		  myScanner = new Scanner(new File(folderLocation+srcFile));
			  		  
	//			  		  while(myScanner.hasNextLine()) {
	//			  			  buildString.append(myScanner.nextLine());
	//			  		  }
	//			  		  String fileContent = buildString.toString();
				      myScanner.close();
					
			  		  Matcher apiMatch = api.matcher(fileContent);
					
			  		  while(apiMatch.find()) {
			  			  count++;
			  		  }
			  		  csvWriter.write(filename,count);
//			  		  System.out.println(count);
				
			      } 
	
			      
			    }
				
			csvWriter.close();
			System.out.println("Finish!!");
		}catch (Exception e) {
			
		}
		
		
	}

}

class CSCallAPI extends Thread{
	
	public void run(){
		
		ICsvListWriter csvWriter = null;
		
		try {
			csvWriter = new CsvListWriter(new FileWriter("E:/Projects/SOContentQualityResources/FeatureValues/TextCode/C#/CodeUnderstandability/APICalls_Score_LT_0.csv"),CsvPreference.STANDARD_PREFERENCE);
	
			String folderLocation = "E:/Projects/SOContentQualityResources/DataStore/C#/CodeReadability/Code_Score_LT_0/";
			String firstColumnL = "Id";
			String secondColumnL = "NoOfAPICalls";
			csvWriter.write(firstColumnL, secondColumnL);
			
			File folder = new File(folderLocation);
			File[] listOfFiles = folder.listFiles();
			
			
			int count = 0;
			
			String pattern = "\\.[a-zA-Z0-9_]+\\(";
			Pattern api = Pattern.compile(pattern);
			
			for (int j = 0; j < listOfFiles.length; j++) {
	//			StringBuilder buildString = new StringBuilder();
				Scanner myScanner = null;
				String [] fileparts = null;
				String filename = null;
				String srcFile = null;
				String fileContent = null;
				count = 0;
		
				if (listOfFiles[j].isFile()) {
			    	  srcFile=listOfFiles[j].getName();
			    	  fileparts = srcFile.split("\\.");
				      filename = fileparts[0];
				      
				      myScanner = new Scanner(new File(folderLocation+srcFile));
				      fileContent = myScanner.useDelimiter("\\A").next();
				      
	//			  		  myScanner = new Scanner(new File(folderLocation+srcFile));
			  		  
	//			  		  while(myScanner.hasNextLine()) {
	//			  			  buildString.append(myScanner.nextLine());
	//			  		  }
	//			  		  String fileContent = buildString.toString();
				      myScanner.close();
					
			  		  Matcher apiMatch = api.matcher(fileContent);
					
			  		  while(apiMatch.find()) {
			  			  count++;
			  		  }
			  		  csvWriter.write(filename,count);
//			  		  System.out.println(count);
				
			      } 
	
			      
			    }
				
			csvWriter.close();
			System.out.println("Finish!!");
		}catch (Exception e) {
			
		}
		
		
	}

}

class JSCallAPI extends Thread{
	
	public void run(){
		
		ICsvListWriter csvWriter = null;
		
		try {
			csvWriter = new CsvListWriter(new FileWriter("E:/Projects/SOContentQualityResources/FeatureValues/TextCode/JavaScript/CodeUnderstandability/APICalls_Score_LT_0.csv"),CsvPreference.STANDARD_PREFERENCE);
	
			String folderLocation = "E:/Projects/SOContentQualityResources/DataStore/JavaScript/CodeReadability/Code_Score_LT_0/";
			String firstColumnL = "Id";
			String secondColumnL = "NoOfAPICalls";
			csvWriter.write(firstColumnL, secondColumnL);
			
			File folder = new File(folderLocation);
			File[] listOfFiles = folder.listFiles();
			
			
			int count = 0;
			
			String pattern = "\\.[a-zA-Z0-9_]+\\(";
			Pattern api = Pattern.compile(pattern);
			
			for (int j = 0; j < listOfFiles.length; j++) {
	//			StringBuilder buildString = new StringBuilder();
				Scanner myScanner = null;
				String [] fileparts = null;
				String filename = null;
				String srcFile = null;
				String fileContent = null;
				count = 0;
		
				if (listOfFiles[j].isFile()) {
			    	  srcFile=listOfFiles[j].getName();
			    	  fileparts = srcFile.split("\\.");
				      filename = fileparts[0];
				      
				      myScanner = new Scanner(new File(folderLocation+srcFile));
				      fileContent = myScanner.useDelimiter("\\A").next();
				      
	//			  		  myScanner = new Scanner(new File(folderLocation+srcFile));
			  		  
	//			  		  while(myScanner.hasNextLine()) {
	//			  			  buildString.append(myScanner.nextLine());
	//			  		  }
	//			  		  String fileContent = buildString.toString();
				      myScanner.close();
					
			  		  Matcher apiMatch = api.matcher(fileContent);
					
			  		  while(apiMatch.find()) {
			  			  count++;
			  		  }
			  		  csvWriter.write(filename,count);
//			  		  System.out.println(count);
				
			      } 
	
			      
			    }
				
			csvWriter.close();
			System.out.println("Finish!!");
		}catch (Exception e) {
			
		}
		
		
	}

}

class PyCallAPI extends Thread{
	
	public void run(){
		
		ICsvListWriter csvWriter = null;
		
		try {
			csvWriter = new CsvListWriter(new FileWriter("E:/Projects/SOContentQualityResources/FeatureValues/TextCode/Python/CodeUnderstandability/APICalls_Score_LT_0.csv"),CsvPreference.STANDARD_PREFERENCE);
	
			String folderLocation = "E:/Projects/SOContentQualityResources/DataStore/Python/CodeReadability/Code_Score_LT_0/";
			String firstColumnL = "Id";
			String secondColumnL = "NoOfAPICalls";
			csvWriter.write(firstColumnL, secondColumnL);
			
			File folder = new File(folderLocation);
			File[] listOfFiles = folder.listFiles();
			
			
			int count = 0;
			
			String pattern = "\\.[a-zA-Z0-9_]+\\(";
			Pattern api = Pattern.compile(pattern);
			
			for (int j = 0; j < listOfFiles.length; j++) {
	//			StringBuilder buildString = new StringBuilder();
				Scanner myScanner = null;
				String [] fileparts = null;
				String filename = null;
				String srcFile = null;
				String fileContent = null;
				count = 0;
		
				if (listOfFiles[j].isFile()) {
			    	  srcFile=listOfFiles[j].getName();
			    	  fileparts = srcFile.split("\\.");
				      filename = fileparts[0];
				      
				      myScanner = new Scanner(new File(folderLocation+srcFile));
				      fileContent = myScanner.useDelimiter("\\A").next();
				      
	//			  		  myScanner = new Scanner(new File(folderLocation+srcFile));
			  		  
	//			  		  while(myScanner.hasNextLine()) {
	//			  			  buildString.append(myScanner.nextLine());
	//			  		  }
	//			  		  String fileContent = buildString.toString();
				      myScanner.close();
					
			  		  Matcher apiMatch = api.matcher(fileContent);
					
			  		  while(apiMatch.find()) {
			  			  count++;
			  		  }
			  		  csvWriter.write(filename,count);
//			  		  System.out.println(count);
				
			      } 
	
			      
			    }
				
			csvWriter.close();
			System.out.println("Finish!!");
		}catch (Exception e) {
			
		}
		
		
	}

}