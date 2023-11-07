package bTL_HSK.DichVu;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import bTL_HSK.Dadabase.Database;

public class DanhSachDichVu {
	private ArrayList<DichVu> listDV;

	public DanhSachDichVu() {
		listDV = new ArrayList<DichVu>();
	}
	
	public ArrayList<DichVu> docDuLieu() {
		try {
			Connection con = Database.getInsConnect().getCon();
			String sql = "select * from DichVu";
			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while(result.next()) {
				String maDV = result.getString(1);
				String tenDV = result.getString(2);
				float giaDV = result.getFloat(3);
				DichVu a = new DichVu(maDV, tenDV, giaDV);
				listDV.add(a);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listDV;
	}

	@Override
	public String toString() {
		return "DanhSachDichVu [listDV=" + listDV + "]";
	}
	
	
}
