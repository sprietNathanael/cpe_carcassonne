/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package Network;

import RessourcesGlobalVariables.Colors;
import RessourcesGlobalVariables.PlayerTypes;
import RessourcesGlobalVariables.eNetworkActions;
import carcassonne.controller.CarcassonneGameControllerLocalNetwork;
import carcassonne.coord.Coord;
import carcassonne.model.carcassonnegame.CarcassonneGame;
import carcassonne.notifyMessage.ObserverMessage;
import carcassonne.view.CarcassonneIHM.menuStart.ParamPlayers;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thomas
 */
public class Host implements Observer
{

    private static final int MAX_PLAYERS = 6;
    private Map<Integer, Socket> sockets;
    private Map<Integer, ObjectOutputStream> outS;
    private Map<Integer, ObjectInputStream> inS;
    private CarcassonneGameControllerLocalNetwork netController;
    private List<ParamPlayers> paramPlayers; // just for initialization
    private int currentPlayerIndex;
    private Thread t;

    public Host(String pseudo)
    {
        sockets = new HashMap<>();
        paramPlayers = new LinkedList<>();
        inS = new HashMap<>();
        outS = new HashMap<>();
        currentPlayerIndex = 0;

        paramPlayers.add(new ParamPlayers(pseudo, Colors.tab.get(currentPlayerIndex), PlayerTypes.player));
        currentPlayerIndex++;

        try {
            t = new Thread(new waitAndAcceptsClient());
            t.start();
            System.out.println("Serveur prêt !");
            //Thread tj = new Thread 
       } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void receiveAction() throws Exception
    {
        for (ObjectInputStream in : inS.values()) {
            eNetworkActions action = (eNetworkActions) in.readObject();

            switch (action) {
                case rotateRight:
                    netController.turnRight();
                    break;
                case putTile:
                    putTile(in);
                    break;
                case putMeeple:
                    putMeeple(in);
                    break;
                case beginGame:
                    netController.beginGame();
                    break;
                case endTurn:
                    netController.endTurn();
                    break;
            }
        }
    }

    @Override
    public void update(Observable o, Object arg)
    {
        try {
            sendToAllClients(arg);
        } catch (Exception ex) {
            Logger.getLogger(Host.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private class waitAndAcceptsClient implements Runnable
    {

        ServerSocket socketserver;
        Socket socket;

        public void run()
        {
            try {
                socketserver = new ServerSocket(6666);
                while (true){
                    if(currentPlayerIndex < MAX_PLAYERS-1) {
                        socket = socketserver.accept();
                        System.out.println("Socket accepté");
                        outS.put(currentPlayerIndex, new ObjectOutputStream(socket.getOutputStream()));
                        inS.put(currentPlayerIndex, new ObjectInputStream(socket.getInputStream()));
                        sockets.put(currentPlayerIndex, socket);
                        receiveClientInfomation(inS.get(currentPlayerIndex));
                        sendToClient(currentPlayerIndex, Colors.tab.get(currentPlayerIndex));
                        currentPlayerIndex++;
                    }
                    else
                    {
                        socket = socketserver.accept();
                        System.out.println("Socket rejettée");
                        (new ObjectOutputStream(socket.getOutputStream())).writeObject("full");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    
    public void beginGame()
    {
        this.t.interrupt();
    }
    
    private class waitAndReceiveInformations implements Runnable
    {
        public void run()
        {
            try {
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private void receiveClientInfomation(ObjectInputStream in) throws Exception
    {
        String pseudo = (String) in.readObject();
        System.out.println("pseudo : " + pseudo);
        ParamPlayers p = new ParamPlayers(pseudo, Colors.tab.get(currentPlayerIndex), PlayerTypes.player);
        paramPlayers.add(p);
    }
    
    public void sendToClient(int playerIndex, Object o)
    {
        try {
            this.sendToSocket(this.outS.get(playerIndex), o);
        } catch (IOException ex) {
            Logger.getLogger(Host.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendToSocket(ObjectOutputStream out, Object o) throws IOException
    {
        out.writeObject(o);
        out.reset();
    }

    public void sendToAllClients(Object o) throws Exception
    {
        for (ObjectOutputStream out : outS.values()) {
            out.reset();
            out.writeObject(o);
        }
    }

    public List<ParamPlayers> getParamPlayers()
    {
        return paramPlayers;
    }

    public void setNetController(CarcassonneGameControllerLocalNetwork netController)
    {
        this.netController = netController;
    }

    private void putTile(ObjectInputStream in) throws Exception
    {
        Coord c = (Coord) in.readObject();
        netController.putCurrentTile(c);
    }

    private void putMeeple(ObjectInputStream in) throws Exception
    {
        String coordinates = (String) in.readObject();
        netController.putMeeple(coordinates);
    }

}
