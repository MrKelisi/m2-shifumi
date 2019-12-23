package m2.devmobile.shifumi.ecouteurs.bouton;

import android.content.Intent;
import android.view.View;

import m2.devmobile.shifumi.MainActivity;

public class EcouteurBoutonRejoindre implements View.OnClickListener {

    MainActivity activity;

    public EcouteurBoutonRejoindre(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        Intent clientIntent = new Intent();
        clientIntent.setAction("m2.devmobile.shifumi.clientactivity");
        activity.startActivityForResult(clientIntent, 222);
    }
}
