package rouge;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvListReader;
import org.supercsv.io.ICsvListReader;
import org.supercsv.prefs.CsvPreference;


public class ROUGEFileGeneration {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		readWithCsvListReader();
	}
	
	private static void readWithCsvListReader() throws Exception {
        
	    //DBConnection myCon = new DBConnection();
	    //Statement myStmt=myCon.getDBConnection();
		ICsvListReader listReader = null;
	        int counterHigh =0;
	        int counterLow =0;
	        int totalScore= 0;
	        try {
	        	
	        	FileWriter writeFile;
	        	PrintWriter printInFile;
	        	String folderHighReference = "E:/Projects/SOContentQualityResources/DataStore/Python/ROUGE/Score_GT_0/Reference/";
	        	String folderHighSystem = "E:/Projects/SOContentQualityResources/DataStore/Python/ROUGE/Score_GT_0/System/";
	        	String folderLowReference = "E:/Projects/SOContentQualityResources/DataStore/Python/ROUGE/Score_LT_0/Reference/";
	        	String folderLowSystem = "E:/Projects/SOContentQualityResources/DataStore/Python/ROUGE/Score_LT_0/System/";
	        	
	            listReader = new CsvListReader(new FileReader("E:/Projects/SOContentQualityResources/CSVData/Python/QueryData/QueryResults_10_AnsCount1_Score_NE_0_2017.csv"), CsvPreference.STANDARD_PREFERENCE);
	            //csvWriter = new CsvListWriter(new FileWriter("./CSVData/questionScore.csv"),CsvPreference.STANDARD_PREFERENCE);
	            listReader.getHeader(true); // skip the header (can't be used with CsvListReader)
	            final CellProcessor[] processors = getProcessors();           
	            List<Object> questionList;
	            
	            while( (questionList = listReader.read(processors)) != null ) {
	            	 
	            	//System.out.println(questionList.get(0).toString() + questionList.get(1).toString());
	            	
	            	//Dynamically File Creation
	            	//File newFile=new File("./Docs/"+questionList.get(0)+".txt");
	            	//newFile.createNewFile();
	                //FileWriter writeFile = new FileWriter(newFile);
	                //BufferedWriter writeBuffer= new BufferedWriter(writeFile);
	            	
	            	//writeBuffer.write(questionList.get(1).toString()+ questionList.get(2).toString());
	            	                        
	                //writeBuffer.close());
	            	String id="";                          		// int
	            	String postTypeId="";                  		// tinyint
	            	String acceptedAnswerId="-9999";          // int
	            	String parentId="-9999";                  // int
	            	String creationDate="";                		// datetime
	            	String deletionDate="";                		// datetime
	            	String score="-9999";                       // int
	            	String viewCount="-9999";                   // int
	            	String bodyText="";                        		// nvarchar (max)
	            	String bodyCode="";
	            	String ownerUserId="-9999";                 // int
	            	String ownerDisplayName="";            		// nvarchar (40)
	            	String lastEditorUserId="-9999";            // int 
	            	String lastEditorDisplayName="";       		// nvarchar (40)
	            	String lastEditDate="";                		// datetime
	            	String lastActivityDate="";            		// datetime
	            	String title="";                      		 // nvarchar (250)
	            	String tags="";                        		// nvarchar (250)
	            	String answerCount="-9999";                 // int
	            	String commentCount="-9999";                // int
	            	String favoriteCount="-9999";             // int
	            	String closedDate="";                  		// datetime
	            	String communityOwnedDate="";         		 // datetime
	            	
	            	try {
	            		id=questionList.get(0).toString();
					}catch (Exception e) {
						// TODO: handle exception
					}
	            	try {
	            		postTypeId=questionList.get(1).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
	            	try {
	            		acceptedAnswerId=questionList.get(2).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
	            	try {
	            		parentId=questionList.get(3).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
	            	try {
	            		creationDate=questionList.get(4).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
	            	try {
	            		deletionDate=questionList.get(5).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
	            	try {
	            		score=questionList.get(6).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
	            	try {
	            		viewCount=questionList.get(7).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
	            	try {
	            		Document doc = Jsoup.parse(questionList.get(8).toString());
	        			Elements contentText = doc.select("p");
//	        			content = doc.select(":not(code)");
	        			String finalString = contentText.text();
	        			finalString = finalString.replaceAll("[\\[,\\]]", "");
	        			Pattern pattern = Pattern.compile("[A-Za-z0-9)]{3,}+[.?;!]+[\\s]");
	        			Matcher matcher = pattern.matcher(finalString);
	        			
	        			while(matcher.find()) {
	        				String m = matcher.group();
	        				finalString = finalString.replace(m,m+"\n");
	        				
//	        				System.out.println(m+"\n\n"+finalString);
	        			}
	        			
	        			bodyText = finalString;
	        			bodyText = bodyText.replaceAll("[.?;!]","");
	        			
	        			
//	        			bodyText = finalString.replaceAll("[A-Za-z0-9)]{3,}+[.?;!]","\n");
//	            		Document doc = Jsoup.parse(questionList.get(8).toString());
	            		Elements contentCode = doc.select("code");
	            		bodyCode = contentCode.text();
	            		bodyCode = bodyCode.replaceAll("[(){},]", " ");
	            		bodyCode = bodyCode.replaceAll("[;]","");
	            		
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
	            	try {
	            		ownerUserId=questionList.get(9).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
	            	try {
	            		ownerDisplayName=questionList.get(10).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
	            	try {
	            		lastEditorUserId=questionList.get(11).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
	            	try {
	            		lastEditorDisplayName=questionList.get(12).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
	            	try {
	            		lastEditDate=questionList.get(13).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
	            	try {
	            		lastActivityDate=questionList.get(14).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
	            	try {
	            		title=questionList.get(15).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
	            	try {
//	            		Document doc = Jsoup.parse(questionList.get(16).toString());
//	        			Elements tagText = doc.select("a");
	            		
	            		tags=questionList.get(16).toString();
	            		tags = tags.replaceAll("[<]", "");
	            		tags = tags.replaceAll("[>]", " ");
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
	            	try {
	            		answerCount=questionList.get(17).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
	            	try {
	            		commentCount=questionList.get(18).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
	            	try {
	            		favoriteCount=questionList.get(19).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
	            	try {
	            		closedDate=questionList.get(20).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
	            	try {
	            		communityOwnedDate=questionList.get(21).toString();
					}catch (Exception e) {
						// TODO: handle exception
						//e.printStackTrace();
					}
	            	
	            	//printInFile.print(id+":::"+body+"\n\n");
	            	//System.out.println(body+"\n\n");
	            	//counter++;
//	            	String query = "INSERT INTO posts (id,postTypeId,acceptedAnswerId,parentId,creationDate,deletionDate,score,viewCount,body,ownerUserId,ownerDisplayName,lastEditorUserId,lastEditorDisplayName,lastEditDate,lastActivityDate,title,tags,answerCount,commentCount,favoriteCount,closedDate,communityOwnedDate) "
//	            			+ "VALUES('"+Integer.valueOf(id)+"','"+Integer.valueOf(postTypeId)+"','"+Integer.valueOf(acceptedAnswerId)+"','"+Integer.valueOf(parentId)+"','"+creationDate+"','"+deletionDate+"','"+Integer.valueOf(score)+"','"+Integer.valueOf(viewCount)+"','"+body+"','"+Integer.valueOf(ownerUserId)+"','"+ownerDisplayName+"','"+Integer.valueOf(lastEditorUserId)+"','"+lastEditorDisplayName+"','"+lastEditDate+"','"+lastActivityDate+"','"+title+"','"+tags+"','"+Integer.valueOf(answerCount)+"','"+Integer.valueOf(commentCount)+"','"+Integer.valueOf(favoriteCount)+"','"+closedDate+"','"+communityOwnedDate+"')";
//	            	String query = "DELETE FROM posts";
	            	//myCon.executeInsertDeleteQuery(query);
	            	//myStmt.executeUpdate(query);
	            	
	            	//System.out.println(id+"\n"+postTypeId+"\n"+acceptedAnswerId+"\n"+score+"\n"+body+"\n"+title+"\n"+tags+"\n"+answerCount);
	            	//System.out.println("****************************************************************************************************");
//	            	totalScore+=Integer.valueOf(score);
//	            	if(Integer.valueOf(score)<4)
//	            		counterLow++;
//	            	else
//	            		counterHigh++;
	            	
	            	//printInFile.printf("%s\t%s\n",id.toString(),score.toString());
	            	//csvWriter.write(id.toString(),score.toString());
	            	
	            	//printInFile.write("\n___________________________________________________________________________________________________\n");
	            	//if(counter>15) break;
	            	if((Integer.valueOf(score)<0)) {
	            		writeFile = new FileWriter(folderLowReference+id+"_Low.txt");
	            		printInFile = new PrintWriter(writeFile);
	            		printInFile.write(title+"\n"+bodyCode);
	            		printInFile.close();
	            		
	            		writeFile = new FileWriter(folderLowSystem+id+"_Low.txt");
	            		printInFile = new PrintWriter(writeFile);
	            		printInFile.write(bodyText + "\n"+ tags);	            		
	            		printInFile.close();
	            		System.out.println(tags);
	            	}
	            	else if((Integer.valueOf(score)>0)) {
	            		writeFile = new FileWriter(folderHighReference+id+"_High.txt");
	            		printInFile = new PrintWriter(writeFile);
	            		printInFile.write(title+"\n"+bodyCode);
	            		printInFile.close();
	            		
	            		writeFile = new FileWriter(folderHighSystem+id+"_High.txt");
	            		printInFile = new PrintWriter(writeFile);
	            		printInFile.write(bodyText + "\n"+ tags);	            		
	            		printInFile.close();
	            	}
	            	
	            }
	            
	            System.out.println("Data Write in File Finished Successfully!!");
	            //System.out.println("LOW="+counterLow+"High="+counterHigh+"Average Score="+(totalScore/50000.0));
	            //printInFile.close();
	            //csvWriter.close();
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
