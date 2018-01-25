/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.controller;

import Network.Host;
import carcassonne.model.carcassonnegame.CarcassonneGame;
import carcassonne.model.carcassonnegame.CarcassonneGameInterface;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thomas
 */
public class CarcassonneGameControllerLocalNetwork extends CarcassonneGameController
{
    private Host host;

    public CarcassonneGameControllerLocalNetwork(Host host) throws Exception
    {
        this(new CarcassonneGame(), host);
    }

    public CarcassonneGameControllerLocalNetwork(CarcassonneGameInterface model, Host host) throws Exception
    {
        super(model);
        
        // double link
        this.host = host;
        host.setNetController(this);
        host.sendToAllSockets(model);
    }

    @Override
    public void endTurn()
    {
        try {
            super.endTurn();
            host.receiveAction();
        } catch (Exception ex) {
            Logger.getLogger(CarcassonneGameControllerLocalNetwork.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
    
}
