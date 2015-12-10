/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingpongextremsickoverloade;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.nio.IntBuffer;
import java.util.Random;

import javax.swing.*;

/**
 *
 * @author T107P06A
 */
public class SickeKomponenten extends JComponent {

    private double player1Kasten = 0;
    private double player2Kasten = 0;
    private double player3Kasten = 0;
    private double player4Kasten = 0;
    private double player1Geschwindigkeit = 2;
    private double player2Geschwindigkeit = 2;
    private double player3Geschwindigkeit = 2;
    private double player4Geschwindigkeit = 2;
    private double ballGeschwindigkeitX = 1;
    private double ballGeschwindigkeitY = 1;
    private double ballX = 0;
    private double ballY = 0;
    private int counterplayer1 = 0;
    private int counterplayer2 = 0;
    private int counterplayer3 = 0;
    private int counterplayer4 = 0;

    private boolean ballRichtungX = true;
    private boolean ballRichtungY = true;
    private int sickerstartwert = (int) (Math.random() * 1);
    private int player1Size = 100;
    private int player2Size = 100;
    private int player3Size = 100;
    private int player4Size = 100;

    private Rectangle player1Rectangle = new Rectangle(50, 50, 20, player1Size);
    private Rectangle player2Rectangle = new Rectangle(650, 50, 20, player2Size);
    private Rectangle player3Rectangle = new Rectangle(2000, 20000, player3Size, 20);
    private Rectangle player4Rectangle = new Rectangle(20000, 20000, player4Size, 20);

    private Rectangle Ball = new Rectangle(100, 100, 20, 20);
    private Rectangle powerUp = new Rectangle(getWidth() + 50, getHeight() + 50, 0, 0);
    private boolean pause = false;
    private int sekunden = 0;

    private boolean player1AmBall;
    private boolean player2AmBall;
    private boolean player3AmBall;
    private boolean player4AmBall;

    private int zufall;
    private int zufallsX = 0;
    private int zufallsY = 0;
    private boolean inverted = false;
    private boolean botgame = false;

    public void setbotgame(boolean botgame) {
        this.botgame = botgame;

    }

    public boolean getInverted() {
        return inverted;

    }

    public int getPlayer1Size() {
        return player1Size;
    }

    public void setPlayer1Size(int player1Size) {
        this.player1Size = player1Size;

    }

    public int getPlayer2Size() {
        return player1Size;
    }

    public void setPlayer2Size(int player2Size) {
        this.player1Size = player2Size;

    }

    public int getPlayer3Size() {
        return player3Size;
    }

    public void setPlayer3Size(int player3Size) {
        this.player3Size = player3Size;

    }

    public int getPlayer4Size() {
        return player1Size;
    }

    public void setPlayee4Size(int player4Size) {
        this.player4Size = player4Size;

    }

    public int getSekunden() {
        return sekunden;
    }

    public void setSekudnen(int sekunden) {
        this.sekunden = sekunden;

    }

    public boolean isPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public int getCounterplayer1() {
        return counterplayer1;
    }

    public int getCounterplayer2() {
        return counterplayer2;
    }

    public int getCounterplayer3() {
        return counterplayer3;
    }

    public int getCounterplayer4() {
        return counterplayer4;
    }

    public void setCounterplayer1(int counterplayer1) {
        this.counterplayer1 = counterplayer1;
    }

    public void setCounterplayer2(int counterplayer2) {
        this.counterplayer2 = counterplayer2;
    }

    public void setCounterplayer3(int counterplayer3) {
        this.counterplayer3 = counterplayer3;
    }

    public void setCounterplayer4(int counterplayer4) {
        this.counterplayer4 = counterplayer4;
    }

    public double getBall() {
        return ballX;
    }

    public void setBall(double ball) {
        this.ballX = ball;
    }

    public double getPlayer1() {
        return player1Kasten;
    }

    public void setPlayer1(boolean richtung) {
        if (richtung) {
            player1Kasten -= player1Geschwindigkeit;
        } else {
            player1Kasten += player1Geschwindigkeit;
        }
        if (player1Kasten <= 0) {
            player1Kasten = 0;

        }
        if (player1Kasten >= getHeight() - player1Size) {
            player1Kasten = getHeight() - player1Size;

        }
    }

    public double getPlayer2() {
        return player2Kasten;
    }

    public void setPlayer2(boolean richtung) {
        if (richtung) {
            player2Kasten -= player2Geschwindigkeit;
        } else {
            player2Kasten += player2Geschwindigkeit;
        }
        if (player2Kasten <= 0) {
            player2Kasten = 0;

        }
        if (player2Kasten >= getHeight() - player2Size) {
            player2Kasten = getHeight() - player2Size;

        }
    }

