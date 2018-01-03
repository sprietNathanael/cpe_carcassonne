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
 *
 * @author nathanael
 */
public class MeeplePlacementMouseAdapter extends LayerMouseAdapter
{
    private final GridPanel gridPanel;
    private final MeeplePlacementMouseListener listener;
    
    private UICoord currentCoord;
    private String tileSlice;
    private String lastSlice;

    public MeeplePlacementMouseAdapter(GridPanel gridPanel, MeeplePlacementMouseListener listener, UICoord currentCoord)
    {
        this.gridPanel = gridPanel;
        this.listener = listener;
        this.currentCoord = currentCoord;
        this.lastSlice = "";
    }

    public String getTileSlice()
    {
        return tileSlice;
    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
       // Get the coordinates of the mouse
        Point2D point = e.getPoint();
        
        // Get the grid equivalent of the pixel coordinates
        UICoord c = this.gridPanel.getGridPositionFromCoordinates(point.getX(), point.getY());
        
        // If the tile entered is the current tile
        if (c != null && c.equals(this.currentCoord)) {
            //Triggers the tile entered
            listener.tileSliceEntered(e, this.currentCoord, listener.getSliceFromCoordinates(point.getX(), point.getY(), this.currentCoord));            
        }
        else
        {
            listener.tileSliceExited(e);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        // Get the coordinates of the mouse
        Point2D point = e.getPoint();
        
        // Get the grid equivalent of the pixel coordinates
        UICoord c = this.gridPanel.getGridPositionFromCoordinates(point.getX(), point.getY());
        
        // If the tile entered is the current tile
        if (c != null && c.equals(this.currentCoord)) {
            //Triggers the tile entered
            listener.mouseClicked(e, this.currentCoord, listener.getSliceFromCoordinates(point.getX(), point.getY(), this.currentCoord));
        }
    }
    
    
}
