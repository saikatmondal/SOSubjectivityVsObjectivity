package entropy;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvListReader;
import org.supercsv.io.CsvListWriter;
import org.supercsv.io.ICsvListReader;
import org.supercsv.io.ICsvListWriter;
import org.supercsv.prefs.CsvPreference;


public class TagBasedTopicEntropy {
	public static void main(String[] args) {
		CalculateTagEntropy obj = new CalculateTagEntropy();
		obj.calculateEntropy();
	}

}

class CalculateTagEntropy{

	public void calculateEntropy() {
		
		ICsvListReader tagReader = null;
    	ICsvListWriter csvWriter = null;
    	
    	double totalFrequency = 51298323.0;
    	
    	int count =0;
    
	    try {
	    	
	    	String dataLocation = "E:/Projects/SOContentQualityResources/CSVData/Tags/Tags.csv";
	    	String outFile = "E:/Projects/SOContentQualityResources/CSVData/Tags/TagsEntropy2.csv";
	    	
	    	//Reading CSV File
	    	
	    	tagReader = new CsvListReader(new FileReader(dataLocation), CsvPreference.STANDARD_PREFERENCE);  
	    	tagReader.getHeader(true); // skip the header (can't be used with CsvListReader)
	        final CellProcessor[] processors = getProcessors();               
	        List<Object> tagList;
	        
	        //Write CSV
	        csvWriter = new CsvListWriter (new FileWriter(outFile),CsvPreference.STANDARD_PREFERENCE);
	        csvWriter.write("Id","TagName","Frequency","Entropy");
	        
	        
	        while( (tagList = tagReader.read(processors)) != null ) {
	        	 
	           	String id="";
	           	String name ="";
	        	String frequency="";
	        	
	        	double entropy = 0.0;
	        	double probability = 0.0;
	        	
	        	try {
	        		id = tagList.get(0).toString();
				}catch (Exception e) {
	
				}
	         	try {
	        		name = tagList.get(1).toString();
				}catch (Exception e) {
	
				}
	        	try {
	        		frequency = tagList.get(2).toString();
	        		probability = (Double.parseDouble(frequency)/totalFrequency);
//	        		entropy = -(probability*Math.log10(probability));
	        		entropy = Math.log10(probability);
	        		
				}catch (Exception e) {
	
				}
	        	
			    count++;
	        	csvWriter.write(id, name, frequency, entropy);
		    }
	        
	        csvWriter.close();
	        System.out.println("Data Write in File Finished Successfully!!"+"Count:"+count);
        
	    } catch (IOException e) {
			e.printStackTrace();
		}
	    finally {
	    	
	        if( tagReader != null ) {
	                try {
	                	tagReader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
	        }
	    }
}
	
	private static CellProcessor[] getProcessors() {
	         
		final CellProcessor[] processors = new CellProcessor[] {
				new Optional(), // post Id not null
		        new Optional(),
		        new Optional()
		};
		
		return processors;
	}
	
}