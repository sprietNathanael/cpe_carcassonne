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
public enum CityEdgeEnum
{
    NORTH, EAST, SOUTH, WEST;

    public static CityEdgeEnum getOpposite(CityEdgeEnum edge)
    {
        CityEdgeEnum oppositeEdge = null;

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

    public static String convertToString(CityEdgeEnum cityEdge)
    {
        String typeCoordinate = null;

        switch (cityEdge) {
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
