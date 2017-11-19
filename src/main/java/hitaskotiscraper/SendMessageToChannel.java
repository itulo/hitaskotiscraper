package hitaskotiscraper;

import java.io.IOException;
import org.jsoup.Jsoup;

public class SendMessageToChannel {
	
	final static String TOKEN = "***";
	final static String CHANNEL = "***";
	
	static void sendMessage(String message){
		String url = "https://api.telegram.org/bot"+TOKEN+"/sendMessage";
		
		try {
			Jsoup.connect(url).data("chat_id", CHANNEL).data("text", message).ignoreContentType(true).post();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause().getMessage());
		}
	}

}
