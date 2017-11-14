/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.board;

import carcassonne.model.tile.AbstractTile;
import carcassonne.model.tile.CasualTile;
import carcassonne.model.type.AbbayType;
import carcassonne.model.type.CityType;
import carcassonne.model.type.CrossType;
import carcassonne.model.type.FieldType;
import carcassonne.model.type.RiverType;
import carcassonne.model.type.RoadType;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Thomas
 */
public class BoardTest
{
    
    public BoardTest()
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
     * Test of addTile method, of class Board.
     */
    @Test
    public void testAddTile() throws Exception
    {
        System.out.println("addTile");
        CasualTile newTiles = new CasualTile("A",
                new AbbayType(), new RoadType(), new RiverType(), new RoadType(), new RiverType(), new CityType(), new RoadType(), // Northen line
                new CityType(), // East
                new RoadType(), new FieldType(), new RiverType(), new FieldType(), new CityType(), new RiverType(), new FieldType(), // Southern line
                new RiverType(), // West
                new RoadType(), new CityType(), new FieldType(), new RiverType() // Center
        );
        int row = 0;
        int column = 0;
        Board instance = new Board();
        instance.addTile(newTiles, row, column);
        assertEquals(instance.getTile(row, column), newTiles);
    }

    /**
     * Test of getTile method, of class Board.
     */
    @Test
    public void testGetTile() throws Exception
    {
        System.out.println("getTile");
        int row = 0;
        int column = 0;
        Board instance = new Board();
        CasualTile expResult = new CasualTile("A",
                new AbbayType(), new RoadType(), new RiverType(), new RoadType(), new RiverType(), new CityType(), new RoadType(), // Northen line
                new CityType(), // East
                new RoadType(), new FieldType(), new RiverType(), new FieldType(), new CityType(), new RiverType(), new FieldType(), // Southern line
                new RiverType(), // West
                new RoadType(), new CityType(), new FieldType(), new RiverType() // Center
        );
        instance.addTile(expResult, row, column);
        AbstractTile result = instance.getTile(row, column);
        assertEquals(expResult, result);
    }

    /**
     * Test of compareTileNorth method, of class Board.
     */
    @Test
    public void testCompareTileNorthTrue()
    {
        System.out.println("compareTileNorthTrue");

        CasualTile tile1 = new CasualTile("A", null, null, new CityType(), new FieldType(), new RoadType(), null, null, 
                                                    null, 
                                                    null, null, null, null, null, null, null, 
                                                    null, 
                                                    null, null, null, null);
        
        CasualTile tile2 = new CasualTile("A", null, null, null, null, null, null, null, 
                                                    null, 
                                                    null, null, new RoadType(), new FieldType(), new CityType(), null, null, 
                                                    null, 
                                                    null, null, null, null);
        
        Board instance = new Board();
        boolean result = instance.compareTileNorth(tile1, tile2);
        assertEquals(true, result);    
    }
    
    /**
     * Test of compareTileNorth method, of class Board.
     */
    @Test
    public void testCompareTileNorthFalse()
    {
        System.out.println("compareTileNorthFalse");

        CasualTile tile1 = new CasualTile("A", null, null, new CityType(), new FieldType(), new RoadType(), null, null, 
                                                    null, 
                                                    null, null, null, null, null, null, null, 
                                                    null, 
                                                    null, null, null, null);
        
        CasualTile tile2 = new CasualTile("A", null, null, null, null, null, null, null, 
                                                    null, 
                                                    null, null, new CityType() , new FieldType(), new RoadType(), null, null, 
                                                    null, 
                                                    null, null, null, null);
        
        Board instance = new Board();
        boolean result = instance.compareTileNorth(tile1, tile2);
        assertEquals(false, result);    
    }    
    

    /**
     * Test of compareTileSouth method, of class Board.
     */
    @Test
    public void testCompareTileSouthTrue()
    {
        System.out.println("compareTileSouthTrue");
        CasualTile tile1 = new CasualTile("A", null, null, null, null, null, null, null, 
                                                    null, 
                                                    null, null, new CrossType(), new FieldType(), new CityType(), null, null, 
                                                    null, 
                                                    null, null, null, null);
        
        CasualTile tile2 = new CasualTile("A", null, null, new CityType(), new FieldType(), new CrossType(), null, null, 
                                                    null, 
                                                    null, null, null, null, null, null, null, 
                                                    null, 
                                                    null, null, null, null);
        
        Board instance = new Board();
        boolean result = instance.compareTileSouth(tile1, tile2);
        assertEquals(true, result); 
    }
    
    /**
     * Test of compareTileSouth method, of class Board.
     */
    @Test
    public void testCompareTileSouthFalse()
    {
        System.out.println("compareTileSouthFalse");
        CasualTile tile1 = new CasualTile("A", null, null, null, null, null, null, null, 
                                                    null, 
                                                    null, null, new CrossType(), new FieldType(), new CityType(), null, null, 
                                                    null, 
                                                    null, null, null, null);
        
        CasualTile tile2 = new CasualTile("A", null, null, new CrossType(), new FieldType(), new CityType(), null, null, 
                                                    null, 
                                                    null, null, null, null, null, null, null, 
                                                    null, 
                                                    null, null, null, null);
        
        Board instance = new Board();
        boolean result = instance.compareTileSouth(tile1, tile2);
        assertEquals(false, result); 
    }

    /**
     * Test of compareTileWest method, of class Board.
     */
    @Test
    public void testCompareTileWest()
    {
        System.out.println("compareTileWest");
        CasualTile tile1 = null;
        CasualTile tile2 = null;
        Board instance = new Board();
        boolean expResult = false;
        boolean result = instance.compareTileWest(tile1, tile2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of compareTileEast method, of class Board.
     */
    @Test
    public void testCompareTileEast()
    {
        System.out.println("compareTileEast");
        CasualTile tile1 = null;
        CasualTile tile2 = null;
        Board instance = new Board();
        boolean expResult = false;
        boolean result = instance.compareTileEast(tile1, tile2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }    
}
