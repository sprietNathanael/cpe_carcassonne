package carcassonne.view.CarcassonneIHM.menuStart;

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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;

public class Local extends JDialog
{

    private static final int NBMAXPLAYERS = 6;

    private ZDialogInfo zInfo = new ZDialogInfo();
    private boolean sendData;
    private JLabel nomLabel, icon, colorsLabel, lbTypeJoueur;
    private JRadioButton tranche1, tranche2, tranche3, tranche4, tranche5;
    /*private JTextField nomJ1, nomJ2, nomJ3, nomJ4, nomJ5, nomJ6;
    private JComboBox<String> colorsJ1, colorsJ2, colorsJ3, colorsJ4, colorsJ5, colorsJ6;
    private JCheckBox chbIaJ1, chbIaJ2, chbIaJ3, chbIaJ4, chbIaJ5, chbIaJ6;*/

    private JTextField tfNomPlayer[];
    private JComboBox<String> cbColors[];
    private JComboBox<String> cbPlayerType[];
    private Settings settings;

    Local self = this;

    public Local(JFrame parent, String title, boolean modal)
    {
        super(parent, title, modal);
        this.initComponent();
    }

    /**
     * Allows to init the differents components for the game
     */
    @SuppressWarnings("unchecked")
    private void initComponent()
    {
        //Set Icon
        this.setIconImage(new ImageIcon(getClass().getResource("/images/icone carcassonne.jpg")).getImage());

        //Dimension Window
        this.setSize(950, 700);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        //Add custom cursor
        Toolkit tk = Toolkit.getDefaultToolkit();
        this.setCursor(tk.createCustomCursor(new ImageIcon(getClass().getResource("/images/curseur.png")).getImage(), new Point(0, 0), "nameCursor"));

        //Image de gauche
        icon = new JLabel(new ImageIcon("resources/knight.jpg"));
        JPanel panIcon = new JPanel();
        panIcon.setBackground(Color.white);
        panIcon.setLayout(new BorderLayout());
        panIcon.add(icon);

        //Numbers of players
        JPanel numPlayers = new JPanel();
        numPlayers.setBackground(Color.white);
        numPlayers.setBorder(BorderFactory.createTitledBorder("Numbers of players"));
        numPlayers.setPreferredSize(new Dimension(440, 60));
        tranche1 = new JRadioButton("2");
        tranche2 = new JRadioButton("3");
        tranche3 = new JRadioButton("4");
        tranche4 = new JRadioButton("5");
        tranche5 = new JRadioButton("6");

        tranche1.setSelected(true);

        ButtonGroup bg = new ButtonGroup();
        bg.add(tranche1);
        bg.add(tranche2);
        bg.add(tranche3);
        bg.add(tranche4);
        bg.add(tranche5);
        numPlayers.add(tranche1);
        numPlayers.add(tranche2);
        numPlayers.add(tranche3);
        numPlayers.add(tranche4);
        numPlayers.add(tranche5);

        //------------- PART PLAYERS ------------------------------------------
        JPanel paPlayer;
        JPanel content = new JPanel();
        content.setBackground(Color.white);

        tfNomPlayer = new JTextField[NBMAXPLAYERS];
        cbPlayerType = new JComboBox[NBMAXPLAYERS];
        cbColors = new JComboBox[NBMAXPLAYERS];

        for (int i = 0; i < NBMAXPLAYERS; i++) {
            paPlayer = new JPanel();
            paPlayer.setBackground(Color.white);
            paPlayer.setPreferredSize(new Dimension(600, 55));
            paPlayer.setBorder(BorderFactory.createTitledBorder("Player" + (i + 1)));

            lbTypeJoueur = new JLabel("Player type : ");
            cbPlayerType[i] = new JComboBox<>();
            cbPlayerType[i].addItem(PlayerTypes.player);
            cbPlayerType[i].addItem(PlayerTypes.basicIA);
            //cbPlayerType[i].addItem(PlayerTypes.advancedIA);            
            nomLabel = new JLabel("Name :");
            colorsLabel = new JLabel("Color :");
            tfNomPlayer[i] = new JTextField();
            tfNomPlayer[i].setPreferredSize(new Dimension(100, 25));
            cbColors[i] = new JComboBox<>();
            cbColors[i].addItem("Red");
            cbColors[i].addItem("Blue");
            cbColors[i].addItem("Green");
            cbColors[i].addItem("Black");
            cbColors[i].addItem("Yellow");
            cbColors[i].addItem("Magenta");
            paPlayer.add(lbTypeJoueur, BorderLayout.WEST);
            paPlayer.add(cbPlayerType[i], BorderLayout.WEST);
            paPlayer.add(nomLabel, BorderLayout.CENTER);
            paPlayer.add(tfNomPlayer[i], BorderLayout.CENTER);
            paPlayer.add(colorsLabel, BorderLayout.EAST);
            paPlayer.add(cbColors[i], BorderLayout.EAST);
            content.add(paPlayer);
        }
        JPanel control = new JPanel();
        JButton okBouton = new JButton("PLAY");
        JButton btBack = new JButton("BACK");

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
                self.settings.setVisible(true);
                self.settings.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                self.setVisible(false);
                setVisible(false);
            }
        });

        okBouton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                zInfo = new ZDialogInfo(tfNomPlayer[0].getText(), getNumbersPlayers(), (String) cbColors[0].getSelectedItem(),
                        tfNomPlayer[1].getText(), (String) cbColors[1].getSelectedItem(),
                        tfNomPlayer[2].getText(), (String) cbColors[2].getSelectedItem(),
                        tfNomPlayer[3].getText(), (String) cbColors[3].getSelectedItem(),
                        tfNomPlayer[4].getText(), (String) cbColors[4].getSelectedItem(),
                        tfNomPlayer[5].getText(), (String) cbColors[5].getSelectedItem()
                );

                ClientWindow clientWindow;

                if (self != null) {
                    List<ParamPlayers> players = self.getPlayers();
                    Set<String> playableColors = new HashSet<>();
                    for(ParamPlayers player : players)
                    {
                        if(player.getPlayerType().equals(PlayerTypes.player))
                        {
                            playableColors.add(player.getColor());
                        }
                    }
                    clientWindow = new ClientWindow(self.getPlayers(), playableColors);
                }
                else {
                    clientWindow = new ClientWindow();
                }

                clientWindow.setVisible(true);

                clientWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                setVisible(false);

            }

            public String getNumbersPlayers()
            {
                return (tranche1.isSelected()) ? tranche1.getText()
                        : (tranche2.isSelected()) ? tranche2.getText()
                        : (tranche3.isSelected()) ? tranche3.getText()
                        : (tranche4.isSelected()) ? tranche4.getText()
                        : (tranche5.isSelected()) ? tranche5.getText()
                        : tranche1.getText();
            }
        });

        control.add(okBouton);
        control.add(btBack);
        control.setBackground(Color.white);

        this.getContentPane().add(panIcon, BorderLayout.WEST);
        this.getContentPane().add(content, BorderLayout.CENTER);
        this.getContentPane().add(control, BorderLayout.SOUTH);
    }

    /**
     * Allows to have the numbers of players selected
     *
     * @return
     */
    public String getNumbersPlayers()
    {
        return (tranche1.isSelected()) ? tranche1.getText()
                : (tranche2.isSelected()) ? tranche2.getText()
                : (tranche3.isSelected()) ? tranche3.getText()
                : (tranche4.isSelected()) ? tranche4.getText()
                : (tranche5.isSelected()) ? tranche5.getText()
                : tranche1.getText();
    }

    public ZDialogInfo showZDialog()
    {
        this.sendData = false;
        this.setVisible(true);
        return this.zInfo;
    }

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
}
