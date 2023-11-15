package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import com.toedter.calendar.JDateChooser;

import Control.DanhSachHoaDon;
import entity.KhachQuen;
import entity.PhongThuong;
import entity.PhongVip;

public class UI_THONGKE implements MouseListener,ActionListener{
	
	//--------DatPhong-----------//
	public JPanel display_ThongKe;
	
	private JTextField TK_txt_NgayTao;
	private JTextField TK_txt_Tong;
	private JDateChooser ThongKe_TuNgay;
	private JDateChooser ThongKe_DenNgay;
	private JTextField ThongKe_SoLuong;
	private JTextField ThongKe_DoanhThu;
	private JButton Tim;
	private JButton TaiLai;

//	private DanhSachPhieuDat phieuDat;
	
	
	String[] cols_name = {"MÃ HÓA ĐƠN","CCCD","MÃ NHÂN VIÊN","NGÀY TẠO","TỔNG THÀNH TIỀN"};
	private Object[][] data = {
            {"1", "Alice", "Smith"},
            {"2", "Bob", "Johnson"},
            {"3", "Charlie", "Williams"}
        };
	
	@SuppressWarnings("serial")
	private DefaultTableModel model = new DefaultTableModel(data,cols_name) {
		public boolean isCellEditable(int row, int column) {
			if(column==0) {
				return false;
			}
			return true;
		};
	};
	private JTable table = new JTable(model);

	
	public UI_THONGKE() {
		//setup data
		data = Default_Custom_UI.cast_data("ListHoaDon");
		model.setDataVector(data, cols_name);
		
//		table
		table.getTableHeader().setFont(Default_Custom_UI.title_font);
		table.getTableHeader().setPreferredSize(new Dimension(100, 50));
		table.setBorder(null);
		
		table.setFont(new Font("Arial",Font.TRUETYPE_FONT,15));
		
		JTableHeader header = table.getTableHeader();
		
		header.setDefaultRenderer(new CustomHeaderRenderer());
		table.setDefaultRenderer(Object.class,new CustomRenderer());
		table.setShowVerticalLines(false);
		table.setRowHeight(30);
		table.addMouseListener(this);
		
		
		display_ThongKe = new JPanel();
		display_ThongKe = new JPanel();
		display_ThongKe.setLayout(new BorderLayout());
		
		JPanel titleJPanel = new JPanel();
		
		titleJPanel.setBorder(new CompoundBorder(new LineBorder(Color.LIGHT_GRAY, 3),new EmptyBorder(10,10,10,10)));
		
		JLabel title = new JLabel("THỐNG KÊ KHÁCH SẠN");
		
		JPanel center_panel = new JPanel();
		title.setFont(Default_Custom_UI.big_title_font);
		titleJPanel.add(title);
		title.setBorder(new EmptyBorder(5,5,5,5));
		display_ThongKe.add(titleJPanel,BorderLayout.NORTH);
		
		JPanel main_pJPanel = new JPanel();
		main_pJPanel.setLayout(new BorderLayout());
		
		main_pJPanel.setBorder(new CompoundBorder(new LineBorder(Color.LIGHT_GRAY, 3),new EmptyBorder(10,10,10,10)));
		
		//center_panel
		//--------------------------------------------------------------------------pending
		center_panel.setLayout(new BorderLayout());
		ThongKe_TuNgay = Default_Custom_UI.defaultDateChooser();
		ThongKe_DenNgay = Default_Custom_UI.defaultDateChooser();
		ThongKe_SoLuong = Default_Custom_UI.default_textfield();
		ThongKe_SoLuong.setEditable(false);
		ThongKe_DoanhThu = Default_Custom_UI.default_textfield();
		ThongKe_DoanhThu.setEditable(false);
		
		Tim = Default_Custom_UI.default_Action_Button("Tìm", "Media/Icon/tim.gif");
		TaiLai = Default_Custom_UI.default_Action_Button("Tải Lại", "Media/Icon/taolai.gif");
		
		
		Tim.setPreferredSize(new Dimension(220,60));
		TaiLai.setPreferredSize(new Dimension(220,60));
		
		JPanel tim_panel = new JPanel();
		tim_panel.setBorder(new EmptyBorder(20,0,20,0));
		tim_panel.add(Tim);
		tim_panel.setBackground(new Color(255,250,245));
		JPanel tailai_panel = new JPanel();
		tailai_panel.setBorder(new EmptyBorder(0,0,20,0));
		tailai_panel.add(TaiLai);
		tailai_panel.setBackground(new Color(255,250,245));
		
		JPanel left_addfield = new JPanel();
		left_addfield.setPreferredSize(new Dimension(250,800));
		left_addfield.add(Default_Custom_UI.default_label("TỪ NGÀY"));
		left_addfield.add(ThongKe_TuNgay);
		left_addfield.add(Default_Custom_UI.default_label("ĐẾN NGÀY"));
		left_addfield.add(ThongKe_DenNgay);
		left_addfield.add(tim_panel);
		left_addfield.add(tailai_panel);
		JLabel tongdoanhthu = Default_Custom_UI.default_label("TỔNG DOANH THU");
		tongdoanhthu.setFont(new Font("Arial", Font.BOLD, 25));
		left_addfield.add(tongdoanhthu);
		left_addfield.add(Default_Custom_UI.default_label("Số lượng hóa đơn bán được:"));
		left_addfield.add(Default_Custom_UI.default_text_panel(ThongKe_SoLuong));
		left_addfield.add(Default_Custom_UI.default_label("Doanh thu:"));
		left_addfield.add(Default_Custom_UI.default_text_panel(ThongKe_DoanhThu));

		
		center_panel.add(left_addfield,BorderLayout.WEST);
		
		TK_txt_NgayTao = Default_Custom_UI.default_textfield();
		TK_txt_Tong = Default_Custom_UI.default_textfield();
		
		left_addfield.setPreferredSize(new Dimension(250,800));

		left_addfield.setBorder(new CompoundBorder(new LineBorder(Color.LIGHT_GRAY, 3),new EmptyBorder(10,10,10,10)));
		
		JPanel content_panel = new JPanel(new BorderLayout());
		JPanel button_panel = new JPanel();
		button_panel.setBorder(new CompoundBorder(new EmptyBorder(10,0,0,0), new CompoundBorder(new LineBorder(Color.LIGHT_GRAY, 3),new EmptyBorder(10,10,10,10))));
		button_panel.setLayout(new GridLayout(1, 4, 10, 30));
		
		//table
		
		JScrollPane jp = new JScrollPane(table);
		
		jp.setBackground(new Color(0,25,51));
		
		jp.setBorder(new LineBorder(Color.LIGHT_GRAY, 3));
		
		content_panel.add(jp,BorderLayout.CENTER);
		
		content_panel.setBorder(new EmptyBorder(0,10,0,0));
		
		left_addfield.setBackground(new Color(255,250,245));
		button_panel.setBackground(new Color(255,250,245));
		titleJPanel.setBackground(new Color(255,250,245));
		content_panel.setBackground(new Color(255,250,245));
		jp.getViewport().setBackground(new Color(255,250,245));
		content_panel.setBorder(new EmptyBorder(0,10,0,0));
		main_pJPanel.setBackground(new Color(255,250,245));
		center_panel.setBackground(new Color(255,250,245));
		
		center_panel.add(content_panel,BorderLayout.CENTER);
		
		main_pJPanel.add(center_panel,BorderLayout.CENTER);
		
		display_ThongKe.add(main_pJPanel,BorderLayout.CENTER);
		
		Tim.addActionListener(this);
		TaiLai.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(TaiLai)) {
			Reload();
		}
		if(e.getSource().equals(Tim)) {
			model.setDataVector(data, cols_name);
			table.setModel(model);
			if (checkDay()) {
				DanhSachHoaDon listHD = new DanhSachHoaDon();
				listHD.docDuLieu();
				DanhSachHoaDon listTK = listHD.getListFromDateToDate(ThongKe_TuNgay.getDate(), ThongKe_DenNgay.getDate());
				int size = listTK.getListHD().size();
				String[][] data_temp = new String[size][5];
				for(int i=0;i<size;i++) {
					data_temp[i][0] = listTK.getListHD().get(i).getMaHD();
					data_temp[i][1] = listTK.getListHD().get(i).getMaPhieuNhan().getpDP().getNhanVien().getMaNV();
					data_temp[i][2] = listTK.getListHD().get(i).getMaPhieuNhan().getpDP().getKhachHang().getCCCD();
					data_temp[i][3] = Integer.toString(listTK.getListHD().get(i).getMaPhieuNhan().getpDP().getPhongs().getListPhong().get(0).getSoPhong());
					float khauTru = 0;
					if(listTK.getListHD().get(i).getMaPhieuNhan().getpDP().getKhachHang() instanceof KhachQuen)
						khauTru = KhachQuen.getKhauTru();
				    float gia = (listTK.getListHD().get(i).getMaPhieuNhan().getpDP().getPhongs().getListPhong().get(0) instanceof PhongVip) ? PhongVip.getGia():PhongThuong.getGia();
				    
				    long soNgay= (listTK.getListHD().get(i).getNgayTra().getTime() - listTK.getListHD().get(i).getMaPhieuNhan().getNgayNhan().getTime()) / (24 * 60 * 60 * 1000);
				    
				    if(soNgay ==0) {
				    	soNgay = 1;
				    }
				    
				    double tong = ((double) gia * ((double)soNgay) + (double)listHD.getMoneyDV(listHD.getListHD().get(i)))*(1 - khauTru);
				    tong = Math.round(tong);
				    DecimalFormat format = new DecimalFormat("#,### VND");
				    data_temp[i][4] =  format.format(tong);
					
				}
				double total = 0;
				int soLuong = 0;
				for(int i=0; i< listTK.getListHD().size();i++) {
					soLuong +=1;
					String temp_cost = table.getValueAt(i, 4).toString();
					
					temp_cost = temp_cost.substring(0, temp_cost.length()-4);
					temp_cost = temp_cost.replace(",", "");
					
					total += Double.parseDouble(temp_cost);
				}
				ThongKe_SoLuong.setText(soLuong+"");
				DecimalFormat format = new DecimalFormat("#,### VND");
				ThongKe_DoanhThu.setText(format.format(total));
				model.setDataVector(data_temp, cols_name);
				table.setModel(model);
				if (listTK.getListHD().size()==0) {
					JOptionPane.showMessageDialog(display_ThongKe, "Không Có Hóa Đơn Nào Được Tạo Vào Thời Gian Này !");
				}
			}	
		}
	}

	private void Reload() {
		// TODO Auto-generated method stub
		ThongKe_TuNgay.setCalendar(null);
		ThongKe_DenNgay.setCalendar(null);
		ThongKe_SoLuong.setText("");
		ThongKe_DoanhThu.setText("");
		model.setDataVector(data, cols_name);
		table.setModel(model);
	}
	private boolean checkDay() {
		// TODO Auto-generated method stub
		if(ThongKe_TuNgay.getCalendar()==null) {
			JOptionPane.showMessageDialog(display_ThongKe,"Không Được Để Trống Ngày Bắt Đầu !");
			ThongKe_TuNgay.requestFocus();
			return false;
		}
		if(ThongKe_DenNgay.getCalendar()==null) {
			JOptionPane.showMessageDialog(display_ThongKe,"Không Được Để Trống Ngày Kết Thúc!");
			ThongKe_DenNgay.requestFocus();
			return false;
		}
		if(ThongKe_TuNgay.getDate().compareTo(ThongKe_DenNgay.getDate())>0) {
			JOptionPane.showMessageDialog(ThongKe_DenNgay,"Từ Ngày Phải Lớn Hơn Hoặc Bằng Đến Ngày");
			return false;
		}
		return true;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(table)) {
			int row = table.getSelectedRow();
			TK_txt_NgayTao.setText(model.getValueAt(row, 3).toString());
			TK_txt_Tong.setText(model.getValueAt(row, 4).toString());
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}


