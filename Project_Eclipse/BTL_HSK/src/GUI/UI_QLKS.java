package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.Painter;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import ConnectDB.Database;
import Control.DanhSachNhanVien;
import Control.DanhSachPhong;

@SuppressWarnings("serial")
public class UI_QLKS extends JFrame implements ActionListener,MouseListener{

//------------------------------------variable/cons-----------------------------------//
	//---variable for tool bar menu---//
	private JPanel menuJPanel;
	private JPanel displayPanel;
	
	//---------------TAB_SODOPHONG---------//
	private JPanel button_SoDoPhong;
	
	//---------------TAB QUANLI------------//
	private JPanel button_QuanLi;
	private JPanel Button_QL_DatPhong;
		
	private JPanel Button_QL_NhanPhong;
	private JPanel Button_QL_TraPhong;
	//--------------TAB_TRACUU------------//
	private JPanel button_TraCuu;
	private JPanel button_TraCuu_KH;
	private JPanel button_TraCuu_NV;
	private JPanel button_TraCuu_DV;
	private JPanel button_TraCuu_HoaDon;
	//--------------TAB THONGKE------------//
	private JPanel button_ThongKe;
	//--------------Connect---------------//
	Database connect ;
	
	//--------------Data-----------------//
	DanhSachPhong listPhong ;
	DanhSachNhanVien listNhanVien;
	
