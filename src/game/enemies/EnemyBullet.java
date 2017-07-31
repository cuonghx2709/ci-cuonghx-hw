package game.enemies;

import game.Utils;
import game.bases.BoxCollider;
import game.bases.GameObject;
import game.bases.renderers.ImageRenderer;
import game.bases.Vector2D;
import game.bases.physics.Physic;
import game.bases.physics.PhysicBody;
import game.players.Player;
import game.scenes.Setting;

/**
 * Created by cuonghx2709 on 7/23/2017.
 */
public class EnemyBullet extends GameObject implements PhysicBody{

    public Vector2D velocity;

    BoxCollider boxCollider;

    public EnemyBullet(){
        super();
        this.velocity = new Vector2D();
        this.renderer = new ImageRenderer(Utils.Loadimage("assets/images/enemies/bullets/white.png"));
        this.boxCollider = new BoxCollider(16,16);
        boxCollider.relativePosition.set(0,0);
        this.children.add(boxCollider);

    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        this.relativePosition.addUp(velocity);
        hitPlayer();
        if (screenPosition.y > Setting.gameWindowHeight || screenPosition.x > 375 || screenPosition.x < 0 || screenPosition.y < 0){
            this.isActive = false;
        }
    }

    private void hitPlayer() {
        Player player = Physic.bodyInRect(boxCollider,Player.class);
        if (player!= null){
            this.isActive = false;
            player.hp -= 1;
        }
    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}
