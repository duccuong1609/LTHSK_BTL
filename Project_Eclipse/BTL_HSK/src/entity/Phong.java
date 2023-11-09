package entity;

public class Phong {
	private int soPhong;
	private String tenPhong;
	private String loaiPhong;
	private boolean IsEmpty;
	public Phong(int soPhong, String tenPhong, String loaiPhong, boolean isEmpty) {
		super();
		this.soPhong = soPhong;
		this.tenPhong = tenPhong;
		this.loaiPhong = loaiPhong;
		IsEmpty = isEmpty;
	}
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
	public boolean isIsEmpty() {
		return IsEmpty;
	}
	public void setIsEmpty(boolean isEmpty) {
		IsEmpty = isEmpty;
	}
	
	
	
	
}
