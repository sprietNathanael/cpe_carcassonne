/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.carcassonnegame;

import carcassonne.model.tile.AbstractTile;

/**
 *
 * @author thomas
 */
public interface CarcassonneGameInterface
{
    public void putTile(AbstractTile tile, int Row, int Column) throws Exception;
    public AbstractTile pileTile();
}
