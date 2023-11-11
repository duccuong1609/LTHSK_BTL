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
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JComboBox;
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

import Control.DanhSachKhachHang;
import entity.KhachHang;

public class UI_TC_KH implements MouseListener,ActionListener{
	
	//--------DatPhong-----------//
	public JPanel display_KH;
	private JButton Then;
	private JButton Xoa;
	private JButton Sua;
	private JButton TaoLai;
	private JButton Tim;
	
	private JComboBox<String> KH_cb_LoaiKhach;
	private JTextField KH_txt_CCCD;
	private JTextField KH_txt_STK;
	private JTextField KH_txt_Hoten;
	private JTextField KH_txt_DiaChi;
	private JTextField KH_txt_Email;
	private JTextField KH_txt_SĐT;
	
	private DanhSachKhachHang list_KhachHang;
	
//	Pattern name = Pattern.compile("([A-Z][a-z]+ )+([A-Z][a-z]+)");
	private Pattern email = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
	private Pattern phone = Pattern.compile("(84|0[3|5|7|8|9])+([0-9]{8})");
	
	String[] cols_name = {"CĂN CƯỚC CÔNG DÂN","SỐ TÀI KHOẢN","HỌ VÀ TÊN","ĐỊA CHỈ","EMAIL","SỐ ĐIỆN THOẠI","LOẠI KHÁCH HÀNG"};
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
	
