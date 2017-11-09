/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.ui_test;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
/**
 *
 * @author nathanael
 */
public class GridPanel extends JPanel
{

    public GridPanel()
    {
        setDoubleBuffered(true);
        setOpaque(false);
        setLayout(new MigLayout());
    }
    
}
