package m2.devmobile.shifumi.ecouteurs.thread;

import android.util.Log;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import m2.devmobile.shifumi.JeuActivity;

/**
 * Classe utilisée par le SERVEUR pour rediriger les requêtes faitent par les clients via les
 * sockets vers des interlocuteurs qui leur répondront.
 */
public class ServeurShifumi extends Thread {

    ServerSocket serverSocket;

    JeuActivity activity;
    InetAddress adresse;
    int port;

    public ServeurShifumi(JeuActivity activity, String adresse, int port) throws UnknownHostException {
        this.activity = activity;
        this.adresse = InetAddress.getByName(adresse);
        this.port = port;
    }

    @Override
    public void run() {
        super.run();

        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(adresse, port));

            Log.e("SERVEUR", "Le serveur est prêt");
            int numClient = 0;

            while(!isInterrupted()) {
                Log.e("SERVEUR", "Attente du prochain client...");

                Socket client = serverSocket.accept();
                numClient++;
                Log.e("SERVEUR", String.format("Client %d connecté !", numClient));

                InterlocuteurClient interlocuteur = new InterlocuteurClient(activity, client, numClient);
                interlocuteur.start();
            }

            Log.e("SERVEUR", "Le serveur s'est arrêté");

        } catch (Exception e) {
            Log.e("SERVEUR", String.format("Le serveur s'est arrêté à cause d'une erreur : \n%s", e.toString()));
            activity.finish();
        }
    }
}
