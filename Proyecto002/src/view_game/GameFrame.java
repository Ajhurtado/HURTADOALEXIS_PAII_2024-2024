package view_game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import controlador_package.Container;


public class GameFrame extends JFrame implements KeyListener{
	
	//private static final long serialVersionUID =1L;
	private JPanel contentPane;
	private Container container;
	
	public GameFrame (String title) {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800,600);
		
		container = new Container();
		contentPane = new JPanel();
		contentPane.setBackground(Color.black);
		setContentPane(contentPane);
		addKeyListener(this);
		
		Timer timer = new Timer(100, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				container.moveDown(1);
				repaint();
				
			}
			
		});
	timer.start();
		
	}
	public void paint (Graphics graphics) {
		super.paint(graphics);
		container.draw(graphics);
		
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int tecla = e.getKeyCode();
        if (tecla == KeyEvent.VK_SPACE) {
            container.drawShoot(getGraphics().create());
          //  container.moveUp(5);

        } else if (tecla == KeyEvent.VK_LEFT) {
            container.moveLeft(10);
        } else if (tecla == KeyEvent.VK_RIGHT) {
            container.moveRight(10);
        }
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
			
		
	
}
