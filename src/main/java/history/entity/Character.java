package history.entity;

import java.util.List;

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
	public Character (String name, String time, String description) {
		super(name,time,description);
		this.aotherName = null;
		this.place = null;
		this.era = null;
	}

	@Override
	public List<Character> loadDataJson() {
		return null;
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
	
}
