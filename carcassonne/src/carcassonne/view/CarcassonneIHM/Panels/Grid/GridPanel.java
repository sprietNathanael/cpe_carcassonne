/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.CarcassonneIHM.Panels.Grid;

import carcassonne.view.CarcassonneIHM.Layers.AbstractLayer;
import carcassonne.view.CarcassonneIHM.Panels.Info.InfoPanel;
import carcassonne.view.CarcassonneIHM.Panels.MainPanel;
import carcassonne.view.CarcassonneIHM.Tools.UICoord;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

/**
 * Main Grid Panel component
 */
public class GridPanel extends JPanel
{
    // Constants
    public static int SEPARATION_LINE_WIDTH = 10;
    public static int INITIAL_TILE_WIDTH = 120;
    public static int MIN_TILE_SIZE = 25;
    public static int MAX_TILE_SIZE = 300;
    public static double MOVE_BORDER_LIMIT = 0.25;
    public static double ZOOM_FACTOR = 1.3;
    
    private UICoord graphicalCenter;
    private int tileSize;
    private final List<AbstractLayer> layers = new ArrayList<>();
    private final GridPanelMouseAdapter mouseListener;
    private boolean firstPaint;
    private int upBorder;
    private int leftBorder;
    private int downBorder;
    private int rightBorder;
    private BufferedImage woodTexture;

