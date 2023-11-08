package bTL_HSK.KhachHang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import bTL_HSK.Dadabase.Database;
import bTL_HSK.NhanVien.NhanVien;

public class DanhSachKhachHang {
	private ArrayList< KhachHang> listKH;

	public DanhSachKhachHang() {
		listKH = new ArrayList<KhachHang>();
	}
	
	public ArrayList<KhachHang> docDuLieu() {
		try {
			Connection con = Database.getInsConnect().getCon();
			String sql = "select * from KhachHang";
			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while(result.next()) {
				String CCCD = result.getString(1);
				String soTK = result.getString(2);
				String hoTen = result.getString(3);
				String soDT = result.getString(4);
				String diaChi = result.getString(5);
				String email = result.getString(6);
				String maLoai = result.getString(7);
				if(maLoai.equalsIgnoreCase("V")) {
					KhachQuen a = new KhachQuen(CCCD, soTK, hoTen, soDT, diaChi, email);
					listKH.add(a);
				}
				else {
					KhachThuong a = new KhachThuong(CCCD, soTK, hoTen, soDT, diaChi, email);
					listKH.add(a);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listKH;
	}
	
	public boolean addKhachHang(KhachHang a) {
		Connection con = Database.getInsConnect().getCon();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("INSERT INTO KhachHang(CCCD,HoTen,SĐT,STK,DiaChi,Email,MaLoaiKH) VALUES (?,?,?,?,?,?,?)");
			stmt.setString(1, a.getCCCD());
			stmt.setString(2, a.getHoTen());
			stmt.setString(3, a.getSoDT());
			stmt.setString(4, a.getSoTK());
			stmt.setString(5, a.getDiaChi());
			stmt.setString(6, a.getEmali());
			stmt.setString(7, a.getMaLoaiKH());
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		docDuLieu();
		return false;
	}
	
	public boolean update(KhachHang a) {
		Connection con = Database.getInsConnect().getCon();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("update KhachHang set HoTen = (?),SĐT = (?),STK = (?),DiaChi = (?),Email = (?),MaLoaiKH = (?) WHERE CCCD = (?)");
			stmt.setString(1, a.getHoTen());
			stmt.setString(2, a.getSoDT());
			stmt.setString(3, a.getSoTK());
			stmt.setString(4, a.getDiaChi());
			stmt.setString(5, a.getEmali());
			stmt.setString(6, a.getMaLoaiKH());
			stmt.setString(7, a.getCCCD());
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public KhachHang getNhanVienByMa(String CCCD) {
		Connection con = Database.getInsConnect().getCon();
		PreparedStatement statement = null;
		try {
			statement = con.prepareStatement("select CCCD, STK, HoTen, SĐT, DiaChi, Email, MaLoaiKH from KhachHang where CCCD = ?");
			statement.setString(1, CCCD);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				String soTK = result.getString(2);
				String hoTen = result.getString(3);
				String soDT = result.getString(4);
				String diaChi = result.getString(5);
				String email = result.getString(6);
				String maLoai = result.getString(7);
				if(maLoai.equalsIgnoreCase("V")) {
					KhachQuen a = new KhachQuen(CCCD, soTK, hoTen, soDT, diaChi, email);
					return a;
				}
				else {
					KhachThuong a = new KhachThuong(CCCD, soTK, hoTen, soDT, diaChi, email);
					return a;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean delete(String CCCD) {
		Connection con = Database.getInsConnect().getCon();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("delete from KhachHang where CCCD = ?");
			stmt.setString(1, CCCD);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public ArrayList<KhachHang> getListKH() {
		return listKH;
	}

	@Override
	public String toString() {
		return "DanhSachKhachHang [listKH=" + listKH + "]";
	}
	
}
