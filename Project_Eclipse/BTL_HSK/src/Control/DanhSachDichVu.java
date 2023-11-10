package Control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import ConnectDB.Database;
import entity.DichVu;

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
	public ArrayList<DichVu> getDichVu(){
		return listDV;
	}

	public ArrayList<DichVu> getDichVuByMaHD(String maHD){
		Connection con = Database.getInsConnect().getCon();
		PreparedStatement statement = null;
		ArrayList<DichVu> list = new ArrayList<DichVu>();
		try {
			statement = con.prepareStatement("select DichVu.MaDV, TenDV, GiaDV \r\n"
					+ "from HoaDon join ChiTietHoaDon \r\n"
					+ "	on HoaDon.MaHoaDon = ChiTietHoaDon.MaHoaDon join DichVu \r\n"
					+ "	on DichVu.MaDV = ChiTietHoaDon.MaDV\r\n"
					+ "where HoaDon.MaHoaDon = ?");
			statement.setString(1, maHD);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				String maDV = result.getString(1);
				String tenDV = result.getString(2);
				float giaDV = result.getFloat(3);
				DichVu a = new DichVu(maDV, tenDV, giaDV);
				list.add(a);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public String toString() {
		return "DanhSachDichVu [listDV=" + listDV + "]";
	}
	
	
}
