/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.carcassonnegame;

import carcassonne.coord.Coord;
import carcassonne.model.aggregate.AbbayAggregate;
import carcassonne.model.aggregate.AbstractAggregate;
import carcassonne.model.aggregate.CityAggregate;
import carcassonne.model.aggregate.FieldAggregate;
import carcassonne.model.aggregate.RoadAggregate;
import carcassonne.model.board.Board;
import carcassonne.model.player.Meeple;
import carcassonne.model.tile.AbstractTile;
import carcassonne.model.player.Player;
import carcassonne.model.set.BasicSet;
import carcassonne.model.set.SetInterface;
import carcassonne.model.tile.CasualTile;
import carcassonne.model.type.AbbayType;
import carcassonne.model.type.CityType;
import carcassonne.model.type.FieldType;
import carcassonne.model.type.RoadType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

/**
 * Represents a carcassonne game, which aggregates all the model entities
 */
public class CarcassonneGame extends Observable implements CarcassonneGameInterface
{

    private ArrayList<Player> players;
    private Board board;
    private int currentPlayerIndex;
    private List<AbstractTile> pile;
    private AbstractTile firstTile;
    private AbstractTile currentTile;
    private ArrayList<Coord> placements;
    private List<RoadAggregate> roadAggregates;
    private List<CityAggregate> cityAggregates;
    private List<FieldAggregate> fieldAggregates;
    private List<AbbayAggregate> abbayAggregates;
    private String notifyMessage;

    public CarcassonneGame() throws Exception
    {
        this(new ArrayList<Player>());
    }

    /**
     * Constructor for CarcassonneGame from an existing list of players
     *
     * @param players existing list of players
     * @throws java.lang.Exception
     */
    public CarcassonneGame(ArrayList<Player> players) throws Exception
    {
        this.board = new Board();

        //Call the basic extension to get the basic tiles into the pile
        SetInterface basicSet = new BasicSet();
        this.pile = basicSet.getSet();
        Collections.shuffle(this.pile);
        this.firstTile = basicSet.getFirstTile();
        this.currentPlayerIndex = 0;
        this.placements = new ArrayList<>();
        this.players = players;
        roadAggregates = new ArrayList<>();
        cityAggregates = new ArrayList<>();
        fieldAggregates = new ArrayList<>();
        abbayAggregates = new ArrayList<>();
    }

    /**
     * Get the first tile of the game
     *
     * @return
     */
    public AbstractTile getFirstTile()
    {
        return this.firstTile;
    }

    /**
     * Gets the current Player
     *
     * @return the current player
     */
    public Player getCurrentPlayer()
    {
        return (this.players.get(this.currentPlayerIndex));
    }

    /**
     * Switches the turn to the next player
     *
     * @return the next Player
     */
    public Player nextPlayer()
    {
        this.currentPlayerIndex++;

        /**
         * If this is the end of the array, go back to the begining
         */
        if (this.currentPlayerIndex >= this.players.size()) {
            this.currentPlayerIndex = 0;
        }
        return this.getCurrentPlayer();
    }

    /**
     * Adds a new set of tiles to the current pile (Used to add a new extension
     * to the game)
     *
     * @param newSet
     * @author Étienne
     */
    public void addSetToPile(SetInterface newSet)
    {
        pile.addAll(newSet.getSet());
    }

    /**
     * Draws the first tile of the pile. Removes it from the pile and then
     * returns it
     *
     * @return AbstractTile The first tile of the pile
     */
    public AbstractTile drawFromPile()
    {
        try {
            this.currentTile = this.pile.remove(0);

        } catch (IndexOutOfBoundsException e) {
            this.currentTile = null;
        }
        return this.currentTile;

    }

