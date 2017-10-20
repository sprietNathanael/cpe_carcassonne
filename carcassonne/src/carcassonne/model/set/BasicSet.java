/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.set;

import carcassonne.model.tile.AbstractTile;
import carcassonne.model.tile.CasualTile;
import carcassonne.model.type.CityType;
import carcassonne.model.type.FieldType;
import carcassonne.model.type.RoadType;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the basic set of the game
 *
 * @author Étienne
 */
public class BasicSet implements SetInterface
{

    private List<AbstractTile> tileList;

    /**
     * Initilialize the basic set with all its tiles
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
     * Add all the tiles of the extension
     */
    private void initiliazeSet()
    {
        //First Tile
        tileList.add(new CasualTile(
                new FieldType(),
                new FieldType(),
                new CityType(),
                new CityType(),
                new CityType(),
                new FieldType(),
                new FieldType(),
                new RoadType(),
                new FieldType(),
                new FieldType(),
                new FieldType(),
                new FieldType(),
                new FieldType(),
                new FieldType(),
                new FieldType(),
                new RoadType(),
                new RoadType(),
                new RoadType(),
                new RoadType(),
                new RoadType()
        ));
    }
}
