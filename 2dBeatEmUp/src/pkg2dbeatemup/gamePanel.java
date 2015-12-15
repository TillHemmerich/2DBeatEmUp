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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import pkg2dbeatemup.abilities.Fireball;

/**
 *
 * @author Till
 */
public class gamePanel extends JPanel {

    int backgroundX;
    int characterMovementSpeed = 28;
    int backgroundScrollSpeed = 6;
    int fireballSize = 30;
    int fireballSpeed = 5;
    private Player player;
    private Image image = Sprite.loadSprite("2d_background.jpg");
    private Rectangle ground = new Rectangle(0, 0, 0, 0);
    private ArrayList<Fireball> fireballsList = new ArrayList();

    public gamePanel() {
        player = new Player();
        this.setSize(image.getHeight(this), image.getWidth(this));
        backgroundX = 0;
        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (true) {
                    for (int j = 0; j < fireballsList.size(); j++) {
                        if (fireballsList.get(j).getX() > getWidth() || fireballsList.get(j).getX() < 0 - fireballSize) {
                            fireballsList.remove(j);
                        }
                    }
                    for (int k = 0; k < fireballsList.size(); k++) {
                        if (fireballsList.get(k).getDirection() == Fireball.DIRECTION_RIGHT) {
                            fireballsList.get(k).setX(fireballsList.get(k).getX() + fireballSpeed);
                        } else if (fireballsList.get(k).getDirection() == Fireball.DIRECTION_LEFT) {
                            fireballsList.get(k).setX(fireballsList.get(k).getX() - fireballSpeed);
                        }

                    }
                    //character
                    if (i < characterMovementSpeed) {
                        i++;
                    } else if (i == characterMovementSpeed) {
                        i = 0;
                    }
                    switch (player.getCurrentState()) {
                        case Player.STATE_IDLE_LEFT:
                            player.setCurrentImage(player.IDLE_LEFT);
                            break;
                        case Player.STATE_IDLE_RIGHT:
                            player.setCurrentImage(player.IDLE_RIGHT);
                            break;
                        case Player.STATE_WALKING_LEFT:
                            if (i <= ((double) characterMovementSpeed * 0.33)) {

                                player.setCurrentImage(player.WALKING_LEFT[0]);
                            } else if (i <= ((double) characterMovementSpeed * 0.66)) {
                                player.setCurrentImage(player.WALKING_LEFT[1]);
                            } else if (i <= (double) characterMovementSpeed) {
                                player.setCurrentImage(player.WALKING_LEFT[2]);
                            }

                            break;
                        case Player.STATE_WALKING_RIGHT:
                            if (i <= ((double) characterMovementSpeed * 0.33)) {
                                player.setCurrentImage(player.WALKING_RIGHT[0]);
                            } else if (i <= ((double) characterMovementSpeed * 0.66)) {
                                player.setCurrentImage(player.WALKING_RIGHT[1]);
                            } else if (i <= (double) characterMovementSpeed) {
                                player.setCurrentImage(player.WALKING_RIGHT[2]);
                            }
                            break;
                        default:
                            player = null;
                    }
                    //background
                    if (backgroundX < getWidth() || backgroundX > 0) {
                        if (player.getCurrentState().equals(Player.STATE_WALKING_RIGHT)) {
                            backgroundX -= backgroundScrollSpeed;
                        } else if (player.getCurrentState().equals(Player.STATE_WALKING_LEFT)) {
                            backgroundX += backgroundScrollSpeed;
                        }

                    }
                    if (backgroundX >= getWidth() || backgroundX <= 0 - getWidth()) {
                        backgroundX = 0;
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
        g.clearRect(0, 0, getWidth(), getHeight());
        paintBackground((Graphics2D) g);
        paintGround((Graphics2D) g);
        paintPlayer((Graphics2D) g);
        paintFireballs((Graphics2D) g);
    }

    public void paintBackground(Graphics2D g) {
        g.drawImage(image, backgroundX, 0, null);
        g.drawImage(image, backgroundX - getWidth(), 0, null);
        g.drawImage(image, backgroundX + getWidth(), 0, null);

    }

    public void paintGround(Graphics2D g) {
        ground.setSize(getWidth(), 50);
        ground.setLocation(0, getHeight() - 68);
        g.fill(ground);

    }

    public void paintPlayer(Graphics2D g) {
        g.drawImage(player.getCurrentImage(), null, 500, (int) (getHeight() - 100));
    }

    public void paintFireballs(Graphics2D g) {
        for (int i = 0; i < fireballsList.size(); i++) {
            g.drawImage(fireballsList.get(i).getCurrentImage(), fireballsList.get(i).getX(), fireballsList.get(i).getY(), fireballSize, fireballSize, null);
        }
    }

    public void addFireball(int direction) {
        Fireball fireball = new Fireball(getWidth()/2, getHeight() - 110, direction);
        fireballsList.add(fireball);
    }

}
