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
import pkg2dbeatemup.input.InputListener;

/**
 *
 * @author phili
 */
public class MyFrame extends JFrame {

    InputListener inputListener;
    MyPanel gamePanel;

    public MyFrame(int x, int y) {
        setSize(x, y);
        inputListener = new InputListener();
        addKeyListener(inputListener);
        gamePanel = new MyPanel(getWidth(), getHeight(), inputListener);
        add(gamePanel);
    }
}
