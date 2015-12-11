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

  private BufferedImage[] walkingLeft = {Sprite.getSprite(0, 1), Sprite.getSprite(2, 1)}; // Gets the upper left images of my sprite sheet
private BufferedImage[] walkingRight = {Sprite.getSprite(0, 2), Sprite.getSprite(2, 2)};
private BufferedImage[] standing = {Sprite.getSprite(1, 0)};


private Animation walkLeft = new Animation(walkingLeft, 10);
private Animation walkRight = new Animation(walkingRight, 10);
private Animation stand = new Animation(standing, 10);
//
//
private Animation animation = stand;
   
}
