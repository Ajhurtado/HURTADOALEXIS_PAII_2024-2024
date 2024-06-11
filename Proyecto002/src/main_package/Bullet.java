package main_package;

import java.awt.Color;
import java.awt.Graphics;

import model_package.Hero;
import model_package.IDrawable;
import model_package.IMovable;
import model_package.IShootable;
import model_package.Role;

public class Bullet  implements IDrawable, IMovable {

	    private int x; // Coordenada X de la bala
	    private int y; // Coordenada Y de la bala
	    private int heroX; // Coordenada X de la nave
	    private int heroY; // Coordenada Y de la nave
	  //AQUI DEBERIA OBTENER LA UBIACION DE LA PUNTA DEL HEROE
	    
	    // Getters y setters
	    public void setX(int x) {
	        this.x = x;
	    }

	    public void setY(int y) {
	        this.y = y;
	    }

	 /*   
	    @Override
		public void moveUp(int variable, IDrawable drawable) {
			// TODO Auto-generated method stub
	    	y = -variable;
	    	/*Hero hero = (Hero) drawable;
	        int[] cord_x = hero.getCordX(); // Obtener las coordenadas X del héroe
	        int[] cord_y = hero.getCordY(); // Obtener las coordenadas Y del héroe
	         x = cord_x[0]; // La punta del héroe puede ser el segundo punto en las coordenadas X
	         y = cord_y[1];
	         
		}
	    
*/
	@Override
	public void moveUp(int variable) {
		//AQUI DEBERIA OBTENER LA COORDENADA Y DE LA PUNTA DEL HEROE PARA PODER MODIFICARLA
		y = - variable;
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
	
		/* graphics.setColor(Color.WHITE);
	     graphics.fillOval(x - 5, y, 10, 10);*/
		
		      
		 Hero hero = (Hero) drawable;
	        int[] cord_x = hero.getCordX(); // Obtener las coordenadas X del héroe
	        int[] cord_y = hero.getCordY(); // Obtener las coordenadas Y del héroe
	         x = cord_x[0]; // La punta del héroe puede ser el segundo punto en las coordenadas X
	         y = cord_y[1]; // La punta del héroe puede ser el segundo punto en las coordenadas Y
		        graphics.setColor(Color.WHITE);
		        graphics.fillOval(x - 5, y - 90, 10, 10); // Dibujar la bala cerca de la punta del héroe
		    
		        //TENDRIA QUE MOVERSE TAMBIEN
		    moveUp(5);
	/*	   graphics.setColor(Color.WHITE);
	       graphics.fillOval(heroX - 5, heroY - 90, 10, 10); // Dibujar la bala cerca de la punta del héroe
	*/	
		        
	}

	@Override
	public void draw(Graphics graphics) {

	}

	

}
