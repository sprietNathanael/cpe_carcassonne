/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.ui_test;

import java.awt.event.MouseEvent;

/**
 * Mouse listener for layers
 * @author nathanael
 */
public interface LayerMouseListener
{
    void tileEntered(MouseEvent e, UICoord p);
    void tileExited(MouseEvent e, UICoord p);

    void mouseClicked(MouseEvent e, UICoord p);
}
