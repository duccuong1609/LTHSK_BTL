package bTL_HSK.PhieuDatPhong;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import bTL_HSK.Dadabase.Database;

public class DanhSachPhieuDat {
	private ArrayList< PhieuDatPhong> listPDP;
	
	public DanhSachPhieuDat() {
		listPDP = new ArrayList<PhieuDatPhong>();
	}
	
	
	public ArrayList<PhieuDatPhong> docDuLieu(){
		try {
			Connection con = Database.getInsConnect().getCon();
			Statement statement = con.createStatement();
			String sql = "select CCCD, NgayDen from PhieuDatPhong";
			ResultSet result = statement.executeQuery(sql);
			while(result.next()) {
				String CCCD = result.getString(1);
				Date ngayDen = result.getDate(2);
				sql = "select * from PhieuDatPhong where CCCD = ? AND NgayDen = ?";
				ResultSet a1 = statement.executeQuery(sql);
				while(a1.next()) {
					
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return listPDP;
		
	}
	
	
}
