package carcassonne.launcher.localLauncher;

/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
import carcassonne.controller.CarcassonneGameControllerMulti;
import carcassonne.model.carcassonnegame.CarcassonneGame;
import carcassonne.model.player.Player;
import carcassonne.view.CarcassonneCmdLine;
import java.util.ArrayList;

/**
 * The main class to launch a carcassonne game in command line interface
 *
 * @author nathanael
 */
public class LauncherCmdLine
{

    public static void main(String[] args) throws Exception
    {
        CarcassonneGame model;
        CarcassonneGameControllerMulti controller;

        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("player1", "blue"));
        players.add(new Player("player2", "green"));
        players.add(new Player("player3", "red"));
        players.add(new Player("player4", "black"));
        model = new CarcassonneGame(players);
        controller = new CarcassonneGameControllerMulti(model);
        CarcassonneCmdLine view = new CarcassonneCmdLine(controller);
        view.go();

    }
}