/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.CarcassonneIHM.Layers.Meeple;

import carcassonne.view.CarcassonneIHM.Panels.Grid.GridPanel;
import carcassonne.view.CarcassonneIHM.Layers.LayerMouseAdapter;
import carcassonne.view.CarcassonneIHM.Tools.UICoord;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

/**
 * A mouse adapter for the meeple placement layer
 * @author nathanael
 */
public class MeeplePlacementMouseAdapter extends LayerMouseAdapter
{
    private final GridPanel gridPanel;
    private final MeeplePlacementMouseListener listener;
    
    private final UICoord currentCoord;
    private String tileSlice;
    private String lastSlice;

    /**
     * Construct the mouse adapter
     * @param gridPanel
     * @param listener
     * @param currentCoord 
     */
    public MeeplePlacementMouseAdapter(GridPanel gridPanel, MeeplePlacementMouseListener listener, UICoord currentCoord)
    {
        this.gridPanel = gridPanel;
        this.listener = listener;
        this.currentCoord = currentCoord;
        this.lastSlice = "";
    }
    
    /**
     * Get the tile slice
     * @return 
     */
    public String getTileSlice()
    {
        return tileSlice;
    }

    @Override
    /**
     * When the mouse moves
     */
    public void mouseMoved(MouseEvent e)
    {
       // Get the coordinates of the mouse
        Point2D point = e.getPoint();
        
        // Get the grid equivalent of the pixel coordinates
        UICoord c = this.gridPanel.getGridPositionFromCoordinates(point.getX(), point.getY());
        
        // If the tile entered is the current tile
        if (c != null && c.equals(this.currentCoord)) {
            //Triggers the tile entered
            String slice = listener.getSliceFromCoordinates(point.getX(), point.getY(), this.currentCoord);
            // If the slice entered is not the current slice
            if(slice != null && !slice.equals(this.lastSlice))
            {
                this.lastSlice = slice;
                // Exit the old slice
                listener.tileSliceExited(e);
                // Enter the new slice
                listener.tileSliceEntered(e, this.currentCoord, slice);               
            }
        }
        else
        {
            listener.tileSliceExited(e);
        }
    }

    /**
     * When the mouse is clicked
     * @param e 
     */
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
