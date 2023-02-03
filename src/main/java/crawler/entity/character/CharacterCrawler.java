package crawler.entity.character;

import crawler.BaseWebCrawler;
import history.entity.Character;

import java.io.IOException;
import java.io.Writer;
import java.io.FileWriter;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.*;

public abstract class CharacterCrawler extends BaseWebCrawler {
	private static Document doc;
    public CharacterCrawler(String url) {
        super(url);
        CharacterCrawler.doc = null;
    }
    
	public static Document getDoc() {
		return doc;
	}

	public void setDoc(Document doc) {
		CharacterCrawler.doc = doc;
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
