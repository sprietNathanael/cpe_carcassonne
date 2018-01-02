/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.ui_test;

import carcassonne.model.tile.AbstractTile;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * Graphical representation of a Tile
 */
public class TileImage extends UICoord
{
    private BufferedImage image;
    private String name;
    private double rotation;

    /**
     * Constructs a Tile Image
     * @param x X component of the tile
     * @param y Y component of the tile
     * @param name Name of the tile
     * @param rotation Rotation of the tile
     */
    public TileImage(int x, int y, String name, int rotation)
    {
        super(x,y);
        this.name = name;
        this.rotation = rotation;
        this.buildImage();
    }
    
    /**
     * Contruct a tile image from an abstract tile
     * @param x
     * @param y
     * @param tile 
     */
    public TileImage(int x, int y, AbstractTile tile)
    {
        this(x,y,tile.getName(),tile.getRotation());
        
    }
    
    /**
     * Builds the tile image representation based on its name
     */
    private void buildImage()
    {
        AffineTransform tx = new AffineTransform();
        this.image = null;
        try 
        {
            // Get the rotation in radians
            double rad_rotation = this.rotation * Math.PI / 180.0;
            
            // Get the tile image based on its name
            this.image = ImageIO.read(new File("resources/tiles/"+this.name + ".jpg"));
            
            // Applies the rotation on the affine transform
            tx.rotate(rad_rotation, this.image.getWidth()/2, this.image.getHeight()/2);
            AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
            //System.out.println(this.name+" "+this.rotation);
            
            // Applies the affine transform on the image
            this.image = op.filter(image, null);
            
        } catch (IOException ex) {
            Logger.getLogger(TilesLayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Rotates the image on the right
     */
    public void turnRight()
    {
        this.rotation += 90;
        this.rotation = this.rotation > 360 ? this.rotation - 360 : this.rotation; 
        // Rebuild the image
        this.buildImage();
    }
    
    /**
     * Rotates the image on the left
     */    
    public void turnLeft()
    {
        this.rotation -= 90;
        this.rotation = this.rotation > 360 ? this.rotation - 360 : this.rotation;
        // Rebuild the image
        this.buildImage();
    }
    
    /**
     * Set the X component of the image
     * @param x 
     */
    public void setX(int x)
    {
        this.x = x;
    }
    
    /**
     * Set the Y component of the image
     * @param y 
     */
    public void setY(int y)
    {
        this.y = y;
    }
    
    /**
     * Set the coordinates of the image
     * @param c 
     */
    public void setCoord(UICoord c)
    {
        this.setX(c.getX());
        this.setY(c.getY());
    }
    
    /**
     * Get the image
     * @return 
     */
    public BufferedImage getImage()
    {
        return this.image;
    }
    
    
}
