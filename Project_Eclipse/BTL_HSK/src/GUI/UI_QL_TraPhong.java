package GUI;

import static org.junit.Assert.isArray;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.JobAttributes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.EventObject;
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.CellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;

import org.junit.runners.model.FrameworkMethod;

import com.toedter.calendar.JDateChooser;

import Control.DanhSachDichVu;
import Control.DanhSachHoaDon;
import Control.DanhSachPhieuNhan;
import entity.DichVu;
import entity.HoaDon;
import entity.PhieuNhanPhong;

public class UI_QL_TraPhong implements MouseListener,ActionListener{
	
	//--------DatPhong-----------//
	public JPanel display_TraPhong;
	private JButton Them;
	private JButton TaoLai;
	private JButton Tim;
	
	private JTextField CCCD;
	private JTextField MAPHONG;
	
//	private DanhSachPhieuDat phieuDat;
	
	
	String[] cols_name = {"MÃ PHIẾU NHẬN","CĂN CƯỚC CÔNG DÂN","MÃ SỐ PHÒNG","NGÀY NHẬN"};
	private Object[][] data = Default_Custom_UI.cast_data("ChuaTraPhong");
	String[] cols_dv_name = {"MÃ DỊCH VỤ","TÊN DỊCH VỤ","SỬ DỤNG"};
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
	private DefaultTableModel model_dv = new DefaultTableModel(data_dv,cols_dv_name) {
		public boolean isCellEditable(int row, int column) {
			if(column==0) {
				return false;
			}
			return true;
		};
	};
	
	private JTable table = new JTable(model);
	private JTable table_dv = new JTable(model_dv);
	
