package View;

import java.awt.*;
import Model.Tile;

public class TileView extends Drawable{
    private int sides;
    private Tile tile;

    public TileView(Tile tile) {
        this.tile = tile;
    }

    public void draw(Graphics g){
        g.setColor(tile.getColor());
        g.fillPolygon(tile.getPointsX(), tile.getPointsY(), tile.getN());
        g.setColor(Color.BLACK);
        g.drawPolygon(tile.getPointsX(), tile.getPointsY(), tile.getN());
    }
}
