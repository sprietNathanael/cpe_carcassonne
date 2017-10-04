/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carcassonne;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
/**
 *
 * @author nathanael
 */
public class CarcassonneGUI extends JFrame {

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
        
        carcassonneBoard =  new JPanel();
        layeredPane.add(carcassonneBoard, JLayeredPane.DEFAULT_LAYER);
        carcassonneBoard.setLayout( new GridLayout(this.plateau.getTailleX(), this.plateau.getTailleY()) );
        carcassonneBoard.setPreferredSize( dim );
        carcassonneBoard.setBounds(0, 0, dim.width, dim.height);
        
        for (int i = 0; i < (this.plateau.getTailleX() * this.plateau.getTailleY()) ; i++)
        {
            JPanel square = new JPanel( new BorderLayout() );
            square.setBorder(BorderFactory.createLineBorder(Color.black));
            carcassonneBoard.add( square );
            square.setBackground(Color.white );
        }
    }
    
}
