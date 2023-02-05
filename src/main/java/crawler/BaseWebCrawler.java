package crawler;

import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;

public abstract class BaseWebCrawler {
    protected String url;

    protected static Document doc = null;

    public static Document getDoc() {
        return doc;
    }

    public static void setDoc(Document doc) {
        BaseWebCrawler.doc = doc;
    }

    public BaseWebCrawler() {};
    
    public BaseWebCrawler(String url) {
        this.url = url;
    }

	public void setUrls(String url) {
		this.url = url;
	}

	public abstract boolean connect(String url);

    public abstract void start() throws IOException;
}