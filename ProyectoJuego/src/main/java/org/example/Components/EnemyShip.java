package org.example.Components;

import org.example.Interfaces.IDibujar;
import org.example.Interfaces.IMovable;
import org.example.Interfaces.IShootable;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemyShip implements IDibujar, IMovable, IShootable {
    private static final int WIDTH = 40;
    private static final int HEIGHT = 5;
    private  int SPEED=1;
    private long lastShootTime = 0;
    private final int shootInterval = 2000;
    private int x,y;
    private static final int[] xPoints = {-20, 20, 20, 0, -20};;
    private static final int[] yPoints = {0, 0, 30, 20, 30};
    private static final int nPoints = 5;
    private boolean alive;
    private double health;
    private int loseHealth=1;
    private List<Bullet> bullets;

    public static EnemyShip spawnEnemy() {
        Random random = new Random();
        int startX = random.nextInt(800 - WIDTH);
        int startY = 100;
        return new EnemyShip(startX, startY);
    }

    public EnemyShip(int startX, int startY) {
        x = startX;
        y = startY;
        alive = true;
        health = 40;
        bullets = new ArrayList<>();
    }

    @Override
    public void draw(Graphics g) {
        if (alive) {
            g.setColor(Color.GREEN);
            Polygon enmyPolygon = new Polygon(xPoints, yPoints, nPoints);
            enmyPolygon.translate(x, y);
            g.fillPolygon(enmyPolygon);
            drawHealthBar(g);
        }
        for (Bullet bullet : bullets) {
            bullet.draw(g);
        }

    }

    @Override
    public void shoot() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastShootTime >= shootInterval) {
            bullets.add(new Bullet(x, y));
            lastShootTime = currentTime;
        }

    }

    @Override
    public void shootDouble() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastShootTime >= shootInterval) {
            bullets.add(new Bullet(x + 20, y + 30));
            bullets.add(new Bullet(x - 20, y + 30));
            lastShootTime = currentTime;
        }
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

    public List<Bullet> getBullets() {
        return bullets;
    }
    
    private void drawHealthBar(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x - WIDTH / 2, y - HEIGHT / 2 - 10, (int) health, 5);
        g.setColor(Color.GREEN);
        g.fillRect(x - WIDTH / 2, y - HEIGHT / 2 - 10, (int) health, 5); // Dibujar la vida restante

    }

    public boolean isAlive() {
        return alive;
    }

    public Rectangle getBounds() {
        Polygon enemyPolygon = new Polygon(xPoints, yPoints, nPoints);
        enemyPolygon.translate(x, y);
        return enemyPolygon.getBounds();
    }

    public void hit() {
        health-= (double) 40 /loseHealth;
        if (health <= 0) {
            alive = false;
        }
    }

    public int getY() {
        return y;
    }

    public void setLoseHealth(int loseHealth) {
        this.loseHealth = loseHealth;
    }

    public void setHealth(int health) {
        this.health = health;
    }

}