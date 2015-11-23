package Tests;


import static org.junit.Assert.*;
import java.awt.AWTException;
import java.awt.Button;
import java.awt.Color;
import java.awt.Robot;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.junit.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import View.Freehand;
import View.MainView;

public class TestSwingApp {

	@Before
	public void testIfMinSizeSet(){ 
		MainView mv = new MainView();
		JFrame frame = mv.drawWindow();
		assertTrue(frame.isMinimumSizeSet());
		dispatch(frame);
	}


	
//	@Test
//	public void testIfFrameOpens() {
//		
//			MainView mv = new MainView();
//			mv.drawWindow();
//			mv.close();
//	}
//	
//	@Test
//	public void testComboBox() throws AWTException{
//		MainView mv = new MainView();
//		mv.drawWindow();
//		JComboBox<String> box = mv.jcombo();
//
//			//Freehand tool
//			
//			Robot bot = new Robot();
//			bot.mouseMove(1200, 310);
//			
//			bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
//			bot.delay(250);
//			bot.mouseMove(1200, 350);
//			bot.delay(250);
//			bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
//			bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
//			bot.delay(250);
//			assertTrue(box.getSelectedItem().equals("Frihand"));
//
//
//			//Rectangular tool
//			bot.mouseMove(1200, 310);
//			
//			bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
//			bot.delay(250);
//			bot.mouseMove(1200, 330);
//			bot.delay(250);
//			bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
//			bot.delay(250);
//			//Stubb
//			assertTrue(box.getSelectedItem().equals("Rektangel"));
//	}
//	
//	/**
//	 * Tests that the colors we are printing out is the same as we are picking.
//	 * @throws AWTException 
//	 */
//	@Test
//	public void testColorChoice() throws AWTException{
//		
//		MainView mv = new MainView();
//		ArrayList<JPanel> list = mv.panels();
//		JPanel chosenColorPanel = mv.getChosenColorPanel();
//		
//		
//		int botWidth = 750;
//		int botHeight = 300;
//
//		mv.drawWindow();
//		
//		
//			Robot bot = new Robot();
//			assertFalse(list.isEmpty());
//			
//			for (int i = 0; i < list.size(); i++) {
//				bot.mouseMove(botWidth, botHeight);
//				bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
//				bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
//				bot.delay(250);
//				//Stubb
//				assertTrue(chosenColorPanel.getBackground().equals(list.get(i).getBackground()));
//
//				botWidth +=100;
//			}
//			
//	}
//
//	@Test
//	public void testChoordsAndDraw() throws AWTException{
//		    
//			MainView mv = new MainView();
//			mv.drawWindow();
//			JPanel frame = mv.getCenterPanel();
//			JLabel label = mv.getJLabelCoords();
//			
//			int screenLocY = (int) frame.getLocationOnScreen().getY();
//		    int screenLocX = (int) frame.getLocationOnScreen().getX()+100;
//
//			
//		    Robot bot = new Robot();
//			Random random = new Random();
//			int randomX = 0;
//			int randomY = 0;
//			
//			for (int i = 50; i < 350; i+=4) {	
//				 randomX = screenLocX+random.nextInt(i);
//				 randomY = screenLocY+i;
//				bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
//				bot.mouseMove(randomX, randomY);
//				bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
//				bot.delay(20);
//				assertTrue(label.getText() != "Koordinater : ");
//			}		
//			//Test drawing rectangle
//			bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
//			bot.mouseMove(screenLocX, screenLocY);
//			bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
//			bot.delay(500);
//
//			//Test drawing freehand
//			bot.mouseMove(1200, 310);
//			bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
//			bot.delay(250);
//			bot.mouseMove(1200, 350);
//			bot.delay(250);
//			bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
//			
//			int botWidth = 750;
//			int botHeight = 300;
//			bot.mouseMove(botWidth, botHeight);
//			bot.delay(250);
//			bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
//			bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
//			
//			for (int i = 50; i < 350; i+=4) {	
//				 randomX = screenLocX+125+random.nextInt(i);
//				 randomY = screenLocY+i;
//				bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
//				bot.mouseMove(randomX, randomY);
//				bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
//				bot.delay(20);
//				assertTrue(label.getText() != "Koordinater : ");
//			}		
//	}	
//	@Test
//	public void testFakeObject(){
//		MainView mv = new MainView();
//		JFrame frame = mv.drawWindow();
//		ArrayList<View.Rectangle> rectangles = new ArrayList<>();
//		ArrayList<View.Freehand> freehands = new ArrayList<>();
//		Freehand freehand = new Freehand();
//		//New
//		System.out.println("Is rectanglelist empty?");
//		assertTrue(rectangles.isEmpty());
//		//Step back
//		
//		//Making fake object and adding to fake list.(Mock)
//		Shape freehandShape = freehand.makeLine(1000, 450, 50, 50);
//		freehand = new Freehand(Color.red, freehandShape);
//		freehands.add(freehand);
//		int before = rectangles.size();
//		assertTrue(freehands.size() == 1);
//		freehands.remove(0);
//		int after = rectangles.size();
//		assertTrue(before == after);
//		assertTrue(freehands.isEmpty());
//		
//		dispatch(frame);
//	}
	@Test
	public void testTitleBar() throws AWTException{
		
		MainView m = mock(MainView.class);
		MainView mv = new MainView();
		mv.drawWindow();
		
		Robot bot = new Robot();
		bot.mouseMove(700, 260);
		bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		bot.delay(1000);
		bot.mouseMove(700, 280);
		bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		bot.delay(1000);
		
		when(mv.getwhichActionWasPerformed().equals("Nytt"));
		verify(mv,times(1)).getwhichActionWasPerformed();
//		when(mockView.actionPerformed(ae) == 2);
		
	}

	private void dispatch(JFrame frame){
		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
	}
}	
