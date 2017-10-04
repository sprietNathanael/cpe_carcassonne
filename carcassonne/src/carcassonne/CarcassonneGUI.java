/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carcassonne;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.Observable;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

/**
 *
 * @author nathanael
 */
public class CarcassonneGUI extends JFrame implements java.util.Observer {

    private Plateau plateau;
    private JPanel carcassonneBoard;
    private JLayeredPane layeredPane;
    private Dimension dim;

    public CarcassonneGUI(Plateau plateau) {
        this.plateau = plateau;
        layeredPane = new JLayeredPane();
        dim = new Dimension(800, 800);
        getContentPane().add(layeredPane);
        layeredPane.setPreferredSize(dim);

        carcassonneBoard = new JPanel();
        layeredPane.add(carcassonneBoard, JLayeredPane.DEFAULT_LAYER);
        carcassonneBoard.setLayout(new GridLayout(this.plateau.getTailleX(), this.plateau.getTailleY()));
        carcassonneBoard.setPreferredSize(dim);
        carcassonneBoard.setBounds(0, 0, dim.width, dim.height);

        for (int i = 0; i < (this.plateau.getTailleX() * this.plateau.getTailleY()); i++) {
            JPanel square = new JPanel(new BorderLayout());
            square.setBorder(BorderFactory.createLineBorder(Color.black));
            JLabel jlabel;
            jlabel = new JLabel("" + i);
            square.add(jlabel);
            carcassonneBoard.add(square);
            square.setBackground(Color.white);
        }
    }
    
    @Override
    public void update(Observable o, Object arg) {
        for (int incX = 0; incX < this.plateau.getTailleX(); incX++) {
            for (int incY = 0; incY < this.plateau.getTailleY(); incY++) {
                System.out.println(this.plateau.getElement(incX, incY));
            }
        }
    }
    
    

}
