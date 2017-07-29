package game.item.sphere;

import game.Utils;
import game.bases.BoxCollider;
import game.bases.GameObject;
import game.bases.Vector2D;
import game.bases.physics.Physic;
import game.bases.physics.PhysicBody;
import game.bases.renderers.Animation;
import game.enemies.EnemyBlack;
import game.enemies.EnemyBlue;
import game.players.Player;

/**
 * Created by cuonghx2709 on 7/26/2017.
 */
public class SphereBullet extends GameObject implements PhysicBody{

    private BoxCollider boxCollider;
    private Vector2D velocity;
    private Vector2D target;

    public SphereBullet(){
        super();
        this.renderer = new Animation(
                Utils.Loadimage("assets/images/sphere-bullets/0.png"),
                Utils.Loadimage("assets/images/sphere-bullets/1.png"),
                Utils.Loadimage("assets/images/sphere-bullets/2.png"),
                Utils.Loadimage("assets/images/sphere-bullets/3.png")
        );
        boxCollider = new BoxCollider(20,20);
        boxCollider.relativePosition.set(0,0);
        children.add(boxCollider);

        this.velocity = new Vector2D();

    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        move();
        hitEnemy();
    }

    private void hitEnemy() {
        EnemyBlue enemy = Physic.bodyInRect(boxCollider, EnemyBlue.class);
        if (enemy!= null){
            this.isActive = false;
            enemy.hp -= 0.5f;
        }
        EnemyBlack enemyBlack = Physic.bodyInRect(boxCollider, EnemyBlack.class);
        if (enemyBlack!= null){
            this.isActive = false;
            enemyBlack.hp -= 0.5f;
        }

    }

    private void move() {
        GameObject enemy = Physic.find(EnemyBlack.class);
        if (enemy == null){
             enemy = Physic.find(EnemyBlue.class);
        }
        if (enemy != null){
            this.target = enemy.relativePosition;
        }
        else {
            target = new Vector2D(Player.instance.relativePosition.x, 0);
        }
        velocity = target.subtract(relativePosition).normdlize().multiply(6);
        relativePosition.addUp(velocity);
        if (relativePosition.y < 0){
            this.isActive = false;
        }
    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}
