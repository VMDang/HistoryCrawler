package crawler.manager;

import crawler.entity.character.DataAggregation;
import crawler.entity.character.KingCharacterCrawler;
import crawler.entity.character.NguoiKeSuCharacterCrawler;
import crawler.entity.character.VanSuCharacterCrawler;
import crawler.entity.dynasty.NguoiKeSuDynastyCrawler;
import crawler.entity.dynasty.WikiDynastyCrawler;
import crawler.entity.event.WikiEventCrawler;
import crawler.entity.festival.CoutureTravelFestivalCrawler;
import crawler.entity.festival.WikiFestivalCrawler;
import crawler.entity.relic.DsvhRelicCrawler;
import history.media.MainScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public void CrawlerToJson() throws IOException {
//        KingCharacterCrawler kingCrawler = new KingCharacterCrawler();
//        kingCrawler.start();
//
//        NguoiKeSuCharacterCrawler nguoiKeSuCharacterCrawler = new NguoiKeSuCharacterCrawler();
//        nguoiKeSuCharacterCrawler.start();
//
//        VanSuCharacterCrawler vanSuCharacterCrawler = new VanSuCharacterCrawler();
//        vanSuCharacterCrawler.start();
//
//        DataAggregation mergeCharacter = new DataAggregation();
//        mergeCharacter.Start();

        NguoiKeSuDynastyCrawler nguoiKeSuDynastyCrawler = new NguoiKeSuDynastyCrawler();
        nguoiKeSuDynastyCrawler.start();

        WikiDynastyCrawler wikiDynastyCrawler = new WikiDynastyCrawler();
        wikiDynastyCrawler.start();

        WikiEventCrawler wikiEventCrawler = new WikiEventCrawler();
        wikiEventCrawler.start();

        CoutureTravelFestivalCrawler coutureTravelFestivalCrawler = new CoutureTravelFestivalCrawler();
        coutureTravelFestivalCrawler.start();

        WikiFestivalCrawler wikiFestivalCrawler = new WikiFestivalCrawler();
        wikiFestivalCrawler.start();

        DsvhRelicCrawler dsvhRelicCrawler = new DsvhRelicCrawler();
        dsvhRelicCrawler.start();

        Map<String, String> baseWebList = CrawlerManager.getBaseWebList();
        final String[] showBaseWebList = {""};

        baseWebList.forEach((key, value) -> {
            if (key.contains("Relic")){
                showBaseWebList[0] += "Web Di tích     : " + value + "\n";
            }else if (key.contains("Festival")){
                showBaseWebList[0] += "Web Lễ hội      : " + value + "\n";
            } else if (key.contains("Dynasty")) {
                showBaseWebList[0] += "Web Triều đại   : " + value + "\n";
            } else if (key.contains("Character")) {
                showBaseWebList[0] += "Web Nhân vật    : " + value + "\n";
            } else if (key.contains("King")) {
                showBaseWebList[0] += "Web Vua         : " + value + "\n";
            }
        });

        Map<String, Integer> entityCrawed = CrawlerManager.getEntityCrawled();
        final String[] showEntityCrawed = {""};

        entityCrawed.forEach((key, value) -> {
            showEntityCrawed[0] += key + " : " + value.toString() +"\n";
        });

        System.out.println("Danh sách URL chính: \n" + showBaseWebList[0]);
        JOptionPane optionPane = new NarrowOptionPane();
        optionPane.setMessage("Danh sách URL chính: \n" + showBaseWebList[0]);
        optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
        JDialog dialog = optionPane.createDialog(null, "Tổng hợp dữ liệu");
        dialog.setVisible(true);

        JOptionPane.showMessageDialog(dialog,"Tổng số url đã duyệt qua: " + CrawlerManager.totalUrlBrowsed() + "\n");
        JOptionPane.showMessageDialog(dialog,"Tổng số đối tượng đã thu thập: \n" + showEntityCrawed[0]);

        return;
    }

    public void JsonToDisplay() throws IOException {
        MainScreen.run();

        Map<String, Integer> entityDisplay = CrawlerManager.getEntityDisplay();

        final String[] showEntityDisplay = {""};
        entityDisplay.forEach((key, value) -> {
            if (key.contains("Relic")){
                showEntityDisplay[0] += "Di tích     : " + value + "\n";
            }else if (key.contains("Festival")){
                showEntityDisplay[0] += "Lễ hội      : " + value + "\n";
            } else if (key.contains("Dynasty")) {
                showEntityDisplay[0] += "Triều đại   : " + value + "\n";
            } else if (key.contains("Character")) {
                showEntityDisplay[0] += "Nhân vật    : " + value + "\n";
            } else if (key.contains("King")) {
                showEntityDisplay[0] += "Vua         : " + value + "\n";
            }else if (key.contains("Event")) {
                showEntityDisplay[0] += "Sự kiện         : " + value + "\n";
            }
        });

        JOptionPane optionPane = new NarrowOptionPane();
        optionPane.setMessage("Tổng số đối tượng đã thu thập:\n" + showEntityDisplay[0]);
        optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
        JDialog dialog = optionPane.createDialog(null, "Tổng số dữ liệu hiển thị \n");
        dialog.setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();

        String[] options = { "Lấy dữ liệu", "Hiển thị dữ liệu" };
        int dessert = JOptionPane.showOptionDialog(null, "Chọn hành động", "Chọn một:",
                0, 3, null, options, options[0]);
        if (dessert == 0) {
            main.CrawlerToJson();

        }
        if (dessert == 1) {
            main.JsonToDisplay();
        }
    }

    class NarrowOptionPane extends JOptionPane {
        NarrowOptionPane() {
        }
    }
}
