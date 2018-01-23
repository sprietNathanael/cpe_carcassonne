/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package RessourcesGlobalVariables;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Thomas
 */
public class Colors
{
    public static String blue = "blue";
    public static String green = "green";
    public static String red = "red";
    public static String black = "black";
    public static String yellow = "yellow";
    
    public static List<String> tab;
    static {
        tab = new LinkedList<>();
        tab.add(blue);
        tab.add(green);
        tab.add(red);
        tab.add(black);
        tab.add(yellow);
    }
    
}
