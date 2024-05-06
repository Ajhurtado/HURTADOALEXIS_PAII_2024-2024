package controlador_package;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import main_package.Bullet;
import model_package.Hero;
import model_package.Opponents;

public class Container {
	final int SCREEN_WITDH =700;
	final int SCREEN_HEIGHT =200;
	
	Hero hero = new Hero();
	Bullet bullet = new Bullet();
	List<Opponents> opponents = new ArrayList<Opponents>();
	Random random = new Random();
	
	//Opponents opponet = new Opponents();
	
	public  Container() {
		opponents.add(new Opponents(random.nextInt(SCREEN_WITDH),random.nextInt(SCREEN_HEIGHT)));
		opponents.add(new Opponents(random.nextInt(SCREEN_WITDH),random.nextInt(SCREEN_HEIGHT)));
		opponents.add(new Opponents(random.nextInt(SCREEN_WITDH),random.nextInt(SCREEN_HEIGHT)));
		opponents.add(new Opponents(random.nextInt(SCREEN_WITDH),random.nextInt(SCREEN_HEIGHT)));
		opponents.add(new Opponents(random.nextInt(SCREEN_WITDH),random.nextInt(SCREEN_HEIGHT)));
	
	}
	

	public void draw( Graphics graphics) {
		hero.draw(graphics);
		for (int i = 0; i< opponents.size(); i++) {
			opponents.get(i).draw(graphics);
		}
		
	}
	
	public void moveLeft(int variable) {
		hero.moveLeft(variable);
		
	}
	
	public void moveRight(int variable) {
		hero.moveRight(variable);
		
	}
	
	public void moveDown(int variable) {
		for (int i = 0; i< opponents.size(); i++) {
			opponents.get(i).moveDown(variable);
		}
	}
	
	public void drawShoot(Graphics graphics) {
		new Bullet().draw(graphics, hero);
	}

	//PRUEBA PESCADO
	public void moveUp(int variable) {
		for (int i = 0; i< opponents.size(); i++) {
		 bullet.moveUp(variable);
		}
	}
	
}