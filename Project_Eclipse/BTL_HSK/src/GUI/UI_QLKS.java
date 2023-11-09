package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.Painter;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.rtf.RTFEditorKit;

import com.toedter.calendar.JCalendar;

public class UI_QLKS extends JFrame implements MouseListener,ActionListener{
//------------------------------------variable/cons-----------------------------------//
	//---variable for tool bar menu---//
	private JPanel menuJPanel;
	private JPanel displayPanel;
	
	//---------------TAB_SODOPHONG---------//
	private JPanel button_SoDoPhong;
	private JPanel display_SoDoPhong;
	private JButton btn_TimPhong = new JButton("Tìm Phòng");
	private JTextField txt_TimPhong = new JTextField(10);
	private JPanel button_TatCaPhong;
	private JPanel button_PhongDaNhan;
	private JPanel button_PhongTrong;
	//---------------TAB QUANLI------------//
	private JPanel button_QuanLi;
	private JPanel Button_QL_DatPhong;
		//--------DatPhong-----------//
		private JPanel display_DatPhong;
		private JButton datPhong_Then;
		private JButton DatPhong_Xoa;
		private JButton DatPhong_Sua;
		private JButton DatPhong_TaoLai;
		
		private JTextField DatPhong_txt_MaNV;
		private JTextField DatPhong_txt_CCCD;
		private JCalendar DatPhong_NgayDen;
		private JCalendar DatPhong_NgayDi;
		
	private JPanel Button_QL_NhanPhong;
	private JPanel Button_QL_TraPhong;
	//--------------TAB_TRACUU------------//
	private JPanel button_TraCuu;
	private JPanel button_TraCuu_KH;
		//------------KH--------------//
		private JPanel display_KH;
		private JButton KH_Then = new JButton("Thêm");
		private JButton KH_Xoa = new JButton("Xoá");
		private JButton KH_Sua = new JButton("Sửa");
		private JButton KH_TaoLai = new JButton("Tạo Lại");
	private JPanel button_TraCuu_NV;
	private JPanel button_TraCuu_DV;
	private JPanel button_TraCuu_HoaDon;
	//--------------TAB THONGKE------------//
	private JPanel button_ThongKe;
	
