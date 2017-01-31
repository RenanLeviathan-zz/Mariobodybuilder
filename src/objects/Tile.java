/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author RenanDuarte
 */
public class Tile {
    private int x,y,width,height,frameX,frameY;
    private ImageIcon src;

    public Tile(int x, int y, int width, int height, int frameX, int frameY, ImageIcon src) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.frameX = frameX;
        this.frameY = frameY;
        this.src = src;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getFrameX() {
        return frameX;
    }

    public int getFrameY() {
        return frameY;
    }

    public Image getSrc() {
        return src.getImage();
    }
}
