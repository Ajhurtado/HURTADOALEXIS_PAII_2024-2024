package org.example.Visual;

import org.example.Components.EnemyShip;
import org.example.Controller.Container;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics;

public class GameFrame extends JFrame implements KeyListener,ActionListener {

    private JPanel contentPane;
    private Container container;
    private Timer timer;
    private boolean gameOver;
    private boolean gameWinner;
    private String nickname;

    public GameFrame(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        container = new Container();
        contentPane = new JPanel();
        contentPane.setBackground(Color.black);
        setContentPane(contentPane);
        addKeyListener(this);
        nickname = JOptionPane.showInputDialog(this, "Enter your nickname:");
        timer = new Timer(10, this);
        timer.start();

    }

   public void paint(Graphics g) {
       super.paint(g);
       if (gameWinner) {
           g.clearRect(0, 0, getWidth(), getHeight());
           g.setColor(Color.BLACK);
           g.fillRect(0, 0, getWidth(), getHeight());
           drawWinnerMessage(g);
       } else if (gameOver) {
           g.clearRect(0, 0, getWidth(), getHeight());
           g.setColor(Color.BLACK);
           g.fillRect(0, 0, getWidth(), getHeight());
           drawGameOverMessage(g);
       } else {
           container.startNextLevel(g);
           container.draw(g);
           container.drawEnemy(g);
           if (container.getSuperEnemy() != null) {
               container.getSuperEnemy().draw(g);
           }
           drawScore(g);
       }

   }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                container.moveLeft();
                break;
            case KeyEvent.VK_RIGHT:
                container.moveRight();
                break;
            case KeyEvent.VK_UP:
                container.moveUp();
                break;
            case KeyEvent.VK_DOWN:
                container.moveDown();
                break;
        }
        repaint();

        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            container.shoot();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        container.updateEnemy();
        container.updateBullets();
        container.updateBulletSuperEnemy();
        container.handleBulletRectangleCollisions();
        checkGameOver();
        repaint();
    }

    public void checkGameOver() {
        if (!container.getShip().isAlive()) {
                gameOver = true;
                timer.stop();
                return;
        }
        for (EnemyShip enemy : container.getEnemies()) {
                if (enemy.getY() > 370) {
                    container.getShip().setAlive(false);
                    gameOver = true;
                    timer.stop();
                    break;
                }
        }
        if (container.isSuperDefeat()) {
            gameWinner = true;
            timer.stop();
        }

    }

    private void drawScore(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Player: " + nickname , 10, 50);
        g.drawString("Score: " + container.getScore(), 680, 50);
    }

    public void drawGameOverMessage(Graphics g) {
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString("GAME OVER", 250, 250);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 35));
        g.drawString("USER " + nickname , 250, 350);
        g.setFont(new Font("Arial", Font.BOLD, 35));
        g.drawString("SCORE " + container.getScore(), 250, 400);

    }

    public void drawWinnerMessage(Graphics g) {
        g.setColor(Color.GREEN);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString("WINNER", 250, 250);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 35));
        g.drawString("USER " + nickname , 250, 350);
        g.setFont(new Font("Arial", Font.BOLD, 35));
        g.drawString("SCORE " + container.getScore(), 250, 400);

    }

}


