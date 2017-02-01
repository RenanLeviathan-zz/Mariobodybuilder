/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mariobodybuilder;

import controls.KeyEvents;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.JFrame;
import objects.Sprite;
import objects.Tile;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import objects.CBox;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import xml.XMLParser;

/**
 *
 * @author RenanDuarte
 */
public class Main extends JFrame implements Runnable {

    private Sprite mario;
    private ArrayList<Tile> platform;
    private int stage;
    private CBox floor;
    private static final int LARGURA = 500, ALTURA = 500;
    private static final int FPS = 30;
    private int bg_scroll = 0;
    private boolean onPlat = true,moving=false;
    private BufferedImage buffer;
    private int GRAVITY = 3;
    private int x = 0, y, xspeed = 2, yspeed = 2, vel = 0;
    private ImageIcon fundo;

    public Main() {
        setSize(LARGURA, ALTURA);
        setTitle("Mario bodybuilder");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    //fases
    public void fase1() {
        Graphics g = getGraphics();
        Graphics b = buffer.getGraphics();
        b.drawImage(fundo.getImage(), bg_scroll-2, 0, this);
        draw(b, mario);
        platform.stream().forEach((t) -> {
            draw(b, t, bg_scroll);
        });
        g.drawImage(buffer, 0, 0, this);
    }

    //métodos do sistema
    public void render() {
        fase1();
    }

    public void draw(Graphics g, Sprite s) {
        g.drawImage(s.getSrc(), s.getX(), s.getY(), s.getX() + s.getWidth(),
                s.getY() + s.getHeight(), s.getFx(), s.getFy(),
                s.getFx() + s.getWidth(), s.getFy() + s.getHeight(), this);
    }

    public void draw(Graphics g, Tile t, int bg_scroll) {
        g.drawImage(t.getSrc(), t.getX() + bg_scroll, t.getY(), t.getX() + t.getWidth() + bg_scroll,
                t.getY() + t.getHeight(), t.getFrameX(), t.getFrameY(),
                t.getFrameX() + t.getWidth(), t.getFrameY() + t.getHeight(), this);
    }

    public void load() {
        buffer = new BufferedImage(LARGURA, ALTURA, BufferedImage.TYPE_INT_RGB);
        mario = new Sprite(30, 404, 32, 64, 0, 0, new ImageIcon("res/mariobuilder1.png"));
        y = mario.getY();
        platform = new ArrayList<>();
        Document doc = XMLParser.parseFile(new File("res/xml/tiles.xml"));
        NodeList tiles = doc.getElementsByTagName("tile");
        floor = new CBox(0, 468, 320, 32);
        for (int i = 0; i < tiles.getLength(); i++) {
            Element e = (Element) tiles.item(i);
            int X = Integer.parseInt(e.getAttribute("x"));
            int Y = Integer.parseInt(e.getAttribute("y"));
            int width = Integer.parseInt(e.getAttribute("width"));
            int height = Integer.parseInt(e.getAttribute("height"));
            int f_x = Integer.parseInt(e.getAttribute("f_x"));
            int f_y = Integer.parseInt(e.getAttribute("f_y"));
            String src = e.getAttribute("src");
            Tile tl = new Tile(X, Y, width, height, f_x, f_y, new ImageIcon(src));
            platform.add(tl);
        }
        fundo = new ImageIcon("res/background.png");
        addKeyListener(new KeyEvents());
    }

    public boolean colidiu(Sprite s, CBox cb) {
        boolean top = (s.getY() + s.getHeight() >= cb.getY())
                && (s.getX() > cb.getX())
                && (s.getX() + s.getWidth() < cb.getX() + cb.getWidth());
        return top;
    }

    public void update() {
        if(KeyEvents.up_actived()&&colidiu(mario,floor)){
            y-=yspeed;
            moving=true;
        }
        if (KeyEvents.right_actived()) {
            vel=1;
            if(mario.getX()>250){
                bg_scroll-=5;
            }
            moving=true;
        }
        if(KeyEvents.left_actived()){
            vel=-1;
            moving=true;
        }
        if(!KeyEvents.left_actived()&&!KeyEvents.right_actived()){
            vel=0;
            moving=false;
        }
        if(moving){
            xspeed=vel*5;
            x+=xspeed;
            mario.move(x, y);
            mario.animar(64);
        }else{
            mario.parar();
        }
    }

    @Override
    public void run() {
        load();
        while (true) {
            render();
            update();
            try {
                Thread.sleep(1000 / FPS);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Main().run();
    }

}
