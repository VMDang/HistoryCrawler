package crawler.entity.festival;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import crawler.manager.CrawlerManager;
import history.entity.Festival;
import org.jsoup.nodes.Element;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CoutureTravelFestivalCrawler extends FestivalCrawler{
    public CoutureTravelFestivalCrawler(String url) {
        super(url);
    }

    @Override
    public void getData() {
        Festival[] festival = new Festival[300];
        ArrayList<String> listName = new ArrayList<String>();
        ArrayList<String> listTime = new ArrayList<String>();
        ArrayList<String> listDescription = new ArrayList<String>();
        ArrayList<String> listAddress = new ArrayList<String>();

        this.connect(url);

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
        try (FileWriter writer = new FileWriter("src\\main\\java\\json\\festival_Couture1.json", true)){
            CrawlerManager.setEntityCrawled("Lễ hội - Couture", listName.size());
            for(int i=0;i<listName.size();i++) {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                gson.toJson(festival[i], writer);
                writer.write(",\n");
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public CoutureTravelFestivalCrawler() {
    }

    @Override
    public void start() {
        String url = "https://www.couturetravelcompany.com/cac-le-hoi-o-viet-nam/";
        CoutureTravelFestivalCrawler festivalCrawler = new CoutureTravelFestivalCrawler(url);
        festivalCrawler.getData();
        CrawlerManager.setBaseWebList("Festival_Couture", url);
    }

    public static void main(String[] args) {
        String url = "https://www.couturetravelcompany.com/cac-le-hoi-o-viet-nam/";
        CoutureTravelFestivalCrawler festivalCrawler = new CoutureTravelFestivalCrawler(url);
        festivalCrawler.getData();
    }
}
