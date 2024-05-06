package model_package;

import java.awt.Color;
import java.awt.Graphics;

public class Hero implements IDrawable, IMovable {

	public int[] cord_x = { 400, 430, 370 };
	public int[] cord_y = { 540, 600, 600 };

	@Override
	public void draw(Graphics graphics) {
		graphics.setColor(Color.WHITE);

		graphics.fillPolygon(cord_x, cord_y, 3);
		graphics.drawLine(0, 500, 800, 500);

	}

	@Override
	public void moveUp(int variable) {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveDown(int variable) {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveLeft(int variable) {
		// Restar
		for (int i = 0; i < cord_x.length; i++) {
			cord_x[i] = cord_x[i] - variable;

		}
	}

	@Override
	public void moveRight(int variable) {
		// Sumar
		for (int i = 0; i < cord_x.length; i++) {
			cord_x[i] = cord_x[i] + variable;

		}

	}

	@Override
	public void draw(Graphics graphics, IDrawable drawable) {
		// TODO Auto-generated method stub

	}
}
