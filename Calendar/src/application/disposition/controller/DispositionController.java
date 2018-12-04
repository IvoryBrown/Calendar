package application.disposition.controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.disposition.database.DispositionDataBase;
import application.disposition.pojo.Doctor;
import application.disposition.setting.ColorToHex;
import application.disposition.setting.LabelCSS;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class DispositionController implements Initializable {

	@FXML
	private ColorPicker doctorTextColor, doctorBackgroundColor;
	@FXML
	private Label testLabel, showInfoLbl;
	@FXML
	private TextField doctorNameTxt;
	@FXML
	private Button newDoctorSave;
	private DispositionDataBase db = new DispositionDataBase();
	private LabelCSS labelCSS = new LabelCSS();

	private void newDoctorSave() {
		newDoctorSave.setOnAction(evt -> {
			showInfoLbl.setText("I");
			showInfoLbl.setStyle(labelCSS.errorCSS());
			newDoctorSave.setOnKeyPressed(new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent ke) {
					if (ke.getCode().equals(KeyCode.I)) {
						if (checkNewDoctorText()) {
							Doctor newDoctor = new Doctor(doctorNameTxt.getText(),
									doctorBackgroundColor.getValue().toString(), doctorTextColor.getValue().toString());
							db.addDoctor(newDoctor);
							doctorNameTxt.clear();
							showInfoLbl.setText("Sikeres Mentés: Új Orvos");
							showInfoLbl.setStyle(labelCSS.goodCSS());
						} else {
							showInfoLbl.setText("Sikertelen Mentés");
							showInfoLbl.setStyle(labelCSS.errorCSS());
						}
					}
				}
			});
		});
	}

	private boolean checkNewDoctorText() {
		if (doctorNameTxt.getText().trim().isEmpty()) {
			doctorNameTxt.setStyle(labelCSS.errorCSS());
			return false;
		} else {
			doctorNameTxt.setStyle(null);
			return true;
		}
	}

	private void testTextColor() {
		doctorTextColor.setOnAction((e) -> {
			testLabel.setStyle("-fx-text-fill:" + ColorToHex.toRGBCode(doctorTextColor.getValue())
					+ "; -fx-background-color:" + ColorToHex.toRGBCode(doctorBackgroundColor.getValue()));

		});
		doctorBackgroundColor.setOnAction((e) -> {
			testLabel.setStyle("-fx-background-color:" + ColorToHex.toRGBCode(doctorBackgroundColor.getValue())
					+ ";-fx-text-fill:" + ColorToHex.toRGBCode(doctorTextColor.getValue()));

		});
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		testTextColor();
		newDoctorSave();
	}
}
