package readability;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvListReader;
import org.supercsv.io.CsvListWriter;
import org.supercsv.io.ICsvListReader;
import org.supercsv.io.ICsvListWriter;
import org.supercsv.prefs.CsvPreference;


//import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;

import utility.ContentLoader;


public class TextReadability{

	public static void main(String[] args) throws IOException {
//		HighTextReadability hTR = new HighTextReadability();
//		hTR.start();
		
		JavaTextReadability jTR = new JavaTextReadability();
		CSharpTextReadability cTR = new CSharpTextReadability();
		JavascriptTextReadability jScTR = new JavascriptTextReadability();
		PyTextReadability pyTR = new PyTextReadability();
		jTR.start();
		cTR.start();
		jScTR.start();
		pyTR.start();
	}
}


//*************************** Java Text Readability ************************************//

class JavaTextReadability extends Thread{

	
	public void run()
	{
		ICsvListWriter csvWriter = null;
		 
		
		//For Input File Location
		String folderLocation = "E:/Projects/SOContentQualityResources/DataStore/Java/TextReadability/Text_Score_LT_0/";

		// For output file
		try {
			csvWriter = new CsvListWriter(new FileWriter("E:/Projects/SOContentQualityResources/FeatureValues/NewTextReadabilityAnalysis/Java_TextOnly_TextReadability_Score_LT_0.csv"),CsvPreference.STANDARD_PREFERENCE);
			csvWriter.write("id","GF","FK","ARI","SMOG","FR","CL");
	
		
		File folder = new File(folderLocation);
		File[] listOfFiles = folder.listFiles();
		ReadabilityEndpoint findReadabilityScore = new ReadabilityEndpoint();
		
		List<Float> gunningFog = new ArrayList<>();
		List<Float> fleschKincaid = new ArrayList<>();
		List<Float> ari = new ArrayList<>();
		List<Float> smog = new ArrayList<>();
		List<Float> fleschReading = new ArrayList<>();
		List<Float> colemanLiau = new ArrayList<>();
		List<Float> total = new ArrayList<Float>();
//		float maxGF = 0.0f, minGF = 1000.0f, maxFK = 0.0f, minFK = 1000.0f,maxARI = 0.0f, minARI = 1000.0f,maxS = 0.0f, minS = 1000.0f,maxFR = 0.0f, minFR = 1000.0f,maxCL = 0.0f, minCL = 1000.0f;
		float GF = 0.0f, FK = 0.0f, ARI = 0.0f, S = 0.0f,FR = 0.0f,CL = 0.0f;
		
		System.out.println("CS Text Readability Start!!!");
		
		    for (int i = 0; i <listOfFiles.length; i++) {
		      if (listOfFiles[i].isFile()) {
		    	  String srcFile=listOfFiles[i].getName();
		    	  String [] fileparts = srcFile.split("\\.");
		    	  String filename = fileparts[0];
		  		  String contentText=ContentLoader.loadFileContent(folderLocation+srcFile);
		  		  
					Map<MetricType, BigDecimal> result= findReadabilityScore.get(contentText);
					
					for (Map.Entry<MetricType, BigDecimal> entry : result.entrySet()) {
						
						if(entry.getKey().equals(MetricType.SMOG)){
							smog.add(entry.getValue().floatValue());
							S=entry.getValue().floatValue();
//							if(maxS<entry.getValue().floatValue()) maxS = entry.getValue().floatValue();
//							if(minS> entry.getValue().floatValue()) minS = entry.getValue().floatValue();
						}
						else if(entry.getKey().equals(MetricType.ARI)) {
							ari.add(entry.getValue().floatValue());
							ARI = entry.getValue().floatValue();
//							if(maxARI<entry.getValue().floatValue()) maxARI = entry.getValue().floatValue();
//							if(minARI> entry.getValue().floatValue()) minARI = entry.getValue().floatValue();
						}
						else if(entry.getKey().equals(MetricType.COLEMAN_LIAU)) {
							colemanLiau.add(entry.getValue().floatValue());
							CL=entry.getValue().floatValue();
//							if(maxCL<entry.getValue().floatValue()) maxCL = entry.getValue().floatValue();
//							if(minCL> entry.getValue().floatValue()) minCL = entry.getValue().floatValue();
						}
						else if(entry.getKey().equals(MetricType.FLESCH_KINCAID)) {
							fleschKincaid.add(entry.getValue().floatValue());
							FK=entry.getValue().floatValue();
//							if(maxFK<entry.getValue().floatValue()) maxFK = entry.getValue().floatValue();
//							if(minFK> entry.getValue().floatValue()) minFK = entry.getValue().floatValue();
						}
						else if(entry.getKey().equals(MetricType.FLESCH_READING)) {
							fleschReading.add(entry.getValue().floatValue());
							FR=entry.getValue().floatValue();
//							if(maxFR<entry.getValue().floatValue()) maxFR = entry.getValue().floatValue();
//							if(minFR> entry.getValue().floatValue()) minFR = entry.getValue().floatValue();
						}
						else if(entry.getKey().equals(MetricType.GUNNING_FOG)){
							gunningFog.add(entry.getValue().floatValue());
							GF=entry.getValue().floatValue();
//							if(maxGF<entry.getValue().floatValue()) maxGF = entry.getValue().floatValue();
//							if(minGF> entry.getValue().floatValue()) minGF = entry.getValue().floatValue();
						}
							//System.out.println(entry.getKey()+" "+entry.getValue().floatValue();
		            }
//					if(filename.equals("1")) {
//						System.out.println(FR);
//					}
					csvWriter.write(filename, GF, FK, ARI, S, FR, CL);
					
				}
		    }
		    
		    //System.out.println(maxS+" "+minS+" "+ maxARI+" "+minARI+" "+maxCL+" "+minCL+" "+maxFK+" "+minFK+" "+maxGF+" "+minGF);
		    
//		    float MAX = -1000.0f, MIN = 1000.0f;
//		    for (int k = 0; k<listOfFiles.length; k++) {
//		    	total.add(smog.get(k).floatValue()
//		    			+ari.get(k).floatValue()
//		    			+colemanLiau.get(k).floatValue()
//		    			+gunningFog.get(k).floatValue()
//		    			+fleschKincaid.get(k).floatValue()
//		    			+fleschReading.get(k).floatValue());
//		    	
//		    	if(MAX<total.get(k).floatValue()) MAX = total.get(k).floatValue();
//				if(MIN> total.get(k).floatValue()) MIN = total.get(k).floatValue();
//		    }
//		    
//		    for (int j = 0; j<listOfFiles.length; j++) {
//		    	  float finalScore=0.0f;
////		    	  float avgScore = 0.0f;
//			      if (listOfFiles[j].isFile()) {
//			    	  String srcFile=listOfFiles[j].getName();
//			    	  String [] fileparts = srcFile.split("\\.");
//			    	  String filename = fileparts[0];
//			    	  
//			    	  finalScore = ((total.get(j).floatValue() - MIN)/(MAX-MIN))*100;
//			    	  
////			    	  totalScore = ((fleschReading.get(j).floatValue() - minFR)/(maxFR-minFR))*100;
////			    			       +((smog.get(j).floatValue() - minS)/(maxS-minS))*100
////			    			       +((ari.get(j).floatValue() - minARI)/(maxARI-minARI))*100
////			    			       +((colemanLiau.get(j).floatValue() - minCL)/(maxCL-minCL))*100
////			    			       +((gunningFog.get(j).floatValue() - minGF)/(maxGF-minGF))*100
////			    			       +((fleschKincaid.get(j).floatValue() - minFK)/(maxFK-minFK))*100;
//			    	  
//			    	  //avgScore= totalScore/6.0f;
//				      //System.out.println(avgScore);
//			    	  csvWriter.write(filename,finalScore);
//			      }
//		  	
//		      }
//
//		    
		    csvWriter.close();
		    System.out.println("Java Text Readability Calculated Successfully!!!");

		    
	}catch (Exception e) {
			// TODO: handle exception
	}
	

	}
}


