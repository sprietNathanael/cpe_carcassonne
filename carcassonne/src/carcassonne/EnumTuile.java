/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carcassonne;

/**
 *
 * @author Thomas
 */
public enum EnumTuile {
    ville,
    champ,
    route;
    
    @Override    
    public String toString() {
        switch(this)
        {
            case ville : return "V";
            case champ : return "C";
            case route : return "R";
            default : return "";
        }
    }
    
    public static EnumTuile toEnum(String t)
    {
        switch(t)
        {
            case "V" : return ville;
            case "C" : return champ;
            case "R" : return route;
            default : return null;
        }
    }
}
