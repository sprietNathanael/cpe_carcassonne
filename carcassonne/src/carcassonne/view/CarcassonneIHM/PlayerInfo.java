/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.CarcassonneIHM;

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

    private String color;
    private String name;
    private BufferedImage image;
    public static final int MEEPLE_BORDER = 10;
    public static final int CURRENT_PLAYER_BORDER_WIDTH = 5;
    private int meepleNumber;
    private int pointNumber;

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
        this.buildImage();
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
    public void buildImage()
    {
        this.image = null;
        try {
            this.image = ImageIO.read(new File("resources/meeples/" + this.color + ".png"));
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
        // Draw the meeple at the center
        int meepleSize = (int) (height / 2);
        int meepleX = this.MEEPLE_BORDER;
        int meepleY = currentHeight + (height / 2) - (meepleSize / 2);
        g2.drawImage(this.image, meepleX, meepleY, meepleSize, meepleSize, null);

        // Draw the Meeple count square
        g2.setColor(Color.WHITE);
        int squareSize = meepleSize / 4;
        int squareX = meepleX + meepleSize / 2 - squareSize / 2;
        int squareY = meepleY + (meepleSize / 2) - (squareSize / 2);
        g2.fillRect(squareX, squareY, squareSize, squareSize);

        // Draw the Meeple count
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Calibri", Font.BOLD, (int) (squareSize * 1.2)));
        int stringY = meepleY + meepleSize / 2 + squareSize / 2;
        g2.drawString("" + this.meepleNumber, squareX, stringY);

        // Draw the informations
        int nameX = meepleX + meepleSize + this.MEEPLE_BORDER;
        g2.setFont(new Font("Calibri", Font.PLAIN, 18));
        g2.drawString(this.name, nameX, meepleY + meepleSize / 4);
        g2.setFont(new Font("Calibri", Font.PLAIN, 13));
        g2.drawString("Nombre de points : " + this.pointNumber, nameX, meepleY + 3 * (meepleSize / 4));

        // If this is the current player, draw a red border
        if (currentPlayer) {
            g2.setColor(Color.red);
            g2.fillRect(0, currentHeight, width, this.CURRENT_PLAYER_BORDER_WIDTH);
            g2.fillRect(width - this.CURRENT_PLAYER_BORDER_WIDTH, currentHeight, this.CURRENT_PLAYER_BORDER_WIDTH, height);
            g2.fillRect(0, currentHeight + height - this.CURRENT_PLAYER_BORDER_WIDTH, width, this.CURRENT_PLAYER_BORDER_WIDTH);
            g2.fillRect(0, currentHeight, this.CURRENT_PLAYER_BORDER_WIDTH, height);
            g2.setColor(Color.black);
        }
    }
}
