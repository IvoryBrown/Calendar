package application.controller;

import java.time.LocalDate;

import application.disposition.database.DispositionDataBase;
import application.disposition.pojo.Doctor;
import application.setting.month.CalendarPaneMonthRight;
import application.setting.tooltip.Popup;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class CalendarMonthLeft {
	DispositionDataBase db = new DispositionDataBase();
	public ObservableList<Doctor> calendarList = FXCollections.observableArrayList();

	public CalendarMonthLeft(LocalDate localDate, CalendarPaneMonthRight p) {
		String date = String.valueOf(localDate);
		calendarList.addAll(db.getAllDoctorSchedule());
		VBox vb = new VBox();
		vb.setPrefSize(210, 60);
		CalendarPaneMonthRight.setTopAnchor(vb, 40.0);
		CalendarPaneMonthRight.setLeftAnchor(vb, 5.0);
		p.getChildren().add(vb);
		int k = 0;
		for (int i = 0; i < calendarList.size(); i++) {
			String s = calendarList.get(i).getDoctorScheduleStartTime().substring(0, 10);
			if (s.equals(date)) {
				k = k + 1;
				Label l = new Label(calendarList.get(i).getDoctorName());
				l.setTooltip(new Popup(calendarList.get(i).getDoctorName() + " "
						+ calendarList.get(i).getDoctorScheduleStartTime().substring(11, 16) + " - "
						+ calendarList.get(i).getDoctorScheduleEndTime().substring(11, 16)));
				l.setFont(Font.font("Verdana", FontWeight.BOLD, 11));
				l.setStyle(" -fx-text-fill: tomato;");
				if (k > 6) {
					l.setText("...");
					vb.getChildren().add(l);
					return;
				}
				vb.getChildren().add(l);
			}

		}
	}
}
