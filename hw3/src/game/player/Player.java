package game.player;

import game.FrameCounter;
import game.Utils;
import game.bases.Contrains;
import game.bases.ImageRenderer;
import game.bases.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by cuonghx2709 on 7/12/2017.
 */
public class Player {

    public Vector2D position;
    public ImageRenderer imageRenderer;
    public Contrains contrains;
    public FrameCounter cooldown;
    public boolean spellDisable;

    public Player() {
        this.position = new Vector2D();
        this.imageRenderer = new ImageRenderer(Utils.Loadimage("assets/images/players/straight/0.png"));
        this.cooldown = new FrameCounter(17); // 17 frame ~= 300milis
    }

    public void move(float dx, float dy) {
        position.addUp(dx, dy);
        contrains.run(position);

    }

    public void setContrain(Contrains contrains){
        this.contrains = contrains;
    }

    public void render(Graphics2D g2d) {
        imageRenderer.render(g2d, position);
    }

    public void castspells(ArrayList<PlayerSpell> playerSpells){
        if(!spellDisable){
            PlayerSpell playerSpell = new PlayerSpell();
            playerSpell.position.set(position.x, position.y);
            playerSpells.add(playerSpell);
            spellDisable = true;
        }
    }
    public void run(){
        if(spellDisable){
            if(cooldown.run()){
                cooldown.reset();
                spellDisable = false;
            }
        }
    }
}

