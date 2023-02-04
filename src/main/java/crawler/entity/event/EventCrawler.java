package crawler.entity.event;

import crawler.BaseWebCrawler;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public abstract class EventCrawler extends BaseWebCrawler {
	protected static Document doc = null;
    public EventCrawler(String url) {
        super(url);
    }

	public static Document getDoc() {
		return doc;
	}

	public static void setDoc(Document doc) {
		EventCrawler.doc = doc;
	}

	public EventCrawler() {
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

	public abstract void getData() throws IOException;
	public abstract void start() throws IOException;

}
