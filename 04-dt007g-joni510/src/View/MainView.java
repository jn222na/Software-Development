package View;

import java.awt.*;
import javax.swing.*;

public class MainView {

	public MainView() {

	}

	JFrame frame = new JFrame("Ritprogram");
	JComboBox<String> box = new JComboBox<String>();
	
	public JFrame drawWindow() {

		frame.setVisible(true);
		frame.setMinimumSize(new Dimension(600, 500));
		frame.setBounds(700, 250, 600, 500);
		frame.setLayout(new BorderLayout());

		JPanel mainPanel = buildTopGrid();
		frame.add(mainPanel, BorderLayout.PAGE_START);
		return frame;
	}
	public JComboBox<String> jcombo(){
		return this.box;
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
		jp4.setOpaque(true);

		
		

		mainPanel.add(jp, BorderLayout.PAGE_START);
		mainPanel.add(jp2, BorderLayout.PAGE_START);
		mainPanel.add(jp3, BorderLayout.PAGE_START);
		mainPanel.add(jp4, BorderLayout.PAGE_START);
		mainPanel.add(jp5, BorderLayout.PAGE_START);
		

		return mainPanel;
	}

	public void close() {
		// frame.dispatchEvent(new WindowEvent(frame,
		// WindowEvent.WINDOW_CLOSING));
	}
}
