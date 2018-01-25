/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.CarcassonneIHM.Layers.Meeple;

import carcassonne.controller.CarcassonneGameController;
import carcassonne.coord.Coord;
import carcassonne.model.player.Meeple;
import carcassonne.view.CarcassonneIHM.Panels.Grid.GridPanel;
import carcassonne.view.CarcassonneIHM.Layers.AbstractLayer;
import carcassonne.view.CarcassonneIHM.Panels.Info.PlayerInfo;
import carcassonne.view.CarcassonneIHM.Tools.UICoord;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;
import javax.imageio.ImageIO;

/**
 * Layer that contains all the placed meeples
 * @author nathanael
 */
public class MeeplesLayer extends AbstractLayer
{
    // Constants
    public static final String MEEPLE_COLORS[] = {"red", "blue", "black", "magenta", "green", "yellow"};
    public static final double MEEPLE_SIZE = 0.2;
    
    //TODO
    // Simplify this ridiculously complicated hashmap
    // This hashmap represents the meeple locations
    // Each coordinates has a pair of
    // - String that represents the location name on the tile
    // - Pair of :
    //      - Meeple
    //      - String that represents the type name (important for the field variant)
    private HashMap<Coord, Pair<String, Pair<Meeple, String>>> meepleLocations;
    private HashMap<String, BufferedImage> meepleImages;
    private HashMap<String, BufferedImage> meepleImages_big;
    private HashMap<String, BufferedImage> meepleImages_field;
    private HashMap<String, BufferedImage> meepleImages_field_big;

    /**
     * Construct the meeple layer
     * @param gridPanel
     * @param controller 
     */
    public MeeplesLayer(GridPanel gridPanel, CarcassonneGameController controller)
    {
        super(gridPanel, controller);
        this.meepleImages = new HashMap<>();
        this.meepleImages_big = new HashMap<>();
        this.meepleImages_field = new HashMap<>();
        this.meepleImages_field_big = new HashMap<>();
        this.meepleLocations = new HashMap<>();
        // Build all the possible meeple images
        this.buildMeepleImages();
    }
    
    /**
     * Build all the possible meeple images
     */
    public void buildMeepleImages()
    {
        BufferedImage image;
        // Browse all colors
        for(String color : MEEPLE_COLORS)
        {
            image = null;
            // Build the basic meeple
            try
            {
                image = ImageIO.read(new File("resources/meeples/"+color+".png"));
            } catch (IOException ex) {
                Logger.getLogger(PlayerInfo.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.meepleImages.put(color, image);
            
            // Build the basic meeple big
            try
            {
                image = ImageIO.read(new File("resources/meeples/"+color+"_big.png"));
            } catch (IOException ex) {
                Logger.getLogger(PlayerInfo.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.meepleImages_big.put(color, image);
            
            // Build the field version of the meeple
            try
            {
                image = ImageIO.read(new File("resources/meeples_field/"+color+".png"));
            } catch (IOException ex) {
                Logger.getLogger(PlayerInfo.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.meepleImages_field.put(color, image);
            
            // Build the field version of the meeple
            try
            {
                image = ImageIO.read(new File("resources/meeples_field/"+color+"_big.png"));
            } catch (IOException ex) {
                Logger.getLogger(PlayerInfo.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.meepleImages_field_big.put(color, image);
        }
    }
    
    /**
     * Paint the layer
     * @param g2 
     */
    @Override
    public void paint(Graphics2D g2)
    {
        if(this.isVisible())
        {
            UICoord center = this.gridPanel.getGraphicalCenter();
            int tileSize = this.gridPanel.getTileSize();
            
            // Browse the meeple placements
            for(Map.Entry<Coord, Pair<String, Pair<Meeple, String>>> entry : this.meepleLocations.entrySet())
            {
                Coord coord = entry.getKey();
                int tile_x = center.getX() + (tileSize * coord.col);
                int tile_y = center.getY() + (tileSize * coord.row);
                UICoord sliceCoord;
                // Get the polygon that represents the meeple location
                Polygon meepleLocation = MeeplePlacementLayer.TILE_SPLITS.get(entry.getValue().getKey());
                if(meepleLocation != null)
                {
                    // Get the bounds of the polygon
                    Rectangle bounds = meepleLocation.getBounds();
                    int x_left = (int)(bounds.x * (tileSize/100.0));
                    int x_right = (int)((bounds.x+bounds.width) * (tileSize/100.0));
                    int y_top = (int)(bounds.y * (tileSize/100.0));
                    int y_bottom = (int)((bounds.y+bounds.height) * (tileSize/100.0));
                    
                    // Place the meeple on the center of the bounds
                    int meepleSize = (int)(tileSize*MEEPLE_SIZE);
                    int meeple_x = tile_x + x_left + (x_right-x_left) - ((int)(tileSize * MEEPLE_SIZE));
                    int meeple_y = tile_y + y_top + (y_bottom-y_top) - ((int)(tileSize * MEEPLE_SIZE));
                    
                    Meeple meeple = entry.getValue().getValue().getKey();
                    String type = entry.getValue().getValue().getValue();
                    
                    // If the type is a field, use the variant
                    if(type.equals("Fi"))
                    {
                        if(meeple.getIsBig())
                        {
                            g2.drawImage(this.meepleImages_field_big.get(meeple.getPlayer().getColor()), meeple_x, meeple_y, meepleSize, meepleSize , null);
                        }
                        else
                        {
                            g2.drawImage(this.meepleImages_field.get(meeple.getPlayer().getColor()), meeple_x, meeple_y, meepleSize, meepleSize , null);
                        }
                    }
                    else
                    {
                        if(meeple.getIsBig())
                        {
                            g2.drawImage(this.meepleImages_big.get(meeple.getPlayer().getColor()), meeple_x, meeple_y, meepleSize, meepleSize , null);
                        }
                        else
                        {
                            g2.drawImage(this.meepleImages.get(meeple.getPlayer().getColor()), meeple_x, meeple_y, meepleSize, meepleSize , null);
                        }
                    }

                }

            }
            
        }
    }
    
    /**
     * Set the meeple locations
     * @param meepleLocations 
     */
    public void setMeeplesLocations(HashMap<Coord, Pair<String, Pair<Meeple, String>>> meepleLocations)
    {
        this.meepleLocations = meepleLocations;
    }
    
    /**
     * Add a meeple
     * @param coord
     * @param location
     * @param meeple
     * @param type 
     */
    public void addMeeple(Coord coord, String location, Meeple meeple, String type)
    {
        this.meepleLocations.put(coord, new Pair<>(location, new Pair<>(meeple,type)));
    }
    
    /**
     * Clean all meeples
     */
    public void cleanMeeple()
    {
        this.meepleLocations.clear();
    }
    
}
