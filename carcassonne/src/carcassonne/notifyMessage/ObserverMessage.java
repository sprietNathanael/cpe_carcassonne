/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */

package carcassonne.notifyMessage;

import carcassonne.coord.Coord;
import carcassonne.model.carcassonnegame.CarcassonneGame;
import carcassonne.model.tile.AbstractTile;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author nathanael
 */
public class ObserverMessage
{
    public final CarcassonneGame game;
    public final String messageType;

    public ObserverMessage(String messageType, CarcassonneGame game)
    {
        this.game = game;
        this.messageType = messageType;
    }
    
}
