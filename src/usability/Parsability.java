package usability;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.supercsv.io.CsvListWriter;
import org.supercsv.io.ICsvListWriter;
import org.supercsv.prefs.CsvPreference;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;


public class Parsability {
	public static void main(String[] args) throws IOException {
		ParseJava obj = new ParseJava();
		obj.parseJava();
	}

}


class ParseJava {
	public void parseJava() throws IOException {
		
		ICsvListWriter csvWriter = null;
		csvWriter = new CsvListWriter(new FileWriter("E:/Projects/SOContentQualityResources/FeatureValues/TextCode/Java/Usability/Parsability/Usability_Score_GT_0.csv"),CsvPreference.STANDARD_PREFERENCE);

		String folderLocation = "E:/Projects/SOContentQualityResources/DataStore/Java/CodeReadability/Code_Score_GT_0/";
		String firstColumnL = "Id";
		String secondColumnL = "Parsability";
		csvWriter.write(firstColumnL, secondColumnL);
		
		File folder = new File(folderLocation);
		File[] listOfFiles = folder.listFiles();
		
		
		for (int j = 0; j < listOfFiles.length; j++) {
//			StringBuilder buildString = new StringBuilder();
			Scanner myScanner = null;
			String [] fileparts = null;
			String filename = null;
			String srcFile = null;
			String fileContent = null;
			CompilationUnit compileJava = null;
	
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

			
			
			try {
				compileJava = JavaParser.parse(fileContent);
			} catch (Exception e) {
				
			}
			
			
			
			if(compileJava != null) {
//				System.out.println("Yes");
				csvWriter.write(filename,1);
			}
			else {
//				System.out.println("No");
				csvWriter.write(filename,0);
			}
		
	      } 
		}
			
		csvWriter.close();
		System.out.println("Done Successfully!!");
		
	}

}