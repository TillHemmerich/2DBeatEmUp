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

    private Player player;
    private Image image = new ImageIcon("Resources/bilder/2d_background.jpg").getImage();
    private Rectangle ground = new Rectangle(0, 0, 0, 0);

    public gamePanel() {
        player = new Player();
        this.setSize(image.getHeight(this), image.getWidth(this));

    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public void paint(Graphics g) {
        paintBackground((Graphics2D) g);
        paintGround((Graphics2D) g);
        paintPlayer((Graphics2D) g, player.getCurrentState());
    }

    public void paintBackground(Graphics2D g) {
        g.drawImage(image, 0, 0, null);

    }

    public void paintGround(Graphics2D g) {
        ground.setSize(getWidth(), 50);
        ground.setLocation(0, getHeight() - 60);
        g.fill(ground);

    }

    public void paintPlayer(Graphics2D g, String state) {
        System.out.println(state);
        BufferedImage player;
        switch (state) {
            case Player.STATE_IDLE_LEFT:
                player = Sprite.getSprite(1, 1);
                break;
            case Player.STATE_IDLE_RIGHT:
                player = Sprite.getSprite(1, 2);
                break;
            default:
                player = null;
        }
        g.drawImage(player, null, 500, (int) (getHeight() - 100));

    }

}
