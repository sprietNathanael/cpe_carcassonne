/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package Network;

import RessourcesGlobalVariables.eNetworkActions;
import carcassonne.coord.Coord;
import java.io.Serializable;

/**
 *
 * @author nathanael
 */
public class NetworkMessage implements Serializable
{
    public final eNetworkActions message;
    public final Object object;

    public NetworkMessage(eNetworkActions message, Object object)
    {
        this.message = message;
        this.object = object;
    }
    
    
}
