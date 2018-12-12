package application.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.disposition.main.DispositionMain;
import application.setting.AnchorBackGround;
import application.setting.LabelSetCSS;
import application.setting.month.CalendarPaneMinMonthRight;
import application.setting.month.CalendarPaneMonthLeft;
import application.setting.month.CalendarPaneMonthRight;
import application.setting.month.MonthSetToString;
import application.setting.month.SunSetToString;
import application.setting.sun.CalendarPaneSunRight;
import application.setting.tooltip.Popup;
import application.setting.week.CalendarPaneWeekRight;
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
	private GridPane setActualGridPaneMonthLeft, setActualGridPaneMonthRight, setActualGridPaneWeekRight,
			actualDateGridPaneSunRight;
	@FXML
	private AnchorPane actualMonthAncorPaneLeft, actualDateAnchorPaneMonthRight, actualDateAnchorPaneWeekRight,
			actualDateAnchorPaneSunRight;
	@FXML
	private Label setYearAndMonthLblLeft, setYearAndMonthLblRight;
	@FXML
	private ComboBox<String> calendarCmb;
	@FXML
	private Button previousMonthLeftBtn, nextMonthLeftBtn, iconCalendarBtn, setTodayAllBtn, nextMonthRightBtn,
			previousMonthRightBtn, nextWeekRightBtn, previousWeekRightBtn, previousSunRightBtn, nextSunRightBtn;
	@FXML
	private HBox monthTurnerHBox, weekTurnerHBox, sunTurnerHBox;

	private LocalDate currentMonth;

	private ArrayList<CalendarPaneMonthLeft> allListActualCalendarDaysLeft = new ArrayList<>(35);
	private ArrayList<CalendarPaneMonthRight> allListActualCalendarDaysRight = new ArrayList<>(35);
	private ArrayList<CalendarPaneWeekRight> allListActualCalendarWeekRight = new ArrayList<>(7);
	private ArrayList<CalendarPaneSunRight> allListActualCalendarSunRight = new ArrayList<>(1);
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
				CalendarPaneMonthRight p = new CalendarPaneMonthRight();
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
		actualDateGridPaneSunRight.setHgap(1);
		actualDateGridPaneSunRight.setVgap(1);
		CalendarPaneSunRight sun = new CalendarPaneSunRight();
		actualDateGridPaneSunRight.add(sun, 0, 0);
		allListActualCalendarSunRight.add(sun);

		populateCalendarMonthLeft(yearMonth);
		populateCalendarMonthRight(yearMonth);
		populateCalendarWeekRight(yearMonth);
		populateCalendarSunRight(yearMonth);
	}

	private void populateCalendarSunRight(LocalDate yearMonth) {
		int z = 0;
		Label sunString = new Label(String.valueOf(SunSetToString.setSun(yearMonth)));
		sunString.setStyle(
				"-fx-font-size: 18.0px; -fx-font-weight: bold; -fx-text-fill: 	#696969; -fx-font-family: 'Arial Narrow';");
		for (CalendarPaneSunRight p : allListActualCalendarSunRight) {
			if (p.getChildren().size() != 0) {
				p.getChildren().remove(0);
				p.getChildren().clear();
			}
			Label txt = new Label(String.valueOf(yearMonth.getDayOfMonth()));
			txt.setMinSize(37, 36);
			txt.setAlignment(Pos.CENTER);
			p.setDate(yearMonth);
			p.setNumber(z += 1);
			CalendarPaneSunRight.setTopAnchor(txt, 35.0);
			CalendarPaneSunRight.setLeftAnchor(txt, 4.40);
			p.getChildren().add(txt);
			yearMonth = yearMonth.plusDays(1);
			LabelSetCSS.setDateNextSunRigth(p, currentMonth, txt);
			LabelSetCSS.setDateNowSunRigth(p, txt);
			sunString.setMinSize(37, 36);
			sunString.setAlignment(Pos.CENTER);
			CalendarPaneSunRight.setTopAnchor(sunString, 5.0);
			CalendarPaneSunRight.setLeftAnchor(sunString, 4.40);
			p.getChildren().add(sunString);
		}
		getYearAndMonthLbl(yearMonth);
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
			txt.getStylesheets().add("/application/view/monthancorpane.css");
			txt.setMinSize(37, 36);
			txt.setAlignment(Pos.CENTER);
			p.setDate(yearMonth);
			p.setNumber(z += 1);
			CalendarPaneWeekRight.setTopAnchor(txt, 35.0);
			CalendarPaneWeekRight.setLeftAnchor(txt, 4.40);
			p.getChildren().add(txt);
			yearMonth = yearMonth.plusDays(1);
			LabelSetCSS.setDateNextWeekRigth(p, currentMonth, txt);
			LabelSetCSS.setDateNowWeekRigth(p, txt);
			p.setOnMouseClicked(e -> getMonthWeekSunSet(p.getDate()));
		}
	}

	private void populateCalendarMonthLeft(LocalDate yearMonth) {
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
			AnchorBackGround.setDateNow(p);
			p.getChildren().add(txt);
			calendarDate = calendarDate.plusDays(1);
			LabelSetCSS.setDateNextLeft(p, currentMonth, txt);
			LabelSetCSS.setDateNowLeft(p, txt);
			p.setOnMouseClicked(e -> getMonthWeekSunSet(p.getDate()));
			
		}
	}


	private void populateCalendarMonthRight(LocalDate yearMonth) {
		calendarDate = LocalDate.of(yearMonth.getYear(), yearMonth.getMonthValue(), 1);
		while (!calendarDate.getDayOfWeek().toString().equals("MONDAY")) {
			calendarDate = calendarDate.minusDays(1);
		}
		int z = 0;
		for (CalendarPaneMonthRight p : allListActualCalendarDaysRight) {
			if (p.getChildren().size() != 0) {
				p.getChildren().remove(0);
				p.getChildren().clear();
			}
			CalendarPaneMinMonthRight minMonth = new CalendarPaneMinMonthRight();
			minMonth.setMinSize(37, 36);
			p.setDate(calendarDate);
			minMonth.setDate(calendarDate);
			p.setNumber(z += 1);
			CalendarPaneMinMonthRight.setTopAnchor(minMonth, 3.0);
			CalendarPaneMinMonthRight.setLeftAnchor(minMonth, 4.40);
			Label txt = new Label(String.valueOf(calendarDate.getDayOfMonth()));
			txt.setMinSize(37, 36);
			txt.setAlignment(Pos.CENTER);
			minMonth.getChildren().add(txt);
			AnchorBackGround.setDateNow(p);
			p.getChildren().add(minMonth);
			calendarDate = calendarDate.plusDays(1);
			LabelSetCSS.setDateNextRigth(p, currentMonth, txt);
			LabelSetCSS.setDateNowRigth(p, txt);
			minMonth.setOnMouseClicked(e -> getMonthWeekSunSet(minMonth.getDate()));
			new CalendarMonthLeft(p.getDate(),p);
		}

	}

	private void getYearAndMonthLbl(LocalDate yearMonth) {
		yearMonth = currentMonth;
		setYearAndMonthLblLeft
				.setText(String.valueOf(yearMonth.getYear() + ". " + MonthSetToString.setMonth(yearMonth)));
		setYearAndMonthLblRight
				.setText(String.valueOf(yearMonth.getYear() + ". " + MonthSetToString.setMonth(yearMonth)));

	}

	public ArrayList<CalendarPaneMonthLeft> getAllCalendarDays() {
		return allListActualCalendarDaysLeft;
	}

	public void setAllCalendarDays(ArrayList<CalendarPaneMonthLeft> allCalendarDays) {
		this.allListActualCalendarDaysLeft = allCalendarDays;
	}

	private void getMonthWeekSunSet(LocalDate yearMonth) {
		currentMonth = yearMonth;
		calendarCmb.setValue("Nap");
		populateCalendarMonthLeft(currentMonth);
		populateCalendarMonthRight(currentMonth);
		populateCalendarWeekRight(currentMonth);
		populateCalendarSunRight(currentMonth);
		actualDateAnchorPaneMonthRight.setVisible(false);
		actualDateAnchorPaneWeekRight.setVisible(false);
		actualDateAnchorPaneSunRight.setVisible(true);
		monthTurnerHBox.setVisible(false);
		weekTurnerHBox.setVisible(false);
		sunTurnerHBox.setVisible(true);
	}

	@FXML
	private void previousMonth() {
		currentMonth = currentMonth.minusMonths(1);
		populateCalendarMonthLeft(currentMonth);
		populateCalendarMonthRight(currentMonth);
		populateCalendarWeekRight(currentMonth);
		populateCalendarSunRight(currentMonth);
		System.out.println("Minuszhét " + currentMonth + " MinuszHonap " + currentMonth);
	}

	@FXML
	private void nextMonth() {
		currentMonth = currentMonth.plusMonths(1);
		populateCalendarMonthLeft(currentMonth);
		populateCalendarMonthRight(currentMonth);
		populateCalendarWeekRight(currentMonth);
		populateCalendarSunRight(currentMonth);
		System.out.println("Pluszhét " + currentMonth + " PlusszHonap " + currentMonth);
	}

	@FXML
	private void setTodayAll() {
		currentMonth = LocalDate.now();
		populateCalendarMonthLeft(currentMonth);
		populateCalendarMonthRight(currentMonth);
		populateCalendarWeekRight(currentMonth);
		populateCalendarSunRight(currentMonth);
	}

	@FXML
	private void previousWeek() {
		currentMonth = currentMonth.minusWeeks(1);
		populateCalendarMonthLeft(currentMonth);
		populateCalendarMonthRight(currentMonth);
		populateCalendarWeekRight(currentMonth);
		populateCalendarSunRight(currentMonth);
		System.out.println("Minuszhét " + currentMonth + " MinuszHonap " + currentMonth);
	}

	@FXML
	private void nextWeek() {
		currentMonth = currentMonth.plusWeeks(1);
		populateCalendarMonthLeft(currentMonth);
		populateCalendarMonthRight(currentMonth);
		populateCalendarWeekRight(currentMonth);
		populateCalendarSunRight(currentMonth);
		System.out.println("Pluszhét " + currentMonth + " PlusszHonap " + currentMonth);
	}

	@FXML
	private void previousSun() {
		currentMonth = currentMonth.minusDays(1);
		populateCalendarMonthLeft(currentMonth);
		populateCalendarMonthRight(currentMonth);
		populateCalendarWeekRight(currentMonth);
		populateCalendarSunRight(currentMonth);
		System.out.println("nap-");
	}

	@FXML
	private void nextSun() {
		currentMonth = currentMonth.plusDays(1);
		populateCalendarMonthLeft(currentMonth);
		populateCalendarMonthRight(currentMonth);
		populateCalendarWeekRight(currentMonth);
		populateCalendarSunRight(currentMonth);
		System.out.println("nap+");
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
		previousSunRightBtn.setTooltip(new Popup("Elöző nap"));
		nextSunRightBtn.setTooltip(new Popup("Következő nap"));
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
				sunTurnerHBox.setVisible(false);
			} else if (calendarCmb.getSelectionModel().getSelectedItem().equals("Hét")) {
				actualDateAnchorPaneMonthRight.setVisible(false);
				actualDateAnchorPaneWeekRight.setVisible(true);
				actualDateAnchorPaneSunRight.setVisible(false);
				monthTurnerHBox.setVisible(false);
				weekTurnerHBox.setVisible(true);
				sunTurnerHBox.setVisible(false);
			} else if (calendarCmb.getSelectionModel().getSelectedItem().equals("Nap")) {
				actualDateAnchorPaneMonthRight.setVisible(false);
				actualDateAnchorPaneWeekRight.setVisible(false);
				actualDateAnchorPaneSunRight.setVisible(true);
				monthTurnerHBox.setVisible(false);
				weekTurnerHBox.setVisible(false);
				sunTurnerHBox.setVisible(true);
			}
		});

	}
	
	@FXML
	private void dispositionScene() {
		DispositionMain main = new DispositionMain();
		main.start();

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
