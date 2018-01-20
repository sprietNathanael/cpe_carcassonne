/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.aggregate;

import carcassonne.model.player.Meeple;
import carcassonne.model.player.Player;
import carcassonne.model.tile.AbstractTile;
import java.util.Map;
import java.util.Set;

/**
 * Deals with the River
 *
 * @author Étienne
 *
 */
public class RiverAggregate extends AbstractAggregate
{

    public RiverAggregate(int col, int row, AbstractTile firstTile, Set<String> locationTypes)
    {
        super(col, row, firstTile, locationTypes);
    }

    @Override
    public void enlargeAggregate(int col, int row, AbstractTile newTile, Set<String> locationTypes)
    {
        super.enlargeAggregate(col, row, newTile, locationTypes);
    }
    
    @Override
    public Set<Player> getWinningPlayers()
    {
        return null;
    }

    @Override
    public int getBiggestPoints()
    {
        return -1;
    }

    @Override
    protected void merge(AbstractAggregate neighborAggregate)
    {

    }

    @Override
    public Map<Player, Set<Meeple>> getPlayers()
    {
        return null;
    }

    @Override
    public boolean addMeeple(Player player, Meeple meeple)
    {
        return false;
    }

    @Override
    public boolean checkIsCompleted()
    {
        // @TODO: à gérer

        return false;
    }

    @Override
    public int countPoints()
    {
        return -1;
    }

    @Override
    public String toString()
    {
        return "River{" + "aggregatedTiles=" + aggregatedTiles + ", aggregatedPositionTypes=" + aggregatedPositionTypes + ", players=" + players + ", isCompleted=" + isCompleted + "\n}";
    }

}
