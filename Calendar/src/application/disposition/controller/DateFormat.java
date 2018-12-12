package application.disposition.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {

	public DateFormat(String sDate) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		try {
			Date date = simpleDateFormat.parse(sDate);

			System.out.println("date : " + simpleDateFormat.format(date));
		} catch (ParseException ex) {
			System.out.println("Exception " + ex);
		}
	}

}
