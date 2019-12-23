package m2.devmobile.shifumi;

import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;

import java.net.UnknownHostException;

import m2.devmobile.shifumi.ecouteurs.thread.InterlocuteurServeur;

public class JeuClientActivity extends JeuActivity {

    public static final String TAG = "JeuClientActivity";
    InterlocuteurServeur interlocuteur;

    public JeuClientActivity() {
        // TODO: PAS BIEN !!! Trouver une alternative
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
    }

    @Override
    public void initActivity(Bundle savedInstanceState) {

        try {
            interlocuteur = new InterlocuteurServeur(this);

            // Attendre 1s que le serveur soit prêt
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    interlocuteur.start();
                }
            }, 1000);

        } catch (UnknownHostException e) {
            Log.e(JeuClientActivity.TAG, "Le démarrage du client a échoué");
            finish();
        }

        labelActivite.setText("Vous êtes le client");

        btnPierre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monChoix.setText("J'ai choisi pierre !");
                interlocuteur.fluxSortant.println("i_chose_rock");
                interlocuteur.fluxSortant.println("what_did_you_choose");
                activationBoutons(false);
            }
        });

        btnFeuille.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monChoix.setText("J'ai choisi feuille !");
                interlocuteur.fluxSortant.println("i_chose_paper");
                interlocuteur.fluxSortant.println("what_did_you_choose");
                activationBoutons(false);
            }
        });

        btnCiseaux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monChoix.setText("J'ai choisi ciseaux !");
                interlocuteur.fluxSortant.println("i_chose_scissors");
                interlocuteur.fluxSortant.println("what_did_you_choose");
                activationBoutons(false);
            }
        });

    }
}
