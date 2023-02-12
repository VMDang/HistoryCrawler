package history.media;

import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;

import history.entity.*;
import history.entity.Character;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import store.Store;

public class MainScreen extends JFrame {
	private Store store;
	public MainScreen(Store store) {
		super();
		this.store = store;
		JFXPanel fxPanel = new JFXPanel();
		this.add(fxPanel);
		this.setTitle("main");
		setVisible(true);
		JFrame frame = this;
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("mainScreen.fxml"));
					MainScreenController controller = new MainScreenController(store, frame);
					loader.setController(controller);
					Parent root = loader.load();
					fxPanel.setScene(new Scene(root, 1024, 768));
					fxPanel.setSize(1024, 768);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void run() throws IOException {
		Store store = new Store();
		Event event1 = new Event();
		List<Event> events =  event1.loadDataJson();
		for (Event event : events) {
			store.addHistory(event);
		}
		Dynasty dynasty1 = new Dynasty();
		List<Dynasty> dynasties =  dynasty1.loadDataJson();
		for (Dynasty dynasty : dynasties) {
			store.addHistory(dynasty);
		}
		Relic relic1 = new Relic();
		List<Relic> relics = relic1.loadDataJson();
		for (Relic relic : relics) {
			store.addHistory(relic);
		}
		Festival festival1 = new Festival();
		List<Festival> festivals = festival1.loadDataJson();
		for (Festival festival : festivals) {
			store.addHistory(festival);
		}
		King king1 = new King();
		List<King> kings = king1.loadDataJson();
		for (King king : kings) {
			store.addHistory(king);
		}
		Character character1 = new Character();
		List<Character> characters = character1.loadDataJson();
		for (Character character : characters) {
			store.addHistory(character);
		}
		new MainScreen(store);
	}
	public static void main(String[] args) throws IOException {
		Store store = new Store();
		Event event1 = new Event();
		List<Event> events =  event1.loadDataJson();
		for (Event event : events) {
			store.addHistory(event);
		}
		Dynasty dynasty1 = new Dynasty();
		List<Dynasty> dynasties =  dynasty1.loadDataJson();
		for (Dynasty dynasty : dynasties) {
			store.addHistory(dynasty);
		}
		Relic relic1 = new Relic();
		List<Relic> relics = relic1.loadDataJson();
		for (Relic relic : relics) {
			store.addHistory(relic);
		}
		Festival festival1 = new Festival();
		List<Festival> festivals = festival1.loadDataJson();
		for (Festival festival : festivals) {
			store.addHistory(festival);
		}
		King king1 = new King();
		List<King> kings = king1.loadDataJson();
		for (King king : kings) {
			store.addHistory(king);
		}
		Character character1 = new Character();
		List<Character> characters = character1.loadDataJson();
		for (Character character : characters) {
			store.addHistory(character);
		}
		new MainScreen(store);
	}
}
