package entity;

public class KhachHang {
	private String CCCD;;
	private String soTK;
	private String hoTen;
	private String soDT;
	private String diaChi;
	private String emali;
	private String maLoaiKH;
	
	
	
	public KhachHang(String cCCD, String soTK, String hoTen, String soDT, String diaChi, String emali,
			String maLoaiKH) {
		super();
		CCCD = cCCD;
		this.soTK = soTK;
		this.hoTen = hoTen;
		this.soDT = soDT;
		this.diaChi = diaChi;
		this.emali = emali;
		this.maLoaiKH = maLoaiKH;
	}
	public String getCCCD() {
		return CCCD;
	}
	public void setCCCD(String cCCD) {
		CCCD = cCCD;
	}
	public String getSoTK() {
		return soTK;
	}
	public void setSoTK(String soTK) {
		this.soTK = soTK;
	}
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
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
	public String getEmali() {
		return emali;
	}
	public void setEmali(String emali) {
		this.emali = emali;
	}
	public String getMaLoaiKH() {
		return maLoaiKH;
	}
	public void setMaLoaiKH(String maLoaiKH) {
		this.maLoaiKH = maLoaiKH;
	}
	
	@Override
	public String toString() {
		return "KhachHang [CCCD=" + CCCD + ", soTK=" + soTK + ", hoTen=" + hoTen + ", soDT=" + soDT + ", diaChi="
				+ diaChi + ", emali=" + emali + ", maLoaiKH=" + maLoaiKH + "]";
	}
}
