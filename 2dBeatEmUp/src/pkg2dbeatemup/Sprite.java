/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2dbeatemup;

/**
 *
 * @author Till
 */
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Sprite {

    private static BufferedImage spriteSheet;
    private static final int TILE_SIZE = 32;
    
    public static BufferedImage loadSprite(String file) {

        BufferedImage sprite = null;
        
        try {
            sprite = ImageIO.read(new File("Resources/bilder/testsprite.png"));
        } catch (IOException e) {
            
            e.printStackTrace();
        }

        return sprite;
    }

    public static BufferedImage getSprite(int xGrid, int yGrid) {

        if (spriteSheet == null) {
            spriteSheet = loadSprite("AnimationSpriteSheet");
        }

        return spriteSheet.getSubimage(xGrid * TILE_SIZE, yGrid * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }

}