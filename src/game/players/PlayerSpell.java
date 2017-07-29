package game.players;

import game.Utils;
import game.bases.BoxCollider;
import game.bases.GameObject;
import game.bases.GameObjectPool;
import game.bases.effect.Effect;
import game.bases.renderers.Animation;
import game.bases.Vector2D;
import game.bases.physics.Physic;
import game.bases.physics.PhysicBody;
import game.enemies.BigExplosion;
import game.enemies.EnemyBlack;
import game.enemies.EnemyBlue;
import game.enemies.Explosion;
import game.item.Pow;


/**
 * Created by cuonghx2709 on 7/11/2017.
 */
public class PlayerSpell extends GameObject implements PhysicBody {

    BoxCollider boxCollider;


    public PlayerSpell()
    {
        relativePosition = new Vector2D();
        renderer = new Animation(
                Utils.Loadimage("assets/images/player-spells/a/0.png"),
                Utils.Loadimage("assets/images/player-spells/a/1.png"),
                Utils.Loadimage("assets/images/player-spells/a/2.png"),
                Utils.Loadimage("assets/images/player-spells/a/3.png")
        );
        this.boxCollider = new BoxCollider(20,20);
        boxCollider.relativePosition.set(0,0);
        this.children.add(boxCollider);



    }
    @Override
    public void run(Vector2D parentPosition){
        super.run(parentPosition);
        this.relativePosition.addUp(0, -10);
        hitEnemy();
        if (this.screenPosition.y < 0){
            this.isActive = false;
        }
    }

    private void hitEnemy() {
        EnemyBlue enemyBlue = Physic.bodyInRect(boxCollider, EnemyBlue.class);

        if (enemyBlue != null){
            this.isActive = false;
            enemyBlue.hp -= 1;

        }
        EnemyBlack enemyBlack = Physic.bodyInRect(boxCollider, EnemyBlack.class);
        if (enemyBlack != null){
            this.isActive = false;
            enemyBlack.hp -= 1;
        }

    }


    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}
