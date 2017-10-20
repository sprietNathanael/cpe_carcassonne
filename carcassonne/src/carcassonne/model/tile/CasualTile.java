/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.tile;

import carcassonne.model.type.AbbayType;
import carcassonne.model.type.AbstractType;
import carcassonne.model.type.CityType;
import carcassonne.model.type.RiverType;
import java.util.HashMap;

/**
 * Class that represents a casual Tile It is cut in 20 parts, in two layers The
 * border layer is cut in 16 parts : - The cardinal points (N,S,E,W) - Their
 * derivatives (NE,NW,SE,SW) - These derivatives are themselves cut in 2 The
 * center layer is cut in 4 parts : CNE, CNW, CSE, CSW
 */
public class CasualTile extends AbstractTile
{

    private HashMap<String, AbstractType> types;
    static private HashMap<String, String[]> neighbouring;

    static {
        neighbouring = new HashMap<>();
        neighbouring.put("NWW", new String[]{"W", "NW"});
        neighbouring.put("NW", new String[]{"W", "NWW", "NNW", "N"});
        neighbouring.put("NNW", new String[]{"NW", "N"});
        neighbouring.put("N", new String[]{"NW", "NNW", "CNW", "CNE", "NNE", "NE"});
        neighbouring.put("NNE", new String[]{"N", "NE"});
        neighbouring.put("NE", new String[]{"N", "NNE", "NEE", "E"});
        neighbouring.put("NEE", new String[]{"NE", "E"});
        neighbouring.put("E", new String[]{"NE", "NEE", "CNE", "CSE", "SEE", "SE"});
        neighbouring.put("SEE", new String[]{"E", "SE"});
        neighbouring.put("SE", new String[]{"E", "SEE", "SSE", "S"});
        neighbouring.put("SSE", new String[]{"SE", "S"});
        neighbouring.put("S", new String[]{"SE", "SSE", "CSE", "CSW", "SSW", "SW"});
        neighbouring.put("SSW", new String[]{"S", "SW"});
        neighbouring.put("SW", new String[]{"S", "SSW", "SWW", "W"});
        neighbouring.put("SWW", new String[]{"SW", "W"});
        neighbouring.put("W", new String[]{"SW", "SWW", "CSW", "CNW", "NWW", "NW"});
        neighbouring.put("CNW", new String[]{"W", "NW", "N", "CSW", "CNE"});
        neighbouring.put("CNE", new String[]{"N", "NE", "E", "CNW", "CSE"});
        neighbouring.put("CSE", new String[]{"E", "SE", "S", "CNE", "CSW"});
        neighbouring.put("CSW", new String[]{"S", "SW", "W", "CSE", "CNW"});

    }

    /**
     * Construct a Tile
     *
     * @param NWW North-west-western type
     * @param NW North-western type
     * @param NNW North-north-western type
     * @param N Northern type
     * @param NNE North-north-eastern type
     * @param NE North-eastern type
     * @param NEE North-east-eastern type
     * @param E Eastern type
     * @param SEE South-east-eastern type
     * @param SE South-eastern type
     * @param SSE South-south-eastern type
     * @param S Southern type
     * @param SSW South-south-western type
     * @param SW South-western type
     * @param SWW South-west-western type
     * @param W Western type
     * @param CNW Center-north-western type
     * @param CNE Center-north-eastern type
     * @param CSE Center-south-eastern type
     * @param CSW Center-south-western type
     */
    public CasualTile(AbstractType NWW, AbstractType NW, AbstractType NNW, AbstractType N, AbstractType NNE, AbstractType NE, AbstractType NEE, AbstractType E, AbstractType SEE, AbstractType SE, AbstractType SSE, AbstractType S, AbstractType SSW, AbstractType SW, AbstractType SWW, AbstractType W, AbstractType CNW, AbstractType CNE, AbstractType CSE, AbstractType CSW)
    {
        this.types = new HashMap<>();
        this.types.put("NWW", NWW);
        this.types.put("NW", NW);
        this.types.put("NNW", NNW);
        this.types.put("N", N);
        this.types.put("NNE", NNE);
        this.types.put("NE", NE);
        this.types.put("NEE", NEE);
        this.types.put("E", E);
        this.types.put("SEE", SEE);
        this.types.put("SE", SE);
        this.types.put("SSE", SSE);
        this.types.put("S", S);
        this.types.put("SSW", SSW);
        this.types.put("SW", SW);
        this.types.put("SWW", SWW);
        this.types.put("W", W);
        this.types.put("CNW", CNW);
        this.types.put("CNE", CNE);
        this.types.put("CSE", CSE);
        this.types.put("CSW", CSW);
    }

