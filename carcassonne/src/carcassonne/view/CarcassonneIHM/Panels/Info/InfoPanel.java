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
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.GradientPaint;
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
import java.util.LinkedHashMap;
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
    private static final Composite CLEAR = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);
    private static final Composite FORBIDDED = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .6f);
    public static int RELIEF_GRADIENT_THICKNESS = 30;
    public static int HIGHLIGHT_GRADIENT_THICKNESS = 10;
    public static int GAPS_BETWEEN_LINES = 10;
    public static int MEEPLE_BUTTON_WIDTH = 100;
    public static int MEEPLE_BUTTON_HEIGHT = 70;
    public static int PREVIEW_BORDER = 10;
    public static int PREVIEWES_GAP = 30;

    private LinkedHashMap<String, PlayerInfo> playerInfoLines;
    private String currentPlayer;
    private AbstractCarcassonneGameController controller;
    private BufferedImage backTile;
    private BufferedImage currentTile;
    private BufferedImage noMeepleButtonImage;
    private BufferedImage bigMeepleOnImage;
    private BufferedImage bigMeepleOffImage;
    private BufferedImage forestTexture;
    private BufferedImage stoneTexture;
    private BufferedImage fields_on;
    private BufferedImage fields_off;
    private int pileSize;
    private boolean displayPassMeepleTurnButton;
    private Polygon meepleButton;
    private Polygon bigMeepleButton;
    private Polygon fieldsButton;
    private InfoPanelMouseAdapter mouseListener;
    private String message;
    private boolean fieldsOn;
    private boolean bigMeepleOn;
    private boolean bigMeepleAvailable;
    private boolean bigMeepleExists;
    private MainPanel mainPanel;

    /**
     * Constructs the information panel
     *
     * @param players
     * @param controller
     */
    public InfoPanel(ArrayList<Player> players, AbstractCarcassonneGameController controller, MainPanel mainPanel)
    {
        // Configure component
        setDoubleBuffered(true);
        setOpaque(false);
        setLayout(new MigLayout());

        this.controller = controller;
        this.mainPanel = mainPanel;
        this.displayPassMeepleTurnButton = false;
        this.fieldsOn = false;

        // Adds the mouse listener
        this.mouseListener = new InfoPanelMouseAdapter(this);
        this.addMouseListener(this.mouseListener);
        this.bigMeepleAvailable = false;
        this.bigMeepleOn = true;
        this.bigMeepleExists = true;

        // Creates information lines from players
        this.playerInfoLines = new LinkedHashMap<>();
        for (Player player : players) {
            this.playerInfoLines.put(player.getName(), new PlayerInfo(player.getName(), player.getColor()));
        }
        
        // Get the meeple button image
        try {
            this.noMeepleButtonImage = ImageIO.read(new File("resources/noMeeple.png"));
        } catch (IOException ex) {
            Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Get the big_meeple_on button image
        try {
            this.bigMeepleOnImage = ImageIO.read(new File("resources/big_meeple_on.png"));
        } catch (IOException ex) {
            Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Get the big_meeple_off button image
        try {
            this.bigMeepleOffImage = ImageIO.read(new File("resources/big_meeple_off.png"));
        } catch (IOException ex) {
            Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Get the back tile image
        try {
            this.backTile = ImageIO.read(new File("resources/tiles/back.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Get the forest texture image
        try {
            this.forestTexture = ImageIO.read(new File("resources/textures/forest.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Get the forest texture image
        try {
            this.stoneTexture = ImageIO.read(new File("resources/textures/stone.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Get the fields on icon
        try {
            this.fields_on = ImageIO.read(new File("resources/fields_on.png"));
        } catch (IOException ex) {
            Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Get the fiels off icon
        try {
            this.fields_off = ImageIO.read(new File("resources/fields_off.png"));
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
                this.currentTile = ImageIO.read(new File("resources/tiles/" + game.getCurrentTile().getPath() + "/"+ game.getCurrentTile().getName() + ".jpg"));
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
        int infoLinesHeight = this.getHeight() / 9;
        Graphics2D g2 = (Graphics2D) g;
        
        BufferedImage stoneTexture_cropped = this.stoneTexture.getSubimage(0, 0, this.getWidth(), this.getHeight());
        g2.drawImage(stoneTexture_cropped, 0, 0, this.getWidth(), this.getHeight(), null);
        int forest_x = InfoPanel.GAPS_BETWEEN_LINES;
        int forest_y = InfoPanel.GAPS_BETWEEN_LINES;
        int forest_w = this.getWidth() - (2*InfoPanel.GAPS_BETWEEN_LINES);
        int forest_h = infoLinesHeight - (2*InfoPanel.GAPS_BETWEEN_LINES);
        
        //BufferedImage stoneTexture_cropped = this.stoneTexture.getSubimage(0, 0, this.getWidth(), this.getHeight());
        BufferedImage forestTexture_cropped = this.forestTexture.getSubimage(0, 0, forest_w, forest_h);
        g2.drawImage(forestTexture_cropped, forest_x, forest_y, forest_w, forest_h, null);
        
        GradientPaint gradient = new GradientPaint(forest_x, forest_y, new Color(30, 30, 30, 255), forest_x+InfoPanel.RELIEF_GRADIENT_THICKNESS, forest_y,  new Color(0, 0, 0, 0));
        g2.setPaint(gradient);
        g2.fillRect(forest_x, forest_y,  InfoPanel.RELIEF_GRADIENT_THICKNESS, forest_h);
        
        gradient = new GradientPaint(forest_x+forest_w-InfoPanel.RELIEF_GRADIENT_THICKNESS, forest_y, new Color(0, 0, 0, 0), forest_x+forest_w, forest_y,  new Color(30, 30, 30, 255));
        g2.setPaint(gradient);
        g2.fillRect(forest_x+forest_w-InfoPanel.RELIEF_GRADIENT_THICKNESS, forest_y, InfoPanel.RELIEF_GRADIENT_THICKNESS, forest_h);
        
        gradient = new GradientPaint(forest_x, forest_y, new Color(30, 30, 30, 255), forest_x, forest_y+InfoPanel.RELIEF_GRADIENT_THICKNESS, new Color(0, 0, 0, 0));
        g2.setPaint(gradient);
        g2.fillRect(forest_x, forest_y, forest_w, InfoPanel.RELIEF_GRADIENT_THICKNESS);
        
        gradient = new GradientPaint(forest_x, forest_y+forest_h - InfoPanel.RELIEF_GRADIENT_THICKNESS, new Color(0, 0, 0, 0), forest_x, forest_y+forest_h,  new Color(30, 30, 30, 255));
        g2.setPaint(gradient);
        g2.fillRect(forest_x, forest_y+forest_h - InfoPanel.RELIEF_GRADIENT_THICKNESS, forest_w, InfoPanel.RELIEF_GRADIENT_THICKNESS);
        
        g2.setColor(Color.black);
        
        // Draw the back tile
        int currentHeight = 0;
        int previewSize = (int) (infoLinesHeight / 2);
        int previewX = forest_x+InfoPanel.PREVIEW_BORDER;
        int previewY = currentHeight + (infoLinesHeight / 2) - (previewSize / 2);
        g2.drawImage(this.backTile, previewX, previewY, previewSize, previewSize, null);

        if (this.currentTile != null) {
            // Draw the pile counter
            g2.setColor(new Color(200,200,200,200));
            float circleSize = (float)(previewSize/1.5);
            int circle_x = previewX + (int)(previewSize/2.0) - (int)(circleSize/2.0);
            int circle_y = previewY + (int)(previewSize/2.0) - (int)(circleSize/2.0);
            //g2.fillOval(circle_x, circle_y, (int)circleSize, (int)circleSize);
            
            
            g2.setColor(Color.black);
            g2.setFont(new Font("Calibri", Font.BOLD, (int) (previewSize * 0.4)));
            g2.drawString("" + this.pileSize, circle_x+(int)(circleSize/2.0)-5, circle_y+(int)(circleSize/2.0)+5);
            g2.setFont(new Font("Calibri", Font.PLAIN, 12));

            // Draw preview tile
            previewX += previewSize + InfoPanel.PREVIEWES_GAP;
            g2.drawImage(this.currentTile, previewX, previewY, previewSize, previewSize, null);
            
            gradient = new GradientPaint(previewX, previewY, new Color(255, 255, 255, 200), previewX, previewY-InfoPanel.HIGHLIGHT_GRADIENT_THICKNESS,  new Color(0, 0, 0, 0));
            g2.setPaint(gradient);            
            Polygon border = new Polygon();
            border.addPoint(previewX-InfoPanel.HIGHLIGHT_GRADIENT_THICKNESS, previewY - InfoPanel.HIGHLIGHT_GRADIENT_THICKNESS);
            border.addPoint(previewX+previewSize+InfoPanel.HIGHLIGHT_GRADIENT_THICKNESS, previewY - InfoPanel.HIGHLIGHT_GRADIENT_THICKNESS);
            border.addPoint(previewX+previewSize, previewY);
            border.addPoint(previewX, previewY);
            g2.fillPolygon(border);

            gradient = new GradientPaint(previewX+previewSize, previewY, new Color(255, 255, 255, 200), previewX+previewSize + InfoPanel.HIGHLIGHT_GRADIENT_THICKNESS, previewY,  new Color(0,0,0,0));
            g2.setPaint(gradient);
            
            border.reset();
            border.addPoint(previewX+previewSize+InfoPanel.HIGHLIGHT_GRADIENT_THICKNESS, previewY - InfoPanel.HIGHLIGHT_GRADIENT_THICKNESS);
            border.addPoint(previewX+previewSize+InfoPanel.HIGHLIGHT_GRADIENT_THICKNESS, previewY +previewSize + InfoPanel.HIGHLIGHT_GRADIENT_THICKNESS);
            border.addPoint(previewX+previewSize, previewY +previewSize);
            border.addPoint(previewX+previewSize, previewY );
            g2.fillPolygon(border);
            
            gradient = new GradientPaint(previewX, previewY+previewSize, new Color(255,255,255, 200), previewX, previewY+previewSize+InfoPanel.HIGHLIGHT_GRADIENT_THICKNESS, new Color(0, 0, 0, 0));
            g2.setPaint(gradient);
            
            border.reset();
            border.addPoint(previewX+previewSize+InfoPanel.HIGHLIGHT_GRADIENT_THICKNESS, previewY + previewSize + InfoPanel.HIGHLIGHT_GRADIENT_THICKNESS);
            border.addPoint(previewX - InfoPanel.HIGHLIGHT_GRADIENT_THICKNESS, previewY +previewSize + InfoPanel.HIGHLIGHT_GRADIENT_THICKNESS);
            border.addPoint(previewX, previewY +previewSize);
            border.addPoint(previewX+previewSize, previewY + previewSize );
            g2.fillPolygon(border);

            gradient = new GradientPaint(previewX, previewY, new Color(255,255,255, 200), previewX - InfoPanel.HIGHLIGHT_GRADIENT_THICKNESS, previewY,  new Color(0, 0, 0, 0));
            g2.setPaint(gradient);
            
            border.reset();
            border.addPoint(previewX-InfoPanel.HIGHLIGHT_GRADIENT_THICKNESS, previewY - InfoPanel.HIGHLIGHT_GRADIENT_THICKNESS);
            border.addPoint(previewX, previewY);
            border.addPoint(previewX, previewY +previewSize);
            border.addPoint(previewX-InfoPanel.HIGHLIGHT_GRADIENT_THICKNESS, previewY + previewSize + InfoPanel.HIGHLIGHT_GRADIENT_THICKNESS);
            g2.fillPolygon(border);
            
            /*gradient = new GradientPaint(previewX, previewY, new Color(255,255,255, 150), previewX+previewSize, previewY+previewSize,  new Color(0, 0, 0, 0));
            g2.setPaint(gradient);
            g2.fillRect(previewX, previewY, previewSize, previewSize);*/
            
            g2.setColor(Color.black);
        }

        currentHeight += infoLinesHeight;

        // Draw every information lines
        for (Map.Entry<String, PlayerInfo> entry : this.playerInfoLines.entrySet()) {
            String key = entry.getKey();
            PlayerInfo value = entry.getValue();
            value.paint(g2, currentHeight, infoLinesHeight, this.getWidth(), key.equals(this.currentPlayer));
            currentHeight += infoLinesHeight+InfoPanel.GAPS_BETWEEN_LINES;
        }

        if (!this.message.isEmpty()) {
            // Draw the message
            currentHeight += 20;
            g2.drawString(this.message, 20, currentHeight);
            currentHeight += 20;

        }
        
        this.meepleButton = null;
        this.bigMeepleButton = null;
        if (this.displayPassMeepleTurnButton) {
            // Draw the pass meeple turn button
            
            double ratio = (double)this.noMeepleButtonImage.getHeight() / (double)this.noMeepleButtonImage.getWidth();
            double width = this.getWidth()/3.0;
            double meeples_button_height = width*ratio;
            int x = (int) ((this.getWidth() / 4.0) - (width/2.0));
            int y = currentHeight;
            g2.drawImage(this.noMeepleButtonImage, x, y, (int)width, (int)meeples_button_height, null);
            
            this.meepleButton = new Polygon();
            this.meepleButton.addPoint(x, y);
            this.meepleButton.addPoint(x+(int)width, y);
            this.meepleButton.addPoint(x+(int)width, y+(int)meeples_button_height);
            this.meepleButton.addPoint(x, y+(int)meeples_button_height);
            
            if(this.bigMeepleExists)
            {
                x = (int) ((3*(this.getWidth() / 4.0)) - (width/2.0));
                if(this.bigMeepleAvailable)
                {
                    this.bigMeepleButton = new Polygon();
                    this.bigMeepleButton.addPoint(x, y);
                    this.bigMeepleButton.addPoint(x+(int)width, y);
                    this.bigMeepleButton.addPoint(x+(int)width, y+(int)meeples_button_height);
                    this.bigMeepleButton.addPoint(x, y+(int)meeples_button_height);

                }
                else
                {
                    g2.setComposite(FORBIDDED);
                }

                if(this.bigMeepleOn)
                {
                    g2.drawImage(this.bigMeepleOnImage, x, y, (int)width, (int)meeples_button_height, null);
                }
                else{
                    g2.drawImage(this.bigMeepleOffImage, x, y, (int)width, (int)meeples_button_height, null);                    
                }
                g2.setComposite(CLEAR);
            }
            
            
            currentHeight += meeples_button_height;
        }
        
        double ratio_FieldsIcon = (double)this.fields_on.getHeight() / (double)this.fields_on.getWidth();
        double width_FieldsIcon = this.getWidth()/3.0;
        double height_FieldsIcon = width_FieldsIcon*ratio_FieldsIcon;
        int x_FieldsIcon = (int) ((this.getWidth() / 2.0) - (width_FieldsIcon / 2.0));
        int y_FieldsIcon = currentHeight;
        if(this.fieldsOn)
        {
            g2.drawImage(this.fields_off, x_FieldsIcon, y_FieldsIcon, (int)width_FieldsIcon, (int)height_FieldsIcon, null);            
        }
        else
        {
            g2.drawImage(this.fields_on, x_FieldsIcon, y_FieldsIcon, (int)width_FieldsIcon, (int)height_FieldsIcon, null);
        }
        
        this.fieldsButton = new Polygon();
        this.fieldsButton.addPoint(x_FieldsIcon, y_FieldsIcon);
        this.fieldsButton.addPoint(x_FieldsIcon+(int)width_FieldsIcon, y_FieldsIcon);
        this.fieldsButton.addPoint(x_FieldsIcon+(int)width_FieldsIcon, y_FieldsIcon+(int)height_FieldsIcon);
        this.fieldsButton.addPoint(x_FieldsIcon, y_FieldsIcon+(int)height_FieldsIcon);
        
        currentHeight+=height_FieldsIcon;

       
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
        else if (this.fieldsButton != null && this.fieldsButton.contains(p)) {
            // Hides the button
            this.mainPanel.switchFields();
            this.fieldsOn = !this.fieldsOn;
        }
        else if (this.bigMeepleButton != null && this.bigMeepleButton.contains(p)) {
            // Hides the button
            //this.mainPanel.switchFields();t
            this.bigMeepleOn = !(this.bigMeepleOn);
            
            this.repaint();
        }
    }

}
