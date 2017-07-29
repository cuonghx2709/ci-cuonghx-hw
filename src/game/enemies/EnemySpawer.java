package game.enemies;

import game.FrameCounter;
import game.bases.GameObject;
import game.bases.GameObjectPool;
import game.bases.Vector2D;

/**
 * Created by cuonghx2709 on 7/18/2017.
 */
public class EnemySpawer extends GameObject {

    private int run = 0;

    FrameCounter counter;

    public EnemySpawer(){
        this.counter = new FrameCounter(50);
    }

    @Override
    public void run(Vector2D parentPosition) {
        run ++;
        if (run < 500) {
            if (counter.run()) {
                EnemyBlue enemy = GameObjectPool.recycle(EnemyBlue.class);
                enemy.relativePosition.set(350, 0);
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
        else  if (run == 1500){
                run = 0;
        }
    }

}
