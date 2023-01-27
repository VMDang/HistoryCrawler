package history.entity;

import history.History;

public class Festival extends History {
	private String nameFes;
	private String timeFes;
	private String placeFes;
		
	public Festival() {
		// TODO Auto-generated constructor stub
	}

	public Festival(String nameFes, String timeFes, String placeFes) {
		super();
		this.nameFes = nameFes;
		this.timeFes = timeFes;
		this.placeFes = placeFes;
	}

	public String getNameFes() {
		return nameFes;
	}

	public void setNameFes(String nameFes) {
		this.nameFes = nameFes;
	}

	public String getTimeFes() {
		return timeFes;
	}

	public void setTimeFes(String timeFes) {
		this.timeFes = timeFes;
	}

	public String getPlaceFes() {
		return placeFes;
	}

	public void setPlaceFes(String placeFes) {
		this.placeFes = placeFes;
	}
	
	
	
			
}
