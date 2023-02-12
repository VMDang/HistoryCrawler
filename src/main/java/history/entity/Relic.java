package history.entity;

import com.google.gson.Gson;
import crawler.manager.CrawlerManager;
import history.History;

import java.io.IOException;
import java.io.Reader;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Relic extends History {
    private String place;
    private String province;

    private String certifacte;

    private String reference;

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getCertifacte() {
        return certifacte;
    }

    public void setCertifacte(String certifacte) {
        this.certifacte = certifacte;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Override
    public List<Relic> loadDataJson() throws IOException {
        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get("src/main/java/json/Relic.json"));
        List<Relic> relics = Arrays.asList(gson.fromJson(reader, Relic[].class));

        CrawlerManager.setEntityDisplay("Relic", relics.size());

        return relics;
    }
    @Override
    public String hienthi() {
		return "Tên: " + this.getName() + "\n" + "Tỉnh:" + this.getProvince() + "\n" + "Chứng nhận: " + this.getCertifacte() + "\n" + "Chi tiết: " + this.getDescription();
	}
   
}