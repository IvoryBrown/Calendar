package application.disposition.pojo;

import javafx.beans.property.SimpleStringProperty;

public class Doctor {

	private final SimpleStringProperty doctorId;
	private final SimpleStringProperty doctorName;
	private final SimpleStringProperty doctorTextColor;
	private final SimpleStringProperty doctorBackgroundColor;
	private final SimpleStringProperty doctorStatus;

	private final SimpleStringProperty doctorScheduleId;
	private final SimpleStringProperty doctorScheduleStartTime;
	private final SimpleStringProperty doctorScheduleEndTime;
	private final SimpleStringProperty doctorIddoctorScheduleId;

	public Doctor() {
		this.doctorId = new SimpleStringProperty("");
		this.doctorName = new SimpleStringProperty("");
		this.doctorTextColor = new SimpleStringProperty("");
		this.doctorBackgroundColor = new SimpleStringProperty("");
		this.doctorStatus = new SimpleStringProperty("");

		this.doctorScheduleId = new SimpleStringProperty("");
		this.doctorScheduleStartTime = new SimpleStringProperty("");
		this.doctorScheduleEndTime = new SimpleStringProperty("");
		this.doctorIddoctorScheduleId = new SimpleStringProperty("");
	}

	public Doctor(String doctorName, String doctorTextColor, String doctorBackgroundColor, String doctorStatus) {
		this.doctorName = new SimpleStringProperty(doctorName);
		this.doctorTextColor = new SimpleStringProperty(doctorTextColor);
		this.doctorBackgroundColor = new SimpleStringProperty(doctorBackgroundColor);
		this.doctorStatus = new SimpleStringProperty(doctorStatus);
		this.doctorId = new SimpleStringProperty("");

		this.doctorScheduleId = new SimpleStringProperty("");
		this.doctorScheduleStartTime = new SimpleStringProperty("");
		this.doctorScheduleEndTime = new SimpleStringProperty("");
		this.doctorIddoctorScheduleId = new SimpleStringProperty("");
	}

	public Doctor(Integer doctorId, String doctorName, String doctorTextColor, String doctorBackgroundColor,
			String doctorStatus) {
		this.doctorName = new SimpleStringProperty(doctorName);
		this.doctorTextColor = new SimpleStringProperty(doctorTextColor);
		this.doctorBackgroundColor = new SimpleStringProperty(doctorBackgroundColor);
		this.doctorStatus = new SimpleStringProperty(doctorStatus);
		this.doctorId = new SimpleStringProperty(String.valueOf(doctorId));

		this.doctorScheduleId = new SimpleStringProperty("");
		this.doctorScheduleStartTime = new SimpleStringProperty("");
		this.doctorScheduleEndTime = new SimpleStringProperty("");
		this.doctorIddoctorScheduleId = new SimpleStringProperty("");
	}

	public Doctor(String doctorScheduleStartTime, String doctorScheduleEndTime, String doctorIddoctorScheduleId) {
		this.doctorName = new SimpleStringProperty("");
		this.doctorTextColor = new SimpleStringProperty("");
		this.doctorBackgroundColor = new SimpleStringProperty("");
		this.doctorStatus = new SimpleStringProperty("");
		this.doctorId = new SimpleStringProperty("");

		this.doctorScheduleId = new SimpleStringProperty(String.valueOf(""));
		this.doctorScheduleStartTime = new SimpleStringProperty(doctorScheduleStartTime);
		this.doctorScheduleEndTime = new SimpleStringProperty(doctorScheduleEndTime);
		this.doctorIddoctorScheduleId = new SimpleStringProperty(String.valueOf(doctorIddoctorScheduleId));
	}

	public Doctor(Integer doctorId, String doctorName, String doctorTextColor, String doctorBackgroundColor,
			String doctorStatus, String doctorScheduleId, String doctorScheduleStartTime, String doctorScheduleEndTime,
			String doctorIddoctorScheduleId) {
		this.doctorName = new SimpleStringProperty(doctorName);
		this.doctorTextColor = new SimpleStringProperty(doctorTextColor);
		this.doctorBackgroundColor = new SimpleStringProperty(doctorBackgroundColor);
		this.doctorStatus = new SimpleStringProperty(doctorStatus);
		this.doctorId = new SimpleStringProperty(String.valueOf(doctorId));

		this.doctorScheduleId = new SimpleStringProperty(String.valueOf(doctorScheduleId));
		this.doctorScheduleStartTime = new SimpleStringProperty(doctorScheduleStartTime);
		this.doctorScheduleEndTime = new SimpleStringProperty(doctorScheduleEndTime);
		this.doctorIddoctorScheduleId = new SimpleStringProperty(String.valueOf(doctorIddoctorScheduleId));
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

	public String getDoctorScheduleId() {
		return this.doctorScheduleId.get();
	}

	public void setDoctorScheduleId(String doctorScheduleId) {
		this.doctorScheduleId.set(doctorScheduleId);
	}

	public String getDoctorScheduleStartTime() {
		String startDate = doctorScheduleStartTime.get().substring(0, 16);
		return startDate;
	}

	public void setDoctorScheduleStartTime(String doctorScheduleStartTime) {
		this.doctorScheduleStartTime.set(doctorScheduleStartTime);
	}

	public String getDoctorScheduleEndTime() {
		String startDate = doctorScheduleEndTime.get().substring(0, 16);
		return startDate;
	}

	public void setDoctorScheduleEndTime(String doctorScheduleEndTime) {
		this.doctorScheduleEndTime.set(doctorScheduleEndTime);
	}

	public String getDoctorIddoctorScheduleId() {
		return this.doctorIddoctorScheduleId.get();
	}

	public void setDoctorIddoctorScheduleId(String doctorIddoctorScheduleId) {
		this.doctorIddoctorScheduleId.set(doctorIddoctorScheduleId);
	}
}
