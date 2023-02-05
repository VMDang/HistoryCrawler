package crawler.entity.character;

import crawler.BaseWebCrawler;


import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


import java.util.*;

public abstract class CharacterCrawler extends BaseWebCrawler {
    public CharacterCrawler(String url) {
        super(url);
        this.doc = null;
    }

	public CharacterCrawler() {
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
	
	public abstract List<String> getAllUrl(String url);
	public abstract void getData(List<String> allUrl) ;
	public abstract void start();

}
