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
import carcassonne.model.type.Building;
import carcassonne.model.type.CityType;
import carcassonne.model.type.CrossType;
import carcassonne.model.type.FieldType;
import carcassonne.model.type.RoadType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This is the set for the expansion "Inns and Cathedrals"
 *
 * @author Bertrand
 */
public class InnsAndCathedralsSet extends AbstractSet
{

    /**
     * Initilializes the inns and cathedrals set with all its tiles
     */
    public InnsAndCathedralsSet()
    {
        super();
        this.initiliazeSet();
        this.meeples.add(new Meeple(true, null));
    }

    /**
     * Adds all the tiles of the extension
     */
    @Override
    protected void initiliazeSet()
    {
        Set<Set<String>> aggregates;
        //Tile EA
        aggregates = new HashSet<>();
        aggregates.add(retTreeSet("NNW", "NW", "NWW", "N", "NNE", "NE", "NEE", "E", "SEE", "SE", "SSE", "CNW", "CNE", "CSE")); //F
        aggregates.add(retTreeSet("SWW", "SW", "SSW")); //F
        aggregates.add(retTreeSet("W", "CSW", "S")); //R

        tileList.add(new CasualTile("EA", "inncathedral", "EA", //Id
                new FieldType(), new FieldType(), new FieldType(), //North section
                new FieldType(), //East section
                new FieldType(), new RoadType(), new FieldType(), //South section
                new RoadType(), //West section
                new FieldType(), new FieldType(Building.inn), new FieldType(), new RoadType(), //Center section
                aggregates
        ));

        aggregates = new HashSet<>();
        aggregates.add(retTreeSet("NWW", "NW", "NNW", "N", "NNE", "NE", "NEE")); //F
        aggregates.add(retTreeSet("SWW", "SW", "SSW", "S", "SSE", "SE", "SEE")); //F
        aggregates.add(retTreeSet("W", "CNW", "CNE", "CSE", "CSW", "E")); //R

        //Tile EB
        tileList.add(new CasualTile("EB", "inncathedral", "EB", //Id
                new FieldType(), new FieldType(Building.inn), new FieldType(), //North section
                new RoadType(), //East section
                new FieldType(), new FieldType(), new FieldType(), //South section
                new RoadType(), //West section
                new RoadType(), new RoadType(), new RoadType(), new RoadType(), //Center section
                aggregates
        ));

        //Tile EC
        aggregates = new HashSet<>();
        aggregates.add(retTreeSet("NWW", "NW", "NNW", "N", "NNE", "NE", "NEE")); //F
        aggregates.add(retTreeSet("SWW", "SW", "SSW")); //F
        aggregates.add(retTreeSet("SSE", "SE", "SEE")); //F
        aggregates.add(retTreeSet("W")); //R
        aggregates.add(retTreeSet("S")); //R
        aggregates.add(retTreeSet("E")); //R

        tileList.add(new CasualTile("EC", "inncathedral", "EC", //Id
                new FieldType(), new FieldType(Building.inn), new FieldType(), //North section
                new RoadType(), //East section
                new FieldType(), new RoadType(), new FieldType(), //South section
                new RoadType(), //West section
                new CrossType(), new CrossType(), new CrossType(), new CrossType(), //Center section
                aggregates
        ));

        //Tile ED
        aggregates = new HashSet<>();
        aggregates.add(retTreeSet("NWW", "NW", "NNW", "N", "NNE", "NE", "NEE")); //F
        aggregates.add(retTreeSet("CNW", "CNE", "CSW", "CSE")); //A
        aggregates.add(retTreeSet("SWW", "SW", "SSW", "S", "SSE", "SE", "SEE")); //F
        aggregates.add(retTreeSet("W")); //R
        aggregates.add(retTreeSet("E")); //R

        tileList.add(new CasualTile("ED", "inncathedral", "ED", //Id
                new FieldType(), new FieldType(), new FieldType(), //North section
                new RoadType(), //East section
                new FieldType(), new FieldType(), new FieldType(), //South section
                new RoadType(), //West section
                new AbbayType(), new AbbayType(), new AbbayType(), new AbbayType(), //Center section
                aggregates
        ));

        //Tile EE
        aggregates = new HashSet<>();
        aggregates.add(retTreeSet("NWW", "NW", "NNW")); //F
        aggregates.add(retTreeSet("W", "CNW", "N")); //R
        aggregates.add(retTreeSet("SWW", "SW", "SSW", "NNE", "NE", "NEE", "CNE", "CSW")); //F
        aggregates.add(retTreeSet("S", "CSE", "E")); //R        
        aggregates.add(retTreeSet("SSE", "SE", "SEE")); //F

        tileList.add(new CasualTile("EE", "inncathedral", "EE", //Id
                new FieldType(), new RoadType(), new FieldType(), //North section
                new RoadType(), //East section
                new FieldType(), new RoadType(), new FieldType(), //South section
                new RoadType(), //West section
                new RoadType(), new FieldType(), new FieldType(), new RoadType(), //Center section
                aggregates
        ));

        //Tile EF
        aggregates = new HashSet<>();
        aggregates.add(retTreeSet("NE", "NEE")); //F
        aggregates.add(retTreeSet("CSW", "CSE", "CNW", "CNE", "E")); //R
        aggregates.add(retTreeSet("SEE", "SE", "SSE", "S", "SSW", "SW")); //F
        aggregates.add(retTreeSet("NNE", "N", "NNW", "NW", "NWW", "SWW", "W")); //C        

        tileList.add(new CasualTile("EF", "inncathedral", "EF", //Id
                new CityType(), new CityType(), new CityType(), //North West section
                new CityType(), //North
                new CityType(), new FieldType(), new FieldType(), //North East section
                new RoadType(), //East
                new FieldType(), new FieldType(), new FieldType(), //South East section
                new FieldType(), //South
                new FieldType(), new FieldType(), new CityType(), //South West section
                new CityType(), //West
                new RoadType(), new RoadType(), new RoadType(), new RoadType(), //Center section
                aggregates
        ));

        //Tile EG
        aggregates = new HashSet<>();
        aggregates.add(retTreeSet("NWW", "NW", "W", "CSW", "SWW", "SW", "SSW", "S", "SSE")); //F
        aggregates.add(retTreeSet("NNW", "N", "NNE", "CNW", "CNE", "CSE", "SE")); //C
        aggregates.add(retTreeSet("NE", "NEE", "E", "SEE")); //F

        tileList.add(new CasualTile("EG", "inncathedral", "EG", //Id
                new FieldType(), new FieldType(), new CityType(), //North West section
                new CityType(), //North
                new CityType(), new FieldType(), new FieldType(), //North East section
                new FieldType(), //East
                new FieldType(), new CityType(), new FieldType(), //South East section
                new FieldType(), //South
                new FieldType(), new FieldType(), new FieldType(), //South West section
                new FieldType(), //West
                new CityType(), new CityType(), new CityType(), new FieldType(), //Center section
                aggregates
        ));

        //Tile EH
        aggregates = new HashSet<>();
        aggregates.add(retTreeSet("NW", "NE", "SW", "SE", "CNW", "CNE", "CSW", "CSE")); //F
        aggregates.add(retTreeSet("NNW", "N", "NNE")); //C
        aggregates.add(retTreeSet("NEE", "E", "SEE")); //C
        aggregates.add(retTreeSet("SSW", "S", "SSE")); //C
        aggregates.add(retTreeSet("NWW", "W", "SWW")); //C

        tileList.add(new CasualTile("EH", "inncathedral", "EH", //Id
                new CityType(), new FieldType(), new CityType(), //North West section
                new CityType(), //North
                new CityType(), new FieldType(), new CityType(), //North East section
                new CityType(), //East
                new CityType(), new FieldType(), new CityType(), //South East section
                new CityType(), //South
                new CityType(), new FieldType(), new CityType(), //South West section
                new CityType(), //West
                new FieldType(), new FieldType(), new FieldType(), new FieldType(), //Center section
                aggregates
        ));

        //Tile EI
        aggregates = new HashSet<>();
        aggregates.add(retTreeSet("NW", "NWW")); //F
        aggregates.add(retTreeSet("NNW", "N", "NNE")); //C
        aggregates.add(retTreeSet("NE", "NEE")); //F        
        aggregates.add(retTreeSet("W")); //R
        aggregates.add(retTreeSet("E")); //R
        aggregates.add(retTreeSet("SW", "SWW")); //F
        aggregates.add(retTreeSet("SSW", "S", "SSE")); //C
        aggregates.add(retTreeSet("SE", "SEE")); //F 

        tileList.add(new CasualTile("EI", "inncathedral", "EI", //Id
                new FieldType(), new FieldType(), new CityType(), //North West section
                new CityType(), //North
                new CityType(), new FieldType(), new FieldType(), //North East section
                new RoadType(), //East
                new FieldType(), new FieldType(), new CityType(), //South East section
                new CityType(), //South
                new CityType(), new FieldType(), new FieldType(), //South West section
                new RoadType(), //West
                new CrossType(), new CrossType(), new CrossType(), new CrossType(), //Center section
                aggregates
        ));

        //Tile EJ
        aggregates = new HashSet<>();
        aggregates.add(retTreeSet("NNW", "N", "NNE")); //C
        aggregates.add(retTreeSet("NW", "NWW", "W", "SWW", "SW", "SSW")); //F
        aggregates.add(retTreeSet("SSE", "SE", "SEE", "E", "NEE", "NE")); //F
        aggregates.add(retTreeSet("CSW", "CSE", "CNW", "CNE", "S")); //R

        tileList.add(new CasualTile("EJ", "inncathedral", "EJ", //Id
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

        //Tiles EK (x2)
        for (int i = 1; i <= 2; i++) {
            aggregates = new HashSet<>();
            aggregates.add(retTreeSet("N", "NNE", "NE", "NEE", "E", "SSE", "SE", "SEE", "S",
                    "SSW", "SW", "SWW", "W", "NWW", "NW", "NNW", "CNW", "CNE", "CSE", "CSW")); //C

            tileList.add(new CasualTile("EK", "inncathedral", "EK" + i, //Id
                    new CityType(), new CityType(), new CityType(), //North section
                    new CityType(), //East section
                    new CityType(), new CityType(), new CityType(), //South section
                    new CityType(), //West section
                    new CityType(Building.cathedral), new CityType(Building.cathedral), new CityType(Building.cathedral), new CityType(Building.cathedral), //Center section
                    aggregates
            ));
        }

        //Tile EL
        aggregates = new HashSet<>();
        aggregates.add(retTreeSet("NNE", "N", "NNW", "NW", "NWW", "SWW", "W")); //C
        aggregates.add(retTreeSet("SSW", "SW", "CSW", "CNW", "CNE", "NE", "NEE")); //F        
        aggregates.add(retTreeSet("S", "CSE", "E")); //R
        aggregates.add(retTreeSet("SEE", "SE", "SSE")); //F

        tileList.add(new CasualTile("EL", "inncathedral", "EL", //Id
                new CityType(true), new CityType(true), new CityType(true), //North West section
                new CityType(true), //North
                new CityType(true), new FieldType(Building.inn), new FieldType(Building.inn), //North East section
                new RoadType(), //East
                new FieldType(), new FieldType(), new FieldType(), //South East section
                new RoadType(), //South
                new FieldType(), new FieldType(), new CityType(true), //South West section
                new CityType(true), //West
                new FieldType(), new FieldType(), new RoadType(), new FieldType(), //Center section
                aggregates
        ));

        //Tile EM
        aggregates = new HashSet<>();
        aggregates.add(retTreeSet("NNW", "N", "NNE")); //C
        aggregates.add(retTreeSet("NW", "NWW", "CNW", "CNE", "CSE", "SSE", "SE", "SEE", "E", "NEE", "NE")); //F
        aggregates.add(retTreeSet("W", "CSW", "S")); //R        
        aggregates.add(retTreeSet("SWW", "SW", "SSW")); //F

        tileList.add(new CasualTile("EM", "inncathedral", "EM", //Id
                new FieldType(), new FieldType(), new CityType(), //North West section
                new CityType(), //North
                new CityType(), new FieldType(), new FieldType(), //North East section
                new FieldType(), //East
                new FieldType(Building.inn), new FieldType(Building.inn), new FieldType(Building.inn), //South East section
                new RoadType(), //South
                new FieldType(), new FieldType(), new FieldType(), //South West section
                new RoadType(), //West
                new FieldType(), new FieldType(), new FieldType(), new RoadType(), //Center section
                aggregates
        ));

        //Tile EN
        aggregates = new HashSet<>();
        aggregates.add(retTreeSet("NNE", "N", "NNW", "NW", "NWW", "SWW", "W")); //C
        aggregates.add(retTreeSet("NE", "NEE", "E", "SSE", "SE", "SEE")); //F        
        aggregates.add(retTreeSet("S", "CSE", "CSW", "CNW", "CNE")); //R
        aggregates.add(retTreeSet("SSW", "SW")); //F

        tileList.add(new CasualTile("EN", "inncathedral", "EN", //Id
                new CityType(), new CityType(), new CityType(), //North West section
                new CityType(), //North
                new CityType(), new FieldType(), new FieldType(), //North East section
                new FieldType(Building.inn), //East
                new FieldType(), new FieldType(), new FieldType(), //South East section
                new RoadType(), //South
                new FieldType(), new FieldType(), new CityType(), //South West section
                new CityType(), //West
                new RoadType(), new RoadType(), new RoadType(), new RoadType(), //Center section
                aggregates
        ));

        //Tile EO
        aggregates = new HashSet<>();
        aggregates.add(retTreeSet("NW", "NE", "SW", "SE", "CNW", "CNE", "CSW", "CSE", "SSW", "S", "SSE")); //F
        aggregates.add(retTreeSet("NNW", "N", "NNE")); //C
        aggregates.add(retTreeSet("NEE", "E", "SEE")); //C
        aggregates.add(retTreeSet("NWW", "W", "SWW")); //C

        tileList.add(new CasualTile("EO", "inncathedral", "EO", //Id
                new CityType(), new FieldType(), new CityType(), //North West section
                new CityType(), //North
                new CityType(), new FieldType(), new CityType(), //North East section
                new CityType(), //East
                new CityType(), new FieldType(), new FieldType(), //South East section
                new FieldType(), //South
                new FieldType(), new FieldType(), new CityType(), //South West section
                new CityType(), //West
                new FieldType(), new FieldType(), new FieldType(), new FieldType(), //Center section
                aggregates
        ));

        //Tile EP
        aggregates = new HashSet<>();
        aggregates.add(retTreeSet("NE", "NEE", "E", "SEE", "SE", "SW", "CSW", "CSE", "CNW", "CNE")); //F
        aggregates.add(retTreeSet("NNE", "N", "NNW", "NW", "NWW", "W", "SWW")); //C
        aggregates.add(retTreeSet("SSW", "S", "SSE")); //C

        tileList.add(new CasualTile("EP", "inncathedral", "EP", //Id
                new CityType(true), new CityType(true), new CityType(true), //North West section
                new CityType(true), //North
                new CityType(true), new FieldType(), new FieldType(), //North East section
                new FieldType(), //East
                new FieldType(), new FieldType(), new CityType(), //South East section
                new CityType(), //South
                new CityType(), new FieldType(), new CityType(true), //South West section
                new CityType(true), //West
                new FieldType(), new FieldType(), new FieldType(), new FieldType(), //Center section
                aggregates
        ));

        //Tile EQ
        aggregates = new HashSet<>();
        aggregates.add(retTreeSet("N")); //R
        aggregates.add(retTreeSet("NW", "NNW")); //F
        aggregates.add(retTreeSet("NNE", "NE")); //F
        aggregates.add(retTreeSet("NWW", "W", "SWW", "CSW", "CSE", "CNW", "CNE", "SEE", "E", "NEE")); //C        
        aggregates.add(retTreeSet("SSE", "SE")); //F
        aggregates.add(retTreeSet("SW", "SSW")); //F
        aggregates.add(retTreeSet("S")); //R        

        tileList.add(new CasualTile("EQ", "inncathedral", "EQ", //Id
                new CityType(true), new FieldType(), new FieldType(), //North West section
                new RoadType(), //North
                new FieldType(), new FieldType(), new CityType(true), //North East section
                new CityType(true), //East
                new CityType(true), new FieldType(), new FieldType(), //South East section
                new RoadType(), //South
                new FieldType(), new FieldType(), new CityType(true), //South West section
                new CityType(true), //West
                new CityType(true), new CityType(true), new CityType(true), new CityType(true), //Center section
                aggregates
        ));
    }
}
