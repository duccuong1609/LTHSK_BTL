package Control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
		HoaDon a = new HoaDon(maHoaDon, null, null, null, null);
		int index = listHD.indexOf(a);
		if(index == -1) {
			return null;
		}
		return listHD.get(index);
	}
	
	public ArrayList<HoaDon> getListHD() {
		return listHD;
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
			}
			n = statement.executeUpdate();
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
	
	
}
