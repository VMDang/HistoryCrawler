package crawler.entity;

import crawler.BaseWebCrawler;

import java.util.List;

public class EventCrawler extends BaseWebCrawler {
    public EventCrawler(List<String> urls) {
        super(urls);
    }

	@Override
	public boolean connect(String url) {
		// TODO Auto-generated method stub
		return false;
	}
}