    /**
     * Puts a Tile on the Board
     *
     * @param tile The tile to put
     * @param row The row at which the tile has to be put
     * @param column The column at which the tile has to be put
     * @throws Exception
     */
    @Override
    public void putTile(AbstractTile tile, int row, int column) throws Exception
    {
        board.addTile(tile, row, column);
        this.manageNewTileAggregates(tile, row, column);
        System.out.println("Aggrégats de route:\n" + roadAggregates);
        System.out.println("Taille de la première route: " + roadAggregates.get(0).countPoints() + "\n");
        System.out.println("Aggrégats de ville:\n" + cityAggregates);
        System.out.println("Taille de la première ville: " + cityAggregates.get(0).getAggregatedTiles().size() + "\n");
        System.out.println("Aggrégats de champs:\n" + fieldAggregates);
        System.out.println("Taille du premier champs: " + fieldAggregates.get(0).getAggregatedTiles().size() + "\n");
        this.notifyBoardChanged();
    }

    /**
     * Test if a meeple of the current player can be put on a tile
     *
     * @return
     */
    public boolean playerMeepleBePutOnCurrentTile()
    {
        return this.currentTile.isMeepable();
    }

    /**
     * Alloxs to put a meeple on a type
     *
     * @param meeple
     * @param tile
     * @param coordinates
     *
     * @param player
     */
    public void putMeeple(Meeple meeple, AbstractTile tile, Player player, String coordinates)
    {
        List<AbstractAggregate> aggregates = new ArrayList<>();

        if (tile.getType(coordinates) instanceof RoadType) {
            aggregates.addAll(roadAggregates);
        }
        else if (tile.getType(coordinates) instanceof CityType) {
            aggregates.addAll(cityAggregates);
        }
        else if (tile.getType(coordinates) instanceof FieldType) {
            aggregates.addAll(fieldAggregates);
        }
        else {
            aggregates.addAll(abbayAggregates);
        }

        Set<String> currentTileLocations;

        //parcours des aggregats
        for (AbstractAggregate aggregate : aggregates) {
            //test si on est sur la bonne tuile
            if (aggregate.getAggregatedTypes().containsKey(tile)) {
                currentTileLocations = aggregate.getAggregatedTypes().get(tile);
                //Si la locations correspond à cet aggrégat, c'est le bon et on ajoute le meeple + player
                if (currentTileLocations.contains(coordinates)) {
                    aggregate.addMeeple(player, meeple);
                }
            }
        }
        /* Update the aggregates, if one just has been completed, we add the points and free the meeple */
        //this.manageCompletedAggregate();
    }

    public Board getBoard()
    {
        return this.board;

    }

    //TODO
    // REMOVE !! Please laissez pour mes tests :'(
    public AbstractTile drawFromPileIndex(int i)
    {
        return this.pile.remove(i);
    }

    /**
     * Used to complete the actions of the tile that has been drawn
     *
     */
    public boolean refreshPlacements()
    {
        this.placements.clear();
        if (this.currentTile != null) {
            this.placements = this.board.getTilePossiblePlacements(this.currentTile);
        }
        if (this.placements.isEmpty()) {
            return false;
        }
        else {
            return true;
        }
    }

    public void replaceCurrentTile()
    {
        this.pile.add(this.currentTile);
        Collections.shuffle(this.pile);
    }

    @Override
    public void notifyObservers()
    {
        super.setChanged();
        super.notifyObservers(this.notifyMessage);
    }

    @Override
    public synchronized void addObserver(Observer o)
    {
        super.addObserver(o);
        this.notifyBoardChanged();

    }

    /**
     * Check if the tile can be placed here
     *
     * @param coordinates
     * @param tile
     * @return
     */
    public boolean checkTilePosition(Coord coordinates, AbstractTile tile)
    {
        return this.board.canTileBePlacedHere(coordinates, tile);
    }

    /**
     * Check if the current tile can be placed here
     *
     * @param coordinates
     * @return
     */
    public boolean checkTilePosition(Coord coordinates)
    {
        return this.checkTilePosition(coordinates, this.currentTile);
    }

