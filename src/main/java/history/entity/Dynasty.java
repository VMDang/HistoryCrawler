package history.entity;

import java.util.ArrayList;

import history.History;

import java.util.List;

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
	public List<Dynasty> loadDataJson() {
		return null;
	}
}
