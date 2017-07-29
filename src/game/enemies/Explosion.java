package game.enemies;

import game.Utils;
import game.bases.GameObject;
import game.bases.Vector2D;
import game.bases.renderers.Animation;
import game.bases.renderers.Renderer;

import java.awt.*;

/**
 * Created by cuonghx2709 on 7/27/2017.
 */
public class Explosion extends GameObject {

    public Explosion(){
        super();
        this.renderer = new Animation(
                Utils.Loadimage("assets/images/enemies/explosion/0.png"),
                Utils.Loadimage("assets/images/enemies/explosion/1.png"),
                Utils.Loadimage("assets/images/enemies/explosion/2.png"),
                Utils.Loadimage("assets/images/enemies/explosion/3.png"),
                Utils.Loadimage("assets/images/enemies/explosion/4.png"),
                Utils.Loadimage("assets/images/enemies/explosion/5.png"),
                Utils.Loadimage("assets/images/enemies/explosion/6.png"),
                Utils.Loadimage("assets/images/enemies/explosion/7.png")
        );
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        Animation animation = (Animation) renderer;

        if (animation.getIndexImage() >= animation.getSize()-1){
            this.isActive = false;
            animation.setImageIndex(0);
        }
    }
}
