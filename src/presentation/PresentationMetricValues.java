package presentation;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.google.api.server.spi.config.ApiMethodConfig.Parameter;

public class PresentationMetricValues {
	
	private String filePathTS = "E:/Projects/SOContentQualityResources/DataStore/Presentation/TextSpeakStore.txt";
	private String postFilePath = "E:/Projects/SOContentQualityResources/DataStore/Presentation/text.txt";
	private String textSpeakContent, postContent;
	private String [] txtspk;
	private String[] post;
	private int count=0;
	private int upperCaseCount=0, lowerCaseCount=0;
	
	
	/**
	 * Check either the post contains any TextSpeak or not
	 * if contain then count the number of TextSpeak within the post content
	 * @return number of TextSpeak
	 */
	public int check() {
		try {
			textSpeakContent = new Scanner(new File(filePathTS)).useDelimiter("\\Z").next();
			postContent = new Scanner (new File(postFilePath)).useDelimiter("\\Z").next();
		}catch(Exception e) {
			e.printStackTrace();
		}
		txtspk = textSpeakContent.split(" ");
		post = postContent.split(" ");
		
		for(String p: post) {
			for(String tsc: txtspk) {
				if(p.equals(tsc))
					count++;
			}
		}
		
		return count;
	}
	
	/**
	 * Count the upper case and lower case characters from the post content 
	 */
	
	public void caseCount() {
		for(int i=0; i<postContent.length(); i++) {
			if(Character.isUpperCase(postContent.charAt(i))) upperCaseCount++;
			else if (Character.isLowerCase(postContent.charAt(i))) lowerCaseCount++;
		}
		System.out.print("UPPER:"+upperCaseCount+"\n\n"+"lower:"+lowerCaseCount+"\n\n");
	}
	
	/**
	 * This method calculate the total number of URLs, email,code segment  residing in <a> tag of a web page
	 */
	public void countURL_Email_CodeSegment() {
		String url = "http://cseku.ac.bd/faculty/~khasan/";
	    Document doc = null;
	    try {
	        doc = Jsoup.connect(url)
	               .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
	               .referrer("http://www.google.com")              
	               .get();
	    } catch (NullPointerException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    } catch (HttpStatusException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
		System.out.println("Total number of links:"+doc.select("a").size()); // URL Count
		
		System.out.println("Total number of code snippets:"+doc.select("code").size()); // Code Segment Count
		/**
		 * Email count
		 */
		Matcher m = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+").matcher(doc.toString());
	    while(m.find()) {             
	     String email = m.group();              
	     System.out.println(email);
	    }
	}
	
}
