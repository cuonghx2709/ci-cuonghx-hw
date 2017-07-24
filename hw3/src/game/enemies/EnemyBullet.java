package game.enemies;

import game.Utils;
import game.bases.BoxCollider;
import game.bases.GameObject;
import game.bases.ImageRenderer;
import game.bases.Vector2D;
import game.players.Player;

/**
 * Created by cuonghx2709 on 7/23/2017.
 */
public class EnemyBullet extends GameObject{

    public Vector2D velocity;



    public EnemyBullet(){
        super();
        this.velocity = new Vector2D();
        this.renderer = new ImageRenderer(Utils.Loadimage("assets/images/enemies/bullets/white.png"));
        this.boxCollider = new BoxCollider(16,16);
        this.boxCollider.position.set(this.position);
        children.add(boxCollider);

    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        this.position.addUp(velocity);
        if (boxCollider.collideWith(Player.instance.boxCollider)){
            remote.add(this);
            Player.instance.life--;
        }
        if (position.y > 800||position.x >375 ||position.y < 0 || position.x <0){
            remote.add(this);
        }
    }

}
