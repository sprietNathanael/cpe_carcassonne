/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.ui_test;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author nathanael
 */
public class PlacementLayer extends AbstractLayer
{
    
    public PlacementLayer(GridPanel gc)
    {
        super(gc);
        this.positions = new ArrayList<Coord>();
    }
        
    public void paint(Graphics2D g2)
    {
        System.out.println("carcassonne.view.ui_test.PlacementLayer.paint()");
        int placeHolderSize = this.gc.getTileSize();
        int shift = placeHolderSize/30;
        int thickness = placeHolderSize/14;
        int tileSize = placeHolderSize - (2*shift);
        Coord center = this.gc.getGraphicalCenter();
        g2.setColor(Color.LIGHT_GRAY);
        for(Coord p : this.positions)
        {
            int x = center.getX()+(placeHolderSize*p.getX())+shift;
            int y = center.getY()+(placeHolderSize*p.getY())+shift;
            g2.fillRect(x,y,tileSize, thickness);
            g2.fillRect(x, y+tileSize-thickness, tileSize, thickness);
            g2.fillRect(x, y, thickness, tileSize);
            g2.fillRect(x+tileSize-thickness, y, thickness, tileSize);
        }
    }
    
}
