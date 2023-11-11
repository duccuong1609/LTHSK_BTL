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
import java.util.Date;

import javax.swing.BoxLayout;
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
import com.toedter.calendar.JDateChooser;

import Control.DanhSachKhachHang;
import Control.DanhSachNhanVien;
import Control.DanhSachPhieuDat;
import Control.DanhSachPhong;
import entity.KhachHang;
import entity.NhanVien;
import entity.PhieuDatPhong;
import entity.Phong;
import entity.PhongThuong;
import entity.PhongVip;

public class UI_QL_DatPhong implements MouseListener,ActionListener{
	
	
	//--------DatPhong-----------//
	public JPanel display_DatPhong;
	private JButton datPhong_Then;
	private JButton DatPhong_Xoa;
	private JButton DatPhong_Sua;
	private JButton DatPhong_TaoLai;
	
	private JComboBox<String> datPhong_cb_SoPhong;
	private JComboBox<String> DatPhong_cb_MaNV;
	private JTextField DatPhong_txt_CCCD;
	private JDateChooser DatPhong_NgayDen;
	private JDateChooser DatPhong_NgayDi;
	private JTextField DatPhong_GiaPhong;
	
//	private DanhSachPhieuDat phieuDat;
	
	
	String[] cols_name = {"MÃ PHIẾU THUÊ","MÃ NHÂN VIÊN","CĂN CƯỚC CÔNG DÂN","MÃ SỐ PHÒNG","NGÀY ĐẾN","NGÀY ĐI"};
	private String [][] data = Default_Custom_UI.cast_data("LayPhieuDatChuaNhan");
	
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
	
