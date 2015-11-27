package Tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.awt.AWTException;
import java.awt.Color;
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

import org.junit.Test;
import org.omg.CORBA.Bounds;

import Controller.Main;
import View.Freehand;
import View.MainView;

public class TestSwingApp {

	int x = 600;
	int y = 600;

	@Test
	public void testIfMinSizeSet() throws Bounds {
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
	public void testComboBox() throws AWTException {
		MainView mv = new MainView();
		mv = initiateWindow();
		JComboBox<String> box = mv.jcombo();

		// Freehand tool

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

		// Rectangular tool
		bot.mouseMove(1200, 310);

		bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		bot.delay(250);
		bot.mouseMove(1200, 330);
		bot.delay(250);
		bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		bot.delay(250);
		// Stubb
		assertTrue(box.getSelectedItem().equals("Rektangel"));
		// dispatch(frame);
	}

	/**
	 * Tests that the colors we are printing out is the same as we are picking.
	 * 
	 * @throws AWTException
	 */
	@Test
	public void testColorChoice() throws AWTException {
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
			// Stubb
			assertTrue(chosenColorPanel.getBackground().equals(list.get(i).getBackground()));

			botWidth += 100;
		}

	}

	@Test
	public void testChoordsAndDraw() throws AWTException {
		MainView mv = new MainView();
		mv = initiateWindow();

		JPanel centerPanel = mv.getCenterPanel();
		JLabel label = mv.getJLabelCoords();

		int screenLocY = (int) centerPanel.getLocationOnScreen().getY();
		int screenLocX = (int) centerPanel.getLocationOnScreen().getX() + 100;

		Robot bot = new Robot();
		Random random = new Random();
		int randomX = 0;
		int randomY = 0;

		for (int i = 50; i < 350; i += 4) {
			randomX = screenLocX + random.nextInt(i);
			randomY = screenLocY + i;
			bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			bot.mouseMove(randomX, randomY);
			bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			bot.mouseMove(randomX + 1, randomY);
			bot.delay(20);
			assertTrue(label.getText() != "Koordinater : ");
		}
		// Test drawing rectangle
		bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		bot.mouseMove(screenLocX, screenLocY);
		bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		bot.delay(500);

		// Test drawing freehand
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

		for (int i = 50; i < 350; i += 4) {
			randomX = screenLocX + 125 + random.nextInt(i);
			randomY = screenLocY + i;
			bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			bot.mouseMove(randomX, randomY);
			bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			bot.delay(20);
			assertTrue(label.getText() != "Koordinater : ");
		}
	}

	@Test(expected = Bounds.class)
	public void testMainException() throws Bounds {
		int x = 599;
		int y = 599;
		Main main = mock(Main.class);
		Main mainn = new Main();
		when(main.run(x, y)).thenReturn(false);
		mainn.run(x, y);
	}

	@Test
	public void testMainWithoutException() throws Bounds {
		x = 600;
		y = 600;

		Main main = mock(Main.class);
		Main mainn = new Main();
		when(main.run(x, y)).thenReturn(false);
		mainn.run(x, y);
	}

	@Test
	public void testFakeObject() {
		MainView mv = new MainView();
		Freehand freehand = new Freehand();
		View.Rectangle rectangle = new View.Rectangle();
		MainView mockView = mock(MainView.class);
		mv = initiateWindow();
		ArrayList<View.Rectangle> rectangles = new ArrayList<>();
		ArrayList<Freehand> freehands = new ArrayList<>();
		View.Rectangle rectangleIniMock = mock(View.Rectangle.class);
		rectangleIniMock.makeRectangle(800, 500, 5, 5);
		Shape rectshape = rectangleIniMock.makeRectangle(800, 500, 20, 20);
		View.Rectangle rectangleShapeMock = new View.Rectangle(Color.red, rectshape);

		rectangles.add(rectangleShapeMock);
		when(mockView.getRectangles()).thenReturn(rectangles);
		verify(rectangleIniMock, times(1)).makeRectangle(800, 500, 5, 5);
		System.out.println(rectangleShapeMock.getShape());

		// Making fake object and adding to fake list.
		Shape freehandShape = freehand.makeLine(1000, 450, 50, 50);
		freehand = new Freehand(Color.red, freehandShape);
		freehands.add(freehand);
		assertTrue(freehands.size() == 1);
		freehands.remove(0);
		assertTrue(freehands.isEmpty());

		Shape rectangleShape = rectangle.makeRectangle(1000, 450, 50, 50);
		rectangle = new View.Rectangle(Color.red, rectangleShape);
		rectangles.add(rectangle);
		System.out.println(rectangles.size());
		assertTrue(rectangles.size() == 2);
		// dispatch(frame);
	}

	@Test
	public void testTitleBar() throws AWTException {

		MainView mv = new MainView();
		mv.drawWindow(x, y);
		mv.buildMenu();
		JPanel jpBottom = mv.buildBottom(Color.lightGray, Color.black);
		JPanel mainPanel = mv.buildTopGrid();
		JFrame frame = mv.buildWindow(jpBottom, mainPanel);

		// ..
		when(mock.getwhichActionWasPerformed()).thenReturn("Nytt").thenReturn("Ångra").thenReturn("Stäng av");

		assertTrue(frame.isShowing());
		System.out.println("Drawing some random stuff");
		recTestMenu(0, mv);
		verify(mock, times(3)).getwhichActionWasPerformed();
		// dispatch(frame);
	}

	int i = 0;
	MainView mock = mock(MainView.class);

	public void recTestMenu(int i, MainView mv) throws AWTException {

		if (i <= 50) {
			Robot bot = new Robot();
			for (int j = 0; j < 80; j += 5) {
				bot.mouseMove(950, 500 + j);
				bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				bot.mouseMove(850 + j, 600);
				bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
				bot.mouseMove(950 + j, 500);
				bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				bot.mouseMove(850, 600 + j);
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
			mock.getwhichActionWasPerformed();

			switch (i) {
			case 0:
				System.out.println(mv.getwhichActionWasPerformed());
				assertTrue(mv.getwhichActionWasPerformed().equals("Nytt"));
				break;
			case 25:
				assertTrue(mv.getwhichActionWasPerformed().equals("Ångra"));
				break;
			case 50:
				assertTrue(mv.getwhichActionWasPerformed().equals("Stäng av"));
				break;
			default:
				break;
			}
			i += 25;
			recTestMenu(i, mv);
		}
	}

	private MainView initiateWindow() {
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

	private void dispatch(JFrame frame) {
		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
	}
}
