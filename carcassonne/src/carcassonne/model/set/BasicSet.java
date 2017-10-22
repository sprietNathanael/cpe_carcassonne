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
        //Tiles A (x3)
        for (int i = 1; i <= 3; i++) {
            tileList.add(new CasualTile("A" + i, //Id
                    new FieldType(), new FieldType(), new FieldType(), //North section
                    new FieldType(), //East section
                    new FieldType(), new RoadType(), new FieldType(), //South section
                    new FieldType(), //West section
                    new AbbayType(), new AbbayType(), new AbbayType(), new AbbayType() //Center section
            ));
        }
        //Tiles B (x4)
        for (int i = 1; i <= 4; i++) {
            tileList.add(new CasualTile("B" + i, //Id
                    new FieldType(), new FieldType(), new FieldType(), //North section
                    new FieldType(), //East section
                    new FieldType(), new RoadType(), new FieldType(), //South section
                    new FieldType(), //West section
                    new AbbayType(), new AbbayType(), new AbbayType(), new AbbayType() //Center section
            ));
        }
        //Tile C (x1)
        tileList.add(new CasualTile("C", //Id
                new CityType(true), new CityType(true), new CityType(true), //North section
                new CityType(true), //East section
                new CityType(true), new CityType(true), new CityType(true), //South section
                new CityType(true), //West section
                new CityType(true), new CityType(true), new CityType(true), new CityType(true) //Center section
        ));

        /* @TODO: ajouter les autres plus complexes (D à T) */
        //Tiles U (x8)
        for (int i = 1; i <= 8; i++) {
            tileList.add(new CasualTile("U" + i, //Id
                    new FieldType(), new RoadType(), new FieldType(), //North section
                    new FieldType(), //East section
                    new FieldType(), new RoadType(), new FieldType(), //South section
                    new FieldType(), //West section
                    new RoadType(), new RoadType(), new RoadType(), new RoadType() //Center section
            ));
        }
        //Tiles V (x9)
        for (int i = 1; i <= 9; i++) {
            tileList.add(new CasualTile("V" + i, //Id
                    new FieldType(), new FieldType(), new FieldType(), //North section
                    new FieldType(), //East section
                    new FieldType(), new RoadType(), new FieldType(), //South section
                    new RoadType(), //West section
                    new FieldType(), new FieldType(), new FieldType(), new RoadType() //Center section
            ));
        }
        //Tiles W (x4)
        for (int i = 1; i <= 4; i++) {
            tileList.add(new CasualTile("W" + i, //Id
                    new FieldType(), new FieldType(), new FieldType(), //North section
                    new RoadType(), //East section
                    new FieldType(), new RoadType(), new FieldType(), //South section
                    new RoadType(), //West section
                    new CrossType(), new CrossType(), new CrossType(), new CrossType() //Center section
            ));
        }
        //Tile X (x1)
        tileList.add(new CasualTile("X", //Id
                new FieldType(), new RoadType(), new FieldType(), //North section
                new RoadType(), //East section
                new FieldType(), new RoadType(), new FieldType(), //South section
                new RoadType(), //West section
                new CrossType(), new CrossType(), new CrossType(), new CrossType() //Center section
        ));
    }
}
