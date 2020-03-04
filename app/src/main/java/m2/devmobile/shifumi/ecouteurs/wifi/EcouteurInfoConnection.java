package m2.devmobile.shifumi.ecouteurs.wifi;

import android.content.Intent;
import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.widget.TextView;

import m2.devmobile.shifumi.R;
import m2.devmobile.shifumi.WifiActivity;
import m2.devmobile.shifumi.WifiServeurActivity;

public class EcouteurInfoConnection implements WifiP2pManager.ConnectionInfoListener {

    WifiActivity activity;

    public EcouteurInfoConnection(WifiActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onConnectionInfoAvailable(WifiP2pInfo info) {
        boolean firstConnection = (activity.adresseServeur == null);

        // L'adresse IP du serveur (= Group Owner) est enfin connue
        activity.adresseServeur = info.groupOwnerAddress;

        if (info.groupFormed) {

            // Lancement de l'activité JeuClientActivity
            if (!info.isGroupOwner) {

                Intent jeuIntent = new Intent();
                jeuIntent.setAction("m2.devmobile.shifumi.jeuclientactivity");
                jeuIntent.putExtra("ADDR_SERV", activity.adresseServeur.getHostAddress());
                activity.startActivityForResult(jeuIntent, 333);
            }

            // Lancement de l'activité JeuServeurActivity (un client s'est connecté)
            else if (!firstConnection) {

                Intent jeuIntent = new Intent();
                jeuIntent.setAction("m2.devmobile.shifumi.jeuserveuractivity");
                jeuIntent.putExtra("ADDR_SERV", activity.adresseServeur.getHostAddress());
                activity.startActivityForResult(jeuIntent, 444);
            }
        }

        if(activity instanceof WifiServeurActivity) {
            ((TextView) activity.findViewById(R.id.labelAdresse)).setText(activity.adresseServeur.getHostAddress());
        }
    }
}
