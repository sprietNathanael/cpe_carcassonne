/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.carcassonnegame;

import carcassonne.model.player.Player;
import carcassonne.model.set.SetInterface;
import carcassonne.model.tile.AbstractTile;
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
     */
    @Test
    public void testGetCurrentPlayer()
    {
        System.out.println("getCurrentPlayer");
        CarcassonneGame instance = new CarcassonneGame();
        Player expResult = null;
        Player result = instance.getCurrentPlayer();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of nextPlayer method, of class CarcassonneGame.
     */
    @Test
    public void testNextPlayer()
    {
        System.out.println("nextPlayer");
        CarcassonneGame instance = new CarcassonneGame();
        Player expResult = null;
        Player result = instance.nextPlayer();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addSetToPile method, of class CarcassonneGame.
     */
    @Test
    public void testAddSetToPile()
    {
        System.out.println("addSetToPile");
        SetInterface newSet = null;
        CarcassonneGame instance = new CarcassonneGame();
        instance.addSetToPile(newSet);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pileTile method, of class CarcassonneGame.
     */
    @Test
    public void testPileTile()
    {
        System.out.println("pileTile");
        CarcassonneGame instance = new CarcassonneGame();
        instance.pileTile();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of drawFromPile method, of class CarcassonneGame.
     */
    @Test
    public void testDrawFromPile()
    {
        System.out.println("drawFromPile");
        CarcassonneGame instance = new CarcassonneGame();
        AbstractTile expResult = null;
        AbstractTile result = instance.drawFromPile();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of putTile method, of class CarcassonneGame.
     */
    @Test
    public void testPutTile() throws Exception
    {
        System.out.println("putTile");
        AbstractTile tile = null;
        int row = 0;
        int column = 0;
        CarcassonneGame instance = new CarcassonneGame();
        instance.putTile(tile, row, column);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
