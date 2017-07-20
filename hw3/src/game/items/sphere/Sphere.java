package game.items.sphere;

import game.FrameCounter;
import game.Utils;
import game.bases.*;

/**
 * Created by cuonghx2709 on 7/20/2017.
 */
public class Sphere extends GameObject {
    InPutManager inPutManager;
    Vector2D velocity;
    Contrains contrains;
    FrameCounter cooldown;
    private int count = 0;
    private boolean bulletDisable;

    public Sphere(){
        this.position = new Vector2D();
        this.velocity = new Vector2D();
        this.imageRenderer = new ImageRenderer(Utils.loadimage("assets/images/sphere/0.png"));
        this.cooldown = new FrameCounter(5);
    }
    @Override
    public void run(){
        this.velocity.set(0,0);
        if(inPutManager.rightPress){
            velocity.x = 10;
        }
        if(inPutManager.leftPress){
            velocity.x = -10;
        }
        if(inPutManager.upPress){
            velocity.y = -10;
        }
        if(inPutManager.downPress){
            velocity.y = 10;
        }
        if(count > 10){
            pow = 0;
            newplayer.powable = false;
            remote.add(this);
        }
        if(inPutManager.shiftPressed&&newplayer.powable){

            if(!bulletDisable){
                count+= 1;
                Spherebullet spherebullet = new Spherebullet();
                spherebullet.position.set(newplayer.position.x, newplayer.position.y - 30);
                newgameObject.add(spherebullet);
                bulletDisable = true;
            }else{
                if(cooldown.run()){
                    cooldown.reset();
                    bulletDisable = false;
                }
            }

        }



        position.addUp(velocity);
        contrains.run(position);
    }

    public void setInPutManager(InPutManager inPutManager){
        this.inPutManager = inPutManager;
    }
    public void setContrains(Contrains contrains){
        this.contrains = contrains;
    }
}
