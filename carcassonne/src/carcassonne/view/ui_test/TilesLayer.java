/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.ui_test;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author nathanael
 */
public class TilesLayer extends AbstractLayer
{
    public TilesLayer(GridPanel gc)
    {
        super(gc);
    }
    
    public void paint(Graphics2D g2)
    {
        System.out.println("carcassonne.view.ui_test.TilesLayer.paint()");
        int tileSize = this.gc.getTileSize();
        Coord center = this.gc.getGraphicalCenter();
        for(Coord p : this.positions)
        {
            int x = center.getX()+(tileSize*p.getX());
            int y = center.getY()+(tileSize*p.getY());
            TileImage tileImage = (TileImage)p;
            g2.drawImage(tileImage.getImage(), x, y, tileSize, tileSize, null);
        }
    }
}
