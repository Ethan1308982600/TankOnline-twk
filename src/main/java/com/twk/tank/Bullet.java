package com.twk.tank;

import java.awt.*;

public class Bullet {
    private static final int SPEED = 10;
    private static int WIDTH = 5, HEIGHT = 5;
    private int x, y;
    private Dir dir;
    private TankFrame tf;
    private boolean live = true;

    public Bullet(int x, int y, Dir dir, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public void paint(Graphics g) {
        if (!live) {
            tf.Bullets.remove(this);
        } else {
            Color c = g.getColor();
            g.setColor(Color.RED);
            g.fillOval(x, y, WIDTH, HEIGHT);
            g.setColor(c);
            move();
        }
    }
    private void move() {
        switch (dir) {
            case left:
                x -= SPEED;
                break;
            case right:
                x += SPEED;
                break;
            case up:
                y -= SPEED;
                break;
            case down:
                y += SPEED;
                break;
            default:
                break;
        }
        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAMA_HEIGHT) this.live = false;
    }
}
