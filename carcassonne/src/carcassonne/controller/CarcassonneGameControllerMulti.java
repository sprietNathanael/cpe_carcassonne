/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.controller;

import carcassonne.model.carcassonnegame.CarcassonneGame;
import carcassonne.model.carcassonnegame.CarcassonneGameInterface;

/**
 * Allows to play at several players on a same computer
 * @author Thomas
 */
public class CarcassonneGameControllerMulti extends AbstractCarcassonneGameController
{

    public CarcassonneGameControllerMulti() throws Exception
    {
    }

    public CarcassonneGameControllerMulti(CarcassonneGameInterface model) throws Exception
    {
        super(model);
    }
    
}
