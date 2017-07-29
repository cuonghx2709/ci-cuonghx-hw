package game;

import game.bases.Contraints;
import game.bases.GameObject;
import game.enemies.EnemySpawer;
import game.inputs.InputManger;
import game.players.Player;
import game.scenes.Backgrounds;
import game.scenes.Setting;
import javafx.scene.media.MediaPlayer;
import tklibs.AudioUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

/**
 * Created by cuonhx on 7/9/17.
 */
public class GameWindow extends JFrame {

    BufferedImage backBufferImage;
    Graphics2D backBufferGraphics2D;

    Backgrounds background;

    InputManger inputManger = new InputManger();
    MediaPlayer mediaPlayer;

    public GameWindow() {

        setupWindow();
        addBackground();
        addPlayer();
        addEnemySpawner();
        SetUpAudio();

        backBufferImage = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        backBufferGraphics2D = (Graphics2D) backBufferImage.getGraphics();

        setupInputs();
        this.setVisible(true);
    }

    private void SetUpAudio() {
        AudioUtils.initialize();
        mediaPlayer = AudioUtils.playMedia("assets/music/1.mp3");
        mediaPlayer.setVolume(0.5d);
        mediaPlayer.setOnRepeat(this::loop);
        mediaPlayer.setOnPlaying(this::run);

    }

    private void addBackground() {
        background = new Backgrounds();
        background.relativePosition.y = this.getHeight();
        GameObject.add(background);
    }

    private void addEnemySpawner() {
        EnemySpawer enemySpawer = new EnemySpawer();
        GameObject.add(enemySpawer);

    }

    private void addPlayer() {
        Player player = new Player();
        player.setContraints(new Contraints(20, this.getHeight(), 0, background.imageRenderer.getWidth()));
        player.setInputManger(inputManger);
        player.relativePosition.set(background.imageRenderer.getWidth()/2, this.getHeight() - 50);

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

    long lastUpdatetime = -1;
    public void loop() {
        while (true) {
            long currenttime = System.currentTimeMillis();
            if (currenttime - lastUpdatetime > 17){
                run();
                render();
                lastUpdatetime = currenttime;
            }
        }
    }

    private void run() {

        GameObject.runAll();
    }

    long start;
    long end;

    private void render() {
        backBufferGraphics2D.setColor(Color.BLACK);
        backBufferGraphics2D.fillRect(0, 0, this.getWidth(), this.getHeight());



      GameObject.renderAll(backBufferGraphics2D);

        Graphics2D g2d = (Graphics2D) this.getGraphics();

        g2d.drawImage(backBufferImage, 0, 0, null);
        g2d.setColor(Color.GREEN);
        g2d.setFont(new Font("1", Font.BOLD,14));
        end = System.currentTimeMillis();
        if (end - start > 0){

            g2d.drawString(String.format("fps : %s", 1000 /(end - start)), 400, 200);
        }
        start = System.currentTimeMillis();
    }

    private void setupWindow() {
        this.setSize(Setting.gameWindowWidth, Setting.gameWindowHeight);
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