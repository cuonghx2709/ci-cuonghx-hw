package game.player;

import game.Utils;
import game.bases.Overlap;
import game.bases.GameObject;
import game.bases.ImageRenderer;
import game.bases.Vector2D;
import game.enemies.EnemySpawner;
import game.items.Item;

/**
 * Created by cuonghx2709 on 7/12/2017.
 */
public class PlayerSpell extends GameObject {

    public PlayerSpell() {
        this.position = new Vector2D();
        this.imageRenderer = new ImageRenderer(Utils.loadimage("assets/images/player-spells/a/1.png"));
    }

    public void run() {
        position.addUp(0, -10);
        boolean status = Overlap.run(position, this.imageRenderer.image.getWidth() - 18, this.imageRenderer.image.getHeight() - 6, enemySpawner.position, enemySpawner.imageRenderer.image.getWidth(), enemySpawner.imageRenderer.image.getHeight());
        if (status) {
            Item item = new Item();
            item.position.set(enemySpawner.position.x, enemySpawner.position.y);
            remote.add(enemySpawner);
            remote.add(this);
            newgameObject.add(item);
            enemySpawner = new EnemySpawner();
            newgameObject.add(enemySpawner);
        }
        if(position.y > 800) {
            remote.add(this);
        }
    }
}
