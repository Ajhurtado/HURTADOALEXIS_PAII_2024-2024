package model_package;

import java.awt.Color;
import java.awt.Graphics;

public class Opponents extends Role implements IDrawable, IMovable {

	public Opponents(int[] cord_x, int[] cord_y) {
		super(cord_x, cord_y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics graphics) {
		graphics.setColor(Color.GREEN);
		graphics.fillPolygon(getCordX(), getCordY(), 5);

	}

	@Override
	public void moveUp(int variable) {

	}

	@Override
	public void moveDown(int variable) {
		for (int i = 0; i < getCordY().length; i++) {
			getCordY()[i] = getCordY()[i] + variable;

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
