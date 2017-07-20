package game.enemies;

import game.Utils;
import game.bases.Overlap;
import game.bases.GameObject;
import game.bases.ImageRenderer;
import game.bases.Vector2D;
import game.player.Player;


/**
 * Created by cuonghx2709 on 7/17/2017.
 */
public class EnemiesBullet extends GameObject{
    public float dx;
    public float dy;
    public EnemiesBullet(){
        this.position = new Vector2D();
        this.imageRenderer = new ImageRenderer(Utils.loadimage("assets/images/enemies/bullets/blue.png"));
    }

    public void setMove(double dx, double dy) {
        this.dx = (float) dx;
        this.dy = (float) dy;
    }
    @Override
    public void run(){
        position.addUp(dx, dy);
        if(position.x < 0  || position.x >= 380||position.y < 0 || position.y > 800 ){
            remote.add(this);
        }
       checkplayer(newplayer);
    }

    private void checkplayer(Player player) {
        if (Overlap.run(player.position, player.imageRenderer.image.getWidth()-22, player.imageRenderer.image.getHeight()-6, position, imageRenderer.image.getWidth(), imageRenderer.image.getHeight())){
            remote.add(this);
            life--;
        }
    }
}
