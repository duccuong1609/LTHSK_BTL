package bTL_HSK;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;

public class DichVu {
	private String maDV;
	private String tenDV;
	private int giaDV;
	
	public String getMaDV() {
		return maDV;
	}
	public void setMaDV(String maDV) {
		this.maDV = maDV;
	}
	public String getTenDV() {
		return tenDV;
	}
	public void setTenDV(String tenDV) {
		this.tenDV = tenDV;
	}
	public int getGiaDV() {
		return giaDV;
	}
	public void setGiaDV(int giaDV) {
		this.giaDV = giaDV;
	}
	
	public DichVu(String maDV, String tenDV, int giaDV) {
		super();
		this.maDV = maDV;
		this.tenDV = tenDV;
		this.giaDV = giaDV;
	}
	
	public DichVu(String maDV) {
		super();
		this.maDV = maDV;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maDV);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DichVu other = (DichVu) obj;
		return Objects.equals(maDV, other.maDV);
	}
	@Override
	public String toString() {
		return "DichVu [maDV=" + maDV + ", tenDV=" + tenDV + ", giaDV=" + giaDV + "]";
	}
}
//--------------------------------------DS_DichVu------------------------------------------//
class DS_DichVu{
	
	ArrayList<DichVu> list;
	
	public DS_DichVu() {
		list = new ArrayList<DichVu>();
		list = docTuBang();
	}
	
	public ArrayList<DichVu> docTuBang(){
		try {
			Connection con = Database.getinsDatabase().getConnection();
			String sql = "select * from DichVu";
			Statement statement = con.createStatement();
			ResultSet rSet = statement.executeQuery(sql);
			
			while (rSet.next()) {
				String ma = rSet.getString(1);
 				String ten = rSet.getString(2);
				int gia = rSet.getInt(3);
				DichVu dv = new DichVu(ma,ten,gia);
				list.add(dv);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	public ArrayList<DichVu> get_list(){
		return list;
	}
	
}
