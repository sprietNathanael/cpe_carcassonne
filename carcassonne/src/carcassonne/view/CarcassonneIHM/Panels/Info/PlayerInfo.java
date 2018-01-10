/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.CarcassonneIHM.Panels.Info;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
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
public class PlayerInfo
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

    /**
     * Construct a Player info
     *
     * @param name
     * @param color
     */
    public PlayerInfo(String name, String color)
    {
        this.color = color.toLowerCase();
        System.out.println(this.color);
        this.name = name;
        this.buildImages();
    }

    /**
     * Updates the player informations
     *
     * @param meeples
     * @param points
     */
    public void updatePlayer(int meeples, int points)
    {
        this.meepleNumber = meeples;
        this.pointNumber = points;
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
        
        // Get the hollow meeple image
        try {
            this.coinImage = ImageIO.read(new File("resources/textures/coin.png"));
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
        g2.drawImage(this.plankTexture, 0, currentHeight, width, height, null);
        
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
        g2.setFont(new Font("Calibri", Font.PLAIN, fontSize));
        g2.setColor(Color.BLACK);
        int iconsSize = (int) (height / 5.0);
        
        int coin_x = width - (int)(width/1.75)-iconsSize;
        int coin_y = currentHeight + height - (int)(height/5.0) - iconsSize;
        
        int pointText_x = coin_x + iconsSize + fontSize/2;
        int pointText_y = coin_y + iconsSize/2 + fontSize/2;
        g2.drawImage(this.coinImage, coin_x, coin_y, iconsSize, iconsSize, null);
        g2.drawString("" + this.pointNumber, pointText_x, pointText_y);
        
        int hollowMeeple_x = width - (int)(width/3.0)-iconsSize;
        int hollowMeeple_y = currentHeight + height - (int)(height/5.0) - iconsSize;
        g2.drawImage(this.hollowMeeple, hollowMeeple_x, hollowMeeple_y, iconsSize, iconsSize, null);
        
        int meepleText_x = hollowMeeple_x + iconsSize + fontSize/2;
        int meepleText_y = hollowMeeple_y + iconsSize/2 + fontSize/2;
        g2.drawString("" + this.meepleNumber, meepleText_x, meepleText_y);

        // Draw the informations
        int nameX = meepleX + meepleSize + PlayerInfo.MEEPLE_BORDER;
        g2.setFont(new Font("Calibri", Font.PLAIN, 18));
        g2.drawString(this.name, nameX, meepleY + meepleSize / 4);

        
    }
}
