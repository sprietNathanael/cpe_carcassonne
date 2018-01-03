/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.ui_test;

import carcassonne.controller.AbstractCarcassonneGameController;
import carcassonne.coord.Coord;
import carcassonne.model.tile.AbstractTile;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author nathanael
 */
public class MeeplePlacementLayer extends AbstractLayer implements TilePlacementMouseListener, MeeplePlacementMouseListener
{

    public static final HashMap<String, Polygon> TILE_SPLITS;

    static {
        TILE_SPLITS = new HashMap<>();
        TILE_SPLITS.put("NWW", new Polygon(new int[]{0, 15, 0}, new int[]{0, 33, 33}, 3));
        TILE_SPLITS.put("NW", new Polygon(new int[]{0, 33, 33, 15}, new int[]{0, 15, 33, 33}, 4));
        TILE_SPLITS.put("NNW", new Polygon(new int[]{0, 33, 33}, new int[]{0, 0, 15}, 3));
        TILE_SPLITS.put("N", new Polygon(new int[]{33, 66, 66, 33}, new int[]{0, 0, 33, 33}, 4));
        TILE_SPLITS.put("NNE", new Polygon(new int[]{66, 100, 66}, new int[]{0, 0, 15}, 3));
        TILE_SPLITS.put("NE", new Polygon(new int[]{66, 100, 85, 66}, new int[]{15, 0, 33, 33}, 4));
        TILE_SPLITS.put("NEE", new Polygon(new int[]{85, 100, 100}, new int[]{33, 0, 33}, 3));
        TILE_SPLITS.put("E", new Polygon(new int[]{66, 100, 100, 66}, new int[]{33, 33, 66, 66}, 4));
        TILE_SPLITS.put("SEE", new Polygon(new int[]{85, 100, 100}, new int[]{66, 66, 100}, 3));
        TILE_SPLITS.put("SE", new Polygon(new int[]{66, 85, 100, 66}, new int[]{66, 66, 100, 85}, 4));
        TILE_SPLITS.put("SSE", new Polygon(new int[]{66, 100, 66}, new int[]{85, 100, 100}, 3));
        TILE_SPLITS.put("S", new Polygon(new int[]{33, 66, 66, 33}, new int[]{66, 66, 100, 100}, 4));
        TILE_SPLITS.put("SSW", new Polygon(new int[]{0, 33, 33}, new int[]{100, 85, 100}, 3));
        TILE_SPLITS.put("SW", new Polygon(new int[]{0, 15, 33, 33}, new int[]{100, 66, 66, 85}, 4));
        TILE_SPLITS.put("SWW", new Polygon(new int[]{0, 15, 0}, new int[]{66, 66, 100}, 3));
        TILE_SPLITS.put("W", new Polygon(new int[]{0, 33, 33, 0}, new int[]{33, 33, 66, 66}, 4));
        TILE_SPLITS.put("CNW", new Polygon(new int[]{33, 50, 50, 33}, new int[]{33, 33, 50, 50}, 4));
        TILE_SPLITS.put("CNE", new Polygon(new int[]{50, 66, 66, 50}, new int[]{33, 33, 50, 50}, 4));
        TILE_SPLITS.put("CSE", new Polygon(new int[]{50, 66, 66, 50}, new int[]{50, 50, 66, 66}, 4));
        TILE_SPLITS.put("CSW", new Polygon(new int[]{33, 50, 50, 33}, new int[]{50, 50, 66, 66}, 4));
    } //tile splits

    private Set<Set<String>> tileAggregates;
    private Set<String> currentTileAggregate;
    private UICoord currentPosition;

    public MeeplePlacementLayer(GridPanel gridPanel, AbstractCarcassonneGameController controller)
    {
        super(gridPanel, controller);
        this.tileAggregates = new HashSet<>();
        this.currentTileAggregate = null;
    }

    public void setAggregates(AbstractTile currentTile)
    {
        this.tileAggregates = currentTile.getAggregateEmplacements();
    }

