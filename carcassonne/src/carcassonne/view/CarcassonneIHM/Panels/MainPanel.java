/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.CarcassonneIHM.Panels;

import Network.NetworkGame;
import carcassonne.view.CarcassonneIHM.Panels.Info.InfoPanel;
import carcassonne.view.CarcassonneIHM.Panels.Grid.GridPanel;
import carcassonne.view.CarcassonneIHM.Layers.Meeple.MeeplesLayer;
import carcassonne.view.CarcassonneIHM.Layers.Meeple.MeeplePlacementLayer;
import carcassonne.view.CarcassonneIHM.Layers.Tile.TilesLayer;
import carcassonne.view.CarcassonneIHM.Layers.Tile.TilePlacementLayer;
import carcassonne.controller.CarcassonneGameController;
import carcassonne.coord.Coord;
import carcassonne.model.carcassonnegame.CarcassonneGame;
import carcassonne.model.player.Meeple;
import carcassonne.model.player.Player;
import carcassonne.model.tile.AbstractTile;
import carcassonne.model.type.AbstractType;
import carcassonne.view.CarcassonneIHM.Layers.Field.FieldsLayer;
import carcassonne.view.CarcassonneIHM.Layers.Tile.TileHighlightmentLayer;
import carcassonne.view.CarcassonneIHM.Tools.TileImage;
import carcassonne.view.CarcassonneIHM.Tools.UICoord;
import RessourcesGlobalVariables.PlayerTypes;
import carcassonne.notifyMessage.ObserverMessage;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Set;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Main panel component
 */
public class MainPanel extends JPanel implements java.util.Observer
{

    private CarcassonneGameController controller;
    private TilesLayer tilesLayer;
    private MeeplesLayer meeplesLayer;
    private TilePlacementLayer tilesPlacementLayer;
    private GridPanel gridPanel;
    private MeeplePlacementLayer meeplePlacementLayer;
    private FieldsLayer fieldsLayer;
    private TileHighlightmentLayer tileHighlightmentLayer;
    private InfoPanel infoPanel;
    private ArrayList<Player> players;
    private UICoord lastCoord;
    private boolean interfaceLocked;
    private boolean putBigMeeple;
    private Set<String> playableColors;

    /**
     * Main panel constructor
     *
     * @param controller CarcassonneGameController
     * @param players
     */
    public MainPanel(CarcassonneGameController controller, ArrayList<Player> players, Set<String> playableColors)
    {
        super();
        
        this.players = players;
        this.controller = controller;
        setLayout(new BorderLayout());
        this.interfaceLocked = false;
        this.playableColors = playableColors;

        // Construct a grid panel
        this.gridPanel = new GridPanel();

        // Construct layers
        this.tilesLayer = new TilesLayer(gridPanel, this.controller);
        this.meeplesLayer = new MeeplesLayer(gridPanel, this.controller);
        this.fieldsLayer = new FieldsLayer(gridPanel, this.controller);
        this.meeplePlacementLayer = new MeeplePlacementLayer(gridPanel, this.controller);
        this.tilesPlacementLayer = new TilePlacementLayer(gridPanel, this.controller, this);
        this.tileHighlightmentLayer = new TileHighlightmentLayer(gridPanel, controller);
        
        // Add the layers to the grid panel
        gridPanel.addLayer(tilesPlacementLayer);
        gridPanel.addLayer(tilesLayer);
        gridPanel.addLayer(fieldsLayer);
        gridPanel.addLayer(meeplePlacementLayer);
        gridPanel.addLayer(meeplesLayer);
        gridPanel.addLayer(tileHighlightmentLayer);
        
        // Hide and show the different layers
        this.meeplePlacementLayer.onHide();
        this.tilesLayer.onShow();
        this.meeplesLayer.onShow();
        this.fieldsLayer.onShow();
        this.tileHighlightmentLayer.onShow();
        
        // Construct an info panel
        this.infoPanel = new InfoPanel(this.players, this.controller, this);
        this.infoPanel.setPreferredSize(new Dimension(300, 10));

        // Add the panels to the main panel
        this.add(this.infoPanel, BorderLayout.WEST);
        this.add(gridPanel, BorderLayout.CENTER);
        this.addComponentListener(new ResizeListener());

    }

