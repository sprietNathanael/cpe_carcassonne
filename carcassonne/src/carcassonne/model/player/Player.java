/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.player;

import carcassonne.model.aggregate.AggregatesEnum;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a player
 */
public class Player implements Serializable
{

    /**
     * How much meeples the player has
     */
    public static int NBMEEPLE = 8;

    private final ArrayList<Meeple> meeples;
    private final String name;
    private int points;
    private final String color;
    private String playerType;
    private int fieldPoints;
    private int cityPoints;
    private int abbayePoints;
    private int roadPoints;
    private int riverPoints;

    public void addToScore(int nb, AggregatesEnum type)
    {
        switch(type)
        {
            case ABBAY:
                this.abbayePoints+=nb;
                break;
            case CITY:
                this.cityPoints+=nb;
                break;
            case FIELD:
                this.fieldPoints+=nb;
                break;
            case ROAD:
                this.roadPoints+=nb;
                break;
            case RIVER:
                this.riverPoints+=nb;
                break;
        }
        points += nb;
    }

    /**
     * Creates a new Player
     *
     * @param name the player's name
     * @param color the player's color
     * @param playerType the player's type
     */
    public Player(String name, String color, String playerType)
    {
        this.name = name;
        this.color = color;
        this.points = 0;
        this.playerType = playerType;
        this.meeples = new ArrayList<>();
        this.abbayePoints = 0;
        this.cityPoints = 0;
        this.fieldPoints = 0;
        this.riverPoints = 0;
        this.roadPoints = 0;
    }
    
    public void addMeeple(Meeple meeple)
    {
        this.meeples.add(meeple);
    }

    public int getFieldPoints()
    {
        return fieldPoints;
    }

    public int getCityPoints()
    {
        return cityPoints;
    }

    public int getAbbayePoints()
    {
        return abbayePoints;
    }

    public int getRoadPoints()
    {
        return roadPoints;
    }

    public int getRiverPoints()
    {
        return riverPoints;
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

    public int getUnusedMeepleNumber()
    {
        int meepleCounter = 0;
        for (int i = 0; i < meeples.size(); i++) {
            if (!meeples.get(i).getIsUsed()) {
                meepleCounter++;
            }
        }
        return meepleCounter;
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

    public String getPlayerType()
    {
        return playerType;
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

        for (Meeple m : meeples) {
            if (m.getIsBig() && !m.getIsUsed()) {
                ret = m;
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
            if (m.getIsUsed() == false && !m.getIsBig()) {
                ret = m;
                break;
            }
        }
        return ret;
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
    
    public void setMode(String mode)
    {
        System.out.println("Change Mode");
        this.playerType = mode;
    }
}
