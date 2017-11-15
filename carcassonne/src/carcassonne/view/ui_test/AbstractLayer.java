/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.ui_test;

import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author nathanael
 */
public class AbstractLayer
{
    GridPanel gc;
    protected ArrayList<Coord> positions;
    
    public AbstractLayer(GridPanel gc)
    {
        this.gc = gc;
        this.positions = new ArrayList<Coord>();
    }
    
    public void addPosition(Coord pos)
    {
        this.positions.add(pos);
    }
    
    public void paint(Graphics2D g2)
    {
    }
    
}
