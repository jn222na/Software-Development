package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

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

	public JFrame drawWindow() {

		frame.setVisible(true);
		frame.setMinimumSize(new Dimension(600, 600));
		frame.setLocationRelativeTo(null);
		frame.setLayout(new BorderLayout());

		JPanel jpBottom = buildBottom();
		JPanel mainPanel = buildTopGrid();

		buildMenu();
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

	public ArrayList<Rectangle> getRectangles() {
		return rectangles;
	}

	public ArrayList<Freehand> getFreehands() {
		return freehands;
	}

	private void buildMenu() {
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
			break;
		case "Ångra":

			jlabelCoords.setText("Ångrade");
			removeLastAdded();
			break;
		case "Stäng av":
			jlabelCoords.setText("Avslutar");
			close();
			break;
		}
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
