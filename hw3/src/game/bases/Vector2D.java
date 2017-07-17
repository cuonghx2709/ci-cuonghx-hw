package game.bases;

/**
 * Created by cuonghx2709 on 7/17/2017.
 */
public class Vector2D {
    public float x;
    public float y;

    public Vector2D(float x, float y) {
        this.x = x;
        this.y = y;
    }
    public  Vector2D(){
        this(0,0);
    }

    public void addUp(float x, float y){
        this.x += x;
        this.y += y;
    }
    public void set(float x, float y){
        this.x = x;
        this.y = y;
    }
}
