/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.aggregate;

/**
 *
 * @author Étienne
 */
public enum RoadEdgeEnum
{
    NORTH, EAST, SOUTH, WEST;

    public static RoadEdgeEnum getOpposite(RoadEdgeEnum edge)
    {
        RoadEdgeEnum oppositeEdge = null;

        switch (edge) {
            case NORTH:
                oppositeEdge = SOUTH;
                break;
            case EAST:
                oppositeEdge = WEST;
                break;
            case SOUTH:
                oppositeEdge = NORTH;
                break;
            case WEST:
                oppositeEdge = EAST;
                break;
        }

        return oppositeEdge;
    }

    public static String convertToString(RoadEdgeEnum roadEdge)
    {
        String typeCoordinate = null;

        switch (roadEdge) {
            case NORTH:
                typeCoordinate = "N";
                break;
            case EAST:
                typeCoordinate = "E";
                break;
            case SOUTH:
                typeCoordinate = "S";
                break;
            case WEST:
                typeCoordinate = "W";
                break;
        }

        return typeCoordinate;
    }
}
