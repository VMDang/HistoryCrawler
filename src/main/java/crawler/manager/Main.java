package crawler.manager;

import crawler.entity.character.KingCharacterCrawler;
import crawler.entity.character.NguoiKeSuCharacterCrawler;
import crawler.entity.character.VanSuCharacterCrawler;
import crawler.entity.dynasty.NguoiKeSuDynastyCrawler;
import crawler.entity.event.WikiEventCrawler;
import crawler.entity.festival.CoutureTravelFestivalCrawler;
import crawler.entity.festival.WikiFestivalCrawler;
import crawler.entity.relic.DsvhRelicCrawler;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        KingCharacterCrawler kingCrawler = new KingCharacterCrawler();
        kingCrawler.start();

        NguoiKeSuCharacterCrawler nguoiKeSuCharacterCrawler = new NguoiKeSuCharacterCrawler();
        nguoiKeSuCharacterCrawler.start();

        VanSuCharacterCrawler vanSuCharacterCrawler = new VanSuCharacterCrawler();
        vanSuCharacterCrawler.start();

        NguoiKeSuDynastyCrawler nguoiKeSuDynastyCrawler = new NguoiKeSuDynastyCrawler();
        nguoiKeSuDynastyCrawler.start();

        WikiEventCrawler wikiEventCrawler = new WikiEventCrawler();
        wikiEventCrawler.start();

        CoutureTravelFestivalCrawler coutureTravelFestivalCrawler = new CoutureTravelFestivalCrawler();
        coutureTravelFestivalCrawler.start();

        WikiFestivalCrawler wikiFestivalCrawler = new WikiFestivalCrawler();
        wikiFestivalCrawler.start();

        DsvhRelicCrawler dsvhRelicCrawler = new DsvhRelicCrawler();
        dsvhRelicCrawler.start();
    }
}
