package hitaskotiscraper;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HitasKotiScraper implements Runnable{
	
	private final static String URL = "http://www.hitaskoti.fi";
	private final static String ADS_PAGE = "/haku_hakutulokset";
	
	private static ArrayList<String> urlsSent = new ArrayList<>();

	public static void main(String[] args) {
		SendMessageToChannel.sendMessage("Start scraping at "+LocalDateTime.now().toString());
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		executor.scheduleAtFixedRate(new HitasKotiScraper(), 0, 5, TimeUnit.MINUTES);
	}
	
	/*
	 * Get the ad. If the ad's date is today and the ad's url has not been sent, send the ad through Telegram
	 */
	public void run(){
		HitasKotiAd ad = null;
		try {
			ad = getFirstHitasKotiAd();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Ad is "+ad.getUrl() + " " + ad.getZip() + " " + ad.getAddress());
		
		if (ad != null){
			if( ad.getDatePublished().isEqual(LocalDate.now()) && !urlsSent.contains(ad.getUrl())){
				String message = URL + ad.getUrl() + "\n" + ad.getZip() + " " + ad.getAddress();
				SendMessageToChannel.sendMessage(message);
			
				urlsSent.add(ad.getUrl());
			}
		}
	}
	
	/**
	 * Get the first advertisement of apartment for sale from http://www.hitaskoti.fi/haku_hakutulokset
	 * 
	 * @return
	 * @throws IOException
	 */
	static HitasKotiAd getFirstHitasKotiAd() throws IOException{
		HitasKotiAd ad = new HitasKotiAd();
		
		Document page = Jsoup.connect(URL+ADS_PAGE).get();
		Element firstAd = page.selectFirst("tr.taulu_vari1");
		
		String date = page.selectFirst("td.borderright").text();
		ad.setDatePublished(LocalDate.parse(date));
		
		Elements elementA = firstAd.select("a");
		
		String adUrl  = elementA.attr("href");
		ad.setUrl(adUrl);
		
		String address = elementA.select("strong").text();
		ad.setAddress(address);
		
		String zip = elementA.select("span").text().replace(" ","");
		ad.setZip(zip);
		
		return ad;
	}
}
