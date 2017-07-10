package game;

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

/**
 * Created by cuonghx2709 on 7/9/2017.
 */
public class GameWindow extends JFrame{

    private BufferedImage backgroud;
    private BufferedImage player;
    private  int playerX;
    BufferedImage playerleft;
    BufferedImage playerright;
    private  int playerY ;
    private  int py;
    BufferedImage backBufferImage;
    Graphics2D backBufferGraphics2D;

    public GameWindow() {

        setupwindow();
        loadImage();
        playerX = backgroud.getWidth()/2;
        playerY = this.getHeight() - player.getHeight();

        setupInputs();
        backBufferImage = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        backBufferGraphics2D = (Graphics2D) backBufferImage.getGraphics();

        this.setVisible(true);
    }

    private void setupInputs() {
        int X = playerX*2- player.getWidth();
        int y = playerY;
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()){
                    case KeyEvent.VK_RIGHT:
                        if(playerX <= X)
                            playerX += 5;
                        break;
                    case KeyEvent.VK_LEFT:
                        if (playerX > 0)
                            playerX -=5;
                        break;
                    case KeyEvent.VK_UP:
                        if(playerY >= player.getHeight())
                            playerY-=5;
                        break;
                    case KeyEvent.VK_DOWN:
                        if(playerY < y)
                            playerY += 5;
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    public void run(){
        py = this.getHeight() - backgroud.getHeight();
        while(true){
            try {
                Thread.sleep(17);
                backBufferGraphics2D.setColor(Color.BLACK);
                backBufferGraphics2D.fillRect(0,0,this.getWidth(),this.getHeight());

                if(py!=0) py++;
                backBufferGraphics2D.drawImage(backgroud, 0, py, null);
                backBufferGraphics2D.drawImage(player, playerX, playerY , null);
                Graphics2D g2d = (Graphics2D)this.getGraphics();
                g2d.drawImage(backBufferImage, 0, 0 , null);
            } catch (InterruptedException e) {
                e.printStackTrace();

            }
        }
    }

    private void loadImage() {
        try {
            backgroud = ImageIO.read(new File("assets/images/background/0.png"));
            player = ImageIO.read(new File("assets/images/players/straight/0.png"));
            playerleft  = ImageIO.read(new File("assets/images/players/left/0.png"));
            playerright = ImageIO.read(new File("assets/images/players/right/0.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setupwindow() {
        this.setSize(800, 800);
        this.setResizable(false);
        this.setTitle("Touhou - remade by cuonghx");
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("closing");
                System.exit(0);
            }
        });
    }

}
