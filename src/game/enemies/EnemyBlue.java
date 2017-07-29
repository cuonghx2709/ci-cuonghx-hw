package game.enemies;

import game.FrameCounter;
import game.Utils;
import game.bases.*;
import game.bases.effect.Effect;
import game.bases.physics.PhysicBody;
import game.bases.renderers.Animation;
import game.bases.renderers.ImageRenderer;
import game.item.Pow;
import game.players.Player;
import tklibs.AudioUtils;

import javax.sound.sampled.Clip;

/**
 * Created by cuonghx2709 on 7/23/2017.
 */
public class EnemyBlue extends GameObject implements PhysicBody{
    public Vector2D velocity;
    FrameCounter shootCounter;

    BoxCollider boxCollider;
    public float hp = 2;

    Effect enemyExplosion;
    public EnemyBlue(){
        super();
        renderer = new Animation(
                Utils.Loadimage("assets/images/enemies/level0/blue/0.png"),
                Utils.Loadimage("assets/images/enemies/level0/blue/1.png"),
                Utils.Loadimage("assets/images/enemies/level0/blue/2.png"),
                Utils.Loadimage("assets/images/enemies/level0/blue/3.png")
        );
        this.velocity = new Vector2D();
        this.shootCounter = new FrameCounter(50);
        this.boxCollider = new BoxCollider(20,20);
        this.boxCollider.relativePosition.set(0,0);

        enemyExplosion = new Effect("assets/music/sfx/enemy-explosion.wav");
        children.add(boxCollider);
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        if (relativePosition.y < 350){
            velocity.set(0,2);
        }
        else {
            velocity.set(-2,0);
        }
        if (relativePosition.x < 50){
            velocity.set(0,-3);
        }
        this.relativePosition.addUp(velocity);

        if(shootCounter.run()) {
            this.shootCounter.reset();
            shoot();
        }
        if (screenPosition.y > 800){
            isActive = false;
        }
        if (hp <= 0){
            this.isActive = false;
            this.hp = 2;
            Explosion explosion = GameObjectPool.recycle(Explosion.class);
            explosion.relativePosition.set(this.relativePosition);
            Pow pow =GameObjectPool.recycle(Pow.class);
            pow.relativePosition.set(this.relativePosition);
            enemyExplosion.play();
        }
    }

    private void shoot() {

        Vector2D target = Player.instance.relativePosition;

        Vector2D bulletVelocity = target.subtract(relativePosition).normdlize().multiply(5);

        EnemyBullet enemyBullet = GameObjectPool.recycle(EnemyBullet.class);
        enemyBullet.renderer = new ImageRenderer(Utils.Loadimage("assets/images/enemies/bullets/red.png"));
        enemyBullet.velocity.set(bulletVelocity);
        enemyBullet.relativePosition.set(this.relativePosition);
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }
}
