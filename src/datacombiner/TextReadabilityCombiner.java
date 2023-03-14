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

public class TextReadabilityCombiner {
	public static void main(String[] args) throws Exception {
		Normalizer obj = new Normalizer();
		obj.combineResults();
	}

}

class Normalizer {
	
public void combineResults() throws Exception {
	

	ICsvListReader listReader = null;
	ICsvListWriter csvWriter = null;
	
	final int NO_OF_FILES = 1;
    
    try {
    	
//    	double MAX = 26594;
//    	double MIN = 0;
    	
        csvWriter = new CsvListWriter(new FileWriter("E:/Projects/SORejectedEdits/DataStore/Edits_2019_1_.csv"),CsvPreference.STANDARD_PREFERENCE);
        
        csvWriter.write("Id","PostId","RevisionGUID","CreationDate","UserId");
		
		for(int i = 1; i<=NO_OF_FILES; i++){
		
			listReader = new CsvListReader(new FileReader("E:/Projects/SORejectedEdits/DataStore/Edits_2019_1.csv"), CsvPreference.STANDARD_PREFERENCE);
	        listReader.getHeader(true); // skip the header (can't be used with CsvListReader)
	        final CellProcessor[] processors = getProcessors();              
	        List<Object> questionList;
	        
	        while( (questionList = listReader.read(processors)) != null ) {
	        	 
	        	String id="";
	        	String GF="";                          		    // int
	            String FK="";                  		    // float
	            String ARI="";
	            String SMOG="";
//	            String FR="";
//	            String CL="";
	        	
	           	try {
	        		id=questionList.get(0).toString();
	        		System.out.println(id);
				}catch (Exception e) {
					// TODO: handle exception
				}
	        	try {
	        		GF=questionList.get(1).toString();
				}catch (Exception e) {
					// TODO: handle exception
				}
	        	try {
	        		FK=questionList.get(2).toString();
				}catch (Exception e) {

				}
	        	try {
	        		ARI=questionList.get(3).toString();
				}catch (Exception e) {

				}
	        	try {
	        		SMOG=questionList.get(4).toString();
				}catch (Exception e) {

				}
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
//	        	double temp = Double.parseDouble(GF);
//	        	if (temp < 0.0) temp = 0.0;
//	        	double score = ((temp- MIN)/(MAX - MIN));
//	        	System.out.println(id+""+GF+""+FK+""+ARI+""+SMOG);
//	        	if(Integer.parseInt(id)<=179291763) {
	        	csvWriter.write(id,GF,FK,ARI,SMOG);
//	        	}
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
    		new Optional(),
    		new Optional(),
    		new Optional(),
//    		new Optional(),
//    		new Optional(),
    		new Optional()
            
    };
    
    return processors;
}

}