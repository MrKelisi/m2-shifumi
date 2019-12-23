package m2.devmobile.shifumi.ecouteurs.thread;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import m2.devmobile.shifumi.JeuActivity;

/**
 * Classe utilisée par le SERVEUR pour répondre les requêtes d'un client.
 * Instanciée par ServeurShifumi dès qu'un client se connecte.
 */
public class InterlocuteurClient extends Thread {

    JeuActivity activity;
    BufferedReader fluxEntrant;
    PrintStream fluxSortant;
    int numClient;

    public InterlocuteurClient(JeuActivity activity, Socket socket, int numClient) throws IOException {
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
                String requete = this.fluxEntrant.readLine();
                String reponse = "bad_request";

                Log.e("SERVEUR_INTER", String.format("Le client %d a envoyé la requête : %s", numClient, requete));

                switch (requete) {
                    case "i_chose_rock":
                        // stocker le choix
                        activity.sonChoix.setText("L'autre a choisi pierre !");
                        reponse = "ok";
                        break;
                    case "i_chose_paper":
                        // stocker le choix
                        activity.sonChoix.setText("L'autre a choisi feuille !");
                        reponse = "ok";
                        break;
                    case "i_chose_scissors":
                        // stocker le choix
                        activity.sonChoix.setText("L'autre a choisi ciseaux !");
                        reponse = "ok";
                        break;
                    case "what_did_you_choose":
                        // Attendre qu'on est choisi
                        while("".equals(activity.monChoix.getText().toString())) {}

                        switch (activity.monChoix.getText().toString()) {
                            case "J'ai choisi pierre !":
                                reponse = "i_chose_rock";
                                break;
                            case "J'ai choisi feuille !":
                                reponse = "i_chose_paper";
                                break;
                            case "J'ai choisi ciseaux !":
                                reponse = "i_chose_scissors";
                                break;
                        }
                        break;
                }

                fluxSortant.println(reponse);
            }
        } catch (Exception e) {
            Log.e("SERVEUR_INTER", String.format("Arrêt du client %d", numClient));
        }
    }
}
