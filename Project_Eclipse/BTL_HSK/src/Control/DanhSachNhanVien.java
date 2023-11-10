package Control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import ConnectDB.Database;
import entity.NhanVien;

public class DanhSachNhanVien {
	private ArrayList< NhanVien> listNV;
	public DanhSachNhanVien() {
		listNV = new ArrayList<NhanVien>();
		
	}
	
	public ArrayList<NhanVien> docDuLieu(){
		try {
			listNV = new ArrayList<NhanVien>();
			Connection con = Database.getInsConnect().getCon();
			String sql = "select * from NhanVien";
			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while(result.next()) {
				String maNV = result.getString(1);
				String tenNV = result.getString(2);
				String soDT = result.getString(3);
				String diaChi =  result.getString(4);
				String email = result.getString(5);
				NhanVien a = new NhanVien(maNV, tenNV, soDT, diaChi, email);
				listNV.add(a);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return listNV;
	}
	
	public boolean updateNV(NhanVien a) {
		Connection con = Database.getInsConnect().getCon();
		PreparedStatement statement = null;
		try {
			statement = con.prepareStatement("update NhanVien set TenNV = ?, SĐT = ?, DiaChi = ?, Email = ?");
			statement.setString(1, a.getTenNV());
			statement.setString(2, a.getSoDT());
			statement.setString(3, a.getDiaChi());
			statement.setString(4, a.getEmail());
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
	public ArrayList<NhanVien> get_listNV(){
		return listNV;
	}
	
	
	public NhanVien getNhanVienByMa(String ma) {
		Connection con = Database.getInsConnect().getCon();
		PreparedStatement statement = null;
		try {
			statement = con.prepareStatement("select MaNV, TenNV, SĐT ,DiaChi, Email from NhanVien where MaNV = ?");
			statement.setString(1, ma);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				String maNV = result.getString(1);
				String tenNV = result.getString(2);
				String soDT = result.getString(3);
				String diaChi = result.getString(4);
				String email = result.getString(5);
				NhanVien a = new NhanVien(maNV, tenNV, soDT, diaChi, email);
				return a;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean removeNV(String ma) {
		Connection con = Database.getInsConnect().getCon();
		PreparedStatement statement = null;
		try {
			statement = con.prepareStatement("delete from NhanVien where MaNV = ?");
			statement.setString(1, ma);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public String toString() {
		return "DanhSachNhanVien [listNV=" + listNV + "]";
	}
	
}	
