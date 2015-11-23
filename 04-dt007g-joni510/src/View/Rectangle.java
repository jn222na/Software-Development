package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

public class Rectangle extends Igraphs {

	Shape shape;
	Color color;

	public Rectangle(Color color, Shape shape) {

		this.color = color;
		this.shape = shape;
	}

	public Rectangle() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics g2, int d, double e, double f, double h) {
		Graphics2D g = (Graphics2D) g2;
		g.setPaint(Color.LIGHT_GRAY);
		Shape r = makeRectangle(d, (int) e, (int) f, (int) h);
		g.draw(r);
	}

	Rectangle2D.Double makeRectangle(double x1, double y1, double x2, double y2) {
		return new Rectangle2D.Double(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
	}
	
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Shape getShape() {
		return shape;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}
}
