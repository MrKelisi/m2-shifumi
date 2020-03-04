package m2.devmobile.shifumi;

import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;

import java.net.UnknownHostException;

import m2.devmobile.shifumi.thread.InterlocuteurServeur;

public class JeuClientActivity extends JeuActivity {

    public static final String TAG = "JeuClientActivity";
    InterlocuteurServeur interlocuteur;

    public JeuClientActivity() {
        //TODO: voir si une alternative existe
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
    }

    @Override
    public void initActivity(Bundle savedInstanceState) {

        try {
            interlocuteur = new InterlocuteurServeur(this);

            // Attendre que le serveur soit prêt
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    interlocuteur.start();
                }
            }, 100);

        } catch (UnknownHostException e) {
            Log.e(JeuClientActivity.TAG, "Le démarrage du client a échoué");
            finish();
        }

        labelActivite.setText("Vous êtes le client");

        btnPierre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cliquer(-1);
                interlocuteur.envoyer("choix_client");
                interlocuteur.envoyer(String.valueOf(monChoix));
            }
        });

        btnFeuille.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cliquer(0);
                interlocuteur.envoyer("choix_client");
                interlocuteur.envoyer(String.valueOf(monChoix));
            }
        });

        btnCiseaux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cliquer(1);
                interlocuteur.envoyer("choix_client");
                interlocuteur.envoyer(String.valueOf(monChoix));
            }
        });

    }
}
