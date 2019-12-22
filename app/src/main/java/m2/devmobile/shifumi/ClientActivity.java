package m2.devmobile.shifumi;

import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.WpsInfo;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import m2.devmobile.shifumi.ecouteurs.EcouteurConnectionChanged;
import m2.devmobile.shifumi.ecouteurs.EcouteurInfoConnection;
import m2.devmobile.shifumi.ecouteurs.EcouteurPeersChanged;

public class ClientActivity extends WifiActivity {

    public MonPeerListener monPeerListener;

    List<WifiP2pDevice> devicesList;
    String[] devicesNames = new String[]{};

    ListView devicesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        this.manager = (WifiP2pManager) this.getSystemService(Context.WIFI_P2P_SERVICE);
        this.channel = this.manager.initialize(this,this.getMainLooper(), null);

        this.ecouteurPeersChanged = new EcouteurPeersChanged(this);
        this.monPeerListener = new MonPeerListener(this);
        this.ecouteurConnectionChanged = new EcouteurConnectionChanged(this);
        this.ecouteurInfoConnection = new EcouteurInfoConnection(this);

        this.devicesListView = findViewById(R.id.devices);
        devicesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ClientActivity.this.devicesListView.setEnabled(false);

                WifiP2pDevice appareil = devicesList.get(position);

                if (appareil != null) {
                    WifiP2pConfig config = new WifiP2pConfig();
                    config.wps.setup = WpsInfo.PBC;
                    config.deviceAddress = appareil.deviceAddress;

                    ClientActivity.this.manager.connect(ClientActivity.this.channel, config, null);
                    Log.e("HELLO", "onItemClick ok");
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        adresseServeur = null;

        IntentFilter filtre = new IntentFilter();
        filtre.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        this.registerReceiver(this.ecouteurPeersChanged, filtre);

        filtre = new IntentFilter();
        filtre.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        this.registerReceiver(this.ecouteurConnectionChanged, filtre);

        this.manager.discoverPeers(this.channel, null); // lance la recherche des appareils wifi directs à proximité
    }

    @Override
    protected void onPause() {
        super.onPause();

        this.unregisterReceiver(this.ecouteurPeersChanged);
        this.unregisterReceiver(this.ecouteurConnectionChanged);
    }

    public void updateDevicesList() {
        devicesListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, devicesNames));
    }
}
