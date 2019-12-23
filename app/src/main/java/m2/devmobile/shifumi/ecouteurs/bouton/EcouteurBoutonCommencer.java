package m2.devmobile.shifumi.ecouteurs.bouton;

import android.content.Intent;
import android.view.View;

import m2.devmobile.shifumi.WifiActivity;

public class EcouteurBoutonCommencer implements View.OnClickListener {

    WifiActivity activity;

    public EcouteurBoutonCommencer(WifiActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        //TODO: dire au client de lancer l'activité JeuClientActivity

        Intent jeuIntent = new Intent();
        jeuIntent.setAction("m2.devmobile.shifumi.jeuserveuractivity");
        activity.startActivityForResult(jeuIntent, 333);
    }
}
