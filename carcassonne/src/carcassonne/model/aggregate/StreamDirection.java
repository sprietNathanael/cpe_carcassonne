/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.aggregate;

import carcassonne.coord.Coord;

/**
 *
 * @author Étienne
 */
public enum StreamDirection
{
    NORTH, EAST, SOUTH, WEST;

    public static StreamDirection getStreamDirection(int col, int row)
    {
        StreamDirection stream = null;

        switch (col) {
            case 1:
                stream = WEST;
                break;
            case -1:
                stream = EAST;
                break;
        }

        switch (row) {
            case 1:
                stream = NORTH;
                break;
            case -1:
                stream = SOUTH;
                break;
        }

        return stream;
    }

    public static StreamDirection getStreamDirectionLocation(String location)
    {
        StreamDirection stream = null;

        switch (location) {
            case "S":
                stream = SOUTH;
                break;
            case "N":
                stream = NORTH;
                break;
            case "W":
                stream = WEST;
                break;
            case "E":
                stream = EAST;
                break;
        }

        return stream;
    }

    public static boolean checkCorrectSreamDirection(Coord iniCoord, Coord finalCoord, StreamDirection stream)
    {
        Coord diffCoord = new Coord(iniCoord.col - finalCoord.col, iniCoord.row - finalCoord.row);
        Coord unExpectedDiff = null;

        switch (stream) {
            case WEST:
                unExpectedDiff = new Coord(1, 0);
                break;
            case EAST:
                unExpectedDiff = new Coord(-1, 0);
                break;
            case NORTH:
                unExpectedDiff = new Coord(0, -1);
                break;
            case SOUTH:
                unExpectedDiff = new Coord(0, 1);
                break;
        }

        return unExpectedDiff != diffCoord;
    }

    /**
     * Check si on dirige la rivière dans le bon sens
     * @param stream
     * @param location
     * @return 
     */
    public static boolean checkCorrectSreamDirection(StreamDirection stream, String location)
    {
        String unExpectedDir = "";

        switch (stream) {
            case WEST:
                unExpectedDir = "E";
                break;
            case EAST:
                unExpectedDir = "W";
                break;
            case NORTH:
                unExpectedDir = "S";
                break;
            case SOUTH:
                unExpectedDir = "E";
                break;
        }

        return !unExpectedDir.equals(location);
    }
}
