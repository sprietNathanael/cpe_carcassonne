/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.aggregate;

import carcassonne.model.tile.AbstractTile;
import java.util.Set;

/**
 *
 *
 *
 * @author Thomas
 *
 */
public class AbbayAggregate extends AbstractAggregate
{

    public AbbayAggregate(int col, int row, AbstractTile firstTile, Set<String> locationTypes)
    {
        super(col, row, firstTile, locationTypes);
    }

    @Override
    public boolean checkIsCompleted()
    {
        return this.aggregatedTiles.size() == 9;
    }

    @Override
    public int countPoints()
    {
        return this.aggregatedTiles.size();
    }
}
