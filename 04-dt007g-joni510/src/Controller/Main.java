package Controller;

import java.awt.Color;

import javax.swing.JPanel;

import org.omg.CORBA.Bounds;

import View.MainView;

public class Main {

	
	public static void main(String[] args) throws Bounds {
		Main mv = new Main();
		int x = 600, y = 600;
		mv.run(x, y);
	}

	public boolean run(int x,int y) throws Bounds{
		MainView mv = new MainView();
		
			if(mv.drawWindow(x, y)){
				mv.buildMenu();
				JPanel jpBottom = mv.buildBottom(Color.lightGray, Color.black);
				JPanel mainPanel = mv.buildTopGrid();
				mv.buildWindow(jpBottom,mainPanel);
				mv.addListeners();
			}else{
				throw new Bounds("Must be larger than 600 x 600");
			}
			return false;
		
			
		
		
//		return true;
	}
	

}
