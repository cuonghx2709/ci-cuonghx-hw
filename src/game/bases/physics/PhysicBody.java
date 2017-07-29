package game.bases.physics;

import game.bases.BoxCollider;

/**
 * Created by cuonghx2709 on 7/26/2017.
 */
public interface PhysicBody {
    BoxCollider getBoxCollider();
    boolean isActive();
}
