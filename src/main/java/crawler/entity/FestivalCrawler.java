package crawler.entity;

import crawler.BaseWebCrawler;

import java.util.List;

public class FestivalCrawler extends BaseWebCrawler {
    public FestivalCrawler(List<String> urls) {
        super(urls);
    }

	@Override
	public boolean connect(String url) {
		// TODO Auto-generated method stub
		return false;
	}
}