	public UI_QL_TraPhong() {
		
		//--------------------------------------table-----------------------------//
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
		//---------------------------------------------------------------------//
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
		
		//------------------------------------------------------------------------=//
		display_TraPhong = new JPanel();
		display_TraPhong = new JPanel();
		display_TraPhong.setLayout(new BorderLayout());
		display_TraPhong.setBackground(new Color(255,250,245));
		
		JPanel titleJPanel = new JPanel();
		titleJPanel.setBackground(new Color(255,250,245));
		
		titleJPanel.setBorder(new CompoundBorder(new LineBorder(Color.LIGHT_GRAY, 3),new EmptyBorder(10,10,10,10)));
		
		JLabel title = new JLabel("QUẢN LÍ TRẢ PHÒNG / LẬP HÓA ĐƠN");
		
		JPanel center_panel = new JPanel();
		center_panel.setBackground(new Color(255,250,245));
		title.setFont(Default_Custom_UI.big_title_font);
		titleJPanel.add(title);
		title.setBorder(new EmptyBorder(5,5,5,5));
		display_TraPhong.add(titleJPanel,BorderLayout.NORTH);
		
		JPanel main_pJPanel = new JPanel();
		main_pJPanel.setBackground(new Color(255,250,245));
		
		main_pJPanel.setLayout(new BorderLayout());
		
		main_pJPanel.setBorder(new CompoundBorder(new LineBorder(Color.LIGHT_GRAY, 3),new EmptyBorder(10,10,10,10)));
		
		//center_panel
		//--------------------------------------------------------------------------pending
		center_panel.setLayout(new BorderLayout());
		center_panel.setBackground(new Color(255,250,245));
		
		JPanel left_addfield = new JPanel();
		left_addfield.setBackground(new Color(255,250,245));
		
		left_addfield.setLayout(new BorderLayout());
		
		center_panel.add(left_addfield,BorderLayout.WEST);
		
		left_addfield.setPreferredSize(new Dimension(350,800));
		
		JScrollPane jp_dv = new JScrollPane(table_dv);
		jp_dv.setBorder(new LineBorder(Color.LIGHT_GRAY, 3));
		jp_dv.getViewport().setBackground(new Color(255,250,245));
		
		CCCD = Default_Custom_UI.default_textfield();
		JPanel cccd_panel = Default_Custom_UI.default_text_panel(CCCD);
		cccd_panel.setPreferredSize(new Dimension(320,35));
		cccd_panel.setBackground(new Color(255,250,245));
		MAPHONG = Default_Custom_UI.default_textfield();
		JPanel ma_panel = Default_Custom_UI.default_text_panel(MAPHONG);
		ma_panel.setBackground(new Color(255,250,245));
		ma_panel.setPreferredSize(new Dimension(320,35));
		left_addfield.add(jp_dv,BorderLayout.CENTER);
		
		JLabel lb1 = Default_Custom_UI.default_label("CĂN CƯỚC CÔNG DÂN");
		lb1.setPreferredSize(new Dimension(300,25));
		lb1.setBackground(new Color(255,250,245));
		JLabel lb2 = Default_Custom_UI.default_label("MÃ SỐ PHÒNG");
		lb2.setPreferredSize(new Dimension(300,25));
		lb2.setBackground(new Color(255,250,245));
		JLabel lb3 = Default_Custom_UI.default_label("DỊCH VỤ ĐÃ SỬ DỤNG");
		lb3.setPreferredSize(new Dimension(300,25));
		lb3.setBackground(new Color(255,250,245));
		
		JPanel other_field = new JPanel();
		other_field.setBackground(new Color(255,250,245));
		other_field.add(lb1);
		other_field.add(cccd_panel);
		other_field.add(lb2);
		other_field.add(ma_panel);
		other_field.add(lb3);
		other_field.setPreferredSize(new Dimension(180,180));
		
		left_addfield.add(other_field,BorderLayout.NORTH);
		
		left_addfield.setBorder(new CompoundBorder(new LineBorder(Color.LIGHT_GRAY, 3),new EmptyBorder(10,10,10,10)));
		left_addfield.setBackground(new Color(255,250,245));
		
		JPanel content_panel = new JPanel(new BorderLayout());
		JPanel button_panel = new JPanel();
		button_panel.setBorder(new CompoundBorder(new EmptyBorder(10,0,0,0), new CompoundBorder(new LineBorder(Color.LIGHT_GRAY, 3),new EmptyBorder(10,10,10,10))));
		button_panel.setLayout(new GridLayout(1, 4, 10, 30));
		button_panel.setBackground(new Color(255,250,245));
		
		//table
		
		JScrollPane jp = new JScrollPane(table);
		jp.setBorder(new LineBorder(Color.LIGHT_GRAY, 3));
		jp.getViewport().setBackground(new Color(255,250,245));
		
		Them = Default_Custom_UI.default_Action_Button("TRẢ PHÒNG", "Media/Icon/them.gif");
		TaoLai = Default_Custom_UI.default_Action_Button("Tạo Lại", "Media/Icon/taolai.gif");
		Tim = Default_Custom_UI.default_Action_Button("Tìm", "Media/Icon/tim.gif");
		
		button_panel.add(TaoLai);
		button_panel.add(Them);
		button_panel.add(Tim);
		
		data_dv = Default_Custom_UI.cast_data("DichVu");
		model_dv.setDataVector(data_dv, cols_dv_name);
		table_dv.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(new JCheckBox()));
		
		content_panel.add(jp,BorderLayout.CENTER);
		center_panel.setBackground(new Color(255,250,245));
		
		content_panel.add(button_panel,BorderLayout.SOUTH);
		content_panel.setBorder(new EmptyBorder(0,10,0,0));
		
		content_panel.setBackground(new Color(255,250,245));
		center_panel.add(content_panel,BorderLayout.CENTER);
		
