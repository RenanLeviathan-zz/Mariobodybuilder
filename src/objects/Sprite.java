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
public class Sprite {
    private int x,y,width,height,fx,fy;
    private ImageIcon src;
    
    public Sprite(int x, int y, int width, int height, int frame_x, int frame_y, ImageIcon src){
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        this.fx=frame_x;
        this.fy=frame_y;
        this.src=src;
    }
    
    public void move(int x, int y){
        this.x=x;
        this.y=y;
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

    public Image getSrc() {
        return src.getImage();
    }
    
    public void setFramePosition(int fx, int fy){
        this.fx=fx;
        this.fy=fy;
    }
    
    public void animar(int f_max){
        if(fx<f_max){
            fx+=width;
        }else{
            fx=0;
        }
    }
    
    public void parar(){
        fx=0;
    }
    
    public int getFx(){
        return this.fx;
    }
    
    public int getFy(){
        return this.fy;
    }
    
}
