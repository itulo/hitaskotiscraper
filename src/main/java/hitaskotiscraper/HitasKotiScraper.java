package hitaskotiscraper;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HitasKotiScraper {
	
	private final static String URL = "http://www.hitaskoti.fi/haku_hakutulokset";

	public static void main(String[] args) {
		try {
			scrape();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static void scrape() throws IOException{
		Document doc = Jsoup.connect(URL).get();
		String title = doc.title();
	}
}
