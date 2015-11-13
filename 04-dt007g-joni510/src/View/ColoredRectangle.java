package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;

public class ColoredRectangle extends Igraphs {

	Shape shape;
	Color color;

	public ColoredRectangle(Color color, Shape shape) {
		
		this.color = color;
		this.shape = shape;
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

	@Override
	public void draw(Graphics g) {
		
		
	}

}
