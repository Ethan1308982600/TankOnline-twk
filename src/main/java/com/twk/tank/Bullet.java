package com.twk.tank;

import java.awt.*;

public class Bullet {
    private static final int SPEED = 6;


    public static int WIDTH_BULLET = ResourceMgr.bulletD.getWidth();
    public static int HEIGHT_BULLET = ResourceMgr.bulletD.getHeight();
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
            switch (dir) {
                case left:
                    g.drawImage(ResourceMgr.bulletL, x, y, null);
                    break;
                case up:
                    g.drawImage(ResourceMgr.bulletU, x, y, null);
                    break;
                case right:
                    g.drawImage(ResourceMgr.bulletR, x, y, null);
                    break;
                case down:
                    g.drawImage(ResourceMgr.bulletD, x, y, null);
                    break;
            }
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
