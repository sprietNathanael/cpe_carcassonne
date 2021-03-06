/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.carcassonnegame;

import RessourcesGlobalVariables.PlayerTypes;
import carcassonne.coord.Coord;
import carcassonne.model.aggregate.AbbayAggregate;
import carcassonne.model.aggregate.AbstractAggregate;
import carcassonne.model.aggregate.CityAggregate;
import carcassonne.model.aggregate.FieldAggregate;
import carcassonne.model.aggregate.RiverAggregate;
import carcassonne.model.aggregate.RoadAggregate;
import carcassonne.model.board.Board;
import carcassonne.model.player.Meeple;
import carcassonne.model.tile.AbstractTile;
import carcassonne.model.player.Player;
import carcassonne.model.set.BasicSet;
import carcassonne.model.set.InnsAndCathedralsSet;
import carcassonne.model.set.RiverSet;
import carcassonne.model.set.SetInterface;
import carcassonne.model.tile.CasualTile;
import carcassonne.model.type.AbstractType;
import carcassonne.model.type.CityType;
import carcassonne.model.type.FieldType;
import carcassonne.model.type.RoadType;
import carcassonne.notifyMessage.ObserverMessage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents a carcassonne game, which aggregates all the model entities
 */
public class CarcassonneGame extends Observable implements CarcassonneGameInterface, Serializable
{

    private ArrayList<Player> players;
    private Board board;
    private int currentPlayerIndex;
    private List<AbstractTile> pile;
    private AbstractTile firstTile;
    private Set<Meeple> meeplesSet;
    private AbstractTile currentTile;
    private Coord lastPutTile;
    private ArrayList<Coord> placements;
    private List<RoadAggregate> roadAggregates;
    private List<CityAggregate> cityAggregates;
    private List<FieldAggregate> fieldAggregates;
    private List<AbbayAggregate> abbayAggregates;
    private RiverAggregate riverAggregate;
    private Player previousPlayer;
    private String notifyMessage;
    private Player winningPlayer;
    private boolean riverExtensionIsUsed;
    private boolean icExtensionIsUsed;

    public CarcassonneGame() throws Exception
    {
        this(new ArrayList<Player>(), new HashSet<SetInterface>(Arrays.asList(new BasicSet())));
    }

    public CarcassonneGame(ArrayList<Player> players) throws Exception
    {
        this(players, new HashSet<SetInterface>(Arrays.asList(new BasicSet())));
    }

    /**
     * Constructor for CarcassonneGame from an existing list of players
     *
     * @param players existing list of players
     * @throws java.lang.Exception
     */
    public CarcassonneGame(ArrayList<Player> players, Set<SetInterface> sets) throws Exception
    {
        this.board = new Board();
        this.pile = new ArrayList<>();
        this.meeplesSet = new HashSet<>();
        this.previousPlayer = null;
        this.riverExtensionIsUsed = false;
        this.icExtensionIsUsed = false;
        HashSet<SetInterface> sideSet = new HashSet<>();
        for (SetInterface set : sets) {
            if (set instanceof InnsAndCathedralsSet) {
                this.icExtensionIsUsed = true;
            }
            else if (set instanceof RiverSet) {
                this.riverExtensionIsUsed = true;
            }
            if (set.isNotShuffleable()) {
                sideSet.add(set);
            }
            else {
                this.pile.addAll(set.getSet());
                this.meeplesSet.addAll(set.getMeeples());
                if (set.getFirstTile() != null) {
                    this.firstTile = set.getFirstTile();
                }
            }
        }
        for (Player player : players) {
            for (Meeple meeple : this.meeplesSet) {
                Meeple intermediate = (Meeple) meeple.clone();
                intermediate.setPlayer(player);
                player.addMeeple(intermediate);
            }
        }
        Collections.shuffle(this.pile, new Random(System.currentTimeMillis()));
        for (SetInterface set : sideSet) {
            this.pile.addAll(0, set.getSet());
            this.meeplesSet.addAll(set.getMeeples());
            if (set.getFirstTile() != null) {
                this.firstTile = set.getFirstTile();
            }
        }
        this.currentPlayerIndex = 0;
        this.placements = new ArrayList<>();
        this.players = players;
        roadAggregates = new ArrayList<>();
        cityAggregates = new ArrayList<>();
        fieldAggregates = new ArrayList<>();
        abbayAggregates = new ArrayList<>();
        this.lastPutTile = null;
    }

