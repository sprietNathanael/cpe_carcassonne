/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.controller;

import carcassonne.model.board.Board;
import carcassonne.model.carcassonnegame.CarcassonneGame;
import carcassonne.model.player.Meeple;
import carcassonne.model.player.Player;
import carcassonne.model.tile.AbstractTile;
import carcassonne.model.tile.CasualTile;
import carcassonne.model.type.CityType;
import carcassonne.model.type.FieldType;
import carcassonne.model.type.RiverType;
import carcassonne.model.type.RoadType;
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
     * AbstractCarcassonneGameController.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetCurrentPlayer() throws Exception
    {
        Player j1 = new Player("bertrand", "pink");
        Player j2 = new Player("thomas", "orange");
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
     * Test of putCurrentTile method, of class AbstractCarcassonneGameController.
     * @throws java.lang.Exception
     */
    @Test
    public void testPutTile() throws Exception
    {
        
        Player j1 = new Player("bertrand", "pink");
        Player j2 = new Player("thomas", "orange");
        ArrayList<Player> list = new ArrayList();
        list.add(j1);
        list.add(j2);
        
        CarcassonneGame jeu =  new CarcassonneGame(list);
        //AbstractCarcassonneGameController game = new AbstractCarcassonneGameController(jeu);
        
        CasualTile t1 = new CasualTile("A",
                new CityType(), new RoadType(), new RiverType(), new RoadType(), new RiverType(), new CityType(), new RoadType(), // Northen line
                new CityType(), // East
                new RoadType(), new FieldType(), new RiverType(), new FieldType(), new CityType(), new RiverType(), new FieldType(), // Southern line
                new RiverType(), // West
                new RoadType(), new CityType(), new FieldType(), new RiverType() // Center
        );
        
        jeu.putTile(t1, 5, 8);
        
        Board bobo  = jeu.getBoard();
        
        AbstractTile expectedTile = bobo.getTile(5,8);
        assertEquals(expectedTile, t1);
          
        
        
    }
    /**
     * Test of putMeeple method, of class AbstractCarcassonneGameController.
     * @throws java.lang.Exception
     */
    @Test
    public void testPutMeeple() throws Exception //IL FAUT PUSHER 
    {
        Player j1 = new Player("bertrand", "pink");
        Player j2 = new Player("thomas", "orange");
        ArrayList<Player> list = new ArrayList();
        list.add(j1);
        list.add(j2);
        CarcassonneGame jeu = new CarcassonneGame(list);
        
        Meeple m1 = new Meeple();
        RoadType rot = new RoadType();
        
        CasualTile t1 = new CasualTile("A",
                new CityType(), new RoadType(), new RiverType(), new RoadType(), new RiverType(), new CityType(), new RoadType(), // Northen line
                new CityType(), // East
                new RoadType(), new FieldType(), new RiverType(), new FieldType(), new CityType(), new RiverType(), new FieldType(), // Southern line
                rot, // West
                new RoadType(), new CityType(), new FieldType(), new RiverType() // Center
        );
        
        jeu.putMeeple(m1, t1, j1, rot);      
        assertEquals(rot.getMeeple(), m1);
    }
     
}
