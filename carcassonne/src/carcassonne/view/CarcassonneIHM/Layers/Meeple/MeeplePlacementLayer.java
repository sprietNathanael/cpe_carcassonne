/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.CarcassonneIHM.Layers.Meeple;

import carcassonne.controller.AbstractCarcassonneGameController;
import carcassonne.model.tile.AbstractTile;
import carcassonne.view.CarcassonneIHM.Panels.Grid.GridPanel;
import carcassonne.view.CarcassonneIHM.Layers.AbstractLayer;
import carcassonne.view.CarcassonneIHM.Layers.Tile.TilePlacementMouseListener;
import carcassonne.view.CarcassonneIHM.Panels.Info.InfoPanel;
import carcassonne.view.CarcassonneIHM.Tools.UICoord;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Layer that contains all the meeple location possibilities
 * @author nathanael
 */
public class MeeplePlacementLayer extends AbstractLayer implements TilePlacementMouseListener, MeeplePlacementMouseListener
{

    // Constant hashmap of polygon representations of the tile slicing
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

    /**
     * Construct the meeple placement layer
     * @param gridPanel
     * @param controller 
     */
    public MeeplePlacementLayer(GridPanel gridPanel, AbstractCarcassonneGameController controller)
    {
        super(gridPanel, controller);
        this.tileAggregates = new HashSet<>();
        this.currentTileAggregate = null;
    }

    /**
     * Set the aggregates from a tile
     * @param currentTile 
     */
    public void setAggregates(AbstractTile currentTile)
    {
        this.tileAggregates = currentTile.getAggregateEmplacements();
    }
    
    /**
     * Set the aggregates from a Set
     * @param aggregates 
     */
    public void setAggregates(Set<Set<String>> aggregates)
    {
        this.tileAggregates = aggregates;
    }
    
    /**
     * Clean all aggregates
     */
    public void cleanAggregates()
    {
        this.tileAggregates.clear();
    }
    
    /**
     * Set the current position on the tile
     * @param c 
     */
    public void setCurrentPosition(UICoord c)
    {
        this.currentPosition = c;
    }

