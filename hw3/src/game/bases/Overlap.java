package game.bases;

/**
 * Created by cuonghx2709 on 7/19/2017.
 */
public class Overlap {
    public static boolean run(Vector2D position1,float width1 , float height1, Vector2D position2, float width2, float height2 ){
        Vector2D min = new Vector2D();
        Vector2D max = new Vector2D();

        min.set(position1.x - width1/2 - width2/2, position1.y - height1/2 - height2/2);
        max.set(position1.x + width1/2 + width2/2, position1.y + height1/2 + height2/2);

        if (position2.x >= min.x && position2.x <= max.x && position2.y <= max.y && position2.y >= min.y){
            return  true;
        }else{
            return false;
        }
    }
}
