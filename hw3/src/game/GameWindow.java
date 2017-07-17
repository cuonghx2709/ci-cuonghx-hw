package game;

import game.bases.Contrains;
import game.bases.Vector2D;
import game.enemies.Enemies;
import game.enemies.EnemiesBullet;
import game.player.Player;
import game.player.PlayerSpell;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;


/**
 * Created by cuonghx2709 on 7/12/2017.
 */
public class GameWindow extends JFrame{


    BufferedImage background;
    BufferedImage bufferedImagebackground;

    boolean upPress;
    boolean downPress;
    boolean rightPress;
    boolean leftPress;
    boolean xPress;
    boolean enemiesDisable;

    private int status = 1;

    private Vector2D backGround = new Vector2D();


    ArrayList<Enemies> enemiess = new ArrayList<>();
    ArrayList<PlayerSpell> playerSpells = new ArrayList<>();
    Player player = new Player();
    FrameCounter cooldown ;


    Graphics2D graphics2Dbfbg;

    public GameWindow(){
        SetUpGameWindow();
        Loadimage();
        SetUpInput();
        cooldown = new FrameCounter(150);//~= 2s
        player.setContrain(new Contrains(0, this.getHeight() - 100, 0, this.getWidth()/2));
        player.position.set(background.getWidth()/2,this.getHeight() - 100);

        backGround.set(0,this.getHeight()- background.getHeight());
        bufferedImagebackground = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        graphics2Dbfbg = (Graphics2D) bufferedImagebackground.getGraphics();

        this.setVisible(true);
    }

    public void loop() {

        while (true){

            run();

            render();

        }
    }
    private void run(){
        int dx = 0;
        int dy = 0;
        if (backGround.y < 0){
            backGround.y += 5;
        }
        if (upPress){
            dy -= 5;
        }
        if (downPress){
            dy += 5;
        }
        if (rightPress){
            dx +=5;
        }
        if (leftPress){
            dx -= 5;
        }
        if (xPress){
            player.castspells(playerSpells);
        }
        for(PlayerSpell playerSpell : playerSpells){
            playerSpell.move();

        }
        player.run();
        player.move(dx, dy);

        for (Enemies enemies: enemiess) {
            enemies.move();
            enemies.makeBullet();
            for(EnemiesBullet enemiesBullet : enemies.enemiesBullets){
                enemiesBullet.move();
            }
        }
        if(!enemiesDisable){
            Enemies enemies = new Enemies(status);
            if(status==1){
                status = 2;
            }else{
                status = 1;
            }
            enemies.rand(400 - enemies.imageRenderer.image.getWidth());
            enemiess.add(enemies);
            enemiesDisable = true;
        }else{
            if(cooldown.run()){
                cooldown.reset();
                enemiesDisable = false;
            }
        }

    }

    private void render(){
        try {
            Thread.sleep(17);
            graphics2Dbfbg.setColor(Color.BLACK);
            graphics2Dbfbg.fillRect(0, 0, this.getWidth(), this.getHeight());
            graphics2Dbfbg.drawImage(background, (int) backGround.x,(int) backGround.y, null);
            player.render(graphics2Dbfbg);
            for (PlayerSpell playerSpell: playerSpells) {
                playerSpell.render(graphics2Dbfbg);
            }
            for (Enemies enemies: enemiess) {
                enemies.render(graphics2Dbfbg);
                for (EnemiesBullet enemiesBullet : enemies.enemiesBullets ){
                    enemiesBullet.render(graphics2Dbfbg);
                }
            }

            Graphics2D g2d = (Graphics2D) this.getGraphics();
            g2d.drawImage(bufferedImagebackground, 0 , 0 , null);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void SetUpInput() {
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()){
                    case KeyEvent.VK_RIGHT:
                        rightPress = true;
                        break;
                    case KeyEvent.VK_LEFT:
                        leftPress = true;
                        break;
                    case KeyEvent.VK_UP:
                        upPress = true;
                        break;
                    case KeyEvent.VK_DOWN:
                        downPress = true;
                        break;
                    case KeyEvent.VK_X:
                        xPress = true;
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()){
                    case KeyEvent.VK_RIGHT:
                        rightPress = false;
                        break;
                    case KeyEvent.VK_LEFT:
                        leftPress = false;
                        break;
                    case KeyEvent.VK_UP:
                        upPress = false;
                        break;
                    case KeyEvent.VK_DOWN:
                        downPress = false;
                        break;
                    case KeyEvent.VK_X:
                        xPress = false;
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void Loadimage () {
        try {
            background = ImageIO.read(new File("assets/images/background/0.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void SetUpGameWindow() {
        this.setSize(800,800);
        this.setTitle("cuonghx");
        this.setResizable(false);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
