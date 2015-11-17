package Tests;


import static org.junit.Assert.*;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.junit.*;
import static org.mockito.Mockito.*;

import View.MainView;

public class TestSwingApp {

	@Before
	public void testIfMinSizeSet(){ 
		MainView mv = new MainView();
		JFrame frame = mv.drawWindow();
		assertTrue(frame.isMinimumSizeSet());
		dispatch(frame);
	}


	
	@Test
	public void testIfFrameOpens() {
		
			MainView mv = new MainView();
			mv.drawWindow();
			mv.close();
	}
	
	@Test
	public void testComboBox() throws AWTException{
		MainView mv = new MainView();
		mv.drawWindow();
		JComboBox<String> box = mv.jcombo();

			//Freehand tool
			
			Robot bot = new Robot();
			bot.mouseMove(1200, 310);
			
			bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			bot.delay(250);
			bot.mouseMove(1200, 380);
			bot.delay(250);
			bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			bot.delay(250);
			assertTrue(box.getSelectedItem().equals("Frihand"));


			//Rectangular tool
			bot.mouseMove(1200, 310);
			
			bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			bot.delay(250);
			bot.mouseMove(1200, 360);
			bot.delay(250);
			bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			bot.delay(250);
			assertTrue(box.getSelectedItem().equals("Rektangel"));
	}
	
	/**
	 * Tests that the colors we are printing out is the same as we are picking.
	 * @throws AWTException 
	 */
	@Test
	public void testColorChoice() throws AWTException{
		
		MainView mv = new MainView();
		
		ArrayList<JPanel> list = mv.panels();
		JPanel chosenColorPanel = mv.getChosenColorPanel();
		
		int botWidth = 750;
		int botHeight = 350;

		mv.drawWindow();
		
		
			Robot bot = new Robot();
			assertFalse(list.isEmpty());
			
			for (int i = 0; i < list.size(); i++) {
				bot.mouseMove(botWidth, botHeight);
				bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
				bot.delay(250);
				assertTrue(chosenColorPanel.getBackground().equals(list.get(i).getBackground()));
				 
				
				botWidth +=100;
			}
	
		//Colorchoice update
	}

	@Test
	public void testChoordsAndDraw() throws AWTException{
		    
			MainView mv = new MainView();

			JPanel p = mv.getChosenColorPanel();
			mv.drawWindow();
			JPanel frame = mv.getCenterPanel();
			JLabel label = mv.getJLabelCoords();
			
			int screenLocY = (int) frame.getLocationOnScreen().getY();
		    int screenLocX = (int) frame.getLocationOnScreen().getX()+100;

			
		    Robot bot = new Robot();
			Random random = new Random();
			int randomX = 0;
			int randomY = 0;
			
			for (int i = 50; i < 350; i+=4) {	
				 randomX = screenLocX+random.nextInt(i);
				 randomY = screenLocY+i;
				bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				bot.mouseMove(randomX, randomY);
				bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
				bot.delay(20);
				assertTrue(label.getText() != "Koordinater : ");
			}		
			//Test drawing rectangle
			bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			bot.mouseMove(screenLocX, screenLocY);
			bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			bot.delay(500);

			//Test drawing freehand
			bot.mouseMove(1200, 310);
			bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			bot.delay(250);
			bot.mouseMove(1200, 380);
			bot.delay(250);
			bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			
			int botWidth = 750;
			int botHeight = 350;
			bot.mouseMove(botWidth, botHeight);
			bot.delay(250);
			bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			
			for (int i = 50; i < 350; i+=4) {	
				 randomX = screenLocX+125+random.nextInt(i);
				 randomY = screenLocY+i;
				bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				bot.mouseMove(randomX, randomY);
				bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
				bot.delay(20);
				assertTrue(label.getText() != "Koordinater : ");
			}		
	}	
	
	@Test
	public void testTitleBar(){
		MainView mv = new MainView();
		JFrame frame = mv.drawWindow();
		ArrayList<View.Rectangle> rectangles = new ArrayList<>();
		ArrayList<View.Freehand> freehands = new ArrayList<>();

		//New
		mv.clearLists();
		assertTrue(rectangles.isEmpty());
		assertTrue(freehands.isEmpty());
		mv.redraw();
		//Step back
		int before = rectangles.size();
		mv.removeLastAdded();
		int after = rectangles.size();
		assertFalse(before == after);
		mv.redraw();
		dispatch(frame);
	}
	private void dispatch(JFrame frame){
		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
	}
}