    public double getPlayer3() {
        return player3Kasten;
    }

    public void setPlayer3(boolean richtung) {
        if (richtung) {
            player3Kasten -= player3Geschwindigkeit;
        } else {
            player3Kasten += player3Geschwindigkeit;
        }
        if (player1Kasten <= 0) {
            player1Kasten = 0;

        }
        if (player1Kasten >= getWidth() - player3Size) {
            player1Kasten = getWidth() - player3Size;

        }
    }

    public double getPlayer4() {
        return player4Kasten;
    }

    public void setPlayer4(boolean richtung) {
        if (richtung) {
            player4Kasten -= player4Geschwindigkeit;
        } else {
            player4Kasten += player4Geschwindigkeit;
        }
        if (player4Kasten <= 0) {
            player4Kasten = 0;

        }
        if (player4Kasten >= getWidth() - player4Size) {
            player4Kasten = getWidth() - player4Size;

        }
    }

    private void botKI(Graphics2D sickeGrafik) {

        player1Rectangle.setLocation(30, (int) Ball.getY() - (player1Size / 2));
        player2Rectangle.setLocation(getWidth() - 50, (int) Ball.getY() - (player2Size / 2));
        player3Rectangle.setLocation((int) Ball.getX() - (player3Size / 2), 10);
        player4Rectangle.setLocation((int) Ball.getX() - (player4Size / 2), getHeight() - 25);

        sickeGrafik.fill(player3Rectangle);
        sickeGrafik.fill(player4Rectangle);

    }
    private int player1X = 30;
    private int player2X = getWidth() - 50;

    public void paint(Graphics g) {

        Graphics2D sickeGrafik = (Graphics2D) g;
        sickeGrafik.setColor(Color.WHITE);

        if (botgame) {
            botKI(sickeGrafik);
        } else {
            player1Rectangle.setLocation(30, (int) getPlayer1());
            player2Rectangle.setLocation(getWidth() - 50, (int) getPlayer2());
        }

        sickeGrafik.fill(player1Rectangle);
        sickeGrafik.fill(player2Rectangle);
        sickeGrafik.fill(player3Rectangle);
        sickeGrafik.fill(player4Rectangle);

        if (player1Rectangle.intersects(Ball)) {
            sickeGrafik.setColor(Color.red);
            sickeGrafik.fill(player1Rectangle);
            player1AmBall = true;
            player2AmBall = false;
            player3AmBall = false;
            player4AmBall = false;

        }

        if (player2Rectangle.intersects(Ball)) {
            sickeGrafik.setColor(Color.red);
            sickeGrafik.fill(player2Rectangle);
            player2AmBall = true;
            player1AmBall = false;
            player3AmBall = false;
            player4AmBall = false;

        }
        if (player3Rectangle.intersects(Ball)) {
            sickeGrafik.setColor(Color.red);
            sickeGrafik.fill(player3Rectangle);
            player1AmBall = false;
            player2AmBall = false;
            player3AmBall = true;
            player4AmBall = false;

        }
        if (player4Rectangle.intersects(Ball)) {
            sickeGrafik.setColor(Color.red);
            sickeGrafik.fill(player4Rectangle);
            player1AmBall = false;
            player2AmBall = false;
            player3AmBall = false;
            player4AmBall = true;

        }

        paintBall(sickeGrafik);
        paintPowerUp(sickeGrafik);

    }

    public void scored(boolean player1scored) {
        if (player1scored) {
            ballRichtungX = false;
            counterplayer1++;
        } else {
            counterplayer2++;
            ballRichtungX = true;

        }
        Ball.setLocation(getWidth() / 2, getHeight() / 2);
        if (sickerstartwert == 1) {
            ballRichtungX = true;
        } else if (sickerstartwert == 0) {
            ballRichtungX = false;
        }
        sickerstartwert = (int) (Math.random() * 2);

    }

    public int zufallszahl(int max, int min) {

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;

    }

