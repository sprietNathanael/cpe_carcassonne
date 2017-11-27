/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.ui_test;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
/**
 *
 * @author nathanael
 */
public class GridPanel extends JPanel
{
    public static int INITIAL_TILE_WIDTH = 120;
    private Coord graphicalCenter;
    private int tileSize;
    private List<AbstractLayer> layers = new ArrayList<AbstractLayer>();

    public GridPanel()
    {
        this.tileSize = INITIAL_TILE_WIDTH;
        setDoubleBuffered(true);
        setOpaque(false);
        setLayout(new MigLayout());
    }
    
    public void addLayer(AbstractLayer test)
    {
        layers.add(test);
        test.onShow();
    }

    @Override
    protected void paintChildren(Graphics g)
    {
        this.graphicalCenter = new Coord(this.getWidth()/2,this.getHeight()/2);
        Graphics2D g2 = (Graphics2D) g;
        
        // X
        // Y
        for(AbstractLayer layer : layers)
        {
            layer.paint(g2);
        }
        super.paintChildren(g);
    }
    
    public int getTileSize()
    {
        return this.tileSize;
    }
    
    public Coord getGraphicalCenter()
    {
        return this.graphicalCenter;
    }
    
    public Coord getGridPositionFromCoordinates(double x, double y)
    {
        double adjustedX = x - this.graphicalCenter.getX();
        double adjustedY = y - this.graphicalCenter.getY();
        int gridX = (int)((x-this.graphicalCenter.getX()) / this.tileSize);
        int gridY = (int)((y-this.graphicalCenter.getY()) / this.tileSize);
        gridX = adjustedX > 0 ? gridX : gridX - 1;
        gridY = adjustedY > 0 ? gridY : gridY -1;
        return new Coord(gridX,gridY);
    }
    
    
}
