package game.enemies;

import game.FrameCounter;
import game.Utils;
import game.bases.Contrains;
import game.bases.GameObject;
import game.bases.ImageRenderer;
import game.bases.Vector2D;
import java.util.Random;

/**
 * Created by cuonghx2709 on 7/12/2017.
 */
public class EnemySpawner extends GameObject {

    private FrameCounter cooldownbullet;
    private boolean bulletdisable;
    private int i = 0;
    private int status = 1;
    private int cooldown = 4;
    private Random random = new Random();
    private Vector2D velocity;
    private Contrains contrains;


    public EnemySpawner() {
        this.position = new Vector2D();
        this.imageRenderer = new ImageRenderer(Utils.loadimage("assets/images/enemies/level0/black/0.png"));
        this.position.set(random.nextInt(350) + 25, 0);
        this.velocity = new Vector2D();
        this.contrains = new Contrains(100, 200, 25, 375);
        this.cooldownbullet = new FrameCounter(50);
    }
    public void run() {
        move();
        shootbullet();

    }

    private void shootbullet() {
        if (!bulletdisable&&position.y > 50) {
            if (status == 2) {
                EnemiesBullet enemiesBullet = new EnemiesBullet();
                enemiesBullet.setMove(5 * Math.cos(Math.PI * i / 180), 5 * Math.sin(i * Math.PI / 180));
                enemiesBullet.position.set(position.x, position.y);
                newgameObject.add(enemiesBullet);
                i += 5;
                if (i > 180) {
                    i = 0;
                    cooldownbullet = new FrameCounter(50);
                    status = 1;
                }
            } else {
                for (int j = 0; j < 360; j += 10) {
                    EnemiesBullet enemiesBullet = new EnemiesBullet();
                    enemiesBullet.setMove(7 * Math.cos(Math.PI * j / 180), 7 * Math.sin(j * Math.PI / 180));
                    enemiesBullet.position.set(position.x, position.y);
                    newgameObject.add(enemiesBullet);
                }
                if (cooldown < 0) {
                    cooldown = 10;
                    cooldownbullet = new FrameCounter(2);
                    status = 2;
                }
            }
            bulletdisable = true;
            cooldown--;


        } else {
            if (cooldownbullet.run()) {
                bulletdisable = false;
                cooldownbullet.reset();
            }
        }
    }

    private void move() {
        if (position.y < 99) {
            velocity.y = 3;
            position.addUp(velocity);
        }else {
            if(cooldown < 0){
                velocity.set(random.nextInt(10)-5, random.nextInt(10)-5);
                cooldown = 5;
            }
            else {
                cooldown --;
            }
            position.addUp(velocity);
            contrains.run(position);

        }

    }
}