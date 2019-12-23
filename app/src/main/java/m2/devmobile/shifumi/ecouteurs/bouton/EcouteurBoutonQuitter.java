package m2.devmobile.shifumi.ecouteurs.bouton;

import android.view.View;

import m2.devmobile.shifumi.MainActivity;

public class EcouteurBoutonQuitter implements View.OnClickListener {

    MainActivity activity;

    public EcouteurBoutonQuitter(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        activity.finish();
        System.exit(0);
    }
}
