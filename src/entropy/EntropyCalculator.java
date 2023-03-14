package entropy;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.supercsv.io.CsvListWriter;
import org.supercsv.io.ICsvListWriter;
import org.supercsv.prefs.CsvPreference;

public class EntropyCalculator {
	public static void main(String[] args) {
		JavaEntropyCalculator jEntropy = new JavaEntropyCalculator();
		CSharpEntropyCalculator cEntropy = new CSharpEntropyCalculator();
		JavascriptEntropyCalculator jSEntropy = new JavascriptEntropyCalculator();
		PyEntropyCalculator pyEntropy = new PyEntropyCalculator();
		
		jEntropy.start();
		cEntropy.start();
		jSEntropy.start();
		pyEntropy.start();
	}
}

//*******************  Java  *******************************//
 
class JavaEntropyCalculator extends Thread{
	
	
	public void run(){
		ICsvListWriter csvWriterH = null;
		ICsvListWriter csvWriterL = null;
		
		// Alpha value calculation
		String alphaFileLocation = "C:/mallet/output/java_keys.txt";
		String probabilityFileLocation = "C:/mallet/output/java_composition.txt";
		List<Double> alpha = new ArrayList<>();
		String[] splited, fileNameType;
		int i=0;
		try {
			System.out.println("Java Entropy Calculation Start!!");
			csvWriterH = new CsvListWriter(new FileWriter("E:/Projects/SOContentQualityResources/CSVData/Java/MetricResults/TopicEntropy/Topic_Entropy_Score_GT_0.csv"),CsvPreference.STANDARD_PREFERENCE);
			csvWriterL = new CsvListWriter(new FileWriter("E:/Projects/SOContentQualityResources/CSVData/Java/MetricResults/TopicEntropy/Topic_Entropy_Score_LT_0.csv"),CsvPreference.STANDARD_PREFERENCE);
			String colOneTitle = "Id";
			String colTwoTtile = "Entropy";
			csvWriterH.write(colOneTitle, colTwoTtile);
			csvWriterL.write(colOneTitle, colTwoTtile);
			FileReader readFile = new FileReader(alphaFileLocation);
			Scanner myScanner = new Scanner(readFile);
			String temp;
			while(myScanner.hasNextLine()) {
				temp = myScanner.nextLine();
				splited=temp.split("\t");
				alpha.add(Double.parseDouble(splited[1].trim()));
//				System.out.println(alpha.get(i));
//				i++;
			}
			
			FileReader readDistFile = new FileReader(probabilityFileLocation);
			Scanner myDisctScanner = new Scanner(readDistFile);
//			double alphaValue = 0.05;
			while(myDisctScanner.hasNextLine()) {
				temp = myDisctScanner.nextLine();
				splited = temp.split("\t");
				fileNameType = splited[1].split("_");
				
				double entropy = 0;
				int index;
				double MAX=0.0;
				
				for (index= 2; index<102; index++) {
					
					double px = alpha.get(index-2) * Double.parseDouble(splited[index].trim());
					if(MAX<Double.parseDouble(splited[index].trim())) MAX = Double.parseDouble(splited[index].trim());
					double logpx = -1 * (Math.log(px)/Math.log(2));
					entropy += (px * logpx);
				}
				if(fileNameType[2].equalsIgnoreCase("1")) {
					csvWriterH.write(fileNameType[1], entropy);
				}
				else if(fileNameType[2].equalsIgnoreCase("2")) {
					csvWriterL.write(fileNameType[1], entropy);
				}
								
//				System.out.println(fileNameType[1]+">>"+fileNameType[2]+">> "+entropy +" "+MAX);
			}
			System.out.println("Java Entropy Calculation Finished!!");
			myScanner.close();
			myDisctScanner.close();
			csvWriterH.close();
			csvWriterL.close();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
		
	
}

//*******************  C#  *******************************//

class CSharpEntropyCalculator extends Thread{
	
	
	public void run(){
		ICsvListWriter csvWriterH = null;
		ICsvListWriter csvWriterL = null;
		
		// Alpha value calculation
		String alphaFileLocation = "C:/mallet/output/csharp_keys.txt";
		String probabilityFileLocation = "C:/mallet/output/csharp_composition.txt";
		List<Double> alpha = new ArrayList<>();
		String[] splited, fileNameType;
		int i=0;
		try {
			System.out.println("C# Entropy Calculation Start!!");
			csvWriterH = new CsvListWriter(new FileWriter("E:/Projects/SOContentQualityResources/CSVData/C_Sharp/MetricResults/TopicEntropy/Topic_Entropy_Score_GT_0.csv"),CsvPreference.STANDARD_PREFERENCE);
			csvWriterL = new CsvListWriter(new FileWriter("E:/Projects/SOContentQualityResources/CSVData/C_Sharp/MetricResults/TopicEntropy/Topic_Entropy_Score_LT_0.csv"),CsvPreference.STANDARD_PREFERENCE);
			String colOneTitle = "Id";
			String colTwoTtile = "Entropy";
			csvWriterH.write(colOneTitle, colTwoTtile);
			csvWriterL.write(colOneTitle, colTwoTtile);
			FileReader readFile = new FileReader(alphaFileLocation);
			Scanner myScanner = new Scanner(readFile);
			String temp;
			while(myScanner.hasNextLine()) {
				temp = myScanner.nextLine();
				splited=temp.split("\t");
				alpha.add(Double.parseDouble(splited[1].trim()));
//				System.out.println(alpha.get(i));
//				i++;
			}
			
			FileReader readDistFile = new FileReader(probabilityFileLocation);
			Scanner myDisctScanner = new Scanner(readDistFile);
//			double alphaValue = 0.05;
			while(myDisctScanner.hasNextLine()) {
				temp = myDisctScanner.nextLine();
				splited = temp.split("\t");
				fileNameType = splited[1].split("_");
				
				double entropy = 0;
				int index;
				double MAX=0.0;
				
				for (index= 2; index<102; index++) {
					
					double px = alpha.get(index-2) * Double.parseDouble(splited[index].trim());
					if(MAX<Double.parseDouble(splited[index].trim())) MAX = Double.parseDouble(splited[index].trim());
					double logpx = -1 * (Math.log(px)/Math.log(2));
					entropy += (px * logpx);
				}
				if(fileNameType[2].equalsIgnoreCase("1")) {
					csvWriterH.write(fileNameType[1], entropy);
				}
				else if(fileNameType[2].equalsIgnoreCase("2")) {
					csvWriterL.write(fileNameType[1], entropy);
				}
								
//				System.out.println(fileNameType[1]+">>"+fileNameType[2]+">> "+entropy +" "+MAX);
			}
			System.out.println("C# Entropy Calculation Finished!!");
			myScanner.close();
			myDisctScanner.close();
			csvWriterH.close();
			csvWriterL.close();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
		
	
}

//*******************  JavaScript  *******************************//

class JavascriptEntropyCalculator extends Thread{
	
	
	public void run(){
		ICsvListWriter csvWriterH = null;
		ICsvListWriter csvWriterL = null;
		
		// Alpha value calculation
		String alphaFileLocation = "C:/mallet/output/javascript_keys.txt";
		String probabilityFileLocation = "C:/mallet/output/javascript_composition.txt";
		List<Double> alpha = new ArrayList<>();
		String[] splited, fileNameType;
		int i=0;
		try {
			System.out.println("Javascript Entropy Calculation Start!!");
			csvWriterH = new CsvListWriter(new FileWriter("E:/Projects/SOContentQualityResources/CSVData/Javascript/MetricResults/TopicEntropy/Topic_Entropy_Score_GT_0.csv"),CsvPreference.STANDARD_PREFERENCE);
			csvWriterL = new CsvListWriter(new FileWriter("E:/Projects/SOContentQualityResources/CSVData/Javascript/MetricResults/TopicEntropy/Topic_Entropy_Score_LT_0.csv"),CsvPreference.STANDARD_PREFERENCE);
			String colOneTitle = "Id";
			String colTwoTtile = "Entropy";
			csvWriterH.write(colOneTitle, colTwoTtile);
			csvWriterL.write(colOneTitle, colTwoTtile);
			FileReader readFile = new FileReader(alphaFileLocation);
			Scanner myScanner = new Scanner(readFile);
			String temp;
			while(myScanner.hasNextLine()) {
				temp = myScanner.nextLine();
				splited=temp.split("\t");
				alpha.add(Double.parseDouble(splited[1].trim()));
//				System.out.println(alpha.get(i));
//				i++;
			}
			
			FileReader readDistFile = new FileReader(probabilityFileLocation);
			Scanner myDisctScanner = new Scanner(readDistFile);
//			double alphaValue = 0.05;
			while(myDisctScanner.hasNextLine()) {
				temp = myDisctScanner.nextLine();
				splited = temp.split("\t");
				fileNameType = splited[1].split("_");
				
				double entropy = 0;
				int index;
				double MAX=0.0;
				
				for (index= 2; index<102; index++) {
					
					double px = alpha.get(index-2) * Double.parseDouble(splited[index].trim());
					if(MAX<Double.parseDouble(splited[index].trim())) MAX = Double.parseDouble(splited[index].trim());
					double logpx = -1 * (Math.log(px)/Math.log(2));
					entropy += (px * logpx);
				}
				if(fileNameType[2].equalsIgnoreCase("1")) {
					csvWriterH.write(fileNameType[1], entropy);
				}
				else if(fileNameType[2].equalsIgnoreCase("2")) {
					csvWriterL.write(fileNameType[1], entropy);
				}
								
//				System.out.println(fileNameType[1]+">>"+fileNameType[2]+">> "+entropy +" "+MAX);
			}
			System.out.println("Javascript Entropy Calculation Finished!!");
			myScanner.close();
			myDisctScanner.close();
			csvWriterH.close();
			csvWriterL.close();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
		
	
}

//*******************  Python  *******************************//

class PyEntropyCalculator extends Thread{
	
	
	public void run(){
		ICsvListWriter csvWriterH = null;
		ICsvListWriter csvWriterL = null;
		
		// Alpha value calculation
		String alphaFileLocation = "C:/mallet/output/python_keys.txt";
		String probabilityFileLocation = "C:/mallet/output/python_composition.txt";
		List<Double> alpha = new ArrayList<>();
		String[] splited, fileNameType;
		int i=0;
		try {
			System.out.println("Python Entropy Calculation Start!!");
			csvWriterH = new CsvListWriter(new FileWriter("E:/Projects/SOContentQualityResources/CSVData/Python/MetricResults/TopicEntropy/Topic_Entropy_Score_GT_0.csv"),CsvPreference.STANDARD_PREFERENCE);
			csvWriterL = new CsvListWriter(new FileWriter("E:/Projects/SOContentQualityResources/CSVData/Python/MetricResults/TopicEntropy/Topic_Entropy_Score_LT_0.csv"),CsvPreference.STANDARD_PREFERENCE);
			String colOneTitle = "Id";
			String colTwoTtile = "Entropy";
			csvWriterH.write(colOneTitle, colTwoTtile);
			csvWriterL.write(colOneTitle, colTwoTtile);
			FileReader readFile = new FileReader(alphaFileLocation);
			Scanner myScanner = new Scanner(readFile);
			String temp;
			while(myScanner.hasNextLine()) {
				temp = myScanner.nextLine();
				splited=temp.split("\t");
				alpha.add(Double.parseDouble(splited[1].trim()));
//				System.out.println(alpha.get(i));
//				i++;
			}
			
			FileReader readDistFile = new FileReader(probabilityFileLocation);
			Scanner myDisctScanner = new Scanner(readDistFile);
//			double alphaValue = 0.05;
			while(myDisctScanner.hasNextLine()) {
				temp = myDisctScanner.nextLine();
				splited = temp.split("\t");
				fileNameType = splited[1].split("_");
				
				double entropy = 0;
				int index;
				double MAX=0.0;
				
				for (index= 2; index<102; index++) {
					
					double px = alpha.get(index-2) * Double.parseDouble(splited[index].trim());
					if(MAX<Double.parseDouble(splited[index].trim())) MAX = Double.parseDouble(splited[index].trim());
					double logpx = -1 * (Math.log(px)/Math.log(2));
					entropy += (px * logpx);
				}
				if(fileNameType[2].equalsIgnoreCase("1")) {
					csvWriterH.write(fileNameType[1], entropy);
				}
				else if(fileNameType[2].equalsIgnoreCase("2")) {
					csvWriterL.write(fileNameType[1], entropy);
				}
								
//				System.out.println(fileNameType[1]+">>"+fileNameType[2]+">> "+entropy +" "+MAX);
			}
			System.out.println("Python Entropy Calculation Finished!!");
			myScanner.close();
			myDisctScanner.close();
			csvWriterH.close();
			csvWriterL.close();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
		
	
}