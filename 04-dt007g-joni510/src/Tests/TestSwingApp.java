package Tests;


import static org.junit.Assert.*;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import org.junit.*;

import View.MainView;

public class TestSwingApp {

	@Before
	public void testIfMinSizeSet(){ 
		MainView mv = new MainView();
		JFrame frame = mv.drawWindow();
		assertTrue(frame.isMinimumSizeSet());
		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
	}
	@Test
	public void testFrameResizeX() {
		System.out.println("Initializing test, dont move your mouse");
		
		try {
			int mousePos =0;
			MainView mv = new MainView();
			JFrame frame = mv.drawWindow();
			mv.drawWindow();

			Robot bot = new Robot();
			for (int i = 0; i < 5; i++) {
				System.out.print(".");
				bot.delay(1000);
			}
			for (int i = 0; i <= 100; i+=10) {
				bot.mouseMove((int) (frame.getLocation().getX()-i),(int)frame.getLocation().getY()+i);
				bot.mousePress(InputEvent.BUTTON1_MASK);
				bot.delay(50);
				mousePos = (int)frame.getLocation().getX()-i;
			}
			bot.mouseRelease(InputEvent.BUTTON1_MASK);
			assertEquals(50, mousePos, 0);
			
			for (int i = 0; i <= 50; i+=10) {
				bot.mouseMove((int)frame.getLocation().getX() , (int)frame.getLocation().getY()-i);
				bot.mousePress(InputEvent.BUTTON1_MASK);
				bot.delay(50);
				mousePos = (int)frame.getLocation().getY()-i;
			}
			assertEquals(50, mousePos, 0);
			bot.mouseRelease(InputEvent.BUTTON1_MASK);
			for (int i = 0; i <= 100; i+=10) {
				bot.mouseMove((int)frame.getLocation().getX()+i , (int)frame.getLocation().getY());
				bot.mousePress(InputEvent.BUTTON1_MASK);
				bot.delay(50);
				mousePos = (int)frame.getLocation().getX()+i;
			}
			assertEquals(800, mousePos, 0);
			bot.mouseRelease(InputEvent.BUTTON1_MASK);
			
			
			bot.mouseRelease(InputEvent.BUTTON1_MASK);
			for (int i = 0; i <= 50; i+=10) {
				bot.mouseMove((int)frame.getLocation().getX()-i , (int)frame.getLocation().getY()-i);
				bot.mousePress(InputEvent.BUTTON1_MASK);
				bot.delay(50);
				mousePos = (int)frame.getLocation().getY()+i;
			}
			assertEquals(50, mousePos, 0);
			bot.mouseRelease(InputEvent.BUTTON1_MASK);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testIfFrameOpens() {
		
			MainView mv = new MainView();
			mv.drawWindow();
//			mv.close();
	}

	@Test
	public void testSystemButtons(){
		//Minimise
		//Fullscreen / !Fullscreen
		//Close
	}
	@Test
	public void testTitleBar(){
		//Move
		//Minimise
		//Close
	}
	



}
