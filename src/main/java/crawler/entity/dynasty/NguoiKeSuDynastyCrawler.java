package crawler.entity.dynasty;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import crawler.manager.CrawlerManager;
import history.entity.Dynasty;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class NguoiKeSuDynastyCrawler extends DynastyCrawler{
    public NguoiKeSuDynastyCrawler(String url) {
        super(url);
    }

    @Override
    public void getData() {
        File theFile = new File("src\\main\\java\\json\\Dynasties_NKS1.json");
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

    public NguoiKeSuDynastyCrawler() {
    }

    @Override
    public void start() {
        String url = "https://nguoikesu.com/dong-lich-su";
        NguoiKeSuDynastyCrawler dynasty = new NguoiKeSuDynastyCrawler(url);
        dynasty.getData();
        CrawlerManager.setBaseWebList("Dynasty_NKS", url);
        CrawlerManager.setEntityCrawled("Triều đại - Người kể sử", dynastyList.size());
    }

    public static void main(String[] args) {
        String url = "https://nguoikesu.com/dong-lich-su";
        NguoiKeSuDynastyCrawler dynasty = new NguoiKeSuDynastyCrawler(url);
        dynasty.getData();
    }
}
