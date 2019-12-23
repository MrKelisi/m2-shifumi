package m2.devmobile.shifumi.ecouteurs.wifi;

import android.content.Intent;
import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.widget.TextView;

import m2.devmobile.shifumi.R;
import m2.devmobile.shifumi.WifiActivity;

public class EcouteurInfoConnection implements WifiP2pManager.ConnectionInfoListener {

    WifiActivity activity;

    public EcouteurInfoConnection(WifiActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onConnectionInfoAvailable(WifiP2pInfo info) {
        boolean firstConnection = (activity.adresseServeur == null);
        activity.adresseServeur = info.groupOwnerAddress;  // l'adresse IP du serveur (= Group Owner) est enfin connue

        TextView title = activity.findViewById(R.id.labelAdresse);
        title.setText(activity.adresseServeur.getHostAddress());

        if (info.groupFormed) {

            // Lancement de l'activité JeuClientActivity
            if (!info.isGroupOwner) {

                Intent jeuIntent = new Intent();
                jeuIntent.setAction("m2.devmobile.shifumi.jeuclientactivity");
                jeuIntent.putExtra("ADDR_SERV", activity.adresseServeur.getHostAddress());
                activity.startActivityForResult(jeuIntent, 333);

                //activity.findViewById(R.id.connexionConfirmed).setVisibility(View.VISIBLE);
            }

            // Lancement de l'activité JeuServeurActivity (un client s'est connecté)
            else if (!firstConnection) {

                Intent jeuIntent = new Intent();
                jeuIntent.setAction("m2.devmobile.shifumi.jeuserveuractivity");
                jeuIntent.putExtra("ADDR_SERV", activity.adresseServeur.getHostAddress());
                activity.startActivityForResult(jeuIntent, 444);

                //activity.findViewById(R.id.connexionConfirmed).setVisibility(View.VISIBLE);
            }
        }

    }

}
