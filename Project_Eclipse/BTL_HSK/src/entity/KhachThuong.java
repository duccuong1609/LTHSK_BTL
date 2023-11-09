package entity;

import java.sql.Connection;
import java.sql.PreparedStatement;

import ConnectDB.Database;

public class KhachThuong extends KhachHang {

	private static String maLoai = "N";
	private String tenLoai = "Khách Thường";
	private float khauTru = 0;
	
	
	public KhachThuong(String cCCD, String soTK, String hoTen, String soDT, String diaChi, String emali) {
		super(cCCD, soTK, hoTen, soDT, diaChi, emali, maLoai);
		// TODO Auto-generated constructor stub
	}


	public static String getMaLoai() {
		return maLoai;
	}


	public String getTenLoai() {
		return tenLoai;
	}


	public float getKhauTru() {
		return khauTru;
	}
	
	public boolean insert() {
		Connection con = Database.getInsConnect().getCon();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("insert into LoaiKhach values (?,?,?)");
			stmt.setString(1, maLoai);
			stmt.setString(2, tenLoai);
			stmt.setString(3, khauTru+"");
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	
}
