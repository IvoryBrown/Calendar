package application.setting;

import java.time.LocalDate;

import application.setting.month.CalendarPaneMonthLeft;
import application.setting.month.CalendarPaneMonthRight;
import application.setting.sun.CalendarPaneSunRight;
import application.setting.week.CalendarPaneWeekRight;
import javafx.scene.control.Label;

public class LabelSetCSS {

	public static void setDateNowLeft(CalendarPaneMonthLeft p, Label l) {
		if (p.getDate().equals(LocalDate.now())) {
			l.setStyle("-fx-background-radius: 24.0px; -fx-background-color: #008B8B; -fx-text-fill: #F5F5F5;");
		}
	}

	public static void setDateNextLeft(CalendarPaneMonthLeft p, LocalDate d, Label l) {
		if (p.getDate().equals(d)) {
			l.setStyle("-fx-background-radius: 24.0px; -fx-background-color: #48D1CC; -fx-text-fill: #F5F5F5;");
		}
	}

	public static void setDateNowRigth(CalendarPaneMonthRight p, Label l) {
		if (p.getDate().equals(LocalDate.now())) {
			l.setStyle("-fx-background-radius: 24.0px; -fx-background-color: #008B8B; -fx-text-fill: #F5F5F5;");
		}
	}

	public static void setDateNextRigth(CalendarPaneMonthRight p, LocalDate d, Label l) {
		if (p.getDate().equals(d)) {
			l.setStyle("-fx-background-radius: 24.0px; -fx-background-color: #48D1CC; -fx-text-fill: #F5F5F5;");
		}
	}

	public static void setDateNowWeekRigth(CalendarPaneWeekRight p, Label l) {
		if (p.getDate().equals(LocalDate.now())) {
			l.setStyle("-fx-background-radius: 24.0px; -fx-background-color: #008B8B; -fx-text-fill: #F5F5F5;");
		}
	}

	public static void setDateNextWeekRigth(CalendarPaneWeekRight p, LocalDate d, Label l) {
		if (p.getDate().equals(d)) {
			l.setStyle("-fx-background-radius: 24.0px; -fx-background-color: #48D1CC; -fx-text-fill: #F5F5F5;");
		}
	}

	public static void setDateNextSunRigth(CalendarPaneSunRight p, LocalDate d, Label l) {
		if (p.getDate().equals(d)) {
			l.setStyle("-fx-background-radius: 24.0px; -fx-background-color: #48D1CC; -fx-text-fill: #F5F5F5;");
		}
	}

	public static void setDateNowSunRigth(CalendarPaneSunRight p, Label l) {
		if (p.getDate().equals(LocalDate.now())) {
			l.setStyle("-fx-background-radius: 24.0px; -fx-background-color: #008B8B; -fx-text-fill: #F5F5F5;");
		}
	}

}
