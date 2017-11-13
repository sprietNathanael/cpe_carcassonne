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
        //Tiles A (x2)
        for (int i = 1; i <= 2; i++) {
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
                    new FieldType(), new FieldType(), new FieldType(), //South section
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
        //Tiles D (x4)
        for (int i = 1; i <= 4; i++) {
            tileList.add(new CasualTile("D" + i, //Id
                    new FieldType(), new FieldType(), new FieldType(), //North West section
                    new RoadType(), //North
                    new FieldType(), new FieldType(), new CityType(), //North East section
                    new CityType(), //East
                    new CityType(), new FieldType(), new FieldType(), //South East section
                    new RoadType(), //South
                    new FieldType(), new FieldType(), new FieldType(), //South West section
                    new FieldType(), //West
                    new RoadType(), new FieldType(), new FieldType(), new RoadType() //Center section
            ));
        }
        //Tiles E (x5)
        for (int i = 1; i <= 5; i++) {
            tileList.add(new CasualTile("E" + i, //Id
                    new FieldType(), new FieldType(), new CityType(), //North West section
                    new CityType(), //North
                    new CityType(), new FieldType(), new FieldType(), //North East section
                    new FieldType(), //East
                    new FieldType(), new FieldType(), new FieldType(), //South East section
                    new FieldType(), //South
                    new FieldType(), new FieldType(), new FieldType(), //South West section
                    new FieldType(), //West
                    new FieldType(), new FieldType(), new FieldType(), new FieldType() //Center section
            ));
        }
        //Tiles F (x2)
        for (int i = 1; i <= 2; i++) {
            tileList.add(new CasualTile("F" + i, //Id
                    new CityType(true), new FieldType(), new FieldType(), //North West section
                    new FieldType(), //North
                    new FieldType(), new FieldType(), new CityType(true), //North East section
                    new CityType(true), //East
                    new CityType(true), new FieldType(), new FieldType(), //South East section
                    new FieldType(), //South
                    new FieldType(), new FieldType(), new CityType(true), //South West section
                    new CityType(true), //West
                    new CityType(true), new CityType(true), new CityType(true), new CityType(true) //Center section
            ));
        }
        //Tile G (x1)
        tileList.add(new CasualTile("G", //Id
                new FieldType(), new FieldType(), new CityType(), //North West section
                new CityType(), //North
                new CityType(), new FieldType(), new FieldType(), //North East section
                new FieldType(), //East
                new FieldType(), new FieldType(), new CityType(), //South East section
                new CityType(), //South
                new CityType(), new FieldType(), new FieldType(), //South West section
                new FieldType(), //West
                new CityType(), new CityType(), new CityType(), new CityType() //Center section
        ));
        //Tiles H (x3)
        for (int i = 1; i <= 3; i++) {
            tileList.add(new CasualTile("H" + i, //Id
                    new CityType(), new FieldType(), new FieldType(), //North West section
                    new FieldType(), //North
                    new FieldType(), new FieldType(), new CityType(), //North East section
                    new CityType(), //East
                    new CityType(), new FieldType(), new FieldType(), //South East section
                    new FieldType(), //South
                    new FieldType(), new FieldType(), new CityType(), //South West section
                    new CityType(), //West
                    new FieldType(), new FieldType(), new FieldType(), new FieldType() //Center section
            ));
        }
        //Tiles I (x2)
        for (int i = 1; i <= 2; i++) {
            tileList.add(new CasualTile("I" + i, //Id
                    new FieldType(), new FieldType(), new FieldType(), //North West section
                    new FieldType(), //North
                    new FieldType(), new FieldType(), new CityType(), //North East section
                    new CityType(), //East
                    new CityType(), new FieldType(), new CityType(), //South East section
                    new CityType(), //South
                    new CityType(), new FieldType(), new FieldType(), //South West section
                    new FieldType(), //West
                    new FieldType(), new FieldType(), new FieldType(), new FieldType() //Center section
            ));
        }
        //Tiles J (x3)
        for (int i = 1; i <= 3; i++) {
            tileList.add(new CasualTile("J" + i, //Id
                    new FieldType(), new FieldType(), new CityType(), //North West section
                    new CityType(), //North
                    new CityType(), new FieldType(), new FieldType(), //North East section
                    new RoadType(), //East
                    new FieldType(), new FieldType(), new FieldType(), //South East section
                    new RoadType(), //South
                    new FieldType(), new FieldType(), new FieldType(), //South West section
                    new FieldType(), //West
                    new FieldType(), new FieldType(), new RoadType(), new FieldType() //Center section
            ));
        }
        //Tiles K (x3)
        for (int i = 1; i <= 3; i++) {
            tileList.add(new CasualTile("K" + i, //Id
                    new FieldType(), new FieldType(), new FieldType(), //North West section
                    new RoadType(), //North
                    new FieldType(), new FieldType(), new CityType(), //North East section
                    new CityType(), //East
                    new CityType(), new FieldType(), new FieldType(), //South East section
                    new FieldType(), //South
                    new FieldType(), new FieldType(), new FieldType(), //South West section
                    new RoadType(), //West
                    new RoadType(), new FieldType(), new FieldType(), new FieldType() //Center section
            ));
        }
        //Tiles L (x3)
        for (int i = 1; i <= 3; i++) {
            tileList.add(new CasualTile("L" + i, //Id
                    new FieldType(), new FieldType(), new FieldType(), //North West section
                    new RoadType(), //North
                    new FieldType(), new FieldType(), new CityType(), //North East section
                    new CityType(), //East
                    new CityType(), new FieldType(), new FieldType(), //South East section
                    new RoadType(), //South
                    new FieldType(), new FieldType(), new FieldType(), //South West section
                    new RoadType(), //West
                    new CrossType(), new FieldType(), new FieldType(), new CrossType() //Center section
            ));
        }
        //Tiles M (x2)
        for (int i = 1; i <= 2; i++) {
            tileList.add(new CasualTile("M" + i, //Id
                    new CityType(true), new CityType(true), new CityType(true), //North West section
                    new CityType(true), //North
                    new CityType(true), new FieldType(), new FieldType(), //North East section
                    new FieldType(), //East
                    new FieldType(), new FieldType(), new FieldType(), //South East section
                    new FieldType(), //South
                    new FieldType(), new FieldType(), new CityType(true), //South West section
                    new CityType(true), //West
                    new FieldType(), new FieldType(), new FieldType(), new FieldType() //Center section
            ));
        }
        //Tiles N (x3)
        for (int i = 1; i <= 3; i++) {
            tileList.add(new CasualTile("N" + i, //Id
                    new CityType(), new CityType(), new CityType(), //North West section
                    new CityType(), //North
                    new CityType(), new FieldType(), new FieldType(), //North East section
                    new FieldType(), //East
                    new FieldType(), new FieldType(), new FieldType(), //South East section
                    new FieldType(), //South
                    new FieldType(), new FieldType(), new CityType(), //South West section
                    new CityType(), //West
                    new FieldType(), new FieldType(), new FieldType(), new FieldType() //Center section
            ));
        }
        //Tiles O (x2)
        for (int i = 1; i <= 2; i++) {
            tileList.add(new CasualTile("O" + i, //Id
                    new CityType(true), new CityType(true), new CityType(true), //North West section
                    new CityType(true), //North
                    new CityType(true), new FieldType(), new FieldType(), //North East section
                    new RoadType(), //East
                    new FieldType(), new FieldType(), new FieldType(), //South East section
                    new RoadType(), //South
                    new FieldType(), new FieldType(), new CityType(true), //South West section
                    new CityType(true), //West
                    new FieldType(), new FieldType(), new RoadType(), new FieldType() //Center section
            ));
        }
        //Tiles P (x3)
        for (int i = 1; i <= 3; i++) {
            tileList.add(new CasualTile("P" + i, //Id
                    new CityType(), new CityType(), new CityType(), //North West section
                    new CityType(), //North
                    new CityType(), new FieldType(), new FieldType(), //North East section
                    new RoadType(), //East
                    new FieldType(), new FieldType(), new FieldType(), //South East section
                    new RoadType(), //South
                    new FieldType(), new FieldType(), new CityType(), //South West section
                    new CityType(), //West
                    new FieldType(), new FieldType(), new RoadType(), new FieldType() //Center section
            ));
        }
        //Tile Q (x1)
        tileList.add(new CasualTile("Q", //Id
                new CityType(true), new CityType(true), new CityType(true), //North West section
                new CityType(true), //North
                new CityType(true), new CityType(true), new CityType(true), //North East section
                new CityType(true), //East
                new CityType(true), new FieldType(), new FieldType(), //South East section
                new FieldType(), //South
                new FieldType(), new FieldType(), new CityType(true), //South West section
                new CityType(true), //West
                new CityType(true), new CityType(true), new CityType(true), new CityType(true) //Center section
        ));
        //Tiles R (x3)
        for (int i = 1; i <= 3; i++) {
            tileList.add(new CasualTile("R" + i, //Id
                    new CityType(), new CityType(), new CityType(), //North West section
                    new CityType(), //North
                    new CityType(), new CityType(), new CityType(), //North East section
                    new CityType(), //East
                    new CityType(), new FieldType(), new FieldType(), //South East section
                    new FieldType(), //South
                    new FieldType(), new FieldType(), new CityType(), //South West section
                    new CityType(), //West
                    new CityType(), new CityType(), new CityType(), new CityType() //Center section
            ));
        }
        //Tiles S (x2)
        for (int i = 1; i <= 3; i++) {
            tileList.add(new CasualTile("S" + i, //Id
                    new CityType(true), new CityType(true), new CityType(true), //North West section
                    new CityType(true), //North
                    new CityType(true), new CityType(true), new CityType(true), //North East section
                    new CityType(true), //East
                    new CityType(true), new FieldType(), new FieldType(), //South East section
                    new RoadType(), //South
                    new FieldType(), new FieldType(), new CityType(true), //South West section
                    new CityType(true), //West
                    new CityType(true), new CityType(true), new CityType(true), new CityType(true) //Center section
            ));
        }
        //Tile T (x1)
        tileList.add(new CasualTile("T", //Id
                new CityType(), new CityType(), new CityType(), //North West section
                new CityType(), //North
                new CityType(), new CityType(), new CityType(), //North East section
                new CityType(), //East
                new CityType(), new FieldType(), new FieldType(), //South East section
                new RoadType(), //South
                new FieldType(), new FieldType(), new CityType(), //South West section
                new CityType(), //West
                new CityType(), new CityType(), new CityType(), new CityType() //Center section
        ));
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
