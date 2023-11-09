package GUI;

import ConnectDB.Database;
import Control.DanhSachHoaDon;
import Control.DanhSachPhieuDat;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		new UI_QLKS();
		
		Database b = new Database();
		b.connect();
		DanhSachHoaDon a = new DanhSachHoaDon();
		a.docDuLieu();
		System.out.println(a);
		
//		new UI_QLKS();
//		DanhSachPhieuDat a = new DanhSachPhieuDat();
//		System.out.println(a.getListPhongChuaTra().toString());
		
	}

}
