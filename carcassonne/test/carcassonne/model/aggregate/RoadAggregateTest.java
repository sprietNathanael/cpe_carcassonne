/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.aggregate;

import carcassonne.model.player.Player;
import carcassonne.model.tile.AbstractTile;
import carcassonne.model.tile.CasualTile;
import carcassonne.model.type.AbbayType;
import carcassonne.model.type.CityType;
import carcassonne.model.type.CrossType;
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
        int result = instance.getCityEdges().size();
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
        int result = instance.getCityEdges().size();
        assertEquals(expResult, result);
    }

    /**
     * Test of countPoints method, of class RoadAggregate.
     */
    @Test
    public void testCountPoints()
    {
        AbstractTile firstTile = new CasualTile("V", new FieldType(), new FieldType(), new FieldType(), new FieldType(), new FieldType(), new RoadType(), new FieldType(), new RoadType(), new FieldType(), new FieldType(), new FieldType(), new RoadType());
        Set<String> locationTypes = new HashSet<>();
        locationTypes.add("S");
        locationTypes.add("CSW");
        locationTypes.add("W");

        RoadAggregate instance = new RoadAggregate(0, 0, firstTile, locationTypes);

        AbstractTile secondTile = new CasualTile("O", new FieldType(), new FieldType(), new FieldType(), new RoadType(), new FieldType(), new RoadType(), new FieldType(), new FieldType(), new FieldType(), new FieldType(), new RoadType(), new FieldType());
        locationTypes = new HashSet<>();
        locationTypes.add("S");
        locationTypes.add("CSE");
        locationTypes.add("E");
        instance.enlargeAggregate(-1, 0, secondTile, locationTypes);
        Player player = new Player("heelo !", "blue", "Player");
        instance.addMeeple(player, player.getFirstMeepleAvailable());

        AbstractTile thirdTile = new CasualTile("X", new FieldType(), new RoadType(), new FieldType(), new RoadType(), new FieldType(), new RoadType(), new FieldType(), new RoadType(), new CrossType(), new CrossType(), new CrossType(), new CrossType());
        locationTypes = new HashSet<>();
        locationTypes.add("S");
        locationTypes.add("E");
        instance.enlargeAggregate(-1, -1, thirdTile, locationTypes);

        AbstractTile fourthTile = new CasualTile("K", new FieldType(), new FieldType(), new FieldType(), new RoadType(), new FieldType(), new FieldType(), new CityType(), new CityType(), new CityType(), new FieldType(), new FieldType(), new FieldType(), new FieldType(), new FieldType(), new FieldType(), new RoadType(), new RoadType(), new FieldType(), new FieldType(), new FieldType());
        locationTypes = new HashSet<>();
        locationTypes.add("N");
        locationTypes.add("CNW");
        locationTypes.add("W");
        instance.enlargeAggregate(0, -1, fourthTile, locationTypes);

        int result = instance.countPoints();
        assertEquals(4, result);
    }
}