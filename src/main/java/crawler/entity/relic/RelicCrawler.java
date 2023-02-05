package crawler.entity.relic;

import crawler.BaseWebCrawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import history.entity.*;

public abstract class RelicCrawler extends BaseWebCrawler {
	protected static List<Relic> relicList = new ArrayList<Relic>();

	public RelicCrawler(String url) {
		super(url);
		this.doc = null;
	}

	public RelicCrawler() {
	}

	@Override
	public boolean connect(String url) {
		Document document = null;
		try {
			document = Jsoup
					.connect(url)
					.userAgent("Jsoup client")
					.timeout(30000).get();
			setDoc(document);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public abstract List<String> getAllUrl(String url);

	public abstract void getData();
}