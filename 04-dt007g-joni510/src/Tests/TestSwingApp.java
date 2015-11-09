package Tests;


import static org.junit.Assert.*;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.WindowEvent;
import javax.swing.JComboBox;
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
	public void testIfFrameOpens() {
		
			MainView mv = new MainView();
			mv.drawWindow();
//			mv.close();
	}
	
	@Test
	public void testSystemButtons(){
		//Minimise
		//Fullscreen / !Fullscreen
		//Exit
	}
	@Test
	public void testTitleBar(){
		//Move
		//Minimise
		//Close
	}
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
		}catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	


	


//	@Test
//	public void testSystemButtons(){
//		//Minimise
//		//Fullscreen / !Fullscreen
//		//Close
//	}
//	@Test
//	public void testTitleBar(){
//		//Move
//		//Minimise
//		//Close
//	}

}
