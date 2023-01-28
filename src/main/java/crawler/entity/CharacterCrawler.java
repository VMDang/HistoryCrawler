package crawler.entity;

import crawler.BaseWebCrawler;
import history.entity.Character;

import java.io.IOException;
import java.io.Writer;
import java.io.FileWriter;
import org.json.simple.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.*;

public class CharacterCrawler extends BaseWebCrawler {
	private static Document doc;
    public CharacterCrawler(List<String> urls) {
        super(urls);
        CharacterCrawler.doc = null;
    }
    
	public static Document getDoc() {
		return doc;
	}

	public void setDoc(Document doc) {
		CharacterCrawler.doc = doc;
	}
	public String getUrlIndex(int i) {
		return this.urls.get(i);
	}
	@Override
	public boolean connect(String url) {
		 Document document = null;
	        try {
	            document = Jsoup
	                    .connect(url)
	                    .userAgent("Jsoup client")
	                    .timeout(20000).get();
	            setDoc(document);
	            return true;
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		return false;
	}
	
	public List<String> getAllUrl_NKS(String url){
		List<String> allUrl = new ArrayList<String>();
		
		Document Doc1 = getDoc();
		Elements charaterATag1 = Doc1.select("h2[itemprop=name] a");
        for(Element a : charaterATag1) {
        	String charaterUrl = "https://nguoikesu.com"+ a.attr("href");
        	System.out.println(charaterUrl);
        	allUrl.add(charaterUrl);
        }
        
		Elements li_tags = Doc1.select("nav.pagination__wrapper li");
		Element nextPageButton = li_tags.get(li_tags.size()-2);
		
		while(!li_tags.get(li_tags.size()-2).className().equals("disabled page-item")) {
			String hrefNext = "https://nguoikesu.com" + nextPageButton.select("a").get(0).attr("href");
			System.out.println(hrefNext + "\n\n");
			
			Document page = null;
	        try{
	            page = Jsoup
	                    .connect(hrefNext)
	                    .userAgent("Jsoup client")
	                    .timeout(20000).get();
	            Elements charaterATags = page.select("h2[itemprop=name] a");
	            for(Element a : charaterATags) {
	            	String charaterUrl = "https://nguoikesu.com"+ a.attr("href");
	            	System.out.println(charaterUrl);
	            	allUrl.add(charaterUrl);
	            } 
	            li_tags = page.select("nav.pagination__wrapper li");
		        nextPageButton = li_tags.get(li_tags.size()-2);
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	       
		}
		return allUrl;
	}
	public void getdata_NKS(List<String> allUrl) {
		for(String url : allUrl) {
			try {
				Document characterPage = Jsoup
	                     				.connect(url)
	                     				.userAgent("Jsoup client")
	                     				.timeout(20000).get();
				String name = characterPage.select("div.page-header h2").text();
				Elements infoBoxs = characterPage.select("div[class*=infobox]");
				if(infoBoxs.size()> 0) {
					Element infoTable = infoBoxs.get(0).select("table").get(0);
					Elements rows = infoTable.select("tbody tr");
					String Sinh = null;
					String Mat = null;
					String description = null;
					for(Element r : rows) {
						Elements col1 = r.select("th");
						Elements col2 = r.select("td");
						if(col1.size() > 0 & col2.size() > 0) {
							String Key = col1.get(0).text();
							String Value = col2.get(0).text();
							if(Key.equals("Sinh")) {
								Sinh = Value;
							}else 
							if(Key.equals("Mất")) {
									Mat = Value;
							}else {
								String KeyValue = Key + " : " + Value +"\n";
								description += KeyValue;
							}	
						}else if(col2.size() == 0) {
							String Value = r.text();
							description += Value +"\n";
						}
					}
					try (Writer writer = new FileWriter("C:\\Users\\Acer\\Documents\\workspace\\JaVa\\History_Project\\HistoryCrawler\\", true)) {
					    Gson gson = new GsonBuilder().create();
					    gson.toJson(event, writer);
					    writer.write('\n');
					}
					
				}else {
					
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		List<String> urls = new ArrayList<String>();
		urls.add("https://nguoikesu.com/nhan-vat");
		
		CharacterCrawler test = new CharacterCrawler(urls);
		String url = test.getUrlIndex(0);
		test.connect(url);
		List<String> allUrl = test.getAllUrl_NKS(url);
		System.out.println(allUrl.size());
		
		test.getdata_NKS(allUrl);
	}
}
