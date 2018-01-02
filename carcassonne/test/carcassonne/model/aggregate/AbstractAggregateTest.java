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
        return new Player("Etidur :)", "blue");
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
        Player player = new Player("Omg", "blue");
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
        Player player = new Player("Omg", "blue");
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
        Player player = new Player("Omg", "blue");
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
     * Test of merge method, if the aggregates are from the same player(s)
     */
    @Test
    public void testMerge()
    {
        AbstractAggregate instance = new AbstractAggregateImpl();
        Player player = new Player("Mo", "blue");
        AbstractAggregate neighborAggregate = new AbstractAggregateImpl(player, player.getBigMeepleAvailable());

        Player player2 = new Player("Mo", "blue");
        instance.addMeeple(player2, player2.getFirstMeepleAvailable());

        instance.merge(neighborAggregate);

        //assertEquals(3, instance.meepleNumber);
    }

    /**
     * Test of merge method, if the aggregates are from different player
     */
    @Test
    public void testMerge2()
    {
        AbstractAggregate instance = new AbstractAggregateImpl();
        Player player = new Player("Mo", "blue");
        AbstractAggregate neighborAggregate = new AbstractAggregateImpl(player, player.getBigMeepleAvailable());

        Player player2 = new Player("test", "blue");
        instance.addMeeple(player2, player2.getBigMeepleAvailable());

        instance.merge(neighborAggregate);

        //assertEquals(2, instance.meepleNumber);
    }

    /**
     * Test of merge method, if the aggregates are from different player and the
     * second player wins
     */
    @Test
    public void testMerge2Bis()
    {
        AbstractAggregate instance = new AbstractAggregateImpl();
        Player player = new Player("Mo", "blue");
        AbstractAggregate neighborAggregate = new AbstractAggregateImpl(player, player.getFirstMeepleAvailable());

        Player player2 = new Player("test", "blue");
        instance.addMeeple(player2, player2.getBigMeepleAvailable());

        instance.merge(neighborAggregate);

        assertEquals(instance.getBiggestPoints(), 2);
    }

    /**
     * Test of merge method, if the aggregates are from different player and
     * have same meeple number
     */
    @Test
    public void testMerge3()
    {
        AbstractAggregate instance = new AbstractAggregateImpl();
        Player player = new Player("Mo", "blue");
        AbstractAggregate neighborAggregate = new AbstractAggregateImpl(player, player.getFirstMeepleAvailable());

        Player player2 = new Player("test", "blue");
        instance.addMeeple(player2, player2.getFirstMeepleAvailable());

        instance.merge(neighborAggregate);
        Map<Player, Set<Meeple>> expResult = new HashMap<>();
        Set<Meeple> meeps = new HashSet<>();
        meeps.add(player.getMeeple().get(0));
        expResult.put(player, meeps);
        meeps = new HashSet<>();
        meeps.add(player2.getMeeple().get(0));
        expResult.put(player2, meeps);

        System.out.println(instance.getBiggestPoints());

        System.out.println(instance.players);
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
        playersSet1.add(new Player("C'est moi ;)", "blue"));

        Set<Player> playersSet2 = new HashSet<>();
        playersSet2.add(new Player("C'est moi ;)", "blue"));
        playersSet2.add(new Player("C'est pas moi :(", "blue"));

        //Result expected
        Set<Player> expResult = new HashSet<>();
        expResult.add(new Player("C'est moi ;)", "blue"));

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
        playersSet2.add(new Player("C'est moi ;)", "blue"));
        playersSet2.add(new Player("C'est pas moi :(", "blue"));

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
        playersSet1.add(new Player("C'est moi ;)", "blue"));
        playersSet1.add(new Player("C'est pas moi :(", "blue"));
        playersSet1.add(new Player("Bonjouue", "blue"));

        Set<Player> playersSet2 = new HashSet<>();
        playersSet2.add(new Player("C'est pas moi non plus !", "blue"));

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

    /**
     * Test of mergeMeeplesSet method, of class AbstractAggregate.
     */
    @Test
    public void testMergeMeeplesSet()
    {
        Player p1 = new Player("No", "blue");
        Player p2 = new Player("Bo", "blue");
        Map<Player, Set<Meeple>> m1 = new HashMap<>();
        Map<Player, Set<Meeple>> m2 = new HashMap<>();
        Map<Player, Set<Meeple>> expResult = new HashMap<>();

        Set<Meeple> meeps = new HashSet<>();
        meeps.add(p1.getBigMeeple());
        m1.put(p1, meeps);

        meeps = new HashSet<>();
        meeps.add(p1.getMeeple().get(0));

        Set<Meeple> meeps2 = new HashSet<>();
        meeps2.add(p2.getMeeple().get(0));
        m2.put(p2, meeps2);

        expResult = m1;
        expResult.put(p1, meeps);
        expResult.put(p2, meeps2);

        assertEquals(expResult, AbstractAggregate.mergeMeeplesSet(m1, m2));
    }

    /**
     * Test of getBiggestMeepleNumber method, of class AbstractAggregate.
     */
    @Test
    public void testGetBiggestPoints()
    {
        System.out.println("getBiggestMeepleNumber");
        Player p = new Player("Bonjour", "blue");
        AbstractAggregateImpl instance = new AbstractAggregateImpl(p, p.getFirstMeepleAvailable());

        Player p2 = new Player("Aurevoir", "blue");
        AbstractAggregateImpl instance2 = new AbstractAggregateImpl(p2, p2.getBigMeepleAvailable());
        instance.merge(instance2);

        int expResult = 2;
        int result = instance.getBiggestPoints();
        assertEquals(expResult, result);
    }

    /**
     * Test of getWinningPlayers method, of class AbstractAggregate.
     */
    @Test
    public void testGetWinningPlayers()
    {
        System.out.println("getBiggestMeepleNumber");
        Player p = new Player("Bonjour", "blue");
        AbstractAggregateImpl instance = new AbstractAggregateImpl(p, p.getFirstMeepleAvailable());

        Player p2 = new Player("Aurevoir", "blue");
        AbstractAggregateImpl instance2 = new AbstractAggregateImpl(p2, p2.getBigMeepleAvailable());
        instance.merge(instance2);

        Set<Player> expResult = new HashSet<>();
        expResult.add(p2);
        Set<Player> result = instance.getWinningPlayers();
        assertEquals(expResult, result);
    }

    /**
     * Test of getWinningPlayers method, when several players won
     */
    @Test
    public void testGetWinningPlayersBis()
    {
        System.out.println("getBiggestMeepleNumber");
        Player p = new Player("Bonjour", "blue");
        AbstractAggregateImpl instance = new AbstractAggregateImpl(p, p.getFirstMeepleAvailable());

        Player p2 = new Player("Aurevoir", "blue");
        AbstractAggregateImpl instance2 = new AbstractAggregateImpl(p2, p2.getBigMeepleAvailable());
        instance.merge(instance2);

        Player p3 = new Player("Heho", "blue");
        Meeple meeple = p3.getFirstMeepleAvailable();
        AbstractAggregateImpl instance3 = new AbstractAggregateImpl(p3, meeple);
        meeple.setIsUsed(true);

        meeple = p3.getFirstMeepleAvailable();
        AbstractAggregateImpl instance4 = new AbstractAggregateImpl();
        instance4.addMeeple(p3, meeple);
        meeple.setIsUsed(true);

        instance3.merge(instance4);
        System.out.println(instance4.getBiggestPoints());
        System.out.println(instance3.getBiggestPoints());

        instance.merge(instance3);

        Set<Player> expResult = new HashSet<>();
        expResult.add(p3);
        expResult.add(p2);

        Set<Player> result = instance.getWinningPlayers();
        assertEquals(expResult, result);
    }

}
