package application.disposition.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import application.disposition.pojo.Doctor;
import application.setting.database.DataBaseConnect;
import application.setting.showinfo.ShowInfoError;

public class DispositionDataBase {

	public ArrayList<Doctor> getAllDoctor() {
		String sql = "SELECT * FROM `orvos`";
		Connection con = DataBaseConnect.getConnection();
		ArrayList<Doctor> doctor = null;
		Statement createStatement = null;
		ResultSet rs = null;
		try {
			createStatement = con.createStatement();
			rs = createStatement.executeQuery(sql);
			doctor = new ArrayList<>();

			while (rs.next()) {
				Doctor actualDoctor = new Doctor(rs.getInt("orvos_id"), rs.getString("orvos_nev"),
						rs.getString("orvos_hater_szin"), rs.getString("orvos_betu_szin"), rs.getString("orvos_aktiv"));
				doctor.add(actualDoctor);
			}
		} catch (SQLException ex) {
			System.out.println("Valami baj van a userek kiolvasásakor");
			System.out.println("" + ex);
			new ShowInfoError("Adatbázis Hiba", "Szerver válasza: ", ex.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (createStatement != null) {
					createStatement.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				new ShowInfoError("Adatbázis Hiba", "Szerver válasza: ", e.getMessage());
			}
		}
		return doctor;
	}

	public ArrayList<Doctor> getAllDoctorSchedule() {
		String sql = "SELECT * FROM `orvos` JOIN `rendeles` ON orvos_id = orvos_orvos_id ORDER BY `kezdes` DESC";
		Connection con = DataBaseConnect.getConnection();
		ArrayList<Doctor> doctor = null;
		Statement createStatement = null;
		ResultSet rs = null;
		try {
			createStatement = con.createStatement();
			rs = createStatement.executeQuery(sql);
			doctor = new ArrayList<>();

			while (rs.next()) {
				Doctor actualDoctor = new Doctor(rs.getInt("orvos_id"), rs.getString("orvos_nev"),
						rs.getString("orvos_hater_szin"), rs.getString("orvos_betu_szin"), rs.getString("orvos_aktiv"),
						rs.getString("beosztas_id"), rs.getString("kezdes"), rs.getString("befejzes"),
						rs.getString("orvos_orvos_id"));
				doctor.add(actualDoctor);
			}
		} catch (SQLException ex) {
			System.out.println("Valami baj van a userek kiolvasásakor");
			System.out.println("" + ex);
			new ShowInfoError("Adatbázis Hiba", "Szerver válasza: ", ex.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (createStatement != null) {
					createStatement.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				new ShowInfoError("Adatbázis Hiba", "Szerver válasza: ", e.getMessage());
			}
		}
		return doctor;
	}

	public void addNewDoctor(Doctor doctor) {
		Connection con = DataBaseConnect.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			String sql = "INSERT INTO `orvos` (orvos_nev, orvos_hater_szin, orvos_betu_szin, orvos_aktiv) VALUES (?,?,?,?)";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, doctor.getDoctorName());
			preparedStatement.setString(2, doctor.getDoctorBackgroundColor());
			preparedStatement.setString(3, doctor.getDoctorTextColor());
			preparedStatement.setString(4, doctor.getDoctorStatus());
			preparedStatement.execute();
		} catch (SQLException ex) {
			new ShowInfoError("Adatbázis Hiba", "Szerver válasza: ", ex.getMessage());
			System.out.println("Valami baj van a contact hozzáadásakor");
			System.out.println("" + ex);
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				new ShowInfoError("Adatbázis Hiba", "Szerver válasza: ", e.getMessage());
			}
		}
	}

	public void addNewScheduleS(Doctor doctor) {
		Connection con = DataBaseConnect.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			String sql = "INSERT INTO `rendeles` ( kezdes, befejzes, orvos_orvos_id) VALUES (?,?,?)";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, doctor.getDoctorScheduleStartTime());
			preparedStatement.setString(2, doctor.getDoctorScheduleEndTime());
			preparedStatement.setString(3, doctor.getDoctorIddoctorScheduleId());
			preparedStatement.execute();
		} catch (SQLException ex) {
			new ShowInfoError("Adatbázis Hiba", "Szerver válasza: ", ex.getMessage());
			System.out.println("Valami baj van a contact hozzáadásakor");
			System.out.println("" + ex);
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				new ShowInfoError("Adatbázis Hiba", "Szerver válasza: ", e.getMessage());
			}
		}
	}

	public void updateDoctor(Doctor doctor) {
		Connection con = DataBaseConnect.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			String sql = "UPDATE `orvos` SET orvos_nev = ?, orvos_hater_szin = ? , orvos_betu_szin = ?, orvos_aktiv = ? WHERE orvos_id = ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, doctor.getDoctorName());
			preparedStatement.setString(2, doctor.getDoctorTextColor());
			preparedStatement.setString(3, doctor.getDoctorBackgroundColor());
			preparedStatement.setString(4, doctor.getDoctorStatus());
			preparedStatement.setInt(5, Integer.parseInt(doctor.getDoctorId()));
			preparedStatement.execute();
		} catch (SQLException ex) {
			System.out.println("Valami baj van a contact hozzáadásakor");
			System.out.println("" + ex);
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException ex) {
				new ShowInfoError("Adatbázis Hiba", "Szerver válasza: ", ex.getMessage());
				System.out.println("Valami baj van a contact hozzáadásakor");
				System.out.println("" + ex);
			}
		}
	}
	
	public void removeContact(Doctor doctor) {
		Connection conn = DataBaseConnect.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			String sql = null;
			sql = "DELETE FROM `rendeles` WHERE beosztas_id = ?";

			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, Integer.parseInt(doctor.getDoctorScheduleId()));
			preparedStatement.execute();
		} catch (SQLException ex) {
			System.out.println("Valami baj van a eszköz törlésekor");
			System.out.println("" + ex);
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				new ShowInfoError("Adatbázis Hiba", "Szerver válasza: ", e.getMessage());
			}
		}
	}
}
