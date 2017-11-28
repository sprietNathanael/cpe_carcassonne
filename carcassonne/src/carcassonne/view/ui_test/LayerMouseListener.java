/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.ui_test;

import java.awt.event.MouseEvent;

/**
 *
 * @author nathanael
 */
public interface LayerMouseListener
{
    void tileEntered(MouseEvent e, Coord p);
    void tileExited(MouseEvent e, Coord p);

    void mouseClicked(MouseEvent e, Coord p);
}