//*************************** C_Sharp Text Readability ************************************//

class CSharpTextReadability extends Thread{

	
	public void run()
	{
		ICsvListWriter csvWriter = null;
		 
		
		//For Input File Location
		String folderLocation = "E:/Projects/SOContentQualityResources/DataStore/C#/TextReadability/Text_Score_LT_0/";

		// For output file
		try {
			csvWriter = new CsvListWriter(new FileWriter("E:/Projects/SOContentQualityResources/FeatureValues/NewTextReadabilityAnalysis/CS_TextOnly_TextReadability_Score_LT_0.csv"),CsvPreference.STANDARD_PREFERENCE);
			csvWriter.write("id","GF","FK","ARI","SMOG","FR","CL");
	
		
		File folder = new File(folderLocation);
		File[] listOfFiles = folder.listFiles();
		ReadabilityEndpoint findReadabilityScore = new ReadabilityEndpoint();
		
		List<Float> gunningFog = new ArrayList<>();
		List<Float> fleschKincaid = new ArrayList<>();
		List<Float> ari = new ArrayList<>();
		List<Float> smog = new ArrayList<>();
		List<Float> fleschReading = new ArrayList<>();
		List<Float> colemanLiau = new ArrayList<>();
		List<Float> total = new ArrayList<Float>();
//		float maxGF = 0.0f, minGF = 1000.0f, maxFK = 0.0f, minFK = 1000.0f,maxARI = 0.0f, minARI = 1000.0f,maxS = 0.0f, minS = 1000.0f,maxFR = 0.0f, minFR = 1000.0f,maxCL = 0.0f, minCL = 1000.0f;
		float GF = 0.0f, FK = 0.0f, ARI = 0.0f, S = 0.0f,FR = 0.0f,CL = 0.0f;
		
		System.out.println("CS Text Readability Start!!!");
		
		    for (int i = 0; i <listOfFiles.length; i++) {
		      if (listOfFiles[i].isFile()) {
		    	  String srcFile=listOfFiles[i].getName();
		    	  String [] fileparts = srcFile.split("\\.");
		    	  String filename = fileparts[0];
		  		  String contentText=ContentLoader.loadFileContent(folderLocation+srcFile);
		  		  
					Map<MetricType, BigDecimal> result= findReadabilityScore.get(contentText);
					
					for (Map.Entry<MetricType, BigDecimal> entry : result.entrySet()) {
						
						if(entry.getKey().equals(MetricType.SMOG)){
							smog.add(entry.getValue().floatValue());
							S=entry.getValue().floatValue();
//							if(maxS<entry.getValue().floatValue()) maxS = entry.getValue().floatValue();
//							if(minS> entry.getValue().floatValue()) minS = entry.getValue().floatValue();
						}
						else if(entry.getKey().equals(MetricType.ARI)) {
							ari.add(entry.getValue().floatValue());
							ARI = entry.getValue().floatValue();
//							if(maxARI<entry.getValue().floatValue()) maxARI = entry.getValue().floatValue();
//							if(minARI> entry.getValue().floatValue()) minARI = entry.getValue().floatValue();
						}
						else if(entry.getKey().equals(MetricType.COLEMAN_LIAU)) {
							colemanLiau.add(entry.getValue().floatValue());
							CL=entry.getValue().floatValue();
//							if(maxCL<entry.getValue().floatValue()) maxCL = entry.getValue().floatValue();
//							if(minCL> entry.getValue().floatValue()) minCL = entry.getValue().floatValue();
						}
						else if(entry.getKey().equals(MetricType.FLESCH_KINCAID)) {
							fleschKincaid.add(entry.getValue().floatValue());
							FK=entry.getValue().floatValue();
//							if(maxFK<entry.getValue().floatValue()) maxFK = entry.getValue().floatValue();
//							if(minFK> entry.getValue().floatValue()) minFK = entry.getValue().floatValue();
						}
						else if(entry.getKey().equals(MetricType.FLESCH_READING)) {
							fleschReading.add(entry.getValue().floatValue());
							FR=entry.getValue().floatValue();
//							if(maxFR<entry.getValue().floatValue()) maxFR = entry.getValue().floatValue();
//							if(minFR> entry.getValue().floatValue()) minFR = entry.getValue().floatValue();
						}
						else if(entry.getKey().equals(MetricType.GUNNING_FOG)){
							gunningFog.add(entry.getValue().floatValue());
							GF=entry.getValue().floatValue();
//							if(maxGF<entry.getValue().floatValue()) maxGF = entry.getValue().floatValue();
//							if(minGF> entry.getValue().floatValue()) minGF = entry.getValue().floatValue();
						}
							//System.out.println(entry.getKey()+" "+entry.getValue().floatValue();
		            }
					csvWriter.write(filename, GF, FK, ARI, S, FR, CL);
					
				}
		    }
		    
		    //System.out.println(maxS+" "+minS+" "+ maxARI+" "+minARI+" "+maxCL+" "+minCL+" "+maxFK+" "+minFK+" "+maxGF+" "+minGF);
		    
//		    float MAX = -1000.0f, MIN = 1000.0f;
//		    for (int k = 0; k<listOfFiles.length; k++) {
//		    	total.add(smog.get(k).floatValue()
//		    			+ari.get(k).floatValue()
//		    			+colemanLiau.get(k).floatValue()
//		    			+gunningFog.get(k).floatValue()
//		    			+fleschKincaid.get(k).floatValue()
//		    			+fleschReading.get(k).floatValue());
//		    	
//		    	if(MAX<total.get(k).floatValue()) MAX = total.get(k).floatValue();
//				if(MIN> total.get(k).floatValue()) MIN = total.get(k).floatValue();
//		    }
//		    
//		    for (int j = 0; j<listOfFiles.length; j++) {
//		    	  float finalScore=0.0f;
////		    	  float avgScore = 0.0f;
//			      if (listOfFiles[j].isFile()) {
//			    	  String srcFile=listOfFiles[j].getName();
//			    	  String [] fileparts = srcFile.split("\\.");
//			    	  String filename = fileparts[0];
//			    	  
//			    	  finalScore = ((total.get(j).floatValue() - MIN)/(MAX-MIN))*100;
//			    	  
////			    	  totalScore = ((fleschReading.get(j).floatValue() - minFR)/(maxFR-minFR))*100;
////			    			       +((smog.get(j).floatValue() - minS)/(maxS-minS))*100
////			    			       +((ari.get(j).floatValue() - minARI)/(maxARI-minARI))*100
////			    			       +((colemanLiau.get(j).floatValue() - minCL)/(maxCL-minCL))*100
////			    			       +((gunningFog.get(j).floatValue() - minGF)/(maxGF-minGF))*100
////			    			       +((fleschKincaid.get(j).floatValue() - minFK)/(maxFK-minFK))*100;
//			    	  
//			    	  //avgScore= totalScore/6.0f;
//				      //System.out.println(avgScore);
//			    	  csvWriter.write(filename,finalScore);
//			      }
//		  	
//		      }
//
//		    
		    csvWriter.close();
		    System.out.println("CS Text Readability Calculated Successfully!!!");

		    
	}catch (Exception e) {
			// TODO: handle exception
	}
	

	}
}

