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
    private List<TestLayer> layers = new ArrayList<TestLayer>();

    public GridPanel()
    {
        this.tileSize = INITIAL_TILE_WIDTH;
        setDoubleBuffered(true);
        setOpaque(false);
        setLayout(new MigLayout());
    }
    
    public void addLayer(TestLayer test)
    {
        layers.add(test);
    }

    @Override
    protected void paintChildren(Graphics g)
    {
        this.graphicalCenter = new Coord(this.getWidth()/2,this.getHeight()/2);
        Graphics2D g2 = (Graphics2D) g;
        
        // X
        // Y
        for(TestLayer layer : layers)
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
    
    
}
