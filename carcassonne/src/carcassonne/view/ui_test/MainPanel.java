/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.ui_test;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.text.Position;

/**
 *
 * @author nathanael
 */
public class MainPanel extends JPanel
{

    public MainPanel()
    {
        super();
        setLayout(new BorderLayout());
        GridPanel gridPanel = new GridPanel();
        TestLayer testLayer = new TestLayer(gridPanel);
        gridPanel.addLayer(testLayer);
        testLayer.addPosition(new Coord(0, 0));
        testLayer.addPosition(new Coord(1, 0));
        testLayer.addPosition(new Coord(0, 1));
        testLayer.addPosition(new Coord(0, -1));
        testLayer.addPosition(new Coord(-1, 0));
        
        this.add(gridPanel);
    }
    
    
}