    public void paintPowerUp(Graphics2D g) { // erstellt und verwaltet die
        // PowerUps

        if (sekunden == 9) {

            zufall = (int) (Math.random() * 3);
            zufallsX = zufallszahl(getWidth() - 100, 100);
            zufallsY = zufallszahl(getHeight() - 100, 100);
        }

        if (sekunden > 10) {

            powerUp.setSize(30, 30);
            powerUp.setLocation(zufallsX, zufallsY);
            if (zufall == 0) {

                powerUpKleiner();
                g.setColor(Color.red);
                g.fill(powerUp);

            } else if (zufall == 1) {
                powerUpgroesser();
                g.setColor(Color.green);
                g.fill(powerUp);

            } else if (zufall == 2) {
                powerUpinverted();
                g.setColor(Color.blue);
                g.fill(powerUp);

            }
            
        }
        if (sekunden > 60) {
            sekunden = 0;
            powerUp.setSize(0, 0);
            powerUp.setLocation(getWidth() + 50, getHeight() + 50);
        }

    }

    public void powerUpinverted() {
        if (powerUp.intersects(Ball)) {

            inverted = !inverted;
            sekunden = 0;
            powerUp.setSize(0, 0);
            powerUp.setLocation(0, 0);
        }

    }

    public void powerUpgroesser() {

        if (powerUp.intersects(Ball)) {

            if (player2AmBall == true) {
                if (player2Size < getHeight() - 50) {
                    player2Rectangle.setSize(20, player2Size + 20);
                    player2Size += 20;

                }
            } else if (player1AmBall == true) {
                if (player1Size < getHeight() - 50) {
                    player1Rectangle.setSize(20, player1Size + 20);
                    player1Size += 20;

                }
                
            } else if (player3AmBall == true) {
                if (player3Size < getHeight() - 50) {
                    player3Rectangle.setSize(player3Size+ 20, 20 );
                    player3Size += 20;

                }
            } else if (player4AmBall == true) {
                if (player4Size < getHeight() - 50) {
                    player4Rectangle.setSize(player4Size + 20, 20);
                    player4Size += 20;

                }
            }
            sekunden = 0;
            powerUp.setSize(0, 0);
            powerUp.setLocation(getWidth() + 50, getHeight() + 50);
        }

    }

    public void powerUpKleiner() {

        if (powerUp.intersects(Ball)) {

            if (player2AmBall == true) {
                if (player2Size > 20) {
                    player2Rectangle.setSize(20, player2Size - 20);
                    player2Size -= 20;
                }

            } else if (player1AmBall == true) {
                if (player1Size > 20) {
                    player1Rectangle.setSize(20, player1Size - 20);
                    player1Size -= 20;
                }
                
            }else if (player3AmBall == true) {
                if (player3Size > 20) {
                    player3Rectangle.setSize( player3Size - 20,20);
                    player3Size -= 20;
                }
                
            }else if (player4AmBall == true) {
                if (player4Size > 20) {
                    player4Rectangle.setSize( player4Size - 20,20);
                    player4Size -= 20;
                }
                
            }
            sekunden = 0;
            powerUp.setSize(0, 0);
            powerUp.setLocation(getWidth() + 50, getHeight() + 50);
        }

    }

    public void softreset() {
        counterplayer1 = 0;
        counterplayer2 = 0;
        player2AmBall = false;
        player1AmBall = false;
        sekunden = 0;
        player1Kasten = getHeight() / 2;
        player2Kasten = getHeight() / 2;
        player1Rectangle.setSize(20, 100);
        player2Rectangle.setSize(20, 100);
        inverted = false;
        repaint();
    }

    public void paintBall(Graphics2D g) {
        if (Ball.getX() >= getWidth() - 50) {
            scored(true);

        }
        if (Ball.getY() >= getHeight() - 20) {
            ballRichtungY = false;

        }
        if (Ball.getX() <= 20) {
            scored(false);
        }
        if (Ball.getY() <= 0) {
            ballRichtungY = true;
        }

        if (player1Rectangle.intersects(Ball)) {
            //java.awt.Toolkit.getDefaultToolkit().beep();
            ballRichtungX = true;
        }
        if (player2Rectangle.intersects(Ball)) {
            //java.awt.Toolkit.getDefaultToolkit().beep();

            ballRichtungX = false;
        }
        if (player3Rectangle.intersects(Ball)) {
            ballRichtungY = true;

        }
        if (player4Rectangle.intersects(Ball)) {
            ballRichtungY = false;
        }
        if (ballRichtungX) {
            ballX = ballGeschwindigkeitX;
        } else {
            ballX = -ballGeschwindigkeitX;
        }
        if (ballRichtungY) {
            ballY = ballGeschwindigkeitY;
        } else {
            ballY = -ballGeschwindigkeitY;
        }

        g.setColor(Color.WHITE);

        g.fill(Ball);

        Ball.setLocation((int) (Ball.getX() + ballX),
                (int) (Ball.getY() + ballY));

    }

}
