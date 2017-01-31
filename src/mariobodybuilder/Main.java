/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mariobodybuilder;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.JFrame;
import objects.Sprite;
import objects.Tile;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import xml.XMLParser;

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
        b.drawImage(fundo.getImage(), 0, 0, this);
        platform.stream().forEach((t) -> {
            draw(b,t);
        });
        g.drawImage(buffer, 0, 0, this);
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
        Document doc = XMLParser.parseFile(new File("res/xml/tiles.xml"));
        NodeList tiles = doc.getElementsByTagName("tile");
        for(int i=0;i<tiles.getLength();i++){
            Element e = (Element) tiles.item(i);
            int x = Integer.parseInt(e.getAttribute("x"));
            int y = Integer.parseInt(e.getAttribute("y"));
            int width = Integer.parseInt(e.getAttribute("width"));
            int height = Integer.parseInt(e.getAttribute("height"));
            int f_x = Integer.parseInt(e.getAttribute("f_x"));
            int f_y = Integer.parseInt(e.getAttribute("f_y"));
            String src = e.getAttribute("src");
            Tile tl = new Tile(x, y, width, height, f_x, f_y, new ImageIcon(src));
            platform.add(tl);
        }
        fundo = new ImageIcon("res/background.png");
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
