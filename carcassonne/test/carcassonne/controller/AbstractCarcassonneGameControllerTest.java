/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.controller;

import carcassonne.coord.Coord;
import carcassonne.model.board.Board;
import carcassonne.model.carcassonnegame.CarcassonneGame;
import carcassonne.model.player.Player;
import carcassonne.model.tile.AbstractTile;
import carcassonne.model.tile.CasualTile;
import carcassonne.model.type.AbbayType;
import carcassonne.model.type.AbstractType;
import carcassonne.model.type.CityType;
import carcassonne.model.type.FieldType;
import carcassonne.model.type.RiverType;
import carcassonne.model.type.RoadType;
import java.awt.Color;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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
     * AbstractCarcassonneGameController.
     */
    @Test
    public void testGetCurrentPlayer()
    {
        Player j1 = new Player("bertrand", Color.pink);
        Player j2 = new Player("thomas", Color.orange);
        ArrayList list = new ArrayList();
        list.add(0, j1);
        list.add(1, j2);

        CarcassonneGame jeu = new CarcassonneGame(list);
        AbstractCarcassonneGameController gameControlSaMere = new AbstractCarcassonneGameController(jeu);

        Player expectedPlayer = gameControlSaMere.getCurrentPlayer();

        System.out.println(expectedPlayer);

        assertEquals(j1, expectedPlayer); //the current player is the first player of the list

    }

    /**
     * Test of putTile method, of class AbstractCarcassonneGameController.
     */
    /*@Test
    public void testPutTile() throws Exception
    {
        Board plateau = new Board();
        
    }*/
    /**
     * Test of putMeeple method, of class AbstractCarcassonneGameController.
     */
    /*@Test
    public void testPutMeeple() throws Exception
    {
        System.out.println("putMeeple");
        String coordinates = "";
        AbstractCarcassonneGameController instance = new AbstractCarcassonneGameController();
        instance.putMeeple(coordinates);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
     */
}
