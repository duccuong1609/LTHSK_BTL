package entity;

import java.util.Objects;

public class Phong {
	private int soPhong;
	private String tenPhong;
	
	private boolean IsEmpty;
	public Phong(int soPhong, String tenPhong, boolean isEmpty) {
		super();
		this.soPhong = soPhong;
		this.tenPhong = tenPhong;
		
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
	
	public boolean isIsEmpty() {
		return IsEmpty;
	}
	public void setIsEmpty(boolean isEmpty) {
		IsEmpty = isEmpty;
	}
	
	public String getTieuDe() {
		return String.format("%s %s %s ", "So phong","Ten phong","Trang thai");
	}
	
	
	public Phong(int soPhong) {
		super();
		this.soPhong = soPhong;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("%s %s %s ", soPhong,tenPhong,IsEmpty ? "Yes":"No");
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
		Phong other = (Phong) obj;
		return soPhong == other.soPhong;
	}
	
	
	
}
