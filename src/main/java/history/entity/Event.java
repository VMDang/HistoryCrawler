package history.entity;

import crawler.manager.CrawlerManager;
import history.History;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

public class Event extends History {
	public Event() {
		
	}
	private Dynasty dynasty = new Dynasty();
	public Event(String name, String time, String description, Dynasty dynasty) {
		super(name, time, description);
		this.dynasty = dynasty;
	}
	public void setDynastyname(String dynastyName) {
		this.dynasty.setName(dynastyName);
	}
	@Override
	public List<Event> loadDataJson() throws IOException {
		Gson gson = new Gson();
		Reader reader = Files.newBufferedReader(Paths.get("src/main/java/json/event.json"));
		List<Event> dks = Arrays.asList(gson.fromJson(reader, Event[].class));

		CrawlerManager.setEntityDisplay("Event", dks.size());
		return dks;
	}
	public static void main(String[] args) throws IOException {
		Event ev1 = new Event();
		List<Event> events =  ev1.loadDataJson();
		for (Event event : events) {
			System.out.println(event.getName());
		}
	}
	@Override
	public String hienthi() {
		return  "Tên: " + this.getName() + "\n" + "Thời gian: " + this.getTime() + "\n" + "Triều đại: " + this.dynasty.getName() + "\n" + "Chi tiết: " + this.getDescription();
	}
}
