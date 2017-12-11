/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.ui_test;

import java.awt.BorderLayout;
import javax.swing.JPanel;

/**
 * Main panel component
 */
public class MainPanel extends JPanel
{

    /**
     * Main panel constructor
     */
    public MainPanel()
    {
        super();
        setLayout(new BorderLayout());
        
        // Construct a grid panel
        GridPanel gridPanel = new GridPanel();
        
        // Construct tiles layer
        TilesLayer tilesLayer = new TilesLayer(gridPanel);
        
        // Construct placement layer
        PlacementLayer placementLayer = new PlacementLayer(gridPanel);
        
        // Add the layers to the grid panel
        gridPanel.addLayer(placementLayer);
        gridPanel.addLayer(tilesLayer);
        
        
        // ONLY FOR TESTS        
        // Set the tile preview for the placement layer
        placementLayer.setPreview(new TileImage(-1,-1,"E",0));
        
        // Add some tiles to the tile layer
        tilesLayer.addPosition(new TileImage(0,0,"A",0));
        tilesLayer.addPosition(new TileImage(1,0,"B",90));
        tilesLayer.addPosition(new TileImage(2,0,"C",180));
        tilesLayer.addPosition(new TileImage(3,0,"D",270));
        
        // Add some possible placements to the placement layer
        placementLayer.addPosition(new Coord(0, 1));
        placementLayer.addPosition(new Coord(0, -1));
        placementLayer.addPosition(new Coord(-1, 0));
        
        // Add the grid panel to the main panel
        this.add(gridPanel);
    }
    
    
}
