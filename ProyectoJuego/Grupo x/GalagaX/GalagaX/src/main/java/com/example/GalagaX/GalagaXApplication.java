package com.example.GalagaX;

import com.example.GalagaX.Visual.GameFrame;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GalagaXApplication {

	public static void main(String[] args) {
		//SpringApplication.run(GalagaXApplication.class, args);
		System.setProperty("java.awt.headless","false");
		GameFrame gameFrame = new GameFrame("Galaga Game");
		gameFrame.setVisible(true);
	}

}
