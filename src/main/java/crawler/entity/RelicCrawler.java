package crawler.entity;

import crawler.BaseWebCrawler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import history.entity.*;

public class RelicCrawler extends BaseWebCrawler {
	private static List<Relic> relicList = new ArrayList<Relic>();
	private static Document doc;

	public RelicCrawler(String url) {
		super(url);
		RelicCrawler.doc = null;
	}

	public static Document getDoc() {
		return doc;
	}

	public void setDoc(Document doc) {
		RelicCrawler.doc = doc;
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

	@Override
	public void start() throws IOException {
		String url = "http://dsvh.gov.vn/danh-muc-di-tich-quoc-gia-dac-biet-1752";
		RelicCrawler relicCrawler = new RelicCrawler(url);
		relicCrawler.connect(url);
		relicCrawler.getDataNoSubUrl(url);
		relicCrawler.getDataHaveSubUrl(url);
	}

	public List<String> getAllUrl(String url ){
		List<String> allUrl = new ArrayList<String>();
		this.connect(url);
		Document Doc1 = getDoc();
		Elements relicATag1 = Doc1.select("p[style = margin-left:5px; margin-right:8px] a");
		for(Element a : relicATag1) {
			String relicURL = "http://dsvh.gov.vn/"+ a.attr("href");
			if (relicURL.contentEquals("http://dsvh.gov.vn/http://di-tich-kien-truc-nghe-thuat-chua-keo-huyen-vu-thu-tinh-thai-binh-2964")){
				continue;
			}else {
				System.out.println(relicURL);
				allUrl.add(relicURL);
			}
		}
		return allUrl;
	}

	public void getDataNoSubUrl(String url) {
		this.connect(url);
		Document Doc = getDoc();
		Elements relics = Doc.select("p[style = margin-left:5px; margin-right:8px] > span");

		for (Element element: relics ) {
			Element tdTag = element.parent().parent();

			String relicName = tdTag.text();
			String relicCertifacte = tdTag.nextElementSibling().text();
			String relicProvice = tdTag.nextElementSibling().nextElementSibling().text();

			Relic dk = new Relic();
			dk.setName(relicName);
			dk.setTime("");
			dk.setCertifacte(relicCertifacte);
			dk.setProvince(relicProvice);
			dk.setDescription("");
			relicList.add(dk);
		}

	}

	public void getDataHaveSubUrl(String URL) {
		this.connect(URL);

		Document Doc = getDoc();
		Elements relics = Doc.select("p[style = margin-left:5px; margin-right:8px] > a");

		List<String> allUrl = getAllUrl(URL);

		File theFile = new File("src\\main\\java\\json\\Relic.json");
		for (String url : allUrl) {
			this.connect(url);
			Elements names = doc.select("div[class=page-content] h1[class=page-title]");
			Elements places = doc.select("div[class=description] p");
			Elements decriptions = doc.select("p[style=text-align:justify]");
			for (int i = 0; i < names.size(); i++) {
				Relic dk = new Relic();
				dk.setName(names.get(i).text());
				dk.setTime("");
				for(int j=0; j<decriptions.size(); j++) {
					dk.setDescription(decriptions.text());
				}
				dk.setPlace(places.text());
				for (Element element: relics ) {
					Element tdTag = element.parent().parent();

					String relicCertifacte = tdTag.nextElementSibling().text();
					String relicProvice = tdTag.nextElementSibling().nextElementSibling().text();
					dk.setCertifacte(relicCertifacte);
					dk.setProvince(relicProvice);
				}
				dk.setReference(url);

				relicList.add(dk);
			}
		}

		try {
			FileWriter fileWriter = new FileWriter(theFile);
			Gson pretty_gs = new GsonBuilder().setPrettyPrinting().create();
			pretty_gs.toJson(relicList, fileWriter);
			fileWriter.close();

		} catch (IOException e){
			System.err.println("Error in writing a file.");
		}
	}
	public static void main(String[] args) {
		String url = "http://dsvh.gov.vn/danh-muc-di-tich-quoc-gia-dac-biet-1752";
		RelicCrawler relicCrawler = new RelicCrawler(url);
		relicCrawler.connect(url);
		relicCrawler.getDataNoSubUrl(url);
		relicCrawler.getDataHaveSubUrl(url);

	}
}