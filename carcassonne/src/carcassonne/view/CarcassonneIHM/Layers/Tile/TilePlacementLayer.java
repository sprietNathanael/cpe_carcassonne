/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.CarcassonneIHM.Layers.Tile;

import carcassonne.controller.CarcassonneGameController;
import carcassonne.coord.Coord;
import carcassonne.model.tile.AbstractTile;
import carcassonne.view.CarcassonneIHM.Panels.Grid.GridPanel;
import carcassonne.view.CarcassonneIHM.Layers.AbstractLayer;
import carcassonne.view.CarcassonneIHM.Layers.Field.FieldsLayer;
import carcassonne.view.CarcassonneIHM.Panels.MainPanel;
import carcassonne.view.CarcassonneIHM.Tools.TileImage;
import carcassonne.view.CarcassonneIHM.Tools.UICoord;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * Layer that contains all the tiles possibilities and the preview
 */
public class TilePlacementLayer extends AbstractLayer implements TilePlacementMouseListener
{

    // Color constants applied to the allowed or forbidded preview
    private static final Composite ALLOWED = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);
    private static final Composite CLEAR = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0);
    private static final Composite FORBIDDED = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .6f);
    private static final Composite MEEPLE = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .7f);
    
    private TileImage previewImage;
    private boolean allowedPlacement;
    private MouseListener mouseListener;
    private MainPanel mainPanel;
    private BufferedImage backTile;
       

    /**
     * Layer contstructor
     *
     * @param gridPanel
     * @param controller
     * @param mainPanel
     */
    public TilePlacementLayer(GridPanel gridPanel, CarcassonneGameController controller, MainPanel mainPanel)
    {
        super(gridPanel, controller);
        // Initialise the position array
        this.positions = new ArrayList<>();

        this.previewImage = null;
        this.mainPanel = mainPanel;

        // Initialise the preview as forbidded
        this.allowedPlacement = false;
        // Get the back tile image
        try {
            this.backTile = ImageIO.read(new File("resources/tiles/back.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
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

    /**
     * On layer hide triggered
     */
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
            
            // Draw all positions
            this.positions.forEach((p) -> {
                int x = center.getX() + (placeHolderSize * p.getX());
                int y = center.getY() + (placeHolderSize * p.getY());
                
                // If this position is the currently selected position
                if (this.previewImage != null && (p.getX() == this.previewImage.getX() && p.getY() == this.previewImage.getY())) {
                    // Draw the preview
                    Composite compositeBackup = g2.getComposite();
                    g2.setComposite(CLEAR);
                    g2.setComposite(this.allowedPlacement ? ALLOWED : FORBIDDED);
                    g2.drawImage(this.previewImage.getImage(), x, y, placeHolderSize, placeHolderSize, null);
                    g2.setComposite(compositeBackup);
                }
                else {
                    //Draw a placeholder
                    g2.setColor(FieldsLayer.MASK_COLORS.get(this.controller.getCurrentPlayer().getColor()));
                    g2.setComposite(FORBIDDED);
                    g2.drawImage(this.backTile, x, y, placeHolderSize, placeHolderSize, null);
                    g2.fillRect(x, y, placeHolderSize, placeHolderSize);
                    g2.setComposite(ALLOWED);
                    g2.setColor(Color.BLACK);
                    /*x += shift;
                    y += shift;
                    g2.fillRect(x, y, tileSize, thickness);
                    g2.fillRect(x, y + tileSize, tileSize, thickness);
                    g2.fillRect(x, y, thickness, tileSize);
                    g2.fillRect(x + tileSize, y, thickness, tileSize);
                    g2.drawLine(x, y, x+tileSize, y+tileSize);
                    g2.drawLine(x, y+tileSize, x+tileSize, y);*/
                }
            });
        }
    }

    
    /**
     * Triggered when the mouse enters a tile
     * @param e
     * @param c
     */
    @Override
    public void tileEntered(MouseEvent e, UICoord c)
    {
        // If the coordinates are in the positions array
        if (this.positions.contains(c)) {
            // Check if the placement is allowed
            this.allowedPlacement = this.controller.checkTilePosition(new Coord(c.getX(), c.getY()));
            if (this.previewImage != null) {
                // Refreshes the component with the preview
                this.previewImage.setCoord(c);
                this.gridPanel.repaint();

            }
        }
    }

    /**
     * Triggered when the mouse exits a tile
     * @param e
     * @param p 
     */
    @Override
    public void tileExited(MouseEvent e, UICoord p)
    {
        if (this.previewImage != null) {
            // Refreshes the component without the preview
            this.previewImage.setCoord(new UICoord(0, 0));
            this.gridPanel.repaint();
            this.allowedPlacement = false;

        }
    }

    /**
     * Triggered when the mouse is clicked
     * @param e
     * @param p 
     */
    @Override
    public void mouseClicked(MouseEvent e, UICoord p)
    {
        // If the right button is clicked
        if (e.getButton() == MouseEvent.BUTTON3) {
            if (this.previewImage != null) {
                e.consume();
                // Turns the preview
                this.controller.turnRight();
                // Check if the placement is allowed
                this.allowedPlacement = this.controller.checkTilePosition(new Coord(this.previewImage.getX(), this.previewImage.getY()));
                // Refreshes the component
                this.previewImage.turnRight();
                this.gridPanel.repaint();
            }
        }
        // If the left button is clicked
        else if (e.getButton() == MouseEvent.BUTTON1) {
            if(this.previewImage != null && (this.previewImage.getX() == p.getX() && this.previewImage.getY() == p.getY())){
                e.consume();
                if(this.allowedPlacement)
                {
                    try {
                        // Put the tile
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
