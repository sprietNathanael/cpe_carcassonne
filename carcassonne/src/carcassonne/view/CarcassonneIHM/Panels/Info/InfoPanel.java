/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.CarcassonneIHM.Panels.Info;

import carcassonne.controller.AbstractCarcassonneGameController;
import carcassonne.model.carcassonnegame.CarcassonneGame;
import carcassonne.model.player.Player;
import carcassonne.view.CarcassonneIHM.Panels.MainPanel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

/**
 * The information panel
 *
 * @author nathanael
 */
public class InfoPanel extends JPanel implements InfoPanelMouseListener
{

    // Constants
    public static int SEPARATION_LINE_WIDTH = 5;
    public static int MEEPLE_BUTTON_WIDTH = 100;
    public static int MEEPLE_BUTTON_HEIGHT = 70;
    public static int PREVIEW_BORDER = 10;
    public static int PREVIEWES_GAP = 30;

    private HashMap<String, PlayerInfo> playerInfoLines;
    private String currentPlayer;
    private AbstractCarcassonneGameController controller;
    private BufferedImage backTile;
    private BufferedImage currentTile;
    private BufferedImage noMeepleButtonImage;
    private int pileSize;
    private boolean displayPassMeepleTurnButton;
    private Polygon meepleButton;
    private InfoPanelMouseAdapter mouseListener;
    private String message;

