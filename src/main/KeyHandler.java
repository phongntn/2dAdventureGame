package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    GamePanel gp;
    public int up,  down, left, right;
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    //Debug
    boolean checkDrawTime = false;

    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = true;
            up = 1;
        }

        if (code == KeyEvent.VK_S) {
            downPressed = true;
            down = 1;
        }

        if (code == KeyEvent.VK_A) {
            leftPressed = true;
            left = 1;
        }

        if (code == KeyEvent.VK_D) {
            rightPressed = true;
            right = 1;
        }

        if (code == KeyEvent.VK_P) {
            if(gp.gameState == gp.playState){
                gp.gameState = gp.pauseState;
            }
            else if (gp.gameState == gp.pauseState) {
                gp.gameState = gp.playState;
            }
        }

        //Debug
        if(code == KeyEvent.VK_T){
            if(checkDrawTime == false){
                checkDrawTime = true;
            }
            else if(checkDrawTime == true){
                checkDrawTime = false;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = false;
            up = 0;
        }

        if (code == KeyEvent.VK_S) {
            downPressed = false;
            down = 0;
        }

        if (code == KeyEvent.VK_A) {
            leftPressed = false;
            left = 0;
        }

        if (code == KeyEvent.VK_D) {
            rightPressed = false;
            right = 0;
        }
    }

    public int keyCount() {
        return up + down + left + right;
    }
}
