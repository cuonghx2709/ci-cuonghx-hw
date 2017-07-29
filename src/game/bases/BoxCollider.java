package game.bases;

import tklibs.Mathx;

/**
 * Created by cuonghx2709 on 7/24/2017.
 */
public class BoxCollider extends  GameObject{

    private  float width;

    @Override
    public String toString() {
        return "BoxCollider{" +
                "width=" + width +
                ", relativePosition=" + relativePosition +
                ", height=" + height +
                ", screenPosition=" + screenPosition +
                '}';
    }

    private  float height;

    public BoxCollider(float width, float height){
        super();
        this.width = width ;
        this.height = height ;
    }
    public  BoxCollider(){
        this(0,0);
    }
    public float left(){
        return this.screenPosition.x - this.width/2;
    }
    public float right(){
        return  this.screenPosition.x + this.width/2;
    }
    public float top(){
        return  this.screenPosition.y - this.height/2;
    }
    public float bottom(){
        return this.screenPosition.y + this.height/2;
    }

    public boolean colliderwith (BoxCollider other){
        boolean xOverlap = Mathx.inRange(this.left(), other.left(), other.right()) ||
                Mathx.inRange(other.left(), this.left(), this.right());
        boolean yOvelap = Mathx.inRange(this.top(), other.top(), other.bottom())||
                Mathx.inRange(other.top(), this.top(),this.bottom());
        return  xOverlap && yOvelap;
    }
}
