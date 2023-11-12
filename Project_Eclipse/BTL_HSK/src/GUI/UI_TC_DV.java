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

import Control.DanhSachDichVu;
import entity.DichVu;
import entity.NhanVien;

public class UI_TC_DV implements MouseListener,ActionListener{
	
	//--------DatPhong-----------//
	public JPanel display_DV;
	private JButton Then;
	private JButton Xoa;
	private JButton Sua;
	private JButton TaoLai;
	private JButton Tim;

	private JTextField DV_txt_MADV;
	private JTextField DV_txt_TENDV;
	private JTextField DV_txt_GIADV;
	
//	private DanhSachPhieuDat phieuDat;
	
	
	String[] cols_name = {"MÃ DỊCH VỤ","TÊN DỊCH VỤ","GIÁ DỊCH VỤ"};
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
	
	public UI_TC_DV() {
		//setup data
		data = Default_Custom_UI.cast_data("DichVu_FULL");
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
		
		
		display_DV = new JPanel();
		display_DV = new JPanel();
		display_DV.setLayout(new BorderLayout());
		
		JPanel titleJPanel = new JPanel();
		
		titleJPanel.setBorder(new CompoundBorder(new LineBorder(Color.LIGHT_GRAY, 3),new EmptyBorder(10,10,10,10)));
		
		JLabel title = new JLabel("TRA CỨU DỊCH VỤ");
		
		JPanel center_panel = new JPanel();
		title.setFont(Default_Custom_UI.big_title_font);
		titleJPanel.add(title);
		title.setBorder(new EmptyBorder(5,5,5,5));
		display_DV.add(titleJPanel,BorderLayout.NORTH);
		
		JPanel main_pJPanel = new JPanel();
		main_pJPanel.setLayout(new BorderLayout());
		
		main_pJPanel.setBorder(new CompoundBorder(new LineBorder(Color.LIGHT_GRAY, 3),new EmptyBorder(10,10,10,10)));
		
		//center_panel
		//--------------------------------------------------------------------------pending
		center_panel.setLayout(new BorderLayout());
		
		JPanel left_addfield = new JPanel();
		
		center_panel.add(left_addfield,BorderLayout.WEST);
		
		DV_txt_MADV = Default_Custom_UI.default_textfield();
		DV_txt_TENDV = Default_Custom_UI.default_textfield();
		DV_txt_GIADV = Default_Custom_UI.default_textfield();
		
		left_addfield.setPreferredSize(new Dimension(250,800));
		
		left_addfield.add(Default_Custom_UI.default_label("MÃ DỊCH VỤ"));
		left_addfield.add(Default_Custom_UI.default_text_panel(DV_txt_MADV)); 
		
		left_addfield.add(Default_Custom_UI.default_label("TÊN DỊCH VỤ"));
		left_addfield.add(Default_Custom_UI.default_text_panel(DV_txt_TENDV)); 
		
		left_addfield.add(Default_Custom_UI.default_label("GIÁ DỊCH VỤ"));
		left_addfield.add(Default_Custom_UI.default_text_panel(DV_txt_GIADV)); 
		
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
		
		content_panel.add(jp,BorderLayout.CENTER);
		
		left_addfield.setBackground(new Color(255,250,245));
		button_panel.setBackground(new Color(255,250,245));
		titleJPanel.setBackground(new Color(255,250,245));
		content_panel.setBackground(new Color(255,250,245));
		jp.getViewport().setBackground(new Color(255,250,245));
		content_panel.setBorder(new EmptyBorder(0,10,0,0));
		main_pJPanel.setBackground(new Color(255,250,245));
		center_panel.setBackground(new Color(255,250,245));
		
		content_panel.add(button_panel,BorderLayout.SOUTH);
		content_panel.setBorder(new EmptyBorder(0,10,0,0));
		
		center_panel.add(content_panel,BorderLayout.CENTER);
		
		main_pJPanel.add(center_panel,BorderLayout.CENTER);
		
		display_DV.add(main_pJPanel,BorderLayout.CENTER);
		
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(table)) {
			int row = table.getSelectedRow();
			DV_txt_MADV.setText(model.getValueAt(row, 0).toString());
			DV_txt_TENDV.setText(model.getValueAt(row, 1).toString());
			DV_txt_GIADV.setText(model.getValueAt(row, 2).toString());
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
			DV_txt_MADV.setText("");
			DV_txt_TENDV.setText("");
			DV_txt_GIADV.setText("");
			DV_txt_MADV.requestFocus();
			data = Default_Custom_UI.cast_data("DichVu_FULL");
			model.setDataVector(data, cols_name);
			table.setModel(model);		
		}
		if(e.getSource().equals(Then)) {
			Them_Sua_DichVu("Them");
		}
		if(e.getSource().equals(Xoa)) {
			if(table.getSelectedRow()==-1) {
				JOptionPane.showMessageDialog(display_DV, "Không Có Dịch Vụ Nào Được Chọn !");
				return;
			}
			else {
				//pending dich_vu_co_dinh_toi_hoa_don
				
				DanhSachDichVu ds = new DanhSachDichVu();
				
				int choose = JOptionPane.showConfirmDialog(display_DV, "Bạn Có Chắc Muốn Xóa Dịch Vụ Này Chứ ?","Chú Ý",JOptionPane.YES_NO_OPTION);
				if(choose == JOptionPane.YES_OPTION) {
					ds.removeDV(model.getValueAt(table.getSelectedRow(), 0).toString());
					data = Default_Custom_UI.cast_data("DichVu_FULL");
					model.setDataVector(data, cols_name);
					table.setModel(model);
				}
			}
		}
		if(e.getSource().equals(Sua)) {
			Them_Sua_DichVu("Sua");
		}
		if(e.getSource().equals(Tim)) {
			Tim_DichVu();
		}
	 }


	private boolean Tim_DichVu() {
		// TODO Auto-generated method stub
		if(DV_txt_MADV.getText().equals("")) {
			JOptionPane.showMessageDialog(display_DV, "Mã Dịch Vụ Không Được Để Trống !");
			DV_txt_MADV.requestFocus();
			return false;
		}
		if(!DV_txt_MADV.getText().matches("DV[0-9]+")) {
			JOptionPane.showMessageDialog(display_DV, "Mã Dịch Vụ Phải Bắt Đầu Từ DV Theo Sau Là Các Kí Số !");
			DV_txt_MADV.setText("");
			DV_txt_MADV.requestFocus();
			return false;
		}
		DanhSachDichVu ds = new DanhSachDichVu();
		ds.docDuLieu();
		
		if(ds.getDichVuByMa(DV_txt_MADV.getText())==null) {
			JOptionPane.showMessageDialog(display_DV, "Không Tìm Thấy Dịch Vụ !");
			DV_txt_MADV.setText("");
			DV_txt_MADV.requestFocus();
			return false;
		}
		
		DichVu dichVu = ds.getDichVuByMa(DV_txt_MADV.getText());
		
		data = new String[1][3];
		data[0][0] = dichVu.getMaDV();
		data[0][1] = dichVu.getTenDV();
		data[0][2] = Float.toString(dichVu.getGia()).substring(0, Float.toString(dichVu.getGia()).length()-2);
		
		model.setDataVector(data, cols_name);
		table.setModel(model);
		
		return true;
	}


	private boolean Them_Sua_DichVu(String type) {
		// TODO Auto-generated method stub
		if(DV_txt_TENDV.getText().equals("")) {
			JOptionPane.showMessageDialog(display_DV, "Tên Dịch Vụ Không Được Trống !");
			DV_txt_TENDV.setText("");
			DV_txt_TENDV.requestFocus();
			return false;
		}
		
		if(DV_txt_GIADV.getText().equals("")) {
			JOptionPane.showMessageDialog(display_DV, "Giá Dịch Vụ Không Được Trống !");
			DV_txt_GIADV.setText("");
			DV_txt_GIADV.requestFocus();
			return false;
		}
		
		try {
			int gia = Integer.parseInt(DV_txt_GIADV.getText());
			DanhSachDichVu ds = new DanhSachDichVu();
			ds.docDuLieu();
			if(type.equals("Them")) {
				String last_maNV = ds.getDichVu().get(ds.getDichVu().size()-1).getMaDV();
				int last_number = Integer.parseInt(last_maNV.substring(2, last_maNV.length())) + 1;
				
				DichVu dv = new DichVu("DV"+(last_number), DV_txt_TENDV.getText(),gia);
				ds.insertDichVu(dv);
				data = Default_Custom_UI.cast_data("DichVu_FULL");
				model.setDataVector(data, cols_name);
				table.setModel(model);
				return true;
			}
			if(type.equals("Sua")) {
				
				if(table.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(display_DV, "Không Có Dịch Vụ Nào Được Chọn !");
					DV_txt_GIADV.setText("");
					DV_txt_GIADV.requestFocus();
					return false;
				}
				if(ds.getDichVuByMa(DV_txt_MADV.getText())==null) {
					JOptionPane.showMessageDialog(display_DV, "Mã Dịch Vụ Không Được Sửa !");
					DV_txt_GIADV.setText(model.getValueAt(table.getSelectedRow(), 0).toString());
					DV_txt_GIADV.requestFocus();
					return false;
				}
				DichVu dv = new DichVu(DV_txt_MADV.getText(), DV_txt_TENDV.getText(), gia);
				ds.updateDV(dv);
				data = Default_Custom_UI.cast_data("DichVu_FULL");
				model.setDataVector(data, cols_name);
				table.setModel(model);
				return true;
			}
		} catch (Exception e2) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(display_DV, "Giá Dịch Vụ Phải Là Số !");
			DV_txt_GIADV.setText("");
			DV_txt_GIADV.requestFocus();
			return false;
		}
		return true;
	}
}


