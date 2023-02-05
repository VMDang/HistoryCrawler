package crawler.entity.character;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import crawler.manager.CrawlerManager;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import history.entity.Character;

public class VanSuCharacterCrawler extends CharacterCrawler{

	public VanSuCharacterCrawler(String url) {
		super(url);
		// TODO Auto-generated constructor stub
	}
	public VanSuCharacterCrawler() {
		
	}
	public List<String> getAllUrl(String url){
		List<String> allUrl = new ArrayList<String>();
		
		Document Doc1 = getDoc();
		Element table = Doc1.selectFirst("table[class = ui selectable celled table]");
		Elements rows = table.select("tbody tr");
        for(Element r : rows) {
        	Element a = r.selectFirst("td > a");
        	String charaterUrl = "https://vansu.vn"+ a.attr("href");
//        	System.out.println(charaterUrl);
        	allUrl.add(charaterUrl);
			CrawlerManager.setCountUrlBrowsed();
        }
        
		Elements buttonsChangePage = table.select("tfoot div a");
		Element nextPageButton = buttonsChangePage.get(buttonsChangePage.size()-1);
		
		do {
			String hrefNext = nextPageButton.attr("href");
//			System.out.println(hrefNext + "\n\n");
			
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
//	            	System.out.println(charaterUrl);
	            	allUrl.add(charaterUrl);
					CrawlerManager.setCountUrlBrowsed();
	            }
	            buttonsChangePage = table1.select("tfoot div a");
	    		nextPageButton = buttonsChangePage.get(buttonsChangePage.size()-1);
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	       
		}while(buttonsChangePage.size()!=1);
		return allUrl;
	}
	public void getData(List<String> allUrl) {
		try (Writer writer = new FileWriter("src\\main\\java\\json\\characterVanSu1.json")) {
		    writer.write('[');

		int count = 0;
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
				List<String> era = new ArrayList<String>();
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
						if(key.equals("Thời kì")) {
							String[] arrEra = cols.get(1).html().split("<br>");
							for(String e : arrEra) {
								era.add(e);
							}
						}
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

				count++;
			    Gson gson = new GsonBuilder().setPrettyPrinting().create();
			    gson.toJson(nv, writer);
			    writer.write(",\n");
				
				
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		    writer.write(']');
			CrawlerManager.setEntityCrawled("Nhân vật - Văn sử:", count);

		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		
		String url = "https://vansu.vn/viet-nam/viet-nam-nhan-vat?page=0";
		VanSuCharacterCrawler test = new VanSuCharacterCrawler(url);
		test.connect(url);
		List<String> allUrl = test.getAllUrl(url);
//		System.out.println(allUrl.size());
		test.getData(allUrl);
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		String url = "https://vansu.vn/viet-nam/viet-nam-nhan-vat?page=0";

		VanSuCharacterCrawler test = new VanSuCharacterCrawler(url);
		test.connect(url);
		List<String> allUrl = test.getAllUrl(url);
//		System.out.println(allUrl.size());
		test.getData(allUrl);
		CrawlerManager.setBaseWebList("Character", url);
	}

}
