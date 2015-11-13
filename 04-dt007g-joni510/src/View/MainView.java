package View;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;

public class MainView {

	JFrame frame = new JFrame("Ritprogram");
	JComboBox<String> box = new JComboBox<String>();
	ArrayList<JPanel> list = new ArrayList<JPanel>();
	JPanel chosenColorPanel = new JPanel();
	JLabel jlabelCoords = new JLabel();
	JPanel mainPanel = new JPanel();
	JPanel jpCenter;

	public JFrame drawWindow() {

		frame.setVisible(true);
		frame.setMinimumSize(new Dimension(600, 500));
		frame.setLocationRelativeTo(null);
		frame.setLayout(new BorderLayout());

		JPanel jpBottom = buildBottom();
		JPanel mainPanel = buildTopGrid();
		frame.add(mainPanel, BorderLayout.PAGE_START);
		frame.add(jpBottom, BorderLayout.PAGE_END);

		jpCenter = new JPanel();

		jpCenter.setSize(new Dimension(frame.getX(), frame.getY() - mainPanel.getY()));
		jpCenter.setLayout(new BorderLayout());
		frame.add(jpCenter, BorderLayout.CENTER);
		jpCenter.add(new CustomMouseMotionListener(), BorderLayout .CENTER);

		addListeners();
		frame.revalidate();
		return frame;
	}

	public JComboBox<String> jcombo() {
		return this.box;
	}

	public ArrayList<JPanel> panels() {
		return list;
	}

	public JPanel getChosenColorPanel() {
		return chosenColorPanel;
	}

	public JPanel getCenterPanel() {
		return jpCenter;
	}

	public JLabel getJLabelCoords() {
		return jlabelCoords;
	}

	private JPanel buildTopGrid() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(1, 6));
		mainPanel.setPreferredSize(new Dimension(100, 50));
		mainPanel.setVisible(true);

		JPanel jp = new JPanel();
		jp.setPreferredSize(new Dimension(100, 100));
		jp.setBackground(Color.red);
		jp.setOpaque(true);
		list.add(jp);

		JPanel jp2 = new JPanel();
		jp2.setPreferredSize(new Dimension(100, 100));
		jp2.setBackground(Color.green);
		jp2.setOpaque(true);
		list.add(jp2);

		JPanel jp3 = new JPanel();
		jp3.setPreferredSize(new Dimension(100, 100));
		jp3.setBackground(Color.blue);
		jp3.setOpaque(true);
		list.add(jp3);

		JPanel jp4 = new JPanel();
		jp4.setPreferredSize(new Dimension(100, 100));
		jp4.setBackground(Color.yellow);
		jp4.setOpaque(true);
		list.add(jp4);

		JPanel jp5 = new JPanel();
		jp5.setPreferredSize(new Dimension(100, 100));
		jp5.setBackground(Color.black);
		jp5.setOpaque(true);
		list.add(jp5);

		box.setVisible(true);
		box.addItem("Rektangel");
		box.addItem("Frihand");

		for (JPanel jPanel : list) {
			mainPanel.add(jPanel, BorderLayout.PAGE_START);
		}

		mainPanel.add(box);
		return mainPanel;
	}

	private JPanel buildBottom() {

		JPanel jpBottom = new JPanel();
		jpBottom.setPreferredSize(new Dimension(frame.getWidth(), 30));
		jpBottom.setBackground(Color.lightGray);
		jpBottom.setLayout(new BorderLayout());

		JPanel chosenColorPanelContainer = new JPanel();
		chosenColorPanelContainer.setLayout(new BorderLayout());
		chosenColorPanelContainer.setBackground(Color.lightGray);

		chosenColorPanel.setLayout(new BorderLayout());
		chosenColorPanel.setBackground(Color.black);
		chosenColorPanel.setPreferredSize(new Dimension(50, 0));
		jpBottom.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		jlabelCoords = new JLabel("Koordinater : ");
		JLabel chosenColorText = new JLabel("Färgval : ");

		jpBottom.add(jlabelCoords, BorderLayout.LINE_START);
		chosenColorPanelContainer.add(chosenColorText, BorderLayout.CENTER);
		chosenColorPanelContainer.add(chosenColorPanel, BorderLayout.LINE_END);
		jpBottom.add(chosenColorPanelContainer, BorderLayout.EAST);

		return jpBottom;
	}

	public void addListeners() {
		for (JPanel jPanel : list) {
			jPanel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					chosenColorPanel.setBackground(jPanel.getBackground());
				}
			});

		}
	}

	int startx, starty;
	Point endDrag, startDrag;
	ArrayList<ColoredRectangle> rectangles = new ArrayList<>();
	ArrayList<Freehand> freehands = new ArrayList<>();
	
	private int boxSelected(){
		return box.getSelectedIndex();
	}
	class CustomMouseMotionListener extends JComponent {

		public CustomMouseMotionListener() {
			
	          this.addMouseListener(new MouseAdapter() {
	        	  
	            public void mousePressed(MouseEvent e) {
//	            	if(boxSelected() == 0){	
		              startDrag = new Point(e.getX(), e.getY());
		              endDrag = startDrag;
		              repaint();
//	            	}
	            	
	            }
	            public void mouseReleased(MouseEvent e) {
	            if(boxSelected() == 0){	
	              Shape r = makeRectangle(startDrag.x, startDrag.y, e.getX(), e.getY());
	              ColoredRectangle coloredRectangle = new ColoredRectangle(chosenColorPanel.getBackground(), r);
	              
	              rectangles.add(coloredRectangle);
	              
	            } 
	            if(boxSelected() == 1){
	            	Shape f = makeLine(startDrag.x, startDrag.y, e.getX(), e.getY());
	    			  Freehand freehand = new Freehand(chosenColorPanel.getBackground(),f);
	    			  freehands.add(freehand);
	            }
	              startDrag = null;
	              endDrag = null;
	              repaint();
	            
	          }
	       });
	          
	          this.addMouseMotionListener(new MouseMotionAdapter() {
	        	  public void mouseMoved(MouseEvent e) {
			  			jlabelCoords.setText("Koordinater : " + e.getX() + " " + e.getY());
			  		}
	            public void mouseDragged(MouseEvent e) {
	            	
						endDrag = new Point(e.getX(), e.getY());
						repaint();
					
	            }
	          });
	        }
		
		public void paint(Graphics g) {
				Graphics2D g2 = (Graphics2D) g;
				
				
				for (ColoredRectangle s : rectangles) {
					g2.setPaint(s.getColor());
					g2.draw(s.shape);
				}
				for (Freehand freehand : freehands) {
					g2.setPaint(freehand.getColor());
					g2.draw(freehand.getShape());
					
				}
				if(boxSelected() == 0){
					if (startDrag != null && endDrag != null) {
						g2.setPaint(Color.LIGHT_GRAY);
						Shape r = makeRectangle(startDrag.x, startDrag.y, endDrag.x, endDrag.y);
						g2.draw(r);
					}
				}
				if(boxSelected() == 1){
					if (startDrag != null && endDrag != null) {
						g2.setPaint(Color.LIGHT_GRAY);
						Shape r = makeLine(startDrag.x, startDrag.y, endDrag.x, endDrag.y);
						g2.draw(r);
					}
				}
		}
				

		private Rectangle2D.Float makeRectangle(int x1, int y1, int x2, int y2) {
			return new Rectangle2D.Float(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
		}
		private Line2D.Float makeLine(int x, int x2, int y, int y2){
			return new Line2D.Float( x,  x2,  y,  y2);
		}
		
	}



	public void close() {
		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
	}
}
