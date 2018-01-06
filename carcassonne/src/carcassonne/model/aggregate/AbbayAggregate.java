/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.aggregate;

import carcassonne.coord.Coord;
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
        if (this.aggregatedTiles.size() == 9)
            this.isCompleted = true;

        return this.isCompleted;
    }

    @Override
    public int countPoints()
    {
        return this.aggregatedTiles.size();
    }
    
    @Override
    public void enlargeAggregate(int col, int row, AbstractTile newTile, Set<String> locationTypes)
    {
        aggregatedTiles.put(new Coord(col, row), newTile);
        aggregatedPositionTypes.put(newTile, locationTypes);
    }
}
