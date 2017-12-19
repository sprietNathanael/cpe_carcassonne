/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.ui_test;

import carcassonne.controller.AbstractCarcassonneGameController;
import carcassonne.coord.Coord;
import carcassonne.model.tile.AbstractTile;
import carcassonne.notifyMessage.ObserverMessage;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import javax.swing.JPanel;

/**
 * Main panel component
 */
public class MainPanel extends JPanel implements java.util.Observer
{
    private final AbstractCarcassonneGameController controller;
    private final TilesLayer tilesLayer;
    private final PlacementLayer placementLayer;
    private final GridPanel gridPanel;
    /**
     * Main panel constructor
     * @param controller AbstractCarcassonneGameController
     */
    public MainPanel(AbstractCarcassonneGameController controller)
    {
        super();
        this.controller = controller;        
        
        // Construct a grid panel
        this.gridPanel = new GridPanel();
        
        // Construct tiles layer
        this.tilesLayer = new TilesLayer(gridPanel, this.controller);
        
        // Construct placement layer
        this.placementLayer = new PlacementLayer(gridPanel, this.controller);
        
        // Add the layers to the grid panel
        gridPanel.addLayer(placementLayer);
        gridPanel.addLayer(tilesLayer);
        
        addPanel();
    }
    
    /*
    * Add the grid panel to the main panel
    */
    private void addPanel() 
    {        
        setLayout(new BorderLayout());        
        this.add(gridPanel);
    }

    @Override
    public void update(Observable o, Object arg)
    {
        ObserverMessage message = (ObserverMessage)arg;
        HashMap<Coord, AbstractTile> board = message.getBoard();
        AbstractTile preview = message.getPreview();
        ArrayList<Coord> placements = message.getPlacements();
        
        if(preview != null)
        {
            this.placementLayer.setPreview(new TileImage(0,0,preview));
        }
        this.placementLayer.cleanPositions();
        
        for(int i = 0; i < placements.size(); i++)
        {
            this.placementLayer.addPosition(new UICoord(placements.get(i)));
        }
        
        board.entrySet().forEach((entry) -> {
            Coord key = entry.getKey();
            AbstractTile value = entry.getValue();
            this.tilesLayer.addPosition(new TileImage(key.col, key.row, value));
        });
        
        this.gridPanel.repaint();
                
    }
        
}
