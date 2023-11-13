package Control;

import static org.junit.Assert.isArray;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import ConnectDB.Database;
import entity.DichVu;
import entity.HoaDon;
import entity.PhieuNhanPhong;

public class DanhSachHoaDon {
	private ArrayList<HoaDon> listHD;
	private DanhSachPhieuNhan listPN = new DanhSachPhieuNhan();
	private DanhSachDichVu listDV = new DanhSachDichVu();
	public DanhSachHoaDon() {
		listHD = new ArrayList<HoaDon>();
		listPN.docDuLieu();
		listDV.docDuLieu();
	}
	
	public ArrayList<HoaDon> docDuLieu(){
		try {
			listHD = new ArrayList<HoaDon>();
			Connection con = Database.getInsConnect().getCon();
			SimpleDateFormat dateTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
			Statement statement = con.createStatement();
			String sql = "select MaHoaDon, MaPhieu, NgayTra, GioTra from HoaDon";
			ResultSet result = statement.executeQuery(sql);
			while(result.next()) {
				String maHD = result.getString(1);
				String maPN = result.getString(2);
				PhieuNhanPhong a = listPN.getPhieuNhanByMa(maPN);
				Date ngayTra = date.parse(result.getString(3));
				Date gioTra = dateTime.parse(result.getString(4));
				ArrayList<DichVu> dichvus = listDV.getDichVuByMaHD(maHD);
				HoaDon b = new HoaDon(maHD, a, dichvus, ngayTra, gioTra);
				listHD.add(b);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listHD;
	}

	public DanhSachHoaDon getListFromDateToDate(Date tuNgay, Date denNgay) {
		DanhSachHoaDon danhSachHoaDon = new DanhSachHoaDon();
		for(int i=0; i < listHD.size();i++) {
			if(listHD.get(i).getNgayTra().compareTo(tuNgay)>=0 && denNgay.compareTo(listHD.get(i).getNgayTra() ) >=0) {
				danhSachHoaDon.add(listHD.get(i));
			}
		}
		return danhSachHoaDon;
	}
	
	
	
	public boolean addHoaDon(HoaDon a) {
		Connection con = Database.getInsConnect().getCon();
		PreparedStatement statement = null;
		int n = 0;
		try {
			SimpleDateFormat dateTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
			statement = con.prepareStatement("INSERT INTO HoaDon(MaHoaDon,MaPhieu,NgayTra,GioTra)\r\n"
					+ "VALUES	\r\n"
					+ "		(?,?, ?, ?)");
			statement.setString(1, a.getMaHD());
			statement.setString(2, a.getMaPhieuNhan().getMaPhieuNhan());
			statement.setString(3, dateTime.format(a.getNgayTra()));
			statement.setString(4, date.format(a.getGioTra()));
			n = statement.executeUpdate();
			if(n > 0 && a.getListDV().size() > 0)
				addChiTietHoaDon(a);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public HoaDon getHoaDonByMa(String maHoaDon) {
		for(int i = 0; i < listHD.size();i++) {
			if(listHD.get(i).getMaHD().equalsIgnoreCase(maHoaDon)) {
				return listHD.get(i);
			}
		}
		return null;
	}
	
	public ArrayList<HoaDon> getListHD() {
		return listHD;
	}
	
	public boolean add(HoaDon a) {
		return listHD.add(a);
	}
	
	public boolean deteleChiTietHoaDon(HoaDon a) {
		Connection con = Database.getInsConnect().getCon();
		PreparedStatement statement = null;
		int n = 0;
		try {
			for(int i = 0; i < a.getListDV().size();i++) {
				statement = con.prepareStatement("delete from ChiTietHoaDon where MaHoaDon = ? AND MaDV = ?");
				statement.setString(1, a.getMaHD());
				statement.setString(2, a.getListDV().get(i).getMaDV());
				n = statement.executeUpdate();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return n > 0;
	}
	
	public boolean deteleHoaDon(HoaDon a) {
		Connection con = Database.getInsConnect().getCon();
		PreparedStatement statement = null;
		int n = 0;
		try {
			deteleChiTietHoaDon(a);
			statement = con.prepareStatement("delete from HoaDon where MaHoaDon = ?");
			statement.setString(1, a.getMaHD());
			n = statement.executeUpdate();
			if(n > 0) {
				boolean b = new DanhSachPhieuNhan().deletePhieuNhan(a.getMaPhieuNhan()) ? new DanhSachPhieuDat().deletePhieuDat(a.getMaPhieuNhan().getpDP()) : false;
				return b;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public boolean addChiTietHoaDon(HoaDon a) {
		Connection con = Database.getInsConnect().getCon();
		PreparedStatement statement = null;
		int n = 0;
		try {

			for(int i = 0; i < a.getListDV().size();i++) {
				statement = con.prepareStatement("INSERT INTO ChiTietHoaDon (MaHoaDon, MaDV)"
						+ "VALUES (?, ?)");
				statement.setString(1, a.getMaHD());
				statement.setString(2, a.getListDV().get(i).getMaDV());
				n = statement.executeUpdate();
			}
			System.out.println(n);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	
	@Override
	public String toString() {
		return "DanhSachHoaDon [listHD=" + listHD + "]";
	}
	
	public double getMoneyDV(HoaDon a) {
		double total = 0;
		for(int i = 0; i< a.getListDV().size();i++) {
			total += a.getListDV().get(i).getGia();
		}
		return total;
	}
	
}
