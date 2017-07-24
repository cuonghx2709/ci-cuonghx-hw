package game.enemies;

import game.FrameCounter;
import game.Utils;
import game.bases.*;
import game.players.Player;

import java.util.Random;

/**
 * Created by cuonghx2709 on 7/23/2017.
 */
public class Enemy2 extends GameObject {
    public Vector2D velocity;
    FrameCounter shootCounter;
    FrameCounter run;
    Contraints contraints;

    int count = 0;

    Random rd = new Random();


    public Enemy2() {
        super();
        this.renderer = new ImageRenderer(Utils.Loadimage("assets/images/enemies/level0/black/0.png"));
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

        if(position.y > 50){
            if (run.run()){
                run.reset();
                velocity.set(rd.nextInt(10)-5, rd.nextInt(10)-5);
            }
        }
        else {
            velocity.set(0,3);
        }

        this.position.addUp(velocity);
        contraints.make(position);

        if (shootCounter.run()) {
            this.shootCounter.reset();
            shoot();
            count ++;
            if (count >= 10){
                for (int j = 0; j < 360; j += 10) {
                    EnemyBullet enemiesBullet = new EnemyBullet();
                    enemiesBullet.renderer = new ImageRenderer(Utils.Loadimage("assets/images/enemies/bullets/pink.png"));
                    enemiesBullet.velocity.setd(7 * Math.cos(Math.PI * j / 180), 7 * Math.sin(j * Math.PI / 180));
                    enemiesBullet.position.set(position.x, position.y);
                    GameObject.add(enemiesBullet);
                }
                count = 0;
            }
        }

    }

    private void shoot() {

        Vector2D target = Player.instance.position;

        Vector2D bulletVelocity = target.subtract(position).normdlize().multiply(6);

        EnemyBullet enemyBullet = new EnemyBullet();
        enemyBullet.velocity.set(bulletVelocity);
        enemyBullet.position.set(this.position);
        GameObject.add(enemyBullet);
    }
}