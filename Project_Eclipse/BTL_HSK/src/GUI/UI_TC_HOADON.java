package GUI;

import static org.junit.Assert.isArray;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
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
import entity.HoaDon;

public class UI_TC_HOADON implements MouseListener,ActionListener{
	
	//--------DatPhong-----------//
	public JPanel display_HoaDon;
	private JButton Tim;
	private JButton Xoa;
//	private JButton Sua;
	private JButton TaoLai;
	
	private JTextField HoaDon_txt_MaHoaDon;
	private JTextField HoaDon_txt_MaNhanVien;
	private JTextField HoaDon_txt_CCCD;
	private JTextField HoaDon_txt_MaSoPhong;
	
//	private DanhSachPhieuDat phieuDat;
	
	
	String[] cols_name = {"MÃ HÓA ĐƠN","MÃ NHÂN VIÊN","CĂN CƯỚC CÔNG DÂN","MÃ SỐ PHÒNG","TỔNG TIỀN"};
	String[] cols_name_dv = {"MÃ DỊCH VỤ","TÊN DỊCH VỤ","GIÁ DỊCH VỤ"};
	private Object[][] data = Default_Custom_UI.cast_data("ListHoaDon");
	private Object[][] data_dv = Default_Custom_UI.cast_data("DichVu_FULL");
	
	@SuppressWarnings("serial")
	private DefaultTableModel model = new DefaultTableModel(data,cols_name) {
		public boolean isCellEditable(int row, int column) {
			if(column==0) {
				return false;
			}
			return true;
		};
	};
	@SuppressWarnings("serial")
	private DefaultTableModel model_dv = new DefaultTableModel(data_dv,cols_name_dv) {
		public boolean isCellEditable(int row, int column) {
			if(column==0) {
				return false;
			}
			return true;
		};
	};
	
	private JTable table = new JTable(model);
	private JTable table_dv = new JTable(model_dv);
	
