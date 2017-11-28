/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.ui_test;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

/**
 *
 * @author nathanael
 */
public class GridMouseAdapter extends MouseAdapter
{
    private MouseEvent dragSource;
    private GridPanel gridPanel;
    private Coord sourceCenter;
    
    GridMouseAdapter(GridPanel gridPane)
    {
        this.gridPanel = gridPane;
    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        //System.out.println("drag : "+e.getX());
        int dx = e.getX() - this.dragSource.getX();
        int dy = e.getY() - this.dragSource.getY();
        //System.out.println("drag : x="+e.getX()+";y="+e.getY()+" : dx="+dx+";dy="+dy);
        Coord newCenter = new Coord(this.sourceCenter.getX()+dx, this.sourceCenter.getY()+dy);
        //System.out.println("new center : "+newCenter);
        this.gridPanel.moveCenterTo(newCenter);
        
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e)
    {
        this.gridPanel.zoom(-e.getWheelRotation());
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        this.dragSource = null;
        this.sourceCenter = null;
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        this.dragSource = e;
        this.sourceCenter = this.gridPanel.getGraphicalCenter();
        System.out.println("pressed at : x="+e.getX()+" ; y="+e.getY());
    }
    
    
    
}
