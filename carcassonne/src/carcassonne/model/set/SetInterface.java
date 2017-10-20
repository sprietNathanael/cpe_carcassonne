/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.set;

import carcassonne.model.tile.AbstractTile;
import java.util.List;

/**
 * Describes the nedded set format
 *
 * @author Étienne
 */
public interface SetInterface
{

    /**
     *
     * @return All the tiles from the wanted extensions
     */
    public List<AbstractTile> getSet();
}
