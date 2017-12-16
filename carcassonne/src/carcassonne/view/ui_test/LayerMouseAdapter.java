/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.ui_test;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

/**
 * Mouse adapter for layer
 */
public class LayerMouseAdapter extends MouseAdapter
{
    private GridPanel gridPanel;
    private LayerMouseListener listener;
    
    private UICoord currentCoord;
    
    /**
     * Construction of layer mouse adapter
     * @param gridPanel
     * @param listener 
     */
    LayerMouseAdapter(GridPanel gridPanel, LayerMouseListener listener)
    {
        this.gridPanel = gridPanel;
        this.listener = listener;
    }
       
    /**
     * Get coordinates
     * @return 
     */
    public UICoord getCurrentCoord()
    {
        return this.currentCoord;
    }
    
    /**
     * When the mouse is moved
     * @param e 
     */
    @Override
    public void mouseMoved(MouseEvent e)
    {
        // Get the coordinates of the mouse
        Point2D point = e.getPoint();
        
        // Get the grid equivalent of the pixel coordinates
        UICoord c = this.gridPanel.getGridPositionFromCoordinates(point.getX(), point.getY());
        
        // If there was coordinates and it was not the current tile
        if (this.currentCoord != null && ! this.currentCoord.equals(c))
        {
            // Triggers the tile exited
            listener.tileExited(e, this.currentCoord);
            // Reset the coordinates
            this.currentCoord = null;
        }
        
        // If the current tile is not equals to the precedent
        if (c != null && ! c.equals(this.currentCoord)) {
            // Update the current coordinates
            this.currentCoord = c;
            //Triggers the tile entered
            listener.tileEntered(e, this.currentCoord);
        }
    }

    /**
     * When the mouse is clicked
     * @param e 
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        Point2D point = e.getPoint();
        // Get grid equivalent
        UICoord c = this.gridPanel.getGridPositionFromCoordinates(point.getX(), point.getY());
        if (c != null) {
            listener.mouseClicked(e, c);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    /**
     * When the mouse is entered
     * @param e 
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        mouseMoved(e);
    }

    /**
     * When the mouse is exited
     * @param e 
     */
    @Override
    public void mouseExited(MouseEvent e) {
        if (this.currentCoord != null) {
            listener.tileExited(e, this.currentCoord);
            this.currentCoord = null;
        }

    }
    
}
