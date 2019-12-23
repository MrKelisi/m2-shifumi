package m2.devmobile.shifumi.ecouteurs.bouton;

import android.content.Intent;
import android.view.View;

import m2.devmobile.shifumi.MainActivity;

public class EcouteurBoutonCreer implements View.OnClickListener {

    MainActivity activity;

    public EcouteurBoutonCreer(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        Intent serveurIntent = new Intent();
        serveurIntent.setAction("m2.devmobile.shifumi.serveuractivity");
        activity.startActivityForResult(serveurIntent, 111);
    }
}
