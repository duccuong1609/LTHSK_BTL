package Control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

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
		listNV.docDuLieu();
		listKH.docDuLieu();
		listPhong.docDuLieu();
	}
	
	// doc du lieu
	
	public ArrayList<PhieuDatPhong> docDuLieu(){
		try {
			listPDP = new ArrayList<PhieuDatPhong>();
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
				KhachHang kh = listKH.getKhachHangByMa(CCCD);
				DanhSachPhong phongs = listPhong.getListPhongByPhieuDat(maPD);
				PhieuDatPhong a = new PhieuDatPhong(maPD, nv, kh, phongs, soLuong, ngayDen, ngayDi);
				listPDP.add(a);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listPDP;
	}
	
	// insert phieu dat phong va update trang thai phong
	
	public boolean insertPhieuDatSQL(PhieuDatPhong a) {
		Connection con = Database.getInsConnect().getCon();
		PreparedStatement statement = null;
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		int n = 0;
		try {
			statement = con.prepareStatement("{call insertPhieuDat(?,?,?,?,?,?)}");
			statement.setString(1, a.getMaPD());
			statement.setString(2, a.getNhanVien().getMaNV());
			statement.setString(3, a.getKhachHang().getCCCD());
			statement.setInt(4, a.getSoLuongPhong());
			statement.setString(5, date.format(a.getNgayDen()));
			statement.setString(6, date.format(a.getNgayDi()));
			n = statement.executeUpdate();
			if(n > 0) {
				DanhSachPhong phongs = a.getPhongs();
				for(int i = 0; i < phongs.getListPhong().size();i++ ) {
					phongs.updateTrangThaiPhong(phongs.getListPhong().get(i).getSoPhong(), 0);
				}
				n = insertChiTietDatPhong(a) ? 1 : 0;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	
	
	
	// update phieuu dat
	public boolean updatePhieuDat(PhieuDatPhong a) {
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		Connection con = Database.getInsConnect().getCon();
		PreparedStatement statement = null;
		int n = 0;
		try {
			statement = con.prepareStatement("{call updatePhieuDat(?,?,?,?,?,?)}");
			statement.setString(1, a.getMaPD());
			statement.setString(2, a.getNhanVien().getMaNV());
			statement.setString(3, a.getKhachHang().getCCCD());
			statement.setInt(4, a.getSoLuongPhong());
			statement.setString(5, date.format(a.getNgayDen()));
			statement.setString(6, date.format(a.getNgayDi()));
			n = statement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	
// 	insert chi tiet dat phong
	public boolean insertChiTietDatPhong(PhieuDatPhong a) {
		Connection con = Database.getInsConnect().getCon();
		PreparedStatement statement = null;
		int n = 0;
		try {
			DanhSachPhong p = a.getPhongs();
			for(int i = 0; i < p.getListPhong().size();i++) {
				statement = con.prepareStatement("{call insertChiTietDatPhong(?,?)}");
				statement.setString(1,a.getMaPD());
				statement.setInt(2, p.getListPhong().get(i).getSoPhong());
			}
			n = statement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	
//	tthem phieu dat vao list
	
	public boolean addPhieuDat(PhieuDatPhong a) {
		return listPDP.add(a);
	}
	
	public boolean deleteChiTietPhieuDat(PhieuDatPhong a) {
		Connection con = Database.getInsConnect().getCon();
		PreparedStatement statement = null;
		int n = 0;
		try {
			DanhSachPhong p = a.getPhongs();
			for(int i = 0; i < p.getListPhong().size();i++) {
				statement = con.prepareStatement("delete ChiTietDatPhong where MaPhieuDat = ? AND SoPhong = ?");
				statement.setString(1,a.getMaPD());
				statement.setInt(2, p.getListPhong().get(i).getSoPhong());
				
			}
			n = statement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return n > 0;
	}
	
	public boolean deletePhieuDat(PhieuDatPhong pd) {
		Connection con = Database.getInsConnect().getCon();
		PreparedStatement statement = null;
		System.out.println(pd.getMaPD());
		int n = 0;
		try {
			System.out.println(deleteChiTietPhieuDat(pd));
			statement = con.prepareStatement("delete from PhieuDatPhong where MaPhieuDat = ?");
			statement.setString(1, pd.getMaPD());
			n = statement.executeUpdate();
			if(n > 0) {
				DanhSachPhong phongs = pd.getPhongs();
				for(int i = 0; i < phongs.getListPhong().size();i++ ) {
					phongs.updateTrangThaiPhong(phongs.getListPhong().get(i).getSoPhong(), 1);
				}
			
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return n > 0;
	}
	
	// Ham lay phieu dat bang CCCD va ngay den
	
	public PhieuDatPhong getPhieuDatByCCCD_NgayDen(String CCCD, String ngayDen) {
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		Date d = null;
		try {
			d = date.parse(ngayDen);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DanhSachPhieuDat pdCCCD = getPhieuDatByCCCD(CCCD);
		for(int i = 0; i < pdCCCD.getListPDP().size();i++) {
			PhieuDatPhong a = pdCCCD.getListPDP().get(i);
			if(a.getNgayDen().compareTo(d) == 0) {
				return a;
			}
		}
		return null;
	}
	
	// hham lay danh sach phieu dat bang CCCD
	
	public DanhSachPhieuDat getPhieuDatByCCCD(String CCCD) {
		DanhSachPhieuDat a = new DanhSachPhieuDat();
		for(PhieuDatPhong pd : listPDP) {
			if(pd.getKhachHang().getCCCD().equalsIgnoreCase(CCCD))
				a.addPhieuDat(pd);
		}
		return a;
	}
	
//	Ham lay Phieu dat theo phieu
	
	
	
	public PhieuDatPhong getPhieuDatPhongByMa(String maPhieu) {
		for(int i = 0; i < listPDP.size();i++) {
			if(listPDP.get(i).getMaPD().equals(maPhieu)) {
				return listPDP.get(i);
			}
		}
		return null;
		
	}
	
//	Hàm lấy dữ liệu Những phiếu chưa trả phòng
	
	public DanhSachPhieuDat getPhieuDatPhongChuaNhan() {
		DanhSachPhieuDat a = new DanhSachPhieuDat();
		Connection con = Database.getInsConnect().getCon();
		docDuLieu();
		try {
			Statement statement  = con.createStatement();
			ResultSet result = statement.executeQuery("{call getListPhongChuaNhan}");
			while(result.next()) {
				String maPhieu = result.getString(1);
				PhieuDatPhong a1 = getPhieuDatPhongByMa(maPhieu);
				if(a1 != null)
					a.addPhieuDat(a1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return a;
		
	}
	
	
	
	
//	Ham lay list Phieu datt
	
	public ArrayList<PhieuDatPhong> getListPDP() {
		return listPDP;
	}
	
	@Override
	public String toString() {
		return "DanhSachPhieuDat [listPDP=" + listPDP ;
	}
	
	
	
	
}
