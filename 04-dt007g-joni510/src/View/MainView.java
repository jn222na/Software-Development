package View;

import java.awt.*;

import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.*;

public class MainView {


	JFrame frame = new JFrame("Ritprogram");
	JComboBox<String> box = new JComboBox<String>();
	ArrayList<JPanel> list = new ArrayList<JPanel>();
	public JFrame drawWindow() {

		frame.setVisible(true);
		frame.setMinimumSize(new Dimension(600, 500));
		frame.setBounds(700, 250, 600, 500);
		frame.setLayout(new BorderLayout());

		JPanel jpBottom = buildBottom();
		JPanel mainPanel = buildTopGrid();
		frame.add(mainPanel, BorderLayout.PAGE_START);
		frame.add(jpBottom, BorderLayout.PAGE_END);
		return frame;
	}
	public JComboBox<String> jcombo(){
		return this.box;
	}
	
	public ArrayList<JPanel> panels(){
		return list;
	}
	private JPanel buildTopGrid() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(1, 6));
		mainPanel.setSize(new Dimension(frame.getWidth(), 0));
		mainPanel.setVisible(true);

		JPanel jp = new JPanel();
		jp.setPreferredSize(new Dimension(100, 50));
		jp.setBackground(Color.red);
		jp.setOpaque(true);
		
		JPanel jp2 = new JPanel();
		jp2.setPreferredSize(new Dimension(100, 100));
		jp2.setBackground(Color.green);
		jp2.setOpaque(true);
		
		
		JPanel jp3 = new JPanel();
		jp3.setPreferredSize(new Dimension(100, 100));
		jp3.setBackground(Color.blue);
		jp3.setOpaque(true);
		
		
		JPanel jp4 = new JPanel();
		jp4.setPreferredSize(new Dimension(100, 100));
		jp4.setBackground(Color.yellow);
		jp4.setOpaque(true);
		
		
		JPanel jp5 = new JPanel();
		jp5.setPreferredSize(new Dimension(100, 100));
		jp5.setBackground(Color.black);
		jp5.setOpaque(true);
		
		
		box.setVisible(true);
		box.addItem("Rektangel");
		box.addItem("Frihand");

		mainPanel.add(jp, BorderLayout.PAGE_START);
		mainPanel.add(jp2, BorderLayout.PAGE_START);
		mainPanel.add(jp3, BorderLayout.PAGE_START);
		mainPanel.add(jp4, BorderLayout.PAGE_START);
		mainPanel.add(jp5, BorderLayout.PAGE_START);
		mainPanel.add(box);
		// Needed for JComboBox to appear don't know why :/
		box.revalidate();

		return mainPanel;
	}
	private JPanel buildBottom() {
		JPanel jpBottom = new JPanel();
		jpBottom.setPreferredSize(new Dimension(frame.getWidth(), 30));
		jpBottom.setBackground(Color.lightGray);
		jpBottom.setLayout(new BorderLayout());

		JPanel chosenColorPanel = new JPanel();
		chosenColorPanel.setLayout(new BorderLayout());
		chosenColorPanel.setBackground(Color.lightGray);

		JPanel chosenColorPanel2 = new JPanel();
		chosenColorPanel2.setLayout(new FlowLayout());
		chosenColorPanel2.setBackground(Color.red);
		chosenColorPanel2.setPreferredSize(new Dimension(50, 0));
		jpBottom.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		JLabel jlabelCoords = new JLabel("Koordinater : 250, 100");
		JLabel chosenColor = new JLabel("Färgval : ");
		jpBottom.add(jlabelCoords, BorderLayout.LINE_START);
		chosenColorPanel.add(chosenColor, BorderLayout.CENTER);
		chosenColorPanel.add(chosenColorPanel2, BorderLayout.LINE_END);
		jpBottom.add(chosenColorPanel, BorderLayout.EAST);
		return jpBottom;
	}
	public void close() {
		// frame.dispatchEvent(new WindowEvent(frame,
		// WindowEvent.WINDOW_CLOSING));
	}
}
