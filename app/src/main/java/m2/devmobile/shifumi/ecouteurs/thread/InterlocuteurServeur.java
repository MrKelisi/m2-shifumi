package m2.devmobile.shifumi.ecouteurs.thread;

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

    InetAddress adresseServeur;
    JeuActivity activity;

    BufferedReader fluxEntrant;
    public PrintStream fluxSortant;

    public InterlocuteurServeur(JeuActivity activity) throws UnknownHostException {
        this.activity = activity;
        this.adresseServeur = InetAddress.getByName(activity.adresseServeur);
    }

    @Override
    public void run() {
        super.run();
        try {
            Socket socket = new Socket(adresseServeur, activity.port);
            this.fluxEntrant = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.fluxSortant = new PrintStream(socket.getOutputStream());

            Log.e("CLIENT", "Le client est prêt");

            while(!isInterrupted()) {
                String reponse = this.fluxEntrant.readLine();
                Log.e("CLIENT", "Réponse du serveur : " + reponse);

                switch (reponse) {
                    case "ok":
                        break;
                    case "i_chose_rock":
                        activity.sonChoix.setText("L'autre a choisi pierre !");
                        break;
                    case "i_chose_paper":
                        activity.sonChoix.setText("L'autre a choisi feuille !");
                        break;
                    case "i_chose_scissors":
                        activity.sonChoix.setText("L'autre a choisi ciseaux !");
                        break;
                }
            }

            Log.e("CLIENT", "Le client s'est arrêté");
            //socket.close();

        } catch(Exception e) {
            Log.e("CLIENT", String.format("Le client s'est arrêté à cause d'une erreur :\n%s", e.toString()));
            activity.finish();
        }
    }

}
