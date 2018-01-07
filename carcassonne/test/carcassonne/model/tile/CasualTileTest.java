/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.tile;

import carcassonne.model.type.AbstractType;
import carcassonne.model.type.CityType;
import carcassonne.model.type.FieldType;
import carcassonne.model.type.RiverType;
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
 * @author nathanael
 */
public class CasualTileTest
{

    public CasualTileTest()
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
     * Test of getId method, of class CasualTile.
     */
    @Test
    public void testGetId()
    {
        System.out.println("getId");
        CasualTile instance = new CasualTile("A",
                new CityType(), new RoadType(), new RiverType(), new RoadType(), new RiverType(), new CityType(), new RoadType(), // Northen line
                new CityType(), // East
                new RoadType(), new FieldType(), new RiverType(), new FieldType(), new CityType(), new RiverType(), new FieldType(), // Southern line
                new RiverType(), // West
                new RoadType(), new CityType(), new FieldType(), new RiverType() // Center
        );
        String expResult = "A";
        String result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNWW method, of class CasualTile.
     */
    @Test
    public void testGetNWW()
    {
        AbstractType cityTest = new CityType();
        CasualTile instance = new CasualTile("A",
                cityTest, new RoadType(), new RiverType(), new RoadType(), new RiverType(), new CityType(), new RoadType(), // Northen line
                new CityType(), // East
                new RoadType(), new FieldType(), new RiverType(), new FieldType(), new CityType(), new RiverType(), new FieldType(), // Southern line
                new RiverType(), // West
                new RoadType(), new CityType(), new FieldType(), new RiverType() // Center
        );
        AbstractType expResult = cityTest;
        AbstractType result = instance.getNWW();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNW method, of class CasualTile.
     */
    @Test
    public void testGetNW()
    {
        AbstractType roadTest = new RoadType();
        CasualTile instance = new CasualTile("A",
                new CityType(), roadTest, new RiverType(), new RoadType(), new RiverType(), new CityType(), new RoadType(), // Northen line
                new CityType(), // East
                new RoadType(), new FieldType(), new RiverType(), new FieldType(), new CityType(), new RiverType(), new FieldType(), // Southern line
                new RiverType(), // West
                new RoadType(), new CityType(), new FieldType(), new RiverType() // Center
        );
        AbstractType expResult = roadTest;
        AbstractType result = instance.getNW();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNNW method, of class CasualTile.
     */
    @Test
    public void testGetNNW()
    {
        AbstractType riverTest = new RiverType();
        CasualTile instance = new CasualTile("A",
                new CityType(), new RoadType(), riverTest, new RoadType(), new RiverType(), new CityType(), new RoadType(), // Northen line
                new CityType(), // East
                new RoadType(), new FieldType(), new RiverType(), new FieldType(), new CityType(), new RiverType(), new FieldType(), // Southern line
                new RiverType(), // West
                new RoadType(), new CityType(), new FieldType(), new RiverType() // Center
        );
        AbstractType expResult = riverTest;
        AbstractType result = instance.getNNW();
        assertEquals(expResult, result);
    }

    /**
     * Test of getN method, of class CasualTile.
     */
    @Test
    public void testGetN()
    {
        AbstractType roadTest = new RoadType();
        CasualTile instance = new CasualTile("A",
                new CityType(), new RoadType(), new RiverType(), roadTest, new RiverType(), new CityType(), new RoadType(), // Northen line
                new CityType(), // East
                new RoadType(), new FieldType(), new RiverType(), new FieldType(), new CityType(), new RiverType(), new FieldType(), // Southern line
                new RiverType(), // West
                new RoadType(), new CityType(), new FieldType(), new RiverType() // Center
        );
        AbstractType expResult = roadTest;
        AbstractType result = instance.getN();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNNE method, of class CasualTile.
     */
    @Test
    public void testGetNNE()
    {
        AbstractType riverTest = new RiverType();
        CasualTile instance = new CasualTile("A",
                new CityType(), new RoadType(), new RiverType(), new RoadType(), riverTest, new CityType(), new RoadType(), // Northen line
                new CityType(), // East
                new RoadType(), new FieldType(), new RiverType(), new FieldType(), new CityType(), new RiverType(), new FieldType(), // Southern line
                new RiverType(), // West
                new RoadType(), new CityType(), new FieldType(), new RiverType() // Center
        );
        AbstractType expResult = riverTest;
        AbstractType result = instance.getNNE();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNE method, of class CasualTile.
     */
    @Test
    public void testGetNE()
    {
        AbstractType cityTest = new CityType();
        CasualTile instance = new CasualTile("A",
                new CityType(), new RoadType(), new RiverType(), new RoadType(), new RiverType(), cityTest, new RoadType(), // Northen line
                new CityType(), // East
                new RoadType(), new FieldType(), new RiverType(), new FieldType(), new CityType(), new RiverType(), new FieldType(), // Southern line
                new RiverType(), // West
                new RoadType(), new CityType(), new FieldType(), new RiverType() // Center
        );
        AbstractType expResult = cityTest;
        AbstractType result = instance.getNE();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNEE method, of class CasualTile.
     */
    @Test
    public void testGetNEE()
    {
        AbstractType roadTest = new RoadType();
        CasualTile instance = new CasualTile("A",
                new CityType(), new RoadType(), new RiverType(), new RoadType(), new RiverType(), new CityType(), roadTest, // Northen line
                new CityType(), // East
                new RoadType(), new FieldType(), new RiverType(), new FieldType(), new CityType(), new RiverType(), new FieldType(), // Southern line
                new RiverType(), // West
                new RoadType(), new CityType(), new FieldType(), new RiverType() // Center
        );
        AbstractType expResult = roadTest;
        AbstractType result = instance.getNEE();
        assertEquals(expResult, result);
    }

    /**
     * Test of getE method, of class CasualTile.
     */
    @Test
    public void testGetE()
    {
        AbstractType cityTest = new CityType();
        CasualTile instance = new CasualTile("A",
                new CityType(), new RoadType(), new RiverType(), new RoadType(), new RiverType(), new CityType(), new RoadType(), // Northen line
                cityTest, // East
                new RoadType(), new FieldType(), new RiverType(), new FieldType(), new CityType(), new RiverType(), new FieldType(), // Southern line
                new RiverType(), // West
                new RoadType(), new CityType(), new FieldType(), new RiverType() // Center
        );
        AbstractType expResult = cityTest;
        AbstractType result = instance.getE();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSEE method, of class CasualTile.
     */
    @Test
    public void testGetSEE()
    {
        AbstractType roadTest = new RoadType();
        CasualTile instance = new CasualTile("A",
                new CityType(), new RoadType(), new RiverType(), new RoadType(), new RiverType(), new CityType(), new RoadType(), // Northen line
                new CityType(), // East
                roadTest, new FieldType(), new RiverType(), new FieldType(), new CityType(), new RiverType(), new FieldType(), // Southern line
                new RiverType(), // West
                new RoadType(), new CityType(), new FieldType(), new RiverType() // Center
        );
        AbstractType expResult = roadTest;
        AbstractType result = instance.getSEE();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSE method, of class CasualTile.
     */
    @Test
    public void testGetSE()
    {
        AbstractType fieldTest = new FieldType();
        CasualTile instance = new CasualTile("A",
                new CityType(), new RoadType(), new RiverType(), new RoadType(), new RiverType(), new CityType(), new RoadType(), // Northen line
                new CityType(), // East
                new RoadType(), fieldTest, new RiverType(), new FieldType(), new CityType(), new RiverType(), new FieldType(), // Southern line
                new RiverType(), // West
                new RoadType(), new CityType(), new FieldType(), new RiverType() // Center
        );
        AbstractType expResult = fieldTest;
        AbstractType result = instance.getSE();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSSE method, of class CasualTile.
     */
    @Test
    public void testGetSSE()
    {
        AbstractType riverTest = new RiverType();
        CasualTile instance = new CasualTile("A",
                new CityType(), new RoadType(), new RiverType(), new RoadType(), new RiverType(), new CityType(), new RoadType(), // Northen line
                new CityType(), // East
                new RoadType(), new FieldType(), riverTest, new FieldType(), new CityType(), new RiverType(), new FieldType(), // Southern line
                new RiverType(), // West
                new RoadType(), new CityType(), new FieldType(), new RiverType() // Center
        );
        AbstractType expResult = riverTest;
        AbstractType result = instance.getSSE();
        assertEquals(expResult, result);
    }

    /**
     * Test of getS method, of class CasualTile.
     */
    @Test
    public void testGetS()
    {
        AbstractType fieldTest = new FieldType();
        CasualTile instance = new CasualTile("A",
                new CityType(), new RoadType(), new RiverType(), new RoadType(), new RiverType(), new CityType(), new RoadType(), // Northen line
                new CityType(), // East
                new RoadType(), new FieldType(), new RiverType(), fieldTest, new CityType(), new RiverType(), new FieldType(), // Southern line
                new RiverType(), // West
                new RoadType(), new CityType(), new FieldType(), new RiverType() // Center
        );
        AbstractType expResult = fieldTest;
        AbstractType result = instance.getS();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSSW method, of class CasualTile.
     */
    @Test
    public void testGetSSW()
    {
        AbstractType cityTest = new CityType();
        CasualTile instance = new CasualTile("A",
                new CityType(), new RoadType(), new RiverType(), new RoadType(), new RiverType(), new CityType(), new RoadType(), // Northen line
                new CityType(), // East
                new RoadType(), new FieldType(), new RiverType(), new FieldType(), cityTest, new RiverType(), new FieldType(), // Southern line
                new RiverType(), // West
                new RoadType(), new CityType(), new FieldType(), new RiverType() // Center
        );
        AbstractType expResult = cityTest;
        AbstractType result = instance.getSSW();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSW method, of class CasualTile.
     */
    @Test
    public void testGetSW()
    {
        AbstractType riverTest = new RiverType();
        CasualTile instance = new CasualTile("A",
                new CityType(), new RoadType(), new RiverType(), new RoadType(), new RiverType(), new CityType(), new RoadType(), // Northen line
                new CityType(), // East
                new RoadType(), new FieldType(), new RiverType(), new FieldType(), new CityType(), riverTest, new FieldType(), // Southern line
                new RiverType(), // West
                new RoadType(), new CityType(), new FieldType(), new RiverType() // Center
        );
        AbstractType expResult = riverTest;
        AbstractType result = instance.getSW();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSWW method, of class CasualTile.
     */
    @Test
    public void testGetSWW()
    {
        AbstractType fieldTest = new FieldType();
        CasualTile instance = new CasualTile("A",
                new CityType(), new RoadType(), new RiverType(), new RoadType(), new RiverType(), new CityType(), new RoadType(), // Northen line
                new CityType(), // East
                new RoadType(), new FieldType(), new RiverType(), new FieldType(), new CityType(), new RiverType(), fieldTest, // Southern line
                new RiverType(), // West
                new RoadType(), new CityType(), new FieldType(), new RiverType() // Center
        );
        AbstractType expResult = fieldTest;
        AbstractType result = instance.getSWW();
        assertEquals(expResult, result);
    }

    /**
     * Test of getW method, of class CasualTile.
     */
    @Test
    public void testGetW()
    {
        AbstractType riverTest = new RiverType();
        CasualTile instance = new CasualTile("A",
                new CityType(), new RoadType(), new RiverType(), new RoadType(), new RiverType(), new CityType(), new RoadType(), // Northen line
                new CityType(), // East
                new RoadType(), new FieldType(), new RiverType(), new FieldType(), new CityType(), new RiverType(), new FieldType(), // Southern line
                riverTest, // West
                new RoadType(), new CityType(), new FieldType(), new RiverType() // Center
        );
        AbstractType expResult = riverTest;
        AbstractType result = instance.getW();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCNW method, of class CasualTile.
     */
    @Test
    public void testGetCNW()
    {
        AbstractType roadTest = new RoadType();
        CasualTile instance = new CasualTile("A",
                new CityType(), new RoadType(), new RiverType(), new RoadType(), new RiverType(), new CityType(), new RoadType(), // Northen line
                new CityType(), // East
                new RoadType(), new FieldType(), new RiverType(), new FieldType(), new CityType(), new RiverType(), new FieldType(), // Southern line
                new RiverType(), // West
                roadTest, new CityType(), new FieldType(), new RiverType() // Center
        );
        AbstractType expResult = roadTest;
        AbstractType result = instance.getCNW();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCNE method, of class CasualTile.
     */
    @Test
    public void testGetCNE()
    {
        AbstractType cityTest = new CityType();
        CasualTile instance = new CasualTile("A",
                new CityType(), new RoadType(), new RiverType(), new RoadType(), new RiverType(), new CityType(), new RoadType(), // Northen line
                new CityType(), // East
                new RoadType(), new FieldType(), new RiverType(), new FieldType(), new CityType(), new RiverType(), new FieldType(), // Southern line
                new RiverType(), // West
                new RoadType(), cityTest, new FieldType(), new RiverType() // Center
        );
        AbstractType expResult = cityTest;
        AbstractType result = instance.getCNE();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCSE method, of class CasualTile.
     */
    @Test
    public void testGetCSE()
    {
        AbstractType fieldTest = new FieldType();
        CasualTile instance = new CasualTile("A",
                new CityType(), new RoadType(), new RiverType(), new RoadType(), new RiverType(), new CityType(), new RoadType(), // Northen line
                new CityType(), // East
                new RoadType(), new FieldType(), new RiverType(), new FieldType(), new CityType(), new RiverType(), new FieldType(), // Southern line
                new RiverType(), // West
                new RoadType(), new CityType(), fieldTest, new RiverType() // Center
        );
        AbstractType expResult = fieldTest;
        AbstractType result = instance.getCSE();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCSW method, of class CasualTile.
     */
    @Test
    public void testGetCSW()
    {
        AbstractType riverTest = new RiverType();
        CasualTile instance = new CasualTile("A",
                new CityType(), new RoadType(), new RiverType(), new RoadType(), new RiverType(), new CityType(), new RoadType(), // Northen line
                new CityType(), // East
                new RoadType(), new FieldType(), new RiverType(), new FieldType(), new CityType(), new RiverType(), new FieldType(), // Southern line
                new RiverType(), // West
                new RoadType(), new CityType(), new FieldType(), riverTest // Center
        );
        AbstractType expResult = riverTest;
        AbstractType result = instance.getCSW();
        assertEquals(expResult, result);
    }

    /**
     * Test of rotateLeft method, of class CasualTile.
     */
    @Test
    public void testRotateRightAgg()
    {
        System.out.println("testRotateRightAgg");
        Set<Set<String>> aggregates;
        aggregates = new HashSet<>();
        aggregates.add(retTreeSet("NEE", "E", "SEE"));
        aggregates.add(retTreeSet("S", "CNW", "CSW", "N"));
        aggregates.add(retTreeSet("NNW", "NW", "NWW", "W", "SWW", "SW", "SSW"));
        aggregates.add(retTreeSet("NNE", "NE", "CNE", "CSE", "SE", "SSE"));
        CasualTile ctD = new CasualTile("D", "D0", //Id
                new FieldType(), new FieldType(), new FieldType(), //North West section
                new RoadType(), //North
                new FieldType(), new FieldType(), new CityType(), //North East section
                new CityType(), //East
                new CityType(), new FieldType(), new FieldType(), //South East section
                new RoadType(), //South
                new FieldType(), new FieldType(), new FieldType(), //South West section
                new FieldType(), //West
                new RoadType(), new FieldType(), new FieldType(), new RoadType(), //Center section
                aggregates
        );
        ctD.rotateRight();
        Set<Set<String>> expResult;
        expResult = new HashSet<>();

        expResult.add(retTreeSet("SSE", "S", "SSW"));
        expResult.add(retTreeSet("W", "CNE", "CNW", "E"));
        expResult.add(retTreeSet("NEE", "NE", "NNE", "N", "NNW", "NW", "NWW"));
        expResult.add(retTreeSet("SEE", "SE", "CSE", "CSW", "SW", "SWW"));

        assertEquals(expResult, ctD.getAggregateEmplacements());
    }

    /**
     * Test of locationsAreBounded method, of class CasualTile.
     */
    @Test
    public void testLocationsAreBounded()
    {
        Set<String> cityLocations = new HashSet<>();
        cityLocations.add("NNW");

        Set<String> locationTypes = new HashSet<>();
        locationTypes.add("NW");

        boolean expResult = true;
        boolean result = CasualTile.locationsAreBounded(cityLocations, locationTypes);
        System.out.println(result);
        assertEquals(expResult, result);
    }

    /**
     * Test of locationsAreBounded method, of class CasualTile.
     */
    @Test
    public void testLocationsAreBounded2()
    {
        Set<String> cityLocations = new HashSet<>();
        cityLocations.add("NNW");
        cityLocations.add("SSW");

        Set<String> locationTypes = new HashSet<>();
        locationTypes.add("E");

        boolean expResult = false;
        boolean result = CasualTile.locationsAreBounded(cityLocations, locationTypes);
        System.out.println(result);
        assertEquals(expResult, result);
    }

    private Set<String> retTreeSet(String... poss)
    {
        Set<String> tsPos = new HashSet<String>();
        for (String pos : poss) {
            tsPos.add(pos);
        }
        return tsPos;
    }

    @Test
    public void testGetCityAggregateEmplacements()
    {
        Set<Set<String>> aggregates = new HashSet<>();
        aggregates.add(retTreeSet("NEE", "E", "SEE"));
        aggregates.add(retTreeSet("S", "CNW", "CSW", "N"));
        aggregates.add(retTreeSet("NNW", "NW", "NWW", "W", "SWW", "SW", "SSW"));
        aggregates.add(retTreeSet("NNE", "NE", "CNE", "CSE", "SE", "SSE"));

        CasualTile tile = new CasualTile("D", "D", //Id
                new FieldType(), new FieldType(), new FieldType(), //North West section
                new RoadType(), //North
                new FieldType(), new FieldType(), new CityType(), //North East section
                new CityType(), //East
                new CityType(), new FieldType(), new FieldType(), //South East section
                new RoadType(), //South
                new FieldType(), new FieldType(), new FieldType(), //South West section
                new FieldType(), //West
                new RoadType(), new FieldType(), new FieldType(), new RoadType(), //Center section
                aggregates
        );

        Set<Set<String>> cityAggregate = new HashSet<>();
        cityAggregate.add(retTreeSet("NEE", "E", "SEE"));

        Set<Set<String>> result = tile.getCityAggregateEmplacements();
        assertEquals(cityAggregate, result);
    }

    @Test
    public void testGetRoadAggregateEmplacements()
    {
        Set<Set<String>> aggregates = new HashSet<>();
        aggregates.add(retTreeSet("NEE", "E", "SEE"));
        aggregates.add(retTreeSet("S", "CNW", "CSW", "N"));
        aggregates.add(retTreeSet("NNW", "NW", "NWW", "W", "SWW", "SW", "SSW"));
        aggregates.add(retTreeSet("NNE", "NE", "CNE", "CSE", "SE", "SSE"));

        CasualTile tile = new CasualTile("D", "D", //Id
                new FieldType(), new FieldType(), new FieldType(), //North West section
                new RoadType(), //North
                new FieldType(), new FieldType(), new CityType(), //North East section
                new CityType(), //East
                new CityType(), new FieldType(), new FieldType(), //South East section
                new RoadType(), //South
                new FieldType(), new FieldType(), new FieldType(), //South West section
                new FieldType(), //West
                new RoadType(), new FieldType(), new FieldType(), new RoadType(), //Center section
                aggregates
        );

        Set<Set<String>> roadAggregate = new HashSet<>();
        roadAggregate.add(retTreeSet("S", "CNW", "CSW", "N"));

        Set<Set<String>> result = tile.getRoadAggregateEmplacements();
        assertEquals(roadAggregate, result);
    }

    @Test
    public void testGetFieldAggregateEmplacements()
    {
        Set<Set<String>> aggregates = new HashSet<>();
        aggregates.add(retTreeSet("NEE", "E", "SEE"));
        aggregates.add(retTreeSet("S", "CNW", "CSW", "N"));
        aggregates.add(retTreeSet("NNW", "NW", "NWW", "W", "SWW", "SW", "SSW"));
        aggregates.add(retTreeSet("NNE", "NE", "CNE", "CSE", "SE", "SSE"));

        CasualTile tile = new CasualTile("D", "D", //Id
                new FieldType(), new FieldType(), new FieldType(), //North West section
                new RoadType(), //North
                new FieldType(), new FieldType(), new CityType(), //North East section
                new CityType(), //East
                new CityType(), new FieldType(), new FieldType(), //South East section
                new RoadType(), //South
                new FieldType(), new FieldType(), new FieldType(), //South West section
                new FieldType(), //West
                new RoadType(), new FieldType(), new FieldType(), new RoadType(), //Center section
                aggregates
        );

        Set<Set<String>> fieldAggregate = new HashSet<>();
        fieldAggregate.add(retTreeSet("NNW", "NW", "NWW", "W", "SWW", "SW", "SSW"));
        fieldAggregate.add(retTreeSet("NNE", "NE", "CNE", "CSE", "SE", "SSE"));

        Set<Set<String>> result = tile.getFieldAggregateEmplacements();
        assertEquals(fieldAggregate, result);
    }

}
