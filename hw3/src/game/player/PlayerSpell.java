package game.player;

import game.Utils;
import game.bases.CheckImage;
import game.bases.GameObject;
import game.bases.ImageRenderer;
import game.bases.Vector2D;
import game.enemies.EnemySpawner;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by cuonghx2709 on 7/12/2017.
 */
public class PlayerSpell extends GameObject {

    public PlayerSpell(){
        this.position = new Vector2D();
        this.imageRenderer = new ImageRenderer(Utils.Loadimage("assets/images/player-spells/a/1.png"));
    }

    public void run(){
        position.addUp(0, -10);
        boolean status = CheckImage.run(position, this.imageRenderer.image.getWidth()-14, this.imageRenderer.image.getHeight()-6, enemySpawner.position, enemySpawner.imageRenderer.image.getWidth(), enemySpawner.imageRenderer.image.getHeight());
        if(status){
            remote.add(enemySpawner);
            enemySpawner = new EnemySpawner();
            newgameObject.add(enemySpawner);
        }
    }

    public void render(Graphics2D graphics2D){
        imageRenderer.render(graphics2D, position);
    }
}
