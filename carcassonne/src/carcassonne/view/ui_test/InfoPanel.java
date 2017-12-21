/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.ui_test;

import carcassonne.model.carcassonnegame.CarcassonneGame;
import carcassonne.model.player.Player;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

/**
 * The information panel
 * @author nathanael
 */
public class InfoPanel extends JPanel
{
    private HashMap<String,PlayerInfo> playerInfoLines;
    private String currentPlayer;
    public static int SEPARATION_LINE_WIDTH = 5;  
    public static int PREVIEW_BORDER = 10;
    public static int PREVIEWES_GAP = 30;
    private BufferedImage backTile;
    private BufferedImage currentTile;
    private int pileSize;
    
    /**
     * Constructs the information panel
     * @param players
     */
    public InfoPanel(ArrayList<Player> players)
    {
        // Configure component
        setDoubleBuffered(true);
        setOpaque(false);
        setLayout(new MigLayout());
        
        // Creates information lines from players
        this.playerInfoLines = new HashMap<String,PlayerInfo>();
        for(Player player : players)
        {
            this.playerInfoLines.put(player.getName(),new PlayerInfo(player.getName(), player.getColor()));
        }
        
        // Get the back tile image
        try {
            this.backTile = ImageIO.read(new File("resources/back.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Refresh the informations from the game
     * @param game 
     */
    public void refresh(CarcassonneGame game)
    {
        this.currentPlayer = game.getCurrentPlayer().getName();
        
        // Updates the information lines
        for(Player player : game.getPlayers())
        {
            this.playerInfoLines.get(player.getName()).updatePlayer(player.getMeeple().size(), player.getPoints());
        }
        
        if(game.getCurrentTile() != null)
        {
            
            // Get the current tile image
            try {
                this.currentTile = ImageIO.read(new File("resources/"+game.getCurrentTile().getName()+".jpg"));
            } catch (IOException ex) {
                Logger.getLogger(InfoPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        // Get the pile size
        this.pileSize = game.getPileSize();
    }

    @Override
    protected void paintChildren(Graphics g)
    {
        int infoLinesHeight = this.getHeight()/6;
        Graphics2D g2 = (Graphics2D) g;
        
        // Draw the back tile
        int currentHeight = 0;
        int previewSize = (int) (infoLinesHeight/2);
        int previewX = this.PREVIEW_BORDER;
        int previewY = currentHeight+(infoLinesHeight/2) - (previewSize/2);
        g2.drawImage(this.backTile, previewX, previewY, previewSize, previewSize, null);
        
        // Draw the pile counter
        g2.setFont(new Font("Calibri", Font.BOLD, (int)(previewSize*0.3)));
        g2.drawString(""+this.pileSize, previewX, previewY-5);
        g2.setFont(new Font("Calibri", Font.PLAIN, 12));
        
        // Draw preview tile
        previewX = this.PREVIEW_BORDER+previewSize+this.PREVIEWES_GAP;
        g2.drawImage(this.currentTile, previewX, previewY, previewSize, previewSize, null);
        
        currentHeight+=infoLinesHeight;
        
        // Draw every information lines
        for (Map.Entry<String, PlayerInfo> entry : this.playerInfoLines.entrySet()) {
            String key = entry.getKey();
            PlayerInfo value = entry.getValue();
            value.paint(g2, currentHeight, infoLinesHeight, this.getWidth()-this.SEPARATION_LINE_WIDTH,key.equals(this.currentPlayer));
            currentHeight += infoLinesHeight;
        }
        
        // Draw the separation line
        g2.setColor(Color.GRAY);
        g2.fillRect(this.getWidth()-this.SEPARATION_LINE_WIDTH, 0, this.SEPARATION_LINE_WIDTH, this.getHeight()-this.SEPARATION_LINE_WIDTH);
        g2.setColor(Color.BLACK);
        super.paintChildren(g);
    }
    
}
