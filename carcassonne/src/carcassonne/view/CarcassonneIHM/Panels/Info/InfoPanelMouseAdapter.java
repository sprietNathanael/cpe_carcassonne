/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.CarcassonneIHM.Panels.Info;

import carcassonne.view.CarcassonneIHM.Layers.LayerMouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.Set;

/**
 * A mouse adapter for the info panel
 * @author nathanael
 */
public class InfoPanelMouseAdapter extends LayerMouseAdapter
{
    private final Set<InfoPanelMouseListener> listeners;
    

    /**
     * Constructs the mouse adapter
     * @param listener 
     */
    public InfoPanelMouseAdapter(InfoPanelMouseListener listener)
    {
        this.listeners = new HashSet<>();
        this.listeners.add(listener);
    }
    
    public void addListener(InfoPanelMouseListener listener)
    {
        this.listeners.add(listener);
    }

    /**
     * When the mouse is clicked
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e)
    {
        // Get the coordinates of the mouse
        Point2D point = e.getPoint();
        for(InfoPanelMouseListener listener : listeners)
        {
            listener.mouseClicked(e, point);
            
        }
    }
}
