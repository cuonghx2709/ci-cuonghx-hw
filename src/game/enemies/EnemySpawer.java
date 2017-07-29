package game.enemies;

import game.FrameCounter;
import game.bases.GameObject;
import game.bases.GameObjectPool;
import game.bases.Vector2D;
import game.bases.physics.Physic;

import java.util.Random;

/**
 * Created by cuonghx2709 on 7/18/2017.
 */
public class EnemySpawer extends GameObject {

    private int run = 0;

    FrameCounter counter;

    public EnemySpawer(){
        this.counter = new FrameCounter(10);
    }

    @Override
    public void run(Vector2D parentPosition) {
        run ++;
        if (run < 500) {
            if (counter.run()){
                EnemyBlue enemyBlue = GameObjectPool.recycle(EnemyBlue.class);
                enemyBlue.relativePosition.set( new Random().nextInt(400 - 20) + 20, 0);
                counter.reset();
            }
        }
        else if (run == 800){
                EnemyBlack enemy = GameObjectPool.recycle(EnemyBlack.class);
                enemy.relativePosition.set(200,0);
            }
            else if (run == 1200){
            EnemyBlack enemy = GameObjectPool.recycle(EnemyBlack.class);
            enemy.relativePosition.set(200,0);
        }
        else  if (run >= 1500 && Physic.find(EnemyBlack.class)== null ){
                run = 0;
        }
    }

}
