package com.example.GalagaX.Components;



import com.example.GalagaX.Interfaces.IDibujar;
import com.example.GalagaX.Interfaces.IMovable;
import com.example.GalagaX.Interfaces.IShootable;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Hero implements IDibujar, IMovable, IShootable {
    private static final int[] xPoints = { -15, 0, 15 };
    private static final int[] yPoints = { 30, -30, 30 };
    private static final int nPoints = 3;
    private int x, y;
    private List<Bullet> bullets;
    private final int speed = 10;
    private boolean alive;
    private int health;
    private int loseHealth;

    public Hero(int startX, int startY) {
        x = startX;
        y = startY;
        alive = true;
        health = 100;
        bullets = new ArrayList<>();
    }

    @Override
    public void draw(Graphics g) {
        if (alive) {
            g.setColor(Color.WHITE);
            Polygon shipPolygon = new Polygon(xPoints, yPoints, nPoints);
            shipPolygon.translate(x, y);
            g.fillPolygon(shipPolygon);
            drawHealthBar(g);
        }
        for (Bullet bullet : bullets) {
            bullet.draw(g);
        }
    }

    public void moveLeft() {
        x -= 25;
        if (x < 0) {
            x = 0;
        }
    }

    public void moveRight() {
        x += 25;
        if (x > 785) {
            x = 785;
        }
    }

    public void moveUp() {
    }

    public void moveDown() {
    }

    public void shoot() {
        bullets.add(new Bullet(x, y));
    }

    @Override
    public void shootDouble() {

    }

    private void drawHealthBar(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(10, 100, 100, 5);
        g.setColor(Color.GREEN);
        g.drawString("VIDA", 10, 80);
        g.fillRect(10, 100, health, 5);
        g.drawString(String.valueOf(health), 100, 80);
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public boolean isAlive() {
        return alive;
    }

    public void hit() {
        health-=loseHealth;
        if (health <= 0) {
            alive = false;
        }
    }


    public Rectangle getBounds() {
        return new Rectangle(x - 15, y - 30, 30, 60);
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void setLoseHealth(int loseHealth) {
        this.loseHealth = loseHealth;
    }

    public int getHealth() {
        return health;
    }

}
