package Controller;

import java.awt.Color;

import javax.swing.JPanel;

import org.omg.CORBA.Bounds;

import View.MainView;
/**
 * 
 * @author Joakim
 * Jag har tagit mig tiden till att försöka mig på att använda mig utav testning(TDD),
 * samtidigt som jag gjorde denna uppgiften. Därför är koden aningens(!) rörig med mina get-metoder.
 */
public class Main {

	
	/**
	 * @param args
	 * @throws Bounds
	 */
	public static void main(String[] args) throws Bounds {
		Main mv = new Main();
		int x = 600, y = 600;
		mv.run(x, y);
	}
/**
 * 
 * @param x
 * @param y
 * @return
 * @throws Bounds
 */
	public boolean run(int x, int y) throws Bounds {
		MainView mv = new MainView();

		if (mv.drawWindow(x, y)) {
			mv.buildMenu();
			JPanel jpBottom = mv.buildBottom(Color.lightGray, Color.black);
			JPanel mainPanel = mv.buildTopGrid();
			mv.buildWindow(jpBottom, mainPanel);
			mv.addListeners();
		} else {
			throw new Bounds("Must be larger than 600 x 600");
		}
		return false;

	}

}
