package bTL_HSK;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	public static Connection con = null;
	private static Database insDatabase = new Database();
	
	public static Database getinsDatabase() {
		return insDatabase;
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
