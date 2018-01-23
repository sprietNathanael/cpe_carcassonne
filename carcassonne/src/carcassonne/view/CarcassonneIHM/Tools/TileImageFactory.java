/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.CarcassonneIHM.Tools;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author nathanael
 */
public class TileImageFactory
{
    private static HashMap<String,BufferedImage> images = new HashMap<>();
    
    public static BufferedImage getImage(String imageName)
    {
        if(images.containsKey(imageName))
        {
            return images.get(imageName);
        }
        else
        {
            BufferedImage image = null;
            try {
                image = ImageIO.read(new File(imageName));
            } catch (IOException ex) {
                Logger.getLogger(TileImageFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
            images.put(imageName, image);
            return image;
        }
    }
}
