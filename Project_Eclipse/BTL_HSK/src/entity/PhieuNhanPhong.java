package entity;

import java.util.Date;
import java.util.Objects;

public class PhieuNhanPhong {
	private String maPhieuNhan;
	private PhieuDatPhong pDP;
	private Date gioNhan;
	private Date ngayNhan;
	
	
	
	public PhieuNhanPhong(String maPhieuNhan, PhieuDatPhong pDP, Date gioNhan, Date ngayNhan) {
		super();
		this.maPhieuNhan = maPhieuNhan;
		this.pDP = pDP;
		this.gioNhan = gioNhan;
		this.ngayNhan = ngayNhan;
	}
	public String getMaPhieuNhan() {
		return maPhieuNhan;
	}
	public void setMaPhieuNhan(String maPhieuNhan) {
		this.maPhieuNhan = maPhieuNhan;
	}
	public PhieuDatPhong getpDP() {
		return pDP;
	}
	public void setpDP(PhieuDatPhong pDP) {
		this.pDP = pDP;
	}
	public Date getGioNhan() {
		return gioNhan;
	}
	public void setGioNhan(Date gioNhan) {
		this.gioNhan = gioNhan;
	}
	public Date getNgayNhan() {
		return ngayNhan;
	}
	public void setNgayNhan(Date ngayNhan) {
		this.ngayNhan = ngayNhan;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maPhieuNhan);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PhieuNhanPhong other = (PhieuNhanPhong) obj;
		return Objects.equals(maPhieuNhan, other.maPhieuNhan);
	}
	
	
	
	
}
