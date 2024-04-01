package com.twk.tank;

import java.awt.*;

public class Tank {
    private int x, y;

    private TankFrame tf;
    private static int TANK_WIDTH = ResourceMgr.tankD.getWidth();
    private static int TANK_HEIGHT = ResourceMgr.tankD.getHeight();

    Dir dir;
    private boolean moving = false;

    public void setMoving(boolean flag) {
        this.moving = flag;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public Tank(int x, int y, Dir dir, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public void paint(Graphics g) {
        switch (dir) {
            case left:
                g.drawImage(ResourceMgr.tankL, x, y, null);
                break;
            case up:
                g.drawImage(ResourceMgr.tankU, x, y, null);
                break;
            case right:
                g.drawImage(ResourceMgr.tankR, x, y, null);
                break;
            case down:
                g.drawImage(ResourceMgr.tankD, x, y, null);
                break;
        }
        move();
    }

    private void move() {
        if (moving) {
            int SPEED = 6;
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
        }
    }

    public void fire() {
        int X = x + TANK_WIDTH/2 - Bullet.WIDTH_BULLET/2;
        int Y = y + TANK_HEIGHT/2 - Bullet.HEIGHT_BULLET/2;
        tf.Bullets.add(new Bullet(X, Y, this.dir, this.tf));
    }
}
