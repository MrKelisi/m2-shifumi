package m2.devmobile.shifumi.thread;

import android.util.Log;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import m2.devmobile.shifumi.JeuActivity;

/**
 * Classe utilisée par le SERVEUR pour rediriger les requêtes faitent par les clients
 * vers des InterlocuteurClient qui se chargeront de leur répondre.
 */
public class ServeurShifumi extends Thread {

    public static final String TAG = "ServeurShifumi";

    private JeuActivity activity;
    private InetAddress adresse;
    private int port;
    private int numClient = 0;

    public ServeurShifumi(JeuActivity activity, String adresse, int port) throws UnknownHostException {
        this.activity = activity;
        this.adresse = InetAddress.getByName(adresse);
        this.port = port;
    }

    @Override
    public void run() {
        super.run();

        try {
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(adresse, port));
            Log.e(TAG, "Le serveur est prêt");

            while(!isInterrupted()) {
                Log.e(TAG, "Attente du prochain client...");

                Socket client = serverSocket.accept();
                Log.e(TAG, String.format("Client %d connecté !", ++numClient));

                InterlocuteurClient interlocuteur = new InterlocuteurClient(activity, client, numClient);
                interlocuteur.start();
            }

            Log.e(TAG, "Le serveur s'est arrêté");

        } catch (Exception e) {
            Log.e(TAG, "Le serveur s'est arrêté à cause d'une erreur :");
            e.printStackTrace();
            activity.finish();
        }
    }
}
