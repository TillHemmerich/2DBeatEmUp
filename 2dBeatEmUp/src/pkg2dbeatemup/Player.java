/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2dbeatemup;

import java.awt.image.BufferedImage;

/**
 *
 * @author Till
 */
public class Player {

//Player states 
    public static final String STATE_WALKING_LEFT = "walking_left";
    public static final String STATE_WALKING_RIGHT = "walking_right";
    public static final String STATE_IDLE_LEFT = "idle_left";
    public static final String STATE_IDLE_RIGHT = "idle_right";
    private String currentState = STATE_IDLE_RIGHT;
    private BufferedImage currentImage;

    private BufferedImage[] walkingLeft = {Sprite.getSprite(0, 1), Sprite.getSprite(2, 1)}; // Gets the upper left images of my sprite sheet
    private BufferedImage[] walkingRight = {Sprite.getSprite(0, 2), Sprite.getSprite(2, 2)};
    private BufferedImage idleLeft = Sprite.getSprite(1, 1);
    private BufferedImage idleRight = Sprite.getSprite(1, 2);

    private Animation walkLeft = new Animation(walkingLeft, 10);
    private Animation walkRight = new Animation(walkingRight, 10);

    public BufferedImage getCurrentImage() {
        return currentImage;
    }

    public void setCurrentImage(BufferedImage currentImage) {
        this.currentImage = currentImage;
    }

    public String getCurrentState() {
        return currentState;
    }

    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }

}
