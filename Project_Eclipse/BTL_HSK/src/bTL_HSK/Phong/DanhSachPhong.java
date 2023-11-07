package bTL_HSK.Phong;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import bTL_HSK.Dadabase.Database;

public class DanhSachPhong {
	private ArrayList<Phong> listPhong;
	
	public DanhSachPhong() {
		// TODO Auto-generated constructor stub
		listPhong = new ArrayList<Phong>();
	}
	
	public ArrayList<Phong> docDuLieu(){
		try {
			Connection con = Database.getInsConnect().getCon();
			String sql = "select * from Phong";
			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while(result.next()) {
				int soPhong = result.getInt(1);
				String tenPhong = result.getString(2);
				String loaiPhong = result.getString(3);
				String isEmpty = result.getString(4);
				if(loaiPhong.equalsIgnoreCase("PV")) {
					PhongVip a = new PhongVip(soPhong, tenPhong, isEmpty.equals("1"));
					listPhong.add(a);
				}
				else {
					PhongThuong a = new PhongThuong(soPhong, tenPhong, isEmpty.equals("1"));
					listPhong.add(a);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listPhong;
	}

	public ArrayList<Phong> getListPhong() {
		return listPhong;
	}
	
	@Override
	public String toString() {
		return "DanhSachPhong [listPhong=" + listPhong + "]";
	}

	
	
	
}
