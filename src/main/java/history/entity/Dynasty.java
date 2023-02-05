package history.entity;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import crawler.manager.CrawlerManager;
import history.History;

import java.util.List;

import com.google.gson.Gson;

public class Dynasty extends History {
	
	private String kingdom;
	private String capital;
	private ArrayList<String> king;
	
	public String getKingdom() {
		return kingdom;
	}
	public void setKingdom(String kingdom) {
		this.kingdom = kingdom;
	}
	public String getCapital() {
		return capital;
	}
	public void setCapital(String capital) {
		this.capital = capital;
	}
	public ArrayList<String> getKing() {
		return king;
	}
	public void setKing(ArrayList<String> king) {
		this.king = king;
	}

	@Override
	public List<Dynasty> loadDataJson() throws IOException {
		Gson gson = new Gson();
		Reader reader = Files.newBufferedReader(Paths.get("src/main/java/json/Dynasties_NKS.json"));
		List<Dynasty> dks = Arrays.asList(gson.fromJson(reader, Dynasty[].class));

		CrawlerManager.setEntityDisplay("Dynasty", dks.size());

		return dks;
	}
	@Override
	public String hienthi() {
		return "Tên: " + this.getName() + "\n" + "Thời gian: " + this.getTime() + "\n" + "Tên nước: " + this.getKingdom() + "\n" + "Thủ đô: " + this.getCapital() + "\n" + "các đời vua: " + this.getKing().toString()
				+ "\n" +  "Chi tiết: " + this.getDescription();
	}
}
