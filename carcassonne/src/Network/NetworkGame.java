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
import carcassonne.view.CarcassonneIHM.menuStart.Online;
import carcassonne.view.CarcassonneIHM.menuStart.ParamPlayers;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
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
    private Online menu;
    private Set<String> color;
    NetworkGame self;

    @SuppressWarnings("unchecked")
    public NetworkGame(String ipAddr, String pseudo, Online menu) throws Exception
    {
        this.menu = menu;
        this.self = this;
        try {
            InetAddress iAddr = InetAddress.getByName(ipAddr);
            socket = new Socket(iAddr, 6666);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
            System.out.println("Socket client crée");
            color = new HashSet<>();
            Thread infoThread = new Thread(new ReceiveInformations());
            infoThread.start();
            // Send pseudo
            sendToServer(pseudo);
            

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private class ReceiveInformations implements Runnable
    {

        public void run()
        {
            boolean continueExec = true;
            while(continueExec)
            {
                try {
                    NetworkMessage netMessage = (NetworkMessage) inputStream.readObject();
                    switch(netMessage.message)
                    {
                        case serverFull:
                            System.out.println("Le serveur est complet");
                            menu.displayClientMessage("Server is Full");
                            break;
                        case allPlayers:
                            LinkedList<ParamPlayers> players = (LinkedList<ParamPlayers>)netMessage.object;
                            menu.flushPanel();
                            for(ParamPlayers player : players)
                            {
                                menu.addPlayer(player);
                            }
                            break;
                        case currentColor:
                            String arg = (String)netMessage.object;
                            System.out.println("couleur recue : " + arg);
                            color.add(arg);
                            break;
                        case sendGame:
                            // Receive first game
                            CarcassonneGameInterface game = (CarcassonneGameInterface)((ObserverMessage)netMessage.object).game;
                            ClientWindow cl = new ClientWindow(color, (CarcassonneGameInterface) game, self);
                            continueExec = false;
                            Thread t = new Thread(new ReceiveData());
                            t.start();
                            break;
                        default:
                            break;
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
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
        NetworkMessage netMessage = (NetworkMessage)inputStream.readObject();
        notifyMessage = (ObserverMessage)netMessage.object;
        System.out.println("Received a notify : "+notifyMessage.messageType);
        notifyObservers();
    }
    
    private void sendToServer(Object message)
    {
        System.out.println("[Client] Sending "+message);
        try {
            outputStream.writeObject(message);
            outputStream.reset();
        } catch (IOException ex) {
            Logger.getLogger(NetworkGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void putTile(AbstractTile tile, int row, int column) throws Exception
    {
        ArrayList<Object> objectToPass = new ArrayList<>();
        objectToPass.add(new Coord(column, row));
        this.sendToServer(new NetworkMessage(eNetworkActions.putTile, objectToPass));
    }

    @Override
    public void beginGame()
    {
        this.sendToServer(new NetworkMessage(eNetworkActions.beginGame, null));
    }

    @Override
    public void endTurn()
    {
        this.sendToServer(new NetworkMessage(eNetworkActions.endTurn, null));
    }

    @Override
    public void putMeeple(Meeple meeple, AbstractTile tile, Player player, String coordinates)
    {
        this.sendToServer(new NetworkMessage(eNetworkActions.putMeeple, coordinates));
    }
    
    @Override
    public void rotateCurrentTileRight()
    {
        this.sendToServer(new NetworkMessage(eNetworkActions.rotateRight, null));
    }

}
