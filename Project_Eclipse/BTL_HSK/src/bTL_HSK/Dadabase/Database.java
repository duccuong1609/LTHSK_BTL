package bTL_HSK.Dadabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	private static Connection con = null;
	private static Database insConnect = new Database();
	public static Connection getCon() {
		return con;
	}
	public static Database getInsConnect() {
		return insConnect;
	}
	
	public void connect() {
		String urlString = "jdbc:sqlserver://localhost:1433;databasename=QLKS";
		String userString = "BTL";
		String passwordString = "123456";
		try {
			con = DriverManager.getConnection(urlString,userString,passwordString);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public static Connection getConnection() {
		return con;
	}

	
}
