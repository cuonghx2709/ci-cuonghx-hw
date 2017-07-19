package game.player;

import game.FrameCounter;
import game.Utils;
import game.bases.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by cuonghx2709 on 7/12/2017.
 */
public class Player extends GameObject {

    private Contrains contrains;
    private InPutManager inPutManager;
    private FrameCounter cooldown;
    private boolean spellDisable;

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
        this.imageRenderer = new ImageRenderer(Utils.Loadimage("assets/images/players/straight/0.png"));
        this.cooldown = new FrameCounter(17); // 17 frame ~= 300milis
    }

    public void setContrain(Contrains contrains){
        this.contrains = contrains;
    }
    public void setInPutManager(InPutManager inPutManager){
        this.inPutManager = inPutManager;
    }
}

