package game.bases.physics;

import game.bases.BoxCollider;

import java.util.Vector;

/**
 * Created by cuonghx2709 on 7/26/2017.
 */
public class Physic {
    private static Vector<PhysicBody> bodies = new Vector<>();

    public static void add(PhysicBody physicBody){
        bodies.add(physicBody);
    }

    public static<T extends PhysicBody> T bodyInRect(BoxCollider boxCollider,  Class<T> classz){
        for (PhysicBody body : bodies){
            if (body.getBoxCollider().colliderwith(boxCollider)){
                if ((body.getClass() == classz) && body.isActive()){
                    return (T) body;
                }
            }
        }
        return null;
    }
    public static<T extends PhysicBody> T find(Class<T> classz){
        for (PhysicBody body : bodies){
            if (body.isActive() && (body.getClass() == classz)){
                return (T) body;
            }
        }
        return null;
    }
}
