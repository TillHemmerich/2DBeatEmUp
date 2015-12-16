/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2dbeatemup;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import pkg2dbeatemup.abilities.Fireball;

/**
 *
 * @author phili
 */
public class MyFrame extends JFrame implements KeyListener {

    MyPanel gamePanel;

    public MyFrame(int x, int y) {
        setSize(x, y);
        addKeyListener(this);
        gamePanel = new MyPanel(getWidth(), getHeight());
        add(gamePanel);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //   System.out.println(e.getKeyCode());
        switch (e.getKeyCode()) {
            // Enter
            case 10:
                // Feuerball
                if (gamePanel.getPlayer().getCurrentState().contains("left")) {
                    gamePanel.addFireball(Fireball.DIRECTION_LEFT);
                } else if (gamePanel.getPlayer().getCurrentState().contains("right")) {
                    gamePanel.addFireball(Fireball.DIRECTION_RIGHT);
                }

                break;
            // A
            case 65:
                gamePanel.getPlayer().setCurrentState(Player.STATE_WALKING_LEFT);
                break;
            // D
            case 68:
                gamePanel.getPlayer().setCurrentState(Player.STATE_WALKING_RIGHT);
                break;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'a':
            case 'A':
                gamePanel.getPlayer().setCurrentState(Player.STATE_IDLE_LEFT);
                break;
            case 'd':
            case 'D':
                gamePanel.getPlayer().setCurrentState(Player.STATE_IDLE_RIGHT);
                break;
        }
    }

}
