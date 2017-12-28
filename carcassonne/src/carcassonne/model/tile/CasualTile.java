/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.tile;

import carcassonne.model.type.AbstractType;
import carcassonne.model.type.CityType;
import carcassonne.model.type.CrossType;
import carcassonne.model.type.FieldType;
import carcassonne.model.type.RoadType;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Class that represents a casual Tile It is cut in 20 parts, in two layers The
 * border layer is cut in 16 parts : - The cardinal points (N,S,E,W) - Their
 * derivatives (NE,NW,SE,SW) - These derivatives are themselves cut in 2 The
 * center layer is cut in 4 parts : CNE, CNW, CSE, CSW
 */
public class CasualTile extends AbstractTile
{

    public static boolean locationsAreBounded(Set<String> cityLocations, Set<String> locationTypes)
    {
        boolean result = false;
        Set<String> neighborLocations = new HashSet();
        //Get all the bonded locations corrsponding to the city locations
        if (cityLocations != null) {
            cityLocations.forEach((cityLocation) -> {
                neighborLocations.addAll(Arrays.asList(CasualTile.neighbouring.get(cityLocation)));
            });
            //Test every bonded location, if there is one the two aggregate are bonding
            for (String locationType : locationTypes) {
                if (neighborLocations.contains(locationType)) {
                    result = true;
                }
            }
        }

        return result;
    }

    /**
     * Return the locations that are only an edge
     *
     * @param locations
     * @return
     */
    public static Set<String> filterLocationEdges(Set<String> locations)
    {
        Set<String> result = new HashSet<>();

        for (String location : locations) {
            if (location.equals("NNW")
                    || location.equals("N")
                    || location.equals("NNE")
                    || location.equals("NEE")
                    || location.equals("E")
                    || location.equals("SEE")
                    || location.equals("SSE")
                    || location.equals("S")
                    || location.equals("SSW")
                    || location.equals("SWW")
                    || location.equals("W")
                    || location.equals("NWWW")) {
                result.add(location);
            }
        }

        return result;
    }

    /**
     * Return the accepted locations of the neighbor
     *
     * @param locations
     * @return
     */
    public static Set<String> getNeighborEdgesLocations(Set<String> locations)
    {
        Set<String> result = new HashSet<>();

        for (String location : locations) {
            switch (location) {
                case "NNW":
                    result.add("SSW");
                    break;
                case "N":
                    result.add("S");
                    break;
                case "NNE":
                    result.add("SSE");
                    break;
                case "NEE":
                    result.add("NWW");
                    break;
                case "E":
                    result.add("W");
                    break;
                case "SEE":
                    result.add("SWW");
                    break;
                case "SSE":
                    result.add("NNE");
                    break;
                case "S":
                    result.add("N");
                    break;
                case "SSW":
                    result.add("NNW");
                    break;
                case "SWW":
                    result.add("SEE");
                    break;
                case "W":
                    result.add("E");
                    break;
                case "NWW":
                    result.add("NEE");
                    break;
                default:
                    break;

            }
        }

        return result;
    }

    private final HashMap<String, AbstractType> types;

