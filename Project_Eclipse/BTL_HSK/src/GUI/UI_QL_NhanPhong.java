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
import java.text.SimpleDateFormat;
import java.util.Date;
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
import Control.DanhSachPhieuDat;
import Control.DanhSachPhieuNhan;
import entity.PhieuDatPhong;
import entity.PhieuNhanPhong;

public class UI_QL_NhanPhong implements MouseListener,ActionListener{
	
	//--------DatPhong-----------//
	public JPanel display_NhanPhong;
	private JButton Them;
	private JButton Xoa;
	private JButton TaoLai;
	private JButton Tim;
	
	private JComboBox<String> NhanPhong_MaPhieuDat;
	private JTextField CCCD;
	private JTextField NgayDen;
	private JTextField NgayDi;
	
	private static SimpleDateFormat dateTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	private static SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
	
//	private DanhSachPhieuDat phieuDat;
	
	String[] cols_name = {"MÃ PHIẾU THUÊ","MÃ NHÂN VIÊN","CĂN CƯỚC CÔNG DÂN","MÃ SỐ PHÒNG","NGÀY ĐẾN","NGÀY ĐI"};

	private Object[][] data = Default_Custom_UI.cast_data("LayPhieuDatChuaNhan");

	
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
	
	public UI_QL_NhanPhong() {
		
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
		
		
		display_NhanPhong = new JPanel();
		display_NhanPhong = new JPanel();
		display_NhanPhong.setLayout(new BorderLayout());
		
		JPanel titleJPanel = new JPanel();
		
		titleJPanel.setBorder(new CompoundBorder(new LineBorder(Color.LIGHT_GRAY, 3),new EmptyBorder(10,10,10,10)));
		
		JLabel title = new JLabel("QUẢN LÍ NHẬN PHÒNG");
		
		JPanel center_panel = new JPanel();
		title.setFont(Default_Custom_UI.big_title_font);
		titleJPanel.add(title);
		title.setBorder(new EmptyBorder(5,5,5,5));
		display_NhanPhong.add(titleJPanel,BorderLayout.NORTH);
		
		JPanel main_pJPanel = new JPanel();
		main_pJPanel.setLayout(new BorderLayout());
		
		main_pJPanel.setBorder(new CompoundBorder(new LineBorder(Color.LIGHT_GRAY, 3),new EmptyBorder(10,10,10,10)));
		
		//center_panel
		//--------------------------------------------------------------------------pending
		center_panel.setLayout(new BorderLayout());
		
		JPanel left_addfield = new JPanel();
		
		center_panel.add(left_addfield,BorderLayout.WEST);
		

		NhanPhong_MaPhieuDat = Default_Custom_UI.add_data_ds_combo("PhieuDatPhongChuaNhan");
		CCCD = Default_Custom_UI.default_textfield();
		NgayDen = Default_Custom_UI.default_textfield();
		NgayDi = Default_Custom_UI.default_textfield();
		NgayDen.setEditable(false);
		NgayDi.setEditable(false);

		
		left_addfield.setPreferredSize(new Dimension(250,800));
		
		left_addfield.add(Default_Custom_UI.default_label("MÃ PHIẾU THUÊ"));
		left_addfield.add(NhanPhong_MaPhieuDat);
		
		left_addfield.add(Default_Custom_UI.default_label("NGÀY ĐẾN"));
		left_addfield.add(Default_Custom_UI.default_text_panel(NgayDen));
		left_addfield.add(Default_Custom_UI.default_label("NGÀY ĐI"));
		left_addfield.add(Default_Custom_UI.default_text_panel(NgayDi));
		
		left_addfield.add(Default_Custom_UI.default_label("CCCD"));
		left_addfield.add(Default_Custom_UI.default_text_panel(CCCD));

		left_addfield.setBorder(new CompoundBorder(new LineBorder(Color.LIGHT_GRAY, 3),new EmptyBorder(10,10,10,10)));
		
		JPanel content_panel = new JPanel(new BorderLayout());
		JPanel button_panel = new JPanel();
		button_panel.setBorder(new CompoundBorder(new EmptyBorder(10,0,0,0), new CompoundBorder(new LineBorder(Color.LIGHT_GRAY, 3),new EmptyBorder(10,10,10,10))));
		button_panel.setLayout(new GridLayout(1, 4, 10, 30));
		
		//table
		
		JScrollPane jp = new JScrollPane(table);
		jp.setBorder(new LineBorder(Color.LIGHT_GRAY, 3));
		
		Them = Default_Custom_UI.default_Action_Button("Nhận Phòng", "Media/Icon/them.gif");
		Xoa = Default_Custom_UI.default_Action_Button("Xoá", "Media/Icon/xoa.gif");

		TaoLai = Default_Custom_UI.default_Action_Button("Tạo Lại", "Media/Icon/taolai.gif");
		Tim = Default_Custom_UI.default_Action_Button("Tìm", "Media/Icon/tim.gif");

		
		button_panel.add(TaoLai);
		button_panel.add(Them);
		button_panel.add(Xoa);
		button_panel.add(Tim);
		
		content_panel.add(jp,BorderLayout.CENTER);
		
		content_panel.add(button_panel,BorderLayout.SOUTH);
		content_panel.setBorder(new EmptyBorder(0,10,0,0));
		
		center_panel.add(content_panel,BorderLayout.CENTER);
		
		main_pJPanel.add(center_panel,BorderLayout.CENTER);
		
		display_NhanPhong.add(main_pJPanel,BorderLayout.CENTER);
		
		Them.addActionListener(this);
		Tim.addActionListener(this);
		
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(table)) {
			int row = table.getSelectedRow();
			
			
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
		if(source.equals(Them)) {
			String maPhieu = NhanPhong_MaPhieuDat.getSelectedItem().toString();
			DanhSachPhieuDat listPD = new DanhSachPhieuDat();
			DanhSachPhieuNhan listPhieuNhan = new DanhSachPhieuNhan();
			
			listPD.docDuLieu();
			listPhieuNhan.docDuLieu();
			PhieuDatPhong pd = listPD.getPhieuDatPhongByMa(maPhieu);
			Date ngayNhan = new Date();
			Date gioNhan = new Date();
			
			int size = listPhieuNhan.getListPN().size();
			int duoi_PD = Integer.parseInt(pd.getMaPD().substring(2,pd.getMaPD().length())) +1 ;
			
			PhieuNhanPhong pn = new PhieuNhanPhong("PN"+(duoi_PD), pd, gioNhan, ngayNhan);
			listPhieuNhan.insertPhieuNhan(pn);
			
			data = Default_Custom_UI.cast_data("LayPhieuDatChuaNhan");
			model.setDataVector(data, cols_name);
			table.setModel(model);
		}
		if(source.equals(Tim)) {
			String cCCD = CCCD.getText();
			for(int i = 0; i < data.length;i++) {
				String temp = table.getValueAt(i, 2).toString();
				if(temp.equals(cCCD)) {
					System.out.println(temp.equals(cCCD));
					table.setRowSelectionInterval(i, i);
				}
			}
			
		}
	}
}


