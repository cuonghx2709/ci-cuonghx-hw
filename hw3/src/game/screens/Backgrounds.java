package game.screens;

import game.Utils;
import game.bases.GameObject;
import game.bases.ImageRenderer;
import game.bases.Vector2D;

/**
 * Created by cuonghx2709 on 7/23/2017.
 */
public class Backgrounds extends GameObject{
    public Backgrounds(){
        super();//lop cha khai bao trong gameobject;
        this.renderer = new ImageRenderer(Utils.Loadimage("assets/images/background/0.png"));
        this.renderer.anchor.set(0, 1);
    }

    @Override
    public void run(Vector2D parentPosition) {

        if (this.position.y - this.renderer.getHeiht() < 0) {
            this.position.addUp(0, 1);
        }
    }
}
