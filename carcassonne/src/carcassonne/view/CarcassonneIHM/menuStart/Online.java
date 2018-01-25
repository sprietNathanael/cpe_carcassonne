/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.CarcassonneIHM.menuStart;

import Network.Host;
import Network.NetworkGame;
import RessourcesGlobalVariables.Colors;
import carcassonne.controller.CarcassonneGameControllerMulti;
import carcassonne.view.CarcassonneIHM.ClientWindow;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author thomas
 */
public class Online extends JDialog
{

    private JLabel icon, lbName;
    private JRadioButton serveur, client;
    private JButton btCreateGame, btJoinGame, btPlay, btPlayHost;
    private CarcassonneGameControllerMulti controller;
    private JTextField tfIpAddress, tfName;
    private Settings settings;
    private Host host; // use in host only

    public Online()
    {
        this.initComponent();
    }

    private void initComponent()
    {
        Online self = this;

        //Add title
        this.setTitle("Jeu en Ligne");
        
        //Set Icon
        this.setIconImage(new ImageIcon(getClass().getResource("/images/icone carcassonne.jpg")).getImage());

        //Dimension Window
        this.setSize(950, 700);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        //Add custom cursor
        Toolkit tk = Toolkit.getDefaultToolkit();
        this.setCursor(tk.createCustomCursor(new ImageIcon(getClass().getResource("/images/curseur.png")).getImage(), new Point(0, 0), "nameCursor"));

        //Image de gauche
        icon = new JLabel(new ImageIcon("resources/logo online.jpg"));
        JPanel panIcon = new JPanel();
        panIcon.setBackground(Color.white);
        panIcon.setLayout(new BorderLayout());
        panIcon.add(icon);

        //Button back
        JPanel panControl = new JPanel();
        JButton btBack = new JButton("BACK");

        btBack.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                //System.out.println(tfName.getText());
                try {
                    self.settings = new Settings();
                } catch (IOException ex) {
                    Logger.getLogger(GameMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
                self.settings.setVisible(true);
                self.settings.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                self.setVisible(false);
                setVisible(false);
            }
        });

        //Parameters
        JPanel panParameters = new JPanel();
        panParameters.setBackground(Color.black);

        //Pseudo
        JPanel panNom = new JPanel();
        panNom.setBackground(Color.white);
        panNom.setPreferredSize(new Dimension(220, 55));
        tfName = new JTextField();
        tfName.setPreferredSize(new Dimension(100, 25));
        panNom.setBorder(BorderFactory.createTitledBorder("Pseudo"));
        panNom.add(tfName);

        //buttons Create and join game to choose
        JPanel panRole = new JPanel();
        panRole.setBackground(Color.white);
        panRole.setPreferredSize(new Dimension(585, 100));
        panRole.setBorder(BorderFactory.createTitledBorder("GAME"));
        btCreateGame = new JButton("Create a game");
        btJoinGame = new JButton("Join a game");

        panRole.add(panNom);
        panRole.add(btCreateGame);
        panRole.add(btJoinGame);

        /* 2 PANNELS : CREATE GAME AND JOIN GAME */
        JPanel panCreate = new JPanel();
        panCreate.setBackground(Color.white);
        panCreate.setPreferredSize(new Dimension(291, 516));
        panCreate.setBorder(BorderFactory.createTitledBorder("HOST"));
        JPanel panJoin = new JPanel();
        panJoin.setBackground(Color.white);
        panJoin.setPreferredSize(new Dimension(291, 516));
        panJoin.setBorder(BorderFactory.createTitledBorder("PLAYER"));

        //Adress IP for join game
        JPanel panIP = new JPanel();
        panIP.setBackground(Color.white);
        panIP.setPreferredSize(new Dimension(220, 60));
        tfIpAddress = new JTextField();
        tfIpAddress.setPreferredSize(new Dimension(100, 25));
        panIP.setBorder(BorderFactory.createTitledBorder("JOIN GAME"));
        lbName = new JLabel("Address IP :");
        panIP.add(lbName);
        panIP.add(tfIpAddress);
        panJoin.add(panIP);

        //button play Player
        btPlay = new JButton("Join party");
        btPlay.setEnabled(false);
        btPlay.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String ip = tfIpAddress.getText();
                try {
                    NetworkGame game = new NetworkGame((ip != "") ? ip : "localhost", tfName.getText());
                } catch (Exception ex) {
                    Logger.getLogger(Online.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        panJoin.add(btPlay);

        //button play uses by host
        btPlayHost = new JButton("Play");
        btPlayHost.setEnabled(false);
        panCreate.add(btPlayHost);
        btPlayHost.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Set<String> color = new HashSet<>();
                color.add(Colors.tab.get(0));
                
                ClientWindow cw = new ClientWindow(color, host);
            }
        });

        //active listenning of buttons
        btCreateGame.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                panJoin.setBackground(Color.black);
                panIP.setBackground(Color.black);
                tfIpAddress.setBackground(Color.black);
                btPlay.setEnabled(false);
                btPlayHost.setEnabled(true);
                btJoinGame.setEnabled(false);

                host = new Host(tfName.getText());
                
            }
        });

        btJoinGame.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                panCreate.setBackground(Color.black);
                btCreateGame.setEnabled(false);
                btPlay.setEnabled(true);

            }
        });

        //Composition of panels with the differents elements
        panParameters.add(panRole, BorderLayout.NORTH);
        panParameters.add(panCreate, BorderLayout.WEST);
        panParameters.add(panJoin, BorderLayout.EAST);

        panControl.add(btBack);
        panControl.setBackground(Color.white);

        this.getContentPane().add(panParameters, BorderLayout.CENTER);
        this.getContentPane().add(panIcon, BorderLayout.WEST);
        this.getContentPane().add(panControl, BorderLayout.SOUTH);

    }
}
