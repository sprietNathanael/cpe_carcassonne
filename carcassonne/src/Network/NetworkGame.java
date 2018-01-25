/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package Network;

import RessourcesGlobalVariables.eNetworkActions;
import carcassonne.controller.AbstractCarcassonneGameController;
import carcassonne.controller.CarcassonneGameControllerInterface;
import carcassonne.model.carcassonnegame.CarcassonneGame;
import carcassonne.model.carcassonnegame.CarcassonneGameInterface;
import carcassonne.model.player.Meeple;
import carcassonne.model.player.Player;
import carcassonne.model.tile.AbstractTile;
import carcassonne.view.CarcassonneIHM.ClientWindow;
import carcassonne.view.CarcassonneIHM.menuStart.ParamPlayers;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.lang.Thread.sleep;
import java.net.Socket;
import java.util.HashSet;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thomas
 */
public class NetworkGame extends Observable implements CarcassonneGameInterface
{

    private Socket socket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    
    private AbstractCarcassonneGameController controller;

    @SuppressWarnings("unchecked")
    public NetworkGame(String ipAddr, String pseudo) throws Exception
    {
        try {
            socket = new Socket(ipAddr, 6666);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());           
            System.out.println("Socket client crée");
            
            // Send pseudo
            outputStream.writeObject(pseudo);
            // Receive color
            Set<String> color = new HashSet<>();
            String colorTemp = (String) inputStream.readObject();
            System.out.println("couleur recue : " + colorTemp);
            color.add(colorTemp);
            // Receive first game
            CarcassonneGameInterface game = (CarcassonneGameInterface) inputStream.readObject();
            ClientWindow cl = new ClientWindow(color, (CarcassonneGameInterface) game);
           
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void receiveGame() throws Exception
    {
        CarcassonneGameInterface game = (CarcassonneGameInterface) inputStream.readObject();
        String msg = (String) inputStream.readObject();
        controller.update((Observable) game, msg); // à voir
    }

    @Override
    public void putTile(AbstractTile tile, int row, int column) throws Exception
    {
        outputStream.writeObject(eNetworkActions.putTile);
        outputStream.writeObject(row);
        outputStream.writeObject(column);
    }

    @Override
    public void beginGame()
    {
        try {
            outputStream.writeObject(eNetworkActions.beginGame);
        } catch (IOException ex) {
            Logger.getLogger(NetworkGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void endTurn()
    {
        try {
            outputStream.writeObject(eNetworkActions.endTurn);
        } catch (IOException ex) {
            Logger.getLogger(NetworkGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void putMeeple(Meeple meeple, AbstractTile tile, Player player, String coordinates)
    {
        try {
            outputStream.writeObject(eNetworkActions.putMeeple);
            outputStream.writeObject(coordinates);
        } catch (IOException ex) {
            Logger.getLogger(NetworkGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void rotateCurrentTileRight()
    {
        try {
            outputStream.writeObject(eNetworkActions.rotateRight);
        } catch (IOException ex) {
            Logger.getLogger(NetworkGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void addObserver(Observer o)
    {
        super.addObserver(o);
        //this.notifyBoardChanged();
    }

}
