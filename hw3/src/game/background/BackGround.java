package game.background;

import game.Utils;
import game.bases.GameObject;
import game.bases.ImageRenderer;
import game.bases.Vector2D;

import java.awt.*;

/**
 * Created by cuonghx2709 on 7/19/2017.
 */
public class BackGround extends GameObject{

    public Graphics2D graphics2D;

    public BackGround(){
        this.imageRenderer = new ImageRenderer(Utils.loadimage("assets/images/background/0.png"));
        this.position = new Vector2D();
    }
    @Override
    public void run() {
        if(position.y < imageRenderer.image.getHeight()/2){
            position.y += 5;
        }
    }
}
