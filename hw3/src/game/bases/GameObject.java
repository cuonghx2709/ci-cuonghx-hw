package game.bases;

import game.background.BackGround;
import game.enemies.EnemySpawner;
import game.player.Player;

import java.awt.*;
import java.util.Vector;

/**
 * Created by cuonghx2709 on 7/19/2017.
 */
public class GameObject {
    public Vector2D position;
    public ImageRenderer imageRenderer;

    public static  BackGround buffBackground = new BackGround();

    private static Vector<GameObject> gameObjects = new Vector<>();
    public static  Vector<GameObject> newgameObject = new Vector<>();
    public static Vector<GameObject> remote = new Vector<>();
    public static Player newplayer;
    public static EnemySpawner enemySpawner;
    public static int life = 1000;
    public static int pow = 0;

    public GameObject(){
        this.position = new Vector2D();
        this.imageRenderer = null;
    }

    public void run(){

    }

    public void render(Graphics2D g2d){
        if (imageRenderer!= null)
        imageRenderer.render(g2d, position);
    }

    public static void renderAll(Graphics2D g2d){

        buffBackground.graphics2D.setColor(Color.BLACK);
        buffBackground.graphics2D.fillRect(0, 0, 800, 800);

        for (GameObject gameObject : gameObjects){
            gameObject.render(buffBackground.graphics2D);
        }
        buffBackground.graphics2D.setColor(Color.GREEN);
        buffBackground.graphics2D.drawString("life : " + life, 400, 200);
        buffBackground.graphics2D.drawString(String.format("pow : %s / 3",pow), 400, 250);
        buffBackground.graphics2D.drawString("fps : 60", 400, 300);
        buffBackground.imageRenderer.render(g2d,GameObject.buffBackground.position);

    }

    public static void add(GameObject gameObject){
        gameObjects.add(gameObject);
    }

    public static void runAll(){
        for (GameObject gameObject:gameObjects){
            gameObject.run();
        }
        gameObjects.addAll(newgameObject);
        gameObjects.removeAll(remote);
        newgameObject.clear();
        remote.clear();
    }
}
