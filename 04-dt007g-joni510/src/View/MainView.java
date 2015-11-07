package View;

import java.awt.Dimension;

import javax.swing.*;

public class MainView {
	
	public MainView() {
		
	}
	public JFrame drawWindow(){
		JFrame frame = new JFrame("Ritprogram");
	    frame.setVisible(true);
	    frame.setSize(500, 500);
	    frame.setMinimumSize(new Dimension(500, 500));
	    frame.setBounds(700,250,500,500);
	    
	    return frame;
	}
	public void close() {
		// TODO Auto-generated method stub
		
	}
}
