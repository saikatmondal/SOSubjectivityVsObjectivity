package usability;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import org.supercsv.io.CsvListWriter;
import org.supercsv.io.ICsvListWriter;
import org.supercsv.prefs.CsvPreference;


public class JavaScriptParser {
	
	public static void main(String[] args) throws InterruptedException, IOException {
		
		
		ICsvListWriter csvWriter = null;
		csvWriter = new CsvListWriter(new FileWriter("E:/Projects/SOContentQualityResources/FeatureValues/TextCode/JavaScript/Usability/Parsability/Usability_Score_LT_0.csv"),CsvPreference.STANDARD_PREFERENCE);

		String folderLocation = "E:/Projects/SOContentQualityResources/DataStore/JavaScript/CodeReadability/Code_Score_LT_0/";
		String firstColumnL = "Id";
		String secondColumnL = "Parsability";
		csvWriter.write(firstColumnL, secondColumnL);
		
		File folder = new File(folderLocation);
		File[] listOfFiles = folder.listFiles();
		
		
		for (int j = 0; j < listOfFiles.length; j++) {

			String [] fileparts = null;
			String filename = null;
			String srcFile = null;
	
			if (listOfFiles[j].isFile()) {
		    	  srcFile=listOfFiles[j].getName();
		    	  fileparts = srcFile.split("\\.");
			      filename = fileparts[0];
			      
			      try {
						ProcessBuilder   ps = new ProcessBuilder("E:/Projects/SOContentQualityResources/JavaScriptParser/JSValidator.exe",folderLocation+srcFile);

						ps.redirectErrorStream(true);
						Process pr = ps.start();  

						BufferedReader in = null;
						in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
						String line= null;
						
						line = in.readLine();
						
						if(line.equalsIgnoreCase("pass")) {
							csvWriter.write(filename,1);
//							System.out.println("YES");
							System.out.println(filename + "  " + "1");
						}
						else {
							csvWriter.write(filename,0);
//							System.out.println("No");
							System.out.println(filename + "  " + "0");
						}
					    
					} catch (IOException e) {
					}
			
		
	      } 
		}
			
		csvWriter.close();
		System.out.println("Done Successfully!!");
		
	}

}


