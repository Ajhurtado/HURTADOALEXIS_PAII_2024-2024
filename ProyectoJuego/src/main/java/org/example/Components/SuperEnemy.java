package org.example.Components;

import org.example.Interfaces.IDibujar;
import org.example.Interfaces.IMovable;
import org.example.Interfaces.IShootable;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SuperEnemy implements IDibujar, IMovable, IShootable {
    private static final int WIDTH = 100;
    private static final int HEIGHT = 70;
    private static final int SPEED = 1;
    private int x;
    private int y;
    private static final int[] xPoints = {-50, 50, 50, 0, -50};;
    private static final int[] yPoints = {0, 0, 70, 50, 70};
    private static final int nPoints = 5;
    private boolean alive;
    private int health;
    private int lostHealth=15;
    private long lastShootTime = 0;
    private final int shootInterval = 2000;
    private List<Bullet> bullets;

    public SuperEnemy(int startX, int startY) {
        x = startX;
        y = startY;
        alive = true;
        health = 100;
        bullets = new ArrayList<>();
    }

    @Override
    public void draw(Graphics g) {
        if (alive) {
            g.setColor(Color.RED);
            Polygon enmyPolygon = new Polygon(xPoints, yPoints, nPoints);
            enmyPolygon.translate(x, y);
            g.fillPolygon(enmyPolygon);
            drawHealthBar(g);
        }
        for (Bullet bullet : bullets) {
            bullet.draw(g);
        }
    }

    private void drawHealthBar(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(x - WIDTH / 2, y-10, health, 5);
        g.setColor(Color.GREEN);
        g.fillRect(x - WIDTH / 2, y- 10, health, 5);
       // g.drawString(String.valueOf(health), 200, 200);
    }

    @Override
    public void moveUp() {

    }

    @Override
    public void moveDown() {
        y += SPEED;
        if (y > 600) {
            alive = false;
        }
    }

    @Override
    public void moveRight() {

    }

    @Override
    public void moveLeft() {

    }

    @Override
    public void shoot() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastShootTime >= shootInterval) {
            bullets.add(new Bullet(x + 50, y + 70));
            bullets.add(new Bullet(x, y + 50));
            bullets.add(new Bullet(x - 50, y + 70));
            lastShootTime = currentTime;
        }
    }

    public void hit() {
        health-=lostHealth;
        if (health <= 0) {
            alive = false;
        }
    }

    @Override
    public void shootDouble() {

    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setLostHealth(int lostHealth) {
        this.lostHealth = lostHealth;
    }

    public Rectangle getBounds() {
        return new Rectangle(x - WIDTH / 2, y - HEIGHT / 2, WIDTH, HEIGHT);
    }

}
