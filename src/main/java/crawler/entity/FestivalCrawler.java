package crawler.entity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import crawler.BaseWebCrawler;
import history.entity.Festival;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
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

	public static void crawlerWiki(String url) throws IOException {
		Festival[] festival = new Festival[300];
		ArrayList<String> listName = new ArrayList<String>();
		ArrayList<String> listTime = new ArrayList<String>();
		ArrayList<String> listFirstHeld = new ArrayList<String>();
		ArrayList<String> listCharacter = new ArrayList<String>();
		ArrayList<String> listPlace = new ArrayList<String>();


		Document doc = Jsoup.connect(url).get();
		Elements elements = doc.select("table.prettytable > tbody > tr ");
		for (Element row : elements) {
			String timeFes = row.select("td:nth-of-type(1)").text();
			if (timeFes.length() == 0) {
				timeFes = null;
			}
			listTime.add(timeFes);

			String placeFes = row.select("td:nth-of-type(2)").text();
			if (placeFes.length() == 0) {
				placeFes = null;
			}
			listPlace.add(placeFes);

			String nameFes = row.select("td:nth-of-type(3)").text();
			if (nameFes.length() == 0) {
				nameFes = null;
			}
			listName.add(nameFes);

			String firstHeld = row.select("td:nth-of-type(4)").text();
			if (firstHeld.length() == 0) {
				firstHeld = null;
			}
			listFirstHeld.add(firstHeld);

			String character = row.select("td:nth-of-type(5)").text();
			if (character.length() == 0) {
				character = null;
			}
			listCharacter.add(character);

		}

		for (int i = 1; i < listTime.size(); i++) {
			festival[i] = new Festival();
			festival[i].setTime(listTime.get(i));
			festival[i].setName(listName.get(i));
			festival[i].setPlace(listPlace.get(i));
			festival[i].setFirstTime(listFirstHeld.get(i));
			festival[i].setCharacter(listCharacter.get(i));
		}

		try(FileWriter writer = new FileWriter("src\\main\\java\\json\\festival_Wiki.json", true)) {
			for (int i = 1; i < listTime.size(); i++) {
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				gson.toJson(festival[i], writer);
				writer.write(",\n");
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void crawlerBlog(String url) throws IOException {
		Festival[] festival = new Festival[300];
		ArrayList<String> listName = new ArrayList<String>();
		ArrayList<String> listTime = new ArrayList<String>();
		ArrayList<String> listDescription = new ArrayList<String>();
		ArrayList<String> listAddress = new ArrayList<String>();

		Document doc = Jsoup.connect(url).get() ;

		for(Element row : doc.getElementsByTag("h4")) {
			listName.add(row.text());

		}
		listName.remove(listName.size()-1);


		for(Element row : doc.select("li:matches(Thời gian:)")) {
			listTime.add(row.text().substring(11));
		}

		for(Element row : doc.select("li:matches(Địa điểm:)")) {
			listAddress.add(row.text().substring(10));

		}

		for (Element row : doc.select("p")) {
			listDescription.add(row.text());
		}

		for(int i=0;i<listName.size();i++) {
			festival[i] = new Festival();
			festival[i].setName(listName.get(i));
			festival[i].setTime(listTime.get(i));
			festival[i].setPlace(listAddress.get(i));
			festival[i].setDescription(listDescription.get(i));
		}
		try (FileWriter writer = new FileWriter("src\\main\\java\\json\\festival_Blog.json", true)){
			for(int i=0;i<listName.size();i++) {
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				gson.toJson(festival[i], writer);
				writer.write(",\n");
			}
		}catch (IOException e){
			e.printStackTrace();
		}

	}
	public static void main(String[] args) throws IOException {
		FestivalCrawler.crawlerWiki("https://vi.wikipedia.org/wiki/L%E1%BB%85_h%E1%BB%99i_Vi%E1%BB%87t_Nam");
		FestivalCrawler.crawlerBlog("https://www.couturetravelcompany.com/cac-le-hoi-o-viet-nam/");
	}
}