package game.enemies;

import game.Utils;
import game.bases.GameObject;
import game.bases.Vector2D;
import game.bases.renderers.Animation;


/**
 * Created by cuonghx2709 on 7/29/2017.
 */
public class BigExplosion extends GameObject{
    public BigExplosion(){
        super();
        this.renderer = new Animation(
                Utils.Loadimage("assets/images/enemies/explosion-big/0.png"),
                Utils.Loadimage("assets/images/enemies/explosion-big/1.png"),
                Utils.Loadimage("assets/images/enemies/explosion-big/2.png"),
                Utils.Loadimage("assets/images/enemies/explosion-big/3.png"),
                Utils.Loadimage("assets/images/enemies/explosion-big/4.png"),
                Utils.Loadimage("assets/images/enemies/explosion-big/5.png"),
                Utils.Loadimage("assets/images/enemies/explosion-big/6.png"),
                Utils.Loadimage("assets/images/enemies/explosion-big/7.png")
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
