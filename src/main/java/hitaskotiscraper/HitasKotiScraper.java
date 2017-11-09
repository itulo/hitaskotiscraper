package hitaskotiscraper;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/**
 * 
 * @author italoarmenti
 *
 */
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
		Document page = Jsoup.connect(URL).get();
		Element firstAd = page.selectFirst("tr.taulu_vari1");
		String date = page.selectFirst("td.borderright").text();
		
		System.out.println(firstAd.toString());
		
		System.out.println(date);
		
		Elements elementA = firstAd.select("a");
		System.out.println(elementA.toString());
		
		String homeUrl = elementA.attr("href");
		System.out.println(homeUrl);
		
		String address = elementA.select("strong").text();
		System.out.println(address);
		
		String zip = elementA.select("span").text().replace(" ","");
	}
}