    /**
     * Constructs the information panel
     *
     * @param players
     * @param controller
     */
    public InfoPanel(ArrayList<Player> players, AbstractCarcassonneGameController controller)
    {
        // Configure component
        setDoubleBuffered(true);
        setOpaque(false);
        setLayout(new MigLayout());

        this.controller = controller;

        this.displayPassMeepleTurnButton = false;

        // Adds the mouse listener
        this.mouseListener = new InfoPanelMouseAdapter(this);
        this.addMouseListener(this.mouseListener);

        // Creates information lines from players
        this.playerInfoLines = new HashMap<>();
        for (Player player : players) {
            this.playerInfoLines.put(player.getName(), new PlayerInfo(player.getName(), player.getColor()));
        }
        
        // Get the meeple button image
        try {
            this.noMeepleButtonImage = ImageIO.read(new File("resources/noMeeple.png"));
        } catch (IOException ex) {
            Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
        }


        // Get the back tile image
        try {
            this.backTile = ImageIO.read(new File("resources/tiles/back.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Initialise the message
        this.message = "";
    }

    /**
     * Refresh the informations from the game
     *
     * @param game
     */
    public void refresh(CarcassonneGame game)
    {
        // Get the current player
        this.currentPlayer = game.getCurrentPlayer().getName();

        // Updates the information lines
        for (Player player : game.getPlayers()) {
            this.playerInfoLines.get(player.getName()).updatePlayer(player.getUnusedMeepleNumber(), player.getPoints());
        }

        if (game.getCurrentTile() != null) {

            // Get the current tile image
            try {
                this.currentTile = ImageIO.read(new File("resources/tiles/" + game.getCurrentTile().getName() + ".jpg"));
            } catch (IOException ex) {
                Logger.getLogger(InfoPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        // Get the pile size
        this.pileSize = game.getPileSize();
    }

    /**
     * Ends a game
     *
     * @param game
     */
    public void endGame(CarcassonneGame game)
    {
        // Get the winner
        this.currentPlayer = game.getWinner().getName();

        // Updates the information lines
        for (Player player : game.getPlayers()) {
            this.playerInfoLines.get(player.getName()).updatePlayer(player.getUnusedMeepleNumber(), player.getPoints());
        }

        // Updates the message
        this.message = "Le joueur gagnant est : " + game.getWinner().getName();
        JOptionPane gg = new JOptionPane();
        ImageIcon img = new ImageIcon("resources/king.png");
        gg.showMessageDialog(null, "Le joueur gagnant est : " + game.getWinner().getName(), "WINNER", JOptionPane.INFORMATION_MESSAGE, img);

        // Reset the current tile
        this.currentTile = null;
    }

    /**
     * Paint the component
     */
    @Override
    protected void paintChildren(Graphics g)
    {
        int infoLinesHeight = this.getHeight() / 8;
        Graphics2D g2 = (Graphics2D) g;

        // Draw the back tile
        int currentHeight = 0;
        int previewSize = (int) (infoLinesHeight / 2);
        int previewX = InfoPanel.PREVIEW_BORDER;
        int previewY = currentHeight + (infoLinesHeight / 2) - (previewSize / 2);
        g2.drawImage(this.backTile, previewX, previewY, previewSize, previewSize, null);

        if (this.currentTile != null) {
            // Draw the pile counter
            g2.setFont(new Font("Calibri", Font.BOLD, (int) (previewSize * 0.3)));
            g2.drawString("" + this.pileSize, previewX, previewY - 5);
            g2.setFont(new Font("Calibri", Font.PLAIN, 12));

            // Draw preview tile
            previewX = InfoPanel.PREVIEW_BORDER + previewSize + InfoPanel.PREVIEWES_GAP;
            g2.drawImage(this.currentTile, previewX, previewY, previewSize, previewSize, null);
        }

        currentHeight += infoLinesHeight;

        // Draw every information lines
        for (Map.Entry<String, PlayerInfo> entry : this.playerInfoLines.entrySet()) {
            String key = entry.getKey();
            PlayerInfo value = entry.getValue();
            value.paint(g2, currentHeight, infoLinesHeight, this.getWidth() - InfoPanel.SEPARATION_LINE_WIDTH, key.equals(this.currentPlayer));
            currentHeight += infoLinesHeight;
        }

        if (!this.message.isEmpty()) {
            // Draw the message
            currentHeight += 20;
            g2.drawString(this.message, 20, currentHeight);
            currentHeight += 20;

        }

        if (this.displayPassMeepleTurnButton) {
            // Draw the pass meeple turn button
            
            double ratio = (double)this.noMeepleButtonImage.getHeight() / (double)this.noMeepleButtonImage.getWidth();
            double width = this.getWidth()/2.0;
            double height = width*ratio;
            int x = (int) ((this.getWidth() / 2.0) - (width / 2.0));
            int y = currentHeight;
            g2.drawImage(this.noMeepleButtonImage, x, y, (int)width, (int)height, null);
            int y_text = y + (int)height + 10;
            int x_text = x + 10;
            
            this.meepleButton = new Polygon();
            this.meepleButton.addPoint(x, y);
            this.meepleButton.addPoint(x+(int)width, y);
            this.meepleButton.addPoint(x+(int)width, y+(int)height);
            this.meepleButton.addPoint(x, y+(int)height);
            // Draw the inner message
            g2.drawString("Ne pas poser", x_text, y_text);
            g2.drawString("de Meeple", x_text, y_text + 16);
        }

        // Draw the separation line
        g2.setColor(Color.GRAY);
        g2.fillRect(this.getWidth() - InfoPanel.SEPARATION_LINE_WIDTH, 0, InfoPanel.SEPARATION_LINE_WIDTH, this.getHeight() - InfoPanel.SEPARATION_LINE_WIDTH);
        g2.setColor(Color.BLACK);
        super.paintChildren(g);
    }

    /**
     * Display the pass meeple turn button
     */
    public void displayPassMeepleTurnButton()
    {
        this.displayPassMeepleTurnButton = true;
    }

    /**
     * Hide the pass meeple turn button
     */
    public void hidePassMeepleTurnButton()
    {
        this.displayPassMeepleTurnButton = false;
        this.meepleButton = null;
    }

    /**
     * When the mouse is clicked
     *
     * @param e
     * @param p
     */
    @Override
    public void mouseClicked(MouseEvent e, Point2D p)
    {
        // If the mouse is clicked inside the button
        if (this.meepleButton != null && this.meepleButton.contains(p)) {
            // Hides the button
            this.hidePassMeepleTurnButton();

            // Ends the turn
            this.controller.endTurn();
        }
    }

}
