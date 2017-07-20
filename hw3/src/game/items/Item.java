package game.items;

import game.Utils;
import game.bases.*;
import game.items.sphere.Sphere;

/**
 * Created by cuonghx2709 on 7/20/2017.
 */
public class Item extends GameObject {


    public Item (){
        this.position = new Vector2D();
        this.imageRenderer = new ImageRenderer(Utils.loadimage("assets/images/items/power-up-red.png"));
    }

    @Override
    public void run() {
        position.addUp(0, 5);
        if (Overlap.run(newplayer.position, newplayer.imageRenderer.image.getWidth()-22, newplayer.imageRenderer.image.getHeight()-6, position, imageRenderer.image.getWidth(), imageRenderer.image.getHeight())){
            remote.add(this);
            pow ++;
        }
        if(!newplayer.powable&&pow >=3){
            newplayer.powable = true;
            Sphere spherel = new Sphere();
            Sphere spherer = new Sphere();
            spherer.position.set(newplayer.position.x + 20,newplayer.position.y);
            spherel.position.set(newplayer.position.x - 20,newplayer.position.y);
            spherel.setInPutManager(newplayer.inPutManager);
            spherer.setInPutManager(newplayer.inPutManager);
            spherel.setContrains(new Contrains(50, 775, -5, 355));
            spherer.setContrains(new Contrains(50, 775, 35, 375+20));
            newgameObject.add(spherel);
            newgameObject.add(spherer);
        }
    }
}
