/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.player;

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
 * @author Thomas
 */
public class PlayerTest
{
    
    public PlayerTest()
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
     * Test of checkMeepleAvailable method, of class Player.
     */
    @Test
    public void testCheckMeepleAvailableTrue()
    {
        System.out.println("checkMeepleAvailableTrue");
        Player p = new Player("test", Color.yellow);        
        boolean result = p.checkMeepleAvailable();
        assertEquals(true, result);
    }
    
    /**
     * Test of checkMeepleAvailable method, of class Player.
     */
    @Test
    public void testCheckMeepleAvailableFalse()
    {
        System.out.println("checkMeepleAvailableFalse");
        Player p = new Player("test", Color.yellow);  
        p.getMeeple().forEach(m -> m.setIsUsed(true));
        boolean result = p.checkMeepleAvailable();
        assertEquals(false, result);
    }

    /**
     * Test of getFirstMeepleAvailable method, of class Player.
     */
    @Test
    public void testGetFirstMeepleAvailable()
    {
        System.out.println("getFirstMeepleAvailable");
        Player player = new Player("Joueur", Color.black);
        Meeple result = player.getFirstMeepleAvailable();
        ArrayList<Meeple> meeples = player.getMeeple();
        Meeple expResult = null;
        for (Meeple m: meeples)
        {
            if (m.getIsUsed() == false) {
                expResult = m;
                break;
            }
        }
        assertEquals(expResult, result);
    }    
}
