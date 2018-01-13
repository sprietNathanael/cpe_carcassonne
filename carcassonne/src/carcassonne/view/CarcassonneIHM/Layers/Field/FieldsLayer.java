/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.CarcassonneIHM.Layers.Field;

import carcassonne.controller.AbstractCarcassonneGameController;
import carcassonne.coord.Coord;
import carcassonne.model.aggregate.FieldAggregate;
import carcassonne.model.carcassonnegame.CarcassonneGame;
import carcassonne.model.player.Player;
import carcassonne.model.tile.AbstractTile;
import carcassonne.view.CarcassonneIHM.Layers.AbstractLayer;
import static carcassonne.view.CarcassonneIHM.Layers.Meeple.MeeplePlacementLayer.TILE_SPLITS;
import carcassonne.view.CarcassonneIHM.Panels.Grid.GridPanel;
import carcassonne.view.CarcassonneIHM.Tools.UICoord;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 *
 * @author nathanael
 */
public class FieldsLayer extends AbstractLayer
{
    private List<FieldAggregate> fields;
    private static int MASK_ALPHA = 100;
    public static HashMap<String, Color> MASK_COLORS;
    static
    {
        MASK_COLORS = new HashMap<>();
        MASK_COLORS.put("red", new Color(255,0,0, MASK_ALPHA));
        MASK_COLORS.put("black", new Color(0,0,0,MASK_ALPHA));
        MASK_COLORS.put("blue", new Color(0,82,232,MASK_ALPHA));
        MASK_COLORS.put("green", new Color(0,187,26,MASK_ALPHA));
        MASK_COLORS.put("magenta", new Color(136,0,138,MASK_ALPHA));
        MASK_COLORS.put("yellow", new Color(244,241,MASK_ALPHA));
        MASK_COLORS.put("transp", new Color(255,255,255,0));
    }
    
    public FieldsLayer(GridPanel gridPanel, AbstractCarcassonneGameController controller)
    {
        super(gridPanel, controller);
    }
    
    public void setFields(List<FieldAggregate> fields)
    {
        this.fields = fields;
    }

    @Override
    public void paint(Graphics2D g2)
    {
        if(this.isVisible())
        {
            Shape intermediate;
            // Affine transform to resize tiles splits
            AffineTransform resizePlacement;
            AffineTransform translatePlacement;
            int tileSize = this.gridPanel.getTileSize();
            UICoord center = this.gridPanel.getGraphicalCenter();
            
            for(FieldAggregate field : this.fields)
            {
                Set<Player> players = field.getWinningPlayers();
                if(!players.isEmpty())
                {
                    Area superShape = new Area();
                    g2.setColor(Color.red);
                    Map<Coord, AbstractTile> tiles = field.getAggregatedTiles();
                    for(Coord coord : tiles.keySet())
                    {
                        Set<String> locations = field.getTileLocations(coord.col, coord.row);
                        Coord coordCorrected = CarcassonneGame.convertCoord(coord.col, coord.row);
                        
                        resizePlacement = new AffineTransform();
                        translatePlacement = new AffineTransform();
                        
                        resizePlacement.scale(tileSize / 100.0, tileSize / 100.0);
            
                        // Translation to apply the center
                        double delta_x = (coordCorrected.col * tileSize) + center.getX();
                        double delta_y = (coordCorrected.row * tileSize) + center.getY();
                        translatePlacement.translate(delta_x, delta_y);
                        
                        for(String location : locations)
                        {
                            intermediate = translatePlacement.createTransformedShape(resizePlacement.createTransformedShape(TILE_SPLITS.get(location)));
                            Area area = new Area(intermediate);
                            superShape.add(area);
                            //g2.fill(intermediate);
                        }
                    }
                    
                    //g2.fill(superShape);
                    Rectangle bounds = superShape.getBounds();
                    ArrayList<String> colors = new ArrayList<String>();
                    for(Player p : players)
                    {
                        colors.add(p.getColor());
                    }
                    colors.add("transp");
                    String color = null;
                    int top_x = bounds.x;
                    int top_y = bounds.y;
                    int bottom_x = top_x+bounds.width;
                    int bottom_y = top_y+bounds.height;
                    if(bounds.width < bounds.height)
                    {
                        bottom_y = top_y + bounds.height;
                        bottom_x = top_x + bounds.height;
                    }
                    else if(bounds.height < bounds.width)
                    {
                        bottom_x = top_x + bounds.width;
                        bottom_y = top_y + bounds.width;
                    }
                    int stripeSize = (int)((tileSize / 6.0)/2);
                    int shift = stripeSize;
                    int stripeXNumber = (int)(bounds.width / shift);
                    int stripeYNumber = (int)(bounds.height / shift);

                    Polygon stripe = new Polygon();
                    Iterator iter = colors.iterator();
                    for(int i = 0; i < stripeYNumber; i++)
                    {
                        try {
                            color = (String) iter.next();
                        } catch (NoSuchElementException e) {
                            iter = colors.iterator();
                            color = (String) iter.next();
                        }

                        // Construct the stripe
                        stripe.reset();
                        stripe.addPoint(top_x, top_y+(i*shift));
                        stripe.addPoint(top_x, top_y+(i*shift)+stripeSize);
                        stripe.addPoint(bottom_x, bottom_y+(i*shift)+stripeSize);
                        stripe.addPoint(bottom_x, bottom_y+(i*shift));

                        Area stripe_area = new Area(stripe);
                        stripe_area.intersect(superShape);

                        g2.setColor(MASK_COLORS.get(color));
                        g2.fill(stripe_area);
                    }

                    iter = colors.iterator();
                    iter.next();
                    for(int i = 0; i < stripeXNumber; i++)
                    {
                        try {
                            color = (String) iter.next();
                        } catch (NoSuchElementException e) {
                            iter = colors.iterator();
                            color = (String) iter.next();
                        }

                        // Construct the stripe
                        stripe.reset();
                        stripe.addPoint(top_x+(i*shift), top_y);
                        stripe.addPoint(top_x+(i*shift)+stripeSize, top_y);
                        stripe.addPoint(bottom_x+(i*shift)+stripeSize, bottom_y);
                        stripe.addPoint(bottom_x+(i*shift), bottom_y);

                        Area stripe_area = new Area(stripe);
                        stripe_area.intersect(superShape);

                        g2.setColor(MASK_COLORS.get(color));
                        g2.fill(stripe_area);
                    }                    
                }
            }
            g2.setColor(Color.BLACK);
        }
    }
    
}
