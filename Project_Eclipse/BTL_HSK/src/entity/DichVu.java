package entity;

import java.util.Objects;

public class DichVu {
	private String maDV;
	private String tenDV;
	private float gia;
	
	public DichVu(String maDV, String tenDV, float gia) {
		super();
		this.maDV = maDV;
		this.tenDV = tenDV;
		this.gia = gia;
	}
	public String getMaDV() {
		return maDV;
	}
	public void setMaDV(String maDV) {
		this.maDV = maDV;
	}
	public String getTenDV() {
		return tenDV;
	}
	public void setTenDV(String tenDV) {
		this.tenDV = tenDV;
	}
	public float getGia() {
		return gia;
	}
	public void setGia(float gia) {
		this.gia = gia;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maDV);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DichVu other = (DichVu) obj;
		return Objects.equals(maDV, other.maDV);
	}
	
	
	
	
}