		main_pJPanel.add(center_panel,BorderLayout.CENTER);
		main_pJPanel.setBackground(new Color(255,250,245));
		display_TraPhong.add(main_pJPanel,BorderLayout.CENTER);
		Them.addActionListener(this);
		Tim.addActionListener(this);
		TaoLai.addActionListener(this);
		
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(table)) {
			selectionData();
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


	public void selectionData() {
		int row = table.getSelectedRow();
		CCCD.setText(table.getValueAt(row, 1).toString());
		MAPHONG.setText(table.getValueAt(row, 2).toString());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if(source.equals(Them)) {
			int row = table.getSelectedRow();
			if(row == -1) {
				JOptionPane.showMessageDialog(display_TraPhong, "Không Có Phiếu Nhận Nào Được Chọn !");
				return;
			}
			String maPhieu = table.getValueAt(row, 0).toString();
			int soPhong = Integer.parseInt(table.getValueAt(row, 2).toString());
			DanhSachPhieuNhan listPN = new DanhSachPhieuNhan();
			listPN.docDuLieu();
			PhieuNhanPhong pn = listPN.getPhieuNhanByMa(maPhieu);
			Date ngayTra = new Date();
			Date gioTra = new Date();
			
			DanhSachDichVu listDV = new DanhSachDichVu();
			listDV.docDuLieu();
			DanhSachDichVu dvs = new DanhSachDichVu();
			for(int i =0 ;i < data_dv.length;i++) {
				if(table_dv.getValueAt(i, 2) != null && table_dv.getValueAt(i, 2).toString().equals("true")) {
					String maDV =  table_dv.getValueAt(i, 0).toString();
					DichVu dv = listDV.getDichVuByMa(maDV);
					dvs.addDichVu(dv);
				}
			}
			
			
			DanhSachHoaDon listHD = new DanhSachHoaDon();
			listHD.docDuLieu();
			int size = listHD.getListHD().size();
			
			int duoi_HD = Integer.parseInt(pn.getMaPhieuNhan().substring(2,pn.getMaPhieuNhan().length())) +1 ;

			HoaDon a = new HoaDon("HD"+(duoi_HD), pn, dvs.getDichVu(), ngayTra, gioTra);
			
			int choose = JOptionPane.showConfirmDialog(display_TraPhong, "Bạn Có Chắc Muốn Trả Phòng Không ?","Chú Ý",JOptionPane.YES_OPTION);
			if(choose == JOptionPane.YES_OPTION) {
				if(listHD.addHoaDon(a))
					listPN.traPhong(soPhong);
				
				JOptionPane.showMessageDialog(display_TraPhong, "Trả Phòng/ Lập Hoá Đơn Thành Công !");
				
				data = Default_Custom_UI.cast_data("ChuaTraPhong");
				model.setDataVector(data, cols_name);
				
				table.setModel(model);
				
				model_dv.setDataVector(data_dv, cols_dv_name);
				table_dv.setModel(model_dv);
				table_dv.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(new JCheckBox()));
			}
		}
		if(source.equals(TaoLai)) {
			table.clearSelection();
			CCCD.setText("");
			MAPHONG.setText("");
			data = Default_Custom_UI.cast_data("ChuaTraPhong");
			model.setDataVector(data, cols_name);
			table.setModel(model);
		}
		if(source.equals(Tim)) {
			
			if(CCCD.getText().equals("")) {
				JOptionPane.showMessageDialog(display_TraPhong, "CCCD Không Được Để Trống");
				return;
			}
			if(MAPHONG.getText().equals("")) {
				JOptionPane.showMessageDialog(display_TraPhong, "Mã Số Phòng Không Được Để Trống");
				return;
			}
			
			String cCCD = CCCD.getText();
			String soPhong = MAPHONG.getText();
			
			ArrayList<Integer> list_temp = new ArrayList<Integer>();
			
			for(int i =0 ; i < data.length;i++) {
				String temp1 = table.getValueAt(i, 1).toString();
				String temp2 = table.getValueAt(i, 2).toString();
				if(cCCD.equals(temp1) && soPhong.equals(temp2)) {
					list_temp.add(i);
				}
			}
			
			if(list_temp.size() == 0) {
				JOptionPane.showMessageDialog(display_TraPhong, "Không Tìm Thấy Phiếu Nhận !");
				return;
			}
			data = new String[list_temp.size()][4];
			for(int i=0;i<list_temp.size();i++) {
				data[i][0] = model.getValueAt(list_temp.get(i), 0).toString();
				data[i][1] = model.getValueAt(list_temp.get(i), 1).toString();
				data[i][2] = model.getValueAt(list_temp.get(i), 2).toString();
				data[i][3] = model.getValueAt(list_temp.get(i), 3).toString();
			}

			JOptionPane.showMessageDialog(display_TraPhong, "Tìm Phiếu Nhận Thành Công !");
			selectionData();
			model.setDataVector(data, cols_name);
			table.setModel(model);
			table.setRowSelectionInterval(0, 0);
			selectionData();
		}
	}
}


