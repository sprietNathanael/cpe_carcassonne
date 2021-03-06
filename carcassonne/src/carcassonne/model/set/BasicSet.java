/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.set;

import carcassonne.model.player.Meeple;
import carcassonne.model.tile.AbstractTile;
import carcassonne.model.tile.CasualTile;
import carcassonne.model.type.AbbayType;
import carcassonne.model.type.CityType;
import carcassonne.model.type.CrossType;
import carcassonne.model.type.FieldType;
import carcassonne.model.type.RoadType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This is the basic set of the game
 *
 * @author Étienne
 */
public class BasicSet extends AbstractSet implements Serializable
{

    /**
     * Initilializes the basic set with all its tiles
     */
    public BasicSet()
    {
        super();
        this.initiliazeSet();
        for(int i = 0; i < 6; i++)
        {
            this.meeples.add(new Meeple(null));            
        }
    }

    /**
     * Adds all the tiles of the extension
     */
    @Override
    protected void initiliazeSet()
    {
        Set<Set<String>> aggregates;
        //Tiles A (x2)
        for (int i = 1; i <= 2; i++) {
            aggregates = new HashSet<>();
            aggregates.add(retTreeSet("S"));
            aggregates.add(retTreeSet("SSW", "SW", "SWW", "W", "NWW", "NW", "NNW", "N",
                    "NNE", "NE", "NEE", "E", "SSE", "SE", "SEE"));
            aggregates.add(retTreeSet("CNW", "CNE", "CSW", "CSE"));

            tileList.add(new CasualTile("A", "casual", "A" + i, //Id
                    new FieldType(), new FieldType(), new FieldType(), //North section
                    new FieldType(), //East section
                    new FieldType(), new RoadType(), new FieldType(), //South section
                    new FieldType(), //West section
                    new AbbayType(), new AbbayType(), new AbbayType(), new AbbayType(), //Center section
                    aggregates
            ));
        }
        //Tiles B (x4)
        for (int i = 1; i <= 4; i++) {
            aggregates = new HashSet<>();
            aggregates.add(retTreeSet("S", "SSW", "SW", "SWW", "W", "NWW", "NW", "NNW", "N",
                    "NNE", "NE", "NEE", "E", "SEE", "SE", "SSE"));
            aggregates.add(retTreeSet("CNW", "CNE", "CSW", "CSE"));

            tileList.add(new CasualTile("B", "casual", "B" + i, //Id
                    new FieldType(), new FieldType(), new FieldType(), //North section
                    new FieldType(), //East section
                    new FieldType(), new FieldType(), new FieldType(), //South section
                    new FieldType(), //West section
                    new AbbayType(), new AbbayType(), new AbbayType(), new AbbayType(), //Center section
                    aggregates
            ));
        }
        //Tile C (x1)
        aggregates = new HashSet<>();
        aggregates.add(retTreeSet("N", "NNE", "NE", "NEE", "E", "SSE", "SE", "SEE", "S",
                "SSW", "SW", "SWW", "W", "NWW", "NW", "NNW", "CNW", "CNE", "CSW", "CSE"));

        tileList.add(new CasualTile("C", "casual", "C", //Id
                new CityType(true), new CityType(true), new CityType(true), //North section
                new CityType(true), //East section
                new CityType(true), new CityType(true), new CityType(true), //South section
                new CityType(true), //West section
                new CityType(true), new CityType(true), new CityType(true), new CityType(true), //Center section
                aggregates
        ));
        //Tiles D (x3)
        for (int i = 1; i <= 3; i++) {
            aggregates = new HashSet<>();
            aggregates.add(retTreeSet("NEE", "E", "SEE"));
            aggregates.add(retTreeSet("S", "CNW", "CSW", "N"));
            aggregates.add(retTreeSet("NNW", "NW", "NWW", "W", "SWW", "SW", "SSW"));
            aggregates.add(retTreeSet("NNE", "NE", "CNE", "CSE", "SE", "SSE"));

            tileList.add(new CasualTile("D", "casual", "D" + i, //Id
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

        this.firstTile = new CasualTile("D", "casual", "D0", //Id
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
        for (int i = 1; i <= 5; i++) {
            aggregates = new HashSet<>();
            aggregates.add(retTreeSet("NNW", "N", "NNE"));
            aggregates.add(retTreeSet("NW", "NWW", "W", "SWW", "SW", "SSW", "S", "SSE", "SE", "SEE", "E", "NEE", "NE", "CSW", "CSE", "CNW", "CNE"));

            tileList.add(new CasualTile("E", "casual", "E" + i, //Id
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
        //Tiles F (x2)
        for (int i = 1; i <= 2; i++) {
            aggregates = new HashSet<>();
            aggregates.add(retTreeSet("SW", "SSW", "S", "SSE", "SE"));
            aggregates.add(retTreeSet("NW", "NNW", "N", "NNE", "NE"));
            aggregates.add(retTreeSet("NWW", "W", "SWW", "CSW", "CSE", "CNW", "CNE", "SEE", "E", "NEE"));

            tileList.add(new CasualTile("F", "casual", "F" + i, //Id
                    new CityType(true), new FieldType(), new FieldType(), //North West section
                    new FieldType(), //North
                    new FieldType(), new FieldType(), new CityType(true), //North East section
                    new CityType(true), //East
                    new CityType(true), new FieldType(), new FieldType(), //South East section
                    new FieldType(), //South
                    new FieldType(), new FieldType(), new CityType(true), //South West section
                    new CityType(true), //West
                    new CityType(true), new CityType(true), new CityType(true), new CityType(true), //Center section
                    aggregates
            ));
        }
        //Tile G (x1)
        aggregates = new HashSet<>();
        aggregates.add(retTreeSet("NW", "NWW", "W", "SWW", "SW"));
        aggregates.add(retTreeSet("NE", "NEE", "E", "SEE", "SE"));
        aggregates.add(retTreeSet("NNW", "N", "NNE", "CSW", "CSE", "CNW", "CNE", "SSW", "S", "SSE"));

        tileList.add(new CasualTile("G", "casual", "G", //Id
                new FieldType(), new FieldType(), new CityType(), //North West section
                new CityType(), //North
                new CityType(), new FieldType(), new FieldType(), //North East section
                new FieldType(), //East
                new FieldType(), new FieldType(), new CityType(), //South East section
                new CityType(), //South
                new CityType(), new FieldType(), new FieldType(), //South West section
                new FieldType(), //West
                new CityType(), new CityType(), new CityType(), new CityType(), //Center section
                aggregates
        ));
        //Tiles H (x3)
        for (int i = 1; i <= 3; i++) {
            aggregates = new HashSet<>();
            aggregates.add(retTreeSet("NW", "NNW", "N", "NNE", "NE", "CSW", "CSE", "CNW", "CNE", "SE", "SSE", "S", "SSW", "SW")); //F
            aggregates.add(retTreeSet("NWW", "W", "SWW")); //C
            aggregates.add(retTreeSet("SEE", "E", "NEE")); //C

            tileList.add(new CasualTile("H", "casual", "H" + i, //Id
                    new CityType(), new FieldType(), new FieldType(), //North West section
                    new FieldType(), //North
                    new FieldType(), new FieldType(), new CityType(), //North East section
                    new CityType(), //East
                    new CityType(), new FieldType(), new FieldType(), //South East section
                    new FieldType(), //South
                    new FieldType(), new FieldType(), new CityType(), //South West section
                    new CityType(), //West
                    new FieldType(), new FieldType(), new FieldType(), new FieldType(), //Center section
                    aggregates
            ));
        }
        //Tiles I (x2)
        for (int i = 1; i <= 2; i++) {
            aggregates = new HashSet<>();
            aggregates.add(retTreeSet("SW", "SWW", "W", "NWW", "NW", "NNW", "N", "NNE", "NE", "CSW", "CSE", "CNW", "CNE", "SE")); //F
            aggregates.add(retTreeSet("NEE", "E", "SEE")); //C
            aggregates.add(retTreeSet("SSW", "S", "SSE")); //C

            tileList.add(new CasualTile("I", "casual", "I" + i, //Id
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
            ));
        }
        //Tiles J (x3)
        for (int i = 1; i <= 3; i++) {
            aggregates = new HashSet<>();
            aggregates.add(retTreeSet("NW", "NWW", "W", "SWW", "SW", "SSW", "CSW", "CNW", "CNE", "NE", "NEE")); //F
            aggregates.add(retTreeSet("SSE", "SE", "SEE")); //F
            aggregates.add(retTreeSet("S", "CSE", "E")); //R
            aggregates.add(retTreeSet("NNW", "N", "NNE")); //C

            tileList.add(new CasualTile("J", "casual", "J" + i, //Id
                    new FieldType(), new FieldType(), new CityType(), //North West section
                    new CityType(), //North
                    new CityType(), new FieldType(), new FieldType(), //North East section
                    new RoadType(), //East
                    new FieldType(), new FieldType(), new FieldType(), //South East section
                    new RoadType(), //South
                    new FieldType(), new FieldType(), new FieldType(), //South West section
                    new FieldType(), //West
                    new FieldType(), new FieldType(), new RoadType(), new FieldType(), //Center section
                    aggregates
            ));
        }
        //Tiles K (x3)
        for (int i = 1; i <= 3; i++) {
            aggregates = new HashSet<>();
            aggregates.add(retTreeSet("NNE", "NE", "CSE", "CSW", "CNE", "SE", "SSE", "S", "SSW", "SW", "SWW")); //F
            aggregates.add(retTreeSet("NWW", "NW", "NNW")); //F
            aggregates.add(retTreeSet("W", "CNW", "N")); //R
            aggregates.add(retTreeSet("NEE", "E", "SEE")); //C

            tileList.add(new CasualTile("K", "casual", "K" + i, //Id
                    new FieldType(), new FieldType(), new FieldType(), //North West section
                    new RoadType(), //North
                    new FieldType(), new FieldType(), new CityType(), //North East section
                    new CityType(), //East
                    new CityType(), new FieldType(), new FieldType(), //South East section
                    new FieldType(), //South
                    new FieldType(), new FieldType(), new FieldType(), //South West section
                    new RoadType(), //West
                    new RoadType(), new FieldType(), new FieldType(), new FieldType(), //Center section
                    aggregates
            ));
        }
        //Tiles L (x3)
        for (int i = 1; i <= 3; i++) {
            aggregates = new HashSet<>();
            aggregates.add(retTreeSet("NNE", "NE", "CNE", "CSE", "SE", "SSE")); //F
            aggregates.add(retTreeSet("NWW", "NW", "NNW")); //F
            aggregates.add(retTreeSet("SWW", "SW", "SSW")); //F
            aggregates.add(retTreeSet("NEE", "E", "SEE")); //C
            aggregates.add(retTreeSet("N")); //R
            aggregates.add(retTreeSet("W")); //R
            aggregates.add(retTreeSet("S")); //R

            tileList.add(new CasualTile("L", "casual", "L" + i, //Id
                    new FieldType(), new FieldType(), new FieldType(), //North West section
                    new RoadType(), //North
                    new FieldType(), new FieldType(), new CityType(), //North East section
                    new CityType(), //East
                    new CityType(), new FieldType(), new FieldType(), //South East section
                    new RoadType(), //South
                    new FieldType(), new FieldType(), new FieldType(), //South West section
                    new RoadType(), //West
                    new CrossType(), new FieldType(), new FieldType(), new CrossType(), //Center section
                    aggregates
            ));
        }
        //Tiles M (x2)
        for (int i = 1; i <= 2; i++) {
            aggregates = new HashSet<>();
            aggregates.add(retTreeSet("NE", "NEE", "E", "SEE", "SE", "SSE", "S", "SSW", "SW", "CSW", "CSE", "CNW", "CNE")); //F
            aggregates.add(retTreeSet("NNE", "N", "NNW", "NW", "NWW", "W", "SWW")); //C

            tileList.add(new CasualTile("M", "casual", "M" + i, //Id
                    new CityType(true), new CityType(true), new CityType(true), //North West section
                    new CityType(true), //North
                    new CityType(true), new FieldType(), new FieldType(), //North East section
                    new FieldType(), //East
                    new FieldType(), new FieldType(), new FieldType(), //South East section
                    new FieldType(), //South
                    new FieldType(), new FieldType(), new CityType(true), //South West section
                    new CityType(true), //West
                    new FieldType(), new FieldType(), new FieldType(), new FieldType(), //Center section
                    aggregates
            ));
        }
        //Tiles N (x3)
        for (int i = 1; i <= 3; i++) {
            aggregates = new HashSet<>();
            aggregates.add(retTreeSet("NE", "NEE", "E", "SEE", "SE", "SSE", "S", "SSW", "SW", "CSW", "CSE", "CNW", "CNE")); //F
            aggregates.add(retTreeSet("NNE", "N", "NNW", "NW", "NWW", "W", "SWW")); //C

            tileList.add(new CasualTile("N", "casual", "N" + i, //Id
                    new CityType(), new CityType(), new CityType(), //North West section
                    new CityType(), //North
                    new CityType(), new FieldType(), new FieldType(), //North East section
                    new FieldType(), //East
                    new FieldType(), new FieldType(), new FieldType(), //South East section
                    new FieldType(), //South
                    new FieldType(), new FieldType(), new CityType(), //South West section
                    new CityType(), //West
                    new FieldType(), new FieldType(), new FieldType(), new FieldType(), //Center section
                    aggregates
            ));
        }
        //Tiles O (x2)
        for (int i = 1; i <= 2; i++) {
            aggregates = new HashSet<>();
            aggregates.add(retTreeSet("NE", "NEE", "SSW", "SW", "CSW", "CSE", "CNW", "CNE")); //F
            aggregates.add(retTreeSet("SEE", "SE", "SSE")); //F
            aggregates.add(retTreeSet("S", "CSE", "E")); //R
            aggregates.add(retTreeSet("NNE", "N", "NNW", "NW", "NWW", "W", "SWW")); //C

            tileList.add(new CasualTile("O", "casual", "O" + i, //Id
                    new CityType(true), new CityType(true), new CityType(true), //North West section
                    new CityType(true), //North
                    new CityType(true), new FieldType(), new FieldType(), //North East section
                    new RoadType(), //East
                    new FieldType(), new FieldType(), new FieldType(), //South East section
                    new RoadType(), //South
                    new FieldType(), new FieldType(), new CityType(true), //South West section
                    new CityType(true), //West
                    new FieldType(), new FieldType(), new RoadType(), new FieldType(), //Center section
                    aggregates
            ));
        }
        //Tiles P (x3)
        for (int i = 1; i <= 3; i++) {
            aggregates = new HashSet<>();
            aggregates.add(retTreeSet("NE", "NEE", "SSW", "SW", "CSW", "CSE", "CNW", "CNE")); //F
            aggregates.add(retTreeSet("SEE", "SE", "SSE")); //F
            aggregates.add(retTreeSet("S", "CSE", "E")); //R
            aggregates.add(retTreeSet("NNE", "N", "NNW", "NW", "NWW", "W", "SWW")); //C

            tileList.add(new CasualTile("P", "casual", "P" + i, //Id
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
        //Tile Q (x1)
        aggregates = new HashSet<>();
        aggregates.add(retTreeSet("SW", "SSW", "S", "SSE", "SE")); //F
        aggregates.add(retTreeSet("N", "NNE", "NE", "NEE", "E", "SEE",
                "SWW", "W", "NWW", "NW", "NNW", "CNW", "CNE", "CSW", "CSE")); //C

        tileList.add(new CasualTile("Q", "casual", "Q", //Id
                new CityType(true), new CityType(true), new CityType(true), //North West section
                new CityType(true), //North
                new CityType(true), new CityType(true), new CityType(true), //North East section
                new CityType(true), //East
                new CityType(true), new FieldType(), new FieldType(), //South East section
                new FieldType(), //South
                new FieldType(), new FieldType(), new CityType(true), //South West section
                new CityType(true), //West
                new CityType(true), new CityType(true), new CityType(true), new CityType(true), //Center section
                aggregates
        ));
        //Tiles R (x3)
        for (int i = 1; i <= 3; i++) {
            aggregates = new HashSet<>();
            aggregates.add(retTreeSet("SW", "SSW", "S", "SSE", "SE")); //F
            aggregates.add(retTreeSet("N", "NNE", "NE", "NEE", "E", "SEE",
                    "SWW", "W", "NWW", "NW", "NNW", "CNW", "CNE", "CSW", "CSE")); //C
            tileList.add(new CasualTile("R", "casual", "R" + i, //Id
                    new CityType(), new CityType(), new CityType(), //North West section
                    new CityType(), //North
                    new CityType(), new CityType(), new CityType(), //North East section
                    new CityType(), //East
                    new CityType(), new FieldType(), new FieldType(), //South East section
                    new FieldType(), //South
                    new FieldType(), new FieldType(), new CityType(), //South West section
                    new CityType(), //West
                    new CityType(), new CityType(), new CityType(), new CityType(), //Center section
                    aggregates
            ));
        }
        //Tiles S (x2)
        for (int i = 1; i <= 3; i++) {
            aggregates = new HashSet<>();
            aggregates.add(retTreeSet("SW", "SSW")); //F
            aggregates.add(retTreeSet("SSE", "SE")); //F
            aggregates.add(retTreeSet("S")); //R
            aggregates.add(retTreeSet("N", "NNE", "NE", "NEE", "E", "SEE",
                    "SWW", "W", "NWW", "NW", "NNW", "CNW", "CNE", "CSW", "CSE")); //C

            tileList.add(new CasualTile("S", "casual", "S" + i, //Id
                    new CityType(true), new CityType(true), new CityType(true), //North West section
                    new CityType(true), //North
                    new CityType(true), new CityType(true), new CityType(true), //North East section
                    new CityType(true), //East
                    new CityType(true), new FieldType(), new FieldType(), //South East section
                    new RoadType(), //South
                    new FieldType(), new FieldType(), new CityType(true), //South West section
                    new CityType(true), //West
                    new CityType(true), new CityType(true), new CityType(true), new CityType(true), //Center section
                    aggregates
            ));
        }
        //Tile T (x1)
        aggregates = new HashSet<>();
        aggregates.add(retTreeSet("SW", "SSW")); //F
        aggregates.add(retTreeSet("SSE", "SE")); //F
        aggregates.add(retTreeSet("S")); //R
        aggregates.add(retTreeSet("N", "NNE", "NE", "NEE", "E", "SEE",
                "SWW", "W", "NWW", "NW", "NNW", "CNW", "CNE", "CSW", "CSE")); //C

        tileList.add(new CasualTile("T", "casual", "T1", //Id
                new CityType(), new CityType(), new CityType(), //North West section
                new CityType(), //North
                new CityType(), new CityType(), new CityType(), //North East section
                new CityType(), //East
                new CityType(), new FieldType(), new FieldType(), //South East section
                new RoadType(), //South
                new FieldType(), new FieldType(), new CityType(), //South West section
                new CityType(), //West
                new CityType(), new CityType(), new CityType(), new CityType(), //Center section
                aggregates
        ));
        //Tiles U (x8)
        for (int i = 1; i <= 8; i++) {
            aggregates = new HashSet<>();
            aggregates.add(retTreeSet("NNW", "NW", "NWW", "W", "SWW", "SW", "SSW")); //F
            aggregates.add(retTreeSet("NNE", "NE", "NEE", "E", "SEE", "SE", "SSE")); //F
            aggregates.add(retTreeSet("N", "CNW", "CNE", "CSE", "CSW", "S")); //R

            tileList.add(new CasualTile("U", "casual", "U" + i, //Id
                    new FieldType(), new RoadType(), new FieldType(), //North section
                    new FieldType(), //East section
                    new FieldType(), new RoadType(), new FieldType(), //South section
                    new FieldType(), //West section
                    new RoadType(), new RoadType(), new RoadType(), new RoadType(), //Center section
                    aggregates
            ));
        }
        //Tiles V (x9)
        for (int i = 1; i <= 9; i++) {
            aggregates = new HashSet<>();
            aggregates.add(retTreeSet("NNW", "NW", "NWW", "N", "NNE", "NE", "NEE", "E", "SEE", "SE", "SSE", "CNW", "CNE", "CSE")); //F
            aggregates.add(retTreeSet("SWW", "SW", "SSW")); //F
            aggregates.add(retTreeSet("W", "CSW", "S")); //R

            tileList.add(new CasualTile("V", "casual", "V" + i, //Id
                    new FieldType(), new FieldType(), new FieldType(), //North section
                    new FieldType(), //East section
                    new FieldType(), new RoadType(), new FieldType(), //South section
                    new RoadType(), //West section
                    new FieldType(), new FieldType(), new FieldType(), new RoadType(), //Center section
                    aggregates
            ));
        }
        //Tiles W (x4)
        for (int i = 1; i <= 4; i++) {
            aggregates = new HashSet<>();
            aggregates.add(retTreeSet("NWW", "NW", "NNW", "N", "NNE", "NE", "NEE")); //F
            aggregates.add(retTreeSet("SWW", "SW", "SSW")); //F
            aggregates.add(retTreeSet("SSE", "SE", "SEE")); //F
            aggregates.add(retTreeSet("W")); //R
            aggregates.add(retTreeSet("S")); //R
            aggregates.add(retTreeSet("E")); //R

            tileList.add(new CasualTile("W", "casual", "W" + i, //Id
                    new FieldType(), new FieldType(), new FieldType(), //North section
                    new RoadType(), //East section
                    new FieldType(), new RoadType(), new FieldType(), //South section
                    new RoadType(), //West section
                    new CrossType(), new CrossType(), new CrossType(), new CrossType(), //Center section
                    aggregates
            ));
        }
        //Tile X (x1)
        aggregates = new HashSet<>();
        aggregates.add(retTreeSet("NWW", "NW", "NNW")); //F
        aggregates.add(retTreeSet("NNE", "NE", "NEE")); //F
        aggregates.add(retTreeSet("SWW", "SW", "SSW")); //F
        aggregates.add(retTreeSet("SSE", "SE", "SEE")); //F
        aggregates.add(retTreeSet("N")); //R
        aggregates.add(retTreeSet("W")); //R
        aggregates.add(retTreeSet("S")); //R
        aggregates.add(retTreeSet("E")); //R
        

        tileList.add(new CasualTile("X", "casual", "X1", //Id
                new FieldType(), new RoadType(), new FieldType(), //North section
                new RoadType(), //East section
                new FieldType(), new RoadType(), new FieldType(), //South section
                new RoadType(), //West section
                new CrossType(), new CrossType(), new CrossType(), new CrossType(), //Center section
                aggregates
        ));
    }
}
