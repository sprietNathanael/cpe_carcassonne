/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
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
    public void testCheckMeepleAvailableFalse()
    {
        System.out.println("checkMeepleAvailableFalse");
        Player p = new Player("test", "yellow", "Player");
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
        Player player = new Player("Joueur", "black", "Player");
        Meeple result = player.getFirstMeepleAvailable();
        ArrayList<Meeple> meeples = player.getMeeple();
        Meeple expResult = null;
        for (Meeple m : meeples) {
            if (m.getIsUsed() == false) {
                expResult = m;
                break;
            }
        }
        assertEquals(expResult, result);
    }

    /**
     * Test of countMeeples method, of class Player.
     */
    @Test
    public void testCountPoints()
    {
        System.out.println("countMeeples");
        Set<Meeple> meeples = new HashSet<>();
        meeples.add(new Meeple(new Player("un", "red", "Player")));
        meeples.add(new Meeple(new Player("deux", "blue", "Player")));
        meeples.add(new Meeple(true, new Player("trois", "yellow", "Player")));

        int expResult = 4;
        int result = Player.countPoints(meeples);
        assertEquals(expResult, result);
    }
}
