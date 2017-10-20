/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.controller;

import carcassonne.model.carcassonnegame.CarcassonneGame;

/**
 * Abstract class which represents a generic class
 * @author Thomas
 */
public class AbstractCarcassonneGameController implements CarcassonneGameControllerInterface
{
    private CarcassonneGame carcassonneGame;
    
    /**
     * Constructor for an AbstractCarcassonneGameController
     */
    public AbstractCarcassonneGameController()
    {
        this.carcassonneGame = new CarcassonneGame();
    }
    
           
}

