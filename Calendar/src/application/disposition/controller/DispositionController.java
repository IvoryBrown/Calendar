package application.disposition.controller;

import java.net.URL;
import java.util.Date;
import java.util.Optional;
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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;
import javafx.util.Callback;

public class DispositionController implements Initializable {

	@FXML
	private ColorPicker doctorTextColor, doctorBackgroundColor;
	private String hexDoctorTextColor, hexDoctorBackgroundColor, idDoctor;
	@FXML
	private Label testLabel, showInfoLbl, scheduleDocktorLabel;
	@FXML
	private TextField doctorNameTxt;
	@FXML
	private Button newDoctorSave;
	@FXML
	private TableView<Doctor> staffDoctorTView, scheduleTableView;
	@FXML
	private HBox hBox;
	private JFXTimePicker startTimePicker, endTimePicker;
	private JFXDatePicker datePicker;
	private boolean check = false;

	private TableColumn<Doctor, String> doctorNameColumn, doctorStatusColumn,removeCol;
	private TableColumn<Doctor, Date> doctorStartDateColumn, doctorEndDateColumn;
	private DispositionDataBase db = new DispositionDataBase();
	private LabelCSS labelCSS = new LabelCSS();
	private final ObservableList<Doctor> dataDoctor = FXCollections.observableArrayList();
	private final ObservableList<Doctor> dataDoctorSchedule = FXCollections.observableArrayList();

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
							removeDataClearTable();
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
				removeDataClearTable();
			}
		});

		staffDoctorTView.setRowFactory(ts -> new TableRow<Doctor>() {
			@Override
			public void updateItem(Doctor item, boolean empty) {
				super.updateItem(item, empty);
				if (!isEmpty()) {
					if (item == null) {
						setStyle("");
					} else {
						setStyle("");
						setStyle(" -fx-text-background-color: " + item.getDoctorTextColor() + "; -fx-background-color: "
								+ item.getDoctorBackgroundColor() + ";");
					}
				} else {
					setStyle("");
				}
			}
		});

		staffDoctorTView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Doctor>() {
			@Override
			public void changed(ObservableValue<? extends Doctor> observable, Doctor oldValue, Doctor newValue) {
				if (oldValue == null || newValue != null) {
					if (newValue.getDoctorStatus().equals("Igen")) {
						check = true;
						scheduleDocktorLabel.setText(newValue.getDoctorName());
						idDoctor = newValue.getDoctorId();
					} else {
						check = false;
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

	private void removeDataClearTable() {
		dataDoctor.clear();
		dataDoctor.addAll(db.getAllDoctor());
	}

	private void scheduleDataBase() {
		datePicker = new JFXDatePicker();
		hBox.getChildren().add(datePicker);
		startTimePicker = new JFXTimePicker();
		startTimePicker.setMaxHeight(30);
		startTimePicker.setMaxWidth(130);
		startTimePicker.set24HourView(true);
		hBox.getChildren().add(startTimePicker);
		endTimePicker = new JFXTimePicker();
		endTimePicker.setMaxHeight(30);
		endTimePicker.setMaxWidth(130);
		endTimePicker.set24HourView(true);
		hBox.getChildren().add(endTimePicker);
	}

	private boolean checkDate() {
		if (startTimePicker.getValue() != null || endTimePicker.getValue() != null) {
			return true;
		} else {
			return false;
		}
	}

	private boolean checkTime() {
		String s = startTimePicker.getEditor().getText();
		int startTime = Integer.valueOf(s.substring(0, s.indexOf(":")));
		String e = endTimePicker.getEditor().getText();
		int endTime = Integer.valueOf(e.substring(0, e.indexOf(":")));
		if (startTime <= endTime) {
			return true;
		} else {
			return false;
		}
	}

	@FXML
	private void scheduleDataBaseAdd() {

		if (!scheduleDocktorLabel.getText().trim().isEmpty()) {
			if (check) {
				if (checkDate()) {
					if (checkTime()) {
						Doctor doctor = new Doctor(datePicker.getValue() + " " + startTimePicker.getValue(),
								datePicker.getValue() + " " + endTimePicker.getValue(), idDoctor);
						db.addNewScheduleS(doctor);
						showInfoLbl.setText("Sikeres Mentés: Új beosztás elkészült");
						showInfoLbl.setStyle(labelCSS.goodCSS());
						scheduleDocktorLabel.setText("");
						startTimePicker.setValue(null);
						endTimePicker.setValue(null);
						datePicker.setValue(null);
						remuveDataScheduleTable();
					} else {
						showInfoLbl.setText("Sikertelen Beosztás: Idő hibás");
						showInfoLbl.setStyle(labelCSS.errorCSS());
					}

				} else {
					showInfoLbl.setText("Sikertelen Beosztás: Dátum hibás");
					showInfoLbl.setStyle(labelCSS.errorCSS());
				}

			} else {
				showInfoLbl.setText("Sikertelen Beosztás: Nem aktív már a dolgozó");
				showInfoLbl.setStyle(labelCSS.errorCSS());
			}
		} else {
			showInfoLbl.setText("Sikertelen Beosztás: Nincs dolgozó kiválasztva");
			showInfoLbl.setStyle(labelCSS.errorCSS());
		}

	}

	private void setScheduleTable() {
		doctorNameColumn = new TableColumn<>("Név");
		doctorNameColumn.setMinWidth(250);
		doctorNameColumn.setCellValueFactory(new PropertyValueFactory<Doctor, String>("doctorName"));

		doctorStatusColumn = new TableColumn<>("Aktív");
		doctorStatusColumn.setMinWidth(95);
		doctorStatusColumn.setCellValueFactory(i -> {
			final String value = i.getValue().getDoctorStatus();
			return Bindings.createObjectBinding(() -> value);
		});

		doctorStartDateColumn = new TableColumn<>("Kezdés");
		doctorStartDateColumn.setMinWidth(150);
		doctorStartDateColumn.setCellValueFactory(new PropertyValueFactory<Doctor, Date>("doctorScheduleStartTime"));

		doctorEndDateColumn = new TableColumn<>("Befejezés");
		doctorEndDateColumn.setMinWidth(150);
		doctorEndDateColumn.setCellValueFactory(new PropertyValueFactory<Doctor, Date>("doctorScheduleEndTime"));
		
		removeCol  = new TableColumn<>("Törlés");
		removeCol.setMinWidth(100);
		Callback<TableColumn<Doctor, String>, TableCell<Doctor, String>> cellFactory = new Callback<TableColumn<Doctor, String>, TableCell<Doctor, String>>() {
			@Override
			public TableCell<Doctor, String> call(final TableColumn<Doctor, String> param) {
				final TableCell<Doctor, String> cell = new TableCell<Doctor, String>() {
					final Button btn = new Button("Törlés");
				
					@Override
					public void updateItem(String item, boolean empty) {
						
					super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
							setText(null);
						} else {
							btn.setOnAction((ActionEvent event) -> {
								Alert alert = new Alert(AlertType.CONFIRMATION);
								alert.setTitle("Törlés");
								alert.setHeaderText("");
								alert.getDialogPane().getStylesheets().add("/application/setting/showinfo/ShowInfo.css");
								alert.initStyle(StageStyle.TRANSPARENT);
								String s = "Biztos törölni szeretnéd ?";
								alert.setContentText(s);
								Optional<ButtonType> result = alert.showAndWait();
								if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
									Doctor doctor = getTableView().getItems().get(getIndex());
									dataDoctorSchedule.remove(doctor);
									db.removeContact(doctor);
								}
							});
							setGraphic(btn);
							setText(null);
						}
					}
				};
				cell.setAlignment(Pos.CENTER);
				return cell;
			}
		};
		removeCol.setCellFactory(cellFactory);
		
		scheduleTableView.setRowFactory(ts -> new TableRow<Doctor>() {
			@Override
			public void updateItem(Doctor item, boolean empty) {
				super.updateItem(item, empty);
				if (!isEmpty()) {
					if (item == null) {
						setStyle("");
					} else {
						setStyle("");
						setStyle(" -fx-text-background-color: " + item.getDoctorTextColor() + "; -fx-background-color: "
								+ item.getDoctorBackgroundColor() + ";");
					}
				} else {
					setStyle("");
				}
			}
		});
	}

	@SuppressWarnings("unchecked")
	private void getDataScheduleTable() {
		scheduleTableView.setItems(dataDoctorSchedule);
		scheduleTableView.getColumns().addAll(doctorNameColumn, doctorStatusColumn, doctorStartDateColumn,
				doctorEndDateColumn,removeCol);
		dataDoctorSchedule.addAll(db.getAllDoctorSchedule());

	}
	
	private void remuveDataScheduleTable() {
		dataDoctorSchedule.clear();
		dataDoctorSchedule.addAll(db.getAllDoctorSchedule());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		testTextColor();
		newDoctorSave();
		setStaffTable();
		getDataStaffTable();
		scheduleDataBase();
		setScheduleTable();
		getDataScheduleTable();
	}
}