    /**
     * Paint the component
     * @param g2 
     */
    @Override
    public void paint(Graphics2D g2)
    {
        if(this.isVisible())
        {
            int tileSize = this.gridPanel.getTileSize();
            UICoord center = this.gridPanel.getGraphicalCenter();
            
            

            // Affine transform to resize tiles splits
            AffineTransform resizeMeeplesPlacement = new AffineTransform();
            AffineTransform translateMeeplesPlacement = new AffineTransform();
            resizeMeeplesPlacement.scale(tileSize / 100.0, tileSize / 100.0);
            
            // Translation to apply the center
            double delta_x = (this.currentPosition.getX() * tileSize) + center.getX();
            double delta_y = (this.currentPosition.getY() * tileSize) + center.getY();
            translateMeeplesPlacement.translate(delta_x, delta_y);
            
            int tile_x = (int)delta_x;
            int tile_y = (int)delta_y;
            
            GradientPaint gradient = new GradientPaint(tile_x, tile_y, new Color(255, 255, 255, 200), tile_x, tile_y-InfoPanel.HIGHLIGHT_GRADIENT_THICKNESS,  new Color(255, 255, 255, 0));
            g2.setPaint(gradient);            
            Polygon border = new Polygon();
            border.addPoint(tile_x-InfoPanel.HIGHLIGHT_GRADIENT_THICKNESS, tile_y - InfoPanel.HIGHLIGHT_GRADIENT_THICKNESS);
            border.addPoint(tile_x+tileSize+InfoPanel.HIGHLIGHT_GRADIENT_THICKNESS, tile_y - InfoPanel.HIGHLIGHT_GRADIENT_THICKNESS);
            border.addPoint(tile_x+tileSize, tile_y);
            border.addPoint(tile_x, tile_y);
            g2.fillPolygon(border);

            gradient = new GradientPaint(tile_x+tileSize, tile_y, new Color(255, 255, 255, 200), tile_x+tileSize + InfoPanel.HIGHLIGHT_GRADIENT_THICKNESS, tile_y, new Color(255, 255, 255, 0));
            g2.setPaint(gradient);
            
            border.reset();
            border.addPoint(tile_x+tileSize+InfoPanel.HIGHLIGHT_GRADIENT_THICKNESS, tile_y - InfoPanel.HIGHLIGHT_GRADIENT_THICKNESS);
            border.addPoint(tile_x+tileSize+InfoPanel.HIGHLIGHT_GRADIENT_THICKNESS, tile_y +tileSize + InfoPanel.HIGHLIGHT_GRADIENT_THICKNESS);
            border.addPoint(tile_x+tileSize, tile_y +tileSize);
            border.addPoint(tile_x+tileSize, tile_y );
            g2.fillPolygon(border);
            
            gradient = new GradientPaint(tile_x, tile_y+tileSize, new Color(255,255,255, 200), tile_x, tile_y+tileSize+InfoPanel.HIGHLIGHT_GRADIENT_THICKNESS, new Color(255, 255, 255, 0));
            g2.setPaint(gradient);
            
            border.reset();
            border.addPoint(tile_x+tileSize+InfoPanel.HIGHLIGHT_GRADIENT_THICKNESS, tile_y + tileSize + InfoPanel.HIGHLIGHT_GRADIENT_THICKNESS);
            border.addPoint(tile_x - InfoPanel.HIGHLIGHT_GRADIENT_THICKNESS, tile_y +tileSize + InfoPanel.HIGHLIGHT_GRADIENT_THICKNESS);
            border.addPoint(tile_x, tile_y +tileSize);
            border.addPoint(tile_x+tileSize, tile_y + tileSize );
            g2.fillPolygon(border);

            gradient = new GradientPaint(tile_x, tile_y, new Color(255,255,255, 200), tile_x - InfoPanel.HIGHLIGHT_GRADIENT_THICKNESS, tile_y, new Color(255, 255, 255, 0));
            g2.setPaint(gradient);
            
            border.reset();
            border.addPoint(tile_x-InfoPanel.HIGHLIGHT_GRADIENT_THICKNESS, tile_y - InfoPanel.HIGHLIGHT_GRADIENT_THICKNESS);
            border.addPoint(tile_x, tile_y);
            border.addPoint(tile_x, tile_y +tileSize);
            border.addPoint(tile_x-InfoPanel.HIGHLIGHT_GRADIENT_THICKNESS, tile_y + tileSize + InfoPanel.HIGHLIGHT_GRADIENT_THICKNESS);
            g2.fillPolygon(border);
            
            if(this.currentTileAggregate != null && this.tileAggregates.size() > 0)
            {
                Shape intermediate;

                g2.setColor(new Color(255, 20, 20, 100));

                // Draw all the location from the aggregate
                for (String split : this.currentTileAggregate) {
                    intermediate = translateMeeplesPlacement.createTransformedShape(resizeMeeplesPlacement.createTransformedShape(TILE_SPLITS.get(split)));
                    g2.fill(intermediate);
                }
                g2.setColor(Color.BLACK);
            }
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

    /**
     * On layer hide
     */
    @Override
    public void onHide()
    {
        super.onHide();
        this.removeMouseInputListener();
        this.cleanAggregates();
    }

    @Override
    public void tileEntered(MouseEvent e, UICoord p)
    {
    }

    @Override
    public void tileExited(MouseEvent e, UICoord p)
    {
        this.currentTileAggregate = null;
    }

    @Override
    public void mouseClicked(MouseEvent e, UICoord p)
    {
    }

    /**
     * On a tile slice entered
     * @param e
     * @param p
     * @param tileSlice 
     */
    @Override
    public void tileSliceEntered(MouseEvent e, UICoord p, String tileSlice)
    {
        if (this.currentPosition.equals(p)) {
            // Loop all the aggregates
            aggregatesLoop:
            for (Set<String> aggregate : this.tileAggregates) {
                // Loop all the locations of the aggregate
                for (String split : aggregate) {
                    // If the current tile slice matches the split
                    if (split.equals(tileSlice)) {
                        // Get the aggregate
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
        // Refreshes the component
        this.gridPanel.repaint();
    }

    /**
     * On a tile slice exited
     * @param e 
     */
    @Override
    public void tileSliceExited(MouseEvent e)
    {
        this.currentTileAggregate = null;
        this.gridPanel.repaint();
    }

    /**
     * On a mouse clicked event
     * @param e
     * @param p
     * @param tileSlice 
     */
    @Override
    public void mouseClicked(MouseEvent e, UICoord p, String tileSlice)
    {
        if (this.currentPosition.equals(p)) {
            if (this.currentTileAggregate != null) {
                try {
                    // Put the meeple
                    this.controller.putMeeple(tileSlice);
                } catch (Exception ex) {
                    Logger.getLogger(MeeplePlacementLayer.class.getName()).log(Level.SEVERE, null, ex);
                }
                // End the turn
                this.controller.endTurn();
                this.currentTileAggregate = null;
            }
        }
    }

    /**
     * Get the slice name from coordinates and a tile coordinates
     * @param x
     * @param y
     * @param tileCoordinates
     * @return 
     */
    @Override
    public String getSliceFromCoordinates(double x, double y, UICoord tileCoordinates)
    {
        double x_filtered, y_filtered;
        int tileSize = this.gridPanel.getTileSize();
        UICoord center = this.gridPanel.getGraphicalCenter();
        // Apply the tile size and the center
        x_filtered = ((x - (tileSize * tileCoordinates.getX())) - center.getX()) / (tileSize / 100.0);
        y_filtered = ((y - (tileSize * tileCoordinates.getY())) - center.getY()) / (tileSize / 100.0);
        // Browses the splits
        for (Map.Entry<String, Polygon> entry : TILE_SPLITS.entrySet()) {
            if (entry.getValue().contains(x_filtered, y_filtered)) {
                return entry.getKey();
            }

        }
        return null;
    }

}