    /**
     * When the component is notified by the observable
     */
    @Override
    @SuppressWarnings("unchecked")
    public void update(Observable o, Object arg)
    {
        ObserverMessage message = (ObserverMessage)arg;
        String messageType = message.messageType;
        CarcassonneGame game = message.game;
        // If the update is from a game change
        switch (messageType) {
            case "newTurn":
            case "boardChanged":
                {
                    // Get the game's informations
                    HashMap<Coord, AbstractTile> board = game.getBoard().getAllTiles();
                    AbstractTile preview = game.getCurrentTile();
                    ArrayList<Coord> placements = game.getPlacements();
                    
                    if(this.playableColors.contains(game.getCurrentPlayer().getColor()))
                    {
                        this.interfaceLocked = false;
                    }
                    else
                    {
                        this.interfaceLocked = true;
                    }
                    
                    if(game.getLastPutTile() != null)
                    {
                        String color;
                        if(game.getPreviousPlayer() == null)
                        {
                            color = "transp";
                        }
                        else
                        {
                            color = game.getPreviousPlayer().getColor();
                        }
                        this.tileHighlightmentLayer.setTileToHighlight(new UICoord(game.getLastPutTile()), color);
                    }
                    
                    this.fieldsLayer.setFields(game.getFieldAggregates());
                    
                    // Set the preview image of the placement layer
                    if (preview != null) {
                        this.tilesPlacementLayer.setPreview(preview);
                    }
                    // If a tile has just been put
                    if(this.lastCoord != null)
                    {
                        // Get the available aggregates in the last putted tile
                        Set<Set<String>> aggregates = game.getFreeAggregatesInTile(this.lastCoord.getX(), this.lastCoord.getY());
                        // If there are available aggregates
                        if(aggregates.size() > 0)
                        {
                            // Give the aggregates to the meeple placement layer
                            this.meeplePlacementLayer.setAggregates(aggregates);
                            // Displays the pass meeple turn button of the info panel
                            this.infoPanel.displayPassMeepleTurnButton();
                            // Reset the last tile
                            this.lastCoord = null;
                        }
                        else
                        {
                            // Reset the last tile
                            this.lastCoord = null;
                            // End the turn
                            this.controller.endTurn();
                            
                            // Exit the function
                            return;
                        }
                    }
                    else
                    {
                        // Hide the pass meeple turn button
                        this.infoPanel.hidePassMeepleTurnButton();
                        // Hide the meeple placement layer
                        this.meeplePlacementLayer.onHide();
                        
                    }       // Clean positions of placement layer and meeple layer
                    this.tilesPlacementLayer.cleanPositions();
                    this.meeplesLayer.cleanMeeple();
                    // Add positions of tiles layer
                    int top = 0;
                    int bottom = 0;
                    int left = 0;
                    int right = 0;
                    for (HashMap.Entry<Coord, AbstractTile> entry : board.entrySet()) {
                        Coord coord = entry.getKey();
                        if(coord.col > right)
                        {
                            right = coord.col;
                        }
                        if(coord.col < left)
                        {
                            left = coord.col;
                        }
                        if(coord.row > bottom)
                        {
                            bottom = coord.row;
                        }
                        if(coord.row < top)
                        {
                            top = coord.row;
                        }
                        AbstractTile tile = entry.getValue();
                        this.tilesLayer.addPosition(new TileImage(coord.col, coord.row, tile));
                        
                        // Add positions of meeples on layer
                        for(HashMap.Entry<String, AbstractType> type : tile.getTypes().entrySet())
                        {
                            Meeple meeple = type.getValue().getMeeple();
                            if(meeple != null)
                            {
                                this.meeplesLayer.addMeeple(coord, type.getKey(), type.getValue().getMeeple(), type.getValue().toString());
                            }
                        }
                    }
                    this.gridPanel.updateBoardBounds(top, left, bottom, right);
                    // Refresh info panel informations
                    this.infoPanel.refresh(game);
                    // Repaint the panels
                    
                    Thread paintThread  = new Thread(new MainPanel.RepaintAll());
                    paintThread.start();
                    break;
                }
            // If the update is from a placements ready
            case "placementsReady":
                {
                    if(!this.interfaceLocked)
                    {
                        // Shows the tile placements layer
                        this.tilesPlacementLayer.onShow();
                        // Get the game's informations
                        
                        ArrayList<Coord> placements = game.getPlacements();
                        // Add positions of placement layer
                        for(int i = 0; i < placements.size(); i++)
                        {
                            this.tilesPlacementLayer.addPosition(new UICoord(placements.get(i)));
                        }       // Repaint the panels
                        Thread paintThread  = new Thread(new MainPanel.RepaintAll());
                        paintThread.start();
                    }
                    break;
                }
            // If the update is from a game end
            case "gameEnds":
                {
                    // Get the game's informations
                    
                    HashMap<Coord, AbstractTile> board = game.getBoard().getAllTiles();
                    AbstractTile preview = game.getCurrentTile();
                    ArrayList<Coord> placements = game.getPlacements();
                    this.infoPanel.hidePassMeepleTurnButton();
                    this.meeplePlacementLayer.onHide();
                    this.meeplesLayer.cleanMeeple();
                    // Add positions of tiles layer
                    for (HashMap.Entry<Coord, AbstractTile> entry : board.entrySet()) {
                        Coord coord = entry.getKey();
                        AbstractTile tile = entry.getValue();
                        this.tilesLayer.addPosition(new TileImage(coord.col, coord.row, tile));
                        
                        // Add positions of meeples
                        for(HashMap.Entry<String, AbstractType> type : tile.getTypes().entrySet())
                        {
                            Meeple meeple = type.getValue().getMeeple();
                            if(meeple != null)
                            {
                                this.meeplesLayer.addMeeple(coord, type.getKey(), type.getValue().getMeeple(), type.getValue().toString());
                            }
                        }
                    }
                    // Refresh info panel informations
                    this.infoPanel.endGame(game);
                    // Repaint panels
                    Thread paintThread  = new Thread(new MainPanel.RepaintAll());
                    paintThread.start();
                    JOptionPane gg = new JOptionPane(); 
                    ImageIcon img = new ImageIcon("resources/king.png"); 
                    gg.setBackground(Color.white);
                    gg.showMessageDialog(null, "Le joueur gagnant est : " + game.getWinner().getName(), "WINNER", JOptionPane.INFORMATION_MESSAGE, img); 
                    break;
                }
            default:
                break;
        }
        
    
    }
    
    /**
     * When a tile is putted
     * @param newCoord 
     */
    public void putTile(UICoord newCoord)
    {
        // Get the tile coordinates
        this.lastCoord = newCoord;
        // Hides the tiles placement layer
        this.tilesPlacementLayer.onHide();
        
        // Show the meeple placement layer
        this.meeplePlacementLayer.setCurrentPosition(newCoord);
        this.meeplePlacementLayer.onShow();
    }
    
    public void switchFields()
    {
        if(this.fieldsLayer.isVisible())
        {
            this.fieldsLayer.onHide();
        }
        else
        {
            this.fieldsLayer.onShow();
        }
        Thread paintThread  = new Thread(new MainPanel.RepaintAll());
        paintThread.start();
    }
    
    private class RepaintAll implements Runnable
    {

        public void run()
        {
            gridPanel.repaint();
            infoPanel.repaint();
        }
    }
    
    class ResizeListener extends ComponentAdapter {
        public void componentResized(ComponentEvent e) {
            gridPanel.reCenter();
            Thread paintThread  = new Thread(new MainPanel.RepaintAll());
            paintThread.start();
        }
}
}
