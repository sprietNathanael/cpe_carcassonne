/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view;

import carcassonne.controller.AbstractCarcassonneGameController;
import java.util.Observable;
import java.util.Observer;

/**
 * View class to represent the game into command line
 * @author Thomas
 */
public class CarcassonneCmdLine implements Observer
{
    private final AbstractCarcassonneGameController controller;    
    
    public CarcassonneCmdLine(AbstractCarcassonneGameController controller)
    {
        this.controller = controller;  
    }
    
    /**
     * The method that will launch the interface
     */
    public void go()
    {
        System.out.println("Bienvenue dans le jeu Carcassonne !");
        this.newTurn();
    }
    
    /**
     * Runs a complete turn
     */
    private void newTurn()
    {
        System.out.println("C'est à "+this.controller.getCurrentPlayer().getName()+" de jouer");
        //System.out.println("La carte à poser est : \n"+this.controller.drawTile());
    }
    
    @Override
    public void update(Observable o, Object arg)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
