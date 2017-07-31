package game.players;

import game.FrameCounter;
import game.Utils;
import game.bases.*;
import game.bases.effect.Effect;
import game.bases.renderers.Animation;
import game.bases.physics.PhysicBody;
import game.bases.renderers.Renderer;
import game.inputs.InputManger;
import game.item.sphere.Sphere;

/**
 * Created by cuonghx2709 on 7/11/2017.
 */
public class Player extends GameObject implements PhysicBody {
    //properties:thuoc tinh
    Contraints contraints ;
    InputManger  inputManger;

    FrameCounter cooldownCounter;
    Vector2D velocity;

    public static Player instance;

    BoxCollider boxCollider;

    Renderer rendererLeft;
    Renderer rendererRight;
    Renderer rendererStraight;

    Sphere spherel = new Sphere();
    Sphere spherer = new Sphere();
    public float hp = 3;
    PlayerExplosion playerExplosion;
    Effect effect;
    Effect dead;



    public Player(){
        this.velocity = new Vector2D();
        this.cooldownCounter = new FrameCounter(10); // 17 fram = 300 milisecon to cool down
        this.rendererLeft  = new Animation(5,
                Utils.Loadimage("assets/images/players/left/0.png"),
                Utils.Loadimage("assets/images/players/left/1.png"),
                Utils.Loadimage("assets/images/players/left/2.png"),
                Utils.Loadimage("assets/images/players/left/3.png"),
                Utils.Loadimage("assets/images/players/left/4.png"),
                Utils.Loadimage("assets/images/players/left/5.png")
        );
        this.rendererRight  = new Animation(5,
                Utils.Loadimage("assets/images/players/right/0.png"),
                Utils.Loadimage("assets/images/players/right/1.png"),
                Utils.Loadimage("assets/images/players/right/2.png"),
                Utils.Loadimage("assets/images/players/right/3.png"),
                Utils.Loadimage("assets/images/players/right/4.png"),
                Utils.Loadimage("assets/images/players/right/5.png")
        );
        this.rendererStraight  = new Animation(5,
                Utils.Loadimage("assets/images/players/straight/0.png"),
                Utils.Loadimage("assets/images/players/straight/1.png"),
                Utils.Loadimage("assets/images/players/straight/2.png"),
                Utils.Loadimage("assets/images/players/straight/3.png"),
                Utils.Loadimage("assets/images/players/straight/4.png"),
                Utils.Loadimage("assets/images/players/straight/5.png"),
                Utils.Loadimage("assets/images/players/straight/6.png")
        );

        instance =  this;
        this.boxCollider = new BoxCollider(20,40);
        boxCollider.relativePosition.set(this.relativePosition);
        this.children.add(boxCollider);


        spherel.relativePosition.set(-20,0);
        Sphere.instancel = spherel;
        children.add(spherel);
        spherer.relativePosition.set(20,0);
        Sphere.instancer = spherer;
        children.add(spherer);
        this.effect = new Effect("assets/music/sfx/player-shoot.wav");
        dead = new Effect("assets/music/sfx/player-dead.wav");

    }

    @Override
    public void run(Vector2D parentPosition){
        renderer = rendererStraight;
        super.run(parentPosition);
                   move();
        castSpell();
        if (hp <= 0){
            isActive = false;
            playerExplosion = GameObjectPool.recycle(PlayerExplosion.class);
            playerExplosion.screenPosition.set(screenPosition);
            dead.play();
        }
    }

    private void castSpell() {
        if(cooldownCounter.run()&&inputManger.xPressed){
            cooldownCounter.reset();
            PlayerSpell playerSpell = GameObjectPool.recycle(PlayerSpell.class);
            playerSpell.relativePosition.set(this.relativePosition.add(0,-30));

            effect.play();
        }
    }

    private void move() {
        this.velocity.set(0,0);
        if(inputManger.rightPressed){
            this.velocity.x += 10;
            renderer = rendererRight;
        }
        if(inputManger.upPressed){
            this.velocity.y -=10;
        }
        if(inputManger.downPressed){
            this.velocity.y += 10;
        }
        if(inputManger.leftPressed){
            this.velocity.x -= 10;
            renderer = rendererLeft;
        }
        if (inputManger.shiftPressed){
            Sphere.instancel.relativePosition.set(-10,-30);
            Sphere.instancer.relativePosition.set(10,-30);
        }else {
            Sphere.instancel.relativePosition.set(-20,0);
            Sphere.instancer.relativePosition.set(20,0);
        }
        this.relativePosition.addUp(velocity);
        this.contraints.make(relativePosition);
    }

    public  void setContraints(Contraints contraints){
        this.contraints = contraints;
    }

    public void setInputManger(InputManger inputManger){
        this.inputManger = inputManger;
    }


    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }

    public void setHp(float hp) {
        this.hp = hp;
    }
}
