package m2.devmobile.shifumi.ecouteurs.bouton;

import android.view.View;

import m2.devmobile.shifumi.WifiActivity;

public class EcouteurBoutonAnnuler implements View.OnClickListener {

    WifiActivity activity;

    public EcouteurBoutonAnnuler(WifiActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {

        activity.manager.removeGroup(activity.channel, null);
        activity.finish();
    }
}
