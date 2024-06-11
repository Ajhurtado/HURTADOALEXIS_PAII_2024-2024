package com.example.GalagaX.Components;



import com.example.GalagaX.Interfaces.IDibujar;
import com.example.GalagaX.Interfaces.IMovable;

import java.awt.*;

public class Bullet implements IDibujar, IMovable {

    private int x, y;
    private final int speed = 10;
    private final int speedDown = 5;

    public Bullet(int startX, int startY) {
        x = startX;
        y = startY;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillOval(x, y, 5, 5);
    }
    @Override
    public void moveUp() {
        y -= speed;
    }

    @Override
    public void moveDown() {
        y += speedDown;
    }

    @Override
    public void moveRight() {

    }

    @Override
    public void moveLeft() {

    }

    public int getY() {
        return y;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 5, 5);
    }


}