    /**
     * Notifies the observers when the board has changed with the right message
     */
    public void notifyBoardChanged()
    {
        this.notifyMessage = "boardChanged";
        this.notifyObservers();
    }

    public void notifyPlacementsReady()
    {
        this.notifyMessage = "placementsReady";
        this.notifyObservers();
    }

    public AbstractTile getCurrentTile()
    {
        return currentTile;
    }

    public ArrayList<Coord> getPlacements()
    {
        return placements;
    }

    public ArrayList<Player> getPlayers()
    {
        return this.players;
    }

    public int getPileSize()
    {
        return this.pile.size();
    }

    private void manageNewTileAggregates(AbstractTile tile, int y, int x)
    {
        Coord convertedCoord = convertCoord(x, y);
        int col = convertedCoord.col, row = convertedCoord.row;

        //Récupère tous les aggrégats des différents types
        Set<Set<String>> roadAggregatesEmplacements = tile.getRoadAggregateEmplacements();
        Set<Set<String>> cityAggregatesEmplacements = tile.getCityAggregateEmplacements();
        Set<Set<String>> fieldAggregatesEmplacements = tile.getFieldAggregateEmplacements();

        //--- Routes ---//
        roadAggregatesEmplacements = updateRoads(tile, col, row, roadAggregatesEmplacements);
        //Création des nouvelles routes
        for (Set<String> currentRoadEmplacement : roadAggregatesEmplacements) {
            roadAggregates.add(new RoadAggregate(col, row, tile, currentRoadEmplacement));
        }

        //--- Villes ---//
        //Il y a deux valeurs renvoyé par l'update de villes, on utilise une classe spécifique pour cela
        CitiesUpdateResult citiesResult = updateCities(tile, col, row, cityAggregatesEmplacements);
        //Villes pas encore créées
        cityAggregatesEmplacements = citiesResult.citiesNotCreated;
        //Ville qui sont présentes sur la tuile courante, nécessaires pour les champs
        Set<CityAggregate> tileCities = citiesResult.citiesOnTile;

        for (Set<String> currentCityEmplacement : cityAggregatesEmplacements) {
            CityAggregate newCity = new CityAggregate(col, row, tile, currentCityEmplacement);
            cityAggregates.add(newCity);
            tileCities.add(newCity);
        }

        //--- Champs ---//
        fieldAggregatesEmplacements = updateFields(tile, col, row, fieldAggregatesEmplacements, tileCities);

        for (Set<String> currentFieldEmplacement : fieldAggregatesEmplacements) {
            FieldAggregate newField = new FieldAggregate(col, row, tile, currentFieldEmplacement, tileCities);
            fieldAggregates.add(newField);

        }
        // Manage abbay
        manageNewTileAggregatesAbbay(convertedCoord, tile);
    }

    private void manageNewTileAggregatesAbbay(Coord coord, AbstractTile tile)
    {
        HashMap<Coord, AbstractTile> nearTiles = board.getNearTilesAbbayRange(coord, tile);
        AbbayAggregate abbayAggregate = null;

        for (Map.Entry<Coord, AbstractTile> entry : nearTiles.entrySet()) {
            Coord key = entry.getKey();
            AbstractTile value = entry.getValue();

            if (value != null) {
                if (tile.getType("CNW").getClass() == AbbayType.class) // carte posée = abbay
                {
                    if (abbayAggregate == null) {
                        abbayAggregate = new AbbayAggregate(coord.col, coord.row, tile, null);
                    }
                    abbayAggregate.enlargeAggregate(key.col, key.row, value, null);
                }
                if (value.getType("CNW").getClass() == AbbayType.class) { //carte autour = abbay

                    for (AbbayAggregate abAgg : abbayAggregates) {
                        if (abAgg.contain(value) == true) {
                            abAgg.enlargeAggregate(coord.row, coord.col, tile, null);
                        }
                    }
                }
            }
        }
        if (abbayAggregate != null) {
            abbayAggregates.add(abbayAggregate);
        }
    }

