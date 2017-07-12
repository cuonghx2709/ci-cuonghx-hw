package game.player;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by cuonghx2709 on 7/12/2017.
 */
public class PlayerSpell {
    public int x ;
    public int y ;
    public BufferedImage image;

    public void move(){
        y -=10;
    }
    public void render(Graphics2D graphics2D){
        graphics2D.drawImage( image, x, y, null);
    }
}
