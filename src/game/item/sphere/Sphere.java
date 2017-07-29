package game.item.sphere;

import game.FrameCounter;
import game.Utils;
import game.bases.GameObject;
import game.bases.GameObjectPool;
import game.bases.Vector2D;
import game.bases.renderers.Animation;


/**
 * Created by cuonghx2709 on 7/26/2017.
 */
public class Sphere extends GameObject {


    FrameCounter cooldown;

    public static Sphere instancel;
    public static Sphere instancer;

    public Sphere(){
        super();
        this.renderer = new Animation(
                Utils.Loadimage("assets/images/sphere/0.png"),
                Utils.Loadimage("assets/images/sphere/1.png"),
                Utils.Loadimage("assets/images/sphere/2.png"),
                Utils.Loadimage("assets/images/sphere/3.png")
        );
        this.cooldown = new FrameCounter(50);
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);

        if (screenPosition.y <= 0){
            isActive = false;
        }
        makeBullet();
    }

    private void makeBullet() {
        if (cooldown.run()){
            cooldown.reset();
            SphereBullet sphereBullet = GameObjectPool.recycle(SphereBullet.class);
            sphereBullet.relativePosition.set(screenPosition);
        }
    }
}
