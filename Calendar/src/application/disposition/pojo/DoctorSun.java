package application.disposition.pojo;

import javafx.beans.property.SimpleStringProperty;

public class DoctorSun {
	
	private final SimpleStringProperty doctorId;
	private final SimpleStringProperty doctorHours;
	private final SimpleStringProperty doctorName;
	private final SimpleStringProperty doctorTextColor;
	private final SimpleStringProperty doctorBackgroundColor;
	
	public DoctorSun() {
		this.doctorId = new SimpleStringProperty("");
		this.doctorHours = new SimpleStringProperty("");
		this.doctorName = new SimpleStringProperty("");
		this.doctorTextColor = new SimpleStringProperty("");
		this.doctorBackgroundColor = new SimpleStringProperty("");
	}
	
	public String getDoctorId() {
		return this.doctorId.get();
	}

	public void setDoctorId(String doctorId) {
		this.doctorId.set(doctorId);
	}
	
	
	public String getDoctorHours() {
		return this.doctorHours.get();
	}
	
	public void setDoctorHours(String doctorHours) {
		this.doctorId.set(doctorHours);
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

}