    public void setAggregates(Set<Set<String>> aggregates)
    {
        System.out.println("+++++++++++++++++++++++++ aggregates are : " + aggregates);
        this.tileAggregates = aggregates;
    }

    public void setCurrentPosition(UICoord c)
    {
        this.currentPosition = c;
        // TODO test if meepable
    }

    @Override
    public void paint(Graphics2D g2)
    {
        if (this.isVisible() && this.currentTileAggregate != null) {
            int tileSize = this.gridPanel.getTileSize();
            UICoord center = this.gridPanel.getGraphicalCenter();

            // Affine transform to resize tiles splits
            AffineTransform resizeMeeplesPlacement = new AffineTransform();
            AffineTransform translateMeeplesPlacement = new AffineTransform();
            resizeMeeplesPlacement.scale(tileSize / 100.0, tileSize / 100.0);
            double delta_x = (this.currentPosition.getX() * tileSize) + center.getX();
            double delta_y = (this.currentPosition.getY() * tileSize) + center.getY();
            translateMeeplesPlacement.translate(delta_x, delta_y);

            Shape intermediate;

            g2.setColor(new Color(255, 20, 20, 100));
            for (String split : this.currentTileAggregate) {
                intermediate = translateMeeplesPlacement.createTransformedShape(resizeMeeplesPlacement.createTransformedShape(TILE_SPLITS.get(split)));
                g2.fill(intermediate);
            }
            g2.setColor(Color.BLACK);
        }
    }

    /**
     * On layer show triggered
     */
    @Override
    public void onShow()
    {
        super.onShow();
        // Attaches a mouse input listener to the layer
        this.attachMouseInputListener(new MeeplePlacementMouseAdapter(this.gridPanel, this, this.currentPosition));
    }

    @Override
    public void onHide()
    {
        super.onHide();
        this.removeMouseInputListener();
    }

    @Override
    public void tileEntered(MouseEvent e, UICoord p)
    {
        System.out.println("********************************** Entered");
    }

    @Override
    public void tileExited(MouseEvent e, UICoord p)
    {
        this.currentTileAggregate = null;
    }

    @Override
    public void mouseClicked(MouseEvent e, UICoord p)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void tileSliceEntered(MouseEvent e, UICoord p, String tileSlice)
    {
        if (this.currentPosition.equals(p)) {
            aggregatesLoop:
            for (Set<String> aggregate : this.tileAggregates) {
                for (String split : aggregate) {
                    if (split.equals(tileSlice)) {
                        this.currentTileAggregate = aggregate;
                        // TODO beautify this ugly piece of code !
                        break aggregatesLoop;
                    }

                }
            }
        }
        else {
            this.currentTileAggregate = null;
        }
        this.gridPanel.repaint();
    }

    @Override
    public void tileSliceExited(MouseEvent e)
    {
        this.currentTileAggregate = null;
        this.gridPanel.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e, UICoord p, String tileSlice)
    {
        if (this.currentPosition.equals(p)) {
            /*for (Set<String> aggregate : tileAggregates) {*/
                if (this.currentTileAggregate != null) {
                    try {
                        this.controller.putMeeple(tileSlice);
                    } catch (Exception ex) {
                        Logger.getLogger(MeeplePlacementLayer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    this.controller.endTurn();
                }
            /*}*/
        }
    }

    @Override
    public String getSliceFromCoordinates(double x, double y, UICoord tileCoordinates)
    {
        double x_filtered, y_filtered;
        int tileSize = this.gridPanel.getTileSize();
        UICoord center = this.gridPanel.getGraphicalCenter();
        x_filtered = ((x - (tileSize * tileCoordinates.getX())) - center.getX()) / (tileSize / 100.0);
        y_filtered = ((y - (tileSize * tileCoordinates.getY())) - center.getY()) / (tileSize / 100.0);
        for (Map.Entry<String, Polygon> entry : TILE_SPLITS.entrySet()) {
            if (entry.getValue().contains(x_filtered, y_filtered)) {
                return entry.getKey();
            }

        }
        return null;
    }

}
