package game.players;

import game.Utils;
import game.bases.BoxCollider;
import game.bases.GameObject;
import game.bases.ImageRenderer;
import game.bases.Vector2D;
import game.enemies.Enemy1;
import game.enemies.EnemySpawer;
import game.screens.Setting;

import java.awt.*;

/**
 * Created by cuonghx2709 on 7/11/2017.
 */
public class PlayerSpell extends GameObject {

    public PlayerSpell()
    {
        position = new Vector2D();
        renderer = new ImageRenderer(Utils.Loadimage("assets/images/player-spells/a/1.png"));
        this.boxCollider = new BoxCollider(20,20);
            children.add(boxCollider);
    }
    @Override
    public void run(Vector2D parentPosition){
        super.run(parentPosition);
        this.position.addUp(0, -Setting.speedBullet);

        for (GameObject enemy : EnemySpawer.Enemies) {
            if(enemy.boxCollider.collideWith(this.boxCollider)){
                remote.add(this);
            }
        }
    }

}