    static private HashMap<String, String[]> neighbouring;
    static private HashMap<String, String> aggRotateRight;
    static private HashMap<String, String> aggRotateLeft;

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
    } //neighbouring

    static {
        aggRotateLeft = new HashMap<>();
        aggRotateLeft.put("NWW", "SSW");
        aggRotateLeft.put("NW", "SW");
        aggRotateLeft.put("NNW", "SWW");
        aggRotateLeft.put("N", "W");
        aggRotateLeft.put("NNE", "NWW");
        aggRotateLeft.put("NE", "NW");
        aggRotateLeft.put("NEE", "NNW");
        aggRotateLeft.put("E", "N");
        aggRotateLeft.put("SEE", "NNE");
        aggRotateLeft.put("SE", "NE");
        aggRotateLeft.put("SSE", "NEE");
        aggRotateLeft.put("S", "E");
        aggRotateLeft.put("SSW", "SEE");
        aggRotateLeft.put("SW", "SE");
        aggRotateLeft.put("SWW", "SSE");
        aggRotateLeft.put("W", "S");
        aggRotateLeft.put("CNW", "CSW");
        aggRotateLeft.put("CNE", "CNW");
        aggRotateLeft.put("CSE", "CNE");
        aggRotateLeft.put("CSW", "CSE");
    }//aggRotateLeft

    static {
        aggRotateRight = new HashMap<>();
        aggRotateRight.put("NWW", "NNE");
        aggRotateRight.put("NW", "NE");
        aggRotateRight.put("NNW", "NEE");
        aggRotateRight.put("N", "E");
        aggRotateRight.put("NNE", "SEE");
        aggRotateRight.put("NE", "SE");
        aggRotateRight.put("NEE", "SSE");
        aggRotateRight.put("E", "S");
        aggRotateRight.put("SEE", "SSW");
        aggRotateRight.put("SE", "SW");
        aggRotateRight.put("SSE", "SWW");
        aggRotateRight.put("S", "W");
        aggRotateRight.put("SSW", "NWW");
        aggRotateRight.put("SW", "NW");
        aggRotateRight.put("SWW", "NNW");
        aggRotateRight.put("W", "N");
        aggRotateRight.put("CNW", "CNE");
        aggRotateRight.put("CNE", "CSE");
        aggRotateRight.put("CSE", "CSW");
        aggRotateRight.put("CSW", "CNW");
    } //aggRotateRight

    /**
     * Construct a Tile
     *
     * @param name
     * @param tileId
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
     * @param aggregates
     */
    public CasualTile(String name, String tileId, AbstractType NWW, AbstractType NW, AbstractType NNW, AbstractType N, AbstractType NNE, AbstractType NE, AbstractType NEE, AbstractType E, AbstractType SEE, AbstractType SE, AbstractType SSE, AbstractType S, AbstractType SSW, AbstractType SW, AbstractType SWW, AbstractType W, AbstractType CNW, AbstractType CNE, AbstractType CSE, AbstractType CSW, Set<Set<String>> aggregates)
    {
        super(name);
        this.id = tileId;
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
        aggregateEmplacements = aggregates;
    }

    /**
     * Construct a Tile
     *
     * @param tileId
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
    public CasualTile(String tileId, AbstractType NWW, AbstractType NW, AbstractType NNW, AbstractType N, AbstractType NNE, AbstractType NE, AbstractType NEE, AbstractType E, AbstractType SEE, AbstractType SE, AbstractType SSE, AbstractType S, AbstractType SSW, AbstractType SW, AbstractType SWW, AbstractType W, AbstractType CNW, AbstractType CNE, AbstractType CSE, AbstractType CSW)
    {
        super("Null");
        this.id = tileId;
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
        aggregateEmplacements = new HashSet();
    }

    /**
     * Construct a none complex tile: using a 3*3 array for borders and the
     * usual 4 boxes for the center
     *
     * @param name
     * @param tileId
     * @param NW (NWW, NW, NNW)
     * @param N Same as usual
     * @param NE (NNE, NE, NEE)
     * @param E Same as usual
     * @param SE (SEE, SE, SSE)
     * @param S Same as usual
     * @param SW (SSW, SW, SSW)
     * @param W Same as usual
     * @param CNW Same as usual
     * @param CNE Same as usual
     * @param CSE Same as usual
     * @param CSW Same as usual
     */
    public CasualTile(String name, String tileId, AbstractType NW, AbstractType N, AbstractType NE, AbstractType E, AbstractType SE, AbstractType S, AbstractType SW, AbstractType W, AbstractType CNW, AbstractType CNE, AbstractType CSE, AbstractType CSW, Set<Set<String>> aggregates)
    {
        super(name);
        this.id = tileId;
        this.types = new HashMap<>();
        this.types.put("NWW", NW);
        this.types.put("NW", NW);
        this.types.put("NNW", NW);
        this.types.put("N", N);
        this.types.put("NNE", NE);
        this.types.put("NE", NE);
        this.types.put("NEE", NE);
        this.types.put("E", E);
        this.types.put("SEE", SE);
        this.types.put("SE", SE);
        this.types.put("SSE", SE);
        this.types.put("S", S);
        this.types.put("SSW", SW);
        this.types.put("SW", SW);
        this.types.put("SWW", SW);
        this.types.put("W", W);
        this.types.put("CNW", CNW);
        this.types.put("CNE", CNE);
        this.types.put("CSE", CSE);
        this.types.put("CSW", CSW);
        aggregateEmplacements = aggregates;
    }

    /**
     * Construct a none complex tile: using a 3*3 array for borders and the
     * usual 4 boxes for the center
     *
     * @param tileId
     * @param NW (NWW, NW, NNW)
     * @param N Same as usual
     * @param NE (NNE, NE, NEE)
     * @param E Same as usual
     * @param SE (SEE, SE, SSE)
     * @param S Same as usual
     * @param SW (SSW, SW, SSW)
     * @param W Same as usual
     * @param CNW Same as usual
     * @param CNE Same as usual
     * @param CSE Same as usual
     * @param CSW Same as usual
     */
    public CasualTile(String tileId, AbstractType NW, AbstractType N, AbstractType NE, AbstractType E, AbstractType SE, AbstractType S, AbstractType SW, AbstractType W, AbstractType CNW, AbstractType CNE, AbstractType CSE, AbstractType CSW)
    {
        super("Null");
        this.id = tileId;
        this.types = new HashMap<>();
        this.types.put("NWW", NW);
        this.types.put("NW", NW);
        this.types.put("NNW", NW);
        this.types.put("N", N);
        this.types.put("NNE", NE);
        this.types.put("NE", NE);
        this.types.put("NEE", NE);
        this.types.put("E", E);
        this.types.put("SEE", SE);
        this.types.put("SE", SE);
        this.types.put("SSE", SE);
        this.types.put("S", S);
        this.types.put("SSW", SW);
        this.types.put("SW", SW);
        this.types.put("SWW", SW);
        this.types.put("W", W);
        this.types.put("CNW", CNW);
        this.types.put("CNE", CNE);
        this.types.put("CSE", CSE);
        this.types.put("CSW", CSW);
        aggregateEmplacements = null;
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
     * Rotates the tile's types from east to west (counter clockwise)
     *
     * @return true if the rotation is successfull
     */
    public boolean rotateLeft()
    {
        super.rotateLeft();
        AbstractType intermediate;
        intermediate = this.types.get("N");
        this.types.put("N", this.types.get("E"));
        this.types.put("E", this.types.get("S"));
        this.types.put("S", this.types.get("W"));
        this.types.put("W", intermediate);

        intermediate = this.types.get("CNW");
        this.types.put("CNW", this.types.get("CNE"));
        this.types.put("CNE", this.types.get("CSE"));
        this.types.put("CSE", this.types.get("CSW"));
        this.types.put("CSW", intermediate);

        intermediate = this.types.get("NW");
        this.types.put("NW", this.types.get("NE"));
        this.types.put("NE", this.types.get("SE"));
        this.types.put("SE", this.types.get("SW"));
        this.types.put("SW", intermediate);

        intermediate = this.types.get("NWW");
        this.types.put("NWW", this.types.get("NNE"));
        this.types.put("NNE", this.types.get("SEE"));
        this.types.put("SEE", this.types.get("SSW"));
        this.types.put("SSW", intermediate);

        intermediate = this.types.get("NNW");
        this.types.put("NNW", this.types.get("NEE"));
        this.types.put("NEE", this.types.get("SSE"));
        this.types.put("SSE", this.types.get("SWW"));
        this.types.put("SWW", intermediate);

        rotateAggregate(false);

        return (true);
    }

    /**
     * Rotates the tile's types from west to east (clockwise)
     *
     * @return true if the rotation is successfull
     */
    public boolean rotateRight()
    {
        super.rotateRight();
        AbstractType intermediate;
        intermediate = this.types.get("N");
        this.types.put("N", this.types.get("W"));
        this.types.put("W", this.types.get("S"));
        this.types.put("S", this.types.get("E"));
        this.types.put("E", intermediate);

        intermediate = this.types.get("CNW");
        this.types.put("CNW", this.types.get("CSW"));
        this.types.put("CSW", this.types.get("CSE"));
        this.types.put("CSE", this.types.get("CNE"));
        this.types.put("CNE", intermediate);

        intermediate = this.types.get("NW");
        this.types.put("NW", this.types.get("SW"));
        this.types.put("SW", this.types.get("SE"));
        this.types.put("SE", this.types.get("NE"));
        this.types.put("NE", intermediate);

        intermediate = this.types.get("NWW");
        this.types.put("NWW", this.types.get("SSW"));
        this.types.put("SSW", this.types.get("SEE"));
        this.types.put("SEE", this.types.get("NNE"));
        this.types.put("NNE", intermediate);

        intermediate = this.types.get("NNW");
        this.types.put("NNW", this.types.get("SWW"));
        this.types.put("SWW", this.types.get("SSE"));
        this.types.put("SSE", this.types.get("NEE"));
        this.types.put("NEE", intermediate);

        rotateAggregate(true);

        return (true);
    }

    private void rotateAggregate(boolean right)
    {
        Set<Set<String>> aggregateEmpTemp = new HashSet<>();
        Set<String> aggSetTemp;

        for (Set<String> setAggragate : aggregateEmplacements) {
            aggSetTemp = new HashSet<>();
            for (String agg : setAggragate) {
                if (right == true) {
                    agg = aggRotateRight.get(agg);
                }
                else {
                    agg = aggRotateLeft.get(agg);
                }
                aggSetTemp.add(agg);
            }
            aggregateEmpTemp.add(aggSetTemp);
        }
        aggregateEmplacements = aggregateEmpTemp;
    }

    /**
     * Check quickly if the tile is composed of a cross road
     *
     * @return boolean
     */
    public boolean isCrossRoad()
    {
        boolean result = false;

        if (this.getCNE() instanceof CrossType
                && this.getCNW() instanceof CrossType
                && this.getCSE() instanceof CrossType
                && this.getCSW() instanceof CrossType) {
            result = true;
        }

        return result;
    }

    public static void main(String str[])
    {
        /*  CasualTile ct = new CasualTile(
                "D", // Name
                "D0", //Id
                new FieldType(), new FieldType(), new FieldType(), new RoadType(), new FieldType(), new FieldType(), new CityType(), //North section
                new CityType(), //East section
                new CityType(), new FieldType(), new FieldType(), new RoadType(), new FieldType(), new FieldType(), new FieldType(), //South section
                new FieldType(), //West section
                new RoadType(), new FieldType(), new FieldType(), new RoadType()//Center section
        );
        System.out.println(ct);*/
 /*ct.rotateLeft();
        System.out.println(ct);
        ct.rotateRight();
        System.out.println(ct);*/

    }

    @Override
    public AbstractType getType(String coordinates)
    {
        return this.types.get(coordinates);
    }

    /**
     * Compare north side of the tile to south side of another tile
     *
     * @param tile_a
     * @return true if match
     */
    @Override
    public boolean compareTileNorth(AbstractTile tile_a)
    {
        CasualTile tile = (CasualTile) tile_a;
        return this.getNNW().getClass() == tile.getSSW().getClass()
                && this.getN().getClass() == tile.getS().getClass()
                && this.getNNE().getClass() == tile.getSSE().getClass();
    }

    /**
     * Compare south side of the tile to north side of another tile
     *
     * @param tile_a
     * @return true if match
     */
    @Override
    public boolean compareTileSouth(AbstractTile tile_a)
    {
        CasualTile tile = (CasualTile) tile_a;
        return this.getSSW().getClass() == tile.getNNW().getClass()
                && this.getS().getClass() == tile.getN().getClass()
                && this.getSSE().getClass() == tile.getNNE().getClass();
    }

    /**
     * Compare west side of the tile to east side of another tile
     *
     * @param tile_a
     * @return true if match
     */
    @Override
    public boolean compareTileWest(AbstractTile tile_a)
    {
        CasualTile tile = (CasualTile) tile_a;
        return this.getNWW().getClass() == tile.getNEE().getClass()
                && this.getW().getClass() == tile.getE().getClass()
                && this.getSWW().getClass() == tile.getSEE().getClass();
    }

    /**
     * Compare east side the tile to west side of another tile
     *
     * @param tile_a
     * @return true if match
     */
    @Override
    public boolean compareTileEast(AbstractTile tile_a)
    {
        CasualTile tile = (CasualTile) tile_a;
        return this.getNEE().getClass() == tile.getNWW().getClass()
                && this.getE().getClass() == tile.getW().getClass()
                && this.getSEE().getClass() == tile.getSWW().getClass();
    }

    public HashMap<String, String[]> getNeighbouring()
    {
        return neighbouring;
    }

    @Override
    public Set<Set<String>> getCityAggregateEmplacements()
    {
        Set<Set<String>> cityAggregatesEmplacements = new HashSet();

        for (Set<String> aggregateEmplacement : aggregateEmplacements) {
            if (this.getAggregateClass(aggregateEmplacement) instanceof CityType) {
                cityAggregatesEmplacements.add(aggregateEmplacement);
            }
        }

        return cityAggregatesEmplacements;
    }

    @Override
    public Set<Set<String>> getRoadAggregateEmplacements()
    {
        Set<Set<String>> roadAggregatesEmplacements = new HashSet();

        for (Set<String> aggregateEmplacement : aggregateEmplacements) {
            if (this.getAggregateClass(aggregateEmplacement) instanceof RoadType) {
                roadAggregatesEmplacements.add(aggregateEmplacement);
            }
        }

        return roadAggregatesEmplacements;
    }

    @Override
    public Set<Set<String>> getFieldAggregateEmplacements()
    {
        Set<Set<String>> fieldAggregatesEmplacements = new HashSet();

        for (Set<String> aggregateEmplacement : aggregateEmplacements) {
            if (this.getAggregateClass(aggregateEmplacement) instanceof FieldType) {
                fieldAggregatesEmplacements.add(aggregateEmplacement);
            }
        }

        return fieldAggregatesEmplacements;
    }

    /**
     * Get the type of an aggregate using its position on the tile
     *
     * @param locations
     * @return
     */
    private AbstractType getAggregateClass(Set<String> locations)
    {
        String location;
        location = (String) locations.iterator().next();

        return this.getType(location);
    }
}
