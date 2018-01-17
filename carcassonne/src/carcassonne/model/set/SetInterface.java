/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.set;

import carcassonne.model.player.Meeple;
import carcassonne.model.tile.AbstractTile;
import java.util.List;
import java.util.Set;

/**
 * Describes the nedded set format
 *
 * @author Étienne
 */
public interface SetInterface
{

    /**
     * Gets the set's tiles
     * 
     * @return All the tiles from the wanted extensions
     */
    public List<AbstractTile> getSet();

    public AbstractTile getFirstTile();
    
    public Set<Meeple> getMeeples();
}
