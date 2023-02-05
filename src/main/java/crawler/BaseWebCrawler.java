package crawler;

import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;

public abstract class BaseWebCrawler {
    protected String url;

    protected Document doc = null;

    public Document getDoc() {
        return this.doc;
    }

    public void setDoc(Document doc) {
        this.doc = doc;
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
