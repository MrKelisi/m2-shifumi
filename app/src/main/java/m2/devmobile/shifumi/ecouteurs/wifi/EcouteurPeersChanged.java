package m2.devmobile.shifumi.ecouteurs.wifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;

import m2.devmobile.shifumi.WifiClientActivity;

public class EcouteurPeersChanged extends BroadcastReceiver {

    WifiClientActivity activity;

    public EcouteurPeersChanged(WifiClientActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        // Le système a détecté un nouvel appareil : réponse à discoverPeers()
        if(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(intent.getAction())) {

            activity.manager.requestPeers(activity.channel, activity.monPeerListener);
        }
    }
}
