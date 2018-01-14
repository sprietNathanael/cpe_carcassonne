/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.set;

import carcassonne.model.tile.AbstractTile;
import carcassonne.model.tile.CasualTile;
import carcassonne.model.type.AbbayType;
import carcassonne.model.type.CrossType;
import carcassonne.model.type.FieldType;
import carcassonne.model.type.RoadType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Bertrand
 */
public class InnsAndCathedralsSet implements SetInterface
{
    private final List<AbstractTile> tileList;
    private AbstractTile firstTile;
    
    public InnsAndCathedralsSet ()
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
                new FieldType(), new FieldType(), new FieldType(), new RoadType(), //Center section
                aggregates
        ));
        
        aggregates = new HashSet<>();
        aggregates.add(retTreeSet("NWW", "NW", "NNW", "N", "NNE", "NE", "NEE")); //F
        aggregates.add(retTreeSet("SWW", "SW", "SSW", "S", "SSE", "SE", "SEE")); //F
        aggregates.add(retTreeSet("W", "CNW", "CNE", "CSE", "CSW", "E")); //R

        //Tile EB
        tileList.add(new CasualTile("EB", "inncathedral", "EB", //Id
                new FieldType(), new FieldType(), new FieldType(), //North section
                new RoadType(), //East section
                new FieldType(), new FieldType(), new FieldType(), //South section
                new RoadType(), //West section
                new RoadType(), new RoadType(), new RoadType(), new RoadType(), //Center section
                aggregates
        ));
        
        aggregates = new HashSet<>();
        aggregates.add(retTreeSet("NWW", "NW", "NNW", "N", "NNE", "NE", "NEE")); //F
        aggregates.add(retTreeSet("SWW", "SW", "SSW")); //F
        aggregates.add(retTreeSet("SSE", "SE", "SEE")); //F
        aggregates.add(retTreeSet("W")); //R
        aggregates.add(retTreeSet("S")); //R
        aggregates.add(retTreeSet("E")); //R

        tileList.add(new CasualTile("EC", "inncathedral", "EC", //Id
                new FieldType(), new FieldType(), new FieldType(), //North section
                new RoadType(), //East section
                new FieldType(), new RoadType(), new FieldType(), //South section
                new RoadType(), //West section
                new CrossType(), new CrossType(), new CrossType(), new CrossType(), //Center section
                aggregates
        ));
        
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
        
        
    }
    
    public static Set<String> retTreeSet(String... poss)
    {
        Set<String> tsPos = new HashSet<>();
        tsPos.addAll(Arrays.asList(poss));        
        return tsPos;
    }
}
