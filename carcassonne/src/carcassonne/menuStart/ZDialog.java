package carcassonne.menuStart;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class ZDialog extends JDialog
{

    private ZDialogInfo zInfo = new ZDialogInfo();
    private boolean sendData;
    private JLabel nomLabel, cheveuxLabel, icon, colorsLabel;
    private JRadioButton tranche1, tranche2, tranche3, tranche4, tranche5;
    private JComboBox cheveux;
    private JTextField nom, numbers;
    private JComboBox<String> colors;
    private JRadioButton nb2, nb3, nb4, nb5, nb6;

    public ZDialog(JFrame parent, String title, boolean modal)
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

    public ZDialogInfo showZDialog()
    {
        this.sendData = false;
        this.setVisible(true);
        return this.zInfo;
    }

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
        tranche1.setSelected(true);
        tranche2 = new JRadioButton("3");
        tranche3 = new JRadioButton("4");
        tranche4 = new JRadioButton("5");
        tranche5 = new JRadioButton("6");
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
        //Le nom
        /*JPanel panNom = new JPanel();
        panNom.setBackground(Color.white);
        panNom.setPreferredSize(new Dimension(220, 60));
        nom = new JTextField();
        nom.setPreferredSize(new Dimension(100, 25));
        panNom.setBorder(BorderFactory.createTitledBorder("Nom du personnage"));
        nomLabel = new JLabel("Saisir un nom :");
        panNom.add(nomLabel);
        panNom.add(nom);*/
        //La couleur des cheveux
        /*JPanel panCheveux = new JPanel();
        panCheveux.setBackground(Color.white);
        panCheveux.setPreferredSize(new Dimension(220, 60));
        panCheveux.setBorder(BorderFactory.createTitledBorder("Couleur de cheveux du personnage"));
        cheveux = new JComboBox();
        cheveux.addItem("Blond");
        cheveux.addItem("Brun");
        cheveux.addItem("Roux");
        cheveux.addItem("Blanc");
        cheveuxLabel = new JLabel("Cheveux");
        panCheveux.add(cheveuxLabel);
        panCheveux.add(cheveux);

        JPanel content = new JPanel();
        content.setBackground(Color.white);
        content.add(panNom);
        content.add(numPlayers);
        content.add(panCheveux);*/
        JPanel panNumb = new JPanel();
        panNumb.setBackground(Color.white);
        panNumb.setPreferredSize(new Dimension(400, 60));
        numbers = new JTextField();
        numbers.setPreferredSize(new Dimension(100, 25));
        panNumb.setBorder(BorderFactory.createTitledBorder("Numbers of players"));
        nomLabel = new JLabel("Numbers (between 2 and 6) :");
        panNumb.add(nomLabel, BorderLayout.WEST);
        panNumb.add(numbers, BorderLayout.EAST);

        JPanel player1 = CreatePlayer(1);
        JPanel player2 = CreatePlayer(2);
        JPanel player3 = CreatePlayer(3);
        JPanel player4 = CreatePlayer(4);
        JPanel player5 = CreatePlayer(5);
        JPanel player6 = CreatePlayer(6);

        JPanel content = new JPanel();
        content.setBackground(Color.white);
        //content.add(numb);
        //content.add(panNumb);
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
            public void actionPerformed(ActionEvent arg0)
            {
                zInfo = new ZDialogInfo(nom.getText(), getAge(), (String) cheveux.getSelectedItem());
                setVisible(false);
            }

            public String getAge()
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
            public void actionPerformed(ActionEvent arg0)
            {
                setVisible(false);
            }
        });

        control.add(okBouton);
        control.add(cancelBouton);
        
        this.getContentPane().add(panIcon, BorderLayout.WEST);
        this.getContentPane().add(panNumb, BorderLayout.NORTH);
        this.getContentPane().add(content, BorderLayout.CENTER);
        this.getContentPane().add(control, BorderLayout.SOUTH);
    }

    private JPanel CreatePlayer(int x)
    {

        JPanel player = new JPanel();
        player.setBackground(Color.white);
        player.setPreferredSize(new Dimension(600, 60));
        player.setBorder(BorderFactory.createTitledBorder("Player " + x));
        nomLabel = new JLabel("Name :");
        colorsLabel = new JLabel("Color :");

        nom = new JTextField();
        nom.setPreferredSize(new Dimension(100, 25));

        colors = new JComboBox<>();
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
