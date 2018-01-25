/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */

package carcassonne.notifyMessage;

import carcassonne.model.carcassonnegame.CarcassonneGame;
import java.io.Serializable;

/**
 * 
 * @author nathanael
 */
public class ObserverMessage implements Serializable
{
    public final CarcassonneGame game;
    public final String messageType;

    public ObserverMessage(String messageType, CarcassonneGame game)
    {
        this.game = game;
        this.messageType = messageType;
    }
    
}
