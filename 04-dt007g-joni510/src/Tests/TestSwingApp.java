package Tests;


import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Shape;
import java.awt.event.InputEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.junit.Before;
import org.junit.Test;

import Controller.Main;
import Controller.Main;

import static org.mockito.Mockito.*;

import org.mockito.*;
import org.mockito.Mockito.*;
import org.omg.CORBA.Bounds;

import View.Freehand;
import View.MainView;

public class TestSwingApp {
	
	int x = 600;
	int y = 600;
	@Test
	public void testIfMinSizeSet() throws Bounds{ 
		MainView mv = new MainView();
		mv.drawWindow(x, y);
		mv.buildMenu();
		JPanel jpBottom = mv.buildBottom(Color.lightGray, Color.black);
		JPanel mainPanel = mv.buildTopGrid();
		JFrame frame = mv.buildWindow(jpBottom, mainPanel);
		assertTrue(frame.isMinimumSizeSet());
		dispatch(frame);
	}


	
	@Test
	public void testIfFrameOpens() {
		
		MainView mv = new MainView();
		mv.drawWindow(x, y);
		mv.close();
	}
	
	@Test
	public void testComboBox() throws AWTException{
		MainView mv = new MainView();
		mv = initiateWindow();
		JComboBox<String> box = mv.jcombo();

			//Freehand tool
			
			Robot bot = new Robot();
			bot.mouseMove(1200, 310);
			
			bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			bot.delay(250);
			bot.mouseMove(1200, 350);
			bot.delay(250);
			bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			bot.delay(250);
			assertTrue(box.getSelectedItem().equals("Frihand"));


			//Rectangular tool
			bot.mouseMove(1200, 310);
			
			bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			bot.delay(250);
			bot.mouseMove(1200, 330);
			bot.delay(250);
			bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			bot.delay(250);
			//Stubb
			assertTrue(box.getSelectedItem().equals("Rektangel"));
//	dispatch(frame);
	}
	
	/**
	 * Tests that the colors we are printing out is the same as we are picking.
	 * @throws AWTException 
	 */
	@Test
	public void testColorChoice() throws AWTException{
		MainView mv = new MainView();
		mv = initiateWindow();
		ArrayList<JPanel> list = mv.panels();
		JPanel chosenColorPanel = mv.getChosenColorPanel();
		
		int botWidth = 750;
		int botHeight = 300;


		System.out.println(list.size());
			Robot bot = new Robot();
			assertFalse(list.isEmpty());
			
			for (int i = 0; i < list.size(); i++) {
				bot.mouseMove(botWidth, botHeight);
				bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
				bot.delay(250);
				//Stubb
				assertTrue(chosenColorPanel.getBackground().equals(list.get(i).getBackground()));

				botWidth +=100;
			}
			
	}

	@Test
	public void testChoordsAndDraw() throws AWTException{
		    MainView mv = new MainView();
		    mv = initiateWindow();
			JPanel centerPanel = mv.getCenterPanel();
			JLabel label = mv.getJLabelCoords();
			
			int screenLocY = (int) centerPanel.getLocationOnScreen().getY();
		    int screenLocX = (int) centerPanel.getLocationOnScreen().getX()+100;

			
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
				bot.mouseMove(randomX+1, randomY);
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
			bot.mouseMove(1200, 350);
			bot.delay(250);
			bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			
			int botWidth = 750;
			int botHeight = 300;
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
	public void testFakeObject(){
		MainView mv = new MainView();
		mv = initiateWindow();
		ArrayList<View.Rectangle> rectangles = new ArrayList<>();
		ArrayList<View.Freehand> freehands = new ArrayList<>();
		Shape shape = null;
		Freehand freehand = new Freehand(Color.red,shape);

		//New
		System.out.println("Is rectanglelist empty?");
		assertTrue(rectangles.isEmpty());
		//Step back
		
		//Making fake object and adding to fake list.(Mock)
		Shape freehandShape = freehand.makeLine(1000, 450, 50, 50);
		freehand = new Freehand(Color.red, freehandShape);
		freehands.add(freehand);
		int before = rectangles.size();
		assertTrue(freehands.size() == 1);
		freehands.remove(0);
		int after = rectangles.size();
		assertTrue(before == after);
		assertTrue(freehands.isEmpty());
		
//		dispatch(frame);
	}
	@Test
	public void testTitleBar() throws AWTException{

		
		MainView mv = new MainView();
		mv.drawWindow(x, y);
		mv.buildMenu();
		JPanel jpBottom = mv.buildBottom(Color.lightGray, Color.black);
		JPanel mainPanel = mv.buildTopGrid();
		JFrame frame = mv.buildWindow(jpBottom, mainPanel);
		assertTrue(frame.isShowing());
		System.out.println("Drawing some random stuff");
		recTestMenu(0);
		dispatch(frame);
	}
	int i = 0;
	public void recTestMenu(int i) throws AWTException{
		
		if(i <= 50){
			Robot bot = new Robot();
			for (int j = 0; j < 80; j+=5) {
				bot.mouseMove(950, 500 +j);
				bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				bot.mouseMove(850+j, 600);
				bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
				bot.mouseMove(950+j, 500 );
				bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				bot.mouseMove(850, 600+j);
				bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			}
			
			bot.mouseMove(800, 260);
			bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			bot.mouseMove(700, 260);
			bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			bot.delay(250);
			bot.mouseMove(700, 280 + i);
			bot.delay(250);
			bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			bot.delay(250);
			
			i+=25;
			recTestMenu(i);
		}
	}
	private MainView initiateWindow(){
		MainView mv = new MainView();
		mv.drawWindow(x, y);
		mv.buildMenu();
		JPanel jpBottom = mv.buildBottom(Color.lightGray, Color.black);
		JPanel mainPanel = mv.buildTopGrid();
		JFrame frame = mv.buildWindow(jpBottom, mainPanel);
		mv.addListeners();
		assertTrue(frame.isShowing());
		return mv;
	}
	private void dispatch(JFrame frame){
		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
	}
}	
