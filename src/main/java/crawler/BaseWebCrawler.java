package crawler;

import java.util.List;

public abstract class BaseWebCrawler {
    protected List<String> urls;
    
    public BaseWebCrawler(List<String> urls) {
        this.urls = urls;
    }

	public void setUrls(List<String> urls) {
		this.urls = urls;
	}

	public abstract boolean connect(String url);
	public abstract void start();
}
