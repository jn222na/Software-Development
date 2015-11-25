package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import org.omg.CORBA.Bounds;

import Controller.Main;

public class MainView implements ActionListener, MenuListener {

	JFrame frame = new JFrame("Ritprogram");
	JComboBox<String> box = new JComboBox<String>();
	ArrayList<JPanel> list = new ArrayList<JPanel>();
	JPanel chosenColorPanel = new JPanel();
	JLabel jlabelCoords = new JLabel();
	JPanel mainPanel = new JPanel();
	JPanel jpCenter = null;
	Freehand freehand = new Freehand();
	Rectangle coloredRectangle = new Rectangle();
	JMenuItem stepBack = null;
	ArrayList<Boolean> wasThisTheLastOne = new ArrayList<Boolean>();
	String whichActionWasPerformed = "";
	
	int minSizeX = 600, minSizeY = 600;
	
	public boolean drawWindow(int x, int y) {
		if(x >= minSizeX && y >= minSizeY){
			frame.setVisible(true);
			frame.setMinimumSize(new Dimension(x, y));
			//Position in the middle
			frame.setLocationRelativeTo(null);
			frame.setLayout(new BorderLayout());
	
			frame.revalidate();
			return true;
		}else{
			return false;
		}
	
	}
	public JFrame buildWindow(JPanel jpBottom, JPanel mainPanel){
		
		frame.add(mainPanel, BorderLayout.PAGE_START);
		frame.add(jpBottom, BorderLayout.PAGE_END);
		// DISPOSE_ON_CLOSE
		// If this does not exists the process will stay active.
		frame.setDefaultCloseOperation(2);
		jpCenter = new JPanel();

		jpCenter.setSize(new Dimension(frame.getX(), frame.getY() - mainPanel.getY()));
		jpCenter.setLayout(new BorderLayout());
		frame.add(jpCenter, BorderLayout.CENTER);
		jpCenter.add(new CustomMouseMotionListener(), BorderLayout.CENTER);

		
		frame.revalidate();
		return frame;
	}
//This area is for testing the app
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

	public ArrayList<Rectangle> getRectangles() {
		return rectangles;
	}

	public ArrayList<Freehand> getFreehands() {
		return freehands;
	}
	public String getwhichActionWasPerformed(){
		return whichActionWasPerformed;
	}
	//------------------------------------	
	public void buildMenu() {
		JMenuBar bar = new JMenuBar();
		JMenu menu = new JMenu("Arkiv");
		menu.addMenuListener(this);
		bar.add(menu);
		JMenuItem item = new JMenuItem("Nytt");
		stepBack = new JMenuItem("Ångra");
		JMenuItem exit = new JMenuItem("Stäng av"); 
		item.addActionListener(this);
		stepBack.addActionListener(this);
		exit.addActionListener(this);
		menu.add(item);
		menu.add(stepBack);
		menu.addSeparator();
		menu.add(exit);
		frame.setJMenuBar(bar);

	}

	
	@Override
	public void actionPerformed(ActionEvent ae) {
		switch (ae.getActionCommand()) {
		
		case "Nytt":
			jlabelCoords.setText("Nytt vald");
			clearLists();
			redraw();
			whichActionWasPerformed = "Nytt";
			break;
		case "Ångra":

			jlabelCoords.setText("Ångrade");
			removeLastAdded();
			whichActionWasPerformed = "Ångra";
			break;
		case "Stäng av":
			jlabelCoords.setText("Avslutar");
			whichActionWasPerformed = "Stäng av";
			close();
			break;
		}
	}

