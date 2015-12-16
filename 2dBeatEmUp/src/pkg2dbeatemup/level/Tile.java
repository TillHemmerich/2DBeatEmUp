/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2dbeatemup.level;

import pkg2dbeatemup.BaseObject;

/**
 *
 * @author phili
 */
public class Tile extends BaseObject {

    private char type;

    public Tile(char type, int x, int y, int width, int height) {
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
        this.type = type;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

}
