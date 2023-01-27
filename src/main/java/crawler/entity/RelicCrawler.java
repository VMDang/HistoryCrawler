package crawler.entity;

import crawler.BaseWebCrawler;

import java.util.List;

public class RelicCrawler extends BaseWebCrawler {
    public RelicCrawler(List<String> urls) {
        super(urls);
    }

	@Override
	public boolean connect(String url) {
		// TODO Auto-generated method stub
		return false;
	}
}
