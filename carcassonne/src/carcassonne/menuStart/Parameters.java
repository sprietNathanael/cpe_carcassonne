package carcassonne.menuStart;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
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

public class Parameters extends JDialog
{

    private ZDialogInfo zInfo = new ZDialogInfo();
    private boolean sendData;
    private JLabel nomLabel, icon, colorsLabel;
    private JRadioButton tranche1, tranche2, tranche3, tranche4, tranche5;
    private JTextField nomJ1, nomJ2, nomJ3, nomJ4, nomJ5, nomJ6;
    private JComboBox<String> colorsJ1, colorsJ2, colorsJ3, colorsJ4, colorsJ5, colorsJ6;

    public Parameters(JFrame parent, String title, boolean modal)
    {
        super(parent, title, modal);
        this.setIconImage(new ImageIcon(getClass().getResource("/images/icone carcassonne.jpg")).getImage());
        this.setSize(1100, 950);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        Toolkit tk = Toolkit.getDefaultToolkit();
        this.setCursor(tk.createCustomCursor(new ImageIcon(getClass().getResource("/images/curseur.png")).getImage(), new Point(0, 0), "nameCursor"));

        this.initComponent();
    }

    /**
     * Allows to init the differents components for the game
     */
    private void initComponent()
    {
        //Icône
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
        JPanel player1 = new JPanel();
        player1.setBackground(Color.white);
        player1.setPreferredSize(new Dimension(600, 55));
        player1.setBorder(BorderFactory.createTitledBorder("Player1"));
        nomLabel = new JLabel("Name :");
        colorsLabel = new JLabel("Color :");
        nomJ1 = new JTextField();
        nomJ1.setPreferredSize(new Dimension(100, 25));
        colorsJ1 = new JComboBox<>();
        colorsJ1.addItem("Red");
        colorsJ1.addItem("Blue");
        colorsJ1.addItem("Green");
        colorsJ1.addItem("Black");
        colorsJ1.addItem("Yellow");
        colorsJ1.addItem("Violet");
        player1.add(nomLabel, BorderLayout.WEST);
        player1.add(nomJ1, BorderLayout.WEST);
        player1.add(colorsLabel, BorderLayout.EAST);
        player1.add(colorsJ1, BorderLayout.EAST);

        JPanel player2 = new JPanel();
        player2.setBackground(Color.white);
        player2.setPreferredSize(new Dimension(600, 55));
        player2.setBorder(BorderFactory.createTitledBorder("Player2"));
        nomLabel = new JLabel("Name :");
        colorsLabel = new JLabel("Color :");
        nomJ2 = new JTextField();
        nomJ2.setPreferredSize(new Dimension(100, 25));
        colorsJ2 = new JComboBox<>();
        colorsJ2.addItem("Red");
        colorsJ2.addItem("Blue");
        colorsJ2.addItem("Green");
        colorsJ2.addItem("Black");
        colorsJ2.addItem("Yellow");
        colorsJ2.addItem("Violet");
        player2.add(nomLabel, BorderLayout.WEST);
        player2.add(nomJ2, BorderLayout.WEST);
        player2.add(colorsLabel, BorderLayout.EAST);
        player2.add(colorsJ2, BorderLayout.EAST);

        JPanel player3 = new JPanel();
        player3.setBackground(Color.white);
        player3.setPreferredSize(new Dimension(600, 55));
        player3.setBorder(BorderFactory.createTitledBorder("Player3"));
        nomLabel = new JLabel("Name :");
        colorsLabel = new JLabel("Color :");
        nomJ3 = new JTextField();
        nomJ3.setPreferredSize(new Dimension(100, 25));
        colorsJ3 = new JComboBox<>();
        colorsJ3.addItem("Red");
        colorsJ3.addItem("Blue");
        colorsJ3.addItem("Green");
        colorsJ3.addItem("Black");
        colorsJ3.addItem("Yellow");
        colorsJ3.addItem("Violet");
        player3.add(nomLabel, BorderLayout.WEST);
        player3.add(nomJ3, BorderLayout.WEST);
        player3.add(colorsLabel, BorderLayout.EAST);
        player3.add(colorsJ3, BorderLayout.EAST);

        JPanel player4 = new JPanel();
        player4.setBackground(Color.white);
        player4.setPreferredSize(new Dimension(600, 55));
        player4.setBorder(BorderFactory.createTitledBorder("Player4"));
        nomLabel = new JLabel("Name :");
        colorsLabel = new JLabel("Color :");
        nomJ4 = new JTextField();
        nomJ4.setPreferredSize(new Dimension(100, 25));
        colorsJ4 = new JComboBox<>();
        colorsJ4.addItem("Red");
        colorsJ4.addItem("Blue");
        colorsJ4.addItem("Green");
        colorsJ4.addItem("Black");
        colorsJ4.addItem("Yellow");
        colorsJ4.addItem("Violet");
        player4.add(nomLabel, BorderLayout.WEST);
        player4.add(nomJ4, BorderLayout.WEST);
        player4.add(colorsLabel, BorderLayout.EAST);
        player4.add(colorsJ4, BorderLayout.EAST);

        JPanel player5 = new JPanel();
        player5.setBackground(Color.white);
        player5.setPreferredSize(new Dimension(600, 55));
        player5.setBorder(BorderFactory.createTitledBorder("Player5"));
        nomLabel = new JLabel("Name :");
        colorsLabel = new JLabel("Color :");
        nomJ5 = new JTextField();
        nomJ5.setPreferredSize(new Dimension(100, 25));
        colorsJ5 = new JComboBox<>();
        colorsJ5.addItem("Red");
        colorsJ5.addItem("Blue");
        colorsJ5.addItem("Green");
        colorsJ5.addItem("Black");
        colorsJ5.addItem("Yellow");
        colorsJ5.addItem("Violet");
        player5.add(nomLabel, BorderLayout.WEST);
        player5.add(nomJ5, BorderLayout.WEST);
        player5.add(colorsLabel, BorderLayout.EAST);
        player5.add(colorsJ5, BorderLayout.EAST);

        JPanel player6 = new JPanel();
        player6.setBackground(Color.white);
        player6.setPreferredSize(new Dimension(600, 55));
        player6.setBorder(BorderFactory.createTitledBorder("Player6"));
        nomLabel = new JLabel("Name :");
        colorsLabel = new JLabel("Color :");
        nomJ6 = new JTextField();
        nomJ6.setPreferredSize(new Dimension(100, 25));
        colorsJ6 = new JComboBox<>();
        colorsJ6.addItem("Red");
        colorsJ6.addItem("Blue");
        colorsJ6.addItem("Green");
        colorsJ6.addItem("Black");
        colorsJ6.addItem("Yellow");
        colorsJ6.addItem("Violet");
        player6.add(nomLabel, BorderLayout.WEST);
        player6.add(nomJ6, BorderLayout.WEST);
        player6.add(colorsLabel, BorderLayout.EAST);
        player6.add(colorsJ6, BorderLayout.EAST);

        JPanel content = new JPanel();
        content.setBackground(Color.white);

        content.add(player1);
        content.add(player2);
        content.add(player3);
        content.add(player4);
        content.add(player5);
        content.add(player6);

        JPanel control = new JPanel();
        JButton okBouton = new JButton("OK");

        okBouton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                zInfo = new ZDialogInfo(nomJ1.getText(), getNumbersPlayers(), (String) colorsJ1.getSelectedItem(),
                        nomJ2.getText(), (String) colorsJ2.getSelectedItem(),
                        nomJ3.getText(), (String) colorsJ3.getSelectedItem(),
                        nomJ4.getText(), (String) colorsJ4.getSelectedItem(),
                        nomJ5.getText(), (String) colorsJ5.getSelectedItem(),
                        nomJ6.getText(), (String) colorsJ6.getSelectedItem()
                );
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

        JButton cancelBouton = new JButton("CANCEL");
        cancelBouton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                setVisible(false);
            }
        });

        control.add(okBouton);
        control.add(cancelBouton);

        this.getContentPane().add(panIcon, BorderLayout.WEST);
        this.getContentPane().add(numPlayers, BorderLayout.NORTH);
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
    public List<Players> getDataPlayers()
    {
        List<Players> li = new LinkedList<Players>();

        Players p1 = new Players(nomJ1.getText(), colorsJ1.toString());
        Players p2 = new Players(nomJ2.getText(), colorsJ2.toString());
        Players p3 = new Players(nomJ3.getText(), colorsJ3.toString());
        Players p4 = new Players(nomJ4.getText(), colorsJ4.toString());
        Players p5 = new Players(nomJ5.getText(), colorsJ5.toString());
        Players p6 = new Players(nomJ6.getText(), colorsJ6.toString());
        //int nbPlayers = 0;

        // nbPlayers = Integer.parseInt(this.getNumbersPlayers());
        if (p1.getNom() != null) {
            li.add(p1);
        }

        if (p2.getNom() != null) {
            li.add(p2);
        }
        if (p3.getNom() != null) {
            li.add(p3);
        }
        if (p4.getNom() != null) {
            li.add(p4);
        }
        if (p5.getNom() != null) {
            li.add(p5);
        }
        if (p6.getNom() != null) {
            li.add(p6);
        }

        return li;
    }

}
