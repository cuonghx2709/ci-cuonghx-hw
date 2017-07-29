package game.bases.renderers;

import game.FrameCounter;
import game.bases.Vector2D;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.*;

/**
 * Created by cuonghx2709 on 7/26/2017.
 */
public class Animation implements Renderer {

    private List<BufferedImage> images;


    private int imageIndex;
    FrameCounter delayImage;

    public Animation(int Delay, BufferedImage... ArrImages){
        images = Arrays.asList(ArrImages);
        this.imageIndex = 0;
        this.delayImage = new FrameCounter(Delay);
    }
    public Animation(BufferedImage... ArrImages){
        this(3, ArrImages);
    }


    @Override
    public void render(Graphics2D g2d, Vector2D position) {
        if (delayImage.run()){
            delayImage.reset();
            changeIndex();
        }
        BufferedImage image = images.get(imageIndex);
        g2d.drawImage(image, (int) (position.x - image.getWidth()/2), (int) (position.y - image.getHeight()/2), null);
    }

    private void changeIndex() {
        imageIndex++;
        if (imageIndex >= images.size()){
            imageIndex = 0;
        }
    }
    public int getIndexImage(){
        return imageIndex;
    }
    public int getSize(){
        return images.size();
    }
    public void setImageIndex(int index){
        this.imageIndex = index;
    }
}
