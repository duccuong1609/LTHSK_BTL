package bTL_HSK.NhanVien;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import bTL_HSK.Dadabase.Database;

public class DanhSachNhanVien {
	private ArrayList< NhanVien> listNV;

	public DanhSachNhanVien() {
		listNV = new ArrayList<NhanVien>();
		
	}
	
	public ArrayList<NhanVien> docDuLieu(){
		try {
			Connection con = Database.getInsConnect().getCon();
			String sql = "select * from NhanVien";
			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while(result.next()) {
				String maNV = result.getString(1);
				String tenNV = result.getString(2);
				String soDT = result.getString(3);
				String diaChi =  result.getString(4);
				String email = result.getString(5);
				NhanVien a = new NhanVien(maNV, tenNV, soDT, diaChi, email);
				listNV.add(a);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return listNV;
	}
	
	
}	
