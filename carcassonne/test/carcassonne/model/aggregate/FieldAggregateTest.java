/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.aggregate;

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
        System.out.println(instance.neighborCities);
        boolean result = instance.checkIsCompleted();
        assertEquals(expResult, result);
    }

}
