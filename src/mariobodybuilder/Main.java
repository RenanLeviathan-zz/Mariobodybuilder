/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mariobodybuilder;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import objects.Sprite;
import objects.Tile;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author RenanDuarte
 */
public class Main extends JFrame implements Runnable{

    private Sprite mario;
    private ArrayList<Tile> platform;
    private int stage;
    private static final int LARGURA=500,ALTURA=500;
    private static final int FPS = 30;
    private BufferedImage buffer;
    private ImageIcon fundo;
    
    public Main(){
        setSize(LARGURA,ALTURA);
        setTitle("Mario bodybuilder");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    //fases
    public void fase1(){
        Graphics g = getGraphics();
        Graphics b = buffer.getGraphics();
        
    }
    
    //métodos do sistema
    public void render(){
        fase1();
    }
    
    public void draw(Graphics g, Sprite s){
        g.drawImage(s.getSrc(), s.getX(), s.getY(), s.getX()+s.getWidth(), 
                s.getY()+s.getHeight(), s.getFx(), s.getFy(), 
                s.getFx()+s.getWidth(), s.getFy()+s.getHeight(), this);
    }
    
    public void draw(Graphics g, Tile t){
        g.drawImage(t.getSrc(), t.getX(), t.getY(), t.getX()+t.getWidth(), 
                t.getY()+t.getHeight(), t.getFrameX(), t.getFrameY(), 
                t.getFrameX()+t.getWidth(), t.getFrameY()+t.getHeight(), this);
    }
    
    public void load(){
        buffer = new BufferedImage(LARGURA, ALTURA, BufferedImage.TYPE_INT_RGB);
        //mario = new Sprite();
        platform = new ArrayList<>();
        fundo = new ImageIcon("res/");
    }
    
    public void update(){
        
    }
    
    @Override
    public void run() {
        load();
        while(true){
            render();
            update();
            try{
                Thread.sleep(1000/FPS);
            }catch(InterruptedException ex){
                ex.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) {
        new Main().run();
    }
    
}
