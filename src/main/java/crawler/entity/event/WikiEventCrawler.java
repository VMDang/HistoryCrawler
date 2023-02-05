package crawler.entity.event;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;



import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import history.entity.Event;
public class WikiEventCrawler extends EventCrawler{
    public WikiEventCrawler(String url) {
        super(url);
    }

    public WikiEventCrawler() {
    }

    @Override
    public void getData() throws IOException {
        Event event = new Event();
        this.connect(url);
        String title = doc.title();
        System.out.println("Title : " + title);
        Elements itemsElements = doc.select("div[class=mw-parser-output]");
        for (Element element : itemsElements) {
            Elements e1 = element.getElementsByTag("p");
            for (Element element2 : e1) {
                if(element2.nextElementSibling() != null) {
                    if(element2.nextElementSibling().tagName() == "dl") {
                        event.setName(element2.text()+ " " + element2.nextElementSibling().text());
                        event.setTime(element2.getElementsByTag("b").text() + " " + element2.nextElementSibling().getElementsByTag("b").text());
                        String hrefString = element2.nextElementSibling().getElementsByTag("a").attr("href");
                        Elements eleDocument = Jsoup.connect("https://vi.wikipedia.org/"+hrefString).get().select("div[class=mw-parser-output]");
                        event.setDescription(eleDocument.select("p").get(0).text());
                    }
                    else {
                        event.setName(element2.text());
                        event.setTime(element2.getElementsByTag("b").text());
                        if(!element2.getElementsByTag("a").attr("class").equals("new")) {
                            String hrefString = element2.getElementsByTag("a").attr("href");
                            Elements eleDocument = Jsoup.connect("https://vi.wikipedia.org/"+hrefString).get().select("div[class=mw-parser-output]");
                            event.setDescription(eleDocument.select("p").get(0).text());
                        }
                    }
                }
                try (FileWriter writer = new FileWriter("src\\main\\java\\json\\event1.json", true)) {
                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    gson.toJson(event, writer);
                    writer.write(",\n");
                }
                catch(IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void start() throws IOException {
        String url = "https://vi.wikipedia.org/wiki/Ni%C3%AAn_bi%E1%BB%83u_l%E1%BB%8Bch_s%E1%BB%AD_Vi%E1%BB%87t_Nam";
        EventCrawler eventCrawler = new WikiEventCrawler(url);
        eventCrawler.getData();
    }

    public static void main(String[] args) throws IOException {
        String url = "https://vi.wikipedia.org/wiki/Ni%C3%AAn_bi%E1%BB%83u_l%E1%BB%8Bch_s%E1%BB%AD_Vi%E1%BB%87t_Nam";
        EventCrawler eventCrawler = new WikiEventCrawler(url);
        eventCrawler.connect(url);
        eventCrawler.getData();
    }
}
