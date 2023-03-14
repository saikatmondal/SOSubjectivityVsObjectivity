package findstatistics;

import java.io.FileReader;

import java.util.List;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvListReader;
import org.supercsv.io.ICsvListReader;
import org.supercsv.prefs.CsvPreference;

public class StatFinder {
	public static void main(String[] args) throws Exception {
		FindStats obj = new FindStats();
		obj.statFinders();
	}

}


class FindStats {

	public void statFinders() throws Exception {
		
		ICsvListReader listReader = null;
	    
	    try {
	    	
	    	listReader = new CsvListReader(new FileReader("E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/JavaScript/APICalls_Score_GT_0.csv"), CsvPreference.STANDARD_PREFERENCE);
	
			
	        listReader.getHeader(true); // skip the header (can't be used with CsvListReader)
	        final CellProcessor[] processors = getProcessors();               
	        List<Object> questionList;
	        
	        int catZero =0;
	        int catOne =0;
	        int catTwo = 0;
	        int catThree =0;
	        int catFour =0;
	        int catFive =0;
	        int catAbove =0;
	        int totalCount = 0;
	        
	        while( (questionList = listReader.read(processors)) != null ) {
	        	 
	    
	        	String id="";                          		    // int
	            String score="";                  		    // float
	        	
	        	
	        	try {
	        		id=questionList.get(0).toString();
				}catch (Exception e) {
					// TODO: handle exception
				}
	        	try {
	        		score=questionList.get(1).toString();
	        		
	        		
	        		if(Integer.parseInt(score) <= 3) {
	        			catZero++;
	        		}
//	        		else if(Integer.parseInt(score) == 1) {
//	        			catOne++;
//	        		}
//	        		else if(Integer.parseInt(score) == 2) {
//	        			catTwo++;
//	        		}
//	        		else if(Integer.parseInt(score) == 3) {
//	        			catThree++;
//	        		}
//	        		else if(Integer.parseInt(score) == 4) {
//	        			catFour++;
//	        		}
//	        		else if(Integer.parseInt(score) == 5) {
//	        			catFive++;
//	        		}
	        		else if(Integer.parseInt(score) > 3) {
	        			catAbove++;
	        		}
	        		totalCount++;
	        		
	        		
				}catch (Exception e) {
	
				}
	        }
	        
	        System.out.println("Category 0:  "+(catZero/(float)totalCount)*100);
//	        System.out.println("Category 01:  "+(catOne/(float)totalCount)*100);
//	        System.out.println("Category 02:  "+(catTwo/(float)totalCount)*100);
//	        System.out.println("Category 03:  "+(catThree/(float)totalCount)*100);
//	        System.out.println("Category 04:  "+(catFour/(float)totalCount)*100);
//	        System.out.println("Category 05:  "+(catFive/(float)totalCount)*100);
	        System.out.println("Category Above:  "+(catAbove/(float)totalCount)*100);
	        
	        System.out.println("Done!!");
	       
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
	            new Optional()
	            
	    };
	    
	    return processors;
	}

}
