/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.ui_test;

import carcassonne.controller.AbstractCarcassonneGameController;
import carcassonne.coord.Coord;
import carcassonne.model.tile.AbstractTile;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Layer that contains all the possibilities and the preview
 */
public class TilePlacementLayer extends AbstractLayer implements TilePlacementMouseListener
{

    // Color constants applied to the allowed or forbidded preview
    private static final Composite ALLOWED = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);
    private static final Composite CLEAR = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0);
    private static final Composite FORBIDDED = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .2f);
    private static final Composite MEEPLE = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .7f);
    
    

    private TileImage previewImage;
    private boolean allowedPlacement;
    private MouseListener mouseListener;
    private MainPanel mainPanel;
       

    /**
     * Layer contstructor
     *
     * @param gridPanel
     * @param controller
     */
    public TilePlacementLayer(GridPanel gridPanel, AbstractCarcassonneGameController controller, MainPanel mainPanel)
    {
        super(gridPanel, controller);
        // Initialise the position array
        this.positions = new ArrayList<>();

        this.previewImage = null;
        this.mainPanel = mainPanel;

        // Initialise the preview as forbidded
        this.allowedPlacement = false;
        
        
        
    }

    /**
     * On layer show triggered
     */
    @Override
    public void onShow()
    {
        super.onShow();
        // Attaches a mouse input listener to the layer
        this.attachMouseInputListener(new TilePlacementMouseAdapter(this.gridPanel, this));
    }

    @Override
    public void onHide()
    {
        super.onHide();
        this.removeMouseInputListener();
    }

    /**
     * Changes the image preview
     *
     * @param preview New image preview
     */
    public void setPreview(AbstractTile preview)
    {
        this.previewImage = null;
        this.previewImage = new TileImage(0,0,preview);
    }

    /**
     * Paint callback
     *
     * @param g2
     */
    @Override
    public void paint(Graphics2D g2)
    {
        if(this.isVisible())
        {
            int placeHolderSize = this.gridPanel.getTileSize();
            int shift = placeHolderSize / 30;
            int thickness = 1;
            int tileSize = placeHolderSize - (2 * shift);
            UICoord center = this.gridPanel.getGraphicalCenter();
            g2.setColor(Color.BLACK);
            this.positions.forEach((p) -> {
                int x = center.getX() + (placeHolderSize * p.getX());
                int y = center.getY() + (placeHolderSize * p.getY());
                if (this.previewImage != null && (p.getX() == this.previewImage.getX() && p.getY() == this.previewImage.getY())) {
                    Composite compositeBackup = g2.getComposite();
                    g2.setComposite(CLEAR);
                    g2.setComposite(this.allowedPlacement ? ALLOWED : FORBIDDED);
                    g2.drawImage(this.previewImage.getImage(), x, y, placeHolderSize, placeHolderSize, null);
                    g2.setComposite(compositeBackup);



                }
                else {
                    x += shift;
                    y += shift;
                    g2.fillRect(x, y, tileSize, thickness);
                    g2.fillRect(x, y + tileSize, tileSize, thickness);
                    g2.fillRect(x, y, thickness, tileSize);
                    g2.fillRect(x + tileSize, y, thickness, tileSize);
                    g2.drawLine(x, y, x+tileSize, y+tileSize);
                    g2.drawLine(x, y+tileSize, x+tileSize, y);
                }
            });
        }
    }

    @Override
    public void tileEntered(MouseEvent e, UICoord c)
    {
        if (this.positions.contains(c)) {
            this.allowedPlacement = this.controller.checkTilePosition(new Coord(c.getX(), c.getY()));
            if (this.previewImage != null) {
                this.previewImage.setCoord(c);
                this.gridPanel.repaint();

            }
        }
    }

    @Override
    public void tileExited(MouseEvent e, UICoord p)
    {
        if (this.previewImage != null) {
            this.previewImage.setCoord(new UICoord(0, 0));
            this.gridPanel.repaint();
            this.allowedPlacement = false;

        }
    }

    @Override
    public void mouseClicked(MouseEvent e, UICoord p)
    {
        if (e.getButton() == MouseEvent.BUTTON3) {
            if (this.previewImage != null) {
                e.consume();
                this.controller.turnRight();
                this.allowedPlacement = this.controller.checkTilePosition(new Coord(this.previewImage.getX(), this.previewImage.getY()));
                this.previewImage.turnRight();
                this.gridPanel.repaint();
            }
        }
        else if (e.getButton() == MouseEvent.BUTTON1) {
            if(this.previewImage != null){
                e.consume();
                if(this.allowedPlacement)
                {
                    try {
                        Coord newCoord = new Coord(p.getX(), p.getY());
                        this.mainPanel.putTile(new UICoord(newCoord.col, newCoord.row));
                        this.controller.putCurrentTile(newCoord);
                    } catch (Exception ex) {
                        Logger.getLogger(TilePlacementLayer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }
}
