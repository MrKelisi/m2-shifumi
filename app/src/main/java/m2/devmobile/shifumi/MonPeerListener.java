package m2.devmobile.shifumi;

import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MonPeerListener implements WifiP2pManager.PeerListListener {

    ClientActivity activity;

    public MonPeerListener(ClientActivity activity) {
        this.activity = activity;
    }

    /** Sert à récupérer la liste des noms des appareils à proximité  */
    private static String[] filtreNoms(List<WifiP2pDevice> listeAppareilsAProximite) {
        int i = -1;
        String[] t = new String[listeAppareilsAProximite.size()];

        for (WifiP2pDevice d : listeAppareilsAProximite)
            t[++i] = d.deviceName;

        return t;
    }


    @Override
    public void onPeersAvailable(WifiP2pDeviceList peers) {

        List devicesList = new ArrayList();
        devicesList.addAll(peers.getDeviceList());

        this.activity.devicesList = devicesList;
        this.activity.devicesNames = filtreNoms(devicesList);

        this.activity.updateDevicesList();
    }
}
