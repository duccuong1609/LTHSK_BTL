package entity;

public class PhongVip extends Phong {

	private static String maLoai = "PV";
	private String tenLoai = "Phòng Vip";
	private float gia = 500;
	public PhongVip(int soPhong, String tenPhong, boolean isEmpty) {
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
