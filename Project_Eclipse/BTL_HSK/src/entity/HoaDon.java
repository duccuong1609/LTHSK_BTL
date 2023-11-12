package entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import Control.DanhSachDichVu;

public class HoaDon {
	private String maHD;
	private PhieuNhanPhong maPhieuNhan;
	private ArrayList<DichVu> listDV;
	private Date ngayTra;
	private Date gioTra;
	public HoaDon(String maHD, PhieuNhanPhong maPhieuNhan, ArrayList<DichVu> listDV, Date ngayTra, Date gioTra) {
		this.maHD = maHD;
		this.maPhieuNhan = maPhieuNhan;
		this.listDV = listDV;
		this.ngayTra = ngayTra;
		this.gioTra = gioTra;
	}
	public String getMaHD() {
		return maHD;
	}
	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}
	public PhieuNhanPhong getMaPhieuNhan() {
		return maPhieuNhan;
	}
	public void setMaPhieuNhan(PhieuNhanPhong maPhieuNhan) {
		this.maPhieuNhan = maPhieuNhan;
	}
	public ArrayList<DichVu> getListDV() {
		return listDV;
	}
	public void setListDV(ArrayList<DichVu> listDV) {
		this.listDV = listDV;
	}
	public Date getNgayTra() {
		return ngayTra;
	}
	public void setNgayTra(Date ngayTra) {
		this.ngayTra = ngayTra;
	}
	public Date getGioTra() {
		return gioTra;
	}
	public void setGioTra(Date gioTra) {
		this.gioTra = gioTra;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maHD);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HoaDon other = (HoaDon) obj;
		return Objects.equals(maHD, other.maHD);
	}
	
	
}
