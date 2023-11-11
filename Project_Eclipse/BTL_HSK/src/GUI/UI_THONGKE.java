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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class UI_THONGKE implements MouseListener,ActionListener{
	
	//--------DatPhong-----------//
	public JPanel display_ThongKe;
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
	
	public UI_THONGKE() {
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
		
		
		display_ThongKe = new JPanel();
		display_ThongKe = new JPanel();
		display_ThongKe.setLayout(new BorderLayout());
		
		JPanel titleJPanel = new JPanel();
		
		titleJPanel.setBorder(new CompoundBorder(new LineBorder(Color.LIGHT_GRAY, 3),new EmptyBorder(10,10,10,10)));
		
		JLabel title = new JLabel("THỐNG KÊ KHÁCH SẠN");
		
		JPanel center_panel = new JPanel();
		title.setFont(Default_Custom_UI.big_title_font);
		titleJPanel.add(title);
		title.setBorder(new EmptyBorder(5,5,5,5));
		display_ThongKe.add(titleJPanel,BorderLayout.NORTH);
		
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
		
		content_panel.add(jp,BorderLayout.CENTER);
		
		content_panel.setBorder(new EmptyBorder(0,10,0,0));
		
		center_panel.add(content_panel,BorderLayout.CENTER);
		
		main_pJPanel.add(center_panel,BorderLayout.CENTER);
		
		display_ThongKe.add(main_pJPanel,BorderLayout.CENTER);
		
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
		}
	}
}


