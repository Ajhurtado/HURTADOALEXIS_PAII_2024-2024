package main_package;

import java.awt.Color;
import java.awt.Graphics;

import model_package.Hero;
import model_package.IDrawable;
import model_package.IMovable;

public class Bullet implements IDrawable, IMovable {

	
	@Override
	public void moveUp(int variable) {
		
	}

	@Override
	public void moveDown(int variable) {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveLeft(int variable) {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveRight(int variable) {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Graphics graphics, IDrawable drawable) {
		graphics.setColor(Color.WHITE);
		graphics.fillOval(((Hero) drawable).cord_x[0] -5, ((Hero) drawable).cord_y[1] - 90, 10, 10);

	}

	@Override
	public void draw(Graphics graphics) {

	}

}
