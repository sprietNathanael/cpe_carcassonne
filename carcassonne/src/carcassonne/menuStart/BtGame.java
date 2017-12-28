/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.menuStart;

/**
 *
 * @author thomas
 */
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;

public class BtGame extends JButton
{

    private Image img;

    public BtGame(String image)
    {
        try {
            img = ImageIO.read(new File(image));
        } catch (IOException e) {
        }
    }

    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
