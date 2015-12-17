/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2dbeatemup;

import com.sun.javafx.tk.Toolkit;
import java.awt.Color;
import java.awt.Dimension;
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
import pkg2dbeatemup.input.InputListener;
import pkg2dbeatemup.level.LevelParser;

/**
 *
 * @author Till
 */
public class MyPanel extends JPanel {

    private int tileSize;
    private int backgroundX;
    private int characterAnimationSpeed = 48;
    private int backgroundScrollSpeed = 6;
    private int fireballSize = 30;
    private int fireballSpeed = 8;
    private Player player;
    private Image image = Sprite.loadSprite("2d_background.jpg");
    private Image terrain = Sprite.loadSprite("terrain.png");
    private ArrayList<Fireball> fireballsList = new ArrayList();
    private LevelParser levelParser;
    private Rectangle visibleArea;

    public MyPanel(int x, int y, InputListener inputListener) {
        setSize(x, y);

        visibleArea = new Rectangle(0, 0, getWidth(), getHeight());
        levelParser = new LevelParser(1, getHeight());
        tileSize = getHeight() / levelParser.getLevelHeight();
        player = new Player(getWidth() / 2, getHeight() - 72 * 2, 40, 40);

        backgroundX = 0;
        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (true) {
                    //handle keyboard input
                    switch (inputListener.getPlayerState()) {
                        case Player.STATE_IDLE_LEFT:
                            player.setCurrentState(Player.STATE_IDLE_LEFT);
                            break;
                        case Player.STATE_IDLE_RIGHT:
                            player.setCurrentState(Player.STATE_IDLE_RIGHT);
                            break;
                        case Player.STATE_WALKING_LEFT:
                            player.setCurrentState(Player.STATE_WALKING_LEFT);
                            break;
                        case Player.STATE_WALKING_RIGHT:
                            player.setCurrentState(Player.STATE_WALKING_RIGHT);
                            break;
                        default:
                            break;
                    }

                    if (inputListener.isEnterPressed()) {
                        if (player.getCurrentState().contains("left")) {
                            addFireball(Fireball.DIRECTION_LEFT);
                        } else if (player.getCurrentState().contains("right")) {
                            addFireball(Fireball.DIRECTION_RIGHT);
                        }
                    }

//update visivle area
                    {
                        visibleArea.setLocation(player.getX() - getWidth() / 2, 0);
                    }
                    //fireballs
                    for (int j = 0; j < fireballsList.size(); j++) {

                        if (!isVisible(fireballsList.get(j).getRectangle())) {
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
                    if (inputListener.isSpacePressed()) {
                        player.setY(player.getY() - 4);
                    }
                    if (levelParser.getTileAt(player.getX(), player.getY() + player.getHeight(), tileSize).getType() != 'E') {
                        player.setY(player.getY() + 2);
                    }
                    if (i < characterAnimationSpeed) {
                        i++;
                    } else if (i == characterAnimationSpeed) {
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

                            if (player.getX() > 0 && levelParser.getTileAt(player.getX(), player.getY(), tileSize).getType() != 'E') {
                                player.setX(player.getX() - 5);
                                if (i <= ((double) characterAnimationSpeed * 0.33)) {

                                    player.setCurrentImage(player.WALKING_LEFT[0]);
                                } else if (i <= ((double) characterAnimationSpeed * 0.66)) {
                                    player.setCurrentImage(player.WALKING_LEFT[1]);
                                } else if (i <= (double) characterAnimationSpeed) {
                                    player.setCurrentImage(player.WALKING_LEFT[2]);
                                }
                            } else {
                                player.setCurrentState(Player.STATE_IDLE_LEFT);
                            }
                            break;

                        case Player.STATE_WALKING_RIGHT:
                            if (levelParser.getTileAt(player.getX() + player.getWidth(), player.getY(), tileSize).getType() != 'E') {
                                player.setX(player.getX() + 5);

                                if (i <= ((double) characterAnimationSpeed * 0.33)) {
                                    player.setCurrentImage(player.WALKING_RIGHT[0]);
                                } else if (i <= ((double) characterAnimationSpeed * 0.66)) {
                                    player.setCurrentImage(player.WALKING_RIGHT[1]);
                                } else if (i <= (double) characterAnimationSpeed) {
                                    player.setCurrentImage(player.WALKING_RIGHT[2]);
                                }
                            } else {
                                player.setCurrentState(Player.STATE_IDLE_RIGHT);
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
                        Logger.getLogger(MyPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        ).start();
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public void paint(Graphics g) {
        g.clearRect(0, 0, getWidth(), getHeight());
        paintBackground((Graphics2D) g);
        paintTiles((Graphics2D) g);
        paintFireballs((Graphics2D) g);
        paintPlayer((Graphics2D) g);
    }

    public void paintBackground(Graphics2D g) {
        g.drawImage(image, backgroundX, 0, getWidth(), getHeight(), null);
        g.drawImage(image, backgroundX - getWidth(), 0, getWidth(), getHeight(), null);
        g.drawImage(image, backgroundX + getWidth(), 0, getWidth(), getHeight(), null);

    }

    public void paintPlayer(Graphics2D g) {
        g.drawImage(player.getCurrentImage(), player.getX() - ((int) visibleArea.getX()), player.getY(), player.getWidth(), player.getHeight(), null);
    }

    public void paintFireballs(Graphics2D g) {
        for (int i = 0; i < fireballsList.size(); i++) {
            if (isVisible(fireballsList.get(i).getRectangle())) {
                g.drawImage(fireballsList.get(i).getCurrentImage(), fireballsList.get(i).getX() - ((int) visibleArea.getX()), fireballsList.get(i).getY(), fireballsList.get(i).getWidth(), fireballsList.get(i).getHeight(), null);
            }
        }
    }

    private void paintTiles(Graphics2D g) {
        for (int y = 0; y < levelParser.getLevelHeight(); y++) {
            for (int x = 0; x < levelParser.getLevelWidth(); x++) {

                if (isVisible(levelParser.getTileAt(x, y).getRectangle())) {
                    if (levelParser.getTileAt(x, y).getType() == 'E') {
                        g.drawImage(terrain, levelParser.getTileAt(x, y).getX() - ((int) visibleArea.getX()), levelParser.getTileAt(x, y).getY(), levelParser.getTileAt(x, y).getWidth(), levelParser.getTileAt(x, y).getHeight(), null);
                    }
                }
            }
        }
    }

    public void addFireball(int direction) {
        Fireball fireball = new Fireball(player.getX() + player.getWidth() / 2, player.getY(), 20, 20, direction);
        fireballsList.add(fireball);
    }

    private boolean isVisible(Rectangle r) {
        if (r.getX() > visibleArea.getX() + visibleArea.getWidth()) {
            return false;
        } else if (r.getX() + r.getWidth() < visibleArea.getX()) {
            return false;
        }
        return true;
    }
}
