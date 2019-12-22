package m2.devmobile.shifumi.ecouteurs;

import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import m2.devmobile.shifumi.R;
import m2.devmobile.shifumi.ServeurActivity;
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

        if(info.groupFormed && !info.isGroupOwner) {
            activity.findViewById(R.id.connexionConfirmed).setVisibility(View.VISIBLE);
        }
        if(info.isGroupOwner && !firstConnection) {
            activity.findViewById(R.id.connexionConfirmed).setVisibility(View.VISIBLE);
        }

    }

}
