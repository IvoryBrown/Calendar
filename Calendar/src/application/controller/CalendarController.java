package application.controller;

import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.MonthDay;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import application.setting.CalendarPaneMinMonthRight;
import application.setting.CalendarPaneMonthLeft;
import application.setting.CalendarPaneRight;
import application.setting.CalendarPaneWeekRight;
import application.setting.MonthSet;
import application.setting.tooltip.Popup;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class CalendarController implements Initializable {

	@FXML
	private GridPane setActualGridPaneMonthLeft, setActualGridPaneMonthRight, setActualGridPaneWeekRight;
	@FXML
	private AnchorPane actualMonthAncorPaneLeft, actualDateAnchorPaneMonthRight, actualDateAnchorPaneWeekRight,
			actualDateAnchorPaneSunRight;
	@FXML
	private Label setYearAndMonthLblLeft, setYearAndMonthLblRight;
	@FXML
	private ComboBox<String> calendarCmb;
	@FXML
	private Button previousMonthLeftBtn, nextMonthLeftBtn, iconCalendarBtn, setTodayAllBtn, nextMonthRightBtn,
			previousMonthRightBtn, nextWeekRightBtn, previousWeekRightBtn;
	@FXML
	private HBox monthTurnerHBox, weekTurnerHBox;

	private LocalDate currentMonth;

	private ArrayList<CalendarPaneMonthLeft> allListActualCalendarDaysLeft = new ArrayList<>(35);
	private ArrayList<CalendarPaneRight> allListActualCalendarDaysRight = new ArrayList<>(35);
	private ArrayList<CalendarPaneWeekRight> allListActualCalendarWeekRight = new ArrayList<>(7);
	private LocalDate calendarDate;

	public void FullCalendarView(LocalDate yearMonth) {
		this.currentMonth = yearMonth;
		setActualGridPaneMonthLeft.setHgap(1);
		setActualGridPaneMonthLeft.setVgap(1);
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 7; j++) {
				CalendarPaneMonthLeft p = new CalendarPaneMonthLeft();
				setActualGridPaneMonthLeft.add(p, j, i);
				allListActualCalendarDaysLeft.add(p);
			}
		}
		setActualGridPaneMonthRight.setHgap(1);
		setActualGridPaneMonthRight.setVgap(1);
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 7; j++) {
				CalendarPaneRight p = new CalendarPaneRight();
				setActualGridPaneMonthRight.add(p, j, i);
				allListActualCalendarDaysRight.add(p);

			}
		}
		setActualGridPaneWeekRight.setHgap(1);
		setActualGridPaneWeekRight.setVgap(1);
		for (int i = 0; i < 7; i++) {
			CalendarPaneWeekRight p = new CalendarPaneWeekRight();
			setActualGridPaneWeekRight.add(p, i, 0);
			allListActualCalendarWeekRight.add(p);
		}

		populateCalendarLeft(yearMonth);
		populateCalendarRight(yearMonth);
		populateCalendarWeekRight(yearMonth);
	}

	private void populateCalendarWeekRight(LocalDate yearMonth) {
		while (!yearMonth.getDayOfWeek().toString().equals("MONDAY")) {
			yearMonth = yearMonth.minusDays(1);
		}
		int z = 0;
		for (CalendarPaneWeekRight p : allListActualCalendarWeekRight) {
			if (p.getChildren().size() != 0) {
				p.getChildren().remove(0);
				p.getChildren().clear();
			}
			Label txt = new Label(String.valueOf(yearMonth.getDayOfMonth()));
			txt.setMinSize(37, 36);
			txt.setAlignment(Pos.CENTER);
			p.setDate(yearMonth);
			p.setNumber(z += 1);
			CalendarPaneWeekRight.setTopAnchor(txt, 3.0);
			CalendarPaneWeekRight.setLeftAnchor(txt, 4.40);
			p.getChildren().add(txt);
			yearMonth = yearMonth.plusDays(1);
			MonthSet.setDateNextWeekRigth(p, currentMonth, txt);
			MonthSet.setDateNowWeekRigth(p, txt);
		}
		getYearAndMonthLbl(yearMonth);
	}

	private void populateCalendarLeft(LocalDate yearMonth) {
		calendarDate = LocalDate.of(yearMonth.getYear(), yearMonth.getMonthValue(), 1);
		while (!calendarDate.getDayOfWeek().toString().equals("MONDAY")) {
			calendarDate = calendarDate.minusDays(1);
		}
		int z = 0;
		for (CalendarPaneMonthLeft p : allListActualCalendarDaysLeft) {
			if (p.getChildren().size() != 0) {
				p.getChildren().remove(0);
				p.getChildren().clear();
			}
			Label txt = new Label(String.valueOf(calendarDate.getDayOfMonth()));
			txt.setMinSize(37, 36);
			txt.setAlignment(Pos.CENTER);
			p.setDate(calendarDate);
			p.setNumber(z += 1);
			CalendarPaneMonthLeft.setTopAnchor(txt, 3.0);
			CalendarPaneMonthLeft.setLeftAnchor(txt, 4.40);
			p.getChildren().add(txt);
			calendarDate = calendarDate.plusDays(1);
			MonthSet.setDateNextLeft(p, currentMonth, txt);
			MonthSet.setDateNowLeft(p, txt);

		}
	}

	private void populateCalendarRight(LocalDate yearMonth) {
		calendarDate = LocalDate.of(yearMonth.getYear(), yearMonth.getMonthValue(), 1);
		while (!calendarDate.getDayOfWeek().toString().equals("MONDAY")) {
			calendarDate = calendarDate.minusDays(1);
		}
		int z = 0;
		for (CalendarPaneRight p : allListActualCalendarDaysRight) {
			if (p.getChildren().size() != 0) {
				p.getChildren().remove(0);
				p.getChildren().clear();
			}
			CalendarPaneMinMonthRight lbl = new CalendarPaneMinMonthRight();
			lbl.setMinSize(37, 36);
			p.setDate(calendarDate);
			lbl.setDate(calendarDate);
			p.setNumber(z += 1);
			CalendarPaneMinMonthRight.setTopAnchor(lbl, 3.0);
			CalendarPaneMinMonthRight.setLeftAnchor(lbl, 4.40);
			Label txt = new Label(String.valueOf(calendarDate.getDayOfMonth()));
			txt.setMinSize(37, 36);
			txt.setAlignment(Pos.CENTER);
			lbl.getChildren().add(txt);
			p.getChildren().add(lbl);
			calendarDate = calendarDate.plusDays(1);
			MonthSet.setDateNextRigth(p, currentMonth, txt);
			MonthSet.setDateNowRigth(p, txt);
		}

	}

	private void getYearAndMonthLbl(LocalDate yearMonth) {
		yearMonth = currentMonth;
		setYearAndMonthLblLeft.setText(String.valueOf(yearMonth.getYear() + ". " + MonthSet.setMonth(yearMonth)));
		setYearAndMonthLblRight.setText(String.valueOf(yearMonth.getYear() + ". " + MonthSet.setMonth(yearMonth)));

	}

	public ArrayList<CalendarPaneMonthLeft> getAllCalendarDays() {
		return allListActualCalendarDaysLeft;
	}

	public void setAllCalendarDays(ArrayList<CalendarPaneMonthLeft> allCalendarDays) {
		this.allListActualCalendarDaysLeft = allCalendarDays;
	}

	@FXML
	private void previousMonth() {
		currentMonth = currentMonth.minusMonths(1);
		populateCalendarLeft(currentMonth);
		populateCalendarRight(currentMonth);
		populateCalendarWeekRight(currentMonth);
		System.out.println("Minuszhét " + currentMonth + " MinuszHonap " + currentMonth);
	}

	@FXML
	private void nextMonth() {
		currentMonth = currentMonth.plusMonths(1);
		populateCalendarLeft(currentMonth);
		populateCalendarRight(currentMonth);
		populateCalendarWeekRight(currentMonth);
		System.out.println("Pluszhét " + currentMonth + " PlusszHonap " + currentMonth);
	}

	@FXML
	private void setTodayAll() {
		populateCalendarLeft(LocalDate.now());
		populateCalendarRight(LocalDate.now());
		populateCalendarWeekRight(LocalDate.now());
		currentMonth = LocalDate.now();
	}

	@FXML
	private void previousWeek() {
		currentMonth = currentMonth.minusWeeks(1);
		populateCalendarLeft(currentMonth);
		populateCalendarRight(currentMonth);
		populateCalendarWeekRight(currentMonth);
		System.out.println("Minuszhét " + currentMonth + " MinuszHonap " + currentMonth);
	}

	@FXML
	private void nextWeek() {
		currentMonth = currentMonth.plusWeeks(1);
		populateCalendarLeft(currentMonth);
		populateCalendarRight(currentMonth);
		populateCalendarWeekRight(currentMonth);
		System.out.println("Pluszhét " + currentMonth + " PlusszHonap " + currentMonth);
	}

	private void setButtonImage() {
		BackgroundImage backgroundImage = new BackgroundImage(
				new Image(getClass().getResource("/application/view/calendar.png").toExternalForm()),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
				BackgroundSize.DEFAULT);
		Background background = new Background(backgroundImage);
		iconCalendarBtn.setBackground(background);
	}

	private void setPopup() {
		previousMonthLeftBtn.setTooltip(new Popup("Elöző hónap"));
		nextMonthLeftBtn.setTooltip(new Popup("Következő hónap"));
		nextMonthRightBtn.setTooltip(new Popup("Következő hónap"));
		previousMonthRightBtn.setTooltip(new Popup("Elöző hónap"));
		previousWeekRightBtn.setTooltip(new Popup("Elöző hét"));
		nextWeekRightBtn.setTooltip(new Popup("Következő hét"));
		setTodayAllBtn.setTooltip(new Popup(String.valueOf(LocalDate.now())));
	}

	private void setCombobox() {
		calendarCmb.getItems().add("Hónap");
		calendarCmb.getItems().add("Hét");
		calendarCmb.getItems().add("Nap");
		calendarCmb.setValue("Hónap");
		calendarCmb.setOnAction((e) -> {
			if (calendarCmb.getSelectionModel().getSelectedItem().equals("Hónap")) {
				actualDateAnchorPaneMonthRight.setVisible(true);
				actualDateAnchorPaneWeekRight.setVisible(false);
				actualDateAnchorPaneSunRight.setVisible(false);
				monthTurnerHBox.setVisible(true);
				weekTurnerHBox.setVisible(false);
			} else if (calendarCmb.getSelectionModel().getSelectedItem().equals("Hét")) {
				actualDateAnchorPaneMonthRight.setVisible(false);
				actualDateAnchorPaneWeekRight.setVisible(true);
				actualDateAnchorPaneSunRight.setVisible(false);
				monthTurnerHBox.setVisible(false);
				weekTurnerHBox.setVisible(true);
			} else if (calendarCmb.getSelectionModel().getSelectedItem().equals("Nap")) {
				actualDateAnchorPaneMonthRight.setVisible(false);
				actualDateAnchorPaneWeekRight.setVisible(false);
				actualDateAnchorPaneSunRight.setVisible(true);
				monthTurnerHBox.setVisible(false);
				weekTurnerHBox.setVisible(false);
			}
		});

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		currentMonth = LocalDate.now();
		FullCalendarView(currentMonth);
		setButtonImage();
		setPopup();
		setCombobox();
	}

}
