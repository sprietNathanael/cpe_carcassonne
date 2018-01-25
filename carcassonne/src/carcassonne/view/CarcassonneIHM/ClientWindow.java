/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.CarcassonneIHM;

import Network.Host;
import Network.NetworkGame;
import carcassonne.controller.CarcassonneGameController;
import carcassonne.controller.CarcassonneGameControllerInterface;
import carcassonne.model.carcassonnegame.CarcassonneGameInterface;
import carcassonne.view.CarcassonneIHM.menuStart.ParamPlayers;
import java.awt.Container;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.File;
import java.util.List;
import java.util.Set;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;

/**
 * The Main client window class
 */
public class ClientWindow extends JFrame
{

    private final String musicGame = "resources/music/musicGame.mp3";
    String uriString = new File(musicGame).toURI().toString();
    MediaPlayer menuMediaPlayer = new MediaPlayer(new Media(uriString));

    /**
     * Window constructor
     *
     * @param playerList
     * @param playableColors
     */
    public ClientWindow(List<ParamPlayers> playerList, Set<String> playableColors, boolean cbExtRiver, boolean cbExtInnsAndCath)
    {
        super("Carcassonne");
        cleanContentPane();
        GameView gameView = new GameView(playerList, playableColors, cbExtRiver, cbExtInnsAndCath);
        gameView.show(getContentPane());

        maximizeWindow();
        menuMediaPlayer.play();
    }

    /**
     * Window contructor
     */
    public ClientWindow()
    {
        super("Carcassonne");
        cleanContentPane();
        GameView gameView = new GameView();
        gameView.show(getContentPane());

        maximizeWindow();
        menuMediaPlayer.play();
    }

    /**
     * Constructor for localNetwork game
     *
     * @param host
     * @param playableColors
     */
    public ClientWindow(Set<String> playableColors, Host host)
    {
        super("Carcassonne");
        cleanContentPane();
        GameView gameView = new GameView(playableColors, host);
        gameView.show(getContentPane());
        menuMediaPlayer.play();
        maximizeWindow();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public ClientWindow(Set<String> playableColors, CarcassonneGameInterface game, NetworkGame netGame)
    {
        super("Carcassonne");
        cleanContentPane();
        GameView gameView = new GameView(playableColors, game, netGame);
        gameView.show(getContentPane());
        menuMediaPlayer.play();
        maximizeWindow();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Clean the window
     */
    private void cleanContentPane()
    {
        Container pane = getContentPane();
        pane.setVisible(false);
        pane.removeAll();
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