    private static Set<String> filterSetString(Set<String> neighborTileLocations, Set<String> authorizedString)
    {
        Set<String> result = new HashSet<>();

        for (String currentString : neighborTileLocations) {
            if (authorizedString.contains(currentString)) {
                result.add(currentString);
            }
        }

        return result;
    }

    private Coord convertCoord(int x, int y)
    {
        return new Coord(x, y * -1);
    }

    /**
     * Get the locations that can be linked to the aggregate we test, in
     * function of the different locations
     *
     * @param neighboredTilesEmplacement
     * @param neighborTileLocations
     * @return
     */
    private static Set<String> getLocationsAuthorized(Coord neighboredTilesEmplacement, Set<String> neighborTileLocations)
    {
        //neighborTileLocations = CasualTile.getNeighborEdgesLocations(neighborTileLocations);
        Set<String> authorizedString = new HashSet<>();

        if (neighboredTilesEmplacement.equals(new Coord(-1, 0))) {
            authorizedString = BasicSet.retTreeSet("NEE", "E", "SEE");
        }
        else if (neighboredTilesEmplacement.equals(new Coord(1, 0))) {
            authorizedString = BasicSet.retTreeSet("NWW", "W", "SWW");
        }
        else if (neighboredTilesEmplacement.equals(new Coord(0, -1))) {
            authorizedString = BasicSet.retTreeSet("NNW", "N", "NNE");
        }
        else if (neighboredTilesEmplacement.equals(new Coord(0, 1))) {
            authorizedString = BasicSet.retTreeSet("SSW", "S", "SSE");
        }
        //Filter the locations using the authorized locations
        neighborTileLocations = filterSetString(neighborTileLocations, authorizedString);
        //Get the corresponding neighbor edges of the result
        neighborTileLocations = CasualTile.getNeighborEdgesLocations(neighborTileLocations);

        return neighborTileLocations;
    }

