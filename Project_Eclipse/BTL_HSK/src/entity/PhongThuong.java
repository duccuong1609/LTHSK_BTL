package entity;

public class PhongThuong extends Phong {
	private static String maLoai = "PN";
	private String tenLoai = "Phòng Thường";
	private float gia = 250;
	public PhongThuong(int soPhong, String tenPhong, boolean isEmpty) {
		super(soPhong, tenPhong, isEmpty);
		// TODO Auto-generated constructor stub
	}
	public static String getMaLoai() {
		return maLoai;
	}
	public String getTenLoai() {
		return tenLoai;
	}
	public float getGia() {
		return gia;
	}
	
	@Override
	public String getTieuDe() {
		// TODO Auto-generated method stub
		return String.format("%s %s %s %s", super.getTieuDe(),"Ma loai","Ten loai","Gia");
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("%s %s %s %s", super.toString(),maLoai,tenLoai,gia);
	}
	
	
	
}
