package game.items.sphere;

import game.Utils;
import game.bases.Overlap;
import game.bases.GameObject;
import game.bases.ImageRenderer;
import game.bases.Vector2D;
import game.enemies.EnemySpawner;
import game.items.Item;

/**
 * Created by cuonghx2709 on 7/20/2017.
 */
public class Spherebullet extends GameObject {

    public Spherebullet(){
        this.position = new Vector2D();
        this.imageRenderer = new ImageRenderer(Utils.loadimage("assets/images/sphere-bullets/0.png"));
    }

    @Override
    public void run() {
        position.addUp(0, -15);
        if (position.y < 0){
            remote.add(this);
        }
        boolean status = Overlap.run(position, this.imageRenderer.image.getWidth() - 18, this.imageRenderer.image.getHeight() - 6, enemySpawner.position, enemySpawner.imageRenderer.image.getWidth(), enemySpawner.imageRenderer.image.getHeight());
        if (status) {
            Item item = new Item();
            item.position.set(enemySpawner.position.x, enemySpawner.position.y);
            remote.add(enemySpawner);
            newgameObject.add(item);
            enemySpawner = new EnemySpawner();
            newgameObject.add(enemySpawner);
        }
    }
}
