/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingpongextremsickoverloade;

import java.awt.*;
import java.io.ObjectInputStream.GetField;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author T107P06A
 */
public class Pingpongextremsickoverloade {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	int zeit = 0;
    	int sleep = 4;
        JFrame sickesFrame = new JFrame("King Pong");
        sickesFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sickesFrame.setSize(720, 480);
        sickesFrame.setResizable(true);
        sickesFrame.setVisible(true);
        sickesFrame.setLayout(new BorderLayout());
        JPanel sickerPanel = new JPanel(new BorderLayout());
        sickerPanel.setBackground(Color.BLACK);
        SickeKomponenten sk = new SickeKomponenten();
        Sickeskeyboard keyboard = new Sickeskeyboard(sk);
        sickesFrame.addKeyListener(keyboard);
        sickerPanel.add(sk);
        sickesFrame.add(sickerPanel);
        JLabel spielStand = new JLabel("0:0");
        sickesFrame.add(spielStand,BorderLayout.SOUTH);
        int botgamezahl = JOptionPane.showConfirmDialog(null, "Ein Botgame? ");
        Object resultplayer1 = JOptionPane.showInputDialog(sickesFrame, "Name Spieler 1:");
        Object resultplayer2 = JOptionPane.showInputDialog(sickesFrame, "Name Spieler 2:");
        int i = 0;
        try {
        	  i = Integer.valueOf(JOptionPane.showInputDialog(sickesFrame, "Anzahl Spiele"));
        	 if (i<0){
        		 JOptionPane.showMessageDialog(null,"Bitte eine positive Ganzzahl eingeben");
        		  i = Integer.valueOf(JOptionPane.showInputDialog(sickesFrame, "Anzahl Spiele"));
        	 }
        } catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Bitte eine positive Ganzzahl eingeben");
			 i = Integer.valueOf(JOptionPane.showInputDialog(sickesFrame, "Anzahl Spiele"));
			
		}
        
        if(botgamezahl==0){
        	sk.setbotgame(true);
        	sleep=2;
        }else if(botgamezahl==1){
        	sk.setbotgame(false);
        	sleep=4;
        }
        
        while (true) {
        	zeit++;
        	int teielerWert = 1000/sleep;
        	if (zeit%teielerWert==0){
        		sk.setSekudnen(sk.getSekunden()+1);
        		
        	}
        	spielStand.setText(resultplayer1+": "+sk.getCounterplayer1()+"  ||  "+resultplayer2+": "+sk.getCounterplayer2()+"                   FPS: "+sk.getFps());
            
        	if(sk.getCounterplayer1()==i||sk.getCounterplayer2()==i){
            	if(sk.getCounterplayer1()>sk.getCounterplayer2()){
            		JOptionPane.showMessageDialog(null, resultplayer1+"Gewinnt");
            		sk.softreset();
            		
            	}else{
            		JOptionPane.showMessageDialog(null, resultplayer2+"Gewinnt");
            		sk.softreset();
            	}
            	
            }
        	
        	if (!keyboard.isPaused() && !sk.isPause()) {
                sickesFrame.setTitle("King Pong");
                sickerPanel.repaint();
                if (sk.getInverted()==false){
                	if (keyboard.iswPressed()) {
                    sk.setPlayer1(true);
                }
                if (keyboard.issPressed()) {
                    sk.setPlayer1(false);
                }
                if (keyboard.isoPressed()) {
                    sk.setPlayer2(true);
                }
                if (keyboard.islPressed()) {
                    sk.setPlayer2(false);
                }
                }else if (sk.getInverted()==true){
                	 if (keyboard.iswPressed()) {
                         sk.setPlayer1(false);
                     }
                     if (keyboard.issPressed()) {
                         sk.setPlayer1(true);
                     }
                     if (keyboard.isoPressed()) {
                         sk.setPlayer2(false);
                     }
                     if (keyboard.islPressed()) {
                         sk.setPlayer2(true);
                     }
                	
                } 
                
            }
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException ex) {
                Logger.getLogger(Pingpongextremsickoverloade.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void paint(Graphics g) {

    }

}
