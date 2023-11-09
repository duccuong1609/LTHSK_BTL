package entity;

public class NhanVien {
	private String maNV; 
	private String tenNV;
	private String soDT;
	private String diaChi;
	private String email;
	
	
	
	public NhanVien(String maNV, String tenNV, String soDT, String diaChi, String email) {
		super();
		this.maNV = maNV;
		this.tenNV = tenNV;
		this.soDT = soDT;
		this.diaChi = diaChi;
		this.email = email;
	}
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
	public String getSoDT() {
		return soDT;
	}
	public void setSoDT(String soDT) {
		this.soDT = soDT;
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
	
	
	
	
}
