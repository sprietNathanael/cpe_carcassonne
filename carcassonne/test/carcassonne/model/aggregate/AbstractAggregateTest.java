/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.aggregate;

import carcassonne.coord.Coord;
import carcassonne.model.player.Meeple;
import carcassonne.model.player.Player;
import carcassonne.model.tile.AbstractTile;
import carcassonne.model.tile.CasualTile;
import carcassonne.model.type.AbbayType;
import carcassonne.model.type.FieldType;
import carcassonne.model.type.RoadType;
import java.awt.Color;
import java.util.HashMap;
import java.util.HashSet;
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

    public static AbstractTile initiateAbstractTile()
    {
        return new CasualTile("A", //Id
                new FieldType(), new FieldType(), new FieldType(), //North section
                new RoadType(), //East section
                new FieldType(), new RoadType(), new FieldType(), //South section
                new FieldType(), //West section
                new RoadType(), new RoadType(), new RoadType(), new RoadType() //Center section
        );
    }

    public static AbstractTile initiateAbstractTileBis()
    {
        return new CasualTile("B", //Id
                new FieldType(), new FieldType(), new FieldType(), //North section
                new RoadType(), //East section
                new FieldType(), new FieldType(), new FieldType(), //South section
                new FieldType(), //West section
                new AbbayType(), new AbbayType(), new AbbayType(), new AbbayType() //Center section
        );
    }

    public static AbstractAggregate initiateAggregate(int x, int y)
    {
        Set<String> types = new HashSet<>();
        types.add("S");
        types.add("E");

        return new RoadAggregate(x, y, initiateAbstractTile(), types);
    }

    public static Set<String> initiateTypes()
    {
        Set<String> types = new HashSet<>();
        types.add("S");
        types.add("E");
        types.add("CNE");

        return types;
    }

    public static Player initiatePlayer()
    {
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
        AbstractAggregate instance = new AbstractAggregateImpl();

        Player player = initiatePlayer();
        boolean result = instance.addMeeple(player, player.getFirstMeepleAvailable());
        assertEquals(true, result);
    }

    /**
     * Test of addMeeple method, of class AbstractAggregate.
     */
    @Test
    public void testAddMeeple2()
    {
        Player player = initiatePlayer();
        AbstractAggregate instance = new AbstractAggregateImpl(player, player.getFirstMeepleAvailable());

        boolean result = instance.addMeeple(player, player.getFirstMeepleAvailable());
        assertEquals(false, result);
    }

    /**
     * Test of getNeighboredCoordinates method, of class AbstractAggregate.
     */
    @Test
    public void testGetNeighboredCoordinates()
    {
        Player player = new Player("Omg", Color.BLUE);
        AbstractAggregate instance = new AbstractAggregateImpl(player, player.getFirstMeepleAvailable());
        Set<Coord> expResult = new HashSet<>();
        expResult.add(new Coord(0, 1));

        Set<Coord> result = instance.getNeighboredCoordinates(0, 0);
        assertEquals(expResult, result);
    }

    /**
     * Test of getNeighboredCoordinates method, of class AbstractAggregate.
     */
    @Test
    public void testGetNeighboredCoordinates2()
    {
        Player player = new Player("Omg", Color.BLUE);
        AbstractAggregate instance = new AbstractAggregateImpl(player, player.getFirstMeepleAvailable());
        instance.enlargeAggregate(1, 0, AbstractAggregateTest.initiateAbstractTile(), AbstractAggregateTest.initiateTypes());

        Set<Coord> expResult = new HashSet<>();
        expResult.add(new Coord(0, 1));
        expResult.add(new Coord(1, 0));

        Set<Coord> result = instance.getNeighboredCoordinates(0, 0);
        assertEquals(expResult, result);
    }

    /**
     * Test of getNeighboredCoordinates method, of class AbstractAggregate.
     */
    @Test
    public void testGetNeighboredCoordinates3()
    {
        Player player = new Player("Omg", Color.BLUE);
        AbstractAggregate instance = new AbstractAggregateImpl(player, player.getFirstMeepleAvailable());
        instance.enlargeAggregate(1, 0, AbstractAggregateTest.initiateAbstractTile(), AbstractAggregateTest.initiateTypes());

        Set<Coord> expResult = new HashSet<>();

        Set<Coord> result = instance.getNeighboredCoordinates(2, 2);
        assertEquals(expResult, result);
    }

    /**
     * Test of enlargeAggregate method, of class AbstractAggregate.
     */
    @Test
    public void testEnlargeAggregate()
    {
        AbstractAggregate instance = new AbstractAggregateImpl();

        Set<String> newTypes = new HashSet<>();
        newTypes.add("S");
        newTypes.add("W");
        AbstractTile newTile = initiateAbstractTileBis();

        //Ajout d'une tuile à l'aggrégat, sur la position 0;1
        instance.enlargeAggregate(0, 1, newTile, newTypes);
        assertEquals(instance.aggregatedTiles.get(new Coord(0, 1)), newTile);
    }

    /**
     * Test of getMeepleNumber method, of class AbstractAggregate.
     */
    @Test
    public void testGetMeepleNumber()
    {
        Player player = AbstractAggregateTest.initiatePlayer();
        AbstractAggregate instance = new AbstractAggregateImpl(player, player.getBigMeepleAvailable());
        int expResult = 2;
        int result = instance.getMeepleNumber();
        assertEquals(expResult, result);
    }

    /**
     * Test of merge method, if the aggregates are from the same player(s)
     */
    @Test
    public void testMerge()
    {
        AbstractAggregate instance = new AbstractAggregateImpl();
        Player player = new Player("Mo", Color.yellow);
        AbstractAggregate neighborAggregate = new AbstractAggregateImpl(player, player.getBigMeepleAvailable());

        Player player2 = new Player("Mo", Color.yellow);
        instance.addMeeple(player2, player2.getFirstMeepleAvailable());

        instance.merge(neighborAggregate);

        assertEquals(3, instance.meepleNumber);
    }

    /**
     * Test of merge method, if the aggregates are from different player
     */
    @Test
    public void testMerge2()
    {
        AbstractAggregate instance = new AbstractAggregateImpl();
        Player player = new Player("Mo", Color.yellow);
        AbstractAggregate neighborAggregate = new AbstractAggregateImpl(player, player.getBigMeepleAvailable());

        Player player2 = new Player("test", Color.yellow);
        instance.addMeeple(player2, player2.getBigMeepleAvailable());

        instance.merge(neighborAggregate);

        assertEquals(2, instance.meepleNumber);
    }

    /**
     * Test of merge method, if the aggregates are from different player and the
     * second player wins
     */
    @Test
    public void testMerge2Bis()
    {
        AbstractAggregate instance = new AbstractAggregateImpl();
        Player player = new Player("Mo", Color.yellow);
        AbstractAggregate neighborAggregate = new AbstractAggregateImpl(player, player.getFirstMeepleAvailable());

        Player player2 = new Player("test", Color.yellow);
        instance.addMeeple(player2, player2.getBigMeepleAvailable());

        instance.merge(neighborAggregate);

        Set<Player> expResult = new HashSet<>();
        expResult.add(new Player("test", Color.yellow));

        assertEquals(expResult, instance.players);
    }

    /**
     * Test of merge method, if the aggregates are from different player and
     * have same meeple number
     */
    @Test
    public void testMerge3()
    {
        AbstractAggregate instance = new AbstractAggregateImpl();
        Player player = new Player("Mo", Color.yellow);
        AbstractAggregate neighborAggregate = new AbstractAggregateImpl(player, player.getFirstMeepleAvailable());

        Player player2 = new Player("test", Color.yellow);
        instance.addMeeple(player2, player2.getFirstMeepleAvailable());

        instance.merge(neighborAggregate);
        Set<Player> expResult = new HashSet<>();
        expResult.add(new Player("Mo", Color.yellow));
        expResult.add(new Player("test", Color.yellow));

        assertEquals(expResult, instance.players);
    }

    /**
     * Test of merge method, if the current aggregate doesn't have any player
     */
    @Test
    public void testMerge4()
    {
        AbstractAggregate instance = new AbstractAggregateImpl();
        Player player = new Player("Mo", Color.yellow);
        AbstractAggregate neighborAggregate = new AbstractAggregateImpl(player, player.getFirstMeepleAvailable());

        //Simulate an aggregate with already 2 players on it 
        neighborAggregate.players.add(new Player("Koukou", Color.DARK_GRAY));

        instance.merge(neighborAggregate);

        Set<Player> expResult = new HashSet<>();
        expResult.add(new Player("Mo", Color.yellow));
        expResult.add(new Player("Koukou", Color.DARK_GRAY));

        assertEquals(expResult, instance.players);
    }

    /**
     * Test of getCommonPlayers, if there is one common player in these
     * collections
     */
    @Test
    public void testGetCommonPlayers()
    {
        Set<Player> playersSet1 = new HashSet<>();
        playersSet1.add(new Player("C'est moi ;)", Color.green));

        Set<Player> playersSet2 = new HashSet<>();
        playersSet2.add(new Player("C'est moi ;)", Color.green));
        playersSet2.add(new Player("C'est pas moi :(", Color.red));

        //Result expected
        Set<Player> expResult = new HashSet<>();
        expResult.add(new Player("C'est moi ;)", Color.green));

        Set<Player> result = AbstractAggregate.getCommonPlayers(playersSet1, playersSet2);
        assertEquals(expResult, result);
    }

    /**
     * Test of getCommonPlayers if there is no player in one of the collection
     */
    @Test
    public void testGetCommonPlayers2()
    {
        Set<Player> playersSet1 = new HashSet<>();

        Set<Player> playersSet2 = new HashSet<>();
        playersSet2.add(new Player("C'est moi ;)", Color.green));
        playersSet2.add(new Player("C'est pas moi :(", Color.red));

        //Result expected
        Set<Player> expResult = new HashSet<>();

        Set<Player> result = AbstractAggregate.getCommonPlayers(playersSet1, playersSet2);
        assertEquals(expResult, result);
    }

    /**
     * Test of getCommonPlayers if there is no player in any collection
     */
    @Test
    public void testGetCommonPlayers3()
    {
        Set<Player> playersSet1 = new HashSet<>();

        Set<Player> playersSet2 = new HashSet<>();

        //Result expected
        Set<Player> expResult = new HashSet<>();

        Set<Player> result = AbstractAggregate.getCommonPlayers(playersSet1, playersSet2);
        assertEquals(expResult, result);
    }

    /**
     * Test of getCommonPlayers if there is no player in common
     */
    @Test
    public void testGetCommonPlayers4()
    {
        Set<Player> playersSet1 = new HashSet<>();
        playersSet1.add(new Player("C'est moi ;)", Color.green));
        playersSet1.add(new Player("C'est pas moi :(", Color.red));
        playersSet1.add(new Player("Bonjouue", Color.red));

        Set<Player> playersSet2 = new HashSet<>();
        playersSet2.add(new Player("C'est pas moi non plus !", Color.red));

        //Result expected
        Set<Player> expResult = new HashSet<>();

        Set<Player> result = AbstractAggregate.getCommonPlayers(playersSet1, playersSet2);
        assertEquals(expResult, result);
    }

    /**
     * Test of checkIsCompleted method, of class AbstractAggregate.
     */
    @Test
    public void testCheckIsCompleted()
    {
        System.out.println("Tester 'checkIsCompleted' pour chaque classe qui étend AbstractAggregate");
        AbstractAggregate abstractAggregate = new AbstractAggregateImpl();
        boolean result = abstractAggregate.checkIsCompleted();
        assertEquals(false, result);
    }

    public class AbstractAggregateImpl extends AbstractAggregate
    {

        public AbstractAggregateImpl()
        {
            super(0, 0, AbstractAggregateTest.initiateAbstractTile(), AbstractAggregateTest.initiateTypes());
        }

        public AbstractAggregateImpl(Player player, Meeple meeple)
        {
            super(0, 1, AbstractAggregateTest.initiateAbstractTile(), AbstractAggregateTest.initiateTypes(), player, meeple);
        }

        public boolean checkIsCompleted()
        {
            return false;
        }
    }
    

    /**
     * Test of mergeSet method, of class CityAggregate.
     */
    @Test
    public void testMergeSet()
    {
        Map<AbstractTile, Set<String>> map1 = new HashMap<>();
        AbstractTile id = initiateAbstractTile();
        Set<String> enums = new HashSet<>();
        enums.add("1");
        enums.add("2");
        map1.put(id, enums);
        
        Map<AbstractTile, Set<String>> map2 = new HashMap<>();
        enums = new HashSet<>();
        enums.add("2");
        enums.add("3");
        map2.put(id, enums);
        
        Map<AbstractTile, Set<String>> expResult = new HashMap<>();
        enums = new HashSet<>();
        enums.add("1");
        enums.add("2");
        enums.add("3");
        expResult.put(id, enums);
        System.out.println(AbstractAggregate.mergeLocationTypesSet(map1, map2).get(id));
        assertEquals(expResult, AbstractAggregate.mergeLocationTypesSet(map1, map2));
    }

}
