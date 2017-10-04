/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carcassonne;

import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author nathanael
 */
public class GUIController extends Observable{

    public GUIController() {
    }
    
    /* (non-Javadoc)
	 * @see java.util.Observable#notifyObservers(java.lang.Object)
     */
    @Override
    public void notifyObservers(Object arg) {
        super.setChanged();
        super.notifyObservers(arg);
    }

    /* (non-Javadoc)
	 * @see java.util.Observable#addObserver(java.util.Observer)
     */
    @Override
    public void addObserver(Observer o) {
        super.addObserver(o);
        this.notifyObservers();
    }
    
}
