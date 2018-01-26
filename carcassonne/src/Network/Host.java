/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package Network;

import RessourcesGlobalVariables.Colors;
import RessourcesGlobalVariables.PlayerTypes;
import RessourcesGlobalVariables.eNetworkActions;
import carcassonne.controller.CarcassonneGameController;
import carcassonne.coord.Coord;
import carcassonne.model.carcassonnegame.CarcassonneGame;
import carcassonne.model.tile.AbstractTile;
import carcassonne.notifyMessage.ObserverMessage;
import carcassonne.view.CarcassonneIHM.menuStart.Online;
import carcassonne.view.CarcassonneIHM.menuStart.ParamPlayers;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
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
    private CarcassonneGameController controller;
    private List<ParamPlayers> paramPlayers; // just for initialization
    private int currentPlayerIndex;
    private Thread clientWaitingThread;
    private Thread messageWaitingThread;
    private Online menu;

    public Host(ParamPlayers paramPlayer, Online menu)
    {
        sockets = new HashMap<>();
        paramPlayers = new LinkedList<>();
        inS = new HashMap<>();
        outS = new HashMap<>();
        currentPlayerIndex = 0;
        this.menu = menu;

        paramPlayers.add(paramPlayer);
        currentPlayerIndex++;
        messageWaitingThread = new Thread(new WaitAndReceiveInformations());

        try {
            clientWaitingThread = new Thread(new WaitAndAcceptsClient());
            clientWaitingThread.start();
            System.out.println("Serveur prêt !");
            //Thread tj = new Thread 
       } catch (Exception e) {

            e.printStackTrace();
        }
    }
    
    public void setController(CarcassonneGameController controller)
    {
        this.controller = controller;
    }

    

    private class WaitAndAcceptsClient implements Runnable
    {

        ServerSocket socketserver;
        Socket socket;

        public void run()
        {
            try {
                socketserver = new ServerSocket(6666);
                InetAddress localAddress = InetAddress.getLocalHost();
                System.out.println("Local adress " + localAddress);

                while (true){
                    if(currentPlayerIndex < MAX_PLAYERS) {
                        socket = socketserver.accept();
                        System.out.println("Socket accepté");
                        outS.put(currentPlayerIndex, new ObjectOutputStream(socket.getOutputStream()));
                        inS.put(currentPlayerIndex, new ObjectInputStream(socket.getInputStream()));
                        sockets.put(currentPlayerIndex, socket);
                        ParamPlayers player = receiveClientInfomation(inS.get(currentPlayerIndex));
                        sendToClient(currentPlayerIndex, new NetworkMessage(eNetworkActions.currentColor,Colors.tab.get(currentPlayerIndex)));
                        sendToAllClients(new NetworkMessage(eNetworkActions.allPlayers, paramPlayers));
                        currentPlayerIndex++;
                    }
                    else
                    {
                        socket = socketserver.accept();
                        System.out.println("Socket rejettée");
                        (new ObjectOutputStream(socket.getOutputStream())).writeObject(new NetworkMessage(eNetworkActions.serverFull, null));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    
    private ParamPlayers receiveClientInfomation(ObjectInputStream in) throws Exception
    {
        String pseudo = (String) in.readObject();
        System.out.println("pseudo : " + pseudo);
        ParamPlayers p = new ParamPlayers(pseudo, Colors.tab.get(currentPlayerIndex), PlayerTypes.player);
        paramPlayers.add(p);
        menu.addPlayer(p);
        return p;
    }
    
    
    public void beginGame()
    {
        this.clientWaitingThread.interrupt();
        this.currentPlayerIndex = 0;
        this.messageWaitingThread.start();
    }
    
    private class WaitAndReceiveInformations implements Runnable
    {
        public void run()
        {
            while(true)
            {
                if(currentPlayerIndex == 0)
                {
                    currentPlayerIndex++;
                }
                else
                {
                    try {
                        receiveAction(currentPlayerIndex);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }
    
    public void receiveAction(int clientToRead) throws Exception
    {
        System.out.println("[Server] Waiting for action of client "+clientToRead);
        NetworkMessage netMessage = (NetworkMessage)inS.get(clientToRead).readObject();
        eNetworkActions action = (eNetworkActions) netMessage.message;
        System.out.println("[Server] received a "+action+" message");
        Object object = netMessage.object;

        switch (action) {
            case rotateRight:
                controller.turnRight();
                break;
            case putTile:
                ArrayList<Object> array = (ArrayList<Object>)object;
                controller.putCurrentTile((Coord)array.get(0));
                break;
            case putMeeple:
                controller.putMeeple((String)object);
                break;
            case beginGame:
                controller.beginGame();
                break;
            case endTurn:
                controller.endTurn();
                currentPlayerIndex++;
                if(currentPlayerIndex >= paramPlayers.size())
                {
                    currentPlayerIndex = 0;
                }
                break;
        }
    }

    public void sendToClient(int playerIndex, Object o)
    {
        try {
            this.sendToSocket(this.outS.get(playerIndex), o);
        } catch (IOException ex) {
            Logger.getLogger(Host.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendToAllClients(Object o) throws Exception
    {
        for (ObjectOutputStream out : outS.values()) {
            out.reset();
            out.writeObject(o);
        }
    }
    
    public void sendToSocket(ObjectOutputStream out, Object o) throws IOException
    {
        out.writeObject(o);
        out.reset();
    }


    public List<ParamPlayers> getParamPlayers()
    {
        return paramPlayers;
    }
    
    @Override
    public void update(Observable o, Object arg)
    {
        try {
            sendToAllClients(new NetworkMessage(eNetworkActions.sendGame, arg));
        } catch (Exception ex) {
            Logger.getLogger(Host.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