//*************************** JavaScript Text Readability ************************************//

class JavascriptTextReadability extends Thread{

	
	public void run()
	{
		ICsvListWriter csvWriter = null;
		 
		
		//For Input File Location
		String folderLocation = "E:/Projects/SOContentQualityResources/DataStore/JavaScript/TextReadability/Text_Score_LT_0/";

		// For output file
		try {
			csvWriter = new CsvListWriter(new FileWriter("E:/Projects/SOContentQualityResources/FeatureValues/NewTextReadabilityAnalysis/JS_TextOnly_TextReadability_Score_LT_0.csv"),CsvPreference.STANDARD_PREFERENCE);
			csvWriter.write("id","GF","FK","ARI","SMOG","FR","CL");
	
		
		File folder = new File(folderLocation);
		File[] listOfFiles = folder.listFiles();
		ReadabilityEndpoint findReadabilityScore = new ReadabilityEndpoint();
		
		List<Float> gunningFog = new ArrayList<>();
		List<Float> fleschKincaid = new ArrayList<>();
		List<Float> ari = new ArrayList<>();
		List<Float> smog = new ArrayList<>();
		List<Float> fleschReading = new ArrayList<>();
		List<Float> colemanLiau = new ArrayList<>();
		List<Float> total = new ArrayList<Float>();
//		float maxGF = 0.0f, minGF = 1000.0f, maxFK = 0.0f, minFK = 1000.0f,maxARI = 0.0f, minARI = 1000.0f,maxS = 0.0f, minS = 1000.0f,maxFR = 0.0f, minFR = 1000.0f,maxCL = 0.0f, minCL = 1000.0f;
		float GF = 0.0f, FK = 0.0f, ARI = 0.0f, S = 0.0f,FR = 0.0f,CL = 0.0f;
		
		System.out.println("CS Text Readability Start!!!");
		
		    for (int i = 0; i <listOfFiles.length; i++) {
		      if (listOfFiles[i].isFile()) {
		    	  String srcFile=listOfFiles[i].getName();
		    	  String [] fileparts = srcFile.split("\\.");
		    	  String filename = fileparts[0];
		  		  String contentText=ContentLoader.loadFileContent(folderLocation+srcFile);
		  		  
					Map<MetricType, BigDecimal> result= findReadabilityScore.get(contentText);
					
					for (Map.Entry<MetricType, BigDecimal> entry : result.entrySet()) {
						
						if(entry.getKey().equals(MetricType.SMOG)){
							smog.add(entry.getValue().floatValue());
							S=entry.getValue().floatValue();
//							if(maxS<entry.getValue().floatValue()) maxS = entry.getValue().floatValue();
//							if(minS> entry.getValue().floatValue()) minS = entry.getValue().floatValue();
						}
						else if(entry.getKey().equals(MetricType.ARI)) {
							ari.add(entry.getValue().floatValue());
							ARI = entry.getValue().floatValue();
//							if(maxARI<entry.getValue().floatValue()) maxARI = entry.getValue().floatValue();
//							if(minARI> entry.getValue().floatValue()) minARI = entry.getValue().floatValue();
						}
						else if(entry.getKey().equals(MetricType.COLEMAN_LIAU)) {
							colemanLiau.add(entry.getValue().floatValue());
							CL=entry.getValue().floatValue();
//							if(maxCL<entry.getValue().floatValue()) maxCL = entry.getValue().floatValue();
//							if(minCL> entry.getValue().floatValue()) minCL = entry.getValue().floatValue();
						}
						else if(entry.getKey().equals(MetricType.FLESCH_KINCAID)) {
							fleschKincaid.add(entry.getValue().floatValue());
							FK=entry.getValue().floatValue();
//							if(maxFK<entry.getValue().floatValue()) maxFK = entry.getValue().floatValue();
//							if(minFK> entry.getValue().floatValue()) minFK = entry.getValue().floatValue();
						}
						else if(entry.getKey().equals(MetricType.FLESCH_READING)) {
							fleschReading.add(entry.getValue().floatValue());
							FR=entry.getValue().floatValue();
//							if(maxFR<entry.getValue().floatValue()) maxFR = entry.getValue().floatValue();
//							if(minFR> entry.getValue().floatValue()) minFR = entry.getValue().floatValue();
						}
						else if(entry.getKey().equals(MetricType.GUNNING_FOG)){
							gunningFog.add(entry.getValue().floatValue());
							GF=entry.getValue().floatValue();
//							if(maxGF<entry.getValue().floatValue()) maxGF = entry.getValue().floatValue();
//							if(minGF> entry.getValue().floatValue()) minGF = entry.getValue().floatValue();
						}
							//System.out.println(entry.getKey()+" "+entry.getValue().floatValue();
		            }
					csvWriter.write(filename, GF, FK, ARI, S, FR, CL);
					
				}
		    }
		    
		    //System.out.println(maxS+" "+minS+" "+ maxARI+" "+minARI+" "+maxCL+" "+minCL+" "+maxFK+" "+minFK+" "+maxGF+" "+minGF);
		    
//		    float MAX = -1000.0f, MIN = 1000.0f;
//		    for (int k = 0; k<listOfFiles.length; k++) {
//		    	total.add(smog.get(k).floatValue()
//		    			+ari.get(k).floatValue()
//		    			+colemanLiau.get(k).floatValue()
//		    			+gunningFog.get(k).floatValue()
//		    			+fleschKincaid.get(k).floatValue()
//		    			+fleschReading.get(k).floatValue());
//		    	
//		    	if(MAX<total.get(k).floatValue()) MAX = total.get(k).floatValue();
//				if(MIN> total.get(k).floatValue()) MIN = total.get(k).floatValue();
//		    }
//		    
//		    for (int j = 0; j<listOfFiles.length; j++) {
//		    	  float finalScore=0.0f;
////		    	  float avgScore = 0.0f;
//			      if (listOfFiles[j].isFile()) {
//			    	  String srcFile=listOfFiles[j].getName();
//			    	  String [] fileparts = srcFile.split("\\.");
//			    	  String filename = fileparts[0];
//			    	  
//			    	  finalScore = ((total.get(j).floatValue() - MIN)/(MAX-MIN))*100;
//			    	  
////			    	  totalScore = ((fleschReading.get(j).floatValue() - minFR)/(maxFR-minFR))*100;
////			    			       +((smog.get(j).floatValue() - minS)/(maxS-minS))*100
////			    			       +((ari.get(j).floatValue() - minARI)/(maxARI-minARI))*100
////			    			       +((colemanLiau.get(j).floatValue() - minCL)/(maxCL-minCL))*100
////			    			       +((gunningFog.get(j).floatValue() - minGF)/(maxGF-minGF))*100
////			    			       +((fleschKincaid.get(j).floatValue() - minFK)/(maxFK-minFK))*100;
//			    	  
//			    	  //avgScore= totalScore/6.0f;
//				      //System.out.println(avgScore);
//			    	  csvWriter.write(filename,finalScore);
//			      }
//		  	
//		      }
//
//		    
		    csvWriter.close();
		    System.out.println("JS Text Readability Calculated Successfully!!!");

		    
	}catch (Exception e) {
			// TODO: handle exception
	}
	

	}
}

