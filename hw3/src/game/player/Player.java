package game.player;

import game.FrameCounter;
import game.Utils;
import game.bases.*;

/**
 * Created by cuonghx2709 on 7/12/2017.
 */
public class Player extends GameObject {

    private Contrains contrains;
    public InPutManager inPutManager;
    private FrameCounter cooldown;

    private boolean spellDisable;
    public boolean powable;

    private Vector2D velocity;

    @Override
    public void run() {
        move();
        castspell();
        newplayer = this;
    }

    private void castspell() {
        if (inPutManager.xPress){
            if(!spellDisable) {
                PlayerSpell playerSpell = new PlayerSpell();
                playerSpell.position.set(position.x, position.y - 30);
                newgameObject.add(playerSpell);
                spellDisable = true;
            }
        }
        if(spellDisable){
            if(cooldown.run()){
                cooldown.reset();
                spellDisable = false;
            }
        }
    }

    private void move() {
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

        position.addUp(velocity);
        contrains.run(position);
    }

    public Player() {
        this.velocity = new Vector2D();
        this.imageRenderer = new ImageRenderer(Utils.loadimage("assets/images/players/straight/0.png"));
        this.cooldown = new FrameCounter(17); // 17 frame ~= 300milis
    }

    public void setContrain(Contrains contrains){
        this.contrains = contrains;
    }
    public void setInPutManager(InPutManager inPutManager){
        this.inPutManager = inPutManager;
    }
}

