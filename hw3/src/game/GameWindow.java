package game;

import game.background.BackGround;
import game.bases.Contrains;
import game.bases.GameObject;
import game.bases.InPutManager;
import game.enemies.EnemySpawner;
import game.player.Player;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;


/**
 * Created by cuonghx2709 on 7/12/2017.
 */
public class GameWindow extends JFrame{

    InPutManager inPutManager = new InPutManager();

    BackGround buffBackground = new BackGround();


    public GameWindow(){
        SetUpGameWindow();
        setUpBackground();
        SetUpInput();
        setPlayer();
        setbuffBackground();
        addenemySpawner();


        this.setVisible(true);
    }

    private void addenemySpawner() {
         GameObject.enemySpawner = new EnemySpawner();
        GameObject.add(GameObject.enemySpawner);
    }

    private void setbuffBackground() {

        buffBackground.imageRenderer.image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        buffBackground.position.set(this.getWidth()/2,this.getHeight()/2);
        buffBackground.graphics2D = (Graphics2D) buffBackground.imageRenderer.image.getGraphics();

    }

    private void setUpBackground() {

        BackGround backGround = new BackGround();
        backGround.position.set(backGround.imageRenderer.image.getWidth()/2, this.getHeight()-backGround.imageRenderer.image.getHeight()/2);
        GameObject.add(backGround);

    }

    private void setPlayer() {
        Player player = new Player();
        player.setContrain(new Contrains(50, this.getHeight() - 25, 15, this.getWidth()/2 - 25));
        player.setInPutManager(inPutManager);
        player.position.set(this.getWidth()/4,this.getHeight() - 100);
        GameObject.add(player);
    }

    public void loop() {

        while (true){

            run();

            render();

        }
    }
    private void run() {
        GameObject.runAll();
    }

    private void render(){
        try {
            Thread.sleep(17);
            buffBackground.graphics2D.setColor(Color.BLACK);
            buffBackground.graphics2D.fillRect(0, 0, this.getWidth(), this.getHeight());

            GameObject.renderAll(buffBackground.graphics2D);

            Graphics2D g2d = (Graphics2D) this.getGraphics();
            buffBackground.imageRenderer.render(g2d,buffBackground.position);
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
                inPutManager.KeyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                inPutManager.KeyReleased(e);
            }
        });
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
