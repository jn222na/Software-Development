package View;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Array;
import java.util.ArrayList;

import javax.swing.*;

public class MainView {


	JFrame frame = new JFrame("Ritprogram");
	JComboBox<String> box = new JComboBox<String>();
	ArrayList<JPanel> list = new ArrayList<JPanel>();
	JPanel chosenColorPanel = new JPanel();
	
	
	public JFrame drawWindow() {

		frame.setVisible(true);
		frame.setMinimumSize(new Dimension(600, 500));
		frame.setBounds(700, 250, 600, 500);
		frame.setLayout(new BorderLayout());

		JPanel jpBottom = buildBottom();
		JPanel mainPanel = buildTopGrid();
		frame.add(mainPanel, BorderLayout.PAGE_START);
		frame.add(jpBottom, BorderLayout.PAGE_END);
		addListeners(jpBottom);
		return frame;
	}
	public JComboBox<String> jcombo(){
		return this.box;
	}
	
	public ArrayList<JPanel> panels(){
		return list;
	}
	public JPanel getChosenColorPanel(){
		return chosenColorPanel;
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
		// Needed for JComboBox to appear don't know why :/
		box.revalidate();

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

		
		chosenColorPanel.setLayout(new FlowLayout());
		chosenColorPanel.setBackground(Color.red);
		chosenColorPanel.setPreferredSize(new Dimension(50, 0));
		jpBottom.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		JLabel jlabelCoords = new JLabel("Koordinater : 250, 100");
		JLabel chosenColor = new JLabel("Färgval : ");
		jpBottom.add(jlabelCoords, BorderLayout.LINE_START);
		chosenColorPanelContainer.add(chosenColor, BorderLayout.CENTER);
		chosenColorPanelContainer.add(chosenColorPanel, BorderLayout.LINE_END);
		jpBottom.add(chosenColorPanelContainer, BorderLayout.EAST);
		return jpBottom;
	}
	
	private void addListeners(JPanel jpBottom){
		for (JPanel jPanel : list) {
			jPanel.addMouseListener(new MouseAdapter() {
				 @Override
				 public void mouseClicked(MouseEvent e) {
					 chosenColorPanel.setBackground(jPanel.getBackground());
				 }
		 });
	
		}
	}
	public void close() {
		// frame.dispatchEvent(new WindowEvent(frame,
		// WindowEvent.WINDOW_CLOSING));
	}
}
