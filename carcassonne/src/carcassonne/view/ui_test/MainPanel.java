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
    private AbstractCarcassonneGameController controller;
    private TilesLayer tilesLayer;
    private PlacementLayer placementLayer;
    /**
     * Main panel constructor
     */
    public MainPanel(AbstractCarcassonneGameController controller)
    {
        super();
        this.controller = controller;
        setLayout(new BorderLayout());
        
        // Construct a grid panel
        GridPanel gridPanel = new GridPanel();
        
        // Construct tiles layer
        this.tilesLayer = new TilesLayer(gridPanel, this.controller);
        
        // Construct placement layer
        this.placementLayer = new PlacementLayer(gridPanel, this.controller);
        
        // Add the layers to the grid panel
        gridPanel.addLayer(placementLayer);
        gridPanel.addLayer(tilesLayer);
        
        
        // ONLY FOR TESTS        
        // Set the tile preview for the placement layer
        /*placementLayer.setPreview(new TileImage(-1,-1,"E",0));
        
        // Add some tiles to the tile layer
        tilesLayer.addPosition(new TileImage(0,0,"A",0));
        tilesLayer.addPosition(new TileImage(1,0,"B",90));
        tilesLayer.addPosition(new TileImage(2,0,"C",180));
        tilesLayer.addPosition(new TileImage(3,0,"D",270));
        
        // Add some possible placements to the placement layer
        placementLayer.addPosition(new Coord(0, 1));
        placementLayer.addPosition(new Coord(0, -1));
        placementLayer.addPosition(new Coord(-1, 0));*/
        
        // Add the grid panel to the main panel
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
        
        System.out.println(board.get(new Coord(0,0)));
        AbstractTile currTile = board.get(new Coord(0,0));
        if(currTile != null)
        {
            this.tilesLayer.addPosition(new TileImage(0,0,currTile));
        }
        
        
    }
    
    
}