	//------------------------------------Main UI-----------------------------------------//
	public UI_QLKS() {
		super("Phầm Mềm Quản Lí Khách Sạn");
		
		connect = new Database();
		connect.connect();
		
		listPhong = new DanhSachPhong();
		
		//-------------tool bar menu ---------//
		
		menuJPanel = new JPanel();
		menuJPanel.setLayout(new BorderLayout());
		displayPanel = new JPanel();
		displayPanel.setLayout(new BorderLayout());

		Menu();
		Display();
		
		getContentPane().setBackground(Color.LIGHT_GRAY);
		
		add(menuJPanel,BorderLayout.WEST);
		add(displayPanel,BorderLayout.CENTER);
		
		// TODO Auto-generated constructor stub
		setSize(1380,720);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	//-------------------------------------Menu---------------------------------------//
	public void Menu() {
		
		Tab_SodoPhong();
		Tab_QuanLi();
		Tab_TraCuu();
		Tab_ThongKe();
		
		Box top_menu = Box.createVerticalBox();
		top_menu.add(button_SoDoPhong);
		top_menu.add(button_QuanLi);
		top_menu.add(button_TraCuu);
		top_menu.add(button_ThongKe);
		
		menuJPanel.add(top_menu,BorderLayout.NORTH);
		
		ImageIcon logo = Default_Custom_UI.createImageIcon("Media/Icon/logo.png");
		logo = Default_Custom_UI.scaleImage(logo, 200, 500);
		JLabel logo_label = new JLabel(logo);
		menuJPanel.add(logo_label,BorderLayout.SOUTH);
		
		menuJPanel.setBorder(new CompoundBorder(new LineBorder(Color.LIGHT_GRAY, 3),new EmptyBorder(20,20,5,20)));
		
	}
	//--------------------------------DISPLAY--------------------------------//
	public void Display() {
		displayPanel.setBorder(new CompoundBorder(new LineBorder(Color.LIGHT_GRAY, 1),new EmptyBorder(0,0,0,0)));
		displayPanel.setBackground(Color.LIGHT_GRAY);
	}
	
	//--------------------------------TAB-SODOPHONG--------------------------//
	public void Tab_SodoPhong() {
		button_SoDoPhong = new JPanel();
		button_SoDoPhong.setBorder(new CompoundBorder(new LineBorder(Color.lightGray,3),new EmptyBorder(15,15,15,15)));
		button_SoDoPhong.addMouseListener(this);
		
		ImageIcon icon_ql = Default_Custom_UI.createImageIcon("Media/Icon/Home.gif");
		icon_ql = Default_Custom_UI.scaleImage(icon_ql, 40, 40);
		button_SoDoPhong.setBackground(new Color(0,25,51));
		
		JLabel button = Default_Custom_UI.default_button("SƠ ĐỒ PHÒNG");
		
		button.setBorder(new EmptyBorder(5,5,0,0));
		
		button_SoDoPhong.setLayout(new BorderLayout());
		button_SoDoPhong.add(new JLabel(icon_ql),BorderLayout.WEST);
		button_SoDoPhong.add(button,BorderLayout.EAST);
		
	}
	//-----------------------------------TAB_THONGKE--------------------------//
	public void Tab_ThongKe() {
		button_ThongKe = new JPanel();
		button_ThongKe.setBorder(new CompoundBorder(new LineBorder(Color.lightGray,3),new EmptyBorder(15,15,15,15)));
		button_ThongKe.addMouseListener(this);
		
		ImageIcon icon_ql = Default_Custom_UI.createImageIcon("Media/Icon/thongke.gif");
		icon_ql = Default_Custom_UI.scaleImage(icon_ql, 40, 40);
		button_ThongKe.setBackground(new Color(0,25,51));
		
		JLabel button = Default_Custom_UI.default_button("THỐNG KÊ");
		
		button.setBorder(new EmptyBorder(5,5,0,0));
		
		button_ThongKe.setLayout(new BorderLayout());
		button_ThongKe.add(new JLabel(icon_ql),BorderLayout.WEST);
		button_ThongKe.add(button,BorderLayout.EAST);
		
	}
	//-----------------------------------TAB QUAN LI-------------------------------------//
	public void Tab_QuanLi() {
		// TAB QUẢN LÍ
		button_QuanLi = new JPanel();
		button_QuanLi.setBorder(new CompoundBorder(new LineBorder(Color.LIGHT_GRAY,3),new EmptyBorder(15,15,15,15)));
		button_QuanLi.addMouseListener(this);
		
		ImageIcon icon_ql = Default_Custom_UI.createImageIcon("Media/Icon/datphong.gif");
		icon_ql = Default_Custom_UI.scaleImage(icon_ql, 40, 40);
		button_QuanLi.setBackground(new Color(0,25,51));
		
		button_QuanLi.setLayout(new BorderLayout());
		button_QuanLi.add(new JLabel(icon_ql),BorderLayout.WEST);
		
        JLabel button = Default_Custom_UI.default_button("NGHIỆP VỤ");
        
        JPopupMenu popupMenu = new JPopupMenu();
        
        Button_QL_DatPhong = Default_Custom_UI.default_child_panel("Media/Icon/traphong.gif", "Quản Lí Đặt Phòng");
        Button_QL_DatPhong.addMouseListener(this);
        Button_QL_NhanPhong = Default_Custom_UI.default_child_panel("Media/Icon/dichvu.gif", "Quản Lí Nhận Phòng");
        Button_QL_NhanPhong.addMouseListener(this);
        Button_QL_TraPhong = Default_Custom_UI.default_child_panel("Media/Icon/hoadon.gif", "Quản Lí Trả Phòng");
        Button_QL_TraPhong.addMouseListener(this);
        
        popupMenu.add(Button_QL_DatPhong);
        popupMenu.add(Button_QL_NhanPhong);
        popupMenu.add(Button_QL_TraPhong);
        
        popupMenu.setBorder(new LineBorder(new Color(204,255,255),2));
        popupMenu.setBackground(new Color(0,25,51));
        button_QuanLi.setComponentPopupMenu(popupMenu);
        
        button.addMouseListener(this);
        popupMenu.addMouseListener(this);
   
        button_QuanLi.add(button,BorderLayout.EAST);
	}
	//-----------------------------------TAB TRA CUU-------------------------------------//
	public void Tab_TraCuu() {
		// TAB QUẢN LÍ
		button_TraCuu = new JPanel();
		button_TraCuu.setBorder(new CompoundBorder(new LineBorder(Color.LIGHT_GRAY,3),new EmptyBorder(15,15,15,15)));
		button_TraCuu.addMouseListener(this);
		
		ImageIcon icon_tc = Default_Custom_UI.createImageIcon("Media/Icon/tracuu.gif");
		icon_tc = Default_Custom_UI.scaleImage(icon_tc, 40, 40);
		button_TraCuu.setBackground(new Color(0,25,51));
		
		button_TraCuu.setLayout(new BorderLayout());
		button_TraCuu.add(new JLabel(icon_tc),BorderLayout.WEST);
		
        JLabel button = Default_Custom_UI.default_button("Tra Cứu");
        
        JPopupMenu popupMenu = new JPopupMenu();
        
        button_TraCuu_KH = Default_Custom_UI.default_child_panel("Media/Icon/khachhang.gif", "Tra Cứu Khách Hàng");
        button_TraCuu_KH.addMouseListener(this);
        button_TraCuu_DV = Default_Custom_UI.default_child_panel("Media/Icon/dichvu.gif", "Tra Cứu Dịch Vụ");
        button_TraCuu_DV.addMouseListener(this);
        button_TraCuu_NV = Default_Custom_UI.default_child_panel("Media/Icon/nhanvien.gif", "Tra Cứu Nhân Viên");
        button_TraCuu_NV.addMouseListener(this);
        button_TraCuu_HoaDon = Default_Custom_UI.default_child_panel("Media/Icon/chinhsua.gif", "Tra Cứu Hóa Đơn");
        button_TraCuu_HoaDon.addMouseListener(this);
        
        popupMenu.add(button_TraCuu_KH);
        popupMenu.add(button_TraCuu_NV);
        popupMenu.add(button_TraCuu_DV);
        popupMenu.add(button_TraCuu_HoaDon);
        
        popupMenu.setBorder(new LineBorder(new Color(204,255,255),2));
        popupMenu.setBackground(new Color(0,25,51));
        button_TraCuu.setComponentPopupMenu(popupMenu);
        
        button.addMouseListener(this);
        popupMenu.addMouseListener(this);
   
        button_TraCuu.add(button,BorderLayout.EAST);
	}
	
	
	
	//-------------------------------------Action/Mouse---------------------------------//
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		mousePress_Menu(e.getSource());
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		button_SoDoPhong.setBackground(new Color(0,25,51));
		button_TraCuu.setBackground(new Color(0,25,51));
		button_ThongKe.setBackground(new Color(0,25,51));
		button_QuanLi.setBackground(new Color(0,25,51));
		
		Button_QL_DatPhong.setBackground(Color.BLACK);
		Button_QL_DatPhong.setOpaque(false);
		Button_QL_NhanPhong.setBackground(Color.BLACK);
		Button_QL_NhanPhong.setOpaque(false);
		Button_QL_TraPhong.setBackground(Color.BLACK);
		Button_QL_TraPhong.setOpaque(false);
		
		button_TraCuu_DV.setBackground(Color.BLACK);
		button_TraCuu_DV.setOpaque(false);
		button_TraCuu_KH.setBackground(Color.BLACK);
		button_TraCuu_KH.setOpaque(false);
		button_TraCuu_NV.setBackground(Color.BLACK);
		button_TraCuu_NV.setOpaque(false);
		button_TraCuu_HoaDon.setBackground(Color.BLACK);
		button_TraCuu_HoaDon.setOpaque(false);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource().equals(button_QuanLi)) {
			button_QuanLi.setBorder(new CompoundBorder(new LineBorder(new Color(255,153,153),3),new EmptyBorder(15,15,15,15)));
		}
		if(e.getSource().equals(button_SoDoPhong)) {
			button_SoDoPhong.setBorder(new CompoundBorder(new LineBorder(new Color(255,153,153),3),new EmptyBorder(15,15,15,15)));
		}
		
