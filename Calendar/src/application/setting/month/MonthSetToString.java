package application.setting.month;

import java.time.LocalDate;

public class MonthSetToString {

	public static String setMonth(LocalDate yearMonth) {
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

}
