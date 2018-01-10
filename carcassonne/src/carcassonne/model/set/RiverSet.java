/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.set;

import carcassonne.model.tile.AbstractTile;
import carcassonne.model.tile.CasualTile;
import carcassonne.model.type.AbbayType;
import carcassonne.model.type.CityType;
import carcassonne.model.type.FieldType;
import carcassonne.model.type.RiverType;
import carcassonne.model.type.RoadType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This is the set for the River extension
 * 
 * @author Bertrand
 */
public class RiverSet implements SetInterface
{
    private final List<AbstractTile> tileList;
    private AbstractTile firstTile;
    
    public RiverSet()
    {
        tileList = new ArrayList<>();
        this.initiliazeSet();
    }

    @Override
    public List<AbstractTile> getSet()
    {
        return tileList;
    }

    @Override
    public AbstractTile getFirstTile()
    {
        return this.firstTile;
    }
    
     /**
     * Adds all the tiles of the extension
     */
    private void initiliazeSet()
    {
        Set<Set<String>> aggregates;
        
        //Tile RA
        aggregates = new HashSet<>();
        aggregates.add(retTreeSet("S", "SSW", "SW", "SWW", "W", "NWW", "NW", "NNW", "N",
                    "NNE", "NE", "NEE", "SSE", "SE", "SEE")); //F

        tileList.add(new CasualTile("RA", "river", "RA", //Id
                new FieldType(), new FieldType(), new FieldType(), //North section
                new RiverType(), //East section
                new FieldType(), new RoadType(), new FieldType(), //South section
                new FieldType(), //West section
                new RiverType(), new RiverType(), new RiverType(), new RiverType(), //Center section
                aggregates
            ));
        
        //Tile RB
        aggregates = new HashSet<>();
        aggregates.add(retTreeSet("NNW", "N", "NNE")); //C
        aggregates.add(retTreeSet("NWW", "NW", "CNW", "CNE", "NE", "NEE")); //F
        aggregates.add(retTreeSet("SWW", "SW", "CSW", "CSE", "SE", "SEE")); //F
        aggregates.add(retTreeSet("SSW", "S", "SSE")); //C

        tileList.add(new CasualTile("RB", "river", "RB", //Id
                new FieldType(), new FieldType(), new CityType(), //North West section
                new CityType(), //North
                new CityType(), new FieldType(), new FieldType(), //North East section
                new FieldType(), //East
                new FieldType(), new FieldType(), new CityType(), //South East section
                new CityType(), //South
                new CityType(), new FieldType(), new FieldType(), //South West section
                new FieldType(), //West
                new RiverType(), new RiverType(), new RiverType(), new RiverType(), //Center section
                aggregates
            ));
        
        //Tiles RC (x2)
        for (int i = 1; i <= 2; i++) {
            aggregates = new HashSet<>();
            aggregates.add(retTreeSet("NWW", "NW", "NNW", "N", "NNE", "NE", "NEE")); //F
            aggregates.add(retTreeSet("SWW", "SW", "SSW", "S", "SSE", "SE", "SEE")); //F

            tileList.add(new CasualTile("RC", "river", "RC" + i, //Id
                    new FieldType(), new FieldType(), new FieldType(), //North section
                    new RiverType(), //East section
                    new FieldType(), new FieldType(), new FieldType(), //South section
                    new RiverType(), //West section
                    new RiverType(), new RiverType(), new RiverType(), new RiverType(), //Center section
                    aggregates
            ));
        }
        
        //Tiles RD (x2)
        for (int i = 1; i <= 2; i++) {
            aggregates = new HashSet<>();
            aggregates.add(retTreeSet("NNW", "NW", "NWW", "N", "NNE", "NE", "NEE", "E", "SEE", "SE", "SSE", "CNW", "CNE", "CSE")); //F
            aggregates.add(retTreeSet("SWW", "SW", "SSW")); //F

            tileList.add(new CasualTile("RD", "river", "RD" + i, //Id
                    new FieldType(), new FieldType(), new FieldType(), //North section
                    new FieldType(), //East section
                    new FieldType(), new RiverType(), new FieldType(), //South section
                    new RiverType(), //West section
                    new FieldType(), new FieldType(), new FieldType(), new RiverType(), //Center section
                    aggregates
            ));
        }
        
        //Tile RE
        aggregates = new HashSet<>();
        aggregates.add(retTreeSet("S", "SSW", "SW", "SWW", "NWW", "NW", "NNW", "N",
                    "NNE", "NE", "NEE", "E", "SSE", "SE", "SEE")); //F

        this.firstTile = new CasualTile("RE", "river", "RE", //Id
                new FieldType(), new FieldType(), new FieldType(), //North section
                new FieldType(), //East section
                new FieldType(), new RoadType(), new FieldType(), //South section
                new RiverType(), //West section
                new RiverType(), new RiverType(), new RiverType(), new RiverType(), //Center section
                aggregates
            );
                
        //Tile RG
        aggregates = new HashSet<>();
        aggregates.add(retTreeSet("NWW", "NW", "NNW")); //F
        aggregates.add(retTreeSet("NNE", "NE", "NEE")); //F
        aggregates.add(retTreeSet("SWW", "SW", "SSW")); //F
        aggregates.add(retTreeSet("SSE", "SE", "SEE")); //F
        aggregates.add(retTreeSet("W", "E", "CNW", "CNE", "CSW", "CSE")); //R       

        tileList.add(new CasualTile("RG", "river", "RG", //Id
                new FieldType(), new RiverType(), new FieldType(), //North section
                new RoadType(), //East section
                new FieldType(), new RiverType(), new FieldType(), //South section
                new RoadType(), //West section
                new RoadType(), new RoadType(), new RoadType(), new RoadType(), //Center section
                aggregates
        ));
        
        //Tile RH
        aggregates = new HashSet<>();
        aggregates.add(retTreeSet("NWW", "NW", "NNW", "N", "NNE", "NE", "NEE")); //F
        aggregates.add(retTreeSet("CNW", "CNE", "CSW", "CSE")); //A
        aggregates.add(retTreeSet("SWW", "SW", "SSW")); //F
        aggregates.add(retTreeSet("SSE", "SE", "SEE")); //F
        aggregates.add(retTreeSet("S")); //F

        tileList.add(new CasualTile("RH", "river", "RH", //Id
                new FieldType(), new FieldType(), new FieldType(), //North section
                new RiverType(), //East section
                new FieldType(), new RoadType(), new FieldType(), //South section
                new RiverType(), //West section
                new AbbayType(), new AbbayType(), new AbbayType(), new AbbayType(), //Center section
                aggregates
        ));
        
        //Tile RI
        aggregates = new HashSet<>();
        aggregates.add(retTreeSet("NNW", "N", "NNE")); //C
        aggregates.add(retTreeSet("NWW", "NW", "NE", "NEE")); //F
        aggregates.add(retTreeSet("SWW", "SW", "SSW")); //F
        aggregates.add(retTreeSet("SSE", "SE", "SEE")); //F
        aggregates.add(retTreeSet("S", "CSW", "CSE", "CNW", "CNE")); //R

        tileList.add(new CasualTile("RI", "river", "RI", //Id
                new FieldType(), new FieldType(), new CityType(), //North West section
                new CityType(), //North
                new CityType(), new FieldType(), new FieldType(), //North East section
                new FieldType(), //East
                new FieldType(), new FieldType(), new FieldType(), //South East section
                new RoadType(), //South
                new FieldType(), new FieldType(), new FieldType(), //South West section
                new FieldType(), //West
                new RoadType(), new RoadType(), new RoadType(), new RoadType(), //Center section
                aggregates
            ));
        
        //Tile RJ
        aggregates = new HashSet<>();
            aggregates.add(retTreeSet("NNE", "NE", "NEE")); //F
            aggregates.add(retTreeSet("N", "CNE", "E"));
            aggregates.add(retTreeSet("NNW", "NW", "NWW", "SEE", "SE", "SSE", "CNW", "CSE")); //F
            aggregates.add(retTreeSet("SWW", "SW", "SSW")); //F

            tileList.add(new CasualTile("RJ", "river", "RJ" , //Id
                    new FieldType(), new RoadType(), new FieldType(), //North section
                    new RoadType(), //East section
                    new FieldType(), new RiverType(), new FieldType(), //South section
                    new RiverType(), //West section
                    new FieldType(), new RoadType(), new FieldType(), new RiverType(), //Center section
                    aggregates
            ));
            
        //Tile RK
        aggregates = new HashSet<>();
        aggregates.add(retTreeSet("NE", "NEE", "SSW", "SW", "CSW", "CSE", "CNW", "CNE")); //F
        aggregates.add(retTreeSet("SEE", "SE", "SSE")); //F
        aggregates.add(retTreeSet("NNE", "N", "NNW", "NW", "NWW", "W", "SWW")); //C

        tileList.add(new CasualTile("RK", "river", "RK", //Id
                new CityType(), new CityType(), new CityType(), //North West section
                new CityType(), //North
                new CityType(), new FieldType(), new FieldType(), //North East section
                new RiverType(), //East
                new FieldType(), new FieldType(), new FieldType(), //South East section
                new RiverType(), //South
                new FieldType(), new FieldType(), new CityType(), //South West section
                new CityType(), //West
                new FieldType(), new FieldType(), new RiverType(), new FieldType(), //Center section
                aggregates
            ));
    }
    
    public static Set<String> retTreeSet(String... poss)
    {
        Set<String> tsPos = new HashSet<>();
        tsPos.addAll(Arrays.asList(poss));        
        return tsPos;
    }
    
}
