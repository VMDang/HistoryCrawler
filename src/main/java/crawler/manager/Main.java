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
                showBaseWebList[0] += "Web Di t??ch     : " + value + "\n";
            }else if (key.contains("Festival")){
                showBaseWebList[0] += "Web L??? h???i      : " + value + "\n";
            } else if (key.contains("Dynasty")) {
                showBaseWebList[0] += "Web Tri???u ?????i   : " + value + "\n";
            } else if (key.contains("Character")) {
                showBaseWebList[0] += "Web Nh??n v???t    : " + value + "\n";
            } else if (key.contains("King")) {
                showBaseWebList[0] += "Web Vua         : " + value + "\n";
            }
        });

        Map<String, Integer> entityCrawed = CrawlerManager.getEntityCrawled();
        final String[] showEntityCrawed = {""};

        entityCrawed.forEach((key, value) -> {
            showEntityCrawed[0] += key + " : " + value.toString() +"\n";
        });

        System.out.println("Danh s??ch URL ch??nh: \n" + showBaseWebList[0]);
        JOptionPane optionPane = new NarrowOptionPane();
        optionPane.setMessage("Danh s??ch URL ch??nh: \n" + showBaseWebList[0]);
        optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
        JDialog dialog = optionPane.createDialog(null, "T???ng h???p d??? li???u");
        dialog.setVisible(true);

        JOptionPane.showMessageDialog(dialog,"T???ng s??? url ???? duy???t qua: " + CrawlerManager.totalUrlBrowsed() + "\n");
        JOptionPane.showMessageDialog(dialog,"T???ng s??? ?????i t?????ng ???? thu th???p: \n" + showEntityCrawed[0]);

        return;
    }

    public void JsonToDisplay() throws IOException {
        MainScreen.run();

        Map<String, Integer> entityDisplay = CrawlerManager.getEntityDisplay();

        final String[] showEntityDisplay = {""};
        entityDisplay.forEach((key, value) -> {
            if (key.contains("Relic")){
                showEntityDisplay[0] += "Di t??ch     : " + value + "\n";
            }else if (key.contains("Festival")){
                showEntityDisplay[0] += "L??? h???i      : " + value + "\n";
            } else if (key.contains("Dynasty")) {
                showEntityDisplay[0] += "Tri???u ?????i   : " + value + "\n";
            } else if (key.contains("Character")) {
                showEntityDisplay[0] += "Nh??n v???t    : " + value + "\n";
            } else if (key.contains("King")) {
                showEntityDisplay[0] += "Vua         : " + value + "\n";
            }else if (key.contains("Event")) {
                showEntityDisplay[0] += "S??? ki???n         : " + value + "\n";
            }
        });

        JOptionPane optionPane = new NarrowOptionPane();
        optionPane.setMessage("T???ng s??? ?????i t?????ng ???? thu th???p:\n" + showEntityDisplay[0]);
        optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
        JDialog dialog = optionPane.createDialog(null, "T???ng s??? d??? li???u hi???n th??? \n");
        dialog.setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();

        String[] options = { "L???y d??? li???u", "Hi???n th??? d??? li???u" };
        int dessert = JOptionPane.showOptionDialog(null, "Ch???n h??nh ?????ng", "Ch???n m???t:",
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
