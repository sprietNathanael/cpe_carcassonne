/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.controller;

import carcassonne.model.carcassonnegame.CarcassonneGame;

/**
 * Allows to play at several players on a same computer
 * @author Thomas
 */
public class CarcassonneGameControllerMulti extends AbstractCarcassonneGameController
{

    public CarcassonneGameControllerMulti()
    {
    }

    public CarcassonneGameControllerMulti(CarcassonneGame model)
    {
        super(model);
    }
    
}
