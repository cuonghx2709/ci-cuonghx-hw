package game.bases;

import java.awt.event.KeyEvent;

/**
 * Created by cuonghx2709 on 7/19/2017.
 */
public class InPutManager {
    public boolean upPress;
    public boolean downPress;
    public boolean rightPress;
    public boolean leftPress;
    public boolean xPress;
    public boolean shiftPressed;

    public void KeyPressed(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_RIGHT:
                rightPress = true;
                break;
            case KeyEvent.VK_LEFT:
                leftPress = true;
                break;
            case KeyEvent.VK_UP:
                upPress = true;
                break;
            case KeyEvent.VK_DOWN:
                downPress = true;
                break;
            case KeyEvent.VK_X:
                xPress = true;
                break;
            case KeyEvent.VK_SHIFT:
                shiftPressed = true;
                break;
            default:
                break;
        }
    }
    public void KeyReleased (KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_RIGHT:
                rightPress = false;
                break;
            case KeyEvent.VK_LEFT:
                leftPress = false;
                break;
            case KeyEvent.VK_UP:
                upPress = false;
                break;
            case KeyEvent.VK_DOWN:
                downPress = false;
                break;
            case KeyEvent.VK_X:
                xPress = false;
                break;
            case KeyEvent.VK_SHIFT:
                shiftPressed = false;
                break;
            default:
                break;
        }
    }
}
