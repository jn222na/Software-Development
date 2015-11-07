package View;

import java.awt.Dimension;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class MainView {
	
	public MainView() {
		
	}
	JFrame frame = new JFrame("Ritprogram");
	public JFrame drawWindow(){
		
	    frame.setVisible(true);
	    frame.setMinimumSize(new Dimension(500, 500));
	    frame.setBounds(700,250,500,500);

	    return frame;
	}
	
	public void close() {
//		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
	}
}
