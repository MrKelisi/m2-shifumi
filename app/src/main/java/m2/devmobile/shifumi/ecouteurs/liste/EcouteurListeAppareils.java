package m2.devmobile.shifumi.ecouteurs.liste;

import android.net.wifi.WpsInfo;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.view.View;
import android.widget.AdapterView;

import m2.devmobile.shifumi.WifiClientActivity;

public class EcouteurListeAppareils implements AdapterView.OnItemClickListener {

    WifiClientActivity activity;

    public EcouteurListeAppareils(WifiClientActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        activity.devicesListView.setEnabled(false);

        WifiP2pDevice device = activity.devicesList.get(position);

        if (device != null) {
            WifiP2pConfig config = new WifiP2pConfig();
            config.wps.setup     = WpsInfo.PBC;
            config.deviceAddress = device.deviceAddress;

            activity.manager.connect(activity.channel, config, null);
        }
    }
}
