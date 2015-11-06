package Tests;


import static org.junit.Assert.*;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

import javax.swing.JFrame;

import org.junit.*;

import View.MainView;

public class TestSwingApp {

	@Test
	public void testIfFrameOpens() {
		
			MainView mv = new MainView();
			mv.drawWindow();
			mv.close();
	}

	@Before
	public void testIfMinSizeSet(){ 
		MainView mv = new MainView();
		JFrame frame = mv.drawWindow();
		assertTrue(frame.isMinimumSizeSet());
	}



}
