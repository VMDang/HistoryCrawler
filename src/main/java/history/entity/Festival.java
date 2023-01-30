package history.entity;

import history.History;

public class Festival  {
	private String name;
	private String time;
	private String place;
	private String firstTime;
	private String character;
	
	public Festival() {
		
	}
	
	public Festival(String name, String time, String place, String firstTime, String character) {
		super();
		this.name = name;
		this.time = time;
		this.place = place;
		this.firstTime = firstTime;
		this.character = character;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getFirstTime() {
		return firstTime;
	}
	public void setFirstTime(String firstTime) {
		this.firstTime = firstTime;
	}
	public String getCharacter() {
		return character;
	}
	public void setCharacter(String character) {
		this.character = character;
	}
	
	
	
	
	
	
		
}
