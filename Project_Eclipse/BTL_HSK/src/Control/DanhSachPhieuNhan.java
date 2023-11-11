package Control;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import ConnectDB.Database;
import entity.PhieuDatPhong;
import entity.PhieuNhanPhong;

public class DanhSachPhieuNhan {
	private ArrayList<PhieuNhanPhong> listPN;
	private DanhSachPhieuDat lisDat = new DanhSachPhieuDat();
	
	public DanhSachPhieuNhan() {
		listPN = new ArrayList<PhieuNhanPhong>();
		lisDat.docDuLieu();
	}
	
	public ArrayList<PhieuNhanPhong> docDuLieu(){
		try {
			listPN = new ArrayList<PhieuNhanPhong>();
			Connection con = Database.getInsConnect().getCon();
			SimpleDateFormat dateTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
			Statement statement = con.createStatement();
			String sql = "select MaPhieuNhan, MaPhieuDat, GioNhan, NgayNhan from PhieuNhanPhong";
			ResultSet result = statement.executeQuery(sql);
			while(result.next()) {
				String maNhan = result.getString(1);
				String maDat = result.getString(2);
				Date gioNhan = dateTime.parse(result.getString(3));
				Date ngayNhan = date.parse(result.getString(4));
				PhieuDatPhong a = lisDat.getPhieuDatPhongByMa(maDat);
				PhieuNhanPhong b = new PhieuNhanPhong(maNhan, a, gioNhan, ngayNhan);
				listPN.add(b);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listPN;
	}

	public DanhSachPhieuNhan getListPhongChuaTra() {
		DanhSachPhieuNhan a = new DanhSachPhieuNhan();
		Connection con = Database.getInsConnect().getCon();
		docDuLieu();
		try {
			Statement statement  = con.createStatement();
			ResultSet result = statement.executeQuery("{call getListPhongChuaTra}");
			while(result.next()) {
				String maPhieu = result.getString(1);
				PhieuNhanPhong a1 = getPhieuNhanByMa(maPhieu);
				if(a1 != null)
					a.addPhieuNhan(a1);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return a;
		
	}
	public ArrayList<PhieuNhanPhong> getListPN() {
		return listPN;
	}
	

	
	public boolean addPhieuNhan(PhieuNhanPhong a) {
		return listPN.add(a);
	}
	
	public boolean updatePhieuNhan(PhieuNhanPhong a) {
		Connection con = Database.getInsConnect().getCon();
		PreparedStatement statement = null;
		SimpleDateFormat dateTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		int n = 0;
		try {
			statement = con.prepareStatement("{call updatePhieuNhan(?,?,?,?)}");
			statement.setString(1, a.getMaPhieuNhan());
			statement.setString(2, a.getpDP().getMaPD());
			statement.setString(3, dateTime.format(a.getGioNhan()));
			statement.setString(4, date.format(a.getNgayNhan()));
			n = statement.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n >0;
	}
	
	
	public boolean insertPhieuNhan(PhieuNhanPhong a) {
		Connection con = Database.getInsConnect().getCon();
		PreparedStatement statement = null;
		SimpleDateFormat dateTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		int n = 0;
		try {
			statement = con.prepareStatement("{call insertPhieuNhan(?,?,?,?)}");
			statement.setString(1, a.getMaPhieuNhan());
			statement.setString(2, a.getpDP().getMaPD());
			statement.setString(3, dateTime.format(a.getGioNhan()));
			statement.setString(4, date.format(a.getNgayNhan()));
			n = statement.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n >0;
	}
	
	public PhieuNhanPhong getPhieuNhanByMa(String ma) {
		PhieuNhanPhong a = new PhieuNhanPhong(ma, null, null, null);
		int index = listPN.indexOf(a);
		if(index == -1)
			return null;
		return listPN.get(index);
	}
	
	@Override
	public String toString() {
		return "DanhSachPhieuNhan [listPN=" + listPN ;
	}
	
}
