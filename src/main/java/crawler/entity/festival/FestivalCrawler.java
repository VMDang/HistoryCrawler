package crawler.entity.festival;

import crawler.BaseWebCrawler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public abstract class FestivalCrawler extends BaseWebCrawler {
	protected static Document doc = null;
	public FestivalCrawler(String url) {
		super(url);
	}

	public static Document getDoc() {
		return doc;
	}

	public static void setDoc(Document doc) {
		FestivalCrawler.doc = doc;
	}

	public FestivalCrawler() {
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

	public abstract void getData() ;
	public abstract void start();
}