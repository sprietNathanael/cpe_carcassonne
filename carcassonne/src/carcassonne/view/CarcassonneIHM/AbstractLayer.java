/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.CarcassonneIHM;

import carcassonne.controller.AbstractCarcassonneGameController;
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
    protected AbstractCarcassonneGameController controller;
    
    /**
     * Construction of an abstract layer
     * @param gridPanel Grid panel
     * @param controller AbstractCarcassonneGameController
     */
    public AbstractLayer(GridPanel gridPanel, AbstractCarcassonneGameController controller)
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
     * @param mouseListener 
     */
    public void attachMouseInputListener(LayerMouseAdapter ml) {
        this.mouseListener = ml;
        this.gridPanel.addMouseListener(this.mouseListener);
        this.gridPanel.addMouseMotionListener(this.mouseListener);
    }
    
    public void removeMouseInputListener() {
        this.gridPanel.removeMouseListener(this.mouseListener);
        this.gridPanel.removeMouseMotionListener(mouseListener);
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
