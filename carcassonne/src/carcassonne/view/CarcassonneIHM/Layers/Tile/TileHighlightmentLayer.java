/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.CarcassonneIHM.Layers.Tile;

import carcassonne.controller.AbstractCarcassonneGameController;
import carcassonne.model.tile.AbstractTile;
import carcassonne.view.CarcassonneIHM.Layers.AbstractLayer;
import carcassonne.view.CarcassonneIHM.Layers.Field.FieldsLayer;
import carcassonne.view.CarcassonneIHM.Panels.Grid.GridPanel;
import carcassonne.view.CarcassonneIHM.Panels.Info.InfoPanel;
import carcassonne.view.CarcassonneIHM.Tools.UICoord;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Polygon;

/**
 *
 * @author nathanael
 */
public class TileHighlightmentLayer extends AbstractLayer
{

    private UICoord tileToHighlight;
    private String color;

    public TileHighlightmentLayer(GridPanel gridPanel, AbstractCarcassonneGameController controller)
    {
        super(gridPanel, controller);
    }

    public void setTileToHighlight(UICoord tile, String color)
    {
        this.tileToHighlight = tile;
        this.color = color;
    }

    @Override
    public void paint(Graphics2D g2)
    {
        if(this.isVisible() && this.tileToHighlight != null)
        {
            int tileSize = this.gridPanel.getTileSize();
            UICoord center = this.gridPanel.getGraphicalCenter();
            // Translation to apply the center
            double delta_x = (this.tileToHighlight.getX() * tileSize) + center.getX();
            double delta_y = (this.tileToHighlight.getY() * tileSize) + center.getY();

            int tile_x = (int) delta_x;
            int tile_y = (int) delta_y;
            Color color = FieldsLayer.MASK_COLORS.get(this.color);
            int r = color.getRed();
            int g = color.getGreen();
            int b = color.getBlue();
            GradientPaint gradient = new GradientPaint(tile_x, tile_y, new Color(r,g,b,200), tile_x, tile_y - InfoPanel.HIGHLIGHT_GRADIENT_THICKNESS, new Color(r, g, b, 0));
            g2.setPaint(gradient);
            Polygon border = new Polygon();
            border.addPoint(tile_x - InfoPanel.HIGHLIGHT_GRADIENT_THICKNESS, tile_y - InfoPanel.HIGHLIGHT_GRADIENT_THICKNESS);
            border.addPoint(tile_x + tileSize + InfoPanel.HIGHLIGHT_GRADIENT_THICKNESS, tile_y - InfoPanel.HIGHLIGHT_GRADIENT_THICKNESS);
            border.addPoint(tile_x + tileSize, tile_y);
            border.addPoint(tile_x, tile_y);
            g2.fillPolygon(border);

            gradient = new GradientPaint(tile_x + tileSize, tile_y, new Color(r, g, b, 200), tile_x + tileSize + InfoPanel.HIGHLIGHT_GRADIENT_THICKNESS, tile_y, new Color(r, g, b, 0));
            g2.setPaint(gradient);

            border.reset();
            border.addPoint(tile_x + tileSize + InfoPanel.HIGHLIGHT_GRADIENT_THICKNESS, tile_y - InfoPanel.HIGHLIGHT_GRADIENT_THICKNESS);
            border.addPoint(tile_x + tileSize + InfoPanel.HIGHLIGHT_GRADIENT_THICKNESS, tile_y + tileSize + InfoPanel.HIGHLIGHT_GRADIENT_THICKNESS);
            border.addPoint(tile_x + tileSize, tile_y + tileSize);
            border.addPoint(tile_x + tileSize, tile_y);
            g2.fillPolygon(border);

            gradient = new GradientPaint(tile_x, tile_y + tileSize, new Color(r, g, b, 200), tile_x, tile_y + tileSize + InfoPanel.HIGHLIGHT_GRADIENT_THICKNESS, new Color(r, g, b, 0));
            g2.setPaint(gradient);

            border.reset();
            border.addPoint(tile_x + tileSize + InfoPanel.HIGHLIGHT_GRADIENT_THICKNESS, tile_y + tileSize + InfoPanel.HIGHLIGHT_GRADIENT_THICKNESS);
            border.addPoint(tile_x - InfoPanel.HIGHLIGHT_GRADIENT_THICKNESS, tile_y + tileSize + InfoPanel.HIGHLIGHT_GRADIENT_THICKNESS);
            border.addPoint(tile_x, tile_y + tileSize);
            border.addPoint(tile_x + tileSize, tile_y + tileSize);
            g2.fillPolygon(border);

            gradient = new GradientPaint(tile_x, tile_y, new Color(r, g, b, 200), tile_x - InfoPanel.HIGHLIGHT_GRADIENT_THICKNESS, tile_y, new Color(r, g, b, 0));
            g2.setPaint(gradient);

            border.reset();
            border.addPoint(tile_x - InfoPanel.HIGHLIGHT_GRADIENT_THICKNESS, tile_y - InfoPanel.HIGHLIGHT_GRADIENT_THICKNESS);
            border.addPoint(tile_x, tile_y);
            border.addPoint(tile_x, tile_y + tileSize);
            border.addPoint(tile_x - InfoPanel.HIGHLIGHT_GRADIENT_THICKNESS, tile_y + tileSize + InfoPanel.HIGHLIGHT_GRADIENT_THICKNESS);
            g2.fillPolygon(border);
        }

    }

}
