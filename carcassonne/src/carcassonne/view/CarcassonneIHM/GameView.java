/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.CarcassonneIHM;

import carcassonne.view.CarcassonneIHM.Panels.MainPanel;
import carcassonne.controller.AbstractCarcassonneGameController;
import carcassonne.controller.CarcassonneGameControllerMulti;
import carcassonne.view.CarcassonneIHM.menuStart.ParamPlayers;
import carcassonne.model.carcassonnegame.CarcassonneGame;
import carcassonne.model.player.Player;
import RessourcesGlobalVariables.PlayerTypes;
import carcassonne.model.set.BasicSet;
import carcassonne.model.set.InnsAndCathedralsSet;
import carcassonne.model.set.RiverSet;
import carcassonne.model.set.SetInterface;
import java.awt.Container;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Main game view class
 */
public class GameView
{

    private AbstractCarcassonneGameController controller;
    private CarcassonneGame game;
    private ArrayList<Player> players;

    /**
     * Game view constructor with a player list
     *
     * @param playerList
     */
    public GameView(List<ParamPlayers> playerList)
    {
        try {
            contructPlayersListAndGame(playerList); // Build the controller
            this.controller = new CarcassonneGameControllerMulti(game);
        } catch (Exception ex) {
            Logger.getLogger(GameView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Game view constructor
     */
    public GameView()
    {
        try {
            // constructs the default players list
            this.constructDefaultPlayersList();
            Set<SetInterface> sets = new HashSet<SetInterface>();
            sets.add(new BasicSet());
            sets.add(new InnsAndCathedralsSet());
            sets.add(new RiverSet());

            // Build the game
            this.game = new CarcassonneGame(players, sets);
            this.game.setRiverExtensionIsUsed(true);
            this.game.setInnsAndCathedralsExtensionIsUsed(true);
            //this.game = new CarcassonneGame(players);

            // Build the controller
            this.controller = new CarcassonneGameControllerMulti(game);
        } catch (Exception ex) {
            Logger.getLogger(GameView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Game view constructor with a player list
     *
     * @param playerList
     * @param localNetworkControler
     */
    public GameView(List<ParamPlayers> playerList, AbstractCarcassonneGameController localNetworkControler)
    {
        try {
            contructPlayersListAndGame(playerList);

            // Build the controller
            this.controller = localNetworkControler;
        } catch (Exception ex) {
            Logger.getLogger(GameView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Creates the players list and the game
     * @param playerList
     * @throws Exception 
     */
    private void contructPlayersListAndGame(List<ParamPlayers> playerList) throws Exception
    {
        this.players = new ArrayList<>();
        ArrayList<String> colors = new ArrayList<>();

        // Iterate through the players to add them to the main list
        for (ParamPlayers p : playerList) {
            // If the player is not empty
            if (!p.getNom().isEmpty()) {
                // If the color already exists in list
                if (colors.contains(p.getColor())) {
                    // Constructs the default players list
                    this.constructDefaultPlayersList();
                    break;
                }
                // Add the color and the player to the lists 
                colors.add(p.getColor());
                players.add(new Player(p.getNom(), p.getColor().toLowerCase(), p.getPlayerType()));
            }
        }

        // If there are not enough players
        if (players.size() < 2) {
            // constructs the default players list
            this.constructDefaultPlayersList();
        }

        // Build the game
        this.game = new CarcassonneGame(players);
    }

    /**
     * Constructs a predefined list of 4 players
     */
    private void constructDefaultPlayersList()
    {
        this.players = new ArrayList<>();
        players.add(new Player("player1", "blue", PlayerTypes.player));
        players.add(new Player("player2", "green", PlayerTypes.player));
        //players.add(new Player("player3", "red", PlayerTypes.player));
        //players.add(new Player("player4", "black", PlayerTypes.player));
    }

    /**
     * Shows the view
     *
     * @param pane Container that receives the main panel
     */
    public void show(Container pane)
    {
        //Construct the main panel and adds it to the main container
        MainPanel mainPanel = new MainPanel(this.controller, this.players);
        this.controller.addObserver(mainPanel);
        this.game.addObserver(controller);

        pane.add(mainPanel);
        try {
            // Begin the game !
            this.controller.beginGame();
        } catch (Exception ex) {
            Logger.getLogger(GameView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
