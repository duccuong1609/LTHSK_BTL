package Control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;


import ConnectDB.Database;
import entity.KhachHang;
import entity.NhanVien;
import entity.PhieuDatPhong;
import entity.Phong;

public class DanhSachPhieuDat {
	private ArrayList< PhieuDatPhong> listPDP;
	private DanhSachNhanVien listNV = new DanhSachNhanVien();
	private DanhSachKhachHang listKH = new DanhSachKhachHang();
	private DanhSachPhong listPhong = new DanhSachPhong();
	
	public DanhSachPhieuDat() {
		listPDP = new ArrayList<PhieuDatPhong>();
		
	}
	
	
	public ArrayList<PhieuDatPhong> docDuLieu(){
		try {
			SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
			
			Connection con = Database.getInsConnect().getCon();
			Statement statement = con.createStatement();
			String sql = "select MaPhieuDat, MaNV, CCCD,NgayDen, NgayDi,soLuong from PhieuDatPhong";
			ResultSet result = statement.executeQuery(sql);
			while(result.next()) {
				String maPD = result.getString(1);
				String maNV = result.getString(2);
				String CCCD = result.getString(3);
				Date ngayDen = date.parse(result.getString(4));
				Date ngayDi = date.parse(result.getString(5));
				int soLuong = result.getInt(6);
				NhanVien nv = listNV.getNhanVienByMa(maNV);
				KhachHang kh = listKH.getNhanVienByMa(CCCD);
				DanhSachPhong phongs = listPhong.getListPhongByPhieu(maPD);
				PhieuDatPhong a = new PhieuDatPhong(maPD, nv, kh, phongs, soLuong, ngayDen, ngayDi);
				listPDP.add(a);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listPDP;
	}

	public boolean addPhieuDat(PhieuDatPhong a) {
		return listPDP.add(a);
	}
	
	public PhieuDatPhong getPhieuDatPhongByMa(String maPhieu) {
		PhieuDatPhong a = new PhieuDatPhong(maPhieu, null, null, listPhong, 0, null, null);
		int index = listPDP.indexOf(a);
		if(index == -1)
			return null;
		return a;
	}
	
//	Hàm lấy dữ liệu Những phiếu chưa trả phòng
	
	public DanhSachPhieuDat getListPhongChuaTra() {
		DanhSachPhieuDat a = new DanhSachPhieuDat();
		Connection con = Database.getInsConnect().getCon();
		docDuLieu();
		try {
			Statement statement  = con.createStatement();
			ResultSet result = statement.executeQuery("{call getListPhongChuaTra}");
			while(result.next()) {
				String maPhieu = result.getString(1);
				PhieuDatPhong a1 = getPhieuDatPhongByMa(maPhieu);
				if(a1 != null)
					a.addPhieuDat(a1);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return a;
		
	}
	
//	Hàm này lấy Phiếu đtặ phòng theo maPhieu
	public PhieuDatPhong getPhieuDat(String maPhieu) {
		PhieuDatPhong a = new PhieuDatPhong(maPhieu, null, null, null, 0, null, null);
		int index = listPDP.indexOf(a);
		return listPDP.get(index);
	}
	
	@Override
	public String toString() {
		return "DanhSachPhieuDat [listPDP=" + listPDP ;
	}
	
	
	
	
}
