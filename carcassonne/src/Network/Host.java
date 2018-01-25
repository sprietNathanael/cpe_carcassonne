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
import java.util.LinkedList;
import java.util.List;
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

    private List<Socket> sockets;
    private List<ObjectOutputStream> outS;
    private List<ObjectInputStream> inS;
    private CarcassonneGameControllerLocalNetwork netController;
    private List<ParamPlayers> paramPlayers; // just for initialization

    public Host(String pseudo)
    {
        sockets = new LinkedList<>();
        paramPlayers = new LinkedList<>();
        inS = new LinkedList<>();
        outS = new LinkedList<>();

        paramPlayers.add(new ParamPlayers(pseudo, Colors.tab.get(0), PlayerTypes.player));

        try {
            Thread t = new Thread(new waitAndAcceptsClient());
            t.start();
            System.out.println("Serveur prêt !");
            t.join();
            //Thread tj = new Thread 
       } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void receiveAction() throws Exception
    {
        for (ObjectInputStream in : inS) {
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
            System.out.println("mmmmmmmmmmmmmmmmmmmm Host : "+((ObserverMessage)arg).game.getBoard().getAllTiles().size());
            sendToAllSockets(arg);
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
                //while (true) {
                socket = socketserver.accept();
                System.out.println("Socket accepté");
                outS.add(new ObjectOutputStream(socket.getOutputStream()));
                inS.add(new ObjectInputStream(socket.getInputStream()));
                sockets.add(socket);
                receiveClientInfomation(inS.get(inS.size() - 1));
                outS.get(outS.size() - 1).writeObject(Colors.tab.get(paramPlayers.size() + 1));
                //}
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

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
        ParamPlayers p = new ParamPlayers(pseudo, Colors.tab.get(paramPlayers.size()), PlayerTypes.player);
        paramPlayers.add(p);
    }

    public void sendToSocket(ObjectOutputStream out, Object o) throws IOException
    {
        out.writeObject(o);
    }

    public void sendToAllSockets(Object o) throws Exception
    {
        for (ObjectOutputStream out : outS) {
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
