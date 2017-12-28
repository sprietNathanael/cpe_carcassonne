/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.carcassonnegame;

import carcassonne.model.player.Player;
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
public class CarcassonneGameTest
{
    
    public CarcassonneGameTest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }
    
    @Before
    public void setUp()
    {
    }
    
    @After
    public void tearDown()
    {
    }

    /**
     * Test of getCurrentPlayer method, of class CarcassonneGame.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetCurrentPlayer() throws Exception
    {
        System.out.println("getCurrentPlayer");
        Player J1 = new Player("Thomas", "yellow");
        Player J2 = new Player("Etienne", "pink");
        ArrayList<Player> list = new ArrayList<>();
        list.add(J1);
        list.add(J2);
        
        CarcassonneGame instance = new CarcassonneGame(list);
        
        Player result = instance.getCurrentPlayer();
        assertEquals(J1, result);
    }

    /**
     * Test of nextPlayer method, of class CarcassonneGame.
     * @throws java.lang.Exception
     */
    @Test
    public void testNextPlayer() throws Exception
    {
         System.out.println("getCurrentPlayer");
        Player J1 = new Player("Thomas", "yellow");
        Player J2 = new Player("Etienne", "pink");
        ArrayList<Player> list = new ArrayList<>();
        list.add(J1);
        list.add(J2);
        
        CarcassonneGame instance = new CarcassonneGame(list);
        
        Player result = instance.nextPlayer();
        assertEquals(J2, result);
    }

    
}
