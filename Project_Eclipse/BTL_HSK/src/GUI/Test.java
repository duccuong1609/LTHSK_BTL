package GUI;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import ConnectDB.Database;
import Control.DanhSachHoaDon;
import Control.DanhSachKhachHang;
import Control.DanhSachPhieuDat;
import Control.DanhSachPhieuNhan;
import Control.DanhSachPhong;
import entity.PhieuDatPhong;

public class Test {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		
//		Database b = new Database();
//		b.connect();
		new UI_QLKS();
//		DanhSachKhachHang kh = new DanhSachKhachHang();
//		DanhSachPhieuDat a = new DanhSachPhieuDat();
//		kh.docDuLieu();
//		a.docDuLieu();
		
//		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
//		PhieuDatPhong b = a.getPhieuDatByCCCD_NgayDen("019283940112","2023-05-11");
//		b.setNgayDi(date.parse("2023-06-25"));
//		System.out.println(a.updatePhieuDat(b));
	}

}
