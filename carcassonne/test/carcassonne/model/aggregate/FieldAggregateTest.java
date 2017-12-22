/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.aggregate;

import carcassonne.model.set.BasicSet;
import static carcassonne.model.set.BasicSet.retTreeSet;
import carcassonne.model.tile.AbstractTile;
import carcassonne.model.tile.CasualTile;
import carcassonne.model.type.CityType;
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
public class FieldAggregateTest
{

    public FieldAggregateTest()
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
     * Test of checkIsCompleted method, of class FieldAggregate.
     */
    @Test
    public void testCheckIsCompleted()
    {
        Set<CityAggregate> set = new HashSet();
        set.add(new CityAggregate(0, 0, AbstractAggregateTest.initiateAbstractTile(), AbstractAggregateTest.initiateTypes()));
        FieldAggregate instance = new FieldAggregate(0, 0,
                AbstractAggregateTest.initiateAbstractTile(),
                AbstractAggregateTest.initiateTypes(),
                set
        );
        boolean expResult = false;
        boolean result = instance.checkIsCompleted();
        assertEquals(expResult, result);
    }

    public AbstractTile getJTile()
    {
        Set<Set<String>> aggregates = new HashSet<>();
        aggregates.add(BasicSet.retTreeSet("NW", "NWW", "W", "SWW", "SW", "SSW", "CSW", "CNW", "CNE", "NE", "NEE")); //F
        aggregates.add(BasicSet.retTreeSet("SSE", "SE", "SEE")); //F
        aggregates.add(BasicSet.retTreeSet("S", "CSE", "E")); //R
        aggregates.add(BasicSet.retTreeSet("NNW", "N", "NNE")); //C
        return new CasualTile("K", new FieldType(), new FieldType(), new FieldType(), new RoadType(), new FieldType(), new FieldType(), new CityType(), new CityType(), new CityType(), new FieldType(), new FieldType(), new FieldType(), new FieldType(), new FieldType(), new FieldType(), new RoadType(), new RoadType(), new FieldType(), new FieldType(), new FieldType());
    }

    public AbstractTile getITile()
    {
        Set<Set<String>> aggregates = new HashSet<>();
        aggregates.add(retTreeSet("SW", "SWW", "W", "NWW", "NW", "NNW", "N", "NNE", "NE", "CSW", "CSE", "CNW", "CNE", "SE")); //F
        aggregates.add(retTreeSet("NEE", "E", "SEE")); //C
        aggregates.add(retTreeSet("SSW", "S", "SSE")); //C

        return new CasualTile("I", "I", //Id
                new FieldType(), new FieldType(), new FieldType(), //North West section
                new FieldType(), //North
                new FieldType(), new FieldType(), new CityType(), //North East section
                new CityType(), //East
                new CityType(), new FieldType(), new CityType(), //South East section
                new CityType(), //South
                new CityType(), new FieldType(), new FieldType(), //South West section
                new FieldType(), //West
                new FieldType(), new FieldType(), new FieldType(), new FieldType(), //Center section
                aggregates
        );
    }

    /**
     * Test of getBoundedCities method, of class FieldAggregate.
     */
    @Test
    public void testGetBoundedCities()
    {
        System.out.println("getBoundedCities");
        CasualTile tile = (CasualTile) getJTile();

        CityAggregate aggr1 = new CityAggregate(0, 0, tile, BasicSet.retTreeSet("NNW", "N", "NNE"));
        CityAggregate aggr2 = new CityAggregate(0, 0, tile, BasicSet.retTreeSet("NWW", "W", "SWW"));

        Set<CityAggregate> citiesAggr = new HashSet();
        citiesAggr.add(aggr1);
        citiesAggr.add(aggr2);

        FieldAggregate instance = new FieldAggregate(0, 0, tile, BasicSet.retTreeSet("NW", "W", "SW", "SSW", "CSW", "CNW", "CNE", "NE", "NEE"), citiesAggr);
        Set<CityAggregate> result = instance.getBoundedCities();

        assertEquals(citiesAggr, result);
    }

    /**
     * Test of getBoundedCities method, of class FieldAggregate.
     */
    @Test
    public void testGetBoundedCities2()
    {
        System.out.println("getBoundedCities");
        CasualTile tile = (CasualTile) getJTile();

        CityAggregate aggr1 = new CityAggregate(0, 0, tile, BasicSet.retTreeSet("NNW", "N", "NNE"));
        CityAggregate aggr2 = new CityAggregate(0, 0, tile, BasicSet.retTreeSet("SE", "SEE", "SSE"));
        aggr1.enlargeAggregate(0, 1, tile, BasicSet.retTreeSet("S", "SSW", "SSE"));
        System.out.println(aggr1.checkIsCompleted());

        Set<CityAggregate> citiesAggr = new HashSet();
        citiesAggr.add(aggr1);
        citiesAggr.add(aggr2);

        FieldAggregate instance = new FieldAggregate(0, 0, tile, BasicSet.retTreeSet("NW", "NWW", "W", "SWW", "SW", "SSW", "CSW", "CNW", "CNE", "NE", "NEE"), citiesAggr);
        Set<CityAggregate> result = instance.getBoundedCities();

        //there is only the first aggr that is bounded to the field
        Set<CityAggregate> expRes = new HashSet();
        expRes.add(aggr1);

        assertEquals(expRes, result);
    }

    /**
     * Test of merge method, of class FieldAggregate.
     */
    @Test
    public void testMerge()
    {
        CasualTile tile = (CasualTile) getJTile();

        CityAggregate aggr1 = new CityAggregate(0, 0, tile, BasicSet.retTreeSet("NNW", "N", "NNE"));
        
        Set<CityAggregate> citiesAggr = new HashSet();
        citiesAggr.add(aggr1);
        
        FieldAggregate instance = new FieldAggregate(0, 0, tile, BasicSet.retTreeSet("NW", "NWW", "W", "SWW", "SW", "SSW", "CSW", "CNW", "CNE", "NE", "NEE"), citiesAggr);
        
        AbstractTile tile2 = getITile();
        tile2.rotateRight();
        CityAggregate aggr2 = new CityAggregate(-1, 0, tile2, BasicSet.retTreeSet("NEE", "E", "SEE"));
        citiesAggr = new HashSet();
        citiesAggr.add(aggr2);
        citiesAggr.add(aggr1);

        FieldAggregate instance2 = new FieldAggregate(-1, 0, tile2, BasicSet.retTreeSet("SW", "SWW", "W", "NWW", "NW", "NNW", "N", "NNE", "NE", "CSW", "CSE", "CNW", "CNE", "SE"), citiesAggr);

    
        instance.merge(instance2);
        
        Set<CityAggregate> expRes = new HashSet();
        expRes.add(aggr1);
        expRes.add(aggr2);
        
        Set<CityAggregate> result = instance.getBoundedCities();

        assertEquals(expRes, result);
    }
}
