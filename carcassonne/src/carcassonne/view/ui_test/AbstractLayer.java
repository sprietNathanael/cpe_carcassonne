/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.ui_test;

import carcassonne.controller.AbstractCarcassonneGameController;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * Abstraction of a layer
 */
public abstract class AbstractLayer
{
    GridPanel gridPanel;
    protected ArrayList<UICoord> positions;
    private LayerMouseAdapter mouseListener;
    private boolean visible;
    protected AbstractCarcassonneGameController controller;
    
    /**
     * Construction of an abstract layer
     * @param gridPanel Grid panel
     */
    public AbstractLayer(GridPanel gridPanel, AbstractCarcassonneGameController controller)
    {
        this.gridPanel = gridPanel;
        this.positions = new ArrayList<>();
        this.controller = controller;
    }
    
    /**
     * Adds a position to the list
     * @param pos Position to add
     */
    public void addPosition(UICoord pos)
    {
        this.positions.add(pos);
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
    public void attachMouseInputListener(LayerMouseAdapter mouseListener) {
        this.mouseListener = mouseListener;
        this.gridPanel.addMouseListener(this.mouseListener);
        this.gridPanel.addMouseMotionListener(this.mouseListener);
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
