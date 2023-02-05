package history.media;

import javax.swing.JFrame;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.IconifyAction;

import history.History;
import history.entity.Character;
import history.entity.Dynasty;
import history.entity.Event;
import history.entity.Festival;
import history.entity.King;
import history.entity.Relic;
import store.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.transformation.FilteredList;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.input.MouseEvent;

public class MainScreenController {
    private Store store;
    private JFrame stage;
	public MainScreenController(Store store, JFrame stage) {
		this.store = store;
		this.stage = stage;
	}
    @FXML
    private TableView<History> tblLichSu;
    
    @FXML
    private Button btnSearch;

    @FXML
    private TableColumn<History, String> colDescription;

    @FXML
    private TableColumn<History, String> colName;

    @FXML
    private TableColumn<History, String> colTime;

    @FXML
    private TextField tfFilter;
    
    @FXML
    private Pane btnSuKien;

    @FXML
    private Pane btnTrieuDai;
    
    @FXML
    private Pane btnDiTich;

    @FXML
    private Pane btnLeHoi;

    @FXML
    private Pane btnNhanVat;
    
    @FXML
    private Pane btnVua;
    
    @FXML
    private Label categoryLabel;
    
    boolean sukienClicked = false;
    boolean trieudaiCliked = false;
    boolean ditichClicked = false;
    boolean lehoiClicked = false;
    boolean nhanvatClicked = false;
    boolean vuaClicked = false;
    
    @FXML
    void btnSearchPress(ActionEvent event) {
    	String filter = tfFilter.getText();
    	String filterType = "name";
    	FilteredList<History> list = new FilteredList<>(store.getItemHistory(),null);
    	list.setPredicate(history -> history.filterProperty(filter, filterType));
    	if(store.getItemHistory() != null) {
    		if(sukienClicked) {
    			tblLichSu.setItems(list.filtered(history -> history instanceof Event));
    		}
    		if(trieudaiCliked) {
    			tblLichSu.setItems(list.filtered(history -> history instanceof Dynasty));
    		}
    		if(ditichClicked) {
    			tblLichSu.setItems(list.filtered(history -> history instanceof Relic));
    		}
    		if(lehoiClicked) {
    			tblLichSu.setItems(list.filtered(history -> history instanceof Festival));
    		}
    		if(vuaClicked) {
    			tblLichSu.setItems(list.filtered(history -> history instanceof King));
    		}
    		if(nhanvatClicked) {
    			tblLichSu.setItems(list.filtered(history -> history instanceof Character));
    		}
    	}
    }
    
    @FXML
    private void initialize() {
    	colName.setCellValueFactory(new PropertyValueFactory<History, String>("Name"));
    	colTime.setCellValueFactory(new PropertyValueFactory<History, String>("Time"));
    	colDescription.setCellValueFactory(new PropertyValueFactory<History, String>("Description"));
    	tblLichSu.setItems(this.store.getItemHistory());
    	btnSuKien.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				tblLichSu.setItems(store.getItemHistory().filtered(history -> history instanceof Event));
			    sukienClicked = true;
			    trieudaiCliked = false;
			    ditichClicked = false;
			    lehoiClicked = false;
			    nhanvatClicked = false;
			    vuaClicked = false;
			    categoryLabel.setText("Sự Kiện Lịch Sử");
			}
		});
    	btnTrieuDai.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				tblLichSu.setItems(store.getItemHistory().filtered(history -> history instanceof Dynasty));
			    sukienClicked = false;
			    trieudaiCliked = true;
			    ditichClicked = false;
			    lehoiClicked = false;
			    nhanvatClicked = false;
			    vuaClicked = false;
			    categoryLabel.setText("Triều Đại Lịch Sử");
			}
		});
    	btnDiTich.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				tblLichSu.setItems(store.getItemHistory().filtered(history -> history instanceof Relic));
			    sukienClicked = false;
			    trieudaiCliked = false;
			    ditichClicked = true;
			    lehoiClicked = false;
			    nhanvatClicked = false;
			    vuaClicked = false;
			    categoryLabel.setText("Di Tích Lịch Sử");
			}
		});
    	btnLeHoi.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				tblLichSu.setItems(store.getItemHistory().filtered(history -> history instanceof Festival));
			    sukienClicked = false;
			    trieudaiCliked = false;
			    ditichClicked = false;
			    lehoiClicked = true;
			    nhanvatClicked = false;
			    vuaClicked = false;
			    categoryLabel.setText("Lễ Hội Việt Nam");
			}
		});
    	btnVua.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				tblLichSu.setItems(store.getItemHistory().filtered(history -> history instanceof King));
			    sukienClicked = false;
			    trieudaiCliked = false;
			    ditichClicked = false;
			    lehoiClicked = false;
			    nhanvatClicked = false;
			    vuaClicked = true;
			    categoryLabel.setText("Các Vị Vua");
			}
		});
    	btnNhanVat.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				tblLichSu.setItems(store.getItemHistory().filtered(history -> (history instanceof Character)));
			    sukienClicked = false;
			    trieudaiCliked = false;
			    ditichClicked = false;
			    lehoiClicked = false;
			    nhanvatClicked = true;
			    vuaClicked = false;
			    categoryLabel.setText("Nhân Vật Lịch Sử");
			}
		});
    	tblLichSu.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<History>() {

			@Override
			public void changed(ObservableValue<? extends History> arg0, History arg1, History arg2) {
		    	Alert alert = new Alert(Alert.AlertType.INFORMATION);
		    	alert.setHeaderText("Chi tiết về thực thể lịch sử");
		    	TextArea area = new TextArea(arg2.hienthi());
		    	area.setEditable(false);
		    	area.setWrapText(true);
		    	alert.setResizable(true);
		    	alert.getDialogPane().setContent(area);
		    	alert.getDialogPane().setPrefSize(1000, 500);
		    	alert.showAndWait();
			}
		});
    }
    
}