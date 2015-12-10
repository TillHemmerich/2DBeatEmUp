/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingpongextremsickoverloade;

import java.awt.event.KeyListener;
import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.*;


/**
 *
 * @author T107P06A
 */
public class Sickeskeyboard implements KeyListener{

    private SickeKomponenten sk; 
    private boolean sPressed;
    private boolean oPressed;
    private boolean wPressed;
    private boolean lPressed;
    private boolean paused;

    Sickeskeyboard(SickeKomponenten sk) {
        this.sk = sk;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'w') {
            wPressed = true;
        }
        if (e.getKeyChar() == 's') {
            sPressed = true;
        }
        if (e.getKeyChar()== 'o'){
            oPressed = true;
        }
        if (e.getKeyChar() == 'l'){
            lPressed = true;
        }
        
        if(e.getKeyCode() == 32) {
            paused = !paused;
        }

       
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyChar() == 's') {
            sPressed = false;
        }
        if (e.getKeyChar() == 'o') {
            oPressed = false;
        }
        if (e.getKeyChar() == 'l') {
            lPressed = false;
        }
        if (e.getKeyChar() == 'w') {
            wPressed = false;
        }
        
    }
    
     public boolean issPressed() {
        return sPressed;
    }

    public boolean isoPressed() {
        return oPressed;
    }

    public boolean iswPressed() {
        return wPressed;
    }

    public boolean islPressed() {
        return lPressed;
    }
    
    public boolean isPaused() {
        return paused;
    }
}
