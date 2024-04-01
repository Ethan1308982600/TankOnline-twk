package com.twk.tank;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = new TankFrame();
        for (int i = 0; i < 5; i++) {
            tf.Tanks.add(new Tank(50 + i * 50, 200, Dir.down, tf));
        }
        while (true) {
            Thread.sleep(20);
            tf.repaint();
        }
    }
}
