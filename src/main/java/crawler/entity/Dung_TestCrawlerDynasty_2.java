package crawler.entity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Dung_TestCrawlerDynasty_2 {

	public static void main(String[] args) {
		List<String> urls = new ArrayList<String>();
		urls.add("https://nguoikesu.com/dong-lich-su");
		DynastyCrawler dynastyCrawler = new DynastyCrawler(urls);
		
		File theFile = new File("DynastyCrawlerData.json");
		for (String url : urls) {
			dynastyCrawler.connect(url);
		}
		

	}

}