    public Set<Meeple> getMeeplesSet()
    {
        return meeplesSet;
    }
    
    public void setPlayerIA(String color)
    {
        for(Player player : this.players)
        {
            if(player.getColor().equals(color))
            {
                player.setMode(PlayerTypes.basicIA);
                break;
            }
        }
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

    public Coord getLastPutTile()
    {
        return lastPutTile;
    }

    public Player getPreviousPlayer()
    {
        return this.previousPlayer;
    }

    public void setPreviousPlayer(Player previousPlayer)
    {
        this.previousPlayer = previousPlayer;
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
     * Get the winner
     *
     * @return
     */
    public Player getWinner()
    {
        return this.winningPlayer;
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

    @Override
    public void beginGame()
    {
        try {
            this.putTile(this.getFirstTile(), 0, 0);
        } catch (Exception ex) {
            Logger.getLogger(CarcassonneGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.beginTurn();
    }

    public void beginTurn()
    {
        processNextTile();
    }

    @Override
    public void endTurn()
    {
        this.manageCompletedAggregates();
        //System.out.println("===========\nPoints des joueurs :" + this.getPlayers());
        this.nextPlayer();
        this.beginTurn();
    }

    /**
     * Process the next Tile
     */
    private void processNextTile()
    {
        this.drawFromPile();
        if (this.currentTile != null) {
            System.out.println("La pièce piochée est : " + this.currentTile.getName());
            //System.out.println(this.currentTile);
            this.notifyNewTurn();
            // If the current tile can be placed
            if (this.refreshPlacements()) {
                this.notifyPlacementsReady();
            }
            // Else put back the current tile and draw another one
            else {
                this.putBackCurrentTile();
                this.processNextTile();
            }
        }
        else {
            System.out.println("Fin de partie");
            this.notifyGameEnds();
        }
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
        this.setPreviousPlayer(this.getCurrentPlayer());
        board.addTile(tile, row, column);
        this.lastPutTile = new Coord(column, row);
        this.manageNewTileAggregates(tile, row, column);
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
    @Override
    public void putMeeple(Meeple meeple, AbstractTile tile, Player player, String coordinates)
    {
        List<AbstractAggregate> aggregates = new ArrayList<>();

        currentTile.putMeeple(coordinates, meeple);
        meeple.setCurrentType(currentTile.getType(coordinates));
        meeple.setIsUsed(true);

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
    }

    /**
     * Get the board
     *
     * @return
     */
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
     * @return false if no placements has been found, true otherwise
     */
    public boolean refreshPlacements()
    {
        this.placements.clear();

        //Manages the specific case of the river
        if (this.riverExtensionIsUsed && !this.riverAggregate.checkIsCompleted()) {
            Coord possibleCoord = riverAggregate.getNextPositionTile();
            this.placements.add(convertCoord(possibleCoord.col, possibleCoord.row));
        }
        //Other cases
        else if (this.currentTile != null) {

            this.placements = this.board.getTilePossiblePlacements(this.currentTile);
        }
        return !this.placements.isEmpty();
    }

    /**
     * Put back the current tile and re-shuffle the pile
     */
    public void putBackCurrentTile()
    {
        this.pile.add(this.currentTile);
        Collections.shuffle(this.pile);
    }

    /**
     * Notifies the observers with the current notify message
     */
    @Override
    public void notifyObservers()
    {
        super.setChanged();
        ObserverMessage obsMessage = new ObserverMessage(this.notifyMessage, this);
        super.notifyObservers(obsMessage);
    }

    /**
     * Add an observer
     *
     * @param o
     */
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
        //System.out.println("Check "+tile);
        boolean result = this.board.canTileBePlacedHere(coordinates, tile);
        //Manage specific case of river
        if (result && riverExtensionIsUsed && !riverAggregate.isCompleted()) {
            result = riverAggregate.checkNewPlacementCorrect(convertCoord(coordinates.col, coordinates.row), tile);
        }

        return result;
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
     * Notifies the observers when this is a new turn
     */
    public void notifyNewTurn()
    {
        this.notifyMessage = "newTurn";
        this.notifyObservers();
    }

    /**
     * Notifies the observers when the turn ends
     */
    public void notifyEndTurn()
    {
        this.notifyMessage = "endTurn";
        this.notifyObservers();
    }

    /**
     * Notifies the observers when the board has changed with the right message
     */
    public void notifyBoardChanged()
    {
        this.notifyMessage = "boardChanged";
        this.notifyObservers();
    }

    /**
     * Notifies the observers that the placements are ready
     */
    public void notifyPlacementsReady()
    {
        this.notifyMessage = "placementsReady";
        this.notifyObservers();
    }

    /**
     * Notifies the observers that the game ended
     */
    public void notifyGameEnds()
    {
        this.notifyMessage = "gameEnds";
        // Manage the last points
        this.managePointsEndGame();
        // Get the winner
        this.winningPlayer = this.players.get(0);
        for (Player player : this.players) {
            if (player.getPoints() > this.winningPlayer.getPoints()) {
                this.winningPlayer = player;
            }
        }
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

    /**
     *
     * @return the pile size
     */
    public int getPileSize()
    {
        return this.pile.size();
    }

    /**
     * Manages the aggregates including enlarge and merge
     *
     * @param tile
     * @param y
     * @param x
     */
    private void manageNewTileAggregates(AbstractTile tile, int y, int x)
    {
        Coord convertedCoord = convertCoord(x, y);
        int col = convertedCoord.col, row = convertedCoord.row;

        //Récupère tous les aggrégats des différents types
        Set<Set<String>> roadAggregatesEmplacements = tile.getRoadAggregateEmplacements();
        Set<Set<String>> cityAggregatesEmplacements = tile.getCityAggregateEmplacements();
        Set<Set<String>> fieldAggregatesEmplacements = tile.getFieldAggregateEmplacements();
        Set<String> abbayAggregateEmplacements = tile.getAbbayAggregateEmplacements();
        Set<String> riverAggregateEmplacements = tile.getRiverAggregateEmplacements();

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
            List<AbstractAggregate> fieldTested = new ArrayList<>();
            fieldTested.addAll(fieldAggregates);

            if (!isPartOfComplexAggregate(currentFieldEmplacement, fieldTested, col, row)) {
                FieldAggregate newField = new FieldAggregate(col, row, tile, currentFieldEmplacement, tileCities);
                fieldAggregates.add(newField);
            }
        }

        //--- Abbayes ---//
        if (!abbayAggregateEmplacements.isEmpty()) {
            //Création d'une abbay s'il y en a une sur la carte
            AbbayAggregate newAbbey = new AbbayAggregate(col, row, tile, abbayAggregateEmplacements);
            //On récupère toutes les tuiles voisines
            for (Map.Entry<Coord, AbstractTile> entry : board.getNearTilesAbbayRange(convertedCoord, tile).entrySet()) {
                Coord neighbordCoord = entry.getKey();
                AbstractTile neighbordTile = entry.getValue();
                Set<String> emptyLocations = new HashSet<>();
                newAbbey.enlargeAggregate(neighbordCoord.col, neighbordCoord.row, neighbordTile, emptyLocations);
            }
            abbayAggregates.add(newAbbey);

        }
        manageExistingAbbayAggregates(convertedCoord, tile);

        //--- River ---//
        if (!riverAggregateEmplacements.isEmpty()) {
            //Si la rivière existe déjà, on la complète avec la nouvelle tuile.
            if (riverAggregate != null) {
                riverAggregate.enlargeAggregate(col, row, tile, riverAggregateEmplacements);
            }
            else {
                //Création de la rivière si elle n'existe pas encore
                riverAggregate = new RiverAggregate(col, row, tile, riverAggregateEmplacements);
            }
        }
    }

    /**
     * Manages abbays aggregates only
     *
     * @param coord
     * @param tile
     */
    private void manageExistingAbbayAggregates(Coord convertedCoord, AbstractTile tile)
    {
        HashMap<Coord, AbstractTile> nearTiles = board.getNearTilesAbbayRange(convertedCoord, tile);
        List<AbbayAggregate> abbeys = new ArrayList<>();
        abbeys.addAll(abbayAggregates);

        //Parcours des abbayes présentes
        for (AbbayAggregate abbey : abbeys) {
            //Parcours des coordonnées voisines
            for (Map.Entry<Coord, AbstractTile> entry : nearTiles.entrySet()) {
                Coord currentCoord = entry.getKey();
                Set<String> emptyLocations = new HashSet<>();

                //Si la coordonnée voisine pointe sur la tuile où le batiment de l'abbaye est présent, c'est une tuile voisine donc on l'ajoute
                if (abbey.coordsAreCenterOfAgg(currentCoord)) {
                    abbey.enlargeAggregate(convertedCoord.col, convertedCoord.row, tile, emptyLocations);
                }
            }
        }
    }

    /**
     * Compare and return similar string
     *
     * @param neighborTileLocations
     * @param authorizedString
     * @return
     */
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

    /**
     * Converts coord from IHM to Model
     *
     * @param x
     * @param y
     * @return
     */
    public static Coord convertCoord(int x, int y)
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
            road.cleanRoadEdgesMap();
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
            city.cleanCityEdgesMap();
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
     * Get free aggregates of the tile, if the current player has at least one
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
            for (CityAggregate city : cityAggregates) {
                currentAggregateLocations = city.getTileLocations(col, row);
                if (currentAggregateLocations != null && city.getPlayers().isEmpty()) {
                    result.add(currentAggregateLocations);
                }
            }
            for (FieldAggregate field : fieldAggregates) {
                currentAggregateLocations = field.getTileLocations(col, row);
                if (currentAggregateLocations != null && field.getPlayers().isEmpty()) {
                    result.add(currentAggregateLocations);
                }
            }

            Set<String> abbayAggEmp = currentTile.getAbbayAggregateEmplacements();
            if (!abbayAggEmp.isEmpty()) {
                result.add(abbayAggEmp);
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
                        player.addToScore(score, aggregate.getType());
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

    /**
     * Manages the end games. Counts points from uncompleted aggregates
     */
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
                    player.addToScore(score, aggregate.getType());
                }
            }

        }
    }

    /**
     * Activate the river extension
     *
     * @deprecated
     */
    public void useRiverExtension()
    {
        //Indicate we now use the extension
        riverExtensionIsUsed = true;
        RiverSet riverSet = new RiverSet();
        List<AbstractTile> newPile = new ArrayList<>();

        //The first tile changes
        firstTile = riverSet.getFirstTile();
        //We add the river add the beginning of the first tile changes
        newPile.addAll(riverSet.getSet());
        newPile.addAll(this.pile);

        this.pile = newPile;
    }

    /**
     * @deprecated
     */
    public void useInnsAndCathedralsExtension()
    {
        //Indicate we now use the extension
        icExtensionIsUsed = true;
        InnsAndCathedralsSet icSet = new InnsAndCathedralsSet();
        this.pile.addAll(icSet.getSet());
        Collections.shuffle(this.pile, new Random(System.currentTimeMillis()));
    }

    public List<FieldAggregate> getFieldAggregates()
    {
        return fieldAggregates;
    }

    /**
     * Method that check if some current locations already compose an existing
     * aggregate. We need this test to prevent complex aggregate to being
     * duplicated
     *
     * @param currentFieldEmplacement Emplacement of the tile to be tested
     * @param aggregates List of agggregates to test (this is a list of all the
     * aggregates from only one specific type (eg: Field)
     * @param col Coords
     * @param row Coords
     * @return True if those locations are already used
     */
    private boolean isPartOfComplexAggregate(Set<String> currentFieldEmplacement, List<AbstractAggregate> aggregates, int col, int row)
    {
        boolean result = false;

        for (AbstractAggregate aggregate : aggregates) {
            Set<String> currentFieldLocations = aggregate.getAggregatedTypesByCoord(col, row);

            if (currentFieldLocations != null) {
                for (String location : currentFieldLocations) {
                    if (currentFieldEmplacement.contains(location)) {
                        result = true;
                        break;
                    }
                }
            }
        }
        return result;
    }

    public void setRiverExtensionIsUsed(boolean b)
    {
        riverExtensionIsUsed = b;
    }

    public void setInnsAndCathedralsExtensionIsUsed(boolean b)
    {
        icExtensionIsUsed = b;
    }

    public int getCurrentPlayerIndex()
    {
        return currentPlayerIndex;
    }

    @Override
    public void rotateCurrentTileRight()
    {
    }

    /* -------------
    Methods for AI :
    ------------- */
    /**
     * Récupère les aggrégats de tous les types, s'ils sont libres et présents
     * aux coordonnées courantes
     *
     * @param col
     * @param row
     * @return
     */
    public Set<AbstractAggregate> getFreeAggregatesOfTile(int col, int row)
    {
        Set<AbstractAggregate> aggregates = new HashSet<>();
        Set<AbstractAggregate> result = new HashSet<>();

        aggregates.addAll(roadAggregates);
        aggregates.addAll(cityAggregates);
        aggregates.addAll(fieldAggregates);
        aggregates.addAll(abbayAggregates);

        //parcours des aggregats
        for (AbstractAggregate aggregate : aggregates) {
            //test si on est sur la bonne tuile
            if (aggregate.getPlayers().isEmpty() && aggregate.getAggregatedTiles().containsKey(convertCoord(col, row))) {
                result.add(aggregate);
            }
        }

        return result;
    }

    /**
     * Renvoie la coordonnées de la tuile posée la plus intéressante pour le
     * joueur (Si abbaye, cathédrale, auberge libre)
     *
     * @param freeAgg
     * @param col
     * @param row
     * @return
     */
    public String getSpecialBonusTypesInTile(Set<AbstractAggregate> freeAgg, int col, int row)
    {
        List<AbstractType> tileTypes;
        AbstractType wantedType = null;
        String result = null;

        Coord coord = convertCoord(col, row);
        for (AbstractAggregate agg : freeAgg) {
            tileTypes = agg.getAggregatedAbstractTypesByCoord(coord.col, coord.row);

            //Si abbaye et type non vide = tuile de l'abbaye qu'on retourne
            if (agg instanceof AbbayAggregate && !tileTypes.isEmpty()) {
                wantedType = tileTypes.get(0);
            }
            //Si ville et un type avec une cathédrale = type qu'on retourne
            else if (agg instanceof CityAggregate) {
                for (AbstractType type : tileTypes) {
                    CityType cityType = (CityType) type;
                    if (cityType.hasCathedral) {
                        wantedType = type;
                        break;
                    }
                }
            }
            //Si route et un type avec une auberge = type qu'on retourne
            else if (agg instanceof RoadAggregate) {
                for (AbstractType type : tileTypes) {
                    RoadType roadType = (RoadType) type;
                    if (roadType.hasInn) {
                        wantedType = type;
                        break;
                    }
                }
            }
            //Si un type déjà trouvé, on stop le parcours
            if (wantedType != null) {
                //Récupère la tuile correspondante
                AbstractTile tile = agg.getAggregatedTiles().get(coord);
                //récupère la location du type voulu
                result = tile.getLocation(wantedType);
                //Arrête le parcours de la boucle
                break;
            }
        }

        return result;
    }

    public String getGoodCityLocation(Set<AbstractAggregate> freeAbstractAgg, int col, int row)
    {
        List<AbstractType> tileTypes;
        AbstractType wantedType = null;
        String result = null;
        Coord coord = convertCoord(col, row);

        for (AbstractAggregate agg : freeAbstractAgg) {
            if (agg instanceof CityAggregate && agg.getAggregatedTiles().size() > 1) {
                tileTypes = agg.getAggregatedAbstractTypesByCoord(coord.col, coord.row);
                if (tileTypes != null && !tileTypes.isEmpty()) {
                    wantedType = tileTypes.get(0);
                    result = agg.getAggregatedTiles().get(coord).getLocation(wantedType);
                    System.out.println("Pose forcée !");
                }
            }
        }
        
        return result;
    }
}
