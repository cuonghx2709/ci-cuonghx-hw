package game.scenes;

import game.Utils;
import game.bases.GameObject;
import game.bases.renderers.ImageRenderer;
import game.bases.Vector2D;

/**
 * Created by cuonghx2709 on 7/23/2017.
 */
public class Backgrounds extends GameObject{
    public static Backgrounds instance;

    public ImageRenderer imageRenderer;
    public Backgrounds(){
        super();//lop cha khai bao trong gameobject;
        this.imageRenderer = new ImageRenderer(Utils.Loadimage("assets/images/background/0.png"));
        this.imageRenderer.anchor.set(0, 1);
        renderer = imageRenderer;
        instance = this;
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        if (this.relativePosition.y - this.imageRenderer.getHeiht() < 0) {
            this.relativePosition.addUp(0, 1);
        }
    }
}