    /**
     * Get North-west-western type
     *
     * @return North-west-western type
     */
    public AbstractType getNWW()
    {
        return this.types.get("NWW");
    }

    /**
     * Get North-western type
     *
     * @return North-western type
     */
    public AbstractType getNW()
    {
        return this.types.get("NW");
    }

    /**
     * Get North-north-western type
     *
     * @return North-north-western type
     */
    public AbstractType getNNW()
    {
        return this.types.get("NNW");
    }

    /**
     * Get Northern type
     *
     * @return Northern type
     */
    public AbstractType getN()
    {
        return this.types.get("N");
    }

    /**
     * Get North-north-eastern type
     *
     * @return North-north-eastern type
     */
    public AbstractType getNNE()
    {
        return this.types.get("NNE");
    }

    /**
     * Get North-eastern type
     *
     * @return North-eastern type
     */
    public AbstractType getNE()
    {
        return this.types.get("NE");
    }

    /**
     * Get North-east-eastern type
     *
     * @return North-east-eastern type
     */
    public AbstractType getNEE()
    {
        return this.types.get("NEE");
    }

    /**
     * Get Eastern type
     *
     * @return Eastern type
     */
    public AbstractType getE()
    {
        return this.types.get("E");
    }

    /**
     * Get South-east-eastern type
     *
     * @return South-east-eastern type
     */
    public AbstractType getSEE()
    {
        return this.types.get("SEE");
    }

    /**
     * Get South-eastern type
     *
     * @return South-eastern type
     */
    public AbstractType getSE()
    {
        return this.types.get("SE");
    }

    /**
     * Get South-south-eastern type
     *
     * @return South-south-eastern type
     */
    public AbstractType getSSE()
    {
        return this.types.get("SSE");
    }

    /**
     * Get Southern type
     *
     * @return Southern type
     */
    public AbstractType getS()
    {
        return this.types.get("S");
    }

    /**
     * Get South-south-western type
     *
     * @return South-south-western type
     */
    public AbstractType getSSW()
    {
        return this.types.get("SSW");
    }

    /**
     * Get South-western type
     *
     * @return South-western type
     */
    public AbstractType getSW()
    {
        return this.types.get("SW");
    }

    /**
     * Get South-west-western type
     *
     * @return South-west-western type
     */
    public AbstractType getSWW()
    {
        return this.types.get("SWW");
    }

    /**
     * Get Western type
     *
     * @return Western type
     */
    public AbstractType getW()
    {
        return this.types.get("W");
    }

    /**
     * Get Center-north-western type
     *
     * @return Center-north-western type
     */
    public AbstractType getCNW()
    {
        return this.types.get("CNW");
    }

    /**
     * Get Center-north-eastern type
     *
     * @return Center-north-eastern type
     */
    public AbstractType getCNE()
    {
        return this.types.get("CNE");
    }

    /**
     * Get Center-south-eastern type
     *
     * @return Center-south-eastern type
     */
    public AbstractType getCSE()
    {
        return this.types.get("CSE");
    }

    /**
     * Get Center-south-western type
     *
     * @return Center-south-western type
     */
    public AbstractType getCSW()
    {
        return this.types.get("CSW");
    }

    /**
     * Displays the tile in command line
     * @return string represents the tile
     */
    @Override
    public String toString()
    {
        return getNNW().toString()+ getN() + getNNE() + getNE() + getNEE() +
                getE() + getSEE() + getSE() + getSSE() + getS() + getSSW() + getS() + getSWW() +
                getW() + getNWW() + getNW();
    }
    
    

    public static void main(String str[])
    {
        CasualTile ct = new CasualTile(new AbbayType(), new CityType(), new RiverType(), new CityType(), new CityType(), new CityType(), new CityType(), new CityType(), new CityType(), new CityType(), new CityType(), new CityType(), new CityType(), new CityType(), new CityType(), new CityType(), new CityType(), new CityType(), new CityType(), new CityType());
        System.out.println(ct);
    }
}
