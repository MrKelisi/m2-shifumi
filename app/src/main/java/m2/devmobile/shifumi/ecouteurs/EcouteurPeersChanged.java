package m2.devmobile.shifumi.ecouteurs;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;
import android.util.Log;

import m2.devmobile.shifumi.ClientActivity;
import m2.devmobile.shifumi.WifiActivity;

public class EcouteurPeersChanged extends BroadcastReceiver {

    ClientActivity activity;

    public EcouteurPeersChanged(ClientActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        // le système a détecté un nouvel appareil connecté en wifi p2p à proximité réponse à discoverPeers() : seulement côté client
        if(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)) {

            activity.manager.requestPeers(activity.channel, activity.monPeerListener);
            Log.e("HELLO", "onReceive EcoutPeersChanged");
        }
    }
}