//*************************** Python Text Readability ************************************//

class PyTextReadability extends Thread{

	
	public void run()
	{
		ICsvListWriter csvWriter = null;
		 
		
		//For Input File Location
		String folderLocation = "E:/Projects/SOContentQualityResources/DataStore/Python/TextReadability/Text_Score_LT_0/";

		// For output file
		try {
			csvWriter = new CsvListWriter(new FileWriter("E:/Projects/SOContentQualityResources/FeatureValues/NewTextReadabilityAnalysis/Python_TextOnly_TextReadability_Score_LT_0.csv"),CsvPreference.STANDARD_PREFERENCE);
			csvWriter.write("id","GF","FK","ARI","SMOG","FR","CL");
	
		
		File folder = new File(folderLocation);
		File[] listOfFiles = folder.listFiles();
		ReadabilityEndpoint findReadabilityScore = new ReadabilityEndpoint();
		
		List<Float> gunningFog = new ArrayList<>();
		List<Float> fleschKincaid = new ArrayList<>();
		List<Float> ari = new ArrayList<>();
		List<Float> smog = new ArrayList<>();
		List<Float> fleschReading = new ArrayList<>();
		List<Float> colemanLiau = new ArrayList<>();
		List<Float> total = new ArrayList<Float>();
//		float maxGF = 0.0f, minGF = 1000.0f, maxFK = 0.0f, minFK = 1000.0f,maxARI = 0.0f, minARI = 1000.0f,maxS = 0.0f, minS = 1000.0f,maxFR = 0.0f, minFR = 1000.0f,maxCL = 0.0f, minCL = 1000.0f;
		float GF = 0.0f, FK = 0.0f, ARI = 0.0f, S = 0.0f,FR = 0.0f,CL = 0.0f;
		
		System.out.println("CS Text Readability Start!!!");
		
		    for (int i = 0; i <listOfFiles.length; i++) {
		      if (listOfFiles[i].isFile()) {
		    	  String srcFile=listOfFiles[i].getName();
		    	  String [] fileparts = srcFile.split("\\.");
		    	  String filename = fileparts[0];
		  		  String contentText=ContentLoader.loadFileContent(folderLocation+srcFile);
		  		  
					Map<MetricType, BigDecimal> result= findReadabilityScore.get(contentText);
					
					for (Map.Entry<MetricType, BigDecimal> entry : result.entrySet()) {
						
						if(entry.getKey().equals(MetricType.SMOG)){
							smog.add(entry.getValue().floatValue());
							S=entry.getValue().floatValue();
//							if(maxS<entry.getValue().floatValue()) maxS = entry.getValue().floatValue();
//							if(minS> entry.getValue().floatValue()) minS = entry.getValue().floatValue();
						}
						else if(entry.getKey().equals(MetricType.ARI)) {
							ari.add(entry.getValue().floatValue());
							ARI = entry.getValue().floatValue();
//							if(maxARI<entry.getValue().floatValue()) maxARI = entry.getValue().floatValue();
//							if(minARI> entry.getValue().floatValue()) minARI = entry.getValue().floatValue();
						}
						else if(entry.getKey().equals(MetricType.COLEMAN_LIAU)) {
							colemanLiau.add(entry.getValue().floatValue());
							CL=entry.getValue().floatValue();
//							if(maxCL<entry.getValue().floatValue()) maxCL = entry.getValue().floatValue();
//							if(minCL> entry.getValue().floatValue()) minCL = entry.getValue().floatValue();
						}
						else if(entry.getKey().equals(MetricType.FLESCH_KINCAID)) {
							fleschKincaid.add(entry.getValue().floatValue());
							FK=entry.getValue().floatValue();
//							if(maxFK<entry.getValue().floatValue()) maxFK = entry.getValue().floatValue();
//							if(minFK> entry.getValue().floatValue()) minFK = entry.getValue().floatValue();
						}
						else if(entry.getKey().equals(MetricType.FLESCH_READING)) {
							fleschReading.add(entry.getValue().floatValue());
							FR=entry.getValue().floatValue();
//							if(maxFR<entry.getValue().floatValue()) maxFR = entry.getValue().floatValue();
//							if(minFR> entry.getValue().floatValue()) minFR = entry.getValue().floatValue();
						}
						else if(entry.getKey().equals(MetricType.GUNNING_FOG)){
							gunningFog.add(entry.getValue().floatValue());
							GF=entry.getValue().floatValue();
//							if(maxGF<entry.getValue().floatValue()) maxGF = entry.getValue().floatValue();
//							if(minGF> entry.getValue().floatValue()) minGF = entry.getValue().floatValue();
						}
							//System.out.println(entry.getKey()+" "+entry.getValue().floatValue();
		            }
					csvWriter.write(filename, GF, FK, ARI, S, FR, CL);
					
				}
		    }
		    
		    //System.out.println(maxS+" "+minS+" "+ maxARI+" "+minARI+" "+maxCL+" "+minCL+" "+maxFK+" "+minFK+" "+maxGF+" "+minGF);
		    
//		    float MAX = -1000.0f, MIN = 1000.0f;
//		    for (int k = 0; k<listOfFiles.length; k++) {
//		    	total.add(smog.get(k).floatValue()
//		    			+ari.get(k).floatValue()
//		    			+colemanLiau.get(k).floatValue()
//		    			+gunningFog.get(k).floatValue()
//		    			+fleschKincaid.get(k).floatValue()
//		    			+fleschReading.get(k).floatValue());
//		    	
//		    	if(MAX<total.get(k).floatValue()) MAX = total.get(k).floatValue();
//				if(MIN> total.get(k).floatValue()) MIN = total.get(k).floatValue();
//		    }
//		    
//		    for (int j = 0; j<listOfFiles.length; j++) {
//		    	  float finalScore=0.0f;
////		    	  float avgScore = 0.0f;
//			      if (listOfFiles[j].isFile()) {
//			    	  String srcFile=listOfFiles[j].getName();
//			    	  String [] fileparts = srcFile.split("\\.");
//			    	  String filename = fileparts[0];
//			    	  
//			    	  finalScore = ((total.get(j).floatValue() - MIN)/(MAX-MIN))*100;
//			    	  
////			    	  totalScore = ((fleschReading.get(j).floatValue() - minFR)/(maxFR-minFR))*100;
////			    			       +((smog.get(j).floatValue() - minS)/(maxS-minS))*100
////			    			       +((ari.get(j).floatValue() - minARI)/(maxARI-minARI))*100
////			    			       +((colemanLiau.get(j).floatValue() - minCL)/(maxCL-minCL))*100
////			    			       +((gunningFog.get(j).floatValue() - minGF)/(maxGF-minGF))*100
////			    			       +((fleschKincaid.get(j).floatValue() - minFK)/(maxFK-minFK))*100;
//			    	  
//			    	  //avgScore= totalScore/6.0f;
//				      //System.out.println(avgScore);
//			    	  csvWriter.write(filename,finalScore);
//			      }
//		  	
//		      }
//
//		    
		    csvWriter.close();
		    System.out.println("Python Text Readability Calculated Successfully!!!");

		    
	}catch (Exception e) {
			// TODO: handle exception
	}
	

	}
}


