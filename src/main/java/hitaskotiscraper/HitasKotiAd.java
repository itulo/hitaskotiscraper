package hitaskotiscraper;

import java.time.LocalDate;

/**
 * Represent an ad for selling a house
 * 
 * @author italoarmenti
 *
 */
public class HitasKotiAd {

	private String url;
	private String address;
	private String zip;
	private LocalDate datePublished;
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getZip() {
		return zip;
	}
	
	public void setZip(String zip) {
		this.zip = zip;
	}
	
	public LocalDate getDatePublished() {
		return datePublished;
	}
	
	public void setDatePublished(LocalDate datePublished) {
		this.datePublished = datePublished;
	}

}
