/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.tile;

import carcassonne.model.type.AbstractType;
import java.util.HashMap;

/**
 *
 * @author nathanael
 */
public class CasualTile extends AbstractTile
{
    private HashMap<String, AbstractType> types;
    static private HashMap<String, String[]> neighbouring;
    
    static
    {
        neighbouring = new HashMap<>();
        neighbouring.put("NWW", new String[] {"W","NW"});
        neighbouring.put("NW", new String[] {"W","NWW","NNW","N"});
        neighbouring.put("NNW", new String[] {"NW","N"});
        neighbouring.put("N", new String[] {"NW","NNW","CNW","CNE","NNE","NE"});
        neighbouring.put("NNE", new String[] {"N","NE"});
        neighbouring.put("NE", new String[] {"N","NNE","NEE","E"});
        neighbouring.put("NEE", new String[] {"NE","E"});
        neighbouring.put("E", new String[] {"NE","NEE","CNE","CSE","SEE","SE"});
        neighbouring.put("SEE", new String[] {"E","SE"});
        neighbouring.put("SE", new String[] {"E","SEE","SSE","S"});
        neighbouring.put("SSE", new String[] {"SE","S"});
        neighbouring.put("S", new String[] {"SE","SSE","CSE","CSW","SSW","SW"});
        neighbouring.put("SSW", new String[] {"S","SW"});
        neighbouring.put("SW", new String[] {"S","SSW","SWW","W"});
        neighbouring.put("SWW", new String[] {"SW","W"});
        neighbouring.put("W", new String[] {"SW","SWW","CSW","CNW","NWW","NW"});
        neighbouring.put("CNW", new String[] {"W","NW","N","CSW","CNE"});
        neighbouring.put("CNE", new String[] {"N","NE","E","CNW","CSE"});
        neighbouring.put("CSE", new String[] {"E","SE","S","CNE","CSW"});
        neighbouring.put("CSW", new String[] {"S","SW","W","CSE","CNW"});
        
    }
    
    
    public CasualTile(AbstractType NWW, AbstractType NW, AbstractType NNW, AbstractType N, AbstractType NNE, AbstractType NE, AbstractType NEE, AbstractType E, AbstractType SEE, AbstractType SE, AbstractType SSE, AbstractType S, AbstractType SSW, AbstractType SW, AbstractType SWW, AbstractType W, AbstractType CNW, AbstractType CNE, AbstractType CSE, AbstractType CSW)
    {
        this.types = new HashMap<>();
        this.types.put("NWW",NWW);
        this.types.put("NW",NW);
        this.types.put("NNW",NNW);
        this.types.put("N",N);
        this.types.put("NNE",NNE);
        this.types.put("NE",NE);
        this.types.put("NEE",NEE);
        this.types.put("E",E);
        this.types.put("SEE",SEE);
        this.types.put("SE",SE);
        this.types.put("SSE",SSE);
        this.types.put("S",S);
        this.types.put("SSW",SSW);
        this.types.put("SW",SW);
        this.types.put("SWW",SWW);
        this.types.put("W",W);
        this.types.put("CNW",CNW);
        this.types.put("CNE",CNE);
        this.types.put("CSE",CSE);
        this.types.put("CSW",CSW);
    }

    public AbstractType getNWW()
    {
        return this.types.get("NWW");
    }

    public AbstractType getNW()
    {
        return this.types.get("NW");
    }

    public AbstractType getNNW()
    {
        return this.types.get("NNW");
    }

    public AbstractType getN()
    {
        return this.types.get("N");
    }

    public AbstractType getNNE()
    {
        return this.types.get("NNE");
    }

    public AbstractType getNE()
    {
        return this.types.get("NE");
    }

    public AbstractType getNEE()
    {
        return this.types.get("NEE");
    }

    public AbstractType getE()
    {
        return this.types.get("E");
    }

    public AbstractType getSEE()
    {
        return this.types.get("SEE");
    }

    public AbstractType getSE()
    {
        return this.types.get("SE");
    }

    public AbstractType getSSE()
    {
        return this.types.get("SSE");
    }

    public AbstractType getS()
    {
        return this.types.get("S");
    }

    public AbstractType getSSW()
    {
        return this.types.get("SSW");
    }

    public AbstractType getSW()
    {
        return this.types.get("SW");
    }

    public AbstractType getSWW()
    {
        return this.types.get("SWW");
    }

    public AbstractType getW()
    {
        return this.types.get("W");
    }

    public AbstractType getCNW()
    {
        return this.types.get("CNW");
    }

    public AbstractType getCNE()
    {
        return this.types.get("CNE");
    }

    public AbstractType getCSE()
    {
        return this.types.get("CSE");
    }

    public AbstractType getCSW()
    {
        return this.types.get("CSW");
    }
    
}
