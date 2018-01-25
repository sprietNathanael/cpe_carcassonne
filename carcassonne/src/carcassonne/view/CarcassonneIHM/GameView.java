/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.CarcassonneIHM;

import Network.Host;
import RessourcesGlobalVariables.Colors;
import carcassonne.view.CarcassonneIHM.Panels.MainPanel;
import carcassonne.controller.CarcassonneGameController;
import carcassonne.controller.CarcassonneGameControllerMulti;
import carcassonne.view.CarcassonneIHM.menuStart.ParamPlayers;
import carcassonne.model.carcassonnegame.CarcassonneGame;
import carcassonne.model.player.Player;
import RessourcesGlobalVariables.PlayerTypes;
import carcassonne.controller.CarcassonneGameControllerLocalNetwork;
import carcassonne.model.carcassonnegame.CarcassonneGameInterface;
import carcassonne.model.set.BasicSet;
import carcassonne.model.set.InnsAndCathedralsSet;
import carcassonne.model.set.RiverSet;
import carcassonne.model.set.SetInterface;
import java.awt.Container;
import java.util.ArrayList;
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

    private CarcassonneGameController controller;
    private CarcassonneGameInterface game;
    private ArrayList<Player> players;
    private Set<String> playableColors;

    /**
     * Game view constructor with a player list
     *
     * @param playerList
     * @param playableColors
     * @param cbExtRiver
     * @param cbExtInnsAndCath
     */
    public GameView(List<ParamPlayers> playerList, Set<String> playableColors, boolean cbExtRiver, boolean cbExtInnsAndCath)
    {
        System.out.println("[GameView] Construct 1");
        try {
            this.playableColors = playableColors;
            contructPlayersListAndGame(playerList, cbExtRiver, cbExtInnsAndCath); // Build the controller
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
        System.out.println("[GameView] Construct 2");
        try {
            this.contructPlayersListAndGame(new ArrayList<>(), true, true);
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
     * @param playableColors
     * @param localNetworkControler
     */
    public GameView(Set<String> playableColors, Host host)
    {
        System.out.println("[GameView] Construct 3");
        try {
            this.playableColors = playableColors;
            contructPlayersListAndGame(host.getParamPlayers(), true, true);
            // Build the controller
            this.controller = new CarcassonneGameControllerLocalNetwork(game, host);

        } catch (Exception ex) {
            Logger.getLogger(GameView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public GameView(Set<String> playableColors, CarcassonneGameInterface game)
    {
        System.out.println("[GameView] Construct 3");
        try {
            this.playableColors = playableColors;

            // Build the controller
            this.controller = new CarcassonneGameControllerMulti(game);
            this.players = ((CarcassonneGame)game).getPlayers();
            this.game = game;
            

        } catch (Exception ex) {
            Logger.getLogger(GameView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Creates the players list and the game
     *
     * @param playerList
     * @throws Exception
     */
    private void contructPlayersListAndGame(List<ParamPlayers> playerList, boolean cbExtRiver, boolean cbExtInnsAndCath) throws Exception
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

        // constructs the default players list
        Set<SetInterface> sets = new HashSet<>();
        sets.add(new BasicSet());
        if (cbExtInnsAndCath) {
            sets.add(new InnsAndCathedralsSet());
        }

        if (cbExtRiver) {
            sets.add(new RiverSet());
        }
        // Build the game
        this.game = new CarcassonneGame(players, sets);
        /*if (cbExtRiver) {
            this.game.setRiverExtensionIsUsed(true);
        }
        if (cbExtInnsAndCath) {
            this.game.setInnsAndCathedralsExtensionIsUsed(true);
        }*/

    }

    /**
     * Constructs a predefined list of 4 players
     */
    private void constructDefaultPlayersList()
    {
        this.players = new ArrayList<>();
        players.add(new Player("player1", Colors.red, PlayerTypes.player));
        players.add(new Player("player2", Colors.blue, PlayerTypes.basicIA));
        //players.add(new Player("player3", "red", PlayerTypes.player));
        //players.add(new Player("player4", "black", PlayerTypes.player));
        this.playableColors = new HashSet<>();

        this.players.stream().filter((player) -> (player.getPlayerType().equals(PlayerTypes.player))).forEachOrdered((player) -> {
            this.playableColors.add(player.getColor());
        });
    }

    /**
     * Shows the view
     *
     * @param pane Container that receives the main panel
     */
    public void show(Container pane)
    {
        //Construct the main panel and adds it to the main container

        MainPanel mainPanel = new MainPanel(this.controller, this.players, this.playableColors);
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
