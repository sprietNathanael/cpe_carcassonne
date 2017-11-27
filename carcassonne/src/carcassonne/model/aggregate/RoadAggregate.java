/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.aggregate;

import carcassonne.model.player.Player;
import carcassonne.model.tile.AbstractTile;
import carcassonne.model.type.AbstractType;
import java.util.List;

/**
 * @author Étienne
 */
public class RoadAggregate extends AbstractAggregate
{

    /**
     * Construct a road aggregation
     *
     * @param row
     * @param col
     * @param firstTile
     * @param types
     */
    public RoadAggregate(int row, int col, AbstractTile firstTile, List<String> types)
    {
        super(row, col, firstTile, types);
    }

    /**
     * Construct a road aggregation
     *
     * @param row
     * @param col
     * @param firstTile
     * @param types
     * @param player
     * @param meepleValue
     */
    public RoadAggregate(int row, int col, AbstractTile firstTile, List<String> types, Player player, int meepleValue)
    {
        super(row, col, firstTile, types, player, meepleValue);
    }

    /**
     * TODO: Gérer les tests pour savoir si une route est terminée
     *
     * @return True if the aggregation is completed
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