	public UI_QL_DatPhong() {
		
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
		
		
		display_DatPhong = new JPanel();
		display_DatPhong = new JPanel();
		display_DatPhong.setLayout(new BorderLayout());
		
		JPanel titleJPanel = new JPanel();
		
		titleJPanel.setBorder(new CompoundBorder(new LineBorder(Color.LIGHT_GRAY, 3),new EmptyBorder(10,10,10,10)));
		
		JLabel title = new JLabel("QUẢN LÍ ĐẶT PHÒNG");
		JPanel center_panel = new JPanel();
		title.setFont(Default_Custom_UI.big_title_font);
		titleJPanel.add(title);
		title.setBorder(new EmptyBorder(5,5,5,5));
		display_DatPhong.add(titleJPanel,BorderLayout.NORTH);
		
		JPanel main_pJPanel = new JPanel();
		main_pJPanel.setLayout(new BorderLayout());
		
		main_pJPanel.setBorder(new CompoundBorder(new LineBorder(Color.LIGHT_GRAY, 3),new EmptyBorder(10,10,10,10)));
		
		//center_panel
		//--------------------------------------------------------------------------pending
		center_panel.setLayout(new BorderLayout());
		
		JPanel left_addfield = new JPanel();
		
		center_panel.add(left_addfield,BorderLayout.WEST);
		
		datPhong_cb_SoPhong = Default_Custom_UI.add_data_ds_combo("Phong");
		DatPhong_cb_MaNV = Default_Custom_UI.add_data_ds_combo("NV");
		DatPhong_txt_CCCD = Default_Custom_UI.default_textfield();
		DatPhong_NgayDen = Default_Custom_UI.defaultDateChooser();
		DatPhong_NgayDi = Default_Custom_UI.defaultDateChooser();
		DatPhong_GiaPhong = Default_Custom_UI.default_textfield();
		DatPhong_GiaPhong.setEditable(false);
		
		JPanel txt_panel = new JPanel();
		txt_panel.setLayout(new BoxLayout(txt_panel, BoxLayout.X_AXIS));
		txt_panel.setPreferredSize(new Dimension(220,35));
		txt_panel.add(DatPhong_txt_CCCD);
		
		
		left_addfield.setPreferredSize(new Dimension(250,800));
		
		left_addfield.add(Default_Custom_UI.default_label("MÃ PHÒNG"));
		left_addfield.add(datPhong_cb_SoPhong); 
		left_addfield.add(Default_Custom_UI.default_label("MÃ NHÂN VIÊN"));
		left_addfield.add(DatPhong_cb_MaNV);
		left_addfield.add(Default_Custom_UI.default_label("CCCD"));
		left_addfield.add(txt_panel);
		left_addfield.add(Default_Custom_UI.default_label("NGÀY ĐẾN"));
		left_addfield.add(DatPhong_NgayDen);
		left_addfield.add(Default_Custom_UI.default_label("NGÀY ĐI"));
		left_addfield.add(DatPhong_NgayDi);
		left_addfield.add(Default_Custom_UI.default_label("GIÁ PHÒNG / 1 NGÀY"));
		left_addfield.add(Default_Custom_UI.default_text_panel(DatPhong_GiaPhong));
		
		left_addfield.setBorder(new CompoundBorder(new LineBorder(Color.LIGHT_GRAY, 3),new EmptyBorder(10,10,10,10)));
		
		
		
		JPanel content_panel = new JPanel(new BorderLayout());
		JPanel button_panel = new JPanel();
		button_panel.setBorder(new CompoundBorder(new EmptyBorder(10,0,0,0), new CompoundBorder(new LineBorder(Color.LIGHT_GRAY, 3),new EmptyBorder(10,10,10,10))));
		button_panel.setLayout(new GridLayout(1, 4, 10, 30));
		
		//table
		
		JScrollPane jp = new JScrollPane(table);
		jp.setBorder(new LineBorder(Color.LIGHT_GRAY, 3));
		
		datPhong_Then = Default_Custom_UI.default_Action_Button("Đặt Phòng", "Media/Icon/them.gif");
		DatPhong_Sua = Default_Custom_UI.default_Action_Button("Sửa", "Media/Icon/chinhsua.gif");
		DatPhong_Xoa = Default_Custom_UI.default_Action_Button("Xoá", "Media/Icon/xoa.gif");
		DatPhong_TaoLai = Default_Custom_UI.default_Action_Button("Tạo Lại", "Media/Icon/taolai.gif");
		
		button_panel.add(DatPhong_TaoLai);
		button_panel.add(datPhong_Then);
		button_panel.add(DatPhong_Xoa);
		button_panel.add(DatPhong_Sua);
		
		content_panel.add(jp,BorderLayout.CENTER);
		
		content_panel.add(button_panel,BorderLayout.SOUTH);
		content_panel.setBorder(new EmptyBorder(0,10,0,0));
		
		center_panel.add(content_panel,BorderLayout.CENTER);
		
		main_pJPanel.add(center_panel,BorderLayout.CENTER);
		
		display_DatPhong.add(main_pJPanel,BorderLayout.CENTER);
		
		datPhong_cb_SoPhong.addActionListener(this);
		datPhong_Then.addActionListener(this);
		DatPhong_Xoa.addActionListener(this);
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(table)) {
			int row = table.getSelectedRow();
			String ma_PhieuDat = model.getValueAt(row, 0).toString();
			
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
		
		if(source.equals(datPhong_cb_SoPhong)) {
			DanhSachPhong ds = new DanhSachPhong();
			ds.docDuLieu();
			int sophong = Integer.parseInt(datPhong_cb_SoPhong.getSelectedItem().toString());
			if(ds.getListPhong().get(sophong-1)!= null) {
				
				if(ds.getListPhong().get(sophong-1) instanceof PhongVip) {
					DatPhong_GiaPhong.setText("300000");
				}
				else {
					DatPhong_GiaPhong.setText("150000");
				}
				
			}
		}
		
		if(source.equals(datPhong_Then)){
			String CCCD = DatPhong_txt_CCCD.getText();
			Date ngayDen = DatPhong_NgayDen.getDate();
			Date ngayDi = DatPhong_NgayDi.getDate();
			String maNV = DatPhong_cb_MaNV.getSelectedItem().toString();
			int soPhong = Integer.parseInt(datPhong_cb_SoPhong.getSelectedItem().toString());
			DanhSachPhieuDat list = new DanhSachPhieuDat();
			NhanVien nv = new DanhSachNhanVien().getNhanVienByMa(maNV);
			KhachHang kh = new DanhSachKhachHang().getKhachHangByMa(CCCD);
			DanhSachPhong phongs = new DanhSachPhong();
			phongs.docDuLieu();
			if(kh == null) {
				JOptionPane.showMessageDialog(display_DatPhong,"Khách hàng không tồn tại!");
			}
			Phong phong = phongs.getPhongBySoPhong(soPhong);
			System.out.println(phong.getSoPhong());
			DanhSachPhong a = new DanhSachPhong();
			a.addPhong(phong);
			list.docDuLieu();
			PhieuDatPhong pd = new PhieuDatPhong("PD"+(list.getListPDP().size()+100), nv, kh, a, 1, ngayDen, ngayDi);
			
			
			new DanhSachPhieuDat().insertPhieuDatSQL(pd);
			
			data = Default_Custom_UI.cast_data("LayPhieuDatChuaNhan");
			model.setDataVector(data, cols_name);
			table.setModel(model);
			
		}
		if(source.equals(DatPhong_Xoa)) {
			int row = table.getSelectedRow();
			String ma_PhieuDat = model.getValueAt(row, 0).toString();
			DanhSachPhieuDat listPD = new DanhSachPhieuDat();
			listPD.docDuLieu();
			PhieuDatPhong pd = listPD.getPhieuDatPhongByMa(ma_PhieuDat);
			
		}
	}
	
	
}


