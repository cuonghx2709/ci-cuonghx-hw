package game.players;

import game.FrameCounter;
import game.Utils;
import game.bases.*;
import game.inputs.InputManger;
import game.items.sphere.sphere.Sphere;
import game.screens.Setting;

/**
 * Created by cuonghx2709 on 7/11/2017.
 */
public class Player extends GameObject{
    //properties:thuoc tinh
    Contraints contraints ;
    InputManger  inputManger;

    FrameCounter cooldownCounter;

    Vector2D velocity;
    public int life = Setting.playerlife;

    public static Player instance;


    public Player(){
        this.velocity = new Vector2D();
        this.cooldownCounter = new FrameCounter(Setting.cooldowntSpell); // 17 fram = 300 milisecon to cool down
        this.renderer  = new ImageRenderer(Utils.Loadimage("assets/images/players/straight/0.png"));
        instance =  this;
        this.boxCollider = new BoxCollider(20,44);
        children.add(boxCollider);
        Sphere spherel = new Sphere();


        spherel.position.set(-20,0);
        children.add(spherel);
        Sphere spherer = new Sphere();
        spherer.position.set(20,0);
        children.add(spherer);
    }

    @Override
    public void run(Vector2D parentPosition){
        super.run(parentPosition);
        move();
        castSpell();
    }

    private void castSpell() {
        if(cooldownCounter.run()&&inputManger.xPressed){
            PlayerSpell playerSpell = new PlayerSpell();
            playerSpell.position.set(this.position.add(0,-20));
            newGameObject.add(playerSpell);
            cooldownCounter.reset();
        }
    }

    private void move() {
        this.velocity.set(0,0);
        if(inputManger.rightPressed){
            this.velocity.x += 10;
        }
        if(inputManger.upPressed){
            this.velocity.y -=10;
        }
        if(inputManger.downPressed){
            this.velocity.y += 10;
        }
        if(inputManger.leftPressed){
            this.velocity.x -= 10;
        }
        this.position.addUp(velocity);
        this.contraints.make(position);
    }

    //methods:phuong thuc
    // setter
    public  void setContraints(Contraints contraints){
        this.contraints = contraints;
    }

    public void setInputManger(InputManger inputManger){
        this.inputManger = inputManger;
    }
}
