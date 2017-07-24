package game.inputs;

import java.awt.event.KeyEvent;

/**
 * Created by cuonghx2709 on 7/18/2017.
 */
public class InputManger {
    public boolean leftPressed;
    public boolean rightPressed;
    public boolean downPressed;
    public boolean upPressed;
    public  boolean xPressed;

    public void keyPress(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_RIGHT:
                rightPressed = true;
                break;
            case KeyEvent.VK_LEFT:
                leftPressed = true;
                break;
            case KeyEvent.VK_DOWN:
                downPressed = true;
                break;
            case KeyEvent.VK_UP:
                upPressed = true;
                break;
            case KeyEvent.VK_X:
                xPressed = true;
            default:
                break;
        }
    }
    public void keyReleased(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_RIGHT:
                rightPressed = false;
                break;
            case KeyEvent.VK_LEFT:
                leftPressed = false;
                break;
            case KeyEvent.VK_DOWN:
                downPressed = false;
                break;
            case KeyEvent.VK_UP:
                upPressed = false;
                break;
            case KeyEvent.VK_X:
                xPressed = false;
            default:
                break;
        }

    }
}
