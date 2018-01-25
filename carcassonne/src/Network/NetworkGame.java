/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package Network;

import RessourcesGlobalVariables.Colors;
import RessourcesGlobalVariables.eNetworkActions;
import carcassonne.controller.CarcassonneGameController;
import carcassonne.coord.Coord;
import carcassonne.model.carcassonnegame.CarcassonneGameInterface;
import carcassonne.model.player.Meeple;
import carcassonne.model.player.Player;
import carcassonne.model.tile.AbstractTile;
import carcassonne.notifyMessage.ObserverMessage;
import carcassonne.view.CarcassonneIHM.ClientWindow;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
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
    private ObserverMessage notifyMessage;

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
            String messageReceived = (String) inputStream.readObject();
            if(messageReceived.equals("full"))
            {
                System.out.println("Le serveur est complet");
            }
            else
            {
                System.out.println("couleur recue : " + messageReceived);
                color.add(messageReceived);
                // Receive first game
                CarcassonneGameInterface game = (CarcassonneGameInterface) inputStream.readObject();
                ClientWindow cl = new ClientWindow(color, (CarcassonneGameInterface) game, this);
                Thread t = new Thread(new ReceiveData());
                t.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class ReceiveData implements Runnable
    {

        public void run()
        {
            while(true)
            {
                try {
                    receiveNotifyMessage();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Notifies the observers with the current notify message
     */
    @Override
    public void notifyObservers()
    {
        super.setChanged();
        super.notifyObservers(this.notifyMessage);
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
    }

    private void receiveNotifyMessage() throws Exception
    {
        notifyMessage = (ObserverMessage) inputStream.readObject();
        notifyObservers();
    }

    @Override
    public void putTile(AbstractTile tile, int row, int column) throws Exception
    {
        outputStream.writeObject(eNetworkActions.putTile);
        outputStream.writeObject(new Coord(column, row));
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

}
