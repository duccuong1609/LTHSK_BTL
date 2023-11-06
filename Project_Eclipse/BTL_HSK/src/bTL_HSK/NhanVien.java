package bTL_HSK;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;

//------------------------------------NhanVien------------------------------------------//
public class NhanVien {
	private String maNV;
	private String tenNV;
	private String sĐT;
	private String diaChi;
	private String email;
	
	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	public String getTenNV() {
		return tenNV;
	}
	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}
	public String getsĐT() {
		return sĐT;
	}
	public void setsĐT(String sĐT) {
		this.sĐT = sĐT;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public NhanVien(String maNV, String tenNV, String sĐT, String diaChi, String email) {
		super();
		this.maNV = maNV;
		this.tenNV = tenNV;
		this.sĐT = sĐT;
		this.diaChi = diaChi;
		this.email = email;
	}
	@Override
	public String toString() {
		return "NhanVien [maNV=" + maNV + ", tenNV=" + tenNV + ", sĐT=" + sĐT + ", diaChi=" + diaChi + ", email="
				+ email + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(maNV);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhanVien other = (NhanVien) obj;
		return Objects.equals(maNV, other.maNV);
	}
	public NhanVien(String maNV) {
		super();
		this.maNV = maNV;
	}
}
//------------------------------------DS_NhanVien------------------------------------------//
class DS_NhanVien{
	ArrayList<NhanVien> list;

	public DS_NhanVien() {
		list = new ArrayList<NhanVien>();
		list = docTuBang();
	}
	
	public ArrayList<NhanVien> docTuBang(){
		try {
			Connection con = Database.getinsDatabase().getConnection();
			String sql = "select * from NhanVien";
			Statement statement = con.createStatement();
			ResultSet rSet = statement.executeQuery(sql);
			
			while (rSet.next()) {
				String ma = rSet.getString(1);
 				String ten = rSet.getString(2);
				String sdt = rSet.getString(3);
				String diachi = rSet.getString(4);
				String email = rSet.getString(5);
				NhanVien nv = new NhanVien(ma, ten, sdt, diachi, email);
				list.add(nv);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	public ArrayList<NhanVien> get_list(){
		return list;
	}
}
