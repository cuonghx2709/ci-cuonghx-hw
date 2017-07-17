package game;

/**
 * Created by cuonghx2709 on 7/17/2017.
 */
public class FrameCounter {
    float count;
    float countmax;
    public  FrameCounter(int countmax){
        this.countmax = countmax;
    }
    public void reset(){
        count = 0;
    }
    public boolean run(){
        if(count < countmax) {
            count +=1;
            return  false;
        }
        return true;
    }
}
