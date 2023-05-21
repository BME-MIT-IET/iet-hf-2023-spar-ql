package Control;

import Model.BearDance;
import Model.Effects;
import Model.Tile;
import Model.Virologist;
import View.TileView;
import View.VirologistView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseListener;
import static javax.swing.text.StyleConstants.getBackground;

public class GamePanel extends JPanel {
//    private GameControl gameControl;
//    public void run(){}
//    public void paintComponents(Graphics g){}
    private Game game;
    private ArrayList<TileView> tileViews = new ArrayList<>();
    private ArrayList<VirologistView> virologistViews = new ArrayList<>();

    public GamePanel(Game _game) {
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(1000, 700));
        this.setFocusable(true);
        this.setLayout(null);
        this.requestFocusInWindow();
        this.game = _game;
        for(int i = 0; i < game.getMap().getTiles().size(); i++) {
            TileView tmp = new TileView(game.getMap().getTile(i));
            tileViews.add(tmp);
        }
        for(int i = 0; i < game.getMap().getVirologists().size(); i++) {
            Virologist virologist = game.getMap().getVirologists().get(i);
            Tile tile = virologist.getTile();
            Polygon polygon = new Polygon(tile.getPointsX(), tile.getPointsY(), tile.getN());
            int xcenter,ycenter;
            int pX[]=tile.getPointsX();
            int pY[]=tile.getPointsY();
            xcenter=(getMaxValue(pX)+getMinValue(pX))/2;
            ycenter=(getMaxValue(pY)+getMinValue(pY))/2;
            VirologistView tmp = new VirologistView();
            Point po =new Point(xcenter,ycenter);
            tmp.setCoordinates(po);
            tmp.setVirologist(virologist);
            virologistViews.add(tmp);
        }
        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                Point p = e.getPoint();
                int activeVirologist = game.getActive();
                Boolean hasMoved = game.getHasMoved();
                if(!hasMoved) {
                    Tile virologistOnTile = game.getMap().getVirologists().get(activeVirologist).getTile();
                    ArrayList<Tile> adjacentTiles = virologistOnTile.getAdjacentTiles();
                    for( Tile tile : adjacentTiles) {
                        int[] xpoints = tile.getPointsX();
                        int[] ypoints = tile.getPointsY();
                        int npoints = tile.getN();
                        Polygon poly = new Polygon(xpoints,ypoints,npoints);
                        if(poly.contains(p) && game.getMap().getVirologists().get(activeVirologist).Move(tile.getId())) {
                            virologistViews.get(activeVirologist).setCoordinates(p);
                            draw();
                            game.setHasMoved(true);
                        }
                    }
                }
            }

        });
        this.requestFocusInWindow();
    }

    /**
     * A virológus meghal és a jelét el kell távolítani a térképről
     * @param v
     */
    public void virologistDie(Virologist v){
        virologistViews.remove(v.getId());
    }

    public void draw() {
        //this.grabFocus();
        this.repaint();
    }

    /**
     * Függvény, ami bejárja az összes tileView-t
     * és kirajzoltatja a mezőket, illetve a virológusokat
     * Kékkel, akik épp nem várnak
     * Pirossal, amelyik éppen aktív, vagyis soron van
     * */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (int i = 0; i < tileViews.size(); i++) {
            tileViews.get(i).draw(g);
        }
        for (int i = 0; i < virologistViews.size(); i++) {
            if(i == game.getActive())
                virologistViews.get(i).draw(g, Color.RED);
            else {
                boolean bear = false;
                List<Effects> effects = virologistViews.get(i).getVirologist().getEffects();
                for (Effects e : effects) { if (e instanceof BearDance) { bear = true; } }
                if(!bear)  virologistViews.get(i).draw(g, Color.BLUE);
                if(bear)  virologistViews.get(i).draw(g, Color.BLACK);
            }
        }
    }

    public static int getMaxValue(int[] numbers){
        int maxValue = numbers[0];
        for(int i=1;i < numbers.length;i++){
            if(numbers[i] > maxValue){
                maxValue = numbers[i];
            }
        }
        return maxValue;
    }
    public static int getMinValue(int[] numbers){
        int minValue = numbers[0];
        for(int i=1;i<numbers.length;i++){
            if(numbers[i] < minValue){
                minValue = numbers[i];
            }
        }
        return minValue;
    }
}
