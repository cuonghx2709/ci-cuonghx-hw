package game.enemies;

import game.FrameCounter;
import game.Utils;
import game.bases.BoxCollider;
import game.bases.GameObject;
import game.bases.ImageRenderer;
import game.bases.Vector2D;
import game.players.Player;
import game.screens.Setting;

/**
 * Created by cuonghx2709 on 7/23/2017.
 */
public class Enemy1 extends GameObject {
    public Vector2D velocity;
    FrameCounter shootCounter;


    public Enemy1(){
        super();
        this.renderer = new ImageRenderer(Utils.Loadimage("assets/images/enemies/level0/blue/0.png"));
        this.velocity = new Vector2D();
        this.shootCounter = new FrameCounter(50);
        this.boxCollider = new BoxCollider(30,30);
        this.children.add(boxCollider);
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        velocity.y = 2;
        if (position.y > 400){
            velocity.set(-2,0);
        }
        if(position.x < 50){
            velocity.set(0,-5);
        }
        this.position.addUp(velocity);

        if(shootCounter.run()){
            this.shootCounter.reset();
            shoot();
        }
        if (position.y < 0){
            EnemySpawer.Enemies.remove(this);
            remote.add(this);
        }

    }

    private void shoot() {

        Vector2D target = Player.instance.position;

        Vector2D bulletVelocity = target.subtract(position).normdlize().multiply(Setting.speedBullet);

        EnemyBullet enemyBullet = new EnemyBullet();
        enemyBullet.velocity.set(bulletVelocity);
        enemyBullet.renderer = new ImageRenderer(Utils.Loadimage("assets/images/enemies/bullets/red.png"));
        enemyBullet.position.set(this.position);
        GameObject.add(enemyBullet);
    }
}
