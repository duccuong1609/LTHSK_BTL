package entity;

public class PhongThuong extends Phong {
	private static String maLoai = "PN";
	private String tenLoai = "Phòng Thường";
	private float gia = 250;
	public PhongThuong(int soPhong, String tenPhong, boolean isEmpty) {
		super(soPhong, tenPhong, maLoai, isEmpty);
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
	
	
	
	
	
}
