package crawler.manager;

import history.entity.Festival;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
//        RelicCrawler relicCrawler = new RelicCrawler("http://dsvh.gov.vn/danh-muc-di-tich-quoc-gia-dac-biet-1752");
//        relicCrawler.start();

        Festival festival = new Festival();
        List<Festival> festivals = festival.loadDataJson();

        for (Festival fes:
             festivals) {
            System.out.println(fes.getName() + " - " + fes.getTime());
        }
    }
}
