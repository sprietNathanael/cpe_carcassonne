/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.aggregate;

import carcassonne.model.board.Board;
import carcassonne.model.tile.AbstractTile;
import java.util.List;

/**
 *
 * @author Thomas
 */
public class AggregateHelper
{
    private static AbstractTile northTile;
    private static AbstractTile southTile;
    private static AbstractTile eastTile;
    private static AbstractTile westTile;
    private static AbstractTile northEastTile;
    private static AbstractTile northWestTile;
    private static AbstractTile soutEastTile;
    private static AbstractTile southWestTile;

    public static void analyseAggregate(AbstractTile currentTile, Board board, List<AbstractAggregate> abstractAggregates)
    {
        setCloseTiles(currentTile);
    }
    
    public static void setCloseTiles(AbstractTile currentTile)
    {
        
    }
}
