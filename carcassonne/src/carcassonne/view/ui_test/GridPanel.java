/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.ui_test;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
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
    public static int MIN_TILE_SIZE = 25;
    public static int MAX_TILE_SIZE = 300;
    public static double MOVE_BORDER_LIMIT = 0.25;
    private Coord graphicalCenter;
    private int tileSize;
    private List<AbstractLayer> layers = new ArrayList<AbstractLayer>();
    private GridMouseAdapter mouseListener;
    private boolean firstPaint;
    private int upBorder;
    private int leftBorder;
    private int downBorder;
    private int rightBorder;

    public GridPanel()
    {
        this.tileSize = INITIAL_TILE_WIDTH;
        setDoubleBuffered(true);
        setOpaque(false);
        setLayout(new MigLayout());
        this.mouseListener = new GridMouseAdapter(this);
        this.addMouseListener(this.mouseListener);
        this.addMouseMotionListener(this.mouseListener);
        this.addMouseWheelListener(this.mouseListener);
        this.upBorder = 0;
        this.leftBorder = 0;
        this.rightBorder = 3;
        this.downBorder = 0;
        this.graphicalCenter = new Coord(0,0);
        this.firstPaint = true;
    }
    
    public void addLayer(AbstractLayer test)
    {
        layers.add(test);
        test.onShow();
    }
    
    public void zoom(int wheelDirection)
    {
        System.out.println("zoom "+wheelDirection);
        int size = (int) (this.tileSize * Math.pow(1.3, wheelDirection));
        if(size < MIN_TILE_SIZE) size = MIN_TILE_SIZE;
        if(size > MAX_TILE_SIZE) size = MAX_TILE_SIZE;
        this.tileSize = size;
        this.repaint();
    }

    @Override
    protected void paintChildren(Graphics g)
    {
        
        Graphics2D g2 = (Graphics2D) g;
        
        // X
        // Y
        for(AbstractLayer layer : layers)
        {
            layer.paint(g2);
        }
        super.paintChildren(g);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        if(this.firstPaint)
        {
            this.firstPaint = false;
            this.moveCenterTo(new Coord(this.getWidth()/2,this.getHeight()/2));
        }
    }
    
    
    
    public int getTileSize()
    {
        return this.tileSize;
    }
    
    public Coord getGraphicalCenter()
    {
        return this.graphicalCenter;
    }
    
    public Coord getGridPositionFromCoordinates(double x, double y)
    {
        /*double adjustedX = x - this.graphicalCenter.getX();
        double adjustedY = y - this.graphicalCenter.getY();
        int gridX = (int)((x-this.graphicalCenter.getX()) / this.tileSize);
        int gridY = (int)((y-this.graphicalCenter.getY()) / this.tileSize);
        gridX = adjustedX > 0 ? gridX : gridX - 1;
        gridY = adjustedY > 0 ? gridY : gridY -1;*/
        return new Coord(this.getGridXFromPixel(x),this.getGridYFromPixel(y));
    }
    
    public int getGridXFromPixel(double x)
    {
        double adjustedX = x - this.graphicalCenter.getX();
        int gridX = (int)((x-this.graphicalCenter.getX()) / this.tileSize);
        gridX = adjustedX > 0 ? gridX : gridX - 1;
        return gridX;
    }
    
    public int getGridYFromPixel(double y)
    {
        double adjustedY = y - this.graphicalCenter.getY();
        int gridY = (int)((y-this.graphicalCenter.getY()) / this.tileSize);
        gridY = adjustedY > 0 ? gridY : gridY - 1;
        return gridY;
    }
    
    public int getPixelXFromGrid(int x)
    {
        int pixelX = x*this.tileSize + this.graphicalCenter.getX();
        return(pixelX);
    }
    
    public int getPixelYFromGrid(int y)
    {
        int pixelY = y*this.tileSize + this.graphicalCenter.getY();
        return(pixelY);
    }
    
    public void moveCenterTo(Coord newCoord)
    {
        int newX = newCoord.getX();
        int newY = newCoord.getY();
        int pixelUpBorder = this.upBorder * this.tileSize;
        int pixelDownBorder = (this.downBorder * this.tileSize)+this.tileSize;
        int pixelLeftBorder = this.leftBorder * this.tileSize;
        int pixelRightBorder = (this.rightBorder * this.tileSize)+this.tileSize;
        
        int newPixelUpBorder = newY + pixelUpBorder;
        int newPixelDownBorder = newY + pixelDownBorder;
        int newPixelRightBorder = newX + pixelRightBorder;
        int newPixelLeftBorder = newX + pixelLeftBorder;
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
        this.graphicalCenter = new Coord(newX,newY);
        this.repaint();
    }
    
    
}
