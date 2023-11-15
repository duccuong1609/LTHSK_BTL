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

import Control.DanhSachNhanVien;
import Control.DanhSachPhieuDat;
import entity.NhanVien;

public class UI_TC_NV implements MouseListener,ActionListener{
	
	//--------DatPhong-----------//
	public JPanel display_NV;
	private JButton Then;
	private JButton Xoa;
	private JButton Sua;
	private JButton TaoLai;
	private JButton Tim;
	
	private JTextField NV_txt_MaNV;
	private JTextField NV_txt_TenNV;
	private JTextField NV_txt_SĐT;
	private JTextField NV_txt_DiaChi;
	private JTextField NV_txt_Email;
	
//	private DanhSachPhieuDat phieuDat;
	private Pattern name = Pattern.compile("([^\\.~\\*!`@#$%^&*()_+={}\\[\\]/><,;:'\"|\\-?/\\d]+)");
	private Pattern email = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
	private Pattern phone = Pattern.compile("(84|0[3|5|7|8|9])+([0-9]{8})");
	
	String[] cols_name = {"MÃ NHÂN VIÊN","TÊN NHÂN VIÊN","ĐỊA CHỈ","EMAIL","SỐ ĐIỆN THOẠI"};
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
	
	public UI_TC_NV() {
		//setup data
		data = Default_Custom_UI.cast_data("NhanVien");
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
		
		
		display_NV = new JPanel();
		display_NV = new JPanel();
		display_NV.setLayout(new BorderLayout());
		
		JPanel titleJPanel = new JPanel();
		
		titleJPanel.setBorder(new CompoundBorder(new LineBorder(Color.LIGHT_GRAY, 3),new EmptyBorder(10,10,10,10)));
		
		JLabel title = new JLabel("TRA CỨU NHÂN VIÊN");
		
		JPanel center_panel = new JPanel();
		title.setFont(Default_Custom_UI.big_title_font);
		titleJPanel.add(title);
		title.setBorder(new EmptyBorder(5,5,5,5));
		display_NV.add(titleJPanel,BorderLayout.NORTH);
		
		JPanel main_pJPanel = new JPanel();
		main_pJPanel.setLayout(new BorderLayout());
		
		main_pJPanel.setBorder(new CompoundBorder(new LineBorder(Color.LIGHT_GRAY, 3),new EmptyBorder(10,10,10,10)));
		
		//center_panel
		//--------------------------------------------------------------------------pending
		center_panel.setLayout(new BorderLayout());
		
		JPanel left_addfield = new JPanel();
		
		center_panel.add(left_addfield,BorderLayout.WEST);
		
		NV_txt_MaNV = Default_Custom_UI.default_textfield();
		NV_txt_TenNV = Default_Custom_UI.default_textfield();
		NV_txt_DiaChi = Default_Custom_UI.default_textfield();
		NV_txt_Email = Default_Custom_UI.default_textfield();
		NV_txt_SĐT = Default_Custom_UI.default_textfield();
		
		left_addfield.setPreferredSize(new Dimension(250,800));
		
		left_addfield.add(Default_Custom_UI.default_label("MÃ NHÂN VIÊN"));
		left_addfield.add(Default_Custom_UI.default_text_panel(NV_txt_MaNV)); 
		
		left_addfield.add(Default_Custom_UI.default_label("TÊN NHÂN VIÊN"));
		left_addfield.add(Default_Custom_UI.default_text_panel(NV_txt_TenNV)); 
		
		left_addfield.add(Default_Custom_UI.default_label("ĐỊA CHỈ"));
		left_addfield.add(Default_Custom_UI.default_text_panel(NV_txt_DiaChi)); 
		
		left_addfield.add(Default_Custom_UI.default_label("SỐ ĐIỆN THOẠI"));
		left_addfield.add(Default_Custom_UI.default_text_panel(NV_txt_SĐT)); 
		
		left_addfield.add(Default_Custom_UI.default_label("EMAIL"));
		left_addfield.add(Default_Custom_UI.default_text_panel(NV_txt_Email)); 
		
		
		left_addfield.setBorder(new CompoundBorder(new LineBorder(Color.LIGHT_GRAY, 3),new EmptyBorder(10,10,10,10)));
		
		
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
		
		left_addfield.setBackground(new Color(255,250,245));
		button_panel.setBackground(new Color(255,250,245));
		titleJPanel.setBackground(new Color(255,250,245));
		content_panel.setBackground(new Color(255,250,245));
		jp.getViewport().setBackground(new Color(255,250,245));
		content_panel.setBorder(new EmptyBorder(0,10,0,0));
		main_pJPanel.setBackground(new Color(255,250,245));
		center_panel.setBackground(new Color(255,250,245));
		
		content_panel.add(jp,BorderLayout.CENTER);
		
		content_panel.add(button_panel,BorderLayout.SOUTH);
		content_panel.setBorder(new EmptyBorder(0,10,0,0));
		
		center_panel.add(content_panel,BorderLayout.CENTER);
		
		main_pJPanel.add(center_panel,BorderLayout.CENTER);
		
		display_NV.add(main_pJPanel,BorderLayout.CENTER);
		
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(table)) {
			int row = table.getSelectedRow();
			NV_txt_MaNV.setText(model.getValueAt(row, 0).toString());
			NV_txt_TenNV.setText(model.getValueAt(row, 1).toString());
			NV_txt_DiaChi.setText(model.getValueAt(row, 2).toString());
			NV_txt_Email.setText(model.getValueAt(row, 3).toString());
			NV_txt_SĐT.setText(model.getValueAt(row, 4).toString());
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


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(TaoLai)) {
			NV_txt_MaNV.setText("");
			NV_txt_SĐT.setText("");
			NV_txt_DiaChi.setText("");
			NV_txt_TenNV.setText("");
			NV_txt_Email.setText("");
			NV_txt_MaNV.requestFocus();
			data = Default_Custom_UI.cast_data("NhanVien");
			model.setDataVector(data, cols_name);
			table.setModel(model);		
		}
		if(e.getSource().equals(Then)) {
			Them_Sua_NhanVien("Them");
		}
		if(e.getSource().equals(Xoa)) {
			Xoa_NhanVien();
		}
		if(e.getSource().equals(Sua)) {
			Them_Sua_NhanVien("Sua");
		}
		if(e.getSource().equals(Tim)) {
			Tim_NhanVien();
		}
		
	}
	private boolean Tim_NhanVien() {
		// TODO Auto-generated method stub
		if(NV_txt_MaNV.getText().equals("")) {
			JOptionPane.showMessageDialog(display_NV, "Mã Nhân Viên Không Được Để Trống !");
			NV_txt_MaNV.requestFocus();
			return false;
		}
		if(!NV_txt_MaNV.getText().matches("NV[0-9]+")) {
			JOptionPane.showMessageDialog(display_NV, "Mã Nhân Viên Phải Bắt Đầu Từ NV Theo Sau Là Các Kí Số !");
			NV_txt_MaNV.setText("");
			NV_txt_MaNV.requestFocus();
			return false;
		}
		DanhSachNhanVien ds = new DanhSachNhanVien();
		
		if(ds.getNhanVienByMa(NV_txt_MaNV.getText())== null) {
			JOptionPane.showMessageDialog(display_NV, "Mã Nhân Viên Không Tồn Tại !");
			NV_txt_MaNV.setText("");
			NV_txt_MaNV.requestFocus();
			return false;
		}
		
		NhanVien a =  ds.getNhanVienByMa(NV_txt_MaNV.getText());
		
		data = new String[1][5];
		
		data[0][0] = a.getMaNV();
		data[0][1] = a.getTenNV();
		data[0][2] = a.getDiaChi();
		data[0][3] = a.getEmail(); 
		data[0][4] = a.getSoDT();
		
		model.setDataVector(data, cols_name);
		table.setModel(model);
		
		return true;
	}

	private boolean Xoa_NhanVien() {
		if(table.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(display_NV, "Không có Nhân Viên Nào Được Chọn !");
			return false;
		}
		
		int choose = JOptionPane.showConfirmDialog(display_NV, "Bạn Có Chắc Muốn Xóa Nhân Viên Này Không ?","Chú Ý",JOptionPane.YES_NO_OPTION);
		if(choose == JOptionPane.YES_OPTION) {
			
			DanhSachNhanVien ds = new DanhSachNhanVien();
			DanhSachPhieuDat dSachPhieuDat = new DanhSachPhieuDat();
			dSachPhieuDat.docDuLieu();
			String[] data_mnv = new String[dSachPhieuDat.getListPDP().size()];
			for(int i=0;i<dSachPhieuDat.getListPDP().size();i++) {
				data_mnv[i] = dSachPhieuDat.getListPDP().get(i).getNhanVien().getMaNV();
			}
			for(int i=0;i<data_mnv.length;i++) {
				if(NV_txt_MaNV.getText().equals(data_mnv[i])) {
					JOptionPane.showMessageDialog(display_NV, "Bạn Không Thể Xóa Nhân Viên Có Tham Gia Quản Lí Đặt/Nhận/Trả Phòng !");
					return false;
				}
			}
			
			ds.removeNV(model.getValueAt(table.getSelectedRow(), 0).toString());
			data = Default_Custom_UI.cast_data("NhanVien");
			model.setDataVector(data, cols_name);
			table.setModel(model);
			
		}
		
		return true;
	}

	private boolean Them_Sua_NhanVien(String type) {
		// TODO Auto-generated method stub
		if(NV_txt_TenNV.getText().equals("")) {
			JOptionPane.showMessageDialog(display_NV, "Tên Nhân Viên Không Được Để Trống !");
			NV_txt_TenNV.requestFocus();
			return false;
		}
		
		if(NV_txt_DiaChi.getText().equals("")) {
			JOptionPane.showMessageDialog(display_NV, "Địa Chỉ Nhân Viên Không Được Để Trống !");
			NV_txt_DiaChi.requestFocus();
			return false;
		}
		
		if(NV_txt_SĐT.getText().equals("")) {
			JOptionPane.showMessageDialog(display_NV, "Số Điện Thoại Nhân Viên Không Được Để Trống !");
			NV_txt_SĐT.requestFocus();
			return false;
		}
		
		if(NV_txt_Email.getText().equals("")) {
			JOptionPane.showMessageDialog(display_NV, "Email Nhân Viên Không Được Để Trống !");
			NV_txt_Email.requestFocus();
			return false;
		}
		if(!name.matcher(NV_txt_TenNV.getText()).matches()) {
			JOptionPane.showMessageDialog(display_NV, "Họ Tên Gồm Từ 2 Từ Trở Lên. Mỗi Từ Có Chữ Cái Đầu Viết Hoa !");
			NV_txt_TenNV.setText("");
			NV_txt_TenNV.requestFocus();
			return false;
		}
		if(!email.matcher(NV_txt_Email.getText()).matches()) {
			JOptionPane.showMessageDialog(display_NV, "Sai Định Dạng Email !");
			NV_txt_Email.setText("");;
			NV_txt_Email.requestFocus();
			return false;
		}
		if(!phone.matcher(NV_txt_SĐT.getText()).matches()) {
			JOptionPane.showMessageDialog(display_NV, "Sai Định Dạng Số Điện Thoại Việt Nam !");
			NV_txt_SĐT.setText("");
			NV_txt_SĐT.requestFocus();
			return false;
		}
		
		if(type.equals("Them")) {
			DanhSachNhanVien ds = new DanhSachNhanVien();
			ds.docDuLieu();
			
			String last_maNV = ds.get_listNV().get(ds.get_listNV().size()-1).getMaNV();
			int last_number = Integer.parseInt(last_maNV.substring(3, last_maNV.length())) + 1;
			
			NhanVien nv = new NhanVien("NV0"+(last_number), NV_txt_TenNV.getText(), NV_txt_SĐT.getText(), NV_txt_DiaChi.getText(), NV_txt_Email.getText());
			
			JOptionPane.showMessageDialog(display_NV, "Thêm Nhân Viên Thành Công !");
			
			ds.insertNhanVien(nv);
			
			data = Default_Custom_UI.cast_data("NhanVien");
			model.setDataVector(data, cols_name);
			table.setModel(model);
		}
		
		if(type.equals("Sua")) {
			if(NV_txt_MaNV.getText().equals("")) {
				JOptionPane.showMessageDialog(display_NV, "Mã Nhân Viên Không Được Để Trống !");
				NV_txt_MaNV.requestFocus();
				return false;
			}
			if(!NV_txt_MaNV.getText().matches("NV[0-9]+")) {
				JOptionPane.showMessageDialog(display_NV, "Mã Nhân Viên Phải Bắt Đầu Từ NV Theo Sau Là Các Kí Số !");
				NV_txt_MaNV.setText("");
				NV_txt_MaNV.requestFocus();
				return false;
			}
			DanhSachNhanVien ds = new DanhSachNhanVien();
			ds.docDuLieu();
			if(ds.getNhanVienByMa(NV_txt_MaNV.getText())==null) {
				JOptionPane.showMessageDialog(display_NV, "Nhân Viên Không Tồn Tại !");
				NV_txt_MaNV.setText("");
				NV_txt_MaNV.requestFocus();
				return false;
			}
			NhanVien nv = new NhanVien(NV_txt_MaNV.getText(), NV_txt_TenNV.getText(), NV_txt_SĐT.getText(),NV_txt_DiaChi.getText(), NV_txt_Email.getText());
			ds.updateNV(nv);
			
			data = Default_Custom_UI.cast_data("NhanVien");
			model.setDataVector(data, cols_name);
			table.setModel(model);
		}
		
		return true;
	}
}


