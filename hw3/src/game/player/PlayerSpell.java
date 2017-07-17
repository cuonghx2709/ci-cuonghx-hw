package game.player;

import game.Utils;
import game.bases.ImageRenderer;
import game.bases.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by cuonghx2709 on 7/12/2017.
 */
public class PlayerSpell {
    public Vector2D position;

    public PlayerSpell(){
        this.position = new Vector2D();
        this.imageRenderer = new ImageRenderer(Utils.Loadimage("assets/images/player-spells/a/1.png"));
    }

    public ImageRenderer imageRenderer;

    public void move(){
        position.addUp(0, -10);
    }
    public void render(Graphics2D graphics2D){
        imageRenderer.render(graphics2D, position);
    }
}
