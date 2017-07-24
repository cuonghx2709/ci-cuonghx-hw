package game.bases;

import game.enemies.EnemySpawer;
import game.players.Player;

import java.awt.*;
import java.util.Vector;

/**
 * Created by cuonghx2709 on 7/18/2017.
 */
public class GameObject {

    public Vector2D position;// vi tri tuong doi relative
    public Vector2D screenPosition;
    public Vector<GameObject> children;

    public ImageRenderer renderer;
    public BoxCollider boxCollider;

    private static Vector<GameObject> gameObjects = new Vector<>();
    public static  Vector<GameObject> newGameObject = new Vector<>();
    public static  Vector<GameObject> remote = new Vector<>();

    public static void add(GameObject gameObject){
        newGameObject.add(gameObject);
    }
    public static void renderAll(Graphics2D g2d){
        for (GameObject gameObject : gameObjects){
            gameObject.render(g2d, gameObject.position);
            for (GameObject child : gameObject.children){
                child.render(g2d, child.screenPosition);
            }
        }

    }
    public  static  void runAll(){
        for (GameObject gameObject : gameObjects){
            gameObject.run(Vector2D.ZENO);
        }
        gameObjects.addAll(newGameObject);
        newGameObject.clear();
        gameObjects.removeAll(remote);
        remote.clear();

    }

    public void run(Vector2D parentPosition){
        this.screenPosition = parentPosition.add(position);

        for (GameObject child : children){
            child.run(screenPosition);
        }
    }
    public void render(Graphics2D g2d, Vector2D position){
        if(renderer != null){
            renderer.render(g2d, position);
        }
    }
    public GameObject(){
        this.position = new Vector2D();
        this.renderer = null;
        this.screenPosition = new Vector2D();
        this.children = new Vector<>();
    }
}
