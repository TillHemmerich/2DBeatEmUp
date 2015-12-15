/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2dbeatemup.abilities;

import java.awt.image.BufferedImage;
import pkg2dbeatemup.Sprite;

/**
 *
 * @author phili
 */
public class Fireball {

    public static final int DIRECTION_LEFT = 0;
    public static final int DIRECTION_RIGHT = 1;
    private int x;
    private int y;
    private int direction;
    private BufferedImage currentImage = Sprite.loadSprite("fireball.png");

    public Fireball(int x, int y, int direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

  

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
    

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public BufferedImage getCurrentImage() {
        return currentImage;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

}
