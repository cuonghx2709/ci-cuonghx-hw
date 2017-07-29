package game.item;

import game.FrameCounter;
import game.Utils;
import game.bases.BoxCollider;
import game.bases.GameObject;
import game.bases.Vector2D;
import game.bases.physics.Physic;
import game.bases.physics.PhysicBody;
import game.bases.renderers.ImageRenderer;
import game.players.Player;

/**
 * Created by cuonghx2709 on 7/29/2017.
 */
public class Bigpow extends GameObject implements PhysicBody{
    BoxCollider boxCollider;
    public Vector2D velocity;
    FrameCounter counter;

    public Bigpow(){
        super();
        this.renderer = new ImageRenderer(Utils.Loadimage("assets/images/items/power-up-blue.png"));
        this.boxCollider = new BoxCollider(12,12);
        this.velocity = new Vector2D();
        this.counter = new FrameCounter(20);
        boxCollider.relativePosition.set(0,0);
        this.children.add(boxCollider);

    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        velocity.set(0,-3);
        if (counter.run()){
            velocity.set(0,3);
        }
        relativePosition.addUp(velocity);
        InrectPlayer();
    }

    private void InrectPlayer() {
        Player player = Physic.bodyInRect(boxCollider,Player.class);
        if (player != null){
            this.isActive = false;
        }
    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}

