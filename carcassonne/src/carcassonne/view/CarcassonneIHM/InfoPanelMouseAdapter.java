/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.CarcassonneIHM;

import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

/**
 *
 * @author nathanael
 */
public class InfoPanelMouseAdapter extends LayerMouseAdapter
{
    private final InfoPanelMouseListener listener;
    

    public InfoPanelMouseAdapter(InfoPanelMouseListener listener)
    {
        this.listener = listener;
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        // Get the coordinates of the mouse
        Point2D point = e.getPoint();
        listener.mouseClicked(e, point);
    }
}
