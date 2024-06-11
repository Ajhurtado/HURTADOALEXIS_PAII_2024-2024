package com.example.GalagaX.Controller;


import com.example.GalagaX.Components.Bullet;
import com.example.GalagaX.Components.EnemyShip;
import com.example.GalagaX.Components.Hero;
import com.example.GalagaX.Components.SuperEnemy;

import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Container {
    private Hero ship;
    private List<EnemyShip> enemies;
    private boolean gameOver;
    private boolean gameWinner;
    private int enemySpawnCounter;
    private int score;
    private SuperEnemy superEnemy;
    private int enemiesDestroyed = 0;
    private boolean superDefeat = false;
    private int currentLevel = 1;
    private boolean levelMessageShown = true;

    public Container() {
        ship = new Hero(400, 500);
        enemies = new LinkedList<>();
        score = 0;
        gameOver = false;
        gameWinner = false;
        enemySpawnCounter = 0;
    }

    public void draw( Graphics graphics) {
        graphics.drawLine(0, 370, 800, 370);
        ship.draw(graphics);

    }
    public void drawEnemy(Graphics graphics){
        for (EnemyShip enemy : getEnemies()) {
            enemy.draw(graphics);
        }
    }
    public void moveLeft() {
        ship.moveLeft();
    }
    public void moveRight() {
        ship.moveRight();
    }
    public void moveDown() {
    }
    public void moveUp() {
        ship.moveRight();
    }
    public void shoot(){
        ship.shoot();
    }

    public void shootSuper(){
        superEnemy.shoot();
    }

    public void shootNormal(){
        for (EnemyShip enemy : getEnemies()) {
            enemy.shoot();
        }
    }

    public void shootDouble(){
        for (EnemyShip enemy : getEnemies()) {
            enemy.shootDouble();
        }

    }

    public void updateBullets() {
        List<Bullet> bullets = ship.getBullets();
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            bullet.moveUp();
            if (bullet.getY() < 0) {
                bullets.remove(i);
                i--;
            }
        }
        updateBulletEnemy();

    }

    public void updateBulletEnemy(){
        for (EnemyShip enemy : enemies) {
            List<Bullet> bullets = enemy.getBullets();
            for (int i = 0; i < bullets.size(); i++) {
                Bullet bullet = bullets.get(i);
                bullet.moveDown();
                if (bullet.getY() > 550) {
                    bullets.remove(i);
                    i--;
                }
            }
        }
    }

    public void updateBulletSuperEnemy(){
        if (superEnemy != null) {
            List<Bullet> bullets = superEnemy.getBullets();
            for (int i = 0; i < bullets.size(); i++) {
                Bullet bullet = bullets.get(i);
                bullet.moveDown();
                if (bullet.getY() > 550) {
                    bullets.remove(i);
                    i--;
                }
            }
        }
    }

    public void handleBulletRectangleCollisions() {
        List<Bullet> bullets = ship.getBullets();
        Iterator<Bullet> bulletIterator = bullets.iterator();
        while (bulletIterator.hasNext()) {
            Bullet bullet = bulletIterator.next();
            if (superEnemy != null && bullet.getBounds().intersects(superEnemy.getBounds())) {
                bulletIterator.remove();
                score+=15;
                superEnemy.hit();
                if (!superEnemy.isAlive()) {
                    handleEnemyDestroyed();

                }
                continue;
            }
            Iterator<EnemyShip> enemyIterator = enemies.iterator();
            while (enemyIterator.hasNext()) {
                EnemyShip enemy = enemyIterator.next();
                if (bullet.getBounds().intersects(enemy.getBounds()) && enemy.isAlive()) {
                    bulletIterator.remove();
                    enemy.hit();
                    if(currentLevel==2){
                        score+=10;
                    }
                    if (!enemy.isAlive()) {
                        enemyIterator.remove();
                        handleEnemyDestroyed();
                       if(currentLevel==1){
                           score += 5;
                       }
                        if (currentLevel == 4) {
                            enemySpawnCounter = Integer.MAX_VALUE;
                                superEnemy = new SuperEnemy(400, 100);
                        }
                    }
                    break;
                }
            }
            bullet.moveUp();
            if (bullet.getY() < 0) {
                bulletIterator.remove();
            }
        }
        handleEnemyBulletHeroCollisions();
    }

    private void handleEnemyBulletHeroCollisions() {
        for (EnemyShip enemy : enemies) {
            List<Bullet> enemyBullets = enemy.getBullets();
            Iterator<Bullet> bulletIterator = enemyBullets.iterator();
            while (bulletIterator.hasNext()) {
                Bullet bullet = bulletIterator.next();
                if (bullet.getBounds().intersects(ship.getBounds())) {
                    bulletIterator.remove();
                    if (currentLevel == 1) {
                        ship.setLoseHealth(5);
                    } if (currentLevel == 2) {
                        ship.setLoseHealth(10);
                    } if (currentLevel == 3){
                        ship.setLoseHealth(15);
                    }
                    /////
                    if (currentLevel == 3){
                        ship.setLoseHealth(15);
                    }
                    //////
                    ship.hit();
                    if (!ship.isAlive()) {
                        gameOver = true;
                    }
                    return;
                }
            }
        }
        if (superEnemy != null) {
            List<Bullet> superEnemyBullets = superEnemy.getBullets();
            Iterator<Bullet> bulletIterator = superEnemyBullets.iterator();
            while (bulletIterator.hasNext()) {
                Bullet bullet = bulletIterator.next();
                if (bullet.getBounds().intersects(ship.getBounds())) {
                    bulletIterator.remove();
                    ship.hit();
                    if (!ship.isAlive()) {
                        gameOver = true;
                        return;
                    }
                }
            }
        }
    }

    public void updateEnemy(){
        if(currentLevel==2) {
            for (Iterator<EnemyShip> iterator = enemies.iterator(); iterator.hasNext(); ) {
                EnemyShip enemy = iterator.next();
                enemy.moveDown();
                shootDouble();
                if (!enemy.isAlive()) {
                    iterator.remove();
                }
            }
        } else {
            for (Iterator<EnemyShip> iterator = enemies.iterator(); iterator.hasNext(); ) {
                EnemyShip enemy = iterator.next();
                enemy.moveDown();
                shootNormal();
                if (!enemy.isAlive()) {
                    iterator.remove();
                }
            }
        } if(currentLevel==4){
                shootSuper();
            if (ship.getHealth() > 75) {
                superEnemy.setLostHealth(15);
            } else if (ship.getHealth() > 50 && ship.getHealth() <= 75) {
                superEnemy.setLostHealth(10);
            } else {
                superEnemy.setLostHealth(5);
            }
        }
        enemySpawnCounter++;
        if (enemySpawnCounter >= 100 && enemies.isEmpty() && score < 2000) {
            enemySpawnCounter = 0;
            EnemyShip newEnemy = EnemyShip.spawnEnemy();
            if (currentLevel == 1) {
                newEnemy.setLoseHealth(1);
            } else if (currentLevel == 2) {
                newEnemy.setHealth(40);
                newEnemy.setLoseHealth(3);
            }
            enemies.add(newEnemy);
        }
    }

    public void startNextLevel(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        if (levelMessageShown) {
            g.drawString("¡Nivel " + currentLevel + "!", 370, 50);
        }
    }

    private void handleEnemyDestroyed() {
        enemiesDestroyed++;
        if (enemiesDestroyed == 2 && currentLevel==1) {
            currentLevel++;
            levelMessageShown = true;
            resetEnemySpawn();
        }if (enemiesDestroyed == 7 &&currentLevel==2) {
            currentLevel++;
            resetEnemySpawn();
        }
        if (enemiesDestroyed == 2 &&currentLevel==3) {
            currentLevel++;
            resetEnemySpawn();
        } if (currentLevel==4 && enemiesDestroyed==1){
            gameWinner = true;
            superDefeat = true;
        }

    }

    public void resetEnemySpawn() {
        enemySpawnCounter = 0;
        enemies.clear();
        enemiesDestroyed = 0;
        levelMessageShown = true;
    }

    public boolean isSuperDefeat() {
        return superDefeat;
    }

    public List<EnemyShip> getEnemies() {
        return enemies;
    }

    public SuperEnemy getSuperEnemy() {
        return superEnemy;
    }

    public int getScore() {
        return score;
    }

    public Hero getShip() {
        return ship;
    }

}
