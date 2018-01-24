/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.controller;

import carcassonne.model.carcassonnegame.CarcassonneGame;
import carcassonne.view.CarcassonneIHM.menuStart.ParamPlayers;
import java.util.AbstractList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Thomas
 */
public class CarcassonneGameControllerLocalNetwork extends AbstractCarcassonneGameController
{
    protected List<ParamPlayers> paramPlayers = null;

    public CarcassonneGameControllerLocalNetwork() throws Exception
    {
        this(new CarcassonneGame());
    }

    public CarcassonneGameControllerLocalNetwork(CarcassonneGame model)
    {
        super(model);
        paramPlayers = new LinkedList<>();
    }

    public List<ParamPlayers> getParamPlayers()
    {
        return paramPlayers;
    }
    
    
}
