/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.CarcassonneIHM;

import carcassonne.view.CarcassonneIHM.menuStart.ParamPlayers;
import java.awt.Container;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;


/**
 * The Main client window class
 */
public class ClientWindow extends JFrame
{

    /**
     * Window constructor
     * @param playerList
     */
    public ClientWindow(List<ParamPlayers> playerList)
    {
        super("Carcassonne");
        cleanContentPane();
        createGameView(playerList);
    }

    /**
     * Window contructor
     */
    public ClientWindow()
    {
        super("Carcassonne");
        cleanContentPane();
        createGameView();
    }

    /**
     * Clean the window
     */
    private void cleanContentPane() {
        Container pane = getContentPane();
        pane.setVisible(false);
        pane.removeAll();
    }

    /**
     * Create the game view from a player list
     */
    private void createGameView(List<ParamPlayers> playerList) {
        GameView gameView = new GameView(playerList);
        gameView.show(getContentPane());
        
        maximizeWindow();
    }

    /**
     * Create the game view
     */
    private void createGameView() {
        GameView gameView = new GameView();
        gameView.show(getContentPane());
        
        maximizeWindow();
    }
    
    /**
     * Maximize the Window and add a cursor
     */
    private void maximizeWindow()
    {
        this.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        getContentPane().setVisible(true);
        this.setIconImage(new ImageIcon(getClass().getResource("/images/icone carcassonne.jpg")).getImage());
        Toolkit tk = Toolkit.getDefaultToolkit();
        this.setCursor(tk.createCustomCursor(new ImageIcon(getClass().getResource("/images/curseur.png")).getImage(), new Point(0, 0), "nameCursor"));
        
    }

}
