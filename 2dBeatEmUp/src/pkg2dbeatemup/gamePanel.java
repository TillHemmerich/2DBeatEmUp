/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2dbeatemup;

import com.sun.javafx.tk.Toolkit;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Till
 */
public class gamePanel extends JPanel {
    
    private Image image = new ImageIcon(this.getClass().getResource("/bilder/2d_background.jpg")).getImage();
   
    private Rectangle ground = new Rectangle(0, 0, 0, 0);
   
    
    public gamePanel() {
        this.setSize(image.getHeight(this), image.getWidth(this));
        
    }

    @Override
    public void paint(Graphics g) {
        paintBackground((Graphics2D) g);
        paintGround((Graphics2D) g);
        paintPlayer((Graphics2D) g);
        
    }
    
  
    public void paintBackground(Graphics2D g) {
        g.drawImage(image, 0, 0, null);
        
    }

    public void paintGround(Graphics2D g) {
        ground.setSize( getWidth(),50);
        ground.setLocation(0,getHeight()-60);
        g.fill(ground);
        
    }
    public void paintPlayer(Graphics2D g){
        Sprite.loadSprite("testsprite");
        g.drawImage(Sprite.getSprite(WIDTH, WIDTH), null, 500, (int)(getHeight()-100));
    }
    
    
}
