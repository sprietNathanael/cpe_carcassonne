/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.ui_test;

import carcassonne.controller.AbstractCarcassonneGameController;
import carcassonne.controller.CarcassonneGameControllerMulti;
import carcassonne.menuStart.Players;
import carcassonne.model.carcassonnegame.CarcassonneGame;
import carcassonne.model.player.Player;
import java.awt.Color;
import java.awt.Container;
import java.util.ArrayList;
import java.util.List;
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
     * Game view constructor
     */
    public GameView(List<Players> li)
    {
        try {
            this.players = new ArrayList<>();
            players.add(new Player("player1", "blue"));
            players.add(new Player("player2", "green"));
            players.add(new Player("player3", "red"));
            players.add(new Player("player4", "black"));
            this.game = new CarcassonneGame(players);
            // TODO
            // Populate sets and initialize game
            this.controller = new CarcassonneGameControllerMulti(game);
        } catch (Exception ex) {
            Logger.getLogger(GameView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    GameView()
    {
          try {
            this.players = new ArrayList<>();
            players.add(new Player("player1", "blue"));
            players.add(new Player("player2", "green"));
            players.add(new Player("player3", "red"));
            players.add(new Player("player4", "black"));
            this.game = new CarcassonneGame(players);
            // TODO
            // Populate sets and initialize game
            this.controller = new CarcassonneGameControllerMulti(game);
        } catch (Exception ex) {
            Logger.getLogger(GameView.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        this.game.addObserver(mainPanel);

        pane.add(mainPanel);
        try {
            this.controller.beginGame();
        } catch (Exception ex) {
            Logger.getLogger(GameView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
