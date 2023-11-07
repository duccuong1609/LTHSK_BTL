package bTL_HSK.PhieuDatPhong;

import java.time.LocalDate;
import java.util.ArrayList;

import bTL_HSK.KhachHang.KhachHang;
import bTL_HSK.NhanVien.NhanVien;
import bTL_HSK.Phong.Phong;

public class PhieuDatPhong {
	private String maPD;
	private NhanVien nhanVien;
	private KhachHang khachHang;
	private ArrayList<Phong> phongs;
	private int soLuongPhong;
	private LocalDate ngayDen;
	private LocalDate ngayDi;
	public PhieuDatPhong(String maPD, NhanVien nhanVien, KhachHang khachHang, ArrayList<Phong> phongs, int soLuongPhong,
			LocalDate ngayDen, LocalDate ngayDi) {
		super();
		this.maPD = maPD;
		this.nhanVien = nhanVien;
		this.khachHang = khachHang;
		this.phongs = phongs;
		this.soLuongPhong = soLuongPhong;
		this.ngayDen = ngayDen;
		this.ngayDi = ngayDi;
	}
	public ArrayList<Phong> getPhongs() {
		return phongs;
	}
	public void setPhongs(ArrayList<Phong> phongs) {
		this.phongs = phongs;
	}
	public int getSoLuongPhong() {
		return soLuongPhong;
	}
	public void setSoLuongPhong(int soLuongPhong) {
		this.soLuongPhong = soLuongPhong;
	}
	public LocalDate getNgayDi() {
		return ngayDi;
	}
	public void setNgayDi(LocalDate ngayDi) {
		this.ngayDi = ngayDi;
	}
	public String getMaPD() {
		return maPD;
	}
	public NhanVien getNhanVien() {
		return nhanVien;
	}
	public KhachHang getKhachHang() {
		return khachHang;
	}
	public LocalDate getNgayDen() {
		return ngayDen;
	}
	
	
	
	
}
