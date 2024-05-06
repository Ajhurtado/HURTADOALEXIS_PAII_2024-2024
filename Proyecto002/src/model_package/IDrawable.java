package model_package;

import java.awt.Graphics;

public interface IDrawable {
	

	
	public void draw(Graphics graphics);
	public void draw(Graphics graphics, IDrawable drawable);
}
