/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2dbeatemup.abilities;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import pkg2dbeatemup.BaseObject;
import pkg2dbeatemup.Sprite;

/**
 *
 * @author phili
 */
public class Fireball extends BaseObject {

    public static final int DIRECTION_LEFT = 0;
    public static final int DIRECTION_RIGHT = 1;
    private int direction;
    private BufferedImage currentImage = Sprite.loadSprite("fireball.png");

    public Fireball(int x, int y, int width, int height, int direction) {
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
        
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public BufferedImage getCurrentImage() {
        return currentImage;
    }

}
