package Control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.print.attribute.standard.MediaSize.NA;

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
			listPhong = new ArrayList<Phong>();
			Connection con = Database.getInsConnect().getCon();
			String sql = "select * from Phong";
			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while(result.next()) {
				int soPhong = result.getInt(1);
				String tenPhong = result.getString(2);
				String loaiPhong = result.getString(3);
				String isEmpty = result.getString(4);
				if(loaiPhong.equalsIgnoreCase("SUP")) {
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

	
	public boolean updateTrangThaiPhong(int soPhong, int trangThai) {
		Connection con = Database.getInsConnect().getCon();
		PreparedStatement statement = null;
		int n = 0;
		try {
			statement = con.prepareStatement("{call updateTrangThaiPhong(?,?)}");
			statement.setInt(1, soPhong);
			statement.setInt(2, trangThai);
			n = statement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	
	
	public  boolean addPhong(Phong a) {
		return listPhong.add(a);
	}
	
	public DanhSachPhong getListTrangThaiPhong(int index) {
		DanhSachPhong list = new DanhSachPhong(); 
		Connection con = Database.getInsConnect().getCon();
		PreparedStatement statement = null;
		try {
			statement = con.prepareStatement("{call getListTrangThaiPhong(?)}");
			statement.setInt(1, index);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				int soPhong = result.getInt(1);
				String tenPhong = result.getString(2);
				String loaiPhong = result.getString(3);
				String isEmpty = result.getString(4);
				if("SUP".equals(loaiPhong)) {
					PhongVip a = new PhongVip(soPhong, tenPhong, isEmpty.equals("1"));
					list.addPhong(a);
				}
				else {
					PhongThuong a = new PhongThuong(soPhong, tenPhong, isEmpty.equals("1"));
					list.addPhong(a);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	
	public Phong getPhongBySoPhong(int soPhong) {
		for(Phong phong : listPhong) {
			if(phong.getSoPhong()== soPhong) {
				return phong;
			}
		}
		return null;
	}
//	public ArrayList<Phong> getListPhongDaDat(){
//		
//	}
	
	
	public ArrayList<Phong> getListPhong() {
		return listPhong;
	}
	public DanhSachPhong getListPhongByPhieuDat(String maPhieu) {
		DanhSachPhong a = new DanhSachPhong();
		try {
			Connection con = Database.getInsConnect().getCon();
			PreparedStatement statement = con.prepareStatement("{call getListPhongByMaPhieuDat(?)}");
			statement.setString(1,maPhieu);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				int soPhong = result.getInt(1);
				String tenPhong = result.getString(2);
				String loaiPhong = result.getString(3);
				String isEmpty = result.getString(4);
				if(loaiPhong.equalsIgnoreCase("PV")) {
					PhongVip b = new PhongVip(soPhong, tenPhong, isEmpty.equals("1"));
					a.addPhong(b);
				}
				else {
					PhongThuong b = new PhongThuong(soPhong, tenPhong, isEmpty.equals("1"));
					a.addPhong(b);
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
		String result = listPhong.get(0).getTieuDe();
		String a = "";
		for(int i = 0; i< listPhong.size();i++) {
			a+= "\n" + listPhong.get(i).toString();
		}
		
		return result+a;
	}

	
	
	
}
