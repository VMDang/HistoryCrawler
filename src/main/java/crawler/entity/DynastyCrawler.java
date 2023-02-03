package crawler.entity;

import crawler.BaseWebCrawler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import history.entity.*;

public class DynastyCrawler extends BaseWebCrawler {
	private static Document doc = null;
	private static List<Dynasty> dynastyList = new ArrayList<>();

	public DynastyCrawler(String url) {
		super(url);
	}

	public static Document getDoc() {
		return doc;
	}

	@Override
	public boolean connect(String url) {
		try {
			DynastyCrawler.doc = Jsoup.connect(url).get();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}

	public void getData_NguoiKeSu() {
		File theFile = new File("src\\main\\java\\json\\Dynasties_NKS_ChangeBaseWeb.json");
		this.connect(url);
		Elements names = doc.select("h3[class = item-title]	");
		Elements descriptions = doc.select("ul[class = issues] li div[class = inner] div");
		for (int i = 0; i < names.size(); i++) {
			Dynasty dk = new Dynasty();
			dk.setName(names.get(i).text());
			dk.setDescription(descriptions.get(i).text());
			dynastyList.add(dk);
		}

		try {
			FileWriter fileWriter = new FileWriter(theFile);
			Gson pretty_gs = new GsonBuilder().setPrettyPrinting().create();
			pretty_gs.toJson(dynastyList, fileWriter);
			fileWriter.close();

		} catch (IOException e){
			System.err.println("Error in writing a file.");
		}
	}

	@Override
	public void start() {
		getData_NguoiKeSu();
	}

}