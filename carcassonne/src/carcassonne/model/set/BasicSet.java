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
import carcassonne.model.type.CrossType;
import carcassonne.model.type.FieldType;
import carcassonne.model.type.RoadType;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This is the basic set of the game
 *
 * @author Étienne
 */
public class BasicSet implements SetInterface
{

    private final List<AbstractTile> tileList;
    private AbstractTile firstTile;

    /**
     * Initilializes the basic set with all its tiles
     */
    public BasicSet()
    {
        tileList = new ArrayList<>();
        this.initiliazeSet();
    }

    @Override
    public List<AbstractTile> getSet()
    {
        return tileList;
    }

    /**
     * Adds all the tiles of the extension
     */
    private void initiliazeSet()
    {
        Set<Set<String>> aggregates;
        //Tiles D (x3)
        for (int i = 1; i <= 1; i++) {
            aggregates = new HashSet<>();
            aggregates.add(retTreeSet("NEE", "E", "SEE"));
            aggregates.add(retTreeSet("S", "CNW", "CSW", "N"));
            aggregates.add(retTreeSet("NNW", "NW", "NWW", "W", "SWW", "SW", "SSW"));
            aggregates.add(retTreeSet("NNE", "NE", "CNE", "CSE", "SE", "SSE"));

            tileList.add(new CasualTile("D", "D" + i, //Id
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
            ));
        }
        //First Tile is a D tils
        aggregates = new HashSet<>();
        aggregates.add(retTreeSet("NEE", "E", "SEE"));
        aggregates.add(retTreeSet("S", "CNW", "CSW", "N"));
        aggregates.add(retTreeSet("NNW", "NW", "NWW", "W", "SWW", "SW", "SSW"));
        aggregates.add(retTreeSet("NNE", "NE", "CNE", "CSE", "SE", "SSE"));

        this.firstTile = new CasualTile("D", "D0", //Id
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
        //Tiles E (x5)
        for (int i = 1; i <= 1; i++) {
            aggregates = new HashSet<>();
            aggregates.add(retTreeSet("NNW", "N", "NNE"));
            aggregates.add(retTreeSet("NW", "NWW", "W", "SWW", "SW", "SSW", "S", "SSE", "SE", "SEE", "E", "NEE", "NE", "CSW", "CSE", "CNW", "CNE"));

            tileList.add(new CasualTile("E", "E" + i, //Id
                    new FieldType(), new FieldType(), new CityType(), //North West section
                    new CityType(), //North
                    new CityType(), new FieldType(), new FieldType(), //North East section
                    new FieldType(), //East
                    new FieldType(), new FieldType(), new FieldType(), //South East section
                    new FieldType(), //South
                    new FieldType(), new FieldType(), new FieldType(), //South West section
                    new FieldType(), //West
                    new FieldType(), new FieldType(), new FieldType(), new FieldType(), //Center section
                    aggregates
            ));
        }
        //Tiles P (x3)
        for (int i = 1; i <= 1; i++) {
            aggregates = new HashSet<>();
            aggregates.add(retTreeSet("NE", "NEE", "SSW", "SW", "CSW", "CSE", "CNW", "CNE")); //F
            aggregates.add(retTreeSet("SEE", "SE", "SSE")); //F
            aggregates.add(retTreeSet("S", "CSE", "E")); //R
            aggregates.add(retTreeSet("NNE", "N", "NNW", "NW", "NWW", "W", "SWW")); //C

            tileList.add(new CasualTile("P", "P" + i, //Id
                    new CityType(), new CityType(), new CityType(), //North West section
                    new CityType(), //North
                    new CityType(), new FieldType(), new FieldType(), //North East section
                    new RoadType(), //East
                    new FieldType(), new FieldType(), new FieldType(), //South East section
                    new RoadType(), //South
                    new FieldType(), new FieldType(), new CityType(), //South West section
                    new CityType(), //West
                    new FieldType(), new FieldType(), new RoadType(), new FieldType(), //Center section
                    aggregates
            ));
        }
        //Tiles V (x9)
        for (int i = 1; i <= 1; i++) {
            aggregates = new HashSet<>();
            aggregates.add(retTreeSet("NNW", "NW", "NWW", "N", "NNE", "NE", "NEE", "E", "SEE", "SE", "SSE", "CNW", "CNE", "CSE")); //F
            aggregates.add(retTreeSet("SWW", "SW", "SSW")); //F
            aggregates.add(retTreeSet("W", "CSW", "S")); //R

            tileList.add(new CasualTile("V", "V" + i, //Id
                    new FieldType(), new FieldType(), new FieldType(), //North section
                    new FieldType(), //East section
                    new FieldType(), new RoadType(), new FieldType(), //South section
                    new RoadType(), //West section
                    new FieldType(), new FieldType(), new FieldType(), new RoadType(), //Center section
                    aggregates
            ));
        }
        //Tiles W (x4)
        for (int i = 1; i <= 1; i++) {
            aggregates = new HashSet<>();
            aggregates.add(retTreeSet("NWW", "NW", "NNW", "N", "NNE", "NE", "NEE")); //F
            aggregates.add(retTreeSet("SWW", "SW", "SSW")); //F
            aggregates.add(retTreeSet("SSE", "SE", "SEE")); //F
            aggregates.add(retTreeSet("W")); //R
            aggregates.add(retTreeSet("S")); //R
            aggregates.add(retTreeSet("E")); //R

            tileList.add(new CasualTile("W", "W" + i, //Id
                    new FieldType(), new FieldType(), new FieldType(), //North section
                    new RoadType(), //East section
                    new FieldType(), new RoadType(), new FieldType(), //South section
                    new RoadType(), //West section
                    new CrossType(), new CrossType(), new CrossType(), new CrossType(), //Center section
                    aggregates
            ));
        }
    }

    public static Set<String> retTreeSet(String... poss)
    {
        Set<String> tsPos = new HashSet<>();
        for (String pos : poss) 
            tsPos.add(pos);
        
        return tsPos;
    }

    @Override
    public AbstractTile getFirstTile()
    {
        return this.firstTile;
    }
}
