package crawler.entity.relic;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import crawler.manager.CrawlerManager;
import history.entity.Relic;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DsvhRelicCrawler extends RelicCrawler{
    public DsvhRelicCrawler(String url) {
        super(url);
    }

    @Override
    public List<String> getAllUrl(String url) {
        List<String> allUrl = new ArrayList<String>();
        this.connect(url);
        Document Doc1 = getDoc();
        Elements relicATag1 = Doc1.select("p[style = margin-left:5px; margin-right:8px] a");
        for(Element a : relicATag1) {
            String relicURL = "http://dsvh.gov.vn/"+ a.attr("href");
            if (relicURL.contentEquals("http://dsvh.gov.vn/http://di-tich-kien-truc-nghe-thuat-chua-keo-huyen-vu-thu-tinh-thai-binh-2964")){
                continue;
            }else {
//                System.out.println(relicURL);
                allUrl.add(relicURL);
                CrawlerManager.setCountUrlBrowsed();
            }
        }
        return allUrl;
    }

    public void getDataNoSubUrl() {
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

    public void getDataHaveSubUrl() {
        this.connect(url);

        Document Doc = getDoc();
        Elements relics = Doc.select("p[style = margin-left:5px; margin-right:8px] > a");

        List<String> allUrl = getAllUrl(url);

        File theFile = new File("src\\main\\java\\json\\Relic1.json");
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

    @Override
    public void getData() {
        this.getDataNoSubUrl();
        this.getDataHaveSubUrl();
    }

    public DsvhRelicCrawler() {
    }

    @Override
    public void start() throws IOException {
        String url = "http://dsvh.gov.vn/danh-muc-di-tich-quoc-gia-dac-biet-1752";
        DsvhRelicCrawler relicCrawler = new DsvhRelicCrawler(url);
        relicCrawler.getData();
        CrawlerManager.setBaseWebList("Relic_Dsvh", url);
        CrawlerManager.setEntityCrawled("Di tích - Dsvh", relicList.size());
    }

    public static void main(String[] args) {
        String url = "http://dsvh.gov.vn/danh-muc-di-tich-quoc-gia-dac-biet-1752";
        DsvhRelicCrawler relicCrawler = new DsvhRelicCrawler(url);
        relicCrawler.getData();
    }
}
