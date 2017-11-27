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
 *
 * @author nathanael
 */
public class PlacementLayer extends AbstractLayer implements GridMouseListener
{
    private TileImage previewImage;
    private static final Composite ALLOWED = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .8f);
    private static final Composite FORBIDDED = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .4f);
    private boolean allowedPreview;
    
    public PlacementLayer(GridPanel gc)
    {
        super(gc);
        this.positions = new ArrayList<Coord>();
        this.previewImage = null;
        this.allowedPreview = false;
    }
    
    public void onShow()
    {
        super.onShow();
        this.attachMouseInputListener(new GridMouseAdapter(this.gc,this));
    }
    
    public void onHide()
    {
        super.onHide();
    }
    
    public void setPreview(TileImage preview)
    {
        this.previewImage = null;
        this.previewImage = preview;
    }
        
    public void paint(Graphics2D g2)
    {
        System.out.println("carcassonne.view.ui_test.PlacementLayer.paint()");
        int placeHolderSize = this.gc.getTileSize();
        int shift = placeHolderSize/30;
        int thickness = placeHolderSize/14;
        int tileSize = placeHolderSize - (2*shift);
        Coord center = this.gc.getGraphicalCenter();
        g2.setColor(Color.LIGHT_GRAY);
        for(Coord p : this.positions)
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
    public void tileEntered(MouseEvent e, Coord c)
    {
        if(this.positions.contains(c))
        {
            this.previewImage.setCoord(c);
            this.gc.repaint();
        }
    }

    @Override
    public void tileExited(MouseEvent e, Coord p)
    {
        this.previewImage.setCoord(new Coord(-1,-1));
        this.gc.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e, Coord p)
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
