/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.aggregate;

import carcassonne.model.carcassonnegame.CarcassonneGame;
import carcassonne.model.tile.AbstractTile;
import carcassonne.model.tile.CasualTile;
import carcassonne.model.type.AbbayType;
import carcassonne.model.type.FieldType;
import carcassonne.model.type.RoadType;
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
 * @author Étienne
 */
public class RoadAggregateTest
{

    private static AbstractTile getExtremityTile()
    {
        return new CasualTile("A", //Id
                new FieldType(), new FieldType(), new FieldType(), //North section
                new FieldType(), //East section
                new FieldType(), new RoadType(), new FieldType(), //South section
                new FieldType(), //West section
                new AbbayType(), new AbbayType(), new AbbayType(), new AbbayType() //Center section
        );
    }

    private static AbstractTile getStraightRoadTile()
    {
        return new CasualTile("U", //Id
                new FieldType(), new RoadType(), new FieldType(), //North section
                new FieldType(), //East section
                new FieldType(), new RoadType(), new FieldType(), //South section
                new FieldType(), //West section
                new RoadType(), new RoadType(), new RoadType(), new RoadType() //Center section
        );
    }

    public RoadAggregateTest()
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
     * Test of getRoadExtremities method, using a tile extremity in the
     * constructor
     */
    @Test
    public void testGetRoadExtremities()
    {
        AbstractTile firstTile = getExtremityTile();
        Set<String> locationTypes = new HashSet<>();
        locationTypes.add("S");

        RoadAggregate instance = new RoadAggregate(0, 0, firstTile, locationTypes);

        int expResult = 1;
        int result = instance.getRoadExtremities();
        assertEquals(expResult, result);
    }

    /**
     * Test of getRoadExtremities method, using a tile extremity in the second
     * tile
     */
    @Test
    public void testGetRoadExtremitiesByEnlargement()
    {
        AbstractTile firstTile = getStraightRoadTile();
        Set<String> locationTypes = new HashSet<>();
        locationTypes.add("S");
        locationTypes.add("CSW");
        locationTypes.add("CSE");
        locationTypes.add("CNE");
        locationTypes.add("CNW");
        locationTypes.add("N");

        RoadAggregate instance = new RoadAggregate(0, 0, firstTile, locationTypes);
        locationTypes = new HashSet<>();
        locationTypes.add("S");
        AbstractTile secondTile = getExtremityTile();
        instance.enlargeAggregate(0, 1, secondTile, locationTypes);

        int expResult = 1;
        int result = instance.getRoadExtremities();
        assertEquals(expResult, result);
    }

    /**
     * Test of getRoadExtremities method, using a tile extremity in the first
     * and second tile (Those tiles are not correctly placed)
     */
    @Test
    public void testGetRoadExtremitiesByEnlargementBis()
    {
        AbstractTile firstTile = getExtremityTile();
        Set<String> locationTypes = new HashSet<>();
        locationTypes.add("S");

        RoadAggregate instance = new RoadAggregate(0, 0, firstTile, locationTypes);
        locationTypes = new HashSet<>();
        locationTypes.add("N");
        AbstractTile secondTile = getExtremityTile();
        instance.enlargeAggregate(0, 1, secondTile, locationTypes);

        int expResult = 2;
        int result = instance.getRoadExtremities();
        assertEquals(expResult, result);
    }

    /**
     * Test of enlargeAggregate method, if we enlarge with a none end tile
     */
    @Test
    public void testEnlargeAggregate()
    {
        AbstractTile firstTile = getExtremityTile();
        Set<String> locationTypes = new HashSet<>();
        locationTypes.add("S");

        RoadAggregate instance = new RoadAggregate(0, 0, firstTile, locationTypes);
        locationTypes = new HashSet<>();
        locationTypes.add("S");
        locationTypes.add("CSW");
        locationTypes.add("CSE");
        locationTypes.add("CNE");
        locationTypes.add("CNW");
        locationTypes.add("N");
        AbstractTile secondTile = getStraightRoadTile();
        instance.enlargeAggregate(0, -1, secondTile, locationTypes);

        int expResult = 1;
        int result = instance.getRoadExtremities();
        assertEquals(expResult, result);
    }

    /**
     * Test of merge method, (Those tiles are not correctly placed)
     */
    @Test
    public void testMerge()
    {
        Set<String> locationTypes = new HashSet<>();
        locationTypes.add("S");

        RoadAggregate instance = new RoadAggregate(0, 0, getExtremityTile(), locationTypes);
        RoadAggregate neighborAggregate = new RoadAggregate(0, 1, getExtremityTile(), locationTypes);
        instance.merge(neighborAggregate);

        int expResult = 2;
        int result = instance.getRoadExtremities();
        assertEquals(expResult, result);
    }

    /**
     * Test of merge method, (Those tiles are not correctly placed)
     */
    @Test
    public void testMergeBis()
    {
        Set<String> locationTypes = new HashSet<>();
        locationTypes.add("S");

        RoadAggregate instance = new RoadAggregate(0, 0, getExtremityTile(), locationTypes);
        RoadAggregate neighborAggregate = new RoadAggregate(0, 1, getStraightRoadTile(), locationTypes);
        instance.merge(neighborAggregate);

        int expResult = 1;
        int result = instance.getRoadExtremities();
        assertEquals(expResult, result);
    }

    /**
     * Test of completeRoad method, of class RoadAggregate.
     */
    @Test
    public void testManageLoopRoad()
    {
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkIsCompleted method, of class RoadAggregate.
     */
    @Test
    public void testCheckIsCompleted()
    {
        System.out.println("checkIsCompleted");
        RoadAggregate instance = null;
        boolean expResult = false;
        boolean result = instance.checkIsCompleted();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class RoadAggregate.
     */
    @Test
    public void testMain()
    {
        System.out.println("main");
        String[] str = null;
        RoadAggregate.main(str);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
