/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.ui_test;

import carcassonne.controller.AbstractCarcassonneGameController;
import carcassonne.coord.Coord;
import carcassonne.model.carcassonnegame.CarcassonneGame;
import carcassonne.model.player.Player;
import carcassonne.model.tile.AbstractTile;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
    private GridPanel gridPanel;
    private InfoPanel infoPanel;
    private ArrayList<Player> players;

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

        // Construct placement layer
        this.placementLayer = new PlacementLayer(gridPanel, this.controller);

        // Add the layers to the grid panel
        gridPanel.addLayer(placementLayer);
        gridPanel.addLayer(tilesLayer);

        this.infoPanel = new InfoPanel(this.players);

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
                this.placementLayer.setPreview(new TileImage(0, 0, preview));
            }

            // Clean and add positions of placement layer
            this.placementLayer.cleanPositions();
            for (int i = 0; i < placements.size(); i++) {
                this.placementLayer.addPosition(new UICoord(placements.get(i)));
            }

            // Add positions of tiles layer
            for (HashMap.Entry<Coord, AbstractTile> entry : board.entrySet()) {
                Coord key = entry.getKey();
                AbstractTile value = entry.getValue();
                this.tilesLayer.addPosition(new TileImage(key.col, key.row, value));
            }
            // Refresh info panel informations
            this.infoPanel.refresh(game);
            this.gridPanel.repaint();
            this.infoPanel.repaint();

        }

    }
}
