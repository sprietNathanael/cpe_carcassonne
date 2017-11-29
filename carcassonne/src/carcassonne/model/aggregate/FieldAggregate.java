/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.aggregate;

import carcassonne.model.player.Meeple;
import carcassonne.model.player.Player;
import carcassonne.model.tile.AbstractTile;
import java.util.Set;

/**
 *
 * @author Étienne
 */
public class FieldAggregate extends AbstractAggregate
{

    /**
     * Construct a field aggregation
     *
     * @param col
     * @param row
     * @param firstTile
     * @param locationtypes
     */
    public FieldAggregate(int col, int row, AbstractTile firstTile, Set<String> locationtypes)
    {
        super(col, row, firstTile, locationtypes);
    }

    /**
     * Construct a field aggregation
     *
     * @param col
     * @param row
     * @param firstTile
     * @param locationtypes
     * @param player
     * @param meeple
     */
    public FieldAggregate(int col, int row, AbstractTile firstTile, Set<String> locationtypes, Player player, Meeple meeple)
    {
        super(col, row, firstTile, locationtypes, player, meeple);
    }

    /**
     * @return false in any case, because a field is never completed
     */
    @Override
    public boolean checkIsCompleted()
    {
        boolean result = isCompleted;

        if (!result) {
            this.isCompleted = false;
        }

        return result;
    }

}