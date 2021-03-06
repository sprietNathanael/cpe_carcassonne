/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.aggregate;

import carcassonne.coord.Coord;
import carcassonne.model.player.Meeple;
import carcassonne.model.player.Player;
import static carcassonne.model.set.BasicSet.retTreeSet;
import carcassonne.model.tile.AbstractTile;
import carcassonne.model.tile.CasualTile;
import carcassonne.model.type.AbbayType;
import carcassonne.model.type.FieldType;
import carcassonne.model.type.RoadType;
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
        Set<Set<String>> aggregates = new HashSet<>();
        aggregates.add(retTreeSet("S"));
        aggregates.add(retTreeSet("SSW", "SW", "SWW", "W", "NWW", "NW", "NNW", "N",
                "NNE", "NE", "NEE", "E", "SSE", "SE", "SSE"));
        return new CasualTile("A", "casual", "A",//Id
                new FieldType(), new FieldType(), new FieldType(), //North section
                new RoadType(), //East section
                new FieldType(), new RoadType(), new FieldType(), //South section
                new FieldType(), //West section
                new RoadType(), new RoadType(), new RoadType(), new RoadType(), aggregates //Center section
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
        return new Player("Etidur :)", "blue", "Player");
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
        Player player = new Player("Omg", "blue", "Player");
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
        Player player = new Player("Omg", "blue", "Player");
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
        Player player = new Player("Omg", "blue", "Player");
        AbstractAggregate instance = new AbstractAggregateImpl(player, player.getFirstMeepleAvailable());
        instance.enlargeAggregate(1, 0, AbstractAggregateTest.initiateAbstractTile(), AbstractAggregateTest.initiateTypes());

        Set<Coord> expResult = new HashSet<>();

        Set<Coord> result = instance.getNeighboredCoordinates(2, 2);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetTileLocations()
    {

        Player player = new Player("Omg", "blue", "Player");
        AbstractAggregate instance = new AbstractAggregateImpl(player, player.getFirstMeepleAvailable());
        Set<String> expResult = null;

        Set<String> result = instance.getAggregatedTypesByCoord(1, 1);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetTileLocationsBis()
    {
        Player player = new Player("Omg", "blue", "Player");
        AbstractAggregate instance = new AbstractAggregateImpl(player, player.getFirstMeepleAvailable());
        //instance.enlargeAggregate(1, 0, AbstractAggregateTest.initiateAbstractTile(), AbstractAggregateTest.initiateTypes());

        Set<String> expResult = new HashSet<>();
        expResult.add("S");
        expResult.add("E");
        expResult.add("CNE");
        Set<String> result = instance.getAggregatedTypesByCoord(0, 1);
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
     * Test of merge method, if the aggregates are from different player
     */
    @Test
    public void testMerge2()
    {
        AbstractAggregate instance = new AbstractAggregateImpl();
        Player player = new Player("Omg", "blue", "Player");
        Set<Meeple> ms = new HashSet<>();
        Meeple m = player.getBigMeepleAvailable();
        ms.add(m);
        AbstractAggregate neighborAggregate = new AbstractAggregateImpl(player, m);

        Map<Player, Set<Meeple>> result = new HashMap<>();
        result.put(player, ms);

        Player player2 = new Player("Omg", "blue", "Player");
        ms = new HashSet<>();
        m = player2.getBigMeepleAvailable();
        instance.addMeeple(player2, m);
        ms.add(m);
        result.put(player2, ms);

        instance.merge(neighborAggregate);

        assertEquals(result, instance.getPlayers());
    }

    /**
     * Test of merge method, if the aggregates are from different player
     */
    @Test
    public void testMerge2bis()
    {
        AbstractAggregate instance = new AbstractAggregateImpl();
        Player player = new Player("Mo", "blue", "Player");
        Set<Meeple> ms = new HashSet<>();
        Meeple m = player.getBigMeepleAvailable();
        ms.add(m);
        AbstractAggregate neighborAggregate = new AbstractAggregateImpl(player, m);

        m = player.getFirstMeepleAvailable();
        instance.addMeeple(player, m);
        ms.add(m);
        instance.merge(neighborAggregate);

        Map<Player, Set<Meeple>> result = new HashMap<>();
        result.put(player, ms);
        System.out.println(instance.getPlayers());
        assertEquals(result, instance.getPlayers());
    }

    /**
     * Test of getCommonPlayers, if there is one common player in these
     * collections
     */
    @Test
    public void testGetCommonPlayers()
    {
        Set<Player> playersSet1 = new HashSet<>();
        playersSet1.add(new Player("C'est moi ;)", "blue", "Player"));

        Set<Player> playersSet2 = new HashSet<>();
        playersSet2.add(new Player("C'est moi ;)", "blue", "Player"));
        playersSet2.add(new Player("C'est pas moi :(", "blue", "Player"));

        //Result expected
        Set<Player> expResult = new HashSet<>();
        expResult.add(new Player("C'est moi ;)", "blue", "Player"));

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
        playersSet2.add(new Player("C'est moi ;)", "blue", "Player"));
        playersSet2.add(new Player("C'est pas moi :(", "blue", "Player"));

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
        playersSet1.add(new Player("C'est moi ;)", "blue", "Player"));
        playersSet1.add(new Player("C'est pas moi :(", "blue", "Player"));
        playersSet1.add(new Player("Bonjouue", "blue", "Player"));

        Set<Player> playersSet2 = new HashSet<>();
        playersSet2.add(new Player("C'est pas moi non plus !", "blue", "Player"));

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
        //System.out.println("Tester 'checkIsCompleted' pour chaque classe qui étend AbstractAggregate");
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

        @Override
        public int countPoints()
        {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

        assertEquals(expResult, AbstractAggregate.mergeLocationTypesSet(map1, map2));
    }
}