	public JPanel buildTopGrid() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(1, 6));
		mainPanel.setPreferredSize(new Dimension(100, 50));
		mainPanel.setVisible(true);
		ArrayList<Color> colors = new ArrayList<Color>();
		colors.add(Color.red);
		colors.add(Color.green);
		colors.add(Color.blue);
		colors.add(Color.yellow);
		colors.add(Color.black);
		
		for (int i = 0; i < colors.size(); i++) {
			JPanel jPanel =new JPanel();
			jPanel.setPreferredSize(new Dimension(100, 100));
			
			jPanel.setBackground(colors.get(i));
			jPanel.setOpaque(true);
			list.add(jPanel);
		}
		box.setVisible(true);
		box.addItem("Rektangel");
		box.addItem("Frihand");

		for (JPanel jPanel : list) {
			mainPanel.add(jPanel, BorderLayout.PAGE_START);
		}

		mainPanel.add(box);
		return mainPanel;
	}

	public JPanel buildBottom(Color bgColor, Color chosenColorPanelColor) {

		JPanel jpBottom = new JPanel();
		jpBottom.setPreferredSize(new Dimension(frame.getWidth(), 30));
		jpBottom.setBackground(Color.lightGray);
		jpBottom.setLayout(new BorderLayout());

		JPanel chosenColorPanelContainer = new JPanel();
		chosenColorPanelContainer.setLayout(new BorderLayout());
		chosenColorPanelContainer.setBackground(bgColor);

		chosenColorPanel.setLayout(new BorderLayout());
		chosenColorPanel.setBackground(chosenColorPanelColor);
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
	ArrayList<Rectangle> rectangles = new ArrayList<>();
	ArrayList<Freehand> freehands = new ArrayList<>();

	private int boxSelected() {
		return box.getSelectedIndex();
	}

	class CustomMouseMotionListener extends JComponent {

		public CustomMouseMotionListener() {

			this.addMouseListener(new MouseAdapter() {

				public void mousePressed(MouseEvent e) {
					startDrag = new Point(e.getX(), e.getY());
					endDrag = startDrag;
					repaint();

				}

				public void mouseReleased(MouseEvent e) {
					if (boxSelected() == 0) {
						Shape rectangleShape = coloredRectangle.makeRectangle(startDrag.x, startDrag.y, e.getX(),
								e.getY());
						Rectangle coloredRectangle = new Rectangle(chosenColorPanel.getBackground(), rectangleShape);
						rectangles.add(coloredRectangle);
						wasThisTheLastOne.add(false);
					}
					if (boxSelected() == 1) {
						Shape freehandShape = freehand.makeLine(startDrag.x, startDrag.y, e.getX(), e.getY());
						Freehand freehand = new Freehand(chosenColorPanel.getBackground(), freehandShape);
						freehands.add(freehand);
						wasThisTheLastOne.add(true);
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

			for (Rectangle s : rectangles) {
				g2.setPaint(s.getColor());
				g2.draw(s.shape);
			}
			for (Freehand freehand : freehands) {
				g2.setPaint(freehand.getColor());
				g2.draw(freehand.getShape());
			}
			if (boxSelected() == 0) {
				if (startDrag != null && endDrag != null) {
					coloredRectangle.draw(g2, startDrag.x, startDrag.y, endDrag.x, endDrag.y);
				}
			}
			if (boxSelected() == 1) {
				if (startDrag != null && endDrag != null) {
					freehand.draw(g2, startDrag.x, startDrag.y, endDrag.x, endDrag.y);
				}
			}
		}
	}

	public void close() {
		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
	}

	public void clearLists() {
		freehands.clear();
		rectangles.clear();
		wasThisTheLastOne.clear();
	}

	public void redraw() {
		jpCenter.repaint();
	}

	public void removeLastAdded() {

		if (wasThisTheLastOne.get(wasThisTheLastOne.size() - 1) == false) {

			rectangles.remove(rectangles.size() - 1);

		}
		if (wasThisTheLastOne.get(wasThisTheLastOne.size() - 1) == true) {

			freehands.remove(freehands.size() - 1);
		}
		wasThisTheLastOne.remove(wasThisTheLastOne.size() - 1);
		redraw();
	}

	@Override
	public void menuCanceled(MenuEvent e) {
	}

	@Override
	public void menuDeselected(MenuEvent e) {
	}

	@Override
	public void menuSelected(MenuEvent e) {
		if (wasThisTheLastOne.isEmpty()) {
			stepBack.setEnabled(false);
		} else {
			stepBack.setEnabled(true);
		}
	}
}
