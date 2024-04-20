package com.twk.tank.TankOnline;

import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *ServerFrame建立一个后端消息展示台，用于记录client端和server端的交互
 * 方便查找bug和调试
 */
@Slf4j
public class ServerFrame extends Frame {
    public static final ServerFrame INSTANCE = new ServerFrame();

    TextArea textAreaLeft = new TextArea();
    TextArea textAreaRight = new TextArea();
    Server server = new Server();

    public ServerFrame () {
        this.setSize(1400, 600);
        this.setLocation(300, 30);
        Panel p = new Panel(new GridLayout(1, 2));
        p.add(textAreaLeft);
        p.add(textAreaRight);
        this.add(p);

        textAreaLeft.setFont(new Font("verderna",Font.PLAIN, 25));

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });


    }

    public void updateServerMsg(String string) {
        this.textAreaLeft.setText(textAreaLeft.getText() + string + System.getProperty("line.separator"));
    }

    public void updateClientMsg(String string) {
        this.textAreaRight.setText(textAreaRight.getText() + string + System.getProperty("line.separator"));
    }


    public static void main(String[] args) {
        ServerFrame.INSTANCE.setVisible(true);
        ServerFrame.INSTANCE.server.ServerStart();
    }
}

