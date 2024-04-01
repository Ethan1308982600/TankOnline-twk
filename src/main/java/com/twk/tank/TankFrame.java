package com.twk.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class TankFrame extends Frame {
    Tank mytank = new Tank(200, 400, Dir.down, this);
    List<Bullet> Bullets = new ArrayList<>();
    List<Tank> Tanks = new ArrayList<>();
    static final int GAME_WIDTH = 800, GAMA_HEIGHT = 600;

    public TankFrame() {
        this.setSize(GAME_WIDTH, GAMA_HEIGHT);
        this.setResizable(false);
        this.setTitle("tank war");
        this.setVisible(true);
        this.addKeyListener(new MyKeyListener());
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAMA_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.black);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAMA_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
//        System.out.println("paint");
        Color c = g.getColor();
        g.setColor(Color.white);
        g.drawString("子弹的数量:" + Bullets.size(), 10, 60);
        mytank.paint(g);
        for (int i = 0; i < Bullets.size(); i++) {
            Bullets.get(i).paint(g);
        }
        for (int i = 0; i < Tanks.size(); i++) {
            Tanks.get(i).paint(g);
        }
    }

    class MyKeyListener extends KeyAdapter {
        boolean bL, bR, bU, bD;

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                case KeyEvent.VK_CONTROL:
                    mytank.fire();
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        private void setMainTankDir() {
            if (!bL && !bU && !bR && !bD) {
                mytank.setMoving(false);
            } else {
                mytank.setMoving(true);
                if (bL) mytank.setDir(Dir.left);
                if (bR) mytank.setDir(Dir.right);
                if (bU) mytank.setDir(Dir.up);
                if (bD) mytank.setDir(Dir.down);
            }

        }
    }


}
