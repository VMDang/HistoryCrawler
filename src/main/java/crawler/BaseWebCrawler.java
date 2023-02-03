package crawler;

import java.util.List;

public abstract class BaseWebCrawler {
    protected String url;
    
    public BaseWebCrawler(String url) {
        this.url = url;
    }

	public void setUrls(String url) {
		this.url = url;
	}

	public abstract boolean connect(String url);
	
}
