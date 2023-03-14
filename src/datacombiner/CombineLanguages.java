
package datacombiner;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;


import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvListReader;
import org.supercsv.io.CsvListWriter;
import org.supercsv.io.ICsvListReader;
import org.supercsv.io.ICsvListWriter;
import org.supercsv.prefs.CsvPreference;

public class CombineLanguages {
	public static void main(String[] args) throws Exception {
		Combiner obj = new Combiner();
		obj.combineResults();
	}

}

class Combiner {
	
public void combineResults() throws Exception {
	

	ICsvListReader listReader = null;
	ICsvListWriter csvWriter = null;
	
	final int NO_OF_FILES = 4;
    
    try {
    	
//    	double MAX = 26290;
//    	double MIN = 0;
    	
        csvWriter = new CsvListWriter(new FileWriter("E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/CombinedLanguages/TextLength/TextLength_Score_LT_0_.csv"),CsvPreference.STANDARD_PREFERENCE);
        
		csvWriter.write("Id","Length");
		
		for(int i = 1; i<=NO_OF_FILES; i++){
		
			listReader = new CsvListReader(new FileReader("E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/CombinedLanguages/TextLength/L"+i+".csv"), CsvPreference.STANDARD_PREFERENCE);
	        listReader.getHeader(true); // skip the header (can't be used with CsvListReader)
	        final CellProcessor[] processors = getProcessors();               
	        List<Object> questionList;
	        
	        while( (questionList = listReader.read(processors)) != null ) {
	        	 
	        	String id="";
	        	String GF="";                          		    // int
//	            String FK="";                  		    // float
//	            String ARI="";
//	            String SMOG="";
//	            String FR="";
//	            String CL="";
	        	
	           	try {
	        		id=questionList.get(0).toString();
				}catch (Exception e) {
					// TODO: handle exception
				}
	        	try {
	        		GF=questionList.get(1).toString();
				}catch (Exception e) {
					// TODO: handle exception
				}
//	        	try {
//	        		FK=questionList.get(2).toString();
//				}catch (Exception e) {
//
//				}
//	        	try {
//	        		ARI=questionList.get(3).toString();
//				}catch (Exception e) {
//
//				}
//	        	try {
//	        		SMOG=questionList.get(4).toString();
//				}catch (Exception e) {
//
//				}
//	        	try {
//	        		FR=questionList.get(5).toString();
//				}catch (Exception e) {
//
//				}
//	        	try {
//	        		CL=questionList.get(6).toString();
//				}catch (Exception e) {
//
//				}
//	        	double temp = Double.parseDouble(FR);
//	        	if (temp < 0.0) temp = 0.0;
//	        	double score = ((temp- MIN)/(MAX - MIN))*100.0;
	        	csvWriter.write(id,GF);
	        }
		}
                
        csvWriter.close();
        System.out.println("Data Write in File Finished Successfully!!");
       
    }
    finally {
    	
        if( listReader != null ) {
                listReader.close();
        }
    }
}

private static CellProcessor[] getProcessors() {
	         
    final CellProcessor[] processors = new CellProcessor[] {
    		new Optional(), // post Id not null
//    		new Optional(),
//    		new Optional(),
//    		new Optional(),
//    		new Optional(),
//    		new Optional(),
    		new Optional()
            
    };
    
    return processors;
}

}