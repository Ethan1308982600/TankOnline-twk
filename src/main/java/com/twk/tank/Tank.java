package com.twk.tank;

import java.awt.*;

public class Tank {
    private int x, y;
    private final int SPEED = 10;

    private TankFrame tf;


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
        Color c = g.getColor();
        g.setColor(Color.yellow);
        g.fillRect(x,y,50,50);
        g.setColor(c);
        move();
    }
    private void move(){
        if (moving) {
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
        tf.Bullets.add(new Bullet(this.x,this.y,this.dir,this.tf));
    }
}
