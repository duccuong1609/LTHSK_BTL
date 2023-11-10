package Control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import ConnectDB.Database;
import entity.KhachHang;
import entity.KhachQuen;
import entity.KhachThuong;
import entity.NhanVien;

public class DanhSachKhachHang {
	private ArrayList< KhachHang> listKH;

	public DanhSachKhachHang() {
		listKH = new ArrayList<KhachHang>();
	}
	
	public ArrayList<KhachHang> docDuLieu() {
		try {
			listKH = new ArrayList<KhachHang>();
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
		listKH.add(a);
		int n = 0;
		try {
			stmt = con.prepareStatement("INSERT INTO KhachHang(CCCD,HoTen,SĐT,STK,DiaChi,Email,MaLoaiKH) VALUES (?,?,?,?,?,?,?)");
			stmt.setString(1, a.getCCCD());
			stmt.setString(2, a.getHoTen());
			stmt.setString(3, a.getSoDT());
			stmt.setString(4, a.getSoTK());
			stmt.setString(5, a.getDiaChi());
			stmt.setString(6, a.getEmali());
			stmt.setString(7, a.getMaLoaiKH());
			n = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return n > 0;
	}
	
	public boolean update(KhachHang a) {
		Connection con = Database.getInsConnect().getCon();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update KhachHang set HoTen = (?),SĐT = (?),STK = (?),DiaChi = (?),Email = (?),MaLoaiKH = (?) WHERE CCCD = (?)");
			stmt.setString(1, a.getHoTen());
			stmt.setString(2, a.getSoDT());
			stmt.setString(3, a.getSoTK());
			stmt.setString(4, a.getDiaChi());
			stmt.setString(5, a.getEmali());
			stmt.setString(6, a.getMaLoaiKH());
			stmt.setString(7, a.getCCCD());
			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n>0;
	}
	
	public KhachHang getKhachHangByMa(String CCCD) {
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
		int n = 0;
		try {
			stmt = con.prepareStatement("delete from KhachHang where CCCD = ?");
			stmt.setString(1, CCCD);
			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	public ArrayList<KhachHang> getListKH() {
		return listKH;
	}
	
	

	@Override
	public String toString() {
		return "DanhSachKhachHang [listKH=" + listKH + "]";
	}
	
}
