package game.items.sphere.sphere;

import game.Utils;
import game.bases.BoxCollider;
import game.bases.GameObject;
import game.bases.ImageRenderer;
import game.bases.Vector2D;
import game.enemies.Enemy1;
import game.enemies.EnemySpawer;
import game.players.Player;
import game.screens.Setting;

import java.util.Random;

/**
 * Created by cuonghx2709 on 7/24/2017.
 */
public class SphereBullet extends GameObject{

     public Vector2D velocity;

     Random random = new Random();

     private Vector2D target;
     public SphereBullet(){

         this.renderer = new ImageRenderer(Utils.Loadimage("assets/images/sphere-bullets/0.png")) ;
         this.velocity =new Vector2D();
         this.boxCollider = new BoxCollider(20,20);
         children.add(boxCollider);
         this.target = new Vector2D(Player.instance.position.x, 0);
         for (int i = 0; i < EnemySpawer.Enemies.size();i++ ) {
             target = EnemySpawer.Enemies.get(random.nextInt(EnemySpawer.Enemies.size())).position;
         }

     }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        {
            Vector2D bulletVelocity = target.subtract(position).normdlize().multiply(Setting.speedBullet);

            this.velocity.set(bulletVelocity);
            this.position.addUp(velocity);
            for (GameObject enemy : EnemySpawer.Enemies) {
                if(enemy.boxCollider.collideWith(this.boxCollider)){
                    EnemySpawer.remoteEnemies.add(enemy);
                    remote.add(this);

                }
            }

        }

    }

}