	public UI_TC_KH() {
		//setup data
		data = Default_Custom_UI.cast_data("KhachHang");
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
		
		
		display_KH = new JPanel();
		display_KH = new JPanel();
		display_KH.setLayout(new BorderLayout());
		
		JPanel titleJPanel = new JPanel();
		
		titleJPanel.setBorder(new CompoundBorder(new LineBorder(Color.LIGHT_GRAY, 3),new EmptyBorder(10,10,10,10)));
		
		JLabel title = new JLabel("TRA CỨU KHÁCH HÀNG");
		
		JPanel center_panel = new JPanel();
		title.setFont(Default_Custom_UI.big_title_font);
		titleJPanel.add(title);
		title.setBorder(new EmptyBorder(5,5,5,5));
		display_KH.add(titleJPanel,BorderLayout.NORTH);
		
		JPanel main_pJPanel = new JPanel();
		main_pJPanel.setLayout(new BorderLayout());
		
		main_pJPanel.setBorder(new CompoundBorder(new LineBorder(Color.LIGHT_GRAY, 3),new EmptyBorder(10,10,10,10)));
		
		//center_panel
		//--------------------------------------------------------------------------pending
		center_panel.setLayout(new BorderLayout());
		
		JPanel left_addfield = new JPanel();
		
		center_panel.add(left_addfield,BorderLayout.WEST);
		
		KH_cb_LoaiKhach = Default_Custom_UI.add_data_ds_combo("");
		KH_cb_LoaiKhach.addItem("V");
		KH_cb_LoaiKhach.addItem("N");
		
		KH_txt_CCCD = Default_Custom_UI.default_textfield();
		KH_txt_DiaChi = Default_Custom_UI.default_textfield();
		KH_txt_Hoten = Default_Custom_UI.default_textfield();
		KH_txt_Email = Default_Custom_UI.default_textfield();
		KH_txt_STK = Default_Custom_UI.default_textfield();
		KH_txt_SĐT = Default_Custom_UI.default_textfield();
		
		left_addfield.setPreferredSize(new Dimension(250,800));
		
		left_addfield.add(Default_Custom_UI.default_label("CĂN CƯỚC CÔNG DÂN"));
		left_addfield.add(Default_Custom_UI.default_text_panel(KH_txt_CCCD)); 
		
		left_addfield.add(Default_Custom_UI.default_label("SỐ TÀI KHOẢN"));
		left_addfield.add(Default_Custom_UI.default_text_panel(KH_txt_STK)); 
		
		left_addfield.add(Default_Custom_UI.default_label("HỌ VÀ TÊN"));
		left_addfield.add(Default_Custom_UI.default_text_panel(KH_txt_Hoten)); 
		
		left_addfield.add(Default_Custom_UI.default_label("ĐỊA CHỈ"));
		left_addfield.add(Default_Custom_UI.default_text_panel(KH_txt_DiaChi)); 
		
		left_addfield.add(Default_Custom_UI.default_label("EMAIL"));
		left_addfield.add(Default_Custom_UI.default_text_panel(KH_txt_Email)); 
		
		left_addfield.add(Default_Custom_UI.default_label("SỐ ĐIỆN THOẠI"));
		left_addfield.add(Default_Custom_UI.default_text_panel(KH_txt_SĐT));
		
		left_addfield.add(Default_Custom_UI.default_label("LOẠI KHÁCH HÀNG"));
		
		left_addfield.setBorder(new CompoundBorder(new LineBorder(Color.LIGHT_GRAY, 3),new EmptyBorder(10,10,10,10)));
		left_addfield.add(KH_cb_LoaiKhach);
		
		
		JPanel content_panel = new JPanel(new BorderLayout());
		JPanel button_panel = new JPanel();
		button_panel.setBorder(new CompoundBorder(new EmptyBorder(10,0,0,0), new CompoundBorder(new LineBorder(Color.LIGHT_GRAY, 3),new EmptyBorder(10,10,10,10))));
		button_panel.setLayout(new GridLayout(1, 4, 10, 30));
		
		//table
		
		JScrollPane jp = new JScrollPane(table);
		
		jp.setBackground(new Color(0,25,51));
		
		jp.setBorder(new LineBorder(Color.LIGHT_GRAY, 3));
		
		Then = Default_Custom_UI.default_Action_Button("Thêm", "Media/Icon/them.gif");
		Sua = Default_Custom_UI.default_Action_Button("Sửa", "Media/Icon/chinhsua.gif");
		Xoa = Default_Custom_UI.default_Action_Button("Xoá", "Media/Icon/xoa.gif");
		TaoLai = Default_Custom_UI.default_Action_Button("Tạo Lại", "Media/Icon/taolai.gif");
		Tim = Default_Custom_UI.default_Action_Button("Tìm", "Media/Icon/tim.gif");
		
		Then.addActionListener(this);
		Sua.addActionListener(this);
		Xoa.addActionListener(this);
		TaoLai.addActionListener(this);
		Tim.addActionListener(this);
		
		button_panel.add(TaoLai);
		button_panel.add(Then);
		button_panel.add(Xoa);
		button_panel.add(Sua);
		button_panel.add(Tim);
		
		content_panel.add(jp,BorderLayout.CENTER);
		
		content_panel.add(button_panel,BorderLayout.SOUTH);
		content_panel.setBorder(new EmptyBorder(0,10,0,0));
		
		center_panel.add(content_panel,BorderLayout.CENTER);
		
		main_pJPanel.add(center_panel,BorderLayout.CENTER);
		
		display_KH.add(main_pJPanel,BorderLayout.CENTER);
		
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(table)) {
			int row = table.getSelectedRow();
			KH_txt_CCCD.setText(model.getValueAt(row, 0).toString());
			KH_txt_STK.setText(model.getValueAt(row, 1).toString());
			KH_txt_Hoten.setText(model.getValueAt(row, 2).toString());
			KH_txt_DiaChi.setText(model.getValueAt(row, 3).toString());
			KH_txt_Email.setText(model.getValueAt(row, 4).toString());
			KH_txt_SĐT.setText(model.getValueAt(row, 5).toString());
			
			if(model.getValueAt(row, 6).toString().equals("VIP")) {
				KH_cb_LoaiKhach.setSelectedIndex(0);
			}
			else {
				KH_cb_LoaiKhach.setSelectedIndex(1);
			}
		}
	}

	private boolean Them_Sua_KhachHang(String type) {
		// TODO Auto-generated method stub
		

		
		if(KH_txt_CCCD.getText().equals("")) {
			JOptionPane.showMessageDialog(display_KH, "Căn Cước Công Dân Không Được Để Trống !");
			KH_txt_CCCD.requestFocus();
			return false;
		}
		if(KH_txt_STK.getText().equals("")) {
			JOptionPane.showMessageDialog(display_KH, "Số Tài Khoản Không Được Để Trống !");
			KH_txt_STK.requestFocus();
			return false;
		}
		if(KH_txt_Hoten.getText().equals("")) {
			JOptionPane.showMessageDialog(display_KH, "Họ Tên Không Được Để Trống !");
			KH_txt_Hoten.requestFocus();
			return false;
		}
		if(KH_txt_DiaChi.getText().equals("")) {
			JOptionPane.showMessageDialog(display_KH, "Địa Chỉ Không Được Để Trống !");
			KH_txt_DiaChi.requestFocus();
			return false;
		}
		if(KH_txt_Email.getText().equals("")) {
			JOptionPane.showMessageDialog(display_KH, "Email Không Được Để Trống !");
			KH_txt_Email.requestFocus();
			return false;
		}
		if(KH_txt_SĐT.getText().equals("")) {
			JOptionPane.showMessageDialog(display_KH, "Số Điện Thoại Không Được Để Trống !");
			KH_txt_SĐT.requestFocus();
			return false;
		}
		
		if(!KH_txt_CCCD.getText().matches("[0-9]{12}")) {
			JOptionPane.showMessageDialog(display_KH, "Căn Cước Công Dân Phải Là 1 Dãy Số Gồm 12 Chữ Số !");
			KH_txt_CCCD.setText("");
			KH_txt_CCCD.requestFocus();
			return false;
		}
		
		if(type.equals("Sua")) {
			
			if(table.getSelectedRow()==-1) {
				JOptionPane.showMessageDialog(display_KH, "Không Có Khách Hàng Nào Được Chọn !");
				return false;
			}
			if(!KH_txt_CCCD.getText().equals(model.getValueAt(table.getSelectedRow(), 0).toString())) {
				JOptionPane.showMessageDialog(display_KH, "Không Được Phép Sửa Căn Cước Công Dân !");
				KH_txt_CCCD.setText(model.getValueAt(table.getSelectedRow(), 0).toString());
				return false;
			}
		}
		
		list_KhachHang = new DanhSachKhachHang();
		list_KhachHang.docDuLieu();
		
		if(type.equals("Them")) {
			if(list_KhachHang.getKhachHangByMa(KH_txt_CCCD.getText())!= null) {
				JOptionPane.showMessageDialog(display_KH, "Khách Hàng Này Đã Tồn Tại Trong Danh Sách !");
				KH_txt_CCCD.requestFocus();
				return false;
			}
		}
		
		if(!KH_txt_STK.getText().matches("[0-9]{9,16}")) {
			JOptionPane.showMessageDialog(display_KH, "Số Tài Khoản Phải Là 1 Dãy Số (Từ 9 Đến 16 Số) !");
			KH_txt_STK.requestFocus();
			return false;
		}
		
//		if(!name.matcher(KH_txt_Hoten.getText()).matches()) {
//			JOptionPane.showMessageDialog(display_KH, "Họ Tên Gồm Từ 2 Từ Trở Lên. Mỗi Từ Có Chữ Cái Đầu Viết Hoa !");
//			KH_txt_Hoten.requestFocus();
//			return false;
//		}
		if(!email.matcher(KH_txt_Email.getText()).matches()) {
			JOptionPane.showMessageDialog(display_KH, "Sai Định Dạng Email !");
			KH_txt_Hoten.requestFocus();
			return false;
		}
		if(!phone.matcher(KH_txt_SĐT.getText()).matches()) {
			JOptionPane.showMessageDialog(display_KH, "Sai Định Dạng Số Điện Thoại Việt Nam !");
			KH_txt_Hoten.requestFocus();
			return false;
		}
		
		if(type.equals("Them")) {
			KhachHang khachHang = new KhachHang(KH_txt_CCCD.getText(), KH_txt_STK.getText(), KH_txt_Hoten.getText(), KH_txt_SĐT.getText(), KH_txt_DiaChi.getText(), KH_txt_Email.getText(), KH_cb_LoaiKhach.getSelectedItem().toString());
			list_KhachHang.addKhachHang(khachHang);
			
			data = Default_Custom_UI.cast_data("KhachHang");
			model.setDataVector(data, cols_name);
			table.setModel(model);
		}
		if(type.equals("Sua")) {
			KhachHang khachHang = new KhachHang(KH_txt_CCCD.getText(), KH_txt_STK.getText(), KH_txt_Hoten.getText(), KH_txt_SĐT.getText(), KH_txt_DiaChi.getText(), KH_txt_Email.getText(), KH_cb_LoaiKhach.getSelectedItem().toString());
			list_KhachHang.update(khachHang);
			
			data = Default_Custom_UI.cast_data("KhachHang");
			model.setDataVector(data, cols_name);
			table.setModel(model);
		}
		
		return true;
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


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(TaoLai)) {
			KH_txt_CCCD.setText("");
			KH_txt_STK.setText("");
			KH_txt_Hoten.setText("");
			KH_txt_DiaChi.setText("");
			KH_txt_Email.setText("");
			KH_txt_SĐT.setText("");
			KH_cb_LoaiKhach.setSelectedIndex(0);
			KH_txt_CCCD.requestFocus();
			
			data = Default_Custom_UI.cast_data("KhachHang");
			model.setDataVector(data, cols_name);
			table.setModel(model);
		}
		if(e.getSource().equals(Then)) {
			Them_Sua_KhachHang("Them");
		}
		if(e.getSource().equals(Xoa)) {
			String CCCD = KH_txt_CCCD.getText();
			list_KhachHang = new DanhSachKhachHang();
			list_KhachHang.docDuLieu();
			if(list_KhachHang.getKhachHangByMa(CCCD)!=null) {
				int choose = JOptionPane.showConfirmDialog(display_KH,"Bạn Có Chắc Muốn Xóa Khách Hàng Này Không ?","Chú Ý",JOptionPane.YES_NO_OPTION);
				if(choose == JOptionPane.YES_OPTION) {
					list_KhachHang.delete(CCCD);
					data = Default_Custom_UI.cast_data("KhachHang");
					model.setDataVector(data, cols_name);
					table.setModel(model);
				}
			}
			else {
				JOptionPane.showMessageDialog(display_KH,"Không Có Khách Hàng Nào Được Chọn");
			}
		}
		if(e.getSource().equals(Sua)) {
			Them_Sua_KhachHang("Sua");
		}
		if(e.getSource().equals(Tim)) {
			Tim_KhachHang();
		}
	}


	private boolean Tim_KhachHang() {
		// TODO Auto-generated method stub
		list_KhachHang = new DanhSachKhachHang();
		list_KhachHang.docDuLieu();
		
		if(KH_txt_CCCD.getText().equals("")) {
			JOptionPane.showMessageDialog(display_KH, "Căn Cước Công Dân Không Được Để Trống !");
			KH_txt_CCCD.requestFocus();
			return false;
		}
		if(!KH_txt_CCCD.getText().matches("[0-9]{12}")) {
			JOptionPane.showMessageDialog(display_KH, "Căn Cước Công Dân Phải Là 1 Dãy Số Gồm 12 Chữ Số !");
			KH_txt_CCCD.setText("");
			KH_txt_CCCD.requestFocus();
			return false;
		}
		if(list_KhachHang.getKhachHangByMa(KH_txt_CCCD.getText())== null) {
			JOptionPane.showMessageDialog(display_KH, "Khách Hàng Này Không Tồn Tại!");
			KH_txt_CCCD.requestFocus();
			return false;
		}
		data = new String[1][7];
		
		data[0][0] = list_KhachHang.getKhachHangByMa(KH_txt_CCCD.getText()).getCCCD();
		data[0][1] = list_KhachHang.getKhachHangByMa(KH_txt_CCCD.getText()).getSoTK();
		data[0][2] = list_KhachHang.getKhachHangByMa(KH_txt_CCCD.getText()).getHoTen();
		data[0][3] = list_KhachHang.getKhachHangByMa(KH_txt_CCCD.getText()).getDiaChi();
		data[0][4] = list_KhachHang.getKhachHangByMa(KH_txt_CCCD.getText()).getEmali();
		data[0][5] = list_KhachHang.getKhachHangByMa(KH_txt_CCCD.getText()).getSoDT();
		if(list_KhachHang.getKhachHangByMa(KH_txt_CCCD.getText()).getMaLoaiKH().equals("V")) {
			data[0][6] = "VIP";
		}
		else {
			data[0][6] = "Normal";
		}
		
		model.setDataVector(data, cols_name);
		table.setModel(model);
		
		return true;
	}
}


