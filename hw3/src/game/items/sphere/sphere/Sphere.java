package game.items.sphere.sphere;

import game.FrameCounter;
import game.Utils;
import game.bases.GameObject;
import game.bases.ImageRenderer;
import game.bases.Vector2D;
import game.items.sphere.sphere.SphereBullet;

/**
 * Created by cuonghx2709 on 7/24/2017.
 */
public class Sphere extends GameObject{

    FrameCounter cooldown;

    public Sphere(){
        super();
        this.position = new Vector2D();
        this.renderer = new ImageRenderer(Utils.Loadimage("assets/images/sphere/0.png"));
        this.cooldown = new FrameCounter(20);

    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        if (cooldown.run()){

            SphereBullet sphereBullet = new SphereBullet();
            sphereBullet.position.set(this.screenPosition);
            newGameObject.add(sphereBullet);
            cooldown.reset();

        }

    }
}
