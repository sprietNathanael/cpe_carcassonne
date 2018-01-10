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

    public boolean coordsAreCenterOfAgg(Coord coord)
    {
        boolean result = false;
        Set<String> types = this.getAggregatedTypesByCoord(coord.col, coord.row);

        if (types != null) {
            if (types.contains("CSE")
                    || types.contains("CSW")
                    || types.contains("CNE")
                    || types.contains("CNW")) {
                result = true;
            }
        }

        return result;
    }

    @Override
    public boolean checkIsCompleted()
    {
        if (this.aggregatedTiles.size() == 9) {
            this.isCompleted = true;
        }

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
        super.enlargeAggregate(col, row, newTile, locationTypes);
    }

    @Override
    public String toString()
    {
        return "AbbayAggregate{" + "aggregatedTiles=" + aggregatedTiles + ", aggregatedPositionTypes=" + aggregatedPositionTypes + ", players=" + players + ", isCompleted=" + isCompleted + "\n}";
    }

}
