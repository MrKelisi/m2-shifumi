package m2.devmobile.shifumi.thread;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import m2.devmobile.shifumi.JeuActivity;

/**
 * Classe utilisée par le CLIENT pour écouter les réponses du serveur.
 */
public class InterlocuteurServeur extends Thread {

    public static final String TAG = "InterlocuteurServeur";

    private JeuActivity activity;
    private InetAddress adresseServeur;
    private BufferedReader fluxEntrant;
    private PrintStream fluxSortant;

    public InterlocuteurServeur(JeuActivity activity) throws UnknownHostException {
        this.activity = activity;
        this.adresseServeur = InetAddress.getByName(activity.adresseServeur);
    }

    public void envoyer(String ligne) {
        fluxSortant.println(ligne);
    }

    @Override
    public void run() {
        super.run();
        try {
            Socket socket = new Socket(adresseServeur, activity.port);
            this.fluxEntrant = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.fluxSortant = new PrintStream(socket.getOutputStream());

            Log.e(TAG, "Le client est prêt");

            while(!isInterrupted()) {
                String reponse = this.fluxEntrant.readLine();
                Log.e(TAG, String.format("Message de serveur : %s", reponse));

                switch (reponse) {
                    case "choix_serveur":
                        activity.sonChoix = Integer.parseInt(fluxEntrant.readLine());
                        activity.resultat.setText("");
                        break;
                    case "reveler":
                        activity.reveler();
                        break;
                    case "rejouer":
                        activity.rejouer();
                        break;
                }
            }

            Log.e(TAG, "Le client s'est arrêté");
            socket.close();

        } catch(Exception e) {
            Log.e(TAG, "Le client s'est arrêté à cause d'une erreur :");
            e.printStackTrace();
            activity.finish();
        }
    }

}
