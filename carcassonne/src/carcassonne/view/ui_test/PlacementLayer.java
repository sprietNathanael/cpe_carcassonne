/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.ui_test;

import carcassonne.controller.AbstractCarcassonneGameController;
import carcassonne.coord.Coord;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Layer that contains all the possibilities and the preview
 */
public class PlacementLayer extends AbstractLayer implements LayerMouseListener
{

    // Color constants applied to the allowed or forbidded preview
    private static final Composite ALLOWED = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);
    private static final Composite CLEAR = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0);
    private static final Composite FORBIDDED = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .2f);

    private TileImage previewImage;
    private boolean allowedPlacement;

    /**
     * Layer contstructor
     *
     * @param gridPanel
     * @param controller
     */
    public PlacementLayer(GridPanel gridPanel, AbstractCarcassonneGameController controller)
    {
        super(gridPanel, controller);
        // Initialise the position array
        this.positions = new ArrayList<UICoord>();

        this.previewImage = null;

        // Initialise the preview as forbidded
        this.allowedPlacement = false;
    }

    /**
     * On layer show triggered
     */
    public void onShow()
    {
        super.onShow();
        // Attaches a mouse input listener to the layer
        this.attachMouseInputListener(new LayerMouseAdapter(this.gridPanel, this));
    }

    public void onHide()
    {
        super.onHide();
    }

    /**
     * Changes the image preview
     *
     * @param preview New image preview
     */
    public void setPreview(TileImage preview)
    {
        this.previewImage = null;
        this.previewImage = preview;
    }

    /**
     * Paint callback
     *
     * @param g2
     */
    public void paint(Graphics2D g2)
    {
        int placeHolderSize = this.gridPanel.getTileSize();
        int shift = placeHolderSize / 30;
        int thickness = placeHolderSize / 14;
        int tileSize = placeHolderSize - (2 * shift);
        UICoord center = this.gridPanel.getGraphicalCenter();
        g2.setColor(Color.LIGHT_GRAY);
        for (UICoord p : this.positions) {
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
                g2.fillRect(x, y + tileSize - thickness, tileSize, thickness);
                g2.fillRect(x, y, thickness, tileSize);
                g2.fillRect(x + tileSize - thickness, y, thickness, tileSize);
            }

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
            this.previewImage.setCoord(new UICoord(-1, -1));
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
                // TODO
                // Call the controller to turn the tile model
                this.gridPanel.repaint();
            }
        }
        else if (e.getButton() == MouseEvent.BUTTON1) {
            if(this.previewImage != null){
                e.consume();
                if(this.allowedPlacement)
                {
                    try {
                        this.controller.putCurrentTile(new Coord(this.previewImage.getX(), this.previewImage.getY()));
                    } catch (Exception ex) {
                        Logger.getLogger(PlacementLayer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }
}
