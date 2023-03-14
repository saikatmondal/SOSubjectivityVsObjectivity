package readability;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.supercsv.io.ICsvListWriter;
import org.supercsv.prefs.CsvPreference;
import org.supercsv.io.CsvListWriter;


import utility.ContentLoader;

public class CodeReadability {
	
	public static double get_readability_score(String codeFragment)
	{
		//code for providing readability score
		return raykernel.apps.readability.eval.Main.getReadability(codeFragment);
		//return new HealsteadComplexityProvider(codeFragment).getHalsteadReadabilityScore();
	}
	

	protected static void collectSampleReadability() throws IOException
	{
		//to write in CSV file
		ICsvListWriter csvWriter = null;

		csvWriter = new CsvListWriter(new FileWriter("E:/Projects/SOContentQualityResources/FeatureValues/TextCode/C#/CodeReadability/Code_Readability_Score_LT_0.csv"),CsvPreference.STANDARD_PREFERENCE);

		
		String folderLocation = "E:/Projects/SOContentQualityResources/DataStore/C#/CodeReadability/Code_Score_LT_0/";
		String firstColumnL = "Id";
		String secondColumnL = "Score";
		csvWriter.write(firstColumnL, secondColumnL);
		
		
		File folder = new File(folderLocation);
		File[] listOfFiles = folder.listFiles();

	    
	    for (int j = 0; j < listOfFiles.length; j++) {
		      if (listOfFiles[j].isFile()) {
		    	  String srcFile=listOfFiles[j].getName();
		  		  String contentCode=ContentLoader.loadFileContent(folderLocation+srcFile);
		  		  
				  double codeReadabilityScoreL=raykernel.apps.readability.eval.Main.getReadability(contentCode);
				  
				  String [] fileparts = srcFile.split("\\.");
			      String filename = fileparts[0];
				  csvWriter.write(filename,codeReadabilityScoreL);
		    	  			
		      } 
		    }
	    csvWriter.close();
	    
	}
	

	public static void main(String[] args) throws IOException {

		CodeReadability.collectSampleReadability();
		System.out.println("Code Readability Calculation Finished!!!!");
	}

}
