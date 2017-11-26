/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.aggregate;

import carcassonne.coord.Coord;
import carcassonne.model.player.Player;
import carcassonne.model.tile.AbstractTile;
import carcassonne.model.tile.CasualTile;
import carcassonne.model.type.AbbayType;
import carcassonne.model.type.AbstractType;
import carcassonne.model.type.FieldType;
import carcassonne.model.type.RoadType;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Étienne
 */
public class AbstractAggregateTest
{
    private static AbstractTile initiateAbstractTile(){
        return new CasualTile("A", //Id
            new FieldType(), new FieldType(), new FieldType(), //North section
            new FieldType(), //East section
            new FieldType(), new RoadType(), new FieldType(), //South section
            new FieldType(), //West section
            new AbbayType(), new AbbayType(), new AbbayType(), new AbbayType() //Center section
        );
    }
    
    private static Player initiatePlayer(){
        return new Player("Etidur :)", Color.GREEN);
    }

    public AbstractAggregateTest()
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
     * Test of addMeeple method, of class AbstractAggregate.
     */
    @Test
    public void testAddMeeple()
    {
        System.out.println("addMeeple");
        
        AbstractTile tile = initiateAbstractTile();
        List<String> types = new ArrayList<>();
        types.add("S");
        
        AbstractAggregate instance = new CityAggregate(0, 0, tile, types);

        Player player = initiatePlayer();
        boolean result = instance.addMeeple(2, player);
        assertEquals(true, result);
    }

    /**
     * Test of addMeeple method, of class AbstractAggregate.
     */
    @Test
    public void testAddMeeple2()
    {
        System.out.println("addMeeple");
        
        AbstractTile tile = initiateAbstractTile();
        List<String> types = new ArrayList<>();
        types.add("S");
        Player player = initiatePlayer();
        
        AbstractAggregate instance = new CityAggregate(0, 0, tile, types, player, 1);

        boolean result = instance.addMeeple(2, player);
        assertEquals(false, result);
    }

    /**
     * Test of getNeighboredCoordinates method, of class AbstractAggregate.
     */
    @Test
    public void testGetNeighboredCoordinates()
    {
        System.out.println("getNeighboredCoordinates");
        int row = 0;
        int col = 0;
        AbstractAggregate instance = null;
        List<Coord> expResult = null;
        List<Coord> result = instance.getNeighboredCoordinates(row, col);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of enlargeAggregate method, of class AbstractAggregate.
     */
    @Test
    public void testEnlargeAggregate()
    {
        System.out.println("enlargeAggregate");
        int row = 0;
        int col = 0;
        AbstractTile newTile = null;
        List<String> types = null;
        AbstractAggregate instance = null;
        instance.enlargeAggregate(row, col, newTile, types);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAggregatedTiles method, of class AbstractAggregate.
     */
    @Test
    public void testGetAggregatedTiles()
    {
        System.out.println("getAggregatedTiles");
        AbstractAggregate instance = null;
        Map<Coord, AbstractTile> expResult = null;
        Map<Coord, AbstractTile> result = instance.getAggregatedTiles();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAggregatedTypes method, of class AbstractAggregate.
     */
    @Test
    public void testGetAggregatedTypes()
    {
        System.out.println("getAggregatedTypes");
        AbstractAggregate instance = null;
        Map<AbstractTile, List<String>> expResult = null;
        Map<AbstractTile, List<String>> result = instance.getAggregatedTypes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPlayers method, of class AbstractAggregate.
     */
    @Test
    public void testGetPlayers()
    {
        System.out.println("getPlayers");
        AbstractAggregate instance = null;
        Set<Player> expResult = null;
        Set<Player> result = instance.getPlayers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMeepleNumber method, of class AbstractAggregate.
     */
    @Test
    public void testGetMeepleNumber()
    {
        System.out.println("getMeepleNumber");
        AbstractAggregate instance = null;
        int expResult = 0;
        int result = instance.getMeepleNumber();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of merge method, of class AbstractAggregate.
     */
    @Test
    public void testMerge()
    {
        System.out.println("merge");
        AbstractAggregate neighborAggregate = null;
        AbstractAggregate instance = null;
        instance.merge(neighborAggregate);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCommonPlayers method, of class AbstractAggregate.
     */
    @Test
    public void testGetCommonPlayers()
    {
        System.out.println("getCommonPlayers");
        Set<Player> playersSet1 = null;
        Set<Player> playersSet2 = null;
        Set<Player> expResult = null;
        Set<Player> result = AbstractAggregate.getCommonPlayers(playersSet1, playersSet2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkIsCompleted method, of class AbstractAggregate.
     */
    @Test
    public void testCheckIsCompleted()
    {
        System.out.println("checkIsCompleted");
        AbstractAggregate instance = null;
        boolean expResult = false;
        boolean result = instance.checkIsCompleted();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class AbstractAggregateImpl extends AbstractAggregate
    {

        public AbstractAggregateImpl()
        {
            super(0, 0, null, null);
        }

        public boolean checkIsCompleted()
        {
            return false;
        }
    }

}
