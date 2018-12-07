package application.disposition.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;

import application.disposition.database.DispositionDataBase;
import application.disposition.pojo.Doctor;
import application.disposition.setting.ColorToHex;
import application.disposition.setting.LabelCSS;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
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
import javafx.scene.layout.HBox;

public class DispositionController implements Initializable {

	@FXML
	private ColorPicker doctorTextColor, doctorBackgroundColor;
	private String hexDoctorTextColor, hexDoctorBackgroundColor;
	@FXML
	private Label testLabel, showInfoLbl, scheduleDocktorLabel;
	@FXML
	private TextField doctorNameTxt;
	@FXML
	private Button newDoctorSave;
	@FXML
	private TableView<Doctor> staffDoctorTView;
	@FXML
	private HBox testBox;

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
							System.out.println(hexDoctorBackgroundColor + " : " + hexDoctorTextColor);
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
		testLabel.setStyle("-fx-text-fill:" + ColorToHex.toRGBCode(doctorTextColor.getValue())
				+ "; -fx-background-color:" + ColorToHex.toRGBCode(doctorBackgroundColor.getValue()));
		hexDoctorTextColor = ColorToHex.toRGBCode(doctorTextColor.getValue());
		hexDoctorBackgroundColor = ColorToHex.toRGBCode(doctorBackgroundColor.getValue());
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
		doctorStatusColumn.setMinWidth(95);
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
				scheduleDocktorLabel.setText("");
				setdataClearTable();
			}
		});

		staffDoctorTView.setRowFactory(ts -> new TableRow<Doctor>() {
			@Override
			public void updateItem(Doctor item, boolean empty) {
				super.updateItem(item, empty);
				if (!isEmpty()) {
					if (item == null) {
						setStyle("");
					}else {
						setStyle("");
						setStyle(" -fx-text-background-color: " + item.getDoctorTextColor() + "; -fx-background-color: "
								+ item.getDoctorBackgroundColor() + ";");
					}
				}else {
					setStyle("");
				}
			}
		});

		staffDoctorTView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Doctor>() {
			@Override
			public void changed(ObservableValue<? extends Doctor> observable, Doctor oldValue, Doctor newValue) {
				if (oldValue == null || newValue != null) {
					if (newValue.getDoctorStatus().equals("Igen")) {
						scheduleDocktorLabel.setText(newValue.getDoctorName());
					} else {
						scheduleDocktorLabel.setText("Nem aktív a munkatárs");
					}
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
		JFXDatePicker test1 = new JFXDatePicker(LocalDate.now());
		testBox.getChildren().add(test1);
		JFXTimePicker test = new JFXTimePicker();
		test.set24HourView(true);
		testBox.getChildren().add(test);
		JFXTimePicker test3 = new JFXTimePicker();
		test3.set24HourView(true);
		testBox.getChildren().add(test3);
		System.out.println(test1.getValue() + ":" + test.getValue());
	}
}
