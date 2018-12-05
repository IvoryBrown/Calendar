package application.disposition.pojo;

import javafx.beans.property.SimpleStringProperty;

public class Doctor {

	private final SimpleStringProperty doctorId;
	private final SimpleStringProperty doctorName;
	private final SimpleStringProperty doctorTextColor;
	private final SimpleStringProperty doctorBackgroundColor;
	private final SimpleStringProperty doctorStatus;

	public Doctor() {
		this.doctorId = new SimpleStringProperty("");
		this.doctorName = new SimpleStringProperty("");
		this.doctorTextColor = new SimpleStringProperty("");
		this.doctorBackgroundColor = new SimpleStringProperty("");
		this.doctorStatus = new SimpleStringProperty("");
	}

	public Doctor(String doctorName, String doctorStatus) {
		this.doctorName = new SimpleStringProperty(doctorName);
		this.doctorTextColor = new SimpleStringProperty("");
		this.doctorBackgroundColor = new SimpleStringProperty("");
		this.doctorStatus = new SimpleStringProperty(doctorStatus);
		this.doctorId = new SimpleStringProperty("");
	}

	public Doctor(String doctorName, String doctorTextColor, String doctorBackgroundColor,String doctorStatus) {
		this.doctorName = new SimpleStringProperty(doctorName);
		this.doctorTextColor = new SimpleStringProperty(doctorTextColor);
		this.doctorBackgroundColor = new SimpleStringProperty(doctorBackgroundColor);
		this.doctorStatus = new SimpleStringProperty(doctorStatus);
		this.doctorId = new SimpleStringProperty("");
	}

	public Doctor(Integer doctorId, String doctorName, String doctorTextColor, String doctorBackgroundColor,
			String doctorStatus) {
		this.doctorName = new SimpleStringProperty(doctorName);
		this.doctorTextColor = new SimpleStringProperty(doctorTextColor);
		this.doctorBackgroundColor = new SimpleStringProperty(doctorBackgroundColor);
		this.doctorStatus = new SimpleStringProperty(doctorStatus);
		this.doctorId = new SimpleStringProperty(String.valueOf(doctorId));
	}

	public String getDoctorId() {
		return this.doctorId.get();
	}

	public void setDoctorId(String doctorId) {
		this.doctorId.set(doctorId);
	}

	public String getDoctorName() {
		return this.doctorName.get();
	}

	public void setDoctorName(String doctorName) {
		this.doctorName.set(doctorName);
	}

	public String getDoctorTextColor() {
		return this.doctorTextColor.get();
	}

	public void setDoctorTextColor(String doctorTextColor) {
		this.doctorTextColor.set(doctorTextColor);
	}

	public String getDoctorBackgroundColor() {
		return this.doctorBackgroundColor.get();
	}

	public void setDoctorBackgroundColor(String doctorBackgroundColor) {
		this.doctorBackgroundColor.set(doctorBackgroundColor);
	}

	public String getDoctorStatus() {
		return this.doctorStatus.get();
	}

	public void setDoctorStatus(String doctorStatus) {
		this.doctorStatus.set(doctorStatus);
	}
}
