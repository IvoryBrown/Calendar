package application.disposition.controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.disposition.database.DispositionDataBase;
import application.disposition.pojo.Doctor;
import application.disposition.setting.ColorToHex;
import application.disposition.setting.LabelCSS;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class DispositionController implements Initializable {

	@FXML
	private ColorPicker doctorTextColor, doctorBackgroundColor;
	private String hexDoctorTextColor, hexDoctorBackgroundColor;
	@FXML
	private Label testLabel, showInfoLbl;
	@FXML
	private TextField doctorNameTxt;
	@FXML
	private Button newDoctorSave;
	@FXML
	private TableView<Doctor> staffDoctorTView;
	private TableColumn<Doctor, String> doctorNameColumn, doctorStatusColumn;
	private DispositionDataBase db = new DispositionDataBase();
	private LabelCSS labelCSS = new LabelCSS();
	private final ObservableList<Doctor> dataDoctor = FXCollections.observableArrayList();

	private void newDoctorSave() {
		newDoctorSave.setOnAction(evt -> {
			if (checkNewDoctorText()) {
				showInfoLbl.setText("Biztos, hogy menteni szeretnéd? 'I/N'");
				showInfoLbl.setStyle(labelCSS.infoCSS());
				newDoctorSave.setOnKeyPressed(new EventHandler<KeyEvent>() {
					@Override
					public void handle(KeyEvent ke) {
						if (ke.getCode().equals(KeyCode.I)) {
							Doctor newDoctor = new Doctor(doctorNameTxt.getText(), hexDoctorBackgroundColor,
									hexDoctorTextColor, "Igen");
							db.addNewDoctor(newDoctor);
							doctorNameTxt.clear();
							showInfoLbl.setText("Sikeres Mentés: Új Orvos");
							showInfoLbl.setStyle(labelCSS.goodCSS());
							setdataClearTable();
						}
						if (ke.getCode().equals(KeyCode.N)) {
							doctorNameTxt.clear();
							showInfoLbl.setText(null);
							showInfoLbl.setStyle(null);
						}
					}
				});
			} else {
				showInfoLbl.setText("Sikertelen Mentés: Új Orvos");
				showInfoLbl.setStyle(labelCSS.errorCSS());
			}
		});
	}

	private boolean checkNewDoctorText() {
		if (doctorNameTxt.getText().trim().isEmpty()) {
			doctorNameTxt.setStyle(labelCSS.errorCSS());
			return false;
		} else {
			doctorNameTxt.setStyle(null);
			return true;
		}
	}

	private void testTextColor() {
		doctorTextColor.setOnAction((e) -> {
			testLabel.setStyle("-fx-text-fill:" + ColorToHex.toRGBCode(doctorTextColor.getValue())
					+ "; -fx-background-color:" + ColorToHex.toRGBCode(doctorBackgroundColor.getValue()));
			hexDoctorTextColor = ColorToHex.toRGBCode(doctorTextColor.getValue());
		});
		doctorBackgroundColor.setOnAction((e) -> {
			testLabel.setStyle("-fx-background-color:" + ColorToHex.toRGBCode(doctorBackgroundColor.getValue())
					+ ";-fx-text-fill:" + ColorToHex.toRGBCode(doctorTextColor.getValue()));
			hexDoctorBackgroundColor = ColorToHex.toRGBCode(doctorBackgroundColor.getValue());
		});
	}

	private void setStaffTable() {
		doctorNameColumn = new TableColumn<>("Név");
		doctorNameColumn.setMinWidth(208);
		doctorNameColumn.setCellValueFactory(new PropertyValueFactory<Doctor, String>("doctorName"));

		doctorStatusColumn = new TableColumn<>("Aktív");
		doctorStatusColumn.setMinWidth(90);
		doctorStatusColumn.setCellValueFactory(i -> {
			final String value = i.getValue().getDoctorStatus();
			return Bindings.createObjectBinding(() -> value);
		});
		doctorStatusColumn.setCellFactory(ComboBoxTableCell.forTableColumn("Igen", "Nem"));
		doctorStatusColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Doctor, String>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<Doctor, String> d) {
				Doctor doctor = (Doctor) d.getTableView().getItems().get(d.getTablePosition().getRow());
				doctor.setDoctorStatus(d.getNewValue());
				db.updateDoctor(doctor);
			}
		});
		staffDoctorTView.setRowFactory(ts -> new TableRow<Doctor>() {
			@Override
			public void updateItem(Doctor item, boolean empty) {
				super.updateItem(item, empty);
				if (item == null) {
					setStyle("");
				} else {
					setStyle("");
					setStyle("-fx-text-background-color: " + item.getDoctorTextColor() + "; -fx-background-color: "
							+ item.getDoctorBackgroundColor() + ";");
				}
			}
		});
	}

	@SuppressWarnings("unchecked")
	private void getDataStaffTable() {
		staffDoctorTView.setItems(dataDoctor);
		staffDoctorTView.getColumns().addAll(doctorNameColumn, doctorStatusColumn);
		dataDoctor.addAll(db.getAllDoctor());

	}

	private void setdataClearTable() {
		dataDoctor.clear();
		dataDoctor.addAll(db.getAllDoctor());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		testTextColor();
		newDoctorSave();
		setStaffTable();
		getDataStaffTable();
	}
}
