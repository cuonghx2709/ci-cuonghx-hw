package game.enemies;

import game.FrameCounter;
import game.bases.GameObject;
import game.bases.Vector2D;

import java.util.Vector;

/**
 * Created by cuonghx2709 on 7/18/2017.
 */
public class EnemySpawer extends GameObject {

    FrameCounter counter;
    int s = 0;

    public static Vector<GameObject> Enemies = new Vector<>();
    public static Vector<GameObject> remoteEnemies = new Vector<>();


    @Override
    public void run(Vector2D parentPosition) {
        if (s < 10){
            if (counter.run()){
                counter.reset();
                Enemy1 enemyl = new Enemy1();
                enemyl.position.set(320, 0);
                GameObject.add(enemyl);
                Enemies.add(enemyl);
                s++;
            }
            if(s == 10){
                counter = new FrameCounter(50);
            }
        }else if (s==100){
            if(counter.run()){
                Enemy2 enemy2 = new Enemy2();
                enemy2.position.set(200,0);
                GameObject.add(enemy2);
                Enemies.add(enemy2);
                s++;
            }

        }else if (s == 1000){
            if(counter.run()){
                Enemy2 enemy2 = new Enemy2();
                enemy2.position.set(200,0);
                GameObject.add(enemy2);
                Enemies.add(enemy2);
                s = 0;
            }
        }
        else s++;
    }

    public EnemySpawer(){
        this.counter = new FrameCounter(40);
    }
}
