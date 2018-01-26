package carcassonne.view.CarcassonneIHM.menuStart;

import RessourcesGlobalVariables.Colors;
import RessourcesGlobalVariables.PlayerTypes;
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
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;

public class Local extends JDialog
{
    private static final int NBMAXPLAYERS = 6;

    //private ZDialogInfo zInfo = new ZDialogInfo();
    private boolean sendData;
    private JLabel nomLabel, icon, colorsLabel, lbTypeJoueur;
    private JRadioButton tranche1, tranche2, tranche3, tranche4, tranche5;

    private JTextField tfNomPlayer[];
    private JComboBox<String> cbColors[];
    private JComboBox<String> cbPlayerType[];
    private Settings settings;
    private final JCheckBox extRiver = new JCheckBox("River");
    private final JCheckBox extInnsAndCath = new JCheckBox("Inns and Cathedrals");

    Local self = this;

    public Local()
    {
        this.initComponent();
    }

    /**
     * Allows to init the differents components for the game
     */
    @SuppressWarnings("unchecked")
    private void initComponent()
    {
        //Add title
        this.setTitle("Jeu en Local");
        
        //Set Icon
        this.setIconImage(new ImageIcon(getClass().getResource("/images/icone carcassonne.jpg")).getImage());

        //Dimension Window
        this.setSize(1050, 700);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        //Add custom cursor
        Toolkit tk = Toolkit.getDefaultToolkit();
        this.setCursor(tk.createCustomCursor(new ImageIcon(getClass().getResource("/images/curseur.png")).getImage(), new Point(0, 0), "nameCursor"));

        //Image de gauche
        icon = new JLabel(new ImageIcon("resources/knight.png"));
        JPanel panIcon = new JPanel();
        panIcon.setLayout(new BorderLayout());
        panIcon.add(icon);

        //------------- PART PLAYERS ------------------------------------------
        JPanel paPlayer;
        JPanel content = new JPanel();

        tfNomPlayer = new JTextField[NBMAXPLAYERS];
        cbPlayerType = new JComboBox[NBMAXPLAYERS];
        cbColors = new JComboBox[NBMAXPLAYERS];

        for (int i = 0; i < NBMAXPLAYERS; i++) {
            paPlayer = new JPanel();
            paPlayer.setBackground(new Color(232,181,87));
            paPlayer.setPreferredSize(new Dimension(600, 55));
            paPlayer.setBorder(BorderFactory.createTitledBorder("Player" + (i + 1)));
            
            lbTypeJoueur = new JLabel("Player type : ");
            cbPlayerType[i] = new JComboBox<>();
            cbPlayerType[i].addItem(PlayerTypes.player);
            cbPlayerType[i].addItem(PlayerTypes.basicIA);

            nomLabel = new JLabel("Name :");
            colorsLabel = new JLabel("Color :");
            tfNomPlayer[i] = new JTextField();
            tfNomPlayer[i].setPreferredSize(new Dimension(100, 25));
            cbColors[i] = new JComboBox<>();
            cbColors[i].addItem(Colors.red);
            cbColors[i].addItem(Colors.blue);
            cbColors[i].addItem(Colors.green);
            cbColors[i].addItem(Colors.black);
            cbColors[i].addItem(Colors.yellow);
            cbColors[i].addItem(Colors.magenta);
            cbColors[i].setSelectedIndex(i);
            paPlayer.add(lbTypeJoueur, BorderLayout.WEST);
            paPlayer.add(cbPlayerType[i], BorderLayout.WEST);
            paPlayer.add(nomLabel, BorderLayout.CENTER);
            paPlayer.add(tfNomPlayer[i], BorderLayout.CENTER);
            paPlayer.add(colorsLabel, BorderLayout.EAST);
            paPlayer.add(cbColors[i], BorderLayout.EAST);
            content.add(paPlayer);
        }

        //Extension choice
        JPanel panExt = new JPanel();
        panExt.setBackground(new Color(232,181,87));
        panExt.setPreferredSize(new Dimension(300, 60));
        panExt.setBorder(BorderFactory.createTitledBorder("EXTENSIONS"));
        extRiver.addActionListener(new StateListener());
        extRiver.setBackground(new Color(232,181,87));
        extInnsAndCath.addActionListener(new StateListener());
        extInnsAndCath.setBackground(new Color(232,181,87));
        panExt.add(extRiver);
        panExt.add(extInnsAndCath);

        JPanel control = new JPanel();
        BtGame btPlay = new BtGame("resources/PlayLocal.png");
        btPlay.setOpaque(false);
        btPlay.setContentAreaFilled(false);
        btPlay.setBorderPainted(false);
        BtGame btBack = new BtGame("resources/BackLocal.png");
        btBack.setOpaque(false);
        btBack.setContentAreaFilled(false);
        btBack.setBorderPainted(false);

        btBack.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                try {
                    self.settings = new Settings();
                } catch (IOException ex) {
                    Logger.getLogger(GameMenu.class.getName()).log(Level.SEVERE, null, ex);
                }

                setVisible(false);
            }
        });

        btPlay.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                /*zInfo = new ZDialogInfo(tfNomPlayer[0].getText(), "6", (String) cbColors[0].getSelectedItem(),
                        tfNomPlayer[1].getText(), (String) cbColors[1].getSelectedItem(),
                        tfNomPlayer[2].getText(), (String) cbColors[2].getSelectedItem(),
                        tfNomPlayer[3].getText(), (String) cbColors[3].getSelectedItem(),
                        tfNomPlayer[4].getText(), (String) cbColors[4].getSelectedItem(),
                        tfNomPlayer[5].getText(), (String) cbColors[5].getSelectedItem()
                );*/

                ClientWindow clientWindow;

                if (self != null) {
                    List<ParamPlayers> players = self.getPlayers();
                    Set<String> playableColors = new HashSet<>();
                    players.stream().filter((player) -> (player.getPlayerType().equals(PlayerTypes.player))).forEachOrdered((player) -> {
                        playableColors.add(player.getColor());
                    });
                    clientWindow = new ClientWindow(self.getPlayers(), playableColors, extRiver.isSelected(), extInnsAndCath.isSelected());
                }
                else {
                    clientWindow = new ClientWindow();
                }

                clientWindow.setVisible(true);
                clientWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setVisible(false);
                btPlay.setEnabled(false);

            }

        });

        content.add(panExt);
        content.setBackground(new Color(190,139,46));

        control.add(btPlay);
        control.add(btBack);
        control.setBackground(new Color(190,139,46));
        
        panIcon.setBackground(new Color(190,139,46));

        this.getContentPane().add(control, BorderLayout.SOUTH);
        this.getContentPane().add(panIcon, BorderLayout.WEST);
        this.getContentPane().add(content, BorderLayout.CENTER);
    }

    /**
     * Allows to have the numbers of players selected
     *
     * @return
     */

    /*public ZDialogInfo showZDialog()
    {
        this.sendData = false;
        this.setVisible(true);
        return this.zInfo;
    }*/
    /**
     * List of players with their characteristics
     *
     * @return
     */
    public List<ParamPlayers> getDataPlayers()
    {
        List<ParamPlayers> li = new LinkedList<>();

        ParamPlayers pTemp = null;

        for (int i = 0; i < NBMAXPLAYERS; i++) {
            pTemp = new ParamPlayers(tfNomPlayer[i].getText(), (String) cbColors[i].getSelectedItem(), (String) cbPlayerType[i].getSelectedItem());

            if (!pTemp.getNom().isEmpty()) {
                li.add(pTemp);
            }
        }
        return li;
    }

    public List<ParamPlayers> getPlayers()
    {
        return (this.getDataPlayers());
    }

    class StateListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("source : " + ((JCheckBox) e.getSource()).getText() + " - état : " + ((JCheckBox) e.getSource()).isSelected());
        }
    }

}
