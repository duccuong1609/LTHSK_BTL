package bTL_HSK.PhieuNhanPhong;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import bTL_HSK.Dadabase.Database;

public class DanhSachPhieuNhan {
	private ArrayList<PhieuNhanPhong> listPN;
	
	public DanhSachPhieuNhan() {
		listPN = new ArrayList<PhieuNhanPhong>();
	}
	
	public ArrayList<PhieuNhanPhong> docDuLieu(){
		try {
			Connection con = Database.getInsConnect().getCon();
			Statement statement = con.createStatement();
			String sql = "";
			ResultSet result = statement.executeQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listPN;
	}
	
}
