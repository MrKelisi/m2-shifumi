package m2.devmobile.shifumi.thread;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import m2.devmobile.shifumi.JeuActivity;

/**
 * Classe utilisée par le SERVEUR pour répondre aux requêtes d'un unique client.
 * Instanciée par ServeurShifumi dès qu'un client se connecte.
 */
public class InterlocuteurClient extends Thread {

    public static final String TAG = "InterlocuteurClient";

    private JeuActivity activity;
    private int numClient;
    private BufferedReader fluxEntrant;
    private PrintStream fluxSortant;

    InterlocuteurClient(JeuActivity activity, Socket socket, int numClient) throws IOException {
        this.activity = activity;
        this.numClient = numClient;
        this.fluxEntrant = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.fluxSortant = new PrintStream(socket.getOutputStream());
    }

    @Override
    public void run() {
        super.run();

        try {
            while (!isInterrupted()) {
                String requete = fluxEntrant.readLine();
                Log.e(TAG, String.format("Message de client %d : %s", numClient, requete));

                switch (requete) {
                    case "choix_client":
                        activity.sonChoix = Integer.parseInt(fluxEntrant.readLine());

                        while(!activity.aRepondu);

                        fluxSortant.println("choix_serveur");
                        fluxSortant.println(activity.monChoix);
                        activity.resultat.setText("");
                        activity.aRepondu = false;

                        sleep(1000);
                        fluxSortant.println("reveler");
                        activity.reveler();

                        sleep(1500);
                        fluxSortant.println("rejouer");
                        activity.rejouer();
                        break;
                    default:
                        break;
                }
            }

        } catch (Exception e) {
            Log.e(TAG, String.format("Arrêt de l'interlocuteur chargé du client %d", numClient));
            e.printStackTrace();
        }
    }
}
