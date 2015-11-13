package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;

public class Freehand extends Igraphs{

		Shape shape;
		Color color;
		int x,y;
		
	public Freehand(Color color, Shape shape) {
		this.shape = shape;
		this.color = color;
	}
	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		g.drawRect(x, y, 3, 3);
	}
	public Shape getShape() {
		return shape;
	}
	public void setShape(Shape shape) {
		this.shape = shape;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}

}