//class HighTextReadability extends Thread{
//
//	
//	@Override
//	public void run() {
//		
//		ICsvListReader questionReader = null;
//    	ICsvListWriter csvWriter = null;
//    	
//    	int count =0;
//    
//	    try {
//	    	
//	    	String dataLocation = "E:/Projects/SOContentQualityResources/CSVData/LowTextOnly.csv";
//	    	String outFile = "E:/Projects/SOContentQualityResources/FeatureData/low_text_readability.csv";
//	    	
//	    	//Reading CSV File
//	    	
//	    	questionReader = new CsvListReader(new FileReader(dataLocation), CsvPreference.STANDARD_PREFERENCE);  
//	    	questionReader.getHeader(true); // skip the header (can't be used with CsvListReader)
//	        final CellProcessor[] processors = getProcessors();               
//	        List<Object> questionList;
//	        
//	        //Write CSV
//	        csvWriter = new CsvListWriter (new FileWriter(outFile),CsvPreference.STANDARD_PREFERENCE);
//	        csvWriter.write("Id","Score","Readability","Category");
//	        
//	        ReadabilityEndpoint findReadabilityScore = new ReadabilityEndpoint();
//	        
//	        while( (questionList = questionReader.read(processors)) != null ) {
//	        	 
//	           	String id="";
//	           	String score ="";
//	        	String title="";                  		
//	        	String text="";                    
//	        	String category="L";
//	        	
//	        	try {
//	        		id=questionList.get(0).toString();
//				}catch (Exception e) {
//	
//				}
//	         	try {
//	        		score=questionList.get(1).toString();
//				}catch (Exception e) {
//	
//				}
//	        	try {
//	        		title=questionList.get(2).toString();
//	        		
//				}catch (Exception e) {
//	
//				}
//	        	try {
//	        		text=questionList.get(3).toString();
//	        		
//				}catch (Exception e) {
//	
//				}
//	        	
//				Map<MetricType, BigDecimal> result= findReadabilityScore.get(title+"\n"+text);
//
//				float totalScore=0.0f, avgScore=0.0f;
//				for (Map.Entry<MetricType, BigDecimal> entry : result.entrySet()) {
//					
//					if(entry.getKey().equals(MetricType.SMOG) || entry.getKey().equals(MetricType.ARI)||entry.getKey().equals(MetricType.COLEMAN_LIAU)||entry.getKey().equals(MetricType.FLESCH_KINCAID)||entry.getKey().equals(MetricType.FLESCH_READING)||entry.getKey().equals(MetricType.GUNNING_FOG)) {
//						totalScore+= entry.getValue().floatValue();
//		            }	    
//				}
//				//System.out.println(totalScore);
//				avgScore= (float) (totalScore/6.0);
//			    count++;
//	        	csvWriter.write(id, score, avgScore, category);
//		    }
//	        
//	        csvWriter.close();
//	        System.out.println("Data Write in File Finished Successfully!!"+"Count:"+count);
//        
//	    } catch (IOException e) {
//			e.printStackTrace();
//		}
//	    finally {
//	    	
//	        if( questionReader != null ) {
//	                try {
//	                	questionReader.close();
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//	        }
//	    }
//}
//	
//	private static CellProcessor[] getProcessors() {
//	         
//		final CellProcessor[] processors = new CellProcessor[] {
//				new Optional(), // post Id not null
//		        new Optional(),
//		        new Optional(),
//		        new Optional(),
//		        new Optional()
//		};
//		
//		return processors;
//	}
//	
//}


