package presentation;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mysql.cj.log.Log;

public class PresentationMain {
	
	public void collectTextSpeak() throws IOException {
		
		String path = "./DataStore/Presentation/TextSpeakStore.txt";
		FileWriter writeFile = new FileWriter(path);
		PrintWriter printFile = new PrintWriter(writeFile);
		String url = "http://cseku.ac.bd/faculty/~rdebnath/";
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
	    Elements textSpeak = doc.select("li>b");
	    
	    for(Element singletxtSpk : textSpeak) {
	    	printFile.write(singletxtSpk.text().toString()+" ");
	    	//System.out.println(singletxtSpk.text().toString());
	    }
	    printFile.close();
	    
	    
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		PresentationMain tsc = new PresentationMain();
		tsc.collectTextSpeak();
		PresentationMetricValues pmv = new PresentationMetricValues();
//		int txtSpkCount=pmv.check();
//		pmv.caseCount();
		pmv.countURL_Email_CodeSegment();
//		System.out.println("Text Speak Count:"+txtSpkCount);

	}

}
