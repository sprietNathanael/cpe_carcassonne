/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.CarcassonneIHM.Layers.Tile;

import carcassonne.controller.CarcassonneGameController;
import carcassonne.view.CarcassonneIHM.Panels.Grid.GridPanel;
import carcassonne.view.CarcassonneIHM.Layers.AbstractLayer;
import carcassonne.view.CarcassonneIHM.Tools.TileImage;
import carcassonne.view.CarcassonneIHM.Tools.UICoord;
import java.awt.Graphics2D;

/**
 * Layer that contains all the placed tiles
 */
public class TilesLayer extends AbstractLayer
{
    /**
     * Layer constructor
     * @param gridPanel The grid panel which the layer is rattached to
     * @param controller
     */
    public TilesLayer(GridPanel gridPanel, CarcassonneGameController controller)
    {
        super(gridPanel, controller);
    }
    
    /**
     * Paint the layer
     * @param g2 
     */
    @Override
    public void paint(Graphics2D g2)
    {
        if(this.isVisible())
        {
            // Get the grid panel properties
            int tileSize = this.gridPanel.getTileSize();
            UICoord center = this.gridPanel.getGraphicalCenter();

            // Browse the placed tiles
            this.positions.forEach((coord) -> {
                // Computes the pixel component of the tile
                int x = center.getX()+(tileSize*coord.getX());
                int y = center.getY()+(tileSize*coord.getY());

                // Cast the coordinates into a Tile Image
                TileImage tileImage = (TileImage)coord;

                // Draw the tile at the computed coordinates
                g2.drawImage(tileImage.getImage(), x, y, tileSize, tileSize, null);
            });
        }
    }
}
