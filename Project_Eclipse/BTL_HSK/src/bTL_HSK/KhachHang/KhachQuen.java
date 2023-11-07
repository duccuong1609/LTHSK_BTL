package bTL_HSK.KhachHang;

import java.sql.Connection;
import java.sql.PreparedStatement;

import bTL_HSK.Dadabase.Database;

public class KhachQuen extends KhachHang {

	
	private static String maLoai = "V";
	private String tenLoai = "Khách Quen";
	private float khauTru = 0.3f;
	
	public KhachQuen(String cCCD, String soTK, String hoTen, String soDT, String diaChi, String emali) {
		super(cCCD, soTK, hoTen, soDT, diaChi, emali, maLoai);
		// TODO Auto-generated constructor stub
	}

	public static String getMaLoai() {
		return maLoai;
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

	public String getTenLoai() {
		return tenLoai;
	}

	public float getKhauTru() {
		return khauTru;
	}
	
}
