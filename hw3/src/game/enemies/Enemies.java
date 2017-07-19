package game.enemies;

import game.FrameCounter;
import game.Utils;
import game.bases.ImageRenderer;
import game.bases.Vector2D;
import tklibs.Mathx;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by cuonghx2709 on 7/12/2017.
 */
public class Enemies {
    public Vector2D position;
    public ImageRenderer imageRenderer;
    public FrameCounter cooldownbullet;
    public boolean bulletdisable;
    public ArrayList<EnemiesBullet> enemiesBullets;
    private int R = 8;
    private int i = 0;
    public int status;


    public Enemies(int status) {
        this.position = new Vector2D();
        this.imageRenderer = new ImageRenderer(Utils.Loadimage("assets/images/enemies/level0/black/0.png"));
        this.status = status;
        enemiesBullets = new ArrayList<>();
        if (status == 1){
        cooldownbullet = new FrameCounter(2);
        }
        else {
            cooldownbullet = new FrameCounter( 50);
        }
    }
    public void  rand(int Width){
        Random random = new Random();
         position.x = random.nextInt(Width);
    }

    public void move(){
        position.addUp(0, 3);
    }
    public void render(Graphics2D g2d){
        imageRenderer.render(g2d, position);
    }
    public void makeBullet(){


        if(!bulletdisable&&position.y < 800){

            if(status == 1){
                EnemiesBullet enemiesBullet = new EnemiesBullet();
                enemiesBullet.setMove(R*Math.cos(Math.PI*i/180),R*Math.sin(i*Math.PI/180));
                enemiesBullet.position.set(position.x,position.y);
                enemiesBullets.add(enemiesBullet);
                i+=10;
                if(i > 360 ){
                    i = 0;
                    cooldownbullet = new FrameCounter(50);
                }
                else {
                    cooldownbullet = new FrameCounter(1);
                }
            }
            else {
                for (int j = 0; j < 360; j+= 10) {
                    EnemiesBullet enemiesBullet = new EnemiesBullet();
                    enemiesBullet.setMove(R*Math.cos(Math.PI*j/180),R*Math.sin(j*Math.PI/180));
                    enemiesBullet.position.set(position.x,position.y);
                    enemiesBullets.add(enemiesBullet);
                }
            }
            bulletdisable = true;
        }else{
            if(cooldownbullet.run()){
                cooldownbullet.reset();
                bulletdisable = false;
            }
        }

    }
}
