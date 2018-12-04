package application.disposition.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import application.disposition.pojo.Doctor;
import application.setting.database.DataBaseConnect;
import application.setting.showinfo.ShowInfoError;

public class DispositionDataBase {

	public void addDoctor(Doctor doctor) {
		Connection con = DataBaseConnect.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			String sql = "INSERT INTO `orvos` (orvos_nev, orvos_hater_szin, orvos_betu_szin) values (?,?,?)";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, doctor.getDoctorName());
			preparedStatement.setString(2, doctor.getDoctorBackgroundColor());
			preparedStatement.setString(3, doctor.getDoctorTextColor());
			preparedStatement.execute();
		} catch (SQLException ex) {
			new ShowInfoError("Adatbázis Hiba", "Szerver válasza: ", ex.getMessage());
			System.out.println("Valami baj van a contact hozzáadásakor");
			System.out.println("" + ex);
		}
	}
}
