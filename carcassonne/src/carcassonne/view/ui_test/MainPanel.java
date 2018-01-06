/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.ui_test;

import carcassonne.controller.AbstractCarcassonneGameController;
import carcassonne.coord.Coord;
import carcassonne.model.carcassonnegame.CarcassonneGame;
import carcassonne.model.player.Meeple;
import carcassonne.model.player.Player;
import carcassonne.model.tile.AbstractTile;
import carcassonne.model.type.AbstractType;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Set;
import javax.swing.JPanel;

/**
 * Main panel component
 */
public class MainPanel extends JPanel implements java.util.Observer
{

    private AbstractCarcassonneGameController controller;
    private TilesLayer tilesLayer;
    private MeeplesLayer meeplesLayer;
    private TilePlacementLayer tilesPlacementLayer;
    private GridPanel gridPanel;
    private MeeplePlacementLayer meeplePlacementLayer;
    private InfoPanel infoPanel;
    private ArrayList<Player> players;
    private UICoord lastCoord;

    /**
     * Main panel constructor
     *
     * @param controller AbstractCarcassonneGameController
     */
    public MainPanel(AbstractCarcassonneGameController controller, ArrayList<Player> players)
    {
        super();
        this.players = players;
        this.controller = controller;
        setLayout(new BorderLayout());

        // Construct a grid panel
        this.gridPanel = new GridPanel();

        // Construct tiles layer
        this.tilesLayer = new TilesLayer(gridPanel, this.controller);
        
        this.meeplesLayer = new MeeplesLayer(gridPanel, this.controller);
        
        this.meeplePlacementLayer = new MeeplePlacementLayer(gridPanel, this.controller);
        
        // Construct placement layer
        this.tilesPlacementLayer = new TilePlacementLayer(gridPanel, this.controller, this);
        
        // Add the layers to the grid panel
        gridPanel.addLayer(tilesPlacementLayer);
        gridPanel.addLayer(tilesLayer);
        gridPanel.addLayer(meeplePlacementLayer);
        gridPanel.addLayer(meeplesLayer);
        this.meeplePlacementLayer.onHide();
        this.tilesLayer.onShow();
        this.meeplesLayer.onShow();
        this.infoPanel = new InfoPanel(this.players, this.controller);

        // Add the grid panel to the main panel
        this.infoPanel.setPreferredSize(new Dimension(300, 10));
        this.add(this.infoPanel, BorderLayout.WEST);
        this.add(gridPanel, BorderLayout.CENTER);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void update(Observable o, Object arg)
    {
        String messageType = (String) arg;
        System.out.println(messageType);
        // If the update is from a game change
        if (messageType.equals("boardChanged")) {
            // Get the game's informations
            CarcassonneGame game = (CarcassonneGame) o;
            HashMap<Coord, AbstractTile> board = game.getBoard().getAllTiles();
            AbstractTile preview = game.getCurrentTile();
            ArrayList<Coord> placements = game.getPlacements();

            // Set the preview image of the placement layer
            if (preview != null) {
                this.tilesPlacementLayer.setPreview(preview);
            }
            // If a tile has just been put
            if(this.lastCoord != null)
            {
                Set<Set<String>> aggregates = game.getFreeAggregatesInTile(this.lastCoord.getX(), this.lastCoord.getY());
                if(aggregates.size() > 0)
                {
                    this.meeplePlacementLayer.setAggregates(aggregates);
                    this.infoPanel.displayPassMeepleTurnButton();
                    this.lastCoord = null;
                }
                else
                {
                    this.lastCoord = null;
                    this.controller.endTurn();
                    return;
                }
            }
            else
            {
                this.infoPanel.hidePassMeepleTurnButton();
                this.meeplePlacementLayer.onHide();
                
            }

            // Clean positions of placement layer
            this.tilesPlacementLayer.cleanPositions();
            this.meeplesLayer.cleanMeeple();

            // Add positions of tiles layer
            for (HashMap.Entry<Coord, AbstractTile> entry : board.entrySet()) {
                Coord coord = entry.getKey();
                AbstractTile tile = entry.getValue();
                this.tilesLayer.addPosition(new TileImage(coord.col, coord.row, tile));
                for(HashMap.Entry<String, AbstractType> type : tile.getTypes().entrySet())
                {
                    Meeple meeple = type.getValue().getMeeple();
                    if(meeple != null)
                    {
                        this.meeplesLayer.addMeeple(coord, type.getKey(), type.getValue().getMeeple());
                    }
                }
            }
            // Refresh info panel informations
            this.infoPanel.refresh(game);
            this.gridPanel.repaint();
            this.infoPanel.repaint();
        
        }
        else if(messageType.equals("placementsReady"))
        {
            this.tilesPlacementLayer.onShow();
            // Get the game's informations
            CarcassonneGame game = (CarcassonneGame) o;
            ArrayList<Coord> placements = game.getPlacements();
            // Add positions of placement layer
            for(int i = 0; i < placements.size(); i++)
            {
                this.tilesPlacementLayer.addPosition(new UICoord(placements.get(i)));
            }
            
            this.gridPanel.repaint();
            this.infoPanel.repaint();
        }
    
    }
    
    public void putTile(UICoord newCoord)
    {
        this.lastCoord = newCoord;
        this.tilesPlacementLayer.onHide();
        this.meeplePlacementLayer.setCurrentPosition(newCoord);
        this.meeplePlacementLayer.onShow();
        //this.gridPanel.repaint();
    }
}
