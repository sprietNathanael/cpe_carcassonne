/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.CarcassonneIHM.Layers;

import carcassonne.controller.CarcassonneGameController;
import carcassonne.view.CarcassonneIHM.Panels.Grid.GridPanel;
import carcassonne.view.CarcassonneIHM.Tools.UICoord;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * Abstraction of a layer
 */
public abstract class AbstractLayer
{
    protected GridPanel gridPanel;
    protected ArrayList<UICoord> positions;
    private LayerMouseAdapter mouseListener;
    private boolean visible;
    protected CarcassonneGameController controller;
    
    /**
     * Construction of an abstract layer
     * @param gridPanel Grid panel
     * @param controller CarcassonneGameController
     */
    public AbstractLayer(GridPanel gridPanel, CarcassonneGameController controller)
    {
        this.gridPanel = gridPanel;        
        this.controller = controller;
        this.positions = new ArrayList<>();
    }
    
    /**
     * Adds a position to the list
     * @param pos Position to add
     */
    public void addPosition(UICoord pos)
    {
        if(!this.positions.contains(pos))
        {
            this.positions.add(pos);            
        }
    }
    
    /**
     * Paints the layer
     * @param g2 
     */
    public abstract void paint(Graphics2D g2);
    
    /**
     * Saves and attaches a mouse input listener to the gridPanel
     * @param ml
     */
    public void attachMouseInputListener(LayerMouseAdapter ml) {
        if(this.mouseListener == null)
        {
            this.mouseListener = ml;
            this.gridPanel.addMouseListener(this.mouseListener);
            this.gridPanel.addMouseMotionListener(this.mouseListener);
        }
    }
    
    /**
     * Removes the mouse input listener from the gridPanel
     */
    public void removeMouseInputListener() {
        this.gridPanel.removeMouseListener(this.mouseListener);
        this.gridPanel.removeMouseMotionListener(this.mouseListener);
        this.mouseListener = null;
    }
    
    /**
     * Test if the layer is visible
     * @return 
     */
    public boolean isVisible()
    {
        return this.visible;
    }
    
    /**
     * Set the layer visible
     */
    public void onShow()
    {
        this.visible = true;
    }
    
    /**
     * Hides the layer
     */
    public void onHide()
    {
        this.visible = false;
    }
    
    /**
     * Clean all positions
     */
    public void cleanPositions()
    {
        this.positions = new ArrayList<>();
    }
            
}
