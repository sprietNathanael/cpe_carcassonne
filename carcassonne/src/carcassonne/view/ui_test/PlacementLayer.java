/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.ui_test;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Layer that contains all the possibilities and the preview
 */
public class PlacementLayer extends AbstractLayer implements LayerMouseListener
{
    // Color constants applied to the allowed or forbidded preview
    private static final Composite ALLOWED = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .8f);
    private static final Composite FORBIDDED = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .4f);
    
    private TileImage previewImage;
    private boolean allowedPreview;
    
    /**
     * Layer contstructor
     * @param gc 
     */
    public PlacementLayer(GridPanel gc)
    {
        super(gc);
        // Initialise the position array
        this.positions = new ArrayList<UICoord>();
        
        this.previewImage = null;
        
        // Initialise the preview as forbidded
        this.allowedPreview = false;
    }
    
    /**
     * On layer show triggered
     */
    public void onShow()
    {
        super.onShow();
        // Attaches a mouse input listener to the layer
        this.attachMouseInputListener(new LayerMouseAdapter(this.gridPanel,this));
    }
    
    public void onHide()
    {
        super.onHide();
    }
    
    /**
     * Changes the image preview
     * @param preview New image preview
     */
    public void setPreview(TileImage preview)
    {
        this.previewImage = null;
        this.previewImage = preview;
    }
        
    /**
     * Paint callback
     * @param g2 
     */
    public void paint(Graphics2D g2)
    {
        int placeHolderSize = this.gridPanel.getTileSize();
        int shift = placeHolderSize/30;
        int thickness = placeHolderSize/14;
        int tileSize = placeHolderSize - (2*shift);
        UICoord center = this.gridPanel.getGraphicalCenter();
        g2.setColor(Color.LIGHT_GRAY);
        for(UICoord p : this.positions)
        {
            int x = center.getX()+(placeHolderSize*p.getX());
            int y = center.getY()+(placeHolderSize*p.getY());
            if(this.previewImage != null && (p.getX() == this.previewImage.getX() && p.getY() == this.previewImage.getY()))
            {
                Composite compositeBackup = g2.getComposite();
                g2.setComposite(this.allowedPreview ? ALLOWED : FORBIDDED);
                g2.drawImage(this.previewImage.getImage(), x, y, placeHolderSize, placeHolderSize, null);
                g2.setComposite(compositeBackup);
            }
            else
            {
                x+=shift;
                y+=shift;
                g2.fillRect(x,y,tileSize, thickness);
                g2.fillRect(x, y+tileSize-thickness, tileSize, thickness);
                g2.fillRect(x, y, thickness, tileSize);
                g2.fillRect(x+tileSize-thickness, y, thickness, tileSize);
            }
            
            
        }
    }

    @Override
    public void tileEntered(MouseEvent e, UICoord c)
    {
        if(this.positions.contains(c))
        {
            this.previewImage.setCoord(c);
            this.gridPanel.repaint();
        }
    }

    @Override
    public void tileExited(MouseEvent e, UICoord p)
    {
            this.previewImage.setCoord(new UICoord(-1,-1));
            this.gridPanel.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e, UICoord p)
    {
        if (e.getButton() == MouseEvent.BUTTON3) {
            if (this.previewImage != null) {
                e.consume();
                this.previewImage.turnRight();
                this.gridPanel.repaint();
            }
        }
    }    
}
