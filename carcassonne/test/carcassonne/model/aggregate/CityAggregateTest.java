/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.aggregate;

import carcassonne.coord.Coord;
import carcassonne.model.tile.AbstractTile;
import carcassonne.model.tile.CasualTile;
import carcassonne.model.type.CityType;
import carcassonne.model.type.FieldType;
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
public class CityAggregateTest
{

    public CityAggregateTest()
    {
    }

    public static AbstractTile getTileE()
    {
        return new CasualTile("E", //Id
                new FieldType(), new FieldType(), new CityType(), //North West section
                new CityType(), //North
                new CityType(), new FieldType(), new FieldType(), //North East section
                new FieldType(), //East
                new FieldType(), new FieldType(), new FieldType(), //South East section
                new FieldType(), //South
                new FieldType(), new FieldType(), new FieldType(), //South West section
                new FieldType(), //West
                new FieldType(), new FieldType(), new FieldType(), new FieldType() //Center section
        );
    }

    public static AbstractTile getTileG()
    {
        return new CasualTile("G", //Id
                new FieldType(), new FieldType(), new CityType(), //North West section
                new CityType(), //North
                new CityType(), new FieldType(), new FieldType(), //North East section
                new FieldType(), //East
                new FieldType(), new FieldType(), new CityType(), //South East section
                new CityType(), //South
                new CityType(), new FieldType(), new FieldType(), //South West section
                new FieldType(), //West
                new CityType(), new CityType(), new CityType(), new CityType() //Center section
        );
    }

    public static AbstractTile getTileI()
    {
        return new CasualTile("I", //Id
                new FieldType(), new FieldType(), new FieldType(), //North West section
                new FieldType(), //North
                new FieldType(), new FieldType(), new CityType(), //North East section
                new CityType(), //East
                new CityType(), new FieldType(), new CityType(), //South East section
                new CityType(), //South
                new CityType(), new FieldType(), new FieldType(), //South West section
                new FieldType(), //West
                new FieldType(), new FieldType(), new FieldType(), new FieldType() //Center section
        );
    }

