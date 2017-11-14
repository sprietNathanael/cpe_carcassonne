/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.ui_test;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Set;

/**
 *
 * @author nathanael
 */
public class TestLayer
{
    private ArrayList<Coord> positions;
    GridPanel gc;
    

    public TestLayer(GridPanel gc)
    {
        this.gc = gc;
        this.positions = new ArrayList<>();
    }
    
    public void addPosition(Coord pos)
    {
        this.positions.add(pos);
    }
    
    public void paint(Graphics2D g2)
    {
        int placeHolderSize = this.gc.getTileSize();
        int shift = placeHolderSize/30;
        int thickness = placeHolderSize/14;
        int tileSize = placeHolderSize - (2*shift);
        Coord center = this.gc.getGraphicalCenter();
        g2.setColor(Color.LIGHT_GRAY);
        System.out.println("carcassonne.view.ui_test.TestLayer.paint()");
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
