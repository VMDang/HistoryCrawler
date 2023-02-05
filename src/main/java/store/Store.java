package store;

import history.History;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Store {

	private ObservableList<History> itemHistory = FXCollections.observableArrayList();
	
	public ObservableList<History> getItemHistory() {
		return itemHistory;
	}
	
	public void addHistory(History history) {
		
		itemHistory.add(history);
	}
}
