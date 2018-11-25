package application.setting.month;

import java.time.LocalDate;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public class CalendarPaneMinMonthRight extends AnchorPane {
	private LocalDate date;
	private Integer number;
	public static String sDate;

	public CalendarPaneMinMonthRight(Node... children) {
		super(children);
		this.setOnMouseClicked(e -> System.out.println(date + "jobb 1"));
	}

	public LocalDate getDate() {
		return this.date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Integer getNumber() {
		return this.number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
}
