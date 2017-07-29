package game.bases.renderers;

import game.bases.Vector2D;

import java.awt.*;

/**
 * Created by cuonghx2709 on 7/26/2017.
 */
public interface Renderer {
    void render(Graphics2D g2d, Vector2D position);
}
