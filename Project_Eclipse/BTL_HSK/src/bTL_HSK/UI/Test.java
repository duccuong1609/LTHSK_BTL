package bTL_HSK.UI;

import bTL_HSK.Dadabase.Database;
import bTL_HSK.DichVu.DanhSachDichVu;
import bTL_HSK.DichVu.DichVu;
import bTL_HSK.KhachHang.DanhSachKhachHang;
import bTL_HSK.NhanVien.DanhSachNhanVien;
import bTL_HSK.PhieuDatPhong.DanhSachPhieuDat;
import bTL_HSK.Phong.DanhSachPhong;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		new UI_QLKS();
		
		Database b = new Database();
		b.connect();
		DanhSachPhieuDat a = new DanhSachPhieuDat();
		a.docDuLieu();
		System.out.println(a);
	}

}
