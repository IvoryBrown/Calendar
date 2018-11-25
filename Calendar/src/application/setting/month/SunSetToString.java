package application.setting.month;

import java.time.LocalDate;

public class SunSetToString {

	public static String setSun(LocalDate sun) {
		String sunString = String.valueOf(sun.getDayOfWeek().toString());
		System.out.println(sunString + "át");
		if (sun.getDayOfWeek().toString().equals("MONDAY")) {
			sunString = "Hétfő";
			return sunString;
		}
		if (sun.getDayOfWeek().toString().equals("TUESDAY")) {
			sunString = "Kedd";
			return sunString;
		}
		if (sun.getDayOfWeek().toString().equals("WEDNESDAY")) {
			sunString = "Szerda";
			return sunString;
		}
		if (sun.getDayOfWeek().toString().equals("THURSDAY")) {
			sunString = "Csütörtök";
			return sunString;
		}
		if (sun.getDayOfWeek().toString().equals("FRIDAY")) {
			sunString = "Péntek";
			return sunString;
		}
		if (sun.getDayOfWeek().toString().equals("SATURDAY")) {
			sunString = "Szombat";
			return sunString;
		}
		if (sun.getDayOfWeek().toString().equals("SUNDAY")) {
			sunString = "Vasárnap";
			return sunString;
		}
		return sunString;

	}
	
}
