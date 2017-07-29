package game.bases.effect;

import tklibs.AudioUtils;

import javax.sound.sampled.Clip;

/**
 * Created by cuonghx2709 on 7/27/2017.
 */
public class Effect {
    public  Clip clip;
    public Effect (String url){
        clip = AudioUtils.loadSound(url);
    }
    public void play(){
        this.clip.setFramePosition(0); // reset con trỏ về đầu đoạn sound
        this.clip.start();
    }
}
