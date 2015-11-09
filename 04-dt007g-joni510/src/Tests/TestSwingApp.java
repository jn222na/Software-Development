package Tests;


import static org.junit.Assert.*;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.WindowEvent;
import java.io.Console;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.junit.*;
import org.junit.internal.runners.statements.ExpectException;
import org.junit.rules.ExpectedException;

import View.MainView;

public class TestSwingApp {

	@Before
	public void testIfMinSizeSet(){ 
		MainView mv = new MainView();
		JFrame frame = mv.drawWindow();
		assertTrue(frame.isMinimumSizeSet());
		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
	}
//	@Test
//	public void testFrameResizeXYZ() {
//		System.out.println("Initializing test, dont move your mouse");
//		System.out.println("Testing resize");
//		
//		try {
//			int mousePos =0;
//			MainView mv = new MainView();
//			JFrame frame = mv.drawWindow();
//			mv.drawWindow();
//
//			Robot bot = new Robot();
//			for (int i = 0; i < 5; i++) {
//				System.out.print(".");
//				bot.delay(1000);
//			}
//			for (int i = 0; i <= 100; i+=10) {
//				bot.mouseMove((int) (frame.getLocation().getX()-i),(int)frame.getLocation().getY()+i);
//				bot.mousePress(InputEvent.BUTTON1_MASK);
//				bot.delay(50);
//				mousePos = (int)frame.getLocation().getX()-i;
//			}
//			bot.mouseRelease(InputEvent.BUTTON1_MASK);
//			
//			
//			assertEquals(50, mousePos, 0);
//			
//			for (int i = 0; i <= 50; i+=10) {
//				bot.mouseMove((int)frame.getLocation().getX() , (int)frame.getLocation().getY()-i);
//				bot.mousePress(InputEvent.BUTTON1_MASK);
//				bot.delay(50);
//				mousePos = (int)frame.getLocation().getY()-i;
//			}
//			assertEquals(50, mousePos, 0);
//			bot.mouseRelease(InputEvent.BUTTON1_MASK);
//			for (int i = 0; i <= 100; i+=10) {
//				bot.mouseMove((int)frame.getLocation().getX()+i , (int)frame.getLocation().getY());
//				bot.mousePress(InputEvent.BUTTON1_MASK);
//				bot.delay(50);
//				mousePos = (int)frame.getLocation().getX()+i;
//			}
//			assertEquals(800, mousePos, 0);
//			bot.mouseRelease(InputEvent.BUTTON1_MASK);
//			
//			
//			bot.mouseRelease(InputEvent.BUTTON1_MASK);
//			for (int i = 0; i <= 50; i+=10) {
//				bot.mouseMove((int)frame.getLocation().getX()-i , (int)frame.getLocation().getY()-i);
//				bot.mousePress(InputEvent.BUTTON1_MASK);
//				bot.delay(50);
//				mousePos = (int)frame.getLocation().getY()+i;
//			}
//			assertEquals(50, mousePos, 0);
//			bot.mouseRelease(InputEvent.BUTTON1_MASK);
//			frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
//		} catch (AWTException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	try {
//		//Testing exit button position
//		MainView mv = new MainView();
//		mv.drawWindow();
//		
//		Robot bot = new Robot();
//		bot.mouseMove(1260, 270);
//		bot.delay(1000);
//		bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
//		bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
//		
//	} catch (AWTException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	}

	
	@Test
	public void testIfFrameOpens() {
		
			MainView mv = new MainView();
			mv.drawWindow();
//			mv.close();
	}
	
//	@Test
//	public void testSystemButtons(){
//		//Minimise
//		//Fullscreen / !Fullscreen
//		//Exit
//	}
//	@Test
//	public void testTitleBar(){
//		//Move
//		//Minimise
//		//Close
//	}
	@Test
	public void testComboBox(){
		MainView mv = new MainView();
		mv.drawWindow();
		JComboBox<String> box = mv.jcombo();
		try {
			//Freehand tool
			
			Robot bot = new Robot();
			bot.mouseMove(1260, 350);
			
			bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			bot.delay(1000);
			bot.mouseMove(1260, 400);
			bot.delay(1000);
			bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			bot.delay(1000);
			assertTrue(box.getSelectedItem() == "Frihand");
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			//Rectangular tool
			Robot bot = new Robot();
			bot.mouseMove(1260, 350);
			
			bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			bot.delay(1000);
			bot.mouseMove(1260, 390);
			bot.delay(1000);
			bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			bot.delay(1000);
			assertTrue(box.getSelectedItem() == "Rektangel");
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

//	@Test
//	public void testColorChoice(){
//		//Colorchoice update
//	}
	


}
