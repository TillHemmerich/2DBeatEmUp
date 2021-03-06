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
public class Player extends BaseObject {

//Player states 
    public static final String STATE_WALKING_LEFT = "walking_left";
    public static final String STATE_WALKING_RIGHT = "walking_right";
    public static final String STATE_IDLE_LEFT = "idle_left";
    public static final String STATE_IDLE_RIGHT = "idle_right";
    private String currentState = STATE_IDLE_RIGHT;
    private BufferedImage currentImage;

    public static final BufferedImage[] WALKING_LEFT = {Sprite.getSprite(0, 1), Sprite.getSprite(1, 1), Sprite.getSprite(2, 1)};
    public static final BufferedImage[] WALKING_RIGHT = {Sprite.getSprite(0, 2), Sprite.getSprite(1, 2), Sprite.getSprite(2, 2)};
    public static final BufferedImage IDLE_LEFT = Sprite.getSprite(1, 1);
    public static final BufferedImage IDLE_RIGHT = Sprite.getSprite(1, 2);

    public Player(int x, int y, int width, int height) {
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
    }

//    private Animation walkLeft = new Animation(walkingLeft, 10);
//    private Animation walkRight = new Animation(walkingRight, 10);
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
