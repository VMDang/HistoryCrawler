package history.entity;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

import crawler.manager.CrawlerManager;
import history.History;

public class Character extends History {
	private String aotherName;
	private String place;
	private List<String> era ;
	public Character() {
		super();
		this.aotherName = null;
		this.place = null;
		this.era = null;
	}
	public String getAotherName() {
		return aotherName;
	}
	public String getPlace() {
		return place;
	}
	public List<String> getEra() {
		return era;
	}
	public Character (String name, String time, String description) {
		super(name,time,description);
		this.aotherName = null;
		this.place = null;
		this.era = null;
	}

	@Override
	public List<Character> loadDataJson() throws IOException {
		Gson gson = new Gson();
		Reader reader = Files.newBufferedReader(Paths.get("src/main/java/json/character.json"));
		List<Character> dks = Arrays.asList(gson.fromJson(reader, Character[].class));
		reader.close();

		CrawlerManager.setEntityDisplay("Character", dks.size());


		return dks;
	}

	public Character (String name, String time, String description,String aotherName,String place,List<String> era) {
		super(name,time,description);
		this.aotherName = aotherName;
		this.place = place;
		this.era = era;
	}
	public void setAotherName(String aotherName) {
		if(aotherName!=null)this.aotherName = aotherName;
		else {
			this.aotherName = "Không có";
		}
	}
	public void setPlace(String place) {
		if(place != null) this.place = place;
		else this.place = "Không rõ";
	}
	public void setEra(List<String> era) {
		this.era = era;
	}
	@Override
	public String hienthi() {
		return "Tên: " + this.getName() + "\n" + "Năm sinh - năm mất: " + this.getTime() + "\n" + "Tên khác: " + this.getAotherName() + "\n" + "Quê quán: " + this.getPlace() + "\n" + "Thời: " + this.getEra().toString()
				+ "\n" + "Chi tiết: " + this.getDescription();
	}
}
