package Control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import ConnectDB.Database;
import entity.Phong;
import entity.PhongThuong;
import entity.PhongVip;

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

	
	public ArrayList<Phong> getListPhongTrong() {
		
		ArrayList<Phong> list = new ArrayList<Phong>(); 
		Connection con = Database.getInsConnect().getCon();
		PreparedStatement statement = null;
		
		try {
			statement = con.prepareStatement("{call getListTrangThaiPhong(?)}");
			statement.setInt(1, 1);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	
//	public ArrayList<Phong> getListPhongDaDat(){
//		
//	}
	
	
	public ArrayList<Phong> getListPhong() {
		return listPhong;
	}
	public ArrayList<Phong> getListPhongByPhieu(String maPhieu) {
		ArrayList<Phong> a = new ArrayList<Phong>();
		try {
			Connection con = Database.getInsConnect().getCon();
			PreparedStatement statement = con.prepareStatement("select ChiTietDatPhong.SoPhong , TenPhong, LoaiPhong, IsEmpty\r\n"
					+ "from PhieuDatPhong join ChiTietDatPhong on PhieuDatPhong.MaPhieuDat = ChiTietDatPhong.MaPhieuDat \r\n"
					+ "join Phong on ChiTietDatPhong.SoPhong = Phong.SoPhong\r\n"
					+ "where PhieuDatPhong.MaPhieuDat = ?");
			statement.setString(1,maPhieu);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				int soPhong = result.getInt(1);
				String tenPhong = result.getString(2);
				String loaiPhong = result.getString(3);
				String isEmpty = result.getString(4);
				if(loaiPhong.equalsIgnoreCase("PV")) {
					PhongVip b = new PhongVip(soPhong, tenPhong, isEmpty.equals("1"));
					a.add(b);
				}
				else {
					PhongThuong b = new PhongThuong(soPhong, tenPhong, isEmpty.equals("1"));
					a.add(b);
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return a;
		
	}
	@Override
	public String toString() {
		return "DanhSachPhong [listPhong=" + listPhong + "]";
	}

	
	
	
}
