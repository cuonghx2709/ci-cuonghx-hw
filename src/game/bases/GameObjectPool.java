package game.bases;

import java.util.Vector;

/**
 * Created by cuonghx2709 on 7/26/2017.
 */
public class GameObjectPool {
    private static Vector<GameObject> pool= new Vector<>();


    public static void add(GameObject gameObject){
        pool.add(gameObject);
    }
    public static<T extends GameObject> T recycle(Class<T> classz){
        for (GameObject gameObject : pool){
            if ((gameObject.getClass() == classz) && (!gameObject.isActive)){
                gameObject.isActive = true;
                return (T) gameObject;
            }
        }

        try {
            T newGameObject = classz.newInstance();
            GameObject.add(newGameObject);
            add(newGameObject);
            return newGameObject;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
