/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.CarcassonneIHM;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

/**
 * A mouse adapter for the grid
 */
public class GridMouseAdapter extends MouseAdapter
{
    private MouseEvent dragSource;
    private final GridPanel gridPanel;
    private UICoord sourceCenter;
    
    /**
     * Constructs a grid mouse adapter
     * @param gridPanel 
     */
    GridMouseAdapter(GridPanel gridPanel)
    {
        this.gridPanel = gridPanel;
    }

    /**
     * When the mouse is dragged
     * @param e 
     */
    @Override
    public void mouseDragged(MouseEvent e)
    {
        // Get the delta component of the dragging
        int dx = e.getX() - this.dragSource.getX();
        int dy = e.getY() - this.dragSource.getY();
        
        // Get the new graphical center
        UICoord newCenter = new UICoord(this.sourceCenter.getX()+dx, this.sourceCenter.getY()+dy);
        
        // Set the new graphical center of the gridpanel
        this.gridPanel.moveCenterTo(newCenter);        
    }
    
    /**
     * When the mouse is pressed, saves the drag source and the current graphical centers
     * @param e 
     */
    @Override
    public void mousePressed(MouseEvent e)
    {
        this.dragSource = e;
        this.sourceCenter = this.gridPanel.getGraphicalCenter();
    }
    
    /**
     * When the mouse is released, reset the drag source
     * @param e 
     */
    @Override
    public void mouseReleased(MouseEvent e)
    {
        this.dragSource = null;
        this.sourceCenter = null;
    }   

    /**
     * When the mouse wheel moved
     * @param e 
     */
    @Override
    public void mouseWheelMoved(MouseWheelEvent e)
    {
        // Update the zoom of the gridPanel
        this.gridPanel.zoom(-e.getWheelRotation());
    }
    
}