    /**
     * Update the road aggregate of the game that are already created
     *
     * @param tile
     * @param col
     * @param row
     * @param roadAggregatesEmplacements
     * @return
     */
    private Set<Set<String>> updateRoads(AbstractTile tile, int col, int row, Set<Set<String>> roadAggregatesEmplacements)
    {
        /**
         * Map qui est modifiée lors des parcours des aggrégats existants.
         * Renseigne lorsqu'un nouvel aggrégat de la tuile a été affecté. Si
         * c'est le cas, les autres matchs deviendront des merge du road
         * aggregate de la map, au lieu d'un enlarge aggregate
         */
        Map<Set<String>, RoadAggregate> roadAlreadyAffected = new HashMap<>();

        /**
         * Met à jour la liste des aggrégats de route, si une route est mergée
         * sur une autre, on l'a supprime
         *
         */
        List<RoadAggregate> updatedRoadAggregates = new ArrayList<>();
        updatedRoadAggregates.addAll(roadAggregates);

        /**
         * Set qui informe les aggrégats qui n'ont pas été enregistré dans un
         * aggrégats déjà existant. Un nouvel aggrégat sera alors créé
         */
        Set<Set<String>> newRoadAggregatesEmplacements = new HashSet<>();
        newRoadAggregatesEmplacements.addAll(roadAggregatesEmplacements);

        //D'abord, on parcours les aggrégate existant
        for (RoadAggregate road : roadAggregates) {
            //Récupère tous les différences de coordonnées entre la road courante et le nouvel index, si ce sont des voisins directs
            Set<Coord> neighboredTilesEmplacements = road.getNeighboredCoordinates(col, row);

            //Test si la road est voisine au nouvel index
            if (!neighboredTilesEmplacements.isEmpty()) {
                Set<String> neighborTileLocations;
                Set<String> neededLocations;
                //Parcours de ces différences de coordonnées
                for (Coord neighboredTilesEmplacement : neighboredTilesEmplacements) {
                    //Récupère les types de la road, de la tuile voisine à l'index
                    neighborTileLocations = road.getAggregatedTypesByCoord(col + neighboredTilesEmplacement.col, row + neighboredTilesEmplacement.row);

                    //Test s'il y a bien des types pour cette tuile
                    if (neighborTileLocations != null) {
                        //Donne la localisation des types de la nouvelle tuile qui sont potentiellement compatibles avec cet aggrégat
                        neededLocations = getLocationsAuthorized(neighboredTilesEmplacement, neighborTileLocations);
                        //System.out.println("Types requis: " + neededLocations);
                        //System.out.println("Types de la tuile: " + roadAggregatesEmplacements);

                        //Parcours tous les morceaux d'aggrégats de la nouvelle tuile
                        for (Set<String> locationInNewTile : roadAggregatesEmplacements) {
                            //Parcours des localisation autorisées
                            for (String neededLocation : neededLocations) {

                                //Teste si les emplacments nécessaires matchent avec le morceau d'aggrégat courant
                                if (locationInNewTile.contains(neededLocation)) {
                                    //Si le morceau n'a pas déjà été affecté à un aggrégat, on l'ajoute
                                    if (!roadAlreadyAffected.containsKey(locationInNewTile)) {
                                        //Ajout de la tuile à cet emplacement dans la route courante
                                        road.enlargeAggregate(col, row, tile, locationInNewTile);
                                        //Informe sur quel aggrégat le morceau vient d'être affecté
                                        roadAlreadyAffected.put(locationInNewTile, road);
                                        //Suppression du morceau d'aggrégat pour ne pas qu'il soit créé dans un nouvel aggrégat
                                        newRoadAggregatesEmplacements.remove(locationInNewTile);

                                        //System.out.println("Placement d'une route aux coordonnées: " + col + ":" + row);
                                    }
                                    //S'il a déjà été affecté, on merge les deux aggrégats car ils sont maintenant communs
                                    else {
                                        RoadAggregate roadToBeCompleted = roadAlreadyAffected.get(locationInNewTile);
                                        //Si les deux routes pointent vers le même objet, il n'y a pas besoin de faire de merge
                                        if (road != roadToBeCompleted) {
                                            //Merge des deux route
                                            roadToBeCompleted.merge(road);
                                            //Suppression de la route mergée
                                            updatedRoadAggregates.remove(road);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        roadAggregates = updatedRoadAggregates;

        return newRoadAggregatesEmplacements;
    }

    /**
     * Update the city aggregates of the game that are already created
     *
     * @param tile
     * @param col
     * @param row
     * @param roadAggregatesEmplacements
     * @return
     */
    private CitiesUpdateResult updateCities(AbstractTile tile, int col, int row, Set<Set<String>> cityAggregatesEmplacements)
    {
        Set<CityAggregate> citiesOnTile = new HashSet<>();

        Map<Set<String>, CityAggregate> cityAlreadyAffected = new HashMap<>();
        List<CityAggregate> updatedCityAggregates = new ArrayList<>();
        updatedCityAggregates.addAll(cityAggregates);

        Set<Set<String>> newCityAggregatesEmplacements = new HashSet<>();
        newCityAggregatesEmplacements.addAll(cityAggregatesEmplacements);

        for (CityAggregate city : cityAggregates) {
            Set<Coord> neighboredTilesEmplacements = city.getNeighboredCoordinates(col, row);

            if (!neighboredTilesEmplacements.isEmpty()) {
                Set<String> neighborTileLocations;
                Set<String> neededLocations;

                for (Coord neighboredTilesEmplacement : neighboredTilesEmplacements) {
                    neighborTileLocations = city.getAggregatedTypesByCoord(col + neighboredTilesEmplacement.col, row + neighboredTilesEmplacement.row);

                    if (neighborTileLocations != null) {
                        neededLocations = getLocationsAuthorized(neighboredTilesEmplacement, neighborTileLocations);

                        for (Set<String> locationInNewTile : cityAggregatesEmplacements) {
                            for (String neededLocation : neededLocations) {

                                if (locationInNewTile.contains(neededLocation)) {
                                    if (!cityAlreadyAffected.containsKey(locationInNewTile)) {
                                        //We add the city because it is present on this tile
                                        if (!citiesOnTile.contains(city)) {
                                            citiesOnTile.add(city);
                                        }
                                        city.enlargeAggregate(col, row, tile, locationInNewTile);
                                        cityAlreadyAffected.put(locationInNewTile, city);
                                        newCityAggregatesEmplacements.remove(locationInNewTile);
                                    }
                                    else {
                                        CityAggregate cityToBeCompleted = cityAlreadyAffected.get(locationInNewTile);
                                        if (city != cityToBeCompleted) {
                                            //We add the city because it is present on this tile
                                            if (citiesOnTile.contains(city)) {
                                                citiesOnTile.remove(city);
                                            }
                                            cityToBeCompleted.merge(city);
                                            updatedCityAggregates.remove(city);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        cityAggregates = updatedCityAggregates;
        //Boucles qui permet de supprimer les références vers les villes qui ont été mergées
        for (FieldAggregate field : fieldAggregates) {
            Set<CityAggregate> citiesToBeDeleted = new HashSet<>();
            for (CityAggregate city : field.getBoundedCities()) {
                if (!cityAggregates.contains(city)) {
                    citiesToBeDeleted.add(city);
                }
            }
            field.deleteBoundedCities(citiesToBeDeleted);
        }

        return new CitiesUpdateResult(newCityAggregatesEmplacements, citiesOnTile);
    }

    /**
     * Update the field aggregates of the game that are already created
     *
     * @param tile
     * @param col
     * @param row
     * @param roadAggregatesEmplacements
     * @return
     */
    private Set<Set<String>> updateFields(AbstractTile tile, int col, int row, Set<Set<String>> fieldAggregatesEmplacements, Set<CityAggregate> tileCities)
    {
        Map<Set<String>, FieldAggregate> fieldAlreadyAffected = new HashMap<>();
        List<FieldAggregate> updatedFieldAggregates = new ArrayList<>();
        updatedFieldAggregates.addAll(fieldAggregates);

        Set<Set<String>> newFieldAggregatesEmplacements = new HashSet<>();
        newFieldAggregatesEmplacements.addAll(fieldAggregatesEmplacements);

        for (FieldAggregate field : fieldAggregates) {
            Set<Coord> neighboredTilesEmplacements = field.getNeighboredCoordinates(col, row);

            if (!neighboredTilesEmplacements.isEmpty()) {
                Set<String> neighborTileLocations;
                Set<String> neededLocations;

                for (Coord neighboredTilesEmplacement : neighboredTilesEmplacements) {
                    neighborTileLocations = field.getAggregatedTypesByCoord(col + neighboredTilesEmplacement.col, row + neighboredTilesEmplacement.row);

                    if (neighborTileLocations != null) {
                        neededLocations = getLocationsAuthorized(neighboredTilesEmplacement, neighborTileLocations);

                        for (Set<String> locationInNewTile : fieldAggregatesEmplacements) {
                            for (String neededLocation : neededLocations) {

                                if (locationInNewTile.contains(neededLocation)) {
                                    if (!fieldAlreadyAffected.containsKey(locationInNewTile)) {
                                        field.enlargeAggregate(col, row, tile, locationInNewTile, tileCities);
                                        fieldAlreadyAffected.put(locationInNewTile, field);
                                        newFieldAggregatesEmplacements.remove(locationInNewTile);
                                    }
                                    else {
                                        FieldAggregate fieldToBeCompleted = fieldAlreadyAffected.get(locationInNewTile);
                                        if (field != fieldToBeCompleted) {
                                            fieldToBeCompleted.merge(field);
                                            updatedFieldAggregates.remove(field);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        fieldAggregates = updatedFieldAggregates;

        return newFieldAggregatesEmplacements;
    }

    /**
     * Get free aggregates of the tile, if the current player has at least on
     * meeple to add
     *
     * @param x
     * @param y
     * @return null if there is no possibilities
     */
    public Set<Set<String>> getFreeAggregatesInTile(int x, int y)
    {
        Set<Set<String>> result = new HashSet<>();

        if (this.getCurrentPlayer().checkMeepleAvailable()) {
            Coord convertedCoord = convertCoord(x, y);
            int col = convertedCoord.col, row = convertedCoord.row;
            Set<String> currentAggregateLocations = null;

            for (RoadAggregate road : roadAggregates) {
                currentAggregateLocations = road.getTileLocations(col, row);
                if (currentAggregateLocations != null && road.getPlayers().isEmpty()) {
                    result.add(currentAggregateLocations);
                }
            }
            currentAggregateLocations = null;
            for (CityAggregate city : cityAggregates) {
                currentAggregateLocations = city.getTileLocations(col, row);
                if (currentAggregateLocations != null && city.getPlayers().isEmpty()) {
                    result.add(currentAggregateLocations);
                }
            }
            currentAggregateLocations = null;
            for (FieldAggregate field : fieldAggregates) {
                currentAggregateLocations = field.getTileLocations(col, row);
                if (currentAggregateLocations != null && field.getPlayers().isEmpty()) {
                    result.add(currentAggregateLocations);
                }
            }
            currentAggregateLocations = null;
            for (AbbayAggregate abbay : abbayAggregates) {
                currentAggregateLocations = abbay.getTileLocations(col, row);
                if (currentAggregateLocations != null && abbay.getPlayers().isEmpty()) {
                    result.add(currentAggregateLocations);
                }
            }
        }

        return result;
    }

    /**
     * Update the points and the meeples of the players, using the aggregates
     * that just has been completed
     */
    public void manageCompletedAggregates()
    {
        Map<Player, Set<Meeple>> playersToUpdate;
        Set<Player> winningPlayers;

        //We create a list of aggregates, by combining cities and roads, the aggregate types that can be completed during a game
        List<AbstractAggregate> aggregates = new ArrayList<>();
        aggregates.addAll(cityAggregates);
        aggregates.addAll(roadAggregates);
        aggregates.addAll(abbayAggregates);

        for (AbstractAggregate aggregate : aggregates) {
            //get aggregates that are currently not completed
            if (!aggregate.isCompleted()) {
                //Get the aggregates that just has been completed during this round
                if (aggregate.checkIsCompleted()) {
                    playersToUpdate = aggregate.getPlayers();
                    winningPlayers = aggregate.getWinningPlayers();
                    int score = aggregate.countPoints();
                    //Update the score of the winning playesr of this aggregate
                    for (Player player : winningPlayers) {
                        player.addToScore(score);
                    }
                    //Browse all of the players and their meeples
                    for (Map.Entry<Player, Set<Meeple>> entry : playersToUpdate.entrySet()) {
                        Set<Meeple> meeples = entry.getValue();
                        for (Meeple meeple : meeples) {
                            //Set all the meeples to "not used"
                            meeple.setIsUsed(false);
                            meeple.removeMeepleFromType();
                        }
                    }
                }
            }
        }
    }

    public void managePointsEndGame()
    {
        Set<Player> winningPlayers;

        List<AbstractAggregate> aggregates = new ArrayList<>();
        aggregates.addAll(cityAggregates);
        aggregates.addAll(roadAggregates);
        aggregates.addAll(abbayAggregates);
        aggregates.addAll(fieldAggregates);

        for (AbstractAggregate aggregate : aggregates) {
            //Update only the aggregates that are not yet completed
            if (!aggregate.isCompleted()) {
                winningPlayers = aggregate.getWinningPlayers();
                int score = aggregate.countPoints();
                //Update the score of the winning playesr of this aggregate
                for (Player player : winningPlayers) {
                    player.addToScore(score);
                }
            }

        }
    }
}