    /**
     * Grid panel constructor
     */
    public GridPanel()
    {
        // Initialise tile size
        this.tileSize = INITIAL_TILE_WIDTH;
        
        // Creates the mouse adapter
        this.mouseListener = new GridPanelMouseAdapter(this);
        
        // Configure the panel
        this.configureComponent();
        
        // Set graphical center
        this.graphicalCenter = new UICoord(0,0);
        this.firstPaint = true;
        
        // Get the wood texture image
        try {
            this.woodTexture = ImageIO.read(new File("resources/textures/wood.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Configure component and adds a mouse listener
     */
    private void configureComponent() {
        setDoubleBuffered(true);
        setOpaque(false);
        setLayout(new MigLayout());
        
        this.addMouseListener(this.mouseListener);
        this.addMouseMotionListener(this.mouseListener);
        this.addMouseWheelListener(this.mouseListener);
        
        // Set graphical centenr
        this.graphicalCenter = new UICoord(0,0);
        this.firstPaint = true;
    }
    
    /**
     * Adds an abstract layer to the component
     * @param layer The abstract layer to add
     */
    public void addLayer(AbstractLayer layer)
    {
        layers.add(layer);
    }

    @Override
    /**
     * Paint Children Callback
     */
    protected void paintChildren(Graphics g)
    {
        // Converts graphics to graphics2D
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(this.woodTexture,0, 0, this.getWidth(), this.getHeight(), null);
        
        // Browses layers
        layers.forEach((layer) -> {
            // Paint the layers
            layer.paint(g2);
        });
        
        
        // Draw the separation line
        //GradientPaint gradient = new GradientPaint(0, 0, new Color(50, 50, 50, 255), 0, 0,  new Color(255, 255, 255, 100));
        GradientPaint gradient = new GradientPaint(0, 0, new Color(30, 30, 30, 255), 0 + GridPanel.SEPARATION_LINE_WIDTH+30, 0,  new Color(0, 0, 0, 0));
        g2.setPaint(gradient);
        //g2.setColor(Color.RED);
        g2.fillRect(0, 0, GridPanel.SEPARATION_LINE_WIDTH+30, this.getHeight());
        g2.setColor(Color.BLACK);
        
        // Call the super methode
        super.paintChildren(g);
    }

    /**
     * Paint component callback
     */
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        // If this is the first time the component is painted
        if(this.firstPaint)
        {
            this.firstPaint = false;
            // Moves the graphical center to the component center
            this.moveCenterTo(new UICoord(this.getWidth()/2,this.getHeight()/2));
        }
    }
    
    /**
     * Get the tile size
     * @return 
     */   
    public int getTileSize()
    {
        return this.tileSize;
    }
    
    /**
     * Get the graphical center
     * @return 
     */
    public UICoord getGraphicalCenter()
    {
        return this.graphicalCenter;
    }
    
    /**
     * Get the position on the grid from pixel coordinates
     * @param x
     * @param y
     * @return 
     */
    public UICoord getGridPositionFromCoordinates(double x, double y)
    {
        return new UICoord(this.getGridXFromPixel(x),this.getGridYFromPixel(y));
    }
    
    /**
     * Get the X component of the position on the grid from the pixel x component
     * @param x
     * @return 
     */
    public int getGridXFromPixel(double x)
    {
        // Get the relative X from the graphical center
        double relativeX = x - this.graphicalCenter.getX();
        // Get the grid X component by dividing relativeX by the tile size
        int gridX = (int)(relativeX / this.tileSize);
        // If relative X is negative (i.e. if x is at left of center), substract one
        gridX = relativeX > 0 ? gridX : gridX - 1;
        return gridX;
    }
    
    /**
     * Get the Y component of the position on the grid from the pixel y component
     * @param y
     * @return 
     */
    public int getGridYFromPixel(double y)
    {
        // Get the relative Y from the graphical center
        double relativeY = y - this.graphicalCenter.getY();
        // Get the grid Y component by dividing relativeY by the tile size
        int gridY = (int)((y-this.graphicalCenter.getY()) / this.tileSize);
        // If relative Y is negative (i.e. if x is over the center), substract one
        gridY = relativeY > 0 ? gridY : gridY - 1;
        return gridY;
    }
   
    /**
     * Moves the graphical center to a new Coordinate
     * @param newCoord Desired Coordinate 
     */
    public void moveCenterTo(UICoord newCoord)
    {
        // Get desired coordinates components
        int newX = newCoord.getX();
        int newY = newCoord.getY();
        
        // Get the game borders pixel values
        int pixelUpBorder = this.upBorder * this.tileSize;
        int pixelDownBorder = (this.downBorder * this.tileSize)+this.tileSize;
        int pixelLeftBorder = this.leftBorder * this.tileSize;
        int pixelRightBorder = (this.rightBorder * this.tileSize)+this.tileSize;
        
        // Compute the potential new borders pixel values
        int newPixelUpBorder = newY + pixelUpBorder;
        int newPixelDownBorder = newY + pixelDownBorder;
        int newPixelRightBorder = newX + pixelRightBorder;
        int newPixelLeftBorder = newX + pixelLeftBorder;
        
        // Control that the border stays in the component
        if(newPixelDownBorder - (this.tileSize*MOVE_BORDER_LIMIT) < 0 )
        {
            newY = this.graphicalCenter.getY();
        }
        if(newPixelUpBorder  + (this.tileSize*MOVE_BORDER_LIMIT) > this.getHeight())
        {
            newY = this.graphicalCenter.getY();
        }
        if(newPixelRightBorder - (this.tileSize*MOVE_BORDER_LIMIT) < 0)
        {
            newX = this.graphicalCenter.getX();
        }
        if(newPixelLeftBorder + (this.tileSize*MOVE_BORDER_LIMIT) > this.getWidth())
        {
            newX = this.graphicalCenter.getX();
        }
        
        // Assign the new graphical center
        this.graphicalCenter = new UICoord(newX,newY);
        
        // Repaint the component
        this.repaint();
    }
    
    /**
     * Zoom on the board
     * @param wheelDirection Mouse wheel direction : -1 or 1
     */
    public void zoom(int wheelDirection)
    {
        // Set the tile size by multiplying it by ZOOM_FACTOR, depending on the wheel direction
        int size = (int) (this.tileSize * Math.pow(ZOOM_FACTOR, wheelDirection));
        
        // Control the zoom according to the max and min constants
        if(size < MIN_TILE_SIZE) size = MIN_TILE_SIZE;
        if(size > MAX_TILE_SIZE) size = MAX_TILE_SIZE;
        this.tileSize = size;
        
        // Repaint the component
        this.repaint();
    }

}
