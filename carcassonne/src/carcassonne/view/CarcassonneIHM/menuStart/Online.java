/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.CarcassonneIHM.menuStart;

import Network.Host;
import Network.NetworkGame;
import RessourcesGlobalVariables.Colors;
import RessourcesGlobalVariables.PlayerTypes;
import carcassonne.controller.CarcassonneGameControllerMulti;
import carcassonne.view.CarcassonneIHM.ClientWindow;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
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
    private JPanel panCreate, panJoin;
    private CarcassonneGameControllerMulti controller;
    private JTextField tfIpAddress, tfName;
    private Settings settings;
    private Host host; // use in host only
    private NetworkGame game;
    private boolean isHost;

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
        panCreate = new JPanel();
        panCreate.setBackground(Color.white);
        panCreate.setPreferredSize(new Dimension(291, 516));
        panCreate.setLayout(new GridLayout(7,1));
        panCreate.setBorder(BorderFactory.createTitledBorder("HOST"));
        panJoin = new JPanel();
        panJoin.setBackground(Color.white);
        panJoin.setPreferredSize(new Dimension(291, 516));
        panJoin.setLayout(new GridLayout(8,1));
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
                try{
                    if (ip == "") 
                        ip = "localhost";
                    game = new NetworkGame(ip, tfName.getText());
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
                host.beginGame();
                ClientWindow cw = new ClientWindow(color, host);
            }
        });

        //active listenning of buttons
        btCreateGame.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                isHost = true;
                panJoin.setBackground(Color.black);
                panIP.setBackground(Color.black);
                tfIpAddress.setBackground(Color.black);
                btPlay.setEnabled(false);
                btPlayHost.setEnabled(true);
                btJoinGame.setEnabled(false);
                ParamPlayers player = new ParamPlayers(tfName.getText(), Colors.tab.get(0), PlayerTypes.player);
                host = new Host(player, self);
                addPlayer(player);
                
            }
        });

        btJoinGame.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                isHost = false;
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
    
    public void addPlayer(ParamPlayers player)
    {
        JPanel curPan;
        if(isHost)
        {
            curPan = panCreate;
        }
        else
        {
            curPan = panJoin;
        }
        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new GridLayout(1,2));
        JLabel label = new JLabel(""+player.getNom());
        
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("resources/meeples_bordered/"+player.getColor()+".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = img.getScaledInstance(30, 30,Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);
        
        playerPanel.add(label);
        JLabel icon_label= new JLabel(imageIcon);
        playerPanel.add(icon_label);
        curPan.add(playerPanel);
    }
    
    public void flushPanel()
    {
        JPanel curPan;
        if(isHost)
        {
            panCreate.removeAll();
        }
        else
        {
            panJoin.removeAll();
        }
    }
    
    public void displayClientMessage(String message)
    {
        panJoin.add(new JLabel(message));
        
        this.revalidate();
        this.repaint();
    }
}
