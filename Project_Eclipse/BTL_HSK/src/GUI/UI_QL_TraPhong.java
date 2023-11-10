package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

public class UI_QL_TraPhong implements MouseListener{
	
	//--------DatPhong-----------//
	public JPanel display_TraPhong;
	private JButton datPhong_Then;
	private JButton DatPhong_Xoa;
	private JButton DatPhong_Sua;
	private JButton DatPhong_TaoLai;
	
	private JComboBox<String> datPhong_cb_SoPhong;
	private JComboBox<String> DatPhong_cb_MaNV;
	private JTextField DatPhong_txt_CCCD;
	private JDateChooser DatPhong_NgayDen;
	private JDateChooser DatPhong_NgayDi;
	
//	private DanhSachPhieuDat phieuDat;
	
	
	String[] cols_name = {"MÃ PHIẾU THUÊ","MÃ NHÂN VIÊN","CĂN CƯỚC CÔNG DÂN","MÃ SỐ PHÒNG","NGÀY ĐẾN","NGÀY ĐI"};
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
	
	public UI_QL_TraPhong() {
		
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
		
		
		display_TraPhong = new JPanel();
		display_TraPhong = new JPanel();
		display_TraPhong.setLayout(new BorderLayout());
		
		JPanel titleJPanel = new JPanel();
		
		titleJPanel.setBorder(new CompoundBorder(new LineBorder(Color.LIGHT_GRAY, 3),new EmptyBorder(10,10,10,10)));
		
		JLabel title = new JLabel("QUẢN LÍ TRẢ PHÒNG / LẬP HÓA ĐƠN");
		
		JPanel center_panel = new JPanel();
		title.setFont(Default_Custom_UI.big_title_font);
		titleJPanel.add(title);
		title.setBorder(new EmptyBorder(5,5,5,5));
		display_TraPhong.add(titleJPanel,BorderLayout.NORTH);
		
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
		
		left_addfield.setBorder(new CompoundBorder(new LineBorder(Color.LIGHT_GRAY, 3),new EmptyBorder(10,10,10,10)));
		
		
		
		JPanel content_panel = new JPanel(new BorderLayout());
		JPanel button_panel = new JPanel();
		button_panel.setBorder(new CompoundBorder(new EmptyBorder(10,0,0,0), new CompoundBorder(new LineBorder(Color.LIGHT_GRAY, 3),new EmptyBorder(10,10,10,10))));
		button_panel.setLayout(new GridLayout(1, 4, 10, 30));
		
		//table
		
		JScrollPane jp = new JScrollPane(table);
		jp.setBorder(new LineBorder(Color.LIGHT_GRAY, 3));
		
		datPhong_Then = Default_Custom_UI.default_Action_Button("Thêm", "Media/Icon/them.gif");
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
		
		display_TraPhong.add(main_pJPanel,BorderLayout.CENTER);
		
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(table)) {
			
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