    public static AbstractTile getTileN()
    {
        return new CasualTile("I", //Id
                new FieldType(), new FieldType(), new FieldType(), //North West section
                new FieldType(), //North
                new FieldType(), new FieldType(), new CityType(), //North East section
                new CityType(), //East
                new CityType(), new FieldType(), new CityType(), //South East section
                new CityType(), //South
                new CityType(), new FieldType(), new FieldType(), //South West section
                new FieldType(), //West
                new FieldType(), new FieldType(), new FieldType(), new FieldType() //Center section
        );
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
     * Test of enlargeAggregate method, of class CityAggregate.
     */
    @Test
    public void testEnlargeAggregateCompleted()
    {
        AbstractTile newTile = getTileE();
        Set<String> locationTypes = new HashSet<>();
        locationTypes.add("N");
        locationTypes.add("NNE");
        locationTypes.add("NNW");

        CityAggregate instance = new CityAggregate(0, 0, newTile, locationTypes);

        newTile = getTileI();
        locationTypes = new HashSet<>();
        locationTypes.add("S");
        locationTypes.add("SSE");
        locationTypes.add("SSW");
        instance.enlargeAggregate(0, 1, newTile, locationTypes);

        //We expect an empty map cause all the neighbors are completed
        Map<Coord, Set<CityEdgeEnum>> result = instance.getCityEdges();
        Map<Coord, Set<CityEdgeEnum>> expResult = new HashMap<>();
        assertEquals(expResult, result);
    }

    /**
     * Test of enlargeAggregate method, of class CityAggregate.
     */
    @Test
    public void testEnlargeAggregateIncompleted()
    {
        AbstractTile newTile = getTileE();
        Set<String> locationTypes = new HashSet<>();
        locationTypes.add("N");
        locationTypes.add("NNE");
        locationTypes.add("NNW");

        CityAggregate instance = new CityAggregate(0, 0, newTile, locationTypes);

        newTile = getTileG();
        locationTypes = new HashSet<>();
        locationTypes.add("S");
        locationTypes.add("SSE");
        locationTypes.add("SSW");
        locationTypes.add("N");
        locationTypes.add("NNE");
        locationTypes.add("NNW");
        locationTypes.add("CNE");
        locationTypes.add("CSE");
        locationTypes.add("CNW");
        locationTypes.add("CSW");
        instance.enlargeAggregate(0, 1, newTile, locationTypes);

        //We expect map map with one incomplete edge
        Map<Coord, Set<CityEdgeEnum>> result = instance.getCityEdges();
        Map<Coord, Set<CityEdgeEnum>> expResult = new HashMap<>();
        Set<CityEdgeEnum> incompleteEdges = new HashSet<>();
        incompleteEdges.add(CityEdgeEnum.NORTH);
        expResult.put(new Coord(0, 1), incompleteEdges);

        assertEquals(expResult, result);
    }

    /**
     * Test of enlargeAggregate method, of class CityAggregate.
     */
    @Test
    public void testEnlargeAggregateIncompletedSeveralTiles()
    {
        AbstractTile newTile = getTileG();
        Set<String> locationTypes = new HashSet<>();
        locationTypes.add("S");
        locationTypes.add("SSE");
        locationTypes.add("SSW");
        locationTypes.add("N");
        locationTypes.add("NNE");
        locationTypes.add("NNW");
        locationTypes.add("CNE");
        locationTypes.add("CSE");
        locationTypes.add("CNW");
        locationTypes.add("CSW");

        CityAggregate instance = new CityAggregate(0, 0, newTile, locationTypes);

        newTile = getTileE();
        locationTypes = new HashSet<>();
        locationTypes.add("N");
        locationTypes.add("NNE");
        locationTypes.add("NNW");
        instance.enlargeAggregate(0, -1, newTile, locationTypes);

        newTile = getTileG();
        locationTypes = new HashSet<>();
        locationTypes.add("S");
        locationTypes.add("SSE");
        locationTypes.add("SSW");
        locationTypes.add("N");
        locationTypes.add("NNE");
        locationTypes.add("NNW");
        locationTypes.add("CNE");
        locationTypes.add("CSE");
        locationTypes.add("CNW");
        locationTypes.add("CSW");
        instance.enlargeAggregate(0, 1, newTile, locationTypes);

        //We expect map map with one incomplete edge
        Map<Coord, Set<CityEdgeEnum>> result = instance.getCityEdges();
        Map<Coord, Set<CityEdgeEnum>> expResult = new HashMap<>();
        Set<CityEdgeEnum> incompleteEdges = new HashSet<>();
        incompleteEdges.add(CityEdgeEnum.NORTH);
        expResult.put(new Coord(0, 1), incompleteEdges);

        assertEquals(expResult, result);
    }

    /**
     * Test of enlargeAggregate method, of class CityAggregate.
     */
    @Test
    public void testEnlargeAggregateIncompletedBis()
    {
        AbstractTile newTile = getTileG();
        Set<String> locationTypes = new HashSet<>();
        locationTypes.add("S");
        locationTypes.add("SSE");
        locationTypes.add("SSW");
        locationTypes.add("N");
        locationTypes.add("NNE");
        locationTypes.add("NNW");
        locationTypes.add("CNE");
        locationTypes.add("CSE");
        locationTypes.add("CNW");
        locationTypes.add("CSW");

        CityAggregate instance = new CityAggregate(0, 0, newTile, locationTypes);

        newTile = getTileG();
        locationTypes = new HashSet<>();
        locationTypes.add("S");
        locationTypes.add("SSE");
        locationTypes.add("SSW");
        locationTypes.add("N");
        locationTypes.add("NNE");
        locationTypes.add("NNW");
        locationTypes.add("CNE");
        locationTypes.add("CSE");
        locationTypes.add("CNW");
        locationTypes.add("CSW");
        instance.enlargeAggregate(0, 1, newTile, locationTypes);

        //We expect map map with two incomplete edge
        Map<Coord, Set<CityEdgeEnum>> result = instance.getCityEdges();
        Map<Coord, Set<CityEdgeEnum>> expResult = new HashMap<>();
        Set<CityEdgeEnum> incompleteEdges = new HashSet<>();
        incompleteEdges.add(CityEdgeEnum.NORTH);
        expResult.put(new Coord(0, 1), incompleteEdges);
        incompleteEdges = new HashSet<>();
        incompleteEdges.add(CityEdgeEnum.SOUTH);
        expResult.put(new Coord(0, 0), incompleteEdges);

        assertEquals(expResult, result);
    }

    /**
     * Test of enlargeAggregate method, of class CityAggregate.
     */
    @Test
    public void testMergeLoop()
    {
        AbstractTile newTile = getTileI();
        Set<String> locationTypes = new HashSet<>();
        locationTypes.add("NEE");
        locationTypes.add("E");
        locationTypes.add("SEE");
        CityAggregate instance = new CityAggregate(0, 0, newTile, locationTypes);
        
        locationTypes = new HashSet<>();
        locationTypes.add("SSE");
        locationTypes.add("S");
        locationTypes.add("SSW");
        CityAggregate instanceBis = new CityAggregate(0, 0, newTile, locationTypes);

        newTile = getTileN();
        newTile.rotateLeft();
        locationTypes = new HashSet<>();
        locationTypes.add("SSE");
        locationTypes.add("S");
        locationTypes.add("SSW");
        locationTypes.add("SW");
        locationTypes.add("SWW");
        locationTypes.add("W");
        locationTypes.add("NWW");
        instance.enlargeAggregate(1, 0, newTile, locationTypes);

        newTile = getTileN();
        locationTypes = new HashSet<>();
        locationTypes.add("SWW");
        locationTypes.add("W");
        locationTypes.add("NWW");
        locationTypes.add("NW");
        locationTypes.add("NNW");
        locationTypes.add("NNE");
        locationTypes.add("N");
        instance.enlargeAggregate(1, -1, newTile, locationTypes);

        newTile = getTileN();
        newTile.rotateRight();
        locationTypes = new HashSet<>();
        locationTypes.add("NNW");
        locationTypes.add("NNE");
        locationTypes.add("N");
        locationTypes.add("NE");
        locationTypes.add("NEE");
        locationTypes.add("E");
        locationTypes.add("SEE");
        instance.enlargeAggregate(0, -1, newTile, locationTypes);
        
        instance.merge(instanceBis);
        
        //We expect map map with no incomplete edges
        Map<Coord, Set<CityEdgeEnum>> expResult = new HashMap<>();
        assertEquals(expResult, instance.getCityEdges());
    }

    /**
     * Test of checkIsCompleted method, of class CityAggregate.
     */
    @Test
    public void testCheckIsCompleted()
    {
        System.out.println("checkIsCompleted");
        CityAggregate instance = null;
        boolean expResult = false;
        boolean result = instance.checkIsCompleted();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCityEdges method, of class CityAggregate.
     */
    @Test
    public void testGetCityEdges()
    {
        System.out.println("getCityEdges");
        CityAggregate instance = null;
        Map<Coord, Set<CityEdgeEnum>> expResult = null;
        Map<Coord, Set<CityEdgeEnum>> result = instance.getCityEdges();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of merge method, of class CityAggregate.
     */
    @Test
    public void testMerge()
    {
        System.out.println("merge");
        CityAggregate neighborAggregate = null;
        CityAggregate instance = null;
        instance.merge(neighborAggregate);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mergeSet method, of class CityAggregate.
     */
    @Test
    public void testMergeSet()
    {
        Map<Coord, Set<CityEdgeEnum>> map1 = new HashMap<>();
        Set<CityEdgeEnum> enums = new HashSet<>();
        enums.add(CityEdgeEnum.WEST);
        enums.add(CityEdgeEnum.EAST);
        map1.put(new Coord(0, 0), enums);

        Map<Coord, Set<CityEdgeEnum>> map2 = new HashMap<>();
        enums = new HashSet<>();
        enums.add(CityEdgeEnum.NORTH);
        map2.put(new Coord(0, 0), enums);
        map2.put(new Coord(1, 0), enums);

        Map<Coord, Set<CityEdgeEnum>> expResult = new HashMap<>();
        Set<CityEdgeEnum> finalEnums = new HashSet<>();
        finalEnums.add(CityEdgeEnum.NORTH);
        finalEnums.add(CityEdgeEnum.WEST);
        finalEnums.add(CityEdgeEnum.EAST);
        expResult.put(new Coord(0, 0), finalEnums);
        expResult.put(new Coord(1, 0), enums);

        assertEquals(expResult, CityAggregate.mergeCityEdgesSet(map1, map2));
    }

}
