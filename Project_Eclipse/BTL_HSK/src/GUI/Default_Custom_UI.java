package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneLayout;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableCellRenderer;
import com.toedter.calendar.JDateChooser;
import Control.DanhSachNhanVien;
import Control.DanhSachPhong;

public class Default_Custom_UI {
	public static Font title_font = new Font("Arial",Font.BOLD, 15);
	public static Font big_title_font = new Font("Arial",Font.BOLD, 25);
	public static Font tag_font = new Font("Arial",Font.BOLD, 12);
	private static DanhSachPhong listPhong ;
	private static DanhSachNhanVien listNhanVien;
//	private static DanhSachPhieuDat list_phieuDat;
	//-----------------------------------DEFAULT-----------------------------------------//
	
		public static JScrollPane default_scrollpane(JPanel content) {
			JScrollPane jp = new ModernScrollPane(content);
			return jp;
		}
		public static JScrollPane default_scrollpane_fortable(JTable content) {
			JScrollPane jp = new ModernScrollPane(content);
			return jp;
		}
	
		public static JPanel default_RoomTag(String tag_name,int number,Color tag_color,Color fore_color) {
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
		public static JPanel default_ROOM() {
			JPanel default_Room = new JPanel();
			
			
			return default_Room;
		}
		public static JDateChooser defaultDateChooser() {
			JDateChooser datechoose = new JDateChooser();
			datechoose.setPreferredSize(new Dimension(220,35));
			datechoose.getDateEditor().getUiComponent().setBorder(new CompoundBorder(new LineBorder(Color.LIGHT_GRAY, 2),new EmptyBorder(5,5,5,5)));
			datechoose.getDateEditor().getUiComponent().setFont(title_font);
			return datechoose;
		}
		
		public static JLabel default_button(String title) {
			JLabel defaultButton = new JLabel(title);
		
			defaultButton.setOpaque(false);
			defaultButton.setForeground(Color.WHITE);
			defaultButton.setBorder(null);
			defaultButton.setFont(title_font);
			
			return defaultButton;
		}
		
		public static JButton default_Action_Button(String txt,String path) {
			ImageIcon icon = createImageIcon(path);
			icon = scaleImage(icon, 40, 40);
			
			JButton btn = new JButton(txt, icon);
			btn.setBackground(new Color(0,25,51));
			btn.setForeground(Color.WHITE);
			btn.setFont(title_font);
			btn.setBorder( new CompoundBorder(new LineBorder(Color.LIGHT_GRAY, 3),new EmptyBorder(5,15,5,15)));
			
			return btn;
		}
		
		public static JLabel default_label(String txt) {
			JLabel lb = new JLabel(txt);
			lb.setFont(title_font);
			lb.setBorder(new EmptyBorder(5, 0, 5, 0));
			return lb;
		}
		
		public static JPanel default_panel(String path,String title) {
			JPanel dJPanel = new JPanel();
			
			dJPanel.setLayout(new BorderLayout());
			ImageIcon icon = createImageIcon(path);
			icon = scaleImage(icon, 40, 40);
			dJPanel.add(new JLabel(icon),BorderLayout.WEST);
			dJPanel.add(default_button(title),BorderLayout.EAST);
			dJPanel.setOpaque(false);
			
			return dJPanel;
		}
		
		public static JPanel default_child_panel(String path,String title) {
			JPanel child = default_panel(path,title);
			child.setBorder(new CompoundBorder(new LineBorder(new Color(244,255,255),2), new EmptyBorder(5,5,5,10)));
			return child;
		}
		
		public static JTextField default_textfield() {
			JTextField txt = new JTextField(17);

//			txt.setMaximumSize(
//			    new Dimension(Integer.MAX_VALUE,
//			    txt.getPreferredSize().height));
			
			txt.setBorder(new CompoundBorder(new LineBorder(Color.LIGHT_GRAY, 2),new EmptyBorder(5,5,5,5)));
			txt.setFont(title_font);
			
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
		public static ImageIcon scaleImage(ImageIcon icon, int w, int h)
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
		//--------------------------------------add_Data-------------------------------------//
		public static JComboBox<String> add_data_ds_combo(String type){
			JComboBox<String> cb = new JComboBox<String>();
			
			if(type.equals("Phong")) {
				listPhong = new DanhSachPhong();
				listPhong.docDuLieu();
				for(int i=0;i<listPhong.getListPhong().size();i++) {
					cb.addItem(Integer.toString(listPhong.getListPhong().get(i).getSoPhong()));
				}
			}
			
			if(type.equals("NV")) {
				listNhanVien = new DanhSachNhanVien();
				listNhanVien.docDuLieu();
				for(int i=0;i<listNhanVien.get_listNV().size();i++) {
					cb.addItem(listNhanVien.get_listNV().get(i).getMaNV());
				}
			}
			cb.setPreferredSize(new Dimension(220,35));
			cb.setRenderer(new CustomComboBoxRenderer());
			cb.setFont(title_font);
			
			return cb;
		}
		//-----------------------------------------------------------------pending
//		public static Object[][] cast_data(String type){
//			if(type.equals("PhieuDat")) {
//				list_phieuDat = new DanhSachPhieuDat();
//			}
//			return null;
//		}
}
class ModernScrollPane extends JScrollPane {

    private static final long serialVersionUID = 8607734981506765935L;

    private static final int SCROLL_BAR_ALPHA_ROLLOVER = 100;
    private static final int SCROLL_BAR_ALPHA = 200;
    private static final int THUMB_SIZE = 8;
    private static final int SB_SIZE = 10;
    private static final Color THUMB_COLOR = new Color(0,25,51);

    public ModernScrollPane(Component view) {
        this(view, VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }

    public ModernScrollPane(int vsbPolicy, int hsbPolicy) {
        this(null, vsbPolicy, hsbPolicy);
    }

    public ModernScrollPane(Component view, int vsbPolicy, int hsbPolicy) {

        setBorder(null);

        // Set ScrollBar UI
        JScrollBar verticalScrollBar = getVerticalScrollBar();
        verticalScrollBar.setOpaque(false);
        verticalScrollBar.setUI(new ModernScrollBarUI(this));

        JScrollBar horizontalScrollBar = getHorizontalScrollBar();
        horizontalScrollBar.setOpaque(false);
        horizontalScrollBar.setUI(new ModernScrollBarUI(this));

        setLayout(new ScrollPaneLayout() {
            private static final long serialVersionUID = 5740408979909014146L;

            @Override
            public void layoutContainer(Container parent) {
                Rectangle availR = ((JScrollPane) parent).getBounds();
                availR.x = availR.y = 0;

                // viewport
                Insets insets = parent.getInsets();
                availR.x = insets.left;
                availR.y = insets.top;
                availR.width -= insets.left + insets.right;
                availR.height -= insets.top + insets.bottom;
                if (viewport != null) {
                    viewport.setBounds(availR);
                }

                boolean vsbNeeded = isVerticalScrollBarfNecessary();
                boolean hsbNeeded = isHorizontalScrollBarNecessary();

                // vertical scroll bar
                Rectangle vsbR = new Rectangle();
                vsbR.width = SB_SIZE;
                vsbR.height = availR.height - (hsbNeeded ? vsbR.width : 0);
                vsbR.x = availR.x + availR.width - vsbR.width;
                vsbR.y = availR.y;
                if (vsb != null) {
                    vsb.setBounds(vsbR);
                }

                // horizontal scroll bar
                Rectangle hsbR = new Rectangle();
                hsbR.height = SB_SIZE;
                hsbR.width = availR.width - (vsbNeeded ? hsbR.height : 0);
                hsbR.x = availR.x;
                hsbR.y = availR.y + availR.height - hsbR.height;
                if (hsb != null) {
                    hsb.setBounds(hsbR);
                }
            }
        });

        // Layering
        setComponentZOrder(getVerticalScrollBar(), 0);
        setComponentZOrder(getHorizontalScrollBar(), 1);
        setComponentZOrder(getViewport(), 2);

        viewport.setView(view);
    }
    private boolean isVerticalScrollBarfNecessary() {
        Rectangle viewRect = viewport.getViewRect();
        Dimension viewSize = viewport.getViewSize();
        return viewSize.getHeight() > viewRect.getHeight();
    }

    private boolean isHorizontalScrollBarNecessary() {
        Rectangle viewRect = viewport.getViewRect();
        Dimension viewSize = viewport.getViewSize();
        return viewSize.getWidth() > viewRect.getWidth();
    }

    /**
     * Class extending the BasicScrollBarUI and overrides all necessary methods
     */
    private static class ModernScrollBarUI extends BasicScrollBarUI {

        private JScrollPane sp;

        public ModernScrollBarUI(ModernScrollPane sp) {
            this.sp = sp;
        }

        @Override
        protected JButton createDecreaseButton(int orientation) {
            return new InvisibleScrollBarButton();
        }

        @Override
        protected JButton createIncreaseButton(int orientation) {
            return new InvisibleScrollBarButton();
        }

        @Override
        protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        }

        @Override
        protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
            int alpha = isThumbRollover() ? SCROLL_BAR_ALPHA_ROLLOVER : SCROLL_BAR_ALPHA;
            int orientation = scrollbar.getOrientation();
            int x = thumbBounds.x;
            int y = thumbBounds.y;

            int width = orientation == JScrollBar.VERTICAL ? THUMB_SIZE : thumbBounds.width;
            width = Math.max(width, THUMB_SIZE);

            int height = orientation == JScrollBar.VERTICAL ? thumbBounds.height : THUMB_SIZE;
            height = Math.max(height, THUMB_SIZE);

            Graphics2D graphics2D = (Graphics2D) g.create();
            graphics2D.setColor(new Color(THUMB_COLOR.getRed(), THUMB_COLOR.getGreen(), THUMB_COLOR.getBlue(), alpha));
            graphics2D.fillRect(x, y, width, height);
            graphics2D.dispose();
        }

        @Override
        protected void setThumbBounds(int x, int y, int width, int height) {
            super.setThumbBounds(x, y, width, height);
            sp.repaint();
        }

        /**
         * Invisible Buttons, to hide scroll bar buttons
         */
        private static class InvisibleScrollBarButton extends JButton {

            private static final long serialVersionUID = 1552427919226628689L;

            private InvisibleScrollBarButton() {
                setOpaque(false);
                setFocusable(false);
                setFocusPainted(false);
                setBorderPainted(false);
                setBorder(BorderFactory.createEmptyBorder());
            }
        }
    }
}

@SuppressWarnings("serial")
class CustomRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        if (row % 2 == 0) {
            cellComponent.setBackground(Color.WHITE);  
        } else {
            cellComponent.setBackground(new Color(224,224,224)); 
        }
        
        table.getColumnModel().getColumn(column).setPreferredWidth(90); 
        table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.getColumnModel().getColumn(3).setPreferredWidth(80);
        table.getColumnModel().getColumn(column).setCellRenderer(new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                if (row % 2 == 0) {
                    cellComponent.setBackground(Color.WHITE); 
                } else {
                    cellComponent.setBackground(new Color(224,224,224));
                }

                return cellComponent;
            }
        });
        
        return cellComponent;
    }
}

@SuppressWarnings("serial")
class CustomHeaderRenderer extends DefaultTableCellRenderer {
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        cellComponent.setBackground(new Color(0,25,51));  
        cellComponent.setForeground(Color.WHITE);
        cellComponent.setFont(new Font("Arial", Font.BOLD, 13));
        setHorizontalAlignment(CENTER);
        return cellComponent;
    }
}

