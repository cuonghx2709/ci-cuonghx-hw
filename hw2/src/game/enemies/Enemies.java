package game.enemies;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Created by cuonghx2709 on 7/12/2017.
 */
public class Enemies {
    public int x;
    public int y = 0;
    public  int dx;
    public BufferedImage image;

    public void  rand(int Width){
        Random random = new Random();
         x = random.nextInt(Width);
         dx = random.nextInt(10)-5;
    }

    public void move(){
        x += dx;
        y += 10;
    }
    public void render(Graphics2D g2d){
        g2d.drawImage(image, x, y, null);
    }
}
