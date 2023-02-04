package crawler.entity.relic;

import crawler.BaseWebCrawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import history.entity.*;

public abstract class RelicCrawler extends BaseWebCrawler {
	protected static Document doc = null;
	protected static List<Relic> relicList = new ArrayList<Relic>();

	public RelicCrawler(String url) {
		super(url);
		RelicCrawler.doc = null;
	}

	public static Document getDoc() {
		return doc;
	}

	public void setDoc(Document doc) {
		RelicCrawler.doc = doc;
	}

	public RelicCrawler() {
	}

	@Override
	public boolean connect(String url) {
		Document document = null;
		try {
			document = Jsoup
					.connect(url)
					.userAgent("Jsoup client")
					.timeout(30000).get();
			setDoc(document);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public abstract List<String> getAllUrl(String url);

	public abstract void getData();
}