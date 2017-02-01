/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controls;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author RenanDuarte
 */
public class KeyEvents implements KeyListener {

    private static boolean up = false;
    private static boolean right = false;
    private static boolean left = false;
    private static boolean space=false;

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                up = true;
                break;
            case KeyEvent.VK_RIGHT:
                right = true;
                break;
            case KeyEvent.VK_LEFT:
                left = true;
                break;
            case KeyEvent.VK_SPACE:
                space=true;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                up = false;
                break;
            case KeyEvent.VK_RIGHT:
                right = false;
                break;
            case KeyEvent.VK_LEFT:
                left = false;
                break;
            case KeyEvent.VK_SPACE:
                space=false;
                break;
        }
    }

    public static boolean up_actived() {
        return up;
    }

    public static boolean right_actived() {
        return right;
    }

    public static boolean left_actived() {
        return left;
    }
    
    public static boolean space_actived(){
        return space;
    }
}
