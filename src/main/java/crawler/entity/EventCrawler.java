package crawler.entity;

import crawler.BaseWebCrawler;
import history.entity.Event;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class EventCrawler extends BaseWebCrawler { 
    public EventCrawler(List<String> urls) {
        super(urls);
    }

	@Override
	public boolean connect(String url) {
		// TODO Auto-generated method stub
		return false;
	}
	public static void main(String[] args) throws IOException {
	   	Event event = new Event();
        Document doc = Jsoup.connect("https://vi.wikipedia.org/wiki/Ni%C3%AAn_bi%E1%BB%83u_l%E1%BB%8Bch_s%E1%BB%AD_Vi%E1%BB%87t_Nam").get();  
        String title = doc.title();  
        System.out.println("Title : " + title);
        Elements itemsElements = doc.select("div[class=mw-parser-output]");
        for (Element element : itemsElements) {
            Elements e1 = element.getElementsByTag("p");
    	    for (Element element2 : e1) {
    	    	if(element2.nextElementSibling() != null) {
    	    		if(element2.nextElementSibling().tagName() == "dl") {
    	    			event.setName(element2.text()+ " " + element2.nextElementSibling().text());
    	    			event.setTime(element2.getElementsByTag("b").text() + " " + element2.nextElementSibling().getElementsByTag("b").text());
    			        String hrefString = element2.nextElementSibling().getElementsByTag("a").attr("href");
    			        Elements eleDocument = Jsoup.connect("https://vi.wikipedia.org/"+hrefString).get().select("div[class=mw-parser-output]");
    			        event.setDescription(eleDocument.select("p").get(0).text());
    	    		}
    	    		else {
    	    	    	event.setName(element2.text());
    			        event.setTime(element2.getElementsByTag("b").text());
    			        if(!element2.getElementsByTag("a").attr("class").equals("new")) {
    			        	String hrefString = element2.getElementsByTag("a").attr("href");
    			        	Elements eleDocument = Jsoup.connect("https://vi.wikipedia.org/"+hrefString).get().select("div[class=mw-parser-output]");
    			        	event.setDescription(eleDocument.select("p").get(0).text());
    			        }
    	    		}
    	    	}
				try (Writer writer = new FileWriter("D:\\HistoryCrawler\\src\\main\\java\\crawler\\json\\event.json", true)) {
				    Gson gson = new GsonBuilder().create();
				    gson.toJson(event, writer);
				    writer.write('\n');
				}
		   }
       }
	       
	}
}
