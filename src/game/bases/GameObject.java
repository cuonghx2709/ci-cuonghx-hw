package game.bases;





import game.bases.physics.Physic;
import game.bases.physics.PhysicBody;
import game.bases.renderers.ImageRenderer;
import game.bases.renderers.Renderer;
import game.item.sphere.Sphere;
import game.item.sphere.SphereBullet;

import java.awt.*;
import java.util.Vector;

/**
 * Created by cuonghx2709 on 7/18/2017.
 */
public class GameObject {

    public Vector2D relativePosition;// vi tri tuong doi relative
    public Vector2D screenPosition;
    public Vector<GameObject> children;

    public Renderer renderer;

    public boolean isActive;


    private static Vector<GameObject> gameObjects = new Vector<>();
    private static  Vector<GameObject> newGameObject = new Vector<>();


    public static void add(GameObject gameObject){
        newGameObject.add(gameObject);
        if (gameObject instanceof PhysicBody){
            Physic.add((PhysicBody) gameObject);
        }

    }
    public static void renderAll(Graphics2D g2d){
        for (GameObject gameObject : gameObjects){
          if (gameObject.isActive){
                gameObject.render(g2d);
                for (GameObject gO : gameObject.children){
                    if (gO instanceof Sphere){
                        gO.render(g2d);
                    }
                }
            }
        }
    }
    public  static  void runAll(){
        for (GameObject gameObject : gameObjects){
            if (gameObject.isActive) {
                gameObject.run(Vector2D.ZENO);
            }

        }
        gameObjects.addAll(newGameObject);
        newGameObject.clear();
    }

    public void run(Vector2D parentPosition){
        this.screenPosition = parentPosition.add(relativePosition);

        for (GameObject child : children){
            child.run(screenPosition);
        }

    }
    public void render(Graphics2D g2d){
        if(renderer != null){
            renderer.render(g2d, screenPosition);
        }
    }
    public GameObject(){
        this.relativePosition = new Vector2D();
        this.renderer = null;
        this.screenPosition = new Vector2D();
        this.children = new Vector<>();
        this.isActive = true;
    }

    public boolean isActive(){
        return this.isActive;
    }

}
