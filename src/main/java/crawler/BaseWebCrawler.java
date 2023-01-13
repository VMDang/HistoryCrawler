package crawler;

import java.util.List;

public abstract class BaseWebCrawler {
    protected List<String> urls;

    public BaseWebCrawler(List<String> urls) {
        this.urls = urls;
    }
    public String getURL(int i) {
    	return urls.get(i);
    }
}
