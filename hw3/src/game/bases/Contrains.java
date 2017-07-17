package game.bases;

import tklibs.Mathx;

/**
 * Created by cuonghx2709 on 7/17/2017.
 */
public class Contrains {
    public float top;
    public float bottom;
    public  float left;
    public  float right;
    public Contrains(float top, float bottom, float left, float right) {
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
    }
    public void run(Vector2D vector2D){
        vector2D.x = Mathx.clamp(vector2D.x, left, right );
        vector2D.y = Mathx.clamp(vector2D.y, top, bottom);
    }
}
