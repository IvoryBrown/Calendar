package application.setting;

import java.time.LocalDate;

import javafx.scene.control.Label;

public class MonthSet {

	public static String setMonth(LocalDate yearMonth) {
		System.out.println("Sethonap " + yearMonth);
		String monthToString = String.valueOf(yearMonth.getMonth());
		if (yearMonth.getMonth().toString().equals("JANUARY")) {
			monthToString = "Január";
			return monthToString;
		}
		if (yearMonth.getMonth().toString().equals("FEBRUARY")) {
			monthToString = "Február";
			return monthToString;
		}
		if (yearMonth.getMonth().toString().equals("MARCH")) {
			monthToString = "Március";
			return monthToString;
		}
		if (yearMonth.getMonth().toString().equals("APRIL")) {
			monthToString = "Április";
			return monthToString;
		}
		if (yearMonth.getMonth().toString().equals("MAY")) {
			monthToString = "Május";
			return monthToString;
		}
		if (yearMonth.getMonth().toString().equals("JUNE")) {
			monthToString = "Június";
			return monthToString;
		}
		if (yearMonth.getMonth().toString().equals("JULY")) {
			monthToString = "Július";
			return monthToString;
		}
		if (yearMonth.getMonth().toString().equals("AUGUST")) {
			monthToString = "Augusztus";
			return monthToString;
		}
		if (yearMonth.getMonth().toString().equals("SEPTEMBER")) {
			monthToString = "Szeptember";
			return monthToString;
		}
		if (yearMonth.getMonth().toString().equals("OCTOBER")) {
			monthToString = "Október";
			return monthToString;
		}
		if (yearMonth.getMonth().toString().equals("NOVEMBER")) {
			monthToString = "November";
			return monthToString;
		}
		if (yearMonth.getMonth().toString().equals("DECEMBER")) {
			monthToString = "December";
			return monthToString;
		}

		return monthToString;

	}

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

	public static void setDateNowRigth(CalendarPaneRight p, Label l) {
		if (p.getDate().equals(LocalDate.now())) {
			l.setStyle("-fx-background-radius: 24.0px; -fx-background-color: #008B8B; -fx-text-fill: #F5F5F5;");
		}
	}

	public static void setDateNextRigth(CalendarPaneRight p, LocalDate d, Label l) {
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

}
