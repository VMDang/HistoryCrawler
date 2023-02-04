package history.entity;

import history.History;

import java.util.List;

public class Dynasty extends History {
	
	private String kingdom;
	private String capital;
	
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


	@Override
	public List<Dynasty> loadDataJson() {
		return null;
	}
}
