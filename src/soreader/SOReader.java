package soreader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Statement;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvListReader;
import org.supercsv.io.CsvListWriter;
import org.supercsv.io.ICsvListReader;
import org.supercsv.io.ICsvListWriter;
import org.supercsv.io.ICsvWriter;
import org.supercsv.prefs.CsvPreference;

import dbconnection.DBConnection;

public class SOReader {

	public static void main(String[] args) throws Exception {

		readWithCsvListReader();

	}
	
private static void readWithCsvListReader() throws Exception {
        

	ICsvListReader listReader = null;
	ICsvListWriter csvWriterTextOnly = null;
	ICsvListWriter csvWriterTextCode = null;

    try {

        
    	// For TextOnlyFile
    	
    	String fileLocation = "E:/Projects/SOContentQualityResources/CSVData/LowQuality/";
    	String outFileTextOnly = "E:/Projects/SOContentQualityResources/CSVData/LowTextOnly.csv";
    	String outFileTextCode = "E:/Projects/SOContentQualityResources/CSVData/LowTextCode.csv";

    	csvWriterTextOnly = new CsvListWriter(new FileWriter(outFileTextOnly),CsvPreference.STANDARD_PREFERENCE);
    	csvWriterTextCode = new CsvListWriter(new FileWriter(outFileTextCode),CsvPreference.STANDARD_PREFERENCE);
    	
    	csvWriterTextOnly.write("Id","Score","Title","Text","Tag");
    	csvWriterTextCode.write("Id","Score","Title","Text","Code","Tag");
    	
    	int NO_OF_FILE = 9;
    	int countText =0, countCode = 0;
    	
    	for (int i = 1; i<= NO_OF_FILE; i++) {
	        listReader = new CsvListReader(new FileReader(fileLocation+"LowQuality_"+i+".csv"), CsvPreference.STANDARD_PREFERENCE);          
	        listReader.getHeader(true); // skip the header (can't be used with CsvListReader)
	        final CellProcessor[] processors = getProcessors();               
	        List<Object> questionList;
	        
	        while( (questionList = listReader.read(processors)) != null ) {
	        	 
	           	String id="";                          		// int
//	        	String postTypeId="";                  		// tinyint
//	        	String acceptedAnswerId="-9999";            // int
//	        	String parentId="-9999";                    // int
//	        	String creationDate="";                		// datetime
//	        	String deletionDate="";                		// datetime
	        	String score="-9999";                       // int
//	        	String viewCount="-9999";                   // int
	        	String bodyText="";                        	// nvarchar (max)
	        	String bodyCode="";                        	// nvarchar (max)
//	        	String ownerUserId="-9999";                 // int
//	        	String ownerDisplayName="";            		// nvarchar (40)
//	        	String lastEditorUserId="-9999";            // int 
//	        	String lastEditorDisplayName="";       		// nvarchar (40)
//	        	String lastEditDate="";                		// datetime
//	        	String lastActivityDate="";            		// datetime
	        	String title="";                      		// nvarchar (250)
	        	String tags="";                        		// nvarchar (250)
//	        	String answerCount="-9999";                 // int
//	        	String commentCount="-9999";                // int
//	        	String favoriteCount="-9999";               // int
//	        	String closedDate="";                  		// datetime
//	        	String communityOwnedDate="";         		// datetime
	        	
	        	try {
	        		id=questionList.get(0).toString();
				}catch (Exception e) {
					// TODO: handle exception
				}
//	        	try {
//	        		postTypeId=questionList.get(1).toString();
//				}catch (Exception e) {
//					// TODO: handle exception
//					//e.printStackTrace();
//				}
//	        	try {
//	        		acceptedAnswerId=questionList.get(2).toString();
//				}catch (Exception e) {
//					// TODO: handle exception
//					//e.printStackTrace();
//				}
//	        	try {
//	        		parentId=questionList.get(3).toString();
//				}catch (Exception e) {
//					// TODO: handle exception
//					//e.printStackTrace();
//				}
//	        	try {
//	        		creationDate=questionList.get(4).toString();
//				}catch (Exception e) {
//					// TODO: handle exception
//					//e.printStackTrace();
//				}
//	        	try {
//	        		deletionDate=questionList.get(5).toString();
//				}catch (Exception e) {
//					// TODO: handle exception
//					//e.printStackTrace();
//				}
	        	try {
	        		score=questionList.get(6).toString();
				}catch (Exception e) {
					// TODO: handle exception
					//e.printStackTrace();
				}
//	        	try {
//	        		viewCount=questionList.get(7).toString();
//				}catch (Exception e) {
//					// TODO: handle exception
//					//e.printStackTrace();
//				}
	        	try {
	        		Document doc = Jsoup.parse(questionList.get(8).toString());
	    			Element contentText = doc.select(":not(pre)").first();
	    			String finalString = contentText.text();
	    			finalString = finalString.replaceAll("[\\[,\\]]", "");
	        		bodyText=finalString;
	        		
	        		Elements contentCode = doc.select("pre");
	        		bodyCode = contentCode.text();
	        		
				}catch (Exception e) {
					// TODO: handle exception
					//e.printStackTrace();
				}
//	        	try {
//	        		ownerUserId=questionList.get(9).toString();
//				}catch (Exception e) {
//					// TODO: handle exception
//					//e.printStackTrace();
//				}
//	        	try {
//	        		ownerDisplayName=questionList.get(10).toString();
//				}catch (Exception e) {
//					// TODO: handle exception
//					//e.printStackTrace();
//				}
//	        	try {
//	        		lastEditorUserId=questionList.get(11).toString();
//				}catch (Exception e) {
//					// TODO: handle exception
//					//e.printStackTrace();
//				}
//	        	try {
//	        		lastEditorDisplayName=questionList.get(12).toString();
//				}catch (Exception e) {
//					// TODO: handle exception
//					//e.printStackTrace();
//				}
//	        	try {
//	        		lastEditDate=questionList.get(13).toString();
//				}catch (Exception e) {
//					// TODO: handle exception
//					//e.printStackTrace();
//				}
//	        	try {
//	        		lastActivityDate=questionList.get(14).toString();
//				}catch (Exception e) {
//					// TODO: handle exception
//					//e.printStackTrace();
//				}
	        	try {
	        		title=questionList.get(15).toString();
				}catch (Exception e) {
					// TODO: handle exception
					//e.printStackTrace();
				}
	        	try {
	        		tags=questionList.get(16).toString();
				}catch (Exception e) {
					// TODO: handle exception
					//e.printStackTrace();
				}
//	        	try {
//	        		answerCount=questionList.get(17).toString();
//				}catch (Exception e) {
//					// TODO: handle exception
//					//e.printStackTrace();
//				}
//	        	try {
//	        		commentCount=questionList.get(18).toString();
//				}catch (Exception e) {
//					// TODO: handle exception
//					//e.printStackTrace();
//				}
//	        	try {
//	        		favoriteCount=questionList.get(19).toString();
//				}catch (Exception e) {
//					// TODO: handle exception
//					//e.printStackTrace();
//				}
//	        	try {
//	        		closedDate=questionList.get(20).toString();
//				}catch (Exception e) {
//					// TODO: handle exception
//					//e.printStackTrace();
//				}
//	        	try {
//	        		communityOwnedDate=questionList.get(21).toString();
//				}catch (Exception e) {
//					// TODO: handle exception
//					//e.printStackTrace();
//				}
	        	
	        	if(bodyCode != null && !bodyCode.isEmpty()) {
	        		csvWriterTextCode.write(id,score, title, bodyText, bodyCode, tags);
	        		countCode++;
	        	}
	        	else {
	        		csvWriterTextOnly.write(id,score, title, bodyText,tags);
	        		countText++;
	        	}
	        }
    	}
    	csvWriterTextOnly.close();
    	csvWriterTextCode.close();
        System.out.println("Data Write in File Finished Successfully!!"+"Count Text:"+countText+"Count Code:"+countCode);
        

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
