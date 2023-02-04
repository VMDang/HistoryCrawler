package crawler.entity.dynasty;

import crawler.BaseWebCrawler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import history.entity.*;

public class WikiDynastyCrawler extends DynastyCrawler {
	private static Document doc = null;
	private static List<Dynasty> dynastyList = new ArrayList<>();
	
	public WikiDynastyCrawler(String url) {
        super(url);
    }

    public static Document getDoc() {
    	return doc;
    }
    
	@Override
	public boolean connect(String url) {
		try {
			WikiDynastyCrawler.doc = Jsoup.connect(url).get();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	@Override
	public void getData() {
		File theFile = new File("src\\main\\java\\json\\Dynasties_wiki.json");
		this.connect(url);
		String aaa = "Thủ đô";
		String bbb = "Chính phủ";
		List<String> linkTrieuDai = new ArrayList<>();
		
			this.connect(url);
			Elements table = doc.select("table[align*=right]");
			for(Element i : table.select("a")) 
				linkTrieuDai.add(i.attr("href"));
			
			for(int l = 0 ; l < linkTrieuDai.size() ; l++ ) {
				if(l==0||l==linkTrieuDai.size()-1) {
					continue;
				}
				else {
					Dynasty dk1 = new Dynasty();
					String link = ("https://vi.wikipedia.org" + linkTrieuDai.get(l));
					Connection webConnection = Jsoup.connect(link);
					Document data;
					try {
						data = webConnection.get();
						String title = data.getElementsByClass("mw-page-title-main").text();
						dk1.setName(title);
						
						Elements info = data.getElementsByClass("infobox").select("[style*=width:22em]");
						for (Element infoElement : info.select("[scope=row]")) {
							if(infoElement.text().equals(aaa)) {
								dk1.setCapital(infoElement.parent().select("td").text());
							}
							else if (infoElement.text().equals(bbb))
								dk1.setKingdom(infoElement.parent().select("td").text());
						}
						dynastyList.add(dk1);
					} catch (IOException e) {
						e.printStackTrace();
					}
				  }
			}
				try {
					FileWriter fileWriter = new FileWriter(theFile);
					Gson pretty_gs = new GsonBuilder().setPrettyPrinting().create();
					pretty_gs.toJson(dynastyList, fileWriter);
					fileWriter.close();
					
				} catch (IOException e){
					System.err.println("Error in writing a file.");
				}
	}
	
	@Override
	public void start() {
		String url = 
		getData();
	}
	
	
}