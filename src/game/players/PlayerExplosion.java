package game.players;

import game.Utils;
import game.bases.GameObject;
import game.bases.GameObjectPool;
import game.bases.Vector2D;
import game.bases.renderers.Animation;

/**
 * Created by cuonghx2709 on 7/27/2017.
 */
public class PlayerExplosion extends GameObject {
    public PlayerExplosion(){
        this.renderer = new Animation(
                Utils.Loadimage("assets/images/players/explosions/0.png"),
                Utils.Loadimage("assets/images/players/explosions/1.png"),
                Utils.Loadimage("assets/images/players/explosions/2.png"),
                Utils.Loadimage("assets/images/players/explosions/3.png"),
                Utils.Loadimage("assets/images/players/explosions/4.png"),
                Utils.Loadimage("assets/images/players/explosions/5.png"),
                Utils.Loadimage("assets/images/players/explosions/6.png"),
                Utils.Loadimage("assets/images/players/explosions/7.png")
        );
    }

    @Override
    public void run(Vector2D parentPosition) {
        Animation animation = (Animation) renderer;
        if (animation.getIndexImage() >= animation.getSize()- 1){
            this.isActive = false;
        }
    }
}
