/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carcassonne;
import carcassonne.EnumTuile;
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
    private JPanel[][] panels;
    private JLabel blank;
    private JLabel road;
    private JLabel castle;
    private JLabel field;

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
        panels = new JPanel[this.plateau.getTailleX()][this.plateau.getTailleY()];
        for (int incX = 0; incX < this.plateau.getTailleX(); incX++) {
            for (int incY = 0; incY < this.plateau.getTailleY(); incY++) {
                panels[incX][incY] = new JPanel();
                panels[incX][incY].setLayout(new GridLayout(3, 3));
                panels[incX][incY].setBorder(BorderFactory.createLineBorder(Color.black));
                panels[incX][incY].setBackground(Color.white);

                carcassonneBoard.add(panels[incX][incY]);
            }

        }
        
        blank = new JLabel("B");
        road = new JLabel("R");
        castle = new JLabel("C");
        field = new JLabel("F");
        
        /*blank.setBackground(Color.blue);
        field.setBackground(Color.green);
        castle.setBackground(Color.red);
        road.setBackground(Color.black);*/
    }

    @Override
    public void update(Observable o, Object arg) {
        for (int incX = 0; incX < this.plateau.getTailleX(); incX++) {
            for (int incY = 0; incY < this.plateau.getTailleY(); incY++) {
                System.out.println(incX+";"+incY);
                panels[incX][incY].removeAll();
                
                // First Line
                panels[incX][incY].add(new JLabel(""));
                
                if (this.plateau.getElement(incX, incY).getNord() == null) {
                    //panels[incX][incY].add(blank);
                    panels[incX][incY].add(new JLabel(""));
                } else if(this.plateau.getElement(incX, incY).getNord() == EnumTuile.ville) {
                    //panels[incX][incY].add(castle);
                    panels[incX][incY].add(new JLabel("V"));
                } else if(this.plateau.getElement(incX, incY).getNord() == EnumTuile.route) {
                    //panels[incX][incY].add(road);
                    panels[incX][incY].add(new JLabel("R"));
                } else if(this.plateau.getElement(incX, incY).getNord() == EnumTuile.champ) {
                    //panels[incX][incY].add(field);
                    panels[incX][incY].add(new JLabel("C"));
                }
                
                panels[incX][incY].add(new JLabel(""));
                
                // Second Line
                if (this.plateau.getElement(incX, incY).getOuest() == null) {
                    //panels[incX][incY].add(blank);
                    panels[incX][incY].add(new JLabel(""));
                } else if(this.plateau.getElement(incX, incY).getOuest() == EnumTuile.ville) {
                    //panels[incX][incY].add(castle);
                    panels[incX][incY].add(new JLabel("V"));
                } else if(this.plateau.getElement(incX, incY).getOuest() == EnumTuile.route) {
                    //panels[incX][incY].add(road);
                    panels[incX][incY].add(new JLabel("R"));
                } else if(this.plateau.getElement(incX, incY).getOuest() == EnumTuile.champ) {
                    //panels[incX][incY].add(field);
                    panels[incX][incY].add(new JLabel("C"));
                }
                
                panels[incX][incY].add(new JLabel(""));
                
                if (this.plateau.getElement(incX, incY).getEst() == null) {
                    //panels[incX][incY].add(blank);
                    panels[incX][incY].add(new JLabel(""));
                } else if(this.plateau.getElement(incX, incY).getEst() == EnumTuile.ville) {
                    //panels[incX][incY].add(castle);
                    panels[incX][incY].add(new JLabel("V"));
                } else if(this.plateau.getElement(incX, incY).getEst() == EnumTuile.route) {
                    //panels[incX][incY].add(road);
                    panels[incX][incY].add(new JLabel("R"));
                } else if(this.plateau.getElement(incX, incY).getEst() == EnumTuile.champ) {
                    //panels[incX][incY].add(field);
                    panels[incX][incY].add(new JLabel("C"));
                }
                
                // Third Line
                panels[incX][incY].add(new JLabel(""));
                
                if (this.plateau.getElement(incX, incY).getSud() == null) {
                    //panels[incX][incY].add(blank);
                    panels[incX][incY].add(new JLabel(""));
                } else if(this.plateau.getElement(incX, incY).getSud() == EnumTuile.ville) {
                    //panels[incX][incY].add(castle);
                    panels[incX][incY].add(new JLabel("V"));
                } else if(this.plateau.getElement(incX, incY).getSud() == EnumTuile.route) {
                    //panels[incX][incY].add(road);
                    panels[incX][incY].add(new JLabel("R"));
                } else if(this.plateau.getElement(incX, incY).getSud() == EnumTuile.champ) {
                    //panels[incX][incY].add(field);
                    panels[incX][incY].add(new JLabel("C"));
                }
                
                //panels[incX][incY].add(blank);
                panels[incX][incY].add(new JLabel(""));
            }
        }
    }

}
