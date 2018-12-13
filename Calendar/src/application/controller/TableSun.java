package application.controller;

import java.sql.Time;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;

import application.disposition.database.DispositionDataBase;
import application.disposition.pojo.Doctor;
import application.setting.sun.CalendarPaneSunRight;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.util.converter.LocalDateStringConverter;

public class TableSun {
	private TableView<Doctor> table;
	private TableColumn<Doctor, String> deviceColumnDoctorName;
	private TableColumn<Doctor, String> deviceColumnDoctorHours;
	private final ObservableList<Doctor> dataSunDoctor = FXCollections.observableArrayList();
	private DispositionDataBase db = new DispositionDataBase();
	private CalendarPaneSunRight sun;

	public TableSun(TableView<Doctor> table, CalendarPaneSunRight sun) {
		this.table = table;
		this.sun = sun;
		dataSunDoctor.addAll(db.getAllDoctorSchedule());
		setTable();
		Sel();
	}

	private void Sel() {
		LocalTime time = LocalTime.parse("10:10");
		for (int i = 0; i < 4; i++) {
			LocalTime time2=	time.plusMinutes(10);
			System.out.println(time2);
		}

	}

	private void setTable() {
		this.table.getColumns().clear();
		String date = String.valueOf(sun.getDate());
		for (int i = 0; i < dataSunDoctor.size(); i++) {
			String dateStart = dataSunDoctor.get(i).getDoctorScheduleStartTime().substring(0, 10);
			if (dateStart.equals(date)) {
				if (!dataSunDoctor.get(i).getDoctorName().trim().isEmpty()) {
					deviceColumnDoctorName = new TableColumn<Doctor, String>(dataSunDoctor.get(i).getDoctorName());
					deviceColumnDoctorName.setMinWidth(260);
					String backgroundColor = dataSunDoctor.get(i).getDoctorBackgroundColor();
					getTable(backgroundColor);

				} else {
					this.table.getColumns().clear();

				}
			}

		}
	}

	private void getTable(String backgroundColor) {
		deviceColumnDoctorName.setCellValueFactory(new PropertyValueFactory<Doctor, String>("doctorName"));
		deviceColumnDoctorName.setCellFactory(column -> {
			return new TableCell<Doctor, String>() {
				@Override
				protected void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					if (item == null || empty) {
						setText(null);
						setStyle("");
					} else {
						setTextFill(Color.CHOCOLATE);
						setStyle("-fx-background-color:" + backgroundColor + ";");
					}
				}
			};
		});

		this.table.getColumns().add(deviceColumnDoctorName);
		this.table.setItems(dataSunDoctor);

	}

}
