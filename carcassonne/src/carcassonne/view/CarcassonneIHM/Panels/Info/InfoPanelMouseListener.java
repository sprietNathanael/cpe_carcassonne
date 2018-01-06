/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.CarcassonneIHM.Panels.Info;

import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

/**
 *
 * @author nathanael
 */
public interface InfoPanelMouseListener
{
    void mouseClicked(MouseEvent e, Point2D p);
}
