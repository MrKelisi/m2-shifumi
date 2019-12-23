package m2.devmobile.shifumi;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.net.UnknownHostException;

import m2.devmobile.shifumi.ecouteurs.thread.ServeurShifumi;

public class JeuServeurActivity extends JeuActivity {

    public static final String TAG = "JeuServeurActivity";
    ServeurShifumi serveurShifumi;

    public JeuServeurActivity() {}

    @Override
    public void initActivity(Bundle savedInstanceState) {

        try {
            serveurShifumi = new ServeurShifumi(this, adresseServeur, port);
            serveurShifumi.start();
        } catch (UnknownHostException e) {
            Log.e(JeuServeurActivity.TAG, "Le démarrage du serveur a échoué");
            finish();
        }

        labelActivite.setText("Vous êtes le serveur");

        btnPierre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monChoix.setText("J'ai choisi pierre !");
                activationBoutons(false);
            }
        });

        btnFeuille.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monChoix.setText("J'ai choisi feuille !");
                activationBoutons(false);
            }
        });

        btnCiseaux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monChoix.setText("J'ai choisi ciseaux !");
                activationBoutons(false);
            }
        });
    }
}
