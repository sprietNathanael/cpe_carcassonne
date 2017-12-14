/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.ui_test;

import carcassonne.controller.AbstractCarcassonneGameController;
import carcassonne.controller.CarcassonneGameControllerMulti;
import carcassonne.controller.CarcassonneGameControllerSolo;
import carcassonne.model.carcassonnegame.CarcassonneGame;
import carcassonne.model.player.Player;
import java.awt.Color;
import java.awt.Container;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Main game view class
 */
public class GameView
{
    private AbstractCarcassonneGameController controller;
    private CarcassonneGame game;

    /**
     * Game view constructor
     */
    public GameView()
    {
        try {
            ArrayList<Player> players = new ArrayList<>();
            players.add(new Player("player1", Color.BLUE));
            players.add(new Player("player2", Color.GREEN));
            players.add(new Player("player3", Color.RED));
            players.add(new Player("player4", Color.BLACK));
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
     * @param pane Container that receives the main panel
     */
    public void show(Container pane)
    {
        //Construct the main panel and adds it to the main container
        MainPanel mainPanel = new MainPanel(this.controller);
        this.game.addObserver(mainPanel);
        
        pane.add(mainPanel);
        try {
            this.controller.beginGame();
        } catch (Exception ex) {
            Logger.getLogger(GameView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
