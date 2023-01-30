package crawler.entity;

import crawler.BaseWebCrawler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import history.entity.*;

public class DynastyCrawler extends BaseWebCrawler {
	private static Document doc = null;
	private static List<Dynasty> dynastyList = new ArrayList<>();
	
    public DynastyCrawler(List<String> urls) {
        super(urls);
    }

    public static Document getDoc() {
    	return doc;
    }
    
    public String getUrlByIndex(int i) {
    	return urls.get(i);
    }
    
	@Override
	public boolean connect(String url) {
		try {
			DynastyCrawler.doc = Jsoup.connect(url).get();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public void getData_NguoiKeSu() {
		File theFile = new File("src\\main\\java\\json\\Dynasties_NKS.json");
		for (String url : urls) {
			this.connect(url);
			Elements names = doc.select("h3[class = item-title]	");
			Elements descriptions = doc.select("ul[class = issues] li div[class = inner] div");
			for (int i = 0; i < names.size(); i++) {
				Dynasty dk = new Dynasty();
				dk.setName(names.get(i).text());
				dk.setDescription(descriptions.get(i).text());
				dynastyList.add(dk);
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
	
	public static void main(String[] args) {
		List<String> urls = new ArrayList<String>();
		urls.add("https://nguoikesu.com/dong-lich-su");
		DynastyCrawler dynastyCrawler = new DynastyCrawler(urls);
		dynastyCrawler.getData_NguoiKeSu();
		
	}
	
}