	public UI_TC_HOADON() {
		
		//--------------------------------table--------------------------------//
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
		//--------------------------------------------------------------------//
		table_dv.getTableHeader().setFont(Default_Custom_UI.title_font);
		table_dv.getTableHeader().setPreferredSize(new Dimension(100, 50));
		table_dv.setBorder(null);
		
		table_dv.setFont(new Font("Arial",Font.TRUETYPE_FONT,15));
		
		JTableHeader header_dv = table_dv.getTableHeader();
		
		header_dv.setDefaultRenderer(new CustomHeaderRenderer());
		table_dv.setDefaultRenderer(Object.class,new CustomRenderer());
		table_dv.setShowVerticalLines(false);
		table_dv.setRowHeight(30);
		table_dv.addMouseListener(this);
		//--------------------------------------------------------------------//
		
		
		display_HoaDon = new JPanel();
		display_HoaDon = new JPanel();
		display_HoaDon.setLayout(new BorderLayout());
		
		JPanel titleJPanel = new JPanel();
		
		titleJPanel.setBorder(new CompoundBorder(new LineBorder(Color.LIGHT_GRAY, 3),new EmptyBorder(10,10,10,10)));
		
		JLabel title = new JLabel("TRA CỨU HÓA ĐƠN");
		
		JPanel center_panel = new JPanel();
		title.setFont(Default_Custom_UI.big_title_font);
		titleJPanel.add(title);
		title.setBorder(new EmptyBorder(5,5,5,5));
		display_HoaDon.add(titleJPanel,BorderLayout.NORTH);
		
		JPanel main_pJPanel = new JPanel();
		main_pJPanel.setLayout(new BorderLayout());
		
		main_pJPanel.setBorder(new CompoundBorder(new LineBorder(Color.LIGHT_GRAY, 3),new EmptyBorder(10,10,10,10)));
		
		//center_panel
		//--------------------------------------------------------------------------pending
		center_panel.setLayout(new BorderLayout());
		
		JPanel left_addfield = new JPanel();
		
		center_panel.add(left_addfield,BorderLayout.WEST);
		
		
		
		left_addfield.setPreferredSize(new Dimension(380,800));
		left_addfield.setLayout(new BorderLayout());
		
		JPanel other_field = new JPanel();
		
		JLabel lb1 = Default_Custom_UI.default_label("MÃ HÓA ĐƠN");
		lb1.setPreferredSize(new Dimension(330,20));
		JLabel lb2 = Default_Custom_UI.default_label("MÃ NHÂN VIÊN");
		lb2.setPreferredSize(new Dimension(330,20));
		JLabel lb3 = Default_Custom_UI.default_label("CCCD");
		lb3.setPreferredSize(new Dimension(330,20));
		JLabel lb4 = Default_Custom_UI.default_label("MÃ SỐ PHÒNG");
		lb4.setPreferredSize(new Dimension(330,20));
		JLabel lb5 = Default_Custom_UI.default_label("DỊCH VỤ ĐÃ DÙNG");
		lb5.setPreferredSize(new Dimension(330,20));
		
		HoaDon_txt_MaNhanVien = Default_Custom_UI.default_textfield();
		HoaDon_txt_CCCD = Default_Custom_UI.default_textfield();
		HoaDon_txt_MaSoPhong = Default_Custom_UI.default_textfield();
		HoaDon_txt_MaHoaDon = Default_Custom_UI.default_textfield();
		
		JPanel jp0 = Default_Custom_UI.default_text_panel(HoaDon_txt_MaHoaDon);
		jp0.setPreferredSize(new Dimension(350,35));
		JPanel jp1 = Default_Custom_UI.default_text_panel(HoaDon_txt_MaNhanVien);
		jp1.setPreferredSize(new Dimension(350,35));
		JPanel jp2 = Default_Custom_UI.default_text_panel(HoaDon_txt_CCCD);
		jp2.setPreferredSize(new Dimension(350,35));
		JPanel jp3 = Default_Custom_UI.default_text_panel(HoaDon_txt_MaSoPhong);
		jp3.setPreferredSize(new Dimension(350,35));
		
		other_field.add(lb1);
		other_field.add(jp0);
		other_field.add(lb2);
		other_field.add(jp1);
		other_field.add(lb3);
		other_field.add(jp2);
		other_field.add(lb4);
		other_field.add(jp3);
		other_field.add(lb5);
		
		other_field.setPreferredSize(new Dimension(180,290));
		
		left_addfield.add(other_field,BorderLayout.NORTH);
		
		left_addfield.setBorder(new CompoundBorder(new LineBorder(Color.LIGHT_GRAY, 3),new EmptyBorder(10,10,10,10)));
		
		
		
		JPanel content_panel = new JPanel(new BorderLayout());
		JPanel button_panel = new JPanel();
		button_panel.setBorder(new CompoundBorder(new EmptyBorder(10,0,0,0), new CompoundBorder(new LineBorder(Color.LIGHT_GRAY, 3),new EmptyBorder(10,10,10,10))));
		button_panel.setLayout(new GridLayout(1, 4, 10, 30));
		
		//table
		
		JScrollPane jp = new JScrollPane(table);
		JScrollPane jp_dv = new JScrollPane(table_dv);
		jp.setBorder(new LineBorder(Color.LIGHT_GRAY, 3));
		jp_dv.setBorder(new LineBorder(Color.LIGHT_GRAY, 3));
		
		left_addfield.add(jp_dv,BorderLayout.CENTER);
		
		Tim = Default_Custom_UI.default_Action_Button("Tìm", "Media/Icon/tim.gif");
//		Sua = Default_Custom_UI.default_Action_Button("Sửa", "Media/Icon/chinhsua.gif");
		Xoa = Default_Custom_UI.default_Action_Button("Xoá", "Media/Icon/xoa.gif");
		TaoLai = Default_Custom_UI.default_Action_Button("Tạo Lại", "Media/Icon/taolai.gif");
		
		button_panel.add(TaoLai);
		button_panel.add(Tim);
		button_panel.add(Xoa);
//		button_panel.add(Sua);
		
		content_panel.add(jp,BorderLayout.CENTER);
		
		content_panel.add(button_panel,BorderLayout.SOUTH);
		content_panel.setBorder(new EmptyBorder(0,10,0,0));
		
		center_panel.add(content_panel,BorderLayout.CENTER);
		
		main_pJPanel.add(center_panel,BorderLayout.CENTER);
		Xoa.addActionListener(this);
		display_HoaDon.add(main_pJPanel,BorderLayout.CENTER);
		
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(table)) {
			int row = table.getSelectedRow();
			HoaDon_txt_MaHoaDon.setText(table.getValueAt(row, 0).toString());
			HoaDon_txt_MaNhanVien.setText(table.getValueAt(row, 1).toString());
			HoaDon_txt_CCCD.setText(table.getValueAt(row, 2).toString());
			HoaDon_txt_MaSoPhong.setText(table.getValueAt(row, 3).toString());
			DanhSachHoaDon a = new DanhSachHoaDon();
			a.docDuLieu();
			HoaDon hd = a.getHoaDonByMa(table.getValueAt(row, 0).toString());
			String[][] datadv = new String[hd.getListDV().size()][3];
			for(int i = 0; i < hd.getListDV().size();i++) {
				datadv[i][0] = hd.getListDV().get(i).getMaDV();
				datadv[i][1] = hd.getListDV().get(i).getTenDV();
				datadv[i][0] = Float.toString(hd.getListDV().get(i).getGia());
			}
			model_dv.setDataVector(datadv, cols_name_dv);
			System.out.println();
			table_dv.setModel(model_dv);
			
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
		Object source = e.getSource();
		
		if(source.equals(Xoa)) {
			String maHD = HoaDon_txt_MaHoaDon.getText();
			DanhSachHoaDon listHD = new DanhSachHoaDon();
			listHD.docDuLieu();
			HoaDon hd = listHD.getHoaDonByMa(maHD);
			listHD.deteleHoaDon(hd);
			
			data = Default_Custom_UI.cast_data("ListHoaDon");
			model.setDataVector(data, cols_name);
			table.setModel(model);
			
			model_dv.setDataVector(data_dv, cols_name_dv);
			table_dv.setModel(model_dv);
			
		}
	}
}


