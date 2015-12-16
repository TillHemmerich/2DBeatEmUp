/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2dbeatemup.level;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author phili
 */
public class LevelParser {

    /*
    Level-Dateien werden nach folgendem Schema benannt: "level"[Nummer des Levels].txt
    
    Levelsymbole:
    '#' markiert den linken und rechten Rand
    '_' leer
    'E' Terrain
    
     */
    //level als 2D Array
    private char[][] level;
    //level id
    private int id;
    private File file;
    BufferedReader reader = null;

    public LevelParser(int id) {
        this.id = id;
        readLevel(id);
    }

    public char getLevelSymbolAt(int x, int y) {
        return level[y][x];
    }

    public int getLevelWidth() {
        return level[0].length;
    }

    public int getLevelHeight() {
        return level.length;
    }

//lädt level von Datei in level-Array
    private void readLevel(int id) {
        try {
            file = new File("Resources/level/level" + id + ".txt");
            reader = new BufferedReader(new FileReader(file));
            String line;

            ArrayList<String> tmpList = new ArrayList();
            while ((line = reader.readLine()) != null) {
                tmpList.add(line.replace(" ", ""));
            }
            level = new char[tmpList.size()][tmpList.get(0).length()];
            for (int i = 0; i < tmpList.size(); i++) {
                for (int j = 0; j < tmpList.get(i).length(); j++) {
                    level[i][j] = tmpList.get(i).charAt(j);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
