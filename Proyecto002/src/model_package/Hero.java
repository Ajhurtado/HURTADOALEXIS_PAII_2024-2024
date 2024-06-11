package model_package;

import java.awt.Color;
import java.awt.Graphics;

import controlador_package.Container;
import main_package.Bullet;

public class Hero extends Role implements IDrawable, IMovable, IShootable {
	
	private Bullet bullet; // Instancia de la bala
	
	
	  // Constructor
    public Hero(int[] cord_x, int[] cord_y) {
        super(cord_x, cord_y);
        bullet = new Bullet(); // Inicialización de la bala
    }

    // Implementación de los métodos abstractos de Drawable
    @Override
    public void draw(Graphics graphics) {
        // Implementación del dibujado específico de Hero
        graphics.setColor(Color.WHITE);
        graphics.fillPolygon(getCordX(), getCordY(), 3);
        graphics.drawLine(0, 500, 800, 500);
    }


    // Implementación de los métodos abstractos de Movable
    @Override
    public void moveLeft(int variable) {
        // Implementación del movimiento hacia la izquierda específico de Hero
        for (int i = 0; i < getCordX().length; i++) {
            getCordX()[i] -= variable;
        }
    }

    @Override
    public void moveRight(int variable) {
        // Implementación del movimiento hacia la derecha específico de Hero
        for (int i = 0; i < getCordX().length; i++) {
            getCordX()[i] += variable;
        }
    }

    @Override
    public void moveDown(int variable) {

    }

	@Override
	public void moveUp(int variable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics graphics, IDrawable drawable) {
		// TODO Auto-generated method stub

	}

	@Override
	 public void shoot(Graphics graphics) {
		//AQUI DEBERIA O BIEN DIBUJAR LA BALA, PARA DESPUES QUE SE MUEVA CREO
		bullet.draw(graphics);  
		/*bullet.setX(getCordX()[0]); // Posición X de la bala
	      bullet.setY(getCordY()[1] - 90); // Posición Y de la bala*/
    }



}
