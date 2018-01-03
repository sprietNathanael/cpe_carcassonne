/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.player;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a player
 */
public class Player
{

    /**
     * How much meeples the player has
     */
    public static int NBMEEPLE = 8;

    private final Meeple bigMeeple;
    private final ArrayList<Meeple> meeples;
    private final String name;
    private int points;
    private final String color;

    public void addToScore(int nb)
    {
        points += nb;
    }

    /**
     * Creates a new Player
     *
     * @param name the player's name
     * @param color the player's color
     */
    public Player(String name, String color)
    {
        this.name = name;
        this.color = color;
        this.points = 0;
        this.bigMeeple = new Meeple(true, this);
        this.meeples = new ArrayList<>();
        for (int i = 1; i < NBMEEPLE; i++) {
            this.meeples.add(new Meeple(this));
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

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.name);
        hash = 59 * hash + Objects.hashCode(this.color);
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Player other = (Player) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return Objects.equals(this.color, other.color);
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
    public String getColor()
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
        boolean ret = false;

        for (Meeple m : meeples) {
            if (!m.getIsUsed()) {
                ret = true;
                break;
            }
        }
        return ret;
    }

    /**
     * Get the big meeple of the player if it is available
     *
     * @return
     */
    public Meeple getBigMeepleAvailable()
    {
        Meeple ret = null;

        if (!bigMeeple.getIsUsed()) {
            ret = bigMeeple;
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

    public Meeple getBigMeeple()
    {
        return bigMeeple;
    }

    @Override
    public String toString()
    {
        return "\nPlayer{name=" + name + ", points=" + points + ", color=" + color + '}';
    }

    public static int countPoints(Set<Meeple> meeples)
    {
        int total = 0;

        for (Meeple meeple : meeples) {
            if (meeple.getIsBig()) {
                total += 2;
            }
            else {
                total++;
            }
        }

        return total;
    }
}
