/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view;

/**
 *
 * @author thomas
 */
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Pannel extends JPanel
{

    /**
     * 
     * @param g 
     */
    @Override
    public void paintComponent(Graphics g)
    {
        try {
            Image img = ImageIO.read(new File("C:\\Users\\thomas\\Desktop\\Programmation\\Carcassonne\\world.jpg"));
            g.drawImage(img, 0, 0, this);
            //Image background
            g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
