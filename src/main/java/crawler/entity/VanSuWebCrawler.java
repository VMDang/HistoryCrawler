package crawler.entity;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import history.entity.Character;

public class VanSuWebCrawler extends CharacterCrawler{

	public VanSuWebCrawler(List<String> urls) {
		super(urls);
		// TODO Auto-generated constructor stub
	}
	
	public List<String> getAllUrl_VanSu(String url){
		List<String> allUrl = new ArrayList<String>();
		
		Document Doc1 = getDoc();
		Element table = Doc1.selectFirst("table[class = ui selectable celled table]");
		Elements rows = table.select("tbody tr");
        for(Element r : rows) {
        	Element a = r.selectFirst("td > a");
        	String charaterUrl = "https://vansu.vn"+ a.attr("href");
        	System.out.println(charaterUrl);
        	allUrl.add(charaterUrl);
        }
        
		Elements buttonsChangePage = table.select("tfoot div a");
		Element nextPageButton = buttonsChangePage.get(buttonsChangePage.size()-1);
		
		do {
			String hrefNext = nextPageButton.attr("href");
			System.out.println(hrefNext + "\n\n");
			
			Document page = null;
	        try{
	            page = Jsoup
	                    .connect(hrefNext)
	                    .userAgent("Jsoup client")
	                    .timeout(20000).get();
	            Element table1 = page.selectFirst("table[class = ui selectable celled table]");
	    		Elements rows1 = table1.select("tbody tr");
	            for(Element r : rows1) {
	            	Element a = r.selectFirst("td > a");
	            	String charaterUrl = "https://vansu.vn"+ a.attr("href");
	            	System.out.println(charaterUrl);
	            	allUrl.add(charaterUrl);
	            }
	            buttonsChangePage = table1.select("tfoot div a");
	    		nextPageButton = buttonsChangePage.get(buttonsChangePage.size()-1);
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	       
		}while(buttonsChangePage.size()!=1);
		return allUrl;
	}
	public void getData_VanSu(List<String> allUrl) {
		try (Writer writer = new FileWriter("C:\\Users\\Acer\\Documents\\workspace\\JaVa\\History_Project2\\HistoryCrawler\\characterVanSu.json", true)) {
		    writer.write('[');
		}catch(IOException e) {
			e.printStackTrace();
		}
		for(String url : allUrl) {
			Character nv = new Character();
			try {
				Document characterPage = Jsoup
	                     				.connect(url)
	                     				.userAgent("Jsoup client")
	                     				.timeout(20000).get();
				String name = characterPage.select("div[class = active section]").text();
				String time = null;
				String depcription = "";
				String era = null;
				String place = null;
				String anotherName = null ;
				
				Element table = characterPage.select("table").get(0);
				Elements rows = table.select("tbody tr");
				for(Element r : rows) {
					Elements cols = r.select("td");
					if(cols.size() == 2) {
						String key = cols.get(0).text();
						String value = cols.get(1).text();
						if(key.equals("Tên khác")) {
							 anotherName = value;
						}
						if(key.equals("Thời kì")) era = value;
						if(key.equals("Năm sinh")) time = value;
						if(key.equals("Tỉnh thành")) place = value;
						
					}else if(cols.size()==1) {
						depcription+= cols.text();
					}
				}
				nv.setName(name);
				nv.setTime(time);
				nv.setPlace(place);
				nv.setAotherName(anotherName);
				nv.setEra(era);
				nv.setDescription(depcription);
				try (Writer writer = new FileWriter("C:\\Users\\Acer\\Documents\\workspace\\JaVa\\History_Project2\\HistoryCrawler\\characterVanSu.json", true)) {
				    Gson gson = new GsonBuilder().setPrettyPrinting().create();
				    gson.toJson(nv, writer);
				    writer.write(",\n");
				}catch(IOException e) {
					e.printStackTrace();
				}
				
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		try (Writer writer = new FileWriter("C:\\Users\\Acer\\Documents\\workspace\\JaVa\\History_Project2\\HistoryCrawler\\characterVanSu.json", true)) {
		    writer.write(']');
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		List<String> urls = new ArrayList<String>();
		urls.add("https://vansu.vn/viet-nam/viet-nam-nhan-vat?page=0");
		
		VanSuWebCrawler test = new VanSuWebCrawler(urls);
		String url = test.getUrlIndex(0);
		test.connect(url);
		List<String> allUrl = test.getAllUrl_VanSu(url);
		System.out.println(allUrl.size());
		test.getData_VanSu(allUrl);
	}
}
