/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.ui_test;

import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import javax.swing.event.MouseInputListener;

/**
 *
 * @author nathanael
 */
public class AbstractLayer
{
    GridPanel gc;
    protected ArrayList<Coord> positions;
    private LayerMouseAdapter mouseListener;
    private boolean visible;
    
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
    
    public void attachMouseInputListener(LayerMouseAdapter mouseListener) {
        this.mouseListener = mouseListener;
        this.gc.addMouseListener(this.mouseListener);
        this.gc.addMouseMotionListener(this.mouseListener);
    }
    
    public boolean isVisible()
    {
        return this.visible;
    }
    
    public void onShow()
    {
        this.visible = true;
    }
    
    public void onHide()
    {
        this.visible = false;
    }
            
}
