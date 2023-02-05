package crawler.entity.dynasty;

import crawler.BaseWebCrawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import history.entity.Dynasty;

public abstract class DynastyCrawler extends BaseWebCrawler {
	protected static List<Dynasty> dynastyList = new ArrayList<>();

	public DynastyCrawler(String url) {
		super(url);
	}

	public static List<Dynasty> getDynastyList() {
		return dynastyList;
	}

	public static void setDynastyList(List<Dynasty> dynastyList) {
		DynastyCrawler.dynastyList = dynastyList;
	}

	public DynastyCrawler() {
	}

	@Override
	public boolean connect(String url) {
		try {
			Document dcm = Jsoup.connect(url).get();
			setDoc(dcm);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}

	public abstract void getData() ;
	public abstract void start();

}