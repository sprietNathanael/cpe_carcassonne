/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.CarcassonneIHM;

import carcassonne.controller.AbstractCarcassonneGameController;
import carcassonne.coord.Coord;
import carcassonne.model.aggregate.FieldAggregate;
import carcassonne.model.player.Meeple;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;
import javax.imageio.ImageIO;

/**
 *
 * @author nathanael
 */
public class MeeplesLayer extends AbstractLayer
{
    public static final String MEEPLE_COLORS[] = {"red", "blue", "black", "magenta", "green", "yellow"};
    public static final double MEEPLE_SIZE = 0.2;
    private HashMap<Coord, Pair<String, Pair<Meeple, String>>> meepleLocations;
    private HashMap<String, BufferedImage> meepleImages;
    private HashMap<String, BufferedImage> meepleImages_field;

    public MeeplesLayer(GridPanel gridPanel, AbstractCarcassonneGameController controller)
    {
        super(gridPanel, controller);
        this.meepleImages = new HashMap<String,BufferedImage>();
        this.meepleImages_field = new HashMap<String,BufferedImage>();
        this.meepleLocations = new HashMap<Coord, Pair<String, Pair<Meeple, String>>>();
        this.buildMeepleImages();
    }
    
    public void buildMeepleImages()
    {
        BufferedImage image;
        for(String color : MEEPLE_COLORS)
        {
            image = null;
            try
            {
                image = ImageIO.read(new File("resources/meeples/"+color+".png"));
            } catch (IOException ex) {
                Logger.getLogger(PlayerInfo.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.meepleImages.put(color, image);
            try
            {
                image = ImageIO.read(new File("resources/meeples_field/"+color+".png"));
            } catch (IOException ex) {
                Logger.getLogger(PlayerInfo.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.meepleImages_field.put(color, image);
        }
    }
    
    @Override
    public void paint(Graphics2D g2)
    {
        if(this.isVisible())
        {
            UICoord center = this.gridPanel.getGraphicalCenter();
            int tileSize = this.gridPanel.getTileSize();
            for(Map.Entry<Coord, Pair<String, Pair<Meeple, String>>> entry : this.meepleLocations.entrySet())
            {
                Coord coord = entry.getKey();
                int tile_x = center.getX() + (tileSize * coord.col);
                int tile_y = center.getY() + (tileSize * coord.row);
                UICoord sliceCoord;
                Polygon meepleLocation = MeeplePlacementLayer.TILE_SPLITS.get(entry.getValue().getKey());
                if(meepleLocation != null)
                {
                    Rectangle bounds = meepleLocation.getBounds();
                    int x_left = (int)(bounds.x * (tileSize/100.0));
                    int x_right = (int)((bounds.x+bounds.width) * (tileSize/100.0));
                    int y_top = (int)(bounds.y * (tileSize/100.0));
                    int y_bottom = (int)((bounds.y+bounds.height) * (tileSize/100.0));

                    int meepleSize = (int)(tileSize*MEEPLE_SIZE);
                    int meeple_x = tile_x + x_left + (x_right-x_left) - ((int)(tileSize * MEEPLE_SIZE));
                    int meeple_y = tile_y + y_top + (y_bottom-y_top) - ((int)(tileSize * MEEPLE_SIZE));
                    Meeple meeple = entry.getValue().getValue().getKey();
                    String type = entry.getValue().getValue().getValue();
                    if(type.equals("Fi"))
                    {
                        g2.drawImage(this.meepleImages_field.get(meeple.getPlayer().getColor()), meeple_x, meeple_y, meepleSize, meepleSize , null);
                    }
                    else
                    {
                        g2.drawImage(this.meepleImages.get(meeple.getPlayer().getColor()), meeple_x, meeple_y, meepleSize, meepleSize , null);
                    }

                }

            }
            
        }
    }
    
    public void setMeeplesLocations(HashMap<Coord, Pair<String, Pair<Meeple, String>>> meepleLocations)
    {
        this.meepleLocations = meepleLocations;
    }
    
    public void addMeeple(Coord coord, String location, Meeple meeple, String type)
    {
        this.meepleLocations.put(coord, new Pair<>(location, new Pair<>(meeple,type)));
    }
    
    public void cleanMeeple()
    {
        this.meepleLocations.clear();
    }
    
}