	//--------------FONT-----------------//
	private Font title_font = new Font("Arial",Font.BOLD, 15);
	private Font big_title_font = new Font("Arial",Font.BOLD, 25);
	private Font tag_font = new Font("Arial",Font.BOLD, 12);
	
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
		displayPanel.setBorder(new CompoundBorder(new LineBorder(Color.LIGHT_GRAY, 1),new EmptyBorder(0,0,0,0)));
		displayPanel.setBackground(Color.LIGHT_GRAY);
		Display_SoDoPhong();
		Display_KhachHang();
		Display_QL_DatPhong();
	}
	//--------------------------------DISPLAY_KHACHHANG--------------------------------//
	public void Display_KhachHang() {
		display_KH = new JPanel();
		display_KH = new JPanel();
		display_KH.setLayout(new BorderLayout());
		
		JPanel titleJPanel = new JPanel();
		
		titleJPanel.setBorder(new CompoundBorder(new LineBorder(Color.LIGHT_GRAY, 3),new EmptyBorder(10,10,10,10)));
		
		JLabel title = new JLabel("DANH SÁCH KHÁCH HÀNG");
		JPanel center_panel = new JPanel();
		title.setFont(big_title_font);
		titleJPanel.add(title);
		title.setBorder(new EmptyBorder(5,5,5,5));
		display_KH.add(titleJPanel,BorderLayout.NORTH);
		
		JPanel main_pJPanel = new JPanel();
		main_pJPanel.setLayout(new BorderLayout());
		JScrollPane jp = new JScrollPane(center_panel);
		
		main_pJPanel.setBorder(new CompoundBorder(new LineBorder(Color.LIGHT_GRAY, 3),new EmptyBorder(10,10,10,10)));
		
		//center_panel
		//--------------------------------------------------------------------------pending
		
		jp.setBorder(null);
		
		main_pJPanel.add(jp,BorderLayout.CENTER);
		
		display_KH.add(main_pJPanel,BorderLayout.CENTER);
	}
	//--------------------------------DISPLAY_QL_DATPHONG--------------------------------//
		public void Display_QL_DatPhong() {
			display_DatPhong = new JPanel();
			display_DatPhong = new JPanel();
			display_DatPhong.setLayout(new BorderLayout());
			
			JPanel titleJPanel = new JPanel();
			
			titleJPanel.setBorder(new CompoundBorder(new LineBorder(Color.LIGHT_GRAY, 3),new EmptyBorder(10,10,10,10)));
			
			JLabel title = new JLabel("QUẢN LÍ ĐẶT PHÒNG");
			JPanel center_panel = new JPanel();
			title.setFont(big_title_font);
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
			
			DatPhong_txt_MaNV = default_textfield();
			DatPhong_txt_CCCD = default_textfield();
			DatPhong_NgayDen = new JCalendar();
			DatPhong_NgayDi = new JCalendar();
			
			left_addfield.setPreferredSize(new Dimension(250,800));
			left_addfield.add(default_label("MÃ NHÂN VIÊN"));
			left_addfield.add(DatPhong_txt_MaNV);
			left_addfield.add(default_label("CCCD"));
			left_addfield.add(DatPhong_txt_CCCD);
			left_addfield.add(default_label("NGÀY ĐẾN"));
			left_addfield.add(DatPhong_NgayDen);
			left_addfield.add(default_label("NGÀY ĐI"));
			left_addfield.add(DatPhong_NgayDi);
			
			left_addfield.setBorder(new CompoundBorder(new LineBorder(Color.LIGHT_GRAY, 3),new EmptyBorder(10,10,10,10)));
			
			JScrollPane jp = new JScrollPane();
			jp.setBorder(new CompoundBorder(new LineBorder(Color.LIGHT_GRAY, 3),new EmptyBorder(10,10,10,10)));
			
			JPanel content_panel = new JPanel(new BorderLayout());
			JPanel button_panel = new JPanel();
			button_panel.setBorder(new CompoundBorder(new EmptyBorder(10,0,0,0), new CompoundBorder(new LineBorder(Color.LIGHT_GRAY, 3),new EmptyBorder(10,10,10,10))));
			button_panel.setLayout(new GridLayout(1, 4, 10, 30));
			
			
			datPhong_Then = default_Action_Button("Thêm", "Media/Icon/them.gif");
			DatPhong_Sua = default_Action_Button("Sửa", "Media/Icon/chinhsua.gif");
			DatPhong_Xoa = default_Action_Button("Xoá", "Media/Icon/xoa.gif");
			DatPhong_TaoLai = default_Action_Button("Tạo Lại", "Media/Icon/taolai.gif");
			
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
		}
	//--------------------------------DISPLAY_SoDoPhong--------------------------------//
	public void Display_SoDoPhong() {
		display_SoDoPhong = new JPanel();
		display_SoDoPhong.setLayout(new BorderLayout());
		
		JPanel titleJPanel = new JPanel();
		
		titleJPanel.setBorder(new CompoundBorder(new LineBorder(Color.LIGHT_GRAY, 3),new EmptyBorder(10,10,10,10)));
		
		JLabel title = new JLabel("SƠ ĐỒ PHÒNG KHÁCH SẠN");
		JPanel center_panel = new JPanel();
		title.setFont(big_title_font);
		titleJPanel.add(title);
		title.setBorder(new EmptyBorder(5,5,5,5));
		display_SoDoPhong.add(titleJPanel,BorderLayout.NORTH);
		
		JPanel main_pJPanel = new JPanel();
		main_pJPanel.setLayout(new BorderLayout());
		JScrollPane jp = new JScrollPane(center_panel);
		
		main_pJPanel.setBorder(new CompoundBorder(new LineBorder(Color.LIGHT_GRAY, 3),new EmptyBorder(10,10,10,10)));
		
		//custom_lai_thanh_scroll <on going>
		
		//tags room
		JPanel top_panel = new JPanel();
		top_panel.setLayout(new BorderLayout());
		JPanel tags_Panel = new JPanel();
		JPanel search_Panel = new JPanel();
		
		tags_Panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		button_TatCaPhong = default_RoomTag("Tất Cả", 40, Color.BLACK,Color.BLACK);
		tags_Panel.add(button_TatCaPhong);
		button_PhongTrong = default_RoomTag("Phòng Trống", 40, Color.GREEN,Color.WHITE);
		tags_Panel.add(button_PhongTrong);
		button_PhongDaNhan = default_RoomTag("Đang Bận", 40, Color.RED,Color.WHITE);
		tags_Panel.add(button_PhongDaNhan);
		
		button_TatCaPhong.addMouseListener(this);
		button_PhongDaNhan.addMouseListener(this);
		button_PhongTrong.addMouseListener(this);
		
		//search tab
		search_Panel.add(btn_TimPhong);
		
		btn_TimPhong.setBackground(Color.BLACK);
		btn_TimPhong.setForeground(Color.WHITE);
		btn_TimPhong.setBorder(new CompoundBorder(new LineBorder(Color.LIGHT_GRAY, 3),new EmptyBorder(10,10,10,10)));
		txt_TimPhong.setBorder(new CompoundBorder(new LineBorder(Color.LIGHT_GRAY, 3),new EmptyBorder(8,10,8,10)));
		txt_TimPhong.setFont(title_font);
		
		search_Panel.add(txt_TimPhong);
		
		btn_TimPhong.addActionListener(this);
		
		top_panel.add(tags_Panel,BorderLayout.WEST);
		top_panel.add(search_Panel,BorderLayout.EAST);
		
		//center_panel
		
		//--------------------------------------------------------------------------pending
		
		
		jp.setBorder(null);
		
		main_pJPanel.add(top_panel,BorderLayout.NORTH);
		main_pJPanel.add(jp,BorderLayout.CENTER);
		
		display_SoDoPhong.add(main_pJPanel,BorderLayout.CENTER);
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
        button_TraCuu.setComponentPopupMenu(popupMenu);
        
        button.addMouseListener(this);
        popupMenu.addMouseListener(this);
   
        button_TraCuu.add(button,BorderLayout.EAST);
	}
	//-----------------------------------DEFAULT-----------------------------------------//
	public JPanel default_RoomTag(String tag_name,int number,Color tag_color,Color fore_color) {
		JPanel default_tag = new JPanel();
		JLabel tags = new JLabel(tag_name +"(" + number + ")");
		tags.setFont(tag_font);
		default_tag.setBackground(tag_color);
		tags.setForeground(fore_color);
		default_tag.add(tags);
		default_tag.setBorder(new CompoundBorder(new LineBorder(Color.LIGHT_GRAY,2),new EmptyBorder(5,15,5,15)));
		
		return default_tag;
	}
	//-----------------------------------------------------------------------------pending
	public JPanel default_ROOM() {
		JPanel default_Room = new JPanel();
		
		
		return default_Room;
	}
	
	public JLabel default_button(String title) {
		JLabel defaultButton = new JLabel(title);
	
		defaultButton.setOpaque(false);
		defaultButton.setForeground(Color.WHITE);
		defaultButton.setBorder(null);
		defaultButton.setFont(title_font);
		
		return defaultButton;
	}
	
	public JButton default_Action_Button(String txt,String path) {
		ImageIcon icon = createImageIcon(path);
		icon = scaleImage(icon, 40, 40);
		
		JButton btn = new JButton(txt, icon);
		btn.setBackground(new Color(0,25,51));
		btn.setForeground(Color.WHITE);
		btn.setFont(title_font);
		btn.setBorder( new CompoundBorder(new LineBorder(Color.LIGHT_GRAY, 3),new EmptyBorder(5,15,5,15)));
		
		return btn;
	}
	
	public JLabel default_label(String txt) {
		JLabel lb = new JLabel(txt);
		lb.setFont(title_font);
		lb.setBorder(new EmptyBorder(5, 0, 5, 0));
		return lb;
	}
	
	public JPanel default_panel(String path,String title) {
		JPanel dJPanel = new JPanel();
		
		dJPanel.setLayout(new BorderLayout());
		ImageIcon icon = createImageIcon(path);
		icon = scaleImage(icon, 40, 40);
		dJPanel.add(new JLabel(icon),BorderLayout.WEST);
		dJPanel.add(default_button(title),BorderLayout.EAST);
		dJPanel.setOpaque(false);
		
		return dJPanel;
	}
	
	public JPanel default_child_panel(String path,String title) {
		JPanel child = default_panel(path,title);
		child.setBorder(new CompoundBorder(new LineBorder(new Color(244,255,255),2), new EmptyBorder(5,5,5,10)));
		return child;
	}
	
	public JTextField default_textfield() {
		JTextField txt = new JTextField(19);
		txt.setBorder(new CompoundBorder(new LineBorder(Color.LIGHT_GRAY, 2),new EmptyBorder(5,5,5,5)));
		return txt;
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
		mousePress_Menu(e.getSource());
		if(e.getSource().equals(button_TatCaPhong)) {
			button_TatCaPhong.setBackground(Color.WHITE);
		}
		if(e.getSource().equals(button_PhongTrong)) {
			button_PhongTrong.setBackground(new Color(0,204,102));
		}
		if(e.getSource().equals(button_PhongDaNhan)) {
			button_PhongDaNhan.setBackground(new Color(255,102,102));
		}
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
		button_TatCaPhong.setBackground(new Color(224,224,224));
		button_PhongTrong.setBackground(new Color(0,102,0));
		button_PhongDaNhan.setBackground(new Color(204,0,0));
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
			displayPanel.add(display_SoDoPhong);
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
			displayPanel.add(display_DatPhong);
			validate();
			repaint();
		}
		if(e.equals(Button_QL_NhanPhong)) {
			Button_QL_NhanPhong.setBackground(new Color(0,51,51));
			Button_QL_NhanPhong.setOpaque(true);
		}
		if(e.equals(Button_QL_TraPhong)) {
			Button_QL_TraPhong.setBackground(new Color(0,51,51));
			Button_QL_TraPhong.setOpaque(true);
		}
		if(e.equals(button_TraCuu_KH)) {
			button_TraCuu_KH.setBackground(new Color(0,51,51));
			button_TraCuu_KH.setOpaque(true);
			
			displayPanel.removeAll();
			displayPanel.add(display_KH);
			validate();
			repaint();
		}
		if(e.equals(button_TraCuu_NV)) {
			button_TraCuu_NV.setBackground(new Color(0,51,51));
			button_TraCuu_NV.setOpaque(true);
		}
		if(e.equals(button_TraCuu_HoaDon)) {
			button_TraCuu_HoaDon.setBackground(new Color(0,51,51));
			button_TraCuu_HoaDon.setOpaque(true);
		}
		if(e.equals(button_TraCuu_DV)) {
			button_TraCuu_DV.setBackground(new Color(0,51,51));
			button_TraCuu_DV.setOpaque(true);
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source =e.getSource();
		
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
