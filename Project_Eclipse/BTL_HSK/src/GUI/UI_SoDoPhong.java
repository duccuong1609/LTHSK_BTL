package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import Control.DanhSachPhong;
import entity.Phong;
import entity.PhongVip;

public class UI_SoDoPhong implements MouseListener,ActionListener{
	//--------------------------------DISPLAY_SoDoPhong--------------------------------//
	public JPanel display_SoDoPhong;
	private JButton btn_TimPhong = new JButton("Tìm Phòng");
	private JTextField txt_TimPhong = new JTextField(10);
	private JPanel button_TatCaPhong;
	private JPanel button_PhongDaNhan;
	private JPanel button_PhongTrong;
	private JPanel center_panel;
	//data
	DanhSachPhong listPhong;
	
	public UI_SoDoPhong() {
		display_SoDoPhong = new JPanel();
		display_SoDoPhong.setLayout(new BorderLayout());
		listPhong = new DanhSachPhong();
		
		JPanel titleJPanel = new JPanel();
		
		titleJPanel.setBorder(new CompoundBorder(new LineBorder(Color.LIGHT_GRAY, 3),new EmptyBorder(10,10,10,10)));
		
		JLabel title = new JLabel("SƠ ĐỒ PHÒNG KHÁCH SẠN");
		center_panel = new JPanel();
		title.setFont(Default_Custom_UI.big_title_font);
		titleJPanel.add(title);
		title.setBorder(new EmptyBorder(5,5,5,5));
		display_SoDoPhong.add(titleJPanel,BorderLayout.NORTH);
		
		JPanel main_pJPanel = new JPanel();
		main_pJPanel.setLayout(new BorderLayout());
		
		JScrollPane scrollBar = Default_Custom_UI.default_scrollpane(center_panel);
		
		main_pJPanel.setBorder(new CompoundBorder(new LineBorder(Color.LIGHT_GRAY, 3),new EmptyBorder(10,10,10,10)));
		
		//custom_lai_thanh_scroll <on going>
		
		//tags room
		JPanel top_panel = new JPanel();
		top_panel.setLayout(new BorderLayout());
		JPanel tags_Panel = new JPanel();
		JPanel search_Panel = new JPanel();
		
		tags_Panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		button_TatCaPhong = Default_Custom_UI.default_RoomTag("Tất Cả",listPhong.docDuLieu().size(), new Color(244,244,244),Color.BLACK);
		tags_Panel.add(button_TatCaPhong);
		button_PhongTrong = Default_Custom_UI.default_RoomTag("Phòng Trống", listPhong.getListTrangThaiPhong(1).getListPhong().size(), new Color(0,153,0),Color.WHITE);
		tags_Panel.add(button_PhongTrong);
		button_PhongDaNhan = Default_Custom_UI.default_RoomTag("Đang Bận", listPhong.getListTrangThaiPhong(0).getListPhong().size(), new Color(255,51,51),Color.WHITE);

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
		txt_TimPhong.setFont(Default_Custom_UI.title_font);
		
		search_Panel.add(txt_TimPhong);
		
		btn_TimPhong.addActionListener(this);
		
		top_panel.add(tags_Panel,BorderLayout.WEST);
		top_panel.add(search_Panel,BorderLayout.EAST);
		
		//center_panel
		center_panel.setLayout(new WrapLayout());
		get_All_Phong(center_panel);
		
		scrollBar.setBorder(new EmptyBorder(20,0,0,0));
		
		main_pJPanel.add(top_panel,BorderLayout.NORTH);
		main_pJPanel.add(scrollBar,BorderLayout.CENTER);
		
		display_SoDoPhong.add(main_pJPanel,BorderLayout.CENTER);
	}
	public JPanel Create_tab_Phong(boolean empty,String tenPhong,boolean vip) {
		JPanel container = new JPanel();
		container.setLayout(new BorderLayout());
		JPanel rightJPanel = new JPanel();
		rightJPanel.setLayout(new BoxLayout(rightJPanel, BoxLayout.Y_AXIS));
		
		ImageIcon icon;
		ImageIcon icon_vip;
		JLabel trong; 
		
		JPanel icon_panel = new JPanel();
		icon_panel.setOpaque(false);
		
		if(empty == true) {
			trong = new JLabel("TRỐNG");
			trong.setBorder(new EmptyBorder(50,60,0,60));
			icon = Default_Custom_UI.createImageIcon("Media/Icon/empty.gif");
			icon = Default_Custom_UI.scaleImage(icon, 30, 30);
			rightJPanel.add(trong);
			trong.setAlignmentX(Component.CENTER_ALIGNMENT);
			rightJPanel.setBackground(new Color(0,153,0));
			
		}
		else {
			icon = Default_Custom_UI.createImageIcon("Media/Icon/unempty.gif");
			icon = Default_Custom_UI.scaleImage(icon, 30, 30);
			trong = new JLabel("ĐANG BẬN");
			trong.setBorder(new EmptyBorder(50,40,0,40));
			rightJPanel.add(trong);
			trong.setAlignmentX(Component.CENTER_ALIGNMENT);
			rightJPanel.setBackground(new Color(255,51,51));
		}
		
		if(vip == true) {
			icon_vip = Default_Custom_UI.createImageIcon("Media/Icon/vip.gif");
			icon_vip = Default_Custom_UI.scaleImage(icon_vip, 30, 30);
			icon_panel.add(new JLabel(icon_vip));
		}
		container.setBorder(new CompoundBorder(new EmptyBorder(5,5,5,5),new LineBorder(Color.LIGHT_GRAY, 3)));
		JLabel maPhong = new JLabel("TÊN PHÒNG");
		
		trong.setFont(Default_Custom_UI.big_title_font);
		trong.setForeground(Color.WHITE);
		
		maPhong.setFont(Default_Custom_UI.title_font);
		maPhong.setForeground(Color.WHITE);
		maPhong.setBorder(new EmptyBorder(7,7,2,7));
		
		JLabel TenPhong = new JLabel(tenPhong);
		TenPhong.setFont(Default_Custom_UI.big_title_font);
		TenPhong.setForeground(Color.WHITE);
		TenPhong.setBorder(new EmptyBorder(7,7,7,7));
		JPanel leftJPanel = new JPanel();
		leftJPanel.setLayout(new BoxLayout(leftJPanel, BoxLayout.Y_AXIS));
		leftJPanel.setBorder(new EmptyBorder(7,7,7,7));
		JLabel icon_label = new JLabel(icon);
		
		icon_panel.add(icon_label);
		
		TenPhong.setAlignmentX(Component.CENTER_ALIGNMENT);
		icon_panel.setAlignmentX(Component.CENTER_ALIGNMENT);
		maPhong.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		leftJPanel.add(maPhong);
		leftJPanel.add(TenPhong);
		leftJPanel.add(icon_panel);
		
		leftJPanel.setBackground(new Color(0,25,51));
		
		container.add(leftJPanel,BorderLayout.WEST);
		container.add(rightJPanel,BorderLayout.CENTER);
		
		return container;
	}
	