		if(e.getSource().equals(button_TraCuu)) {
			button_TraCuu.setBorder(new CompoundBorder(new LineBorder(new Color(255,153,153),3),new EmptyBorder(15,15,15,15)));
		}
		
		if(e.getSource().equals(button_ThongKe)) {
			button_ThongKe.setBorder(new CompoundBorder(new LineBorder(new Color(255,153,153),3),new EmptyBorder(15,15,15,15)));
		}
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource().equals(button_QuanLi)) {
			button_QuanLi.setBorder(new CompoundBorder(new LineBorder(Color.LIGHT_GRAY,3),new EmptyBorder(15,15,15,15)));
		}
		if(e.getSource().equals(button_SoDoPhong)) {
			button_SoDoPhong.setBorder(new CompoundBorder(new LineBorder(Color.LIGHT_GRAY,3),new EmptyBorder(15,15,15,15)));
		}
		if(e.getSource().equals(button_TraCuu)) {
			button_TraCuu.setBorder(new CompoundBorder(new LineBorder(Color.LIGHT_GRAY,3),new EmptyBorder(15,15,15,15)));
		}
		if(e.getSource().equals(button_ThongKe)) {
			button_ThongKe.setBorder(new CompoundBorder(new LineBorder(Color.LIGHT_GRAY,3),new EmptyBorder(15,15,15,15)));
		}
	}
	//--------------------------------------mouse action menu----------------------------------------/
	public void mousePress_Menu(Object e) {
		if(e.equals(button_SoDoPhong)) {
			button_SoDoPhong.setBackground(new Color(0,51,51));
			displayPanel.removeAll();
			
			UI_SoDoPhong soDoPhong = new UI_SoDoPhong(); 
			displayPanel.add(soDoPhong.display_SoDoPhong);
			validate();
			repaint();
		}
		if(e.equals(button_TraCuu)) {
			button_TraCuu.setBackground(new Color(0,51,51));

		}
		if(e.equals(button_QuanLi)) {
			button_QuanLi.setBackground(new Color(0,51,51));
			
		}
		if(e.equals(button_ThongKe)) {
			button_ThongKe.setBackground(new Color(0,51,51));
			
		}
		
		if(e.equals(Button_QL_DatPhong)) {
			Button_QL_DatPhong.setBackground(new Color(0,51,51));
			Button_QL_DatPhong.setOpaque(true);
			displayPanel.removeAll();
			
			UI_QL_DatPhong QL_DATPHONG =  new UI_QL_DatPhong();
			displayPanel.add(QL_DATPHONG.display_DatPhong);
			
			Database db = new Database();
			db.connect();
			
			validate();
			repaint();
		}
		if(e.equals(Button_QL_NhanPhong)) {
			Button_QL_NhanPhong.setBackground(new Color(0,51,51));
			Button_QL_NhanPhong.setOpaque(true);
			displayPanel.removeAll();
			
			UI_QL_NhanPhong QL_NHANPHONG =  new UI_QL_NhanPhong();
			displayPanel.add(QL_NHANPHONG.display_NhanPhong);
			
			Database db = new Database();
			db.connect();
			
			validate();
			repaint();
		}
		if(e.equals(Button_QL_TraPhong)) {
			Button_QL_TraPhong.setBackground(new Color(0,51,51));
			Button_QL_TraPhong.setOpaque(true);
			displayPanel.removeAll();
			
			UI_QL_TraPhong QL_TRAPHONG =  new UI_QL_TraPhong();
			displayPanel.add(QL_TRAPHONG.display_TraPhong);
			
			Database db = new Database();
			db.connect();
			
			validate();
			repaint();
		}
		if(e.equals(button_TraCuu_KH)) {
			Button_QL_TraPhong.setBackground(new Color(0,51,51));
			Button_QL_TraPhong.setOpaque(true);
			displayPanel.removeAll();
			
			UI_TC_KH KH = new UI_TC_KH();
			displayPanel.add(KH.display_KH);
			
			Database db = new Database();
			db.connect();
			
			validate();
			repaint();
		}
		if(e.equals(button_TraCuu_NV)) {
			Button_QL_TraPhong.setBackground(new Color(0,51,51));
			Button_QL_TraPhong.setOpaque(true);
			displayPanel.removeAll();
			
			UI_TC_NV NV = new UI_TC_NV();
			displayPanel.add(NV.display_NV);
			
			Database db = new Database();
			db.connect();
			
			validate();
			repaint();
		}
		if(e.equals(button_TraCuu_HoaDon)) {
			button_TraCuu_HoaDon.setBackground(new Color(0,51,51));
			button_TraCuu_HoaDon.setOpaque(true);
			displayPanel.removeAll();
			
			UI_TC_HOADON NV = new UI_TC_HOADON();
			displayPanel.add(NV.display_HoaDon);
			
			Database db = new Database();
			db.connect();
			
			validate();
			repaint();
		}
		if(e.equals(button_TraCuu_DV)) {
			button_TraCuu_DV.setBackground(new Color(0,51,51));
			button_TraCuu_DV.setOpaque(true);
			displayPanel.removeAll();
			
			UI_TC_DV dv = new UI_TC_DV();
			displayPanel.add(dv.display_DV);
			
			Database db = new Database();
			db.connect();
			
			validate();
			repaint();
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
//		Object source =e.getSource();
		
	}
}

class BackgroundPainter implements Painter<JComponent> {

private Color color = null;

BackgroundPainter(Color c) {
    color = c;
}

@Override
public void paint(Graphics2D g, JComponent object, int width, int height) {
    if (color != null) {
        g.setColor(color);
        g.fillRect(0, 0, width - 1, height - 1);
    }
}
}
@SuppressWarnings("serial")
class CustomComboBoxRenderer extends DefaultListCellRenderer {
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
       JLabel lbl = (JLabel)super.getListCellRendererComponent(list, value, index, isSelected,  cellHasFocus);
       lbl.setBorder(BorderFactory.createEmptyBorder(7,7,7,7));
       lbl.setBackground(Color.WHITE);
       return lbl;
    }
 }
