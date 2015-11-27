package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Line2D;

public class Freehand extends Igraphs {

	Shape shape;
	Color color;
	int x, y;

	public Freehand(Color color, Shape shape) {
		this.shape = shape;
		this.color = color;

	}

	public Freehand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics g2, int d, double e, double f, double h) {
		Graphics2D g = (Graphics2D) g2;
		g.setPaint(Color.LIGHT_GRAY);
		Shape r = makeLine(d, e, f, h);
		g.draw(r);
	}

	public Line2D.Double makeLine(double x, double x2, double y, double y2) {
		return new Line2D.Double(x, x2, y, y2);
	}

	public Shape getShape() {
		return shape;
	}

	public Color getColor() {
		return color;
	}

}
