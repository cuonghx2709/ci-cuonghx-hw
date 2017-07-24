package game;

import game.bases.Contraints;
import game.bases.GameObject;
import game.enemies.Enemy1;
import game.enemies.EnemySpawer;
import game.inputs.InputManger;
import game.players.Player;
import game.screens.Backgrounds;
import game.screens.Setting;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
/**
 * Created by huynq on 7/9/17.
 */
public class GameWindow extends JFrame {

    BufferedImage backBufferImage;
    Graphics2D backBufferGraphics2D;

    Backgrounds background;

    InputManger inputManger = new InputManger();


    public GameWindow() {

        setupWindow();
        addBackground();
        addPlayer();
        addEnemySpawner();



        backBufferImage = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        backBufferGraphics2D = (Graphics2D) backBufferImage.getGraphics();

        setupInputs();
        this.setVisible(true);
    }

    private void addBackground() {
        background = new Backgrounds();
        background.position.y = this.getHeight();
        GameObject.add(background);
    }

    private void addEnemySpawner() {
        GameObject.add(new EnemySpawer());
         }

    private void addPlayer() {
        Player player = new Player();
        player.setContraints(new Contraints(20, this.getHeight(), 0, background.renderer.getWidth()));
        player.setInputManger(inputManger);
        player.position.set(background.renderer.getWidth()/2, this.getHeight() - 50);

        GameObject.add(player);
    }

    private void setupInputs() {
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                inputManger.keyPress(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                inputManger.keyReleased(e);
            }
        });
    }


    long lasUpdateTime;

    long start;
    long end;
    public void loop() {
         while (true) {
            long currenTime = System.currentTimeMillis();
            if (currenTime - lasUpdateTime > Setting.frameDelay) {
                lasUpdateTime = currenTime;


                run();

                render();

            }
        }
    }

    private void run() {

        GameObject.runAll();

    }

    private void render() {

        backBufferGraphics2D.setColor(Color.BLACK);
        backBufferGraphics2D.fillRect(0, 0, this.getWidth(), this.getHeight());



        GameObject.renderAll(backBufferGraphics2D);
        Graphics2D g2d = (Graphics2D) this.getGraphics();
        g2d.drawImage(backBufferImage, 0, 0, null);
        g2d.setColor(Color.WHITE);
        g2d.drawString(String.format("life : %s", Player.instance.life),400,400);
        end = System.currentTimeMillis();
        if(end - start != 0)
        g2d.drawString(String.format("fps : %s", 1000/(end-start)),400,450);
        start = System.currentTimeMillis();

    }

    private void setupWindow() {
        this.setSize(Setting.WindowWidth, Setting.Windowheight);
        this.setResizable(false);
        this.setTitle("Touhou - Remade by cuonghx");

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}