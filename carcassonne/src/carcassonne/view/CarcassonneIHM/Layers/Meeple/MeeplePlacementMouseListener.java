/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.CarcassonneIHM.Layers.Meeple;

import carcassonne.view.CarcassonneIHM.Tools.UICoord;
import java.awt.event.MouseEvent;

/**
 * A mouse listener for the meeple placement layer
 * @author nathanael
 */
public interface MeeplePlacementMouseListener
{
    void tileSliceEntered(MouseEvent e, UICoord p, String tileSlice);
    void tileSliceExited(MouseEvent e);
    
    void mouseClicked(MouseEvent e, UICoord p, String tileSlice);
    
    String getSliceFromCoordinates(double x, double y, UICoord tileCoordinates);
    
}
