package entity;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import Control.DanhSachPhong;

public class PhieuDatPhong {
	
	private String maPD;
	private NhanVien nhanVien;
	private KhachHang khachHang;
	private DanhSachPhong phongs;
	private int soLuongPhong;
	private Date ngayDen;
	private Date ngayDi;
	public PhieuDatPhong(String maPD, NhanVien nhanVien, KhachHang khachHang, DanhSachPhong phongs, int soLuongPhong,
			Date ngayDen, Date ngayDi) {
		super();
		this.maPD = maPD;
		this.nhanVien = nhanVien;
		this.khachHang = khachHang;
		this.phongs = phongs;
		this.soLuongPhong = soLuongPhong;
		this.ngayDen = ngayDen;
		this.ngayDi = ngayDi;
	}
	public String getMaPD() {
		return maPD;
	}
	public void setMaPD(String maPD) {
		this.maPD = maPD;
	}
	public NhanVien getNhanVien() {
		return nhanVien;
	}
	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	public KhachHang getKhachHang() {
		return khachHang;
	}
	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}
	public DanhSachPhong getPhongs() {
		return phongs;
	}
	public void setPhongs(DanhSachPhong phongs) {
		this.phongs = phongs;
	}
	public int getSoLuongPhong() {
		return soLuongPhong;
	}
	public void setSoLuongPhong(int soLuongPhong) {
		this.soLuongPhong = soLuongPhong;
	}
	public Date getNgayDen() {
		return ngayDen;
	}
	public void setNgayDen(Date ngayDen) {
		this.ngayDen = ngayDen;
	}
	public Date getNgayDi() {
		return ngayDi;
	}
	public void setNgayDi(Date ngayDi) {
		this.ngayDi = ngayDi;
	}
	
	
	
	
	@Override
	public int hashCode() {
		return Objects.hash(maPD);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PhieuDatPhong other = (PhieuDatPhong) obj;
		return Objects.equals(maPD, other.maPD);
	}
	public Object[] getDataTable() {
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		Object[] data = new Object[6];
		data[0] = maPD;
		data[1] = nhanVien.getMaNV();
		data[2] = khachHang.getCCCD();
		data[3] = phongs.getListPhong().get(0).getSoPhong();
		data[4] = date.format(ngayDen);
		data[5] = date.format(ngayDi);
		return data;
	}

	
	
	
}
