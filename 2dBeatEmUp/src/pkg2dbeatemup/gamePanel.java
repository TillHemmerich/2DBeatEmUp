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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Till
 */
public class gamePanel extends JPanel {

    int iMax = 28;
    private Player player;
    private Image image = new ImageIcon("Resources/bilder/2d_background.jpg").getImage();
    private Rectangle ground = new Rectangle(0, 0, 0, 0);

    public gamePanel() {
        player = new Player();
        this.setSize(image.getHeight(this), image.getWidth(this));
        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (true) {

                    if (i < iMax) {
                        i++;
                    } else if (i == iMax) {
                        i = 0;
                    }
                    switch (player.getCurrentState()) {
                        case Player.STATE_IDLE_LEFT:
                            player.setCurrentImage(Sprite.getSprite(1, 1));
                            break;
                        case Player.STATE_IDLE_RIGHT:
                            player.setCurrentImage(Sprite.getSprite(1, 2));
                            break;
                        case Player.STATE_WALKING_LEFT:
                            if (i <= ((double) iMax * 0.33)) {
                                player.setCurrentImage(Sprite.getSprite(0, 1));
                            } else if (i <= ((double) iMax * 0.66)) {
                                player.setCurrentImage(Sprite.getSprite(1, 1));
                            } else if (i <= (double) iMax) {
                                player.setCurrentImage(Sprite.getSprite(2, 1));
                            }

                            break;
                        case Player.STATE_WALKING_RIGHT:
                            if (i <= ((double) iMax * 0.33)) {
                                player.setCurrentImage(Sprite.getSprite(0, 2));
                            } else if (i <= ((double) iMax * 0.66)) {
                                player.setCurrentImage(Sprite.getSprite(1, 2));
                            } else if (i <= (double) iMax) {
                                player.setCurrentImage(Sprite.getSprite(2, 2));
                            }
                            break;
                        default:
                            player = null;
                    }
                    repaint();
                    try {
                        Thread.sleep(1000 / 60);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(gamePanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }).start();
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
        ground.setLocation(0, getHeight());
        g.fill(ground);

    }

    public void paintPlayer(Graphics2D g, String state) {
        g.drawImage(player.getCurrentImage(), null, 500, (int) (getHeight() - 100));

    }

}
