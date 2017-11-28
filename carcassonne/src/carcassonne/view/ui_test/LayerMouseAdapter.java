/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.ui_test;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

/**
 *
 * @author nathanael
 */
public class LayerMouseAdapter extends MouseAdapter
{
    private GridPanel gridPanel;
    private LayerMouseListener listener;
    
    private Coord currentCoord;
    
    LayerMouseAdapter(GridPanel gridPanel, LayerMouseListener listener)
    {
        this.gridPanel = gridPanel;
        this.listener = listener;
    }
       
    public Coord getCurrentCoord()
    {
        return this.currentCoord;
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {
        Point2D point = e.getPoint();
        Coord c = this.gridPanel.getGridPositionFromCoordinates(point.getX(), point.getY());
        if (this.currentCoord != null && ! this.currentCoord.equals(c))
        {
            listener.tileExited(e, this.currentCoord);
            this.currentCoord = null;
        }
        if (c != null && ! c.equals(this.currentCoord)) {
            this.currentCoord = c;
            listener.tileEntered(e, this.currentCoord);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point2D point = e.getPoint();
        Coord c = this.gridPanel.getGridPositionFromCoordinates(point.getX(), point.getY());
        if (c != null) {
            listener.mouseClicked(e, c);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        mouseMoved(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (this.currentCoord != null) {
            listener.tileExited(e, this.currentCoord);
            this.currentCoord = null;
        }

    }
    
}
