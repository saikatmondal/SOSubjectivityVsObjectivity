package someasure;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvListReader;
import org.supercsv.io.CsvListWriter;
import org.supercsv.io.ICsvListReader;
import org.supercsv.io.ICsvListWriter;
import org.supercsv.prefs.CsvPreference;


public class CommentAnswerCount {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		JavaCommentAnswerCount jCount = new JavaCommentAnswerCount();
		jCount.start();
	}
}
class JavaCommentAnswerCount extends Thread{
        
    public void run() {
    	ICsvListReader listReader = null;
    	ICsvListWriter csvWriterH = null;
    	ICsvListWriter csvWriterL = null;
		final int NO_OF_FILES=10;
	
	    try {       
	    	
	    	csvWriterH = new CsvListWriter(new FileWriter("E:/Projects/SOContentQualityResources/CSVData/Python/MetricResults/Score_GT_0.csv"),CsvPreference.STANDARD_PREFERENCE);
	    	csvWriterL = new CsvListWriter(new FileWriter("E:/Projects/SOContentQualityResources/CSVData/Python/MetricResults/Score_LT_0.csv"),CsvPreference.STANDARD_PREFERENCE);	    	
	    	
	    	String firstColumnTitle = "Id";
			String secondColumnTitle = "Score";
			csvWriterH.write(firstColumnTitle, secondColumnTitle);
			csvWriterL.write(firstColumnTitle, secondColumnTitle);
	    	
	    	for (int i =1; i <= NO_OF_FILES; i++) {
	    		System.out.println("File No:"+i);
	    		
		    	//Reading CSV File
		        listReader = new CsvListReader(new FileReader("E:/Projects/SOContentQualityResources/CSVData/Python/QueryData/QueryResults_"+i+"_AnsCount1_Score_NE_0_2017.csv"), CsvPreference.STANDARD_PREFERENCE);          
		        listReader.getHeader(true); // skip the header (can't be used with CsvListReader)
		        final CellProcessor[] processors = getProcessors();               
		        List<Object> questionList;
        
		        while( (questionList = listReader.read(processors)) != null ) {
		        	 
		        	String id="";                          		// int
		        	String score="-9999";                       // int
		        	String bodyText="";                        	// nvarchar (max)
		        	String bodyCode="";                        	// nvarchar (max)
		        	String answerCount="-9999";                 // int
		        	String commentCount="-9999";                // int
		        	
		        	try {
		        		id=questionList.get(0).toString();
					}catch (Exception e) {
						// TODO: handle exception
					}
		        	
		        	try {
		        		score=questionList.get(6).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
		        	        	
		         	
		        	if(Integer.valueOf(score)> 0){
		        		// Text File Creation and Writing Text
		        		csvWriterH.write(id, score);	
		        	}
		        	
		        	else if(Integer.valueOf(score) < 0){
		        		// Text File Creation and Writing Text
		        		csvWriterL.write(id, score);
		
		        	}
		        	
		        }
	    	}
		        csvWriterH.close();
		        csvWriterL.close();
		        System.out.println("Data Write in File Finished Successfully!!");
	}catch (Exception e) {
		// TODO: handle exception
	}
    finally {
    	
        try {
        	if( listReader != null ) {
                listReader.close();
        }
		} catch (Exception e2) {
			// TODO: handle exception
		}
    }
}

private static CellProcessor[] getProcessors() {
	         
    final CellProcessor[] processors = new CellProcessor[] {
    		new Optional(), // post Id not null
            new Optional(),
            new Optional(),
            new Optional(),
            new Optional(),
            new Optional(),
            new Optional(),
            new Optional(),
            new Optional(),
            new Optional(),
            new Optional(),
            new Optional(),
            new Optional(),
            new Optional(),
            new Optional(),
            new Optional(),
            new Optional(),
            new Optional(),
            new Optional(),
            new Optional(),
            new Optional(),
            new Optional()
    };
    
    return processors;
}
}



