package bTL_HSK;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;

//--------------------------------PhongKhachSan-------------------------------------------//

public class PhongKhachSan {
	private int soPhong;
	private String tenPhong;
	private String loaiPhong;
	private boolean Empty;
	
	public int getSoPhong() {
		return soPhong;
	}
	public void setSoPhong(int soPhong) {
		this.soPhong = soPhong;
	}
	public String getTenPhong() {
		return tenPhong;
	}
	public void setTenPhong(String tenPhong) {
		this.tenPhong = tenPhong;
	}
	public String getLoaiPhong() {
		return loaiPhong;
	}
	public void setLoaiPhong(String loaiPhong) {
		this.loaiPhong = loaiPhong;
	}
	public boolean isEmpty() {
		return Empty;
	}
	public void setEmpty(boolean empty) {
		Empty = empty;
	}
	@Override
	public int hashCode() {
		return Objects.hash(soPhong);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PhongKhachSan other = (PhongKhachSan) obj;
		return soPhong == other.soPhong;
	}
	public PhongKhachSan(int soPhong) {
		super();
		this.soPhong = soPhong;
	}
	
	public PhongKhachSan(int soPhong, String tenPhong, String loaiPhong, boolean empty) {
		super();
		this.soPhong = soPhong;
		this.tenPhong = tenPhong;
		this.loaiPhong = loaiPhong;
		Empty = empty;
	}
	@Override
	public String toString() {
		return "PhongKhachSan [soPhong=" + soPhong + ", tenPhong=" + tenPhong + ", loaiPhong=" + loaiPhong + ", Empty="
				+ Empty + "]";
	}
}

//--------------------------------DS_PhongKhachSan-------------------------------------------//

class DS_PhongKhachSan{
	ArrayList<PhongKhachSan> list;

	public DS_PhongKhachSan() {
		list = new ArrayList<PhongKhachSan>();
	}
	
	public ArrayList<PhongKhachSan> docTuBang(){
		try {
			Connection con = Database.getinsDatabase().getConnection();
			String sql = "select * from Phong";
			Statement statement = con.createStatement();
			ResultSet rSet = statement.executeQuery(sql);
			
			while (rSet.next()) {
				int soPhong = rSet.getInt(1);
 				String tenPhong = rSet.getString(2);
				String LoaiPhong = rSet.getString(3);
				String bit = rSet.getString(4);
				boolean empty = false;
				if(bit.equals("1")) {
					empty = true;
				}
				PhongKhachSan phong = new PhongKhachSan(soPhong, tenPhong, LoaiPhong, empty);
				list.add(phong);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	public ArrayList<PhongKhachSan> get_list(){
		return list;
	}
}

//--------------------------------DS_LoaiPhongKhachSan-------------------------------------------//

class DS_LoaiPhongKhachSan{
	ArrayList<LoaiPhongKhachSan> list;

	public DS_LoaiPhongKhachSan() {
		list = new ArrayList<LoaiPhongKhachSan>();
		list = docTuBang();
	}
	
	public ArrayList<LoaiPhongKhachSan> docTuBang(){
		try {
			Connection con = Database.getinsDatabase().getConnection();
			String sql = "select * from LoaiPhong";
			Statement statement = con.createStatement();
			ResultSet rSet = statement.executeQuery(sql);
			
			while (rSet.next()) {
				String ma = rSet.getString(1);
				String ten = rSet.getString(2);
				int gia = rSet.getInt(3);

				LoaiPhongKhachSan s = new LoaiPhongKhachSan(ma, ten, gia);
				list.add(s);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	public ArrayList<LoaiPhongKhachSan> get_list(){
		return list;
	}
}

//--------------------------------LoaiPhongKhachSan-------------------------------------------//

class LoaiPhongKhachSan {
	private String maLoai;
	private String tenLoai;
	private int giaPhong;
	
	public String getMaLoai() {
		return maLoai;
	}
	public void setMaLoai(String maLoai) {
		this.maLoai = maLoai;
	}
	public String getTenLoai() {
		return tenLoai;
	}
	public void setTenLoai(String tenLoai) {
		this.tenLoai = tenLoai;
	}
	public int getGiaPhong() {
		return giaPhong;
	}
	public void setGiaPhong(int giaPhong) {
		this.giaPhong = giaPhong;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(maLoai);
	}
	
	public LoaiPhongKhachSan(String maLoai, String tenLoai, int giaPhong) {
		super();
		this.maLoai = maLoai;
		this.tenLoai = tenLoai;
		this.giaPhong = giaPhong;
	}
	
	public LoaiPhongKhachSan(String maLoai) {
		super();
		this.maLoai = maLoai;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoaiPhongKhachSan other = (LoaiPhongKhachSan) obj;
		return Objects.equals(maLoai, other.maLoai);
	}
	
	@Override
	public String toString() {
		return "LoaiPhongKhachSan [maLoai=" + maLoai + ", tenLoai=" + tenLoai + ", giaPhong=" + giaPhong + "]";
	}
}
