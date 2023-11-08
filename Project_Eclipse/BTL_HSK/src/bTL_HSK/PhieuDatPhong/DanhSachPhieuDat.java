package bTL_HSK.PhieuDatPhong;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import javax.print.attribute.standard.MediaSize.NA;

import bTL_HSK.Dadabase.Database;
import bTL_HSK.KhachHang.DanhSachKhachHang;
import bTL_HSK.KhachHang.KhachHang;
import bTL_HSK.NhanVien.DanhSachNhanVien;
import bTL_HSK.NhanVien.NhanVien;
import bTL_HSK.Phong.DanhSachPhong;
import bTL_HSK.Phong.Phong;

public class DanhSachPhieuDat {
	private ArrayList< PhieuDatPhong> listPDP;
	private DanhSachNhanVien listNV = new DanhSachNhanVien();
	private DanhSachKhachHang listKH = new DanhSachKhachHang();
	private DanhSachPhong listPhong = new DanhSachPhong();
	
	public DanhSachPhieuDat() {
		listPDP = new ArrayList<PhieuDatPhong>();
		listKH.docDuLieu();
		listNV.docDuLieu();
		listPhong.docDuLieu();
	}
	
	
	public ArrayList<PhieuDatPhong> docDuLieu(){
		try {
			SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
			Connection con = Database.getInsConnect().getCon();
			Statement statement = con.createStatement();
			String sql = "select MaPhieuDat, MaNV, CCCD,NgayDen, NgayDi from PhieuDatPhong";
			ResultSet result = statement.executeQuery(sql);
			while(result.next()) {
				String maPD = result.getString(1);
				String maNV = result.getString(2);
				String CCCD = result.getString(3);
				Date ngayDen = date.parse(result.getString(4));
				Date ngayDi = date.parse(result.getString(5));
				NhanVien nv = listNV.getNhanVienByMa(maNV);
				KhachHang kh = listKH.getNhanVienByMa(CCCD);
				ArrayList<Phong> phongs = listPhong.getListPhongByPhieu(maPD);
				PhieuDatPhong a = new PhieuDatPhong(maPD, nv, kh, phongs, phongs.size(), ngayDen, ngayDi);
				listPDP.add(a);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listPDP;
	}


	@Override
	public String toString() {
		return "DanhSachPhieuDat [listPDP=" + listPDP ;
	}
	
	
	
	
}
