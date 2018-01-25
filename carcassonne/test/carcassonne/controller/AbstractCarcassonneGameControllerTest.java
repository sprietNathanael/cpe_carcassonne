/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.controller;

import carcassonne.model.carcassonnegame.CarcassonneGame;
import carcassonne.model.player.Player;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author thomas
 */
public class AbstractCarcassonneGameControllerTest
{

    public AbstractCarcassonneGameControllerTest()
    {
    }

    /**
     * Test of getCurrentPlayer method, of class
 CarcassonneGameController.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetCurrentPlayer() throws Exception
    {
        Player j1 = new Player("bertrand", "blue", "Player");
        Player j2 = new Player("thomas", "black", "Player");
        ArrayList<Player> list = new ArrayList<>();
        list.add(0, j1);
        list.add(1, j2);

        CarcassonneGame jeu = new CarcassonneGame(list);
        CarcassonneGameController gameController = new CarcassonneGameController(jeu);

        Player expectedPlayer = gameController.getCurrentPlayer();

        System.out.println(expectedPlayer);

        assertEquals(j1, expectedPlayer); //the current player is the first player of the list
    }
}
