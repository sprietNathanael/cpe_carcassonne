/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.menuStart;

import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 *
 * @author thomas
 */
public class ParamPlayers
{

    private String nom, color;

    public ParamPlayers(String nom, String color)
    {
        this.nom = nom;
        this.color = color;

    }

    /**
     * @return the nom
     */
    public String getNom()
    {
        return nom;
    }

    /**
     * @return the color
     */
    public String getColor()
    {
        return color;
    }

}
