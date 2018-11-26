package application.setting;

import application.setting.month.CalendarPaneMonthLeft;
import application.setting.month.CalendarPaneMonthRight;

public class AnchorBackGround {
	
	
	
	public static void setDateNow(CalendarPaneMonthLeft p) {
		if (p.getNumber() == 7 || p.getNumber() == 14 || p.getNumber() == 21 || p.getNumber() == 28
				|| p.getNumber() == 35) {
			p.setStyle(" -fx-background-color: #c5e5c8;");
		
		} else if (p.getNumber() == 6 || p.getNumber() == 13 || p.getNumber() == 20 || p.getNumber() == 27
				|| p.getNumber() == 34) {
			p.setStyle(" -fx-background-color: #e7f5e3;");
			
		} else {
			p.setStyle(" -fx-background-color: #F0FFFF;");
			
		}
	}
	
	public static void setDateNow(CalendarPaneMonthRight p) {
		if (p.getNumber() == 7 || p.getNumber() == 14 || p.getNumber() == 21 || p.getNumber() == 28
				|| p.getNumber() == 35) {
			p.setStyle(" -fx-background-color: #c5e5c8;");
			
		} else if (p.getNumber() == 6 || p.getNumber() == 13 || p.getNumber() == 20 || p.getNumber() == 27
				|| p.getNumber() == 34) {
			p.setStyle(" -fx-background-color: #e7f5e3;");
			
		} else {
			p.setStyle(" -fx-background-color: #F0FFFF;");
			
		}
	}

}
