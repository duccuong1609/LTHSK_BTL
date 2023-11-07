package bTL_HSK.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.Painter;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class UI_QLKS extends JFrame implements ActionListener,MouseListener{
//------------------------------------variable/cons-----------------------------------//
	//---variable for tool bar menu---//
	private JPanel menuJPanel;
	private JPanel displayPanel;
	
	//---------------TAB_SODOPHONG---------//
	private JPanel button_SoDoPhong;
	private JPanel display_SoDoPhong;
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
	
	//--------------FONT-----------------//
	private Font title_font = new Font("Arial",Font.BOLD, 15);
	private Font big_title_font = new Font("Arial",Font.BOLD, 20);
	
	//------------------------------------Main UI-----------------------------------------//
	public UI_QLKS() {
		super("Phầm Mềm Quản Lí Khách Sạn");
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
		setSize(1280,720);
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
		
		ImageIcon logo = createImageIcon("Media/Icon/logo.png");
		logo = scaleImage(logo, 200, 500);
		JLabel logo_label = new JLabel(logo);
		menuJPanel.add(logo_label,BorderLayout.SOUTH);
		
		menuJPanel.setBorder(new CompoundBorder(new LineBorder(Color.LIGHT_GRAY, 3),new EmptyBorder(20,20,5,20)));
		
	}
	//--------------------------------DISPLAY--------------------------------//
	public void Display() {
		displayPanel.setBorder(new CompoundBorder(new LineBorder(Color.LIGHT_GRAY, 3),new EmptyBorder(0,20,0,20)));
		Display_SoDoPhong();
	}
	//--------------------------------DISPLAY_SoDoPhong--------------------------------//
	public void Display_SoDoPhong() {
		display_SoDoPhong = new JPanel();
		JLabel title = new JLabel("SƠ ĐỒ PHÒNG KHÁCH SẠN");
		title.setFont(big_title_font);
		title.setBorder(new EmptyBorder(5,5,5,5));
		display_SoDoPhong.add(title,BorderLayout.NORTH);
	}
	//--------------------------------TAB-SODOPHONG--------------------------//
	public void Tab_SodoPhong() {
		button_SoDoPhong = new JPanel();
		button_SoDoPhong.setBorder(new CompoundBorder(new LineBorder(Color.lightGray,3),new EmptyBorder(15,15,15,15)));
		button_SoDoPhong.addMouseListener(this);
		
		ImageIcon icon_ql = createImageIcon("Media/Icon/Home.gif");
		icon_ql = scaleImage(icon_ql, 40, 40);
		button_SoDoPhong.setBackground(new Color(0,25,51));
		
		JLabel button = default_button("SƠ ĐỒ PHÒNG");
		
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
		
		ImageIcon icon_ql = createImageIcon("Media/Icon/thongke.gif");
		icon_ql = scaleImage(icon_ql, 40, 40);
		button_ThongKe.setBackground(new Color(0,25,51));
		
		JLabel button = default_button("THỐNG KÊ");
		
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
		
		ImageIcon icon_ql = createImageIcon("Media/Icon/datphong.gif");
		icon_ql = scaleImage(icon_ql, 40, 40);
		button_QuanLi.setBackground(new Color(0,25,51));
		
		button_QuanLi.setLayout(new BorderLayout());
		button_QuanLi.add(new JLabel(icon_ql),BorderLayout.WEST);
		
        JLabel button = default_button("NGHIỆP VỤ");
        
        JPopupMenu popupMenu = new JPopupMenu();
        
        Button_QL_DatPhong = default_child_panel("Media/Icon/traphong.gif", "Quản Lí Đặt Phòng");
        Button_QL_DatPhong.addMouseListener(this);
        Button_QL_NhanPhong = default_child_panel("Media/Icon/dichvu.gif", "Quản Lí Nhận Phòng");
        Button_QL_NhanPhong.addMouseListener(this);
        Button_QL_TraPhong = default_child_panel("Media/Icon/hoadon.gif", "Quản Lí Trả Phòng");
        Button_QL_TraPhong.addMouseListener(this);
        
        popupMenu.add(Button_QL_DatPhong);
        popupMenu.add(Button_QL_NhanPhong);
        popupMenu.add(Button_QL_TraPhong);
        
        popupMenu.setBorder(new LineBorder(new Color(204,255,255),2));
        popupMenu.setBackground(new Color(0,25,51));
        button.setComponentPopupMenu(popupMenu);
        
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
		
		ImageIcon icon_tc = createImageIcon("Media/Icon/tracuu.gif");
		icon_tc = scaleImage(icon_tc, 40, 40);
		button_TraCuu.setBackground(new Color(0,25,51));
		
		button_TraCuu.setLayout(new BorderLayout());
		button_TraCuu.add(new JLabel(icon_tc),BorderLayout.WEST);
		
        JLabel button = default_button("Tra Cứu");
        
        JPopupMenu popupMenu = new JPopupMenu();
        
        button_TraCuu_KH = default_child_panel("Media/Icon/khachhang.gif", "Tra Cứu Khách Hàng");
        button_TraCuu_KH.addMouseListener(this);
        button_TraCuu_DV = default_child_panel("Media/Icon/dichvu.gif", "Tra Cứu Dịch Vụ");
        button_TraCuu_DV.addMouseListener(this);
        button_TraCuu_NV = default_child_panel("Media/Icon/nhanvien.gif", "Tra Cứu Nhân Viên");
        button_TraCuu_NV.addMouseListener(this);
        button_TraCuu_HoaDon = default_child_panel("Media/Icon/chinhsua.gif", "Tra Cứu Hóa Đơn");
        button_TraCuu_HoaDon.addMouseListener(this);
        
        popupMenu.add(button_TraCuu_KH);
        popupMenu.add(button_TraCuu_NV);
        popupMenu.add(button_TraCuu_DV);
        popupMenu.add(button_TraCuu_HoaDon);
        
        popupMenu.setBorder(new LineBorder(new Color(204,255,255),2));
        popupMenu.setBackground(new Color(0,25,51));
        button.setComponentPopupMenu(popupMenu);
        
        button.addMouseListener(this);
        popupMenu.addMouseListener(this);
   
        button_TraCuu.add(button,BorderLayout.EAST);
	}
	//-----------------------------------DEFAULT-----------------------------------------//
	public JLabel default_button(String title) {
		JLabel defaultButton = new JLabel(title);
	
		defaultButton.setOpaque(false);
		defaultButton.setForeground(Color.WHITE);
		defaultButton.setBorder(null);
		defaultButton.setFont(title_font);
		
		return defaultButton;
	}
	
	public JPanel default_panel(String path,String title) {
		JPanel dJPanel = new JPanel();
		
		dJPanel.setLayout(new BorderLayout());
		ImageIcon icon = createImageIcon(path);
		icon = scaleImage(icon, 40, 40);
		dJPanel.add(new JLabel(icon),BorderLayout.WEST);
		dJPanel.add(new JPanel() {
			@Override
			public void setOpaque(boolean isOpaque) {
				// TODO Auto-generated method stub
				super.setOpaque(false);
			}
		},BorderLayout.CENTER);
		dJPanel.add(default_button(title),BorderLayout.EAST);
		dJPanel.setOpaque(false);
		return dJPanel;
	}
	
	public JPanel default_child_panel(String path,String title) {
		JPanel child = default_panel(path,title);
		child.setBorder(new CompoundBorder(new LineBorder(new Color(244,255,255),2), new EmptyBorder(5,5,5,10)));
		return child;
	}
	
	//-----------------------------------IMAGE-------------------------------------------//
	protected static ImageIcon createImageIcon(String path) {
		try {
			ImageIcon icon = new ImageIcon(path);
			return icon;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Cannot find find image");
		}
		return null;
    }
	public ImageIcon scaleImage(ImageIcon icon, int w, int h)
    {
        int nw = icon.getIconWidth();
        int nh = icon.getIconHeight();

        if(icon.getIconWidth() > w)
        {
          nw = w;
          nh = (nw * icon.getIconHeight()) / icon.getIconWidth();
        }

        if(nh > h)
        {
          nh = h;
          nw = (icon.getIconWidth() * nh) / icon.getIconHeight();
        }

        return new ImageIcon(icon.getImage().getScaledInstance(nw, nh, Image.SCALE_DEFAULT));
    }
	
	//-------------------------------------Action/Mouse---------------------------------//
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(button_SoDoPhong)) {
			button_SoDoPhong.setBackground(new Color(0,51,51));
			displayPanel.removeAll();
			displayPanel.add(display_SoDoPhong);
			validate();
		}
		
		if(e.getSource().equals(Button_QL_DatPhong)) {
			Button_QL_DatPhong.setBackground(new Color(0,51,51));
			Button_QL_DatPhong.setOpaque(true);
		}
		if(e.getSource().equals(Button_QL_NhanPhong)) {
			Button_QL_NhanPhong.setBackground(new Color(0,51,51));
			Button_QL_NhanPhong.setOpaque(true);
		}
		if(e.getSource().equals(Button_QL_TraPhong)) {
			Button_QL_TraPhong.setBackground(new Color(0,51,51));
			Button_QL_TraPhong.setOpaque(true);
		}
		if(e.getSource().equals(button_TraCuu_KH)) {
			button_TraCuu_KH.setBackground(new Color(0,51,51));
			button_TraCuu_KH.setOpaque(true);
		}
		if(e.getSource().equals(button_TraCuu_NV)) {
			button_TraCuu_NV.setBackground(new Color(0,51,51));
			button_TraCuu_NV.setOpaque(true);
		}
		if(e.getSource().equals(button_TraCuu_HoaDon)) {
			button_TraCuu_HoaDon.setBackground(new Color(0,51,51));
			button_TraCuu_HoaDon.setOpaque(true);
		}
		if(e.getSource().equals(button_TraCuu_DV)) {
			button_TraCuu_DV.setBackground(new Color(0,51,51));
			button_TraCuu_DV.setOpaque(true);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		button_SoDoPhong.setBackground(new Color(0,25,51));
		
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
		Object sourceObject = e.getSource();
		
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
		Object sourceObject = e.getSource();
		
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
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