	public void get_All_Phong(JPanel container) {
		listPhong = new DanhSachPhong();
		get_PhongTrong(container, listPhong);
		get_PhongBan(container, listPhong);
	}
	public void get_PhongTrong(JPanel container,DanhSachPhong list) {
		DanhSachPhong listPhong = list.getListTrangThaiPhong(1);
		
		for(int i=0;i<listPhong.getListPhong().size();i++) {
			String tenPhong = listPhong.getListPhong().get(i).getTenPhong();
			boolean trangThai = listPhong.getListPhong().get(i).isIsEmpty();
			if(listPhong.getListPhong().get(i) instanceof PhongVip) {
				container.add(Create_tab_Phong(trangThai, tenPhong, true));
			}
			else {
				container.add(Create_tab_Phong(trangThai, tenPhong, false));
			}
		}
	}
	public void get_PhongBan(JPanel container,DanhSachPhong list) {
		DanhSachPhong listPhong = list.getListTrangThaiPhong(0);
		for(int i=0;i<listPhong.getListPhong().size();i++) {
			String tenPhong = listPhong.getListPhong().get(i).getTenPhong();
			boolean trangThai = listPhong.getListPhong().get(i).isIsEmpty();
			
			if(listPhong.getListPhong().get(i) instanceof PhongVip) {
				container.add(Create_tab_Phong(trangThai, tenPhong, true));
			}
			else {
				container.add(Create_tab_Phong(trangThai, tenPhong, false));
			}
		}
	}
	public JPanel timPhong(JPanel container,DanhSachPhong list,int ma) {
		for(int i=0;i<listPhong.getListTrangThaiPhong(1).getListPhong().size();i++) {
			if(listPhong.getListTrangThaiPhong(1).getListPhong().get(i).getSoPhong() == ma) {
				String tenPhong = listPhong.getListTrangThaiPhong(1).getListPhong().get(i).getTenPhong();
				boolean trangThai = listPhong.getListTrangThaiPhong(1).getListPhong().get(i).isIsEmpty();
				
				return Create_tab_Phong(trangThai, tenPhong, true);
			}
		}
		for(int i=0;i<listPhong.getListTrangThaiPhong(0).getListPhong().size();i++) {
			if(listPhong.getListTrangThaiPhong(0).getListPhong().get(i).getSoPhong() == ma) {
				String tenPhong = listPhong.getListTrangThaiPhong(0).getListPhong().get(i).getTenPhong();
				boolean trangThai = listPhong.getListTrangThaiPhong(0).getListPhong().get(i).isIsEmpty();
				
				return Create_tab_Phong(trangThai, tenPhong, true);
			}
		}
		JOptionPane.showMessageDialog(display_SoDoPhong, "KHÔNG TÌM THẤY MÃ PHÒNG");
		return null;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(button_TatCaPhong)) {
			button_TatCaPhong.setBackground(Color.WHITE);
			center_panel.removeAll();
			get_All_Phong(center_panel);
		}
		if(e.getSource().equals(button_PhongTrong)) {
			button_PhongTrong.setBackground(new Color(0,204,102));
			center_panel.removeAll();
			get_PhongTrong(center_panel, new DanhSachPhong());
		}
		if(e.getSource().equals(button_PhongDaNhan)) {
			button_PhongDaNhan.setBackground(new Color(255,102,102));
			center_panel.removeAll();
			get_PhongBan(center_panel, new DanhSachPhong());
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(button_TatCaPhong)) {
			button_TatCaPhong.setBackground(new Color(244,244,244));
		}
		if(e.getSource().equals(button_PhongTrong)) {
			button_PhongTrong.setBackground(new Color(0,153,0));
		}
		if(e.getSource().equals(button_PhongDaNhan)) {
			button_PhongDaNhan.setBackground(new Color(255,51,51));
		}
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
		if(e.getSource().equals(btn_TimPhong)) {
			try {
				int ma = Integer.parseInt(txt_TimPhong.getText());
				if(timPhong(center_panel, new DanhSachPhong(), ma)!=null) {
					center_panel.removeAll();
					center_panel.add(timPhong(center_panel, new DanhSachPhong(), ma));
				}
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(display_SoDoPhong,"Mã Phòng Nhập Vào Phải Là Số");
			}
			
		}
		
	}
}
