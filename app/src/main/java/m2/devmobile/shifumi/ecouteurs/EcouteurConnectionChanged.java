package m2.devmobile.shifumi.ecouteurs;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.p2p.WifiP2pManager;

import m2.devmobile.shifumi.ServeurActivity;
import m2.devmobile.shifumi.WifiActivity;

public class EcouteurConnectionChanged extends BroadcastReceiver {

    WifiActivity activity;

    public EcouteurConnectionChanged(WifiActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        // le réseau local a changé
        if (WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action)) {

            NetworkInfo networkInfo = (NetworkInfo) intent.getParcelableExtra(WifiP2pManager.EXTRA_NETWORK_INFO);
            if (networkInfo.isConnected()) {
                activity.manager.requestConnectionInfo(activity.channel, activity.ecouteurInfoConnection);
            }
        }
    }
}
