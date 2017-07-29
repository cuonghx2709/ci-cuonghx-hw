package game.enemies;

import game.FrameCounter;
import game.Utils;
import game.bases.*;
import game.bases.physics.PhysicBody;
import game.bases.renderers.Animation;
import game.bases.renderers.ImageRenderer;
import game.players.Player;

import java.util.Random;

/**
 * Created by cuonghx2709 on 7/27/2017.
 */
public class EnemyBlack extends GameObject implements PhysicBody{
    public Vector2D velocity;
    FrameCounter shootCounter;
    FrameCounter run;
    Contraints contraints;

    int count = 0;

    Random rd = new Random();
    BoxCollider boxCollider;

    public float hp = 10;


    public EnemyBlack() {
        super();
        this.renderer = new Animation(
                Utils.Loadimage("assets/images/enemies/level0/black/0.png"),
                Utils.Loadimage("assets/images/enemies/level0/black/1.png"),
                Utils.Loadimage("assets/images/enemies/level0/black/2.png"),
                Utils.Loadimage("assets/images/enemies/level0/black/4.png"),
                Utils.Loadimage("assets/images/enemies/level0/black/5.png"),
                Utils.Loadimage("assets/images/enemies/level0/black/6.png"),
                Utils.Loadimage("assets/images/enemies/level0/black/7.png"),
                Utils.Loadimage("assets/images/enemies/level0/black/8.png")
        );
        this.velocity = new Vector2D();
        this.shootCounter = new FrameCounter(15);
        this.boxCollider = new BoxCollider(30, 30);
        this.children.add(boxCollider);
        this.run = new FrameCounter(30);
        this.contraints = new Contraints(50,100,20,350);

    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        if (hp <= 0){
            this.isActive = false;
            BigExplosion bigExplosion = GameObjectPool.recycle(BigExplosion.class);
            bigExplosion.relativePosition.set(relativePosition);
            hp = 10;
        }

        if(relativePosition.y > 50){
            if (run.run()){
                run.reset();
                velocity.set(rd.nextInt(10)-5, rd.nextInt(10)-5);
            }
        }
        else {
            velocity.set(0,3);
        }

        this.relativePosition.addUp(velocity);
        contraints.make(relativePosition);

        if (shootCounter.run()) {
            this.shootCounter.reset();
            shoot();
            count ++;
            if (count >= 10){
                for (int j = 0; j < 360; j += 10) {
                    EnemyBullet enemiesBullet = GameObjectPool.recycle(EnemyBullet.class);
                    enemiesBullet.renderer = new ImageRenderer(Utils.Loadimage("assets/images/enemies/bullets/pink.png"));
                    enemiesBullet.velocity.setd(5 * Math.cos(Math.PI * j / 180), 5 * Math.sin(j * Math.PI / 180));
                    enemiesBullet.relativePosition.set(screenPosition);
                }
                count = 0;
            }
        }

    }

    private void shoot() {

        Vector2D target = Player.instance.relativePosition;

        Vector2D bulletVelocity = target.subtract(relativePosition).normdlize().multiply(5);

        EnemyBullet enemyBullet = GameObjectPool.recycle(EnemyBullet.class);
        enemyBullet.renderer = new ImageRenderer(Utils.Loadimage("assets/images/enemies/bullets/green.png"));
        if (count < 10 && count > 5){
            enemyBullet.renderer = new ImageRenderer(Utils.Loadimage("assets/images/enemies/bullets/yellow.png"));
        }
        enemyBullet.velocity.set(bulletVelocity);
        enemyBullet.relativePosition.set(screenPosition);
    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}
