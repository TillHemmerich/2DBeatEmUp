/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2dbeatemup.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import pkg2dbeatemup.Player;

/**
 *
 * @author phili
 */
public class InputListener implements KeyListener {

    private boolean dPressed = false;
    private boolean aPressed = false;
    private boolean enterPressed = false;
    private boolean spacePressed = false;
    private String playerState = Player.STATE_IDLE_RIGHT;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
        switch (e.getKeyCode()) {
            // Enter
            case 10:
                enterPressed = true;
                break;
            // A
            case 65:
                aPressed = true;
                playerState = Player.STATE_WALKING_LEFT;
                break;
            // D
            case 68:
                dPressed = true;
                playerState = Player.STATE_WALKING_RIGHT;
                break;
            //space
            case 32:
                spacePressed = true;
                break;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            // A
            case 65:
                aPressed = false;
                playerState = Player.STATE_IDLE_LEFT;
                break;
            // D
            case 68:
                dPressed = false;
                playerState = Player.STATE_IDLE_RIGHT;
                break;
            //enter
            case 10:
                enterPressed = false;
                break;
            //space
            case 32:
                spacePressed = false;
        }
    }

    public boolean isSpacePressed() {
        return spacePressed;
    }

    public String getPlayerState() {
        return playerState;
    }

    public boolean isdPressed() {
        return dPressed;
    }

    public boolean isaPressed() {
        return aPressed;
    }

    public boolean isEnterPressed() {
        return enterPressed;
    }

}
