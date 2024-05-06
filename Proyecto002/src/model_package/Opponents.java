package model_package;

import java.awt.Color;
import java.awt.Graphics;

public class Opponents implements IDrawable, IMovable {

	int[] cord_x = new int[5];
	int[] cord_y = new int[5];

	public Opponents(int randomX, int randomY) {
		cord_x[0] = randomX;
		cord_x[1] = randomX + 100;
		cord_x[2] = randomX + 100;
		cord_x[3] = randomX + 50;
		cord_x[4] = randomX;

		cord_y[0] = randomY;
		cord_y[1] = randomY;
		cord_y[2] = randomY + 50;
		cord_y[3] = randomY + 25;
		cord_y[4] = randomY + 50;

	}

	@Override
	public void draw(Graphics graphics) {
		graphics.setColor(Color.GREEN);
		graphics.fillPolygon(cord_x, cord_y, 5);

	}

	@Override
	public void moveUp(int variable) {

	}

	@Override
	public void moveDown(int variable) {
		for (int i = 0; i < cord_y.length; i++) {
			cord_y[i] = cord_y[i] + variable;

		}

	}

	@Override
	public void moveLeft(int variable) {

	}

	@Override
	public void moveRight(int variable) {

	}

	@Override
	public void draw(Graphics graphics, IDrawable drawable) {
		// TODO Auto-generated method stub

	}
}
