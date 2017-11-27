/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.ui_test;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.text.Position;

/**
 *
 * @author nathanael
 */
public class MainPanel extends JPanel
{

    public MainPanel()
    {
        super();
        setLayout(new BorderLayout());
        GridPanel gridPanel = new GridPanel();
        TilesLayer tilesLayer = new TilesLayer(gridPanel);
        PlacementLayer placementLayer = new PlacementLayer(gridPanel);
        placementLayer.setPreview(new TileImage(-1,-1,"E",0));
        gridPanel.addLayer(placementLayer);
        gridPanel.addLayer(tilesLayer);
        //testLayer.addPosition(new Coord(0, 0));
        tilesLayer.addPosition(new TileImage(0,0,"A",0));
        tilesLayer.addPosition(new TileImage(1,0,"B",90));
        tilesLayer.addPosition(new TileImage(2,0,"C",180));
        tilesLayer.addPosition(new TileImage(3,0,"D",270));
        placementLayer.addPosition(new Coord(0, 1));
        placementLayer.addPosition(new Coord(0, -1));
        placementLayer.addPosition(new Coord(-1, 0));
        
        this.add(gridPanel);
    }
    
    
}
