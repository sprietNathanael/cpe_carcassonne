/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.menuStart;

import carcassonne.model.player.Player;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author thomas
 */
public class Settingstemp extends JFrame

{

    private int nbPlayers = 0;

    private JLabel nomLabel, colorsLabel, icon;
    private JComboBox colors;
    private JTextField nom,numbers;
    private JRadioButton nb2, nb3, nb4, nb5, nb6;

    public Settingstemp() throws IOException
    {
        this.setTitle("Settings");
        this.setIconImage(new ImageIcon(getClass().getResource("/images/icone carcassonne.jpg")).getImage());
        this.setSize(1100, 950);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        //this.setContentPane(new Background(back));
        Toolkit tk = Toolkit.getDefaultToolkit();
        this.setCursor(tk.createCustomCursor(new ImageIcon(getClass().getResource("/images/curseur.png")).getImage(), new Point(0, 0), "nameCursor"));

        initComponent();

    }

    public int getNbPlayers()
    {
        return nbPlayers;
    }

    private void initComponent()
    {
        //Icône
        icon = new JLabel(new ImageIcon("resources/knight.jpg"));
        JPanel panIcon = new JPanel();
        panIcon.setBackground(Color.white);
        panIcon.setLayout(new BorderLayout());
        panIcon.add(icon);

        //Numbers 
        /*JPanel numb = new JPanel();
        numb.setBackground(Color.white);
        numb.setBorder(BorderFactory.createTitledBorder("Numbers of players"));
        numb.setPreferredSize(new Dimension(440, 60));
        nb2 = new JRadioButton("2");
        nb3 = new JRadioButton("3");
        nb4 = new JRadioButton("4");
        nb5 = new JRadioButton("5");
        nb6 = new JRadioButton("6");
        ButtonGroup bg = new ButtonGroup();
        bg.add(nb2);
        bg.add(nb3);
        bg.add(nb4);
        bg.add(nb5);
        bg.add(nb6);
        numb.add(nb2);
        numb.add(nb3);
        numb.add(nb4);
        numb.add(nb5);
        numb.add(nb6);*/
        JPanel panNumb = new JPanel();
        panNumb.setBackground(Color.white);
        panNumb.setPreferredSize(new Dimension(400, 60));
        numbers = new JTextField();
        numbers.setPreferredSize(new Dimension(100, 25));
        panNumb.setBorder(BorderFactory.createTitledBorder("Numbers of players"));
        nomLabel = new JLabel("Numbers (between 2 and 6) :");
        panNumb.add(nomLabel, BorderLayout.WEST);
        panNumb.add(numbers, BorderLayout.EAST);

        //Players
        JPanel player1 = CreatePlayer(1);
        JPanel player2 = CreatePlayer(2);
        JPanel player3 = CreatePlayer(3);
        JPanel player4 = CreatePlayer(4);
        JPanel player5 = CreatePlayer(5);
        JPanel player6 = CreatePlayer(6);

        JPanel content = new JPanel();
        content.setBackground(Color.white);
        //content.add(numb);
        content.add(panNumb);
        content.add(player1,  BorderLayout.EAST);
        content.add(player2,  BorderLayout.EAST);
        content.add(player3,  BorderLayout.EAST);
        content.add(player4);
        content.add(player5);
        content.add(player6);
        

        //Save Data
        JPanel control = new JPanel();
        JButton okBouton = new JButton("OK");

        okBouton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                setVisible(false);
            }
        });

        JButton cancelBouton = new JButton("Annuler");
        cancelBouton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                setVisible(false);
            }

            public String getJoueurs()
            {
                return (nb2.isSelected()) ? nb2.getText()
                        : (nb3.isSelected()) ? nb3.getText()
                        : (nb4.isSelected()) ? nb4.getText()
                        : (nb5.isSelected()) ? nb5.getText()
                        : (nb6.isSelected()) ? nb6.getText()
                        : nb2.getText();
            }
        });

        control.add(okBouton);
        control.add(cancelBouton);

        this.getContentPane().add(panIcon, BorderLayout.WEST);
        this.getContentPane().add(content, BorderLayout.CENTER);
        this.getContentPane().add(control, BorderLayout.SOUTH);
    }

    private JPanel CreatePlayer(int x)
    {

        JPanel player = new JPanel();
        player.setBackground(Color.white);
        player.setPreferredSize(new Dimension(300, 60));
        player.setBorder(BorderFactory.createTitledBorder("Player " + x));
        nomLabel = new JLabel("Name :");
        colorsLabel = new JLabel("Color :");

        nom = new JTextField();
        nom.setPreferredSize(new Dimension(100, 25));

        colors = new JComboBox();
        colors.addItem("Red");
        colors.addItem("Blue");
        colors.addItem("Green");
        colors.addItem("Black");
        colors.addItem("Yellow");
        colors.addItem("Violet");

        player.add(nomLabel, BorderLayout.WEST);
        player.add(nom, BorderLayout.WEST);
        player.add(colorsLabel, BorderLayout.EAST);
        player.add(colors, BorderLayout.EAST);

        return player;
    }

}
