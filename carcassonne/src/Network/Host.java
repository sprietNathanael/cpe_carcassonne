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
import carcassonne.controller.CarcassonneGameControllerLocalNetworkServer;
import carcassonne.view.CarcassonneIHM.menuStart.ParamPlayers;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Thomas
 */
public class Host
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
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    private void ReceiveAction(ObjectInputStream in) throws Exception
    {
        eNetworkActions action = (eNetworkActions) in.readObject();
        
        switch (action)
        {
            case rotateRight :
                netController.turnRight();
                break;
            case putTile :
                break;
            case putMeeple :
                break;
            case beginGame :
                break;
            case endTurn :
                break;
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
                while (true) {
                    socket = socketserver.accept();
                    System.out.println("Socket accepté");
                    outS.add(new ObjectOutputStream(socket.getOutputStream()));
                    inS.add(new ObjectInputStream(socket.getInputStream()));
                    sockets.add(socket);
                    receiveClientInfomation(inS.get(inS.size() - 1));
                }
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

    private void sendToSocket(ObjectOutputStream out, Object o) throws IOException
    {
        out.writeObject(o);
    }

    private void sendToAllSockets(Object o) throws Exception
    {
        for (ObjectOutputStream out : outS) {
            out.writeObject(o);
        }
    }

}
