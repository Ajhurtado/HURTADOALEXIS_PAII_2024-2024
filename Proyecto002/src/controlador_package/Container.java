package controlador_package;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import main_package.Bullet;
import model_package.Hero;
import model_package.Opponents;

public class Container {
	final int SCREEN_WIDTH =700;
	final int SCREEN_HEIGHT =200;
	

	
	Hero hero; // Declarar una instancia de Hero
	
	//Bullet bullet;
	//Lista de Bullet
	 List<Bullet> bullets;
	
	
	//Opponents opoOpponents;
	List<Opponents> opponents;
	
    public Container() {
        // Crear una instancia de Hero con coordenadas específicas
        int[] heroCordX = {400, 430, 370};
        int[] heroCordY = {540, 600, 600};
        hero = new Hero(heroCordX, heroCordY);
        opponents = new ArrayList<>();
        //Balas
        bullets = new ArrayList<>();
    
        
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int randomX = random.nextInt(SCREEN_WIDTH - 100); // Ajustar el rango de X para evitar salirse de la pantalla
            int randomY = random.nextInt(SCREEN_HEIGHT - 50); // Ajustar el rango de Y para evitar salirse de la pantalla

            int[] opponentCordX = new int[5]; 
            int[] opponentCordY = new int[5];

            opponentCordX[0] = randomX;
            opponentCordX[1] = randomX + 100;
            opponentCordX[2] = randomX + 100;
            opponentCordX[3] = randomX + 50;
            opponentCordX[4] = randomX;

            opponentCordY[0] = randomY;
            opponentCordY[1] = randomY;
            opponentCordY[2] = randomY + 50;
            opponentCordY[3] = randomY + 25;
            opponentCordY[4] = randomY + 50;
            
            opponents.add(new Opponents(opponentCordX, opponentCordY));
            
        }
      
    }
	

	public void draw( Graphics graphics) {
		hero.draw(graphics);
		for (int i = 0; i< opponents.size(); i++) {
			opponents.get(i).draw(graphics);
		}
		
		//Aqui simplemente estara dibujandose la bala
		//new Bullet().draw(graphics, hero);
	
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
	
	public void moveUp(int variable) {
		//aqui debemos obtener la bala y modificar el y, capas usando un GET para obtener la y e irle aumentando
	/*	 for (Bullet bullet : bullets) {
		        bullet.moveUp(variable);
		    }*/
		
	}
	
	public void drawShoot(Graphics graphics) {
		//Aqui se dibujara igual la bala, pero al ser llamado en el GameFrame el container.drawShoot, se dibujara cuando se aplasta el ESPACIO
		new Bullet().draw(graphics, hero);
	   
		
	}


	
}