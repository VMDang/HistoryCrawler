package crawler.manager;

import crawler.entity.FestivalCrawler;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FestivalCrawler.crawlerWiki("https://vi.wikipedia.org/wiki/L%E1%BB%85_h%E1%BB%99i_Vi%E1%BB%87t_Nam");
        FestivalCrawler.crawlerBlog("https://www.couturetravelcompany.com/cac-le-hoi-o-viet-nam/");
    }
}
