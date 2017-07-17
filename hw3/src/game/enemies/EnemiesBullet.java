package game.enemies;

import game.Utils;
import game.bases.ImageRenderer;
import game.bases.Vector2D;

import java.awt.*;

/**
 * Created by cuonghx2709 on 7/17/2017.
 */
public class EnemiesBullet {
    public Vector2D position;
    public ImageRenderer imageRenderer;
    public float dx;
    public float dy;
    public EnemiesBullet(){
        this.position = new Vector2D();
        this.imageRenderer = new ImageRenderer(Utils.Loadimage("assets/images/enemies/bullets/blue.png"));
    }

    public void setMove(double dx, double dy) {
        this.dx = (float) dx;
        this.dy = (float) dy;
    }

    public void move(){
        position.addUp(dx, dy);
    }
    public  void render(Graphics2D g2d){
        imageRenderer.render(g2d, position);
    }
}
