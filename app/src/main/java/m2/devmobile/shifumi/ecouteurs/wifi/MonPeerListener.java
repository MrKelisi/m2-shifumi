package m2.devmobile.shifumi.ecouteurs.wifi;

import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager;

import java.util.ArrayList;
import java.util.List;

import m2.devmobile.shifumi.WifiClientActivity;

public class MonPeerListener implements WifiP2pManager.PeerListListener {

    WifiClientActivity activity;

    public MonPeerListener(WifiClientActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onPeersAvailable(WifiP2pDeviceList peers) {

        activity.devicesList = new ArrayList<>(peers.getDeviceList());
        activity.devicesNames = filtreNoms(activity.devicesList);

        activity.updateDevicesList();
    }


    /** Sert à récupérer la liste des noms des appareils à proximité  */
    private static String[] filtreNoms(List<WifiP2pDevice> listeAppareilsAProximite) {
        int i = -1;
        String[] t = new String[listeAppareilsAProximite.size()];

        for (WifiP2pDevice d : listeAppareilsAProximite)
            t[++i] = d.deviceName;

        return t;
    }
}
