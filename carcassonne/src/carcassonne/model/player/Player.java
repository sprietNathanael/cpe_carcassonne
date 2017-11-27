/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.player;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Represents a player
 */
public class Player
{

    /**
     * How much meeples the player has
     */
    public static int NBMEEPLE = 8;

    private ArrayList<Meeple> meeples;
    private String name;
    private int points;
    private Color color;

    /**
     * Creates a new Player
     *
     * @param name the player's name
     * @param color the player's color
     */
    public Player(String name, Color color)
    {
        this.name = name;
        this.color = color;
        this.points = 0;
        this.meeples = new ArrayList<>();
        this. meeples.add(new Meeple(true));
        for (int i = 1; i < NBMEEPLE ; i++) {
                this.meeples.add(new Meeple());
            }
    }

    /**
     * Gets the meeples of the player
     *
     * @return ArrayList Meeple the list of the meeples
     */
    public ArrayList<Meeple> getMeeple()
    {
        return this.meeples;
    }

    /**
     * Gets the name of the player
     *
     * @return string the name of the player
     */
    public String getName()
    {
        return name;
    }

    /**
     * Gets the points of the player
     *
     * @return integer the points of the player
     */
    public int getPoints()
    {
        return points;
    }

    /**
     * Gets the color of the player
     *
     * @return Color the color of the player
     */
    public Color getColor()
    {
        return color;
    }

    /**
     * Allows to check if a player has meeple
     *
     * @return true if more than one meeple is available
     */
    public boolean checkMeepleAvailable()
    {
        boolean ret = true;

        for (Meeple m : meeples) {
            if (m.getIsUsed()) {
                ret = false;
                break;
            }
        }
        return ret;
    }

    /**
     * Get the first meeple no used
     *
     * @return
     */
    public Meeple getFirstMeepleAvailable()
    {
        Meeple ret = null;
        for (Meeple m : meeples) {
            if (m.getIsUsed() == false) {
                ret = m;
                break;
            }
        }
        return ret;
    }

}
