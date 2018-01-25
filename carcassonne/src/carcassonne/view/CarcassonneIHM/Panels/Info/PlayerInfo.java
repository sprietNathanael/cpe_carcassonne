/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.CarcassonneIHM.Panels.Info;

import carcassonne.model.player.Player;
import carcassonne.view.CarcassonneIHM.ScoreDialog;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * Graphical informations line about a player
 *
 * @author nathanael
 */
public class PlayerInfo implements InfoPanelMouseListener
{
    // Constants
    public static final int MEEPLE_BORDER = 10;
    public static final int CURRENT_PLAYER_BORDER_WIDTH = 5;
    
    private String color;
    private String name;
    private BufferedImage image;
    private BufferedImage image_bordered;
    private int meepleNumber;
    private int pointNumber;
    private BufferedImage plankTexture;
    private BufferedImage coinImage;
    private BufferedImage hollowMeeple;
    private BufferedImage hollowMeeple_big;
    private BufferedImage infoImage;
    private int bigMeepleNumber;
    private Rectangle infoButton;
    private Player player;

    /**
     * Construct a Player info
     *
     * @param name
     * @param color
     */
    public PlayerInfo(Player player, InfoPanelMouseAdapter adapter)
    {
        this.player = player;
        this.color = player.getColor().toLowerCase();
        adapter.addListener(this);
        this.buildImages();
    }

    /**
     * Updates the player informations
     *
     * @param meeples
     * @param points
     */
    public void updatePlayer(Player player, int bigMeeple)
    {
        this.player = player;
        this.meepleNumber = bigMeeple == 1 ? player.getUnusedMeepleNumber()-1 : player.getUnusedMeepleNumber();
        this.bigMeepleNumber = bigMeeple;
        this.pointNumber = player.getPoints();
    }

    /**
     * Get the meeple image
     */
    public void buildImages()
    {
        this.image = null;
        try {
            this.image = ImageIO.read(new File("resources/meeples_bordered/" + this.color + ".png"));
        } catch (IOException ex) {
            Logger.getLogger(PlayerInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.image_bordered = null;
        try {
            this.image_bordered = ImageIO.read(new File("resources/meeples_bordered/" + this.color + "_hollowed.png"));
        } catch (IOException ex) {
            Logger.getLogger(PlayerInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Get the plank texture image
        try {
            this.plankTexture = ImageIO.read(new File("resources/textures/plank.png"));
        } catch (IOException ex) {
            Logger.getLogger(PlayerInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Get the hollow meeple image
        try {
            this.hollowMeeple = ImageIO.read(new File("resources/meeples_bordered/brown.png"));
        } catch (IOException ex) {
            Logger.getLogger(PlayerInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         // Get the hollow big meeple image
        try {
            this.hollowMeeple_big = ImageIO.read(new File("resources/meeples_bordered/brown_big.png"));
        } catch (IOException ex) {
            Logger.getLogger(PlayerInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Get the hollow meeple image
        try {
            this.coinImage = ImageIO.read(new File("resources/textures/coin.png"));
        } catch (IOException ex) {
            Logger.getLogger(PlayerInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Get the info image
        try {
            this.infoImage = ImageIO.read(new File("resources/info.png"));
        } catch (IOException ex) {
            Logger.getLogger(PlayerInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Paints the informations
     *
     * @param g2
     * @param currentHeight
     * @param height
     * @param width
     * @param currentPlayer
     */
    public void paint(Graphics2D g2, int currentHeight, int height, int width, boolean currentPlayer)
    {
        g2.drawImage(this.plankTexture, 5, currentHeight, width-10, height, null);
              
        int meepleSize = (int) (height / 2);
        int meepleX = PlayerInfo.MEEPLE_BORDER;
        int meepleY = currentHeight + (height / 2) - (meepleSize / 2);
        // If this is the current player, draw the plain meeple
        if (currentPlayer) {
            // Draw the meeple at the center
            g2.drawImage(this.image, meepleX, meepleY, meepleSize, meepleSize, null);
        }
        else
        {
            g2.drawImage(this.image_bordered, meepleX, meepleY, meepleSize, meepleSize, null);            
        }
        
        int fontSize = height/6;
        g2.setFont(new Font("Times New Roman", Font.PLAIN, fontSize));
        g2.setColor(Color.BLACK);
        int iconsSize = (int) (height / 5.0);
        
        int coin_x = width - (int)(width/1.5)-iconsSize;
        int coin_y = currentHeight + height - (int)(height/5.0) - iconsSize;
        g2.drawImage(this.coinImage, coin_x, coin_y, iconsSize, iconsSize, null);
        
        int pointText_x = coin_x + iconsSize + fontSize/2;
        int pointText_y = coin_y + iconsSize/2 + fontSize/2;
        g2.drawString("" + this.pointNumber, pointText_x, pointText_y);
        
        int hollowMeeple_x = width - (int)(width/2.3)-iconsSize;
        int hollowMeeple_y = currentHeight + height - (int)(height/5.0) - iconsSize;
        g2.drawImage(this.hollowMeeple, hollowMeeple_x, hollowMeeple_y, iconsSize, iconsSize, null);
        
        int meepleText_x = hollowMeeple_x + iconsSize + fontSize/2;
        int meepleText_y = hollowMeeple_y + iconsSize/2 + fontSize/2;
        g2.drawString("" + this.meepleNumber, meepleText_x, meepleText_y);
        
        if(this.bigMeepleNumber != -1)
        {
            int hollowMeeple_big_x = width - (int)(width/3.7)-iconsSize;
            int hollowMeeple_big_y = currentHeight + height - (int)(height/5.0) - iconsSize;
            g2.drawImage(this.hollowMeeple_big, hollowMeeple_big_x, hollowMeeple_big_y, iconsSize, iconsSize, null);

            int meepleText_big_x = hollowMeeple_big_x + iconsSize + fontSize/2;
            int meepleText_big_y = hollowMeeple_big_y + iconsSize/2 + fontSize/2;
            g2.drawString("" + this.bigMeepleNumber, meepleText_big_x, meepleText_big_y);
        }

        // Draw the informations
        int nameX = meepleX + meepleSize + PlayerInfo.MEEPLE_BORDER;
        g2.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        g2.drawString(this.player.getName(), nameX, meepleY + meepleSize / 4);
        
        int infoSize = (int)(height/4.0);
        int infoY = currentHeight+ ((int)(height / 2.0))- ((int)(infoSize/2.0));
        int infoX = 4*(int)(width/5.0);
        this.infoButton = new Rectangle(infoX, infoY, infoSize, infoSize);
        g2.drawImage(this.infoImage, infoX, infoY, infoSize, infoSize, null);

        
    }

    @Override
    public void mouseClicked(MouseEvent e, Point2D p)
    {
        if(this.infoButton.contains(p))
        {
            try {
                new ScoreDialog(this.player);
            } catch (IOException ex) {
                Logger.getLogger(PlayerInfo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
