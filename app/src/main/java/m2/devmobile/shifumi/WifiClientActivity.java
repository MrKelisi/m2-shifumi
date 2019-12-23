package m2.devmobile.shifumi;

import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import m2.devmobile.shifumi.ecouteurs.bouton.EcouteurBoutonAnnuler;
import m2.devmobile.shifumi.ecouteurs.liste.EcouteurListeAppareils;
import m2.devmobile.shifumi.ecouteurs.wifi.EcouteurConnectionChanged;
import m2.devmobile.shifumi.ecouteurs.wifi.EcouteurInfoConnection;
import m2.devmobile.shifumi.ecouteurs.wifi.EcouteurPeersChanged;
import m2.devmobile.shifumi.ecouteurs.wifi.MonPeerListener;

public class WifiClientActivity extends WifiActivity {

    public MonPeerListener monPeerListener;

    public List<WifiP2pDevice> devicesList;
    public String[] devicesNames = new String[]{};

    Button btnAnnuler;
    public ListView devicesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        this.ecouteurPeersChanged = new EcouteurPeersChanged(this);
        this.monPeerListener = new MonPeerListener(this);
        this.ecouteurConnectionChanged = new EcouteurConnectionChanged(this);
        this.ecouteurInfoConnection = new EcouteurInfoConnection(this);

        btnAnnuler = findViewById(R.id.btnAnnuler);
        btnAnnuler.setOnClickListener(new EcouteurBoutonAnnuler(this));

        devicesListView = findViewById(R.id.devices);
        devicesListView.setOnItemClickListener(new EcouteurListeAppareils(this));
    }

    @Override
    protected void onResume() {
        super.onResume();

        IntentFilter filtre = new IntentFilter();
        filtre.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        registerReceiver(this.ecouteurPeersChanged, filtre);

        filtre = new IntentFilter();
        filtre.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        registerReceiver(this.ecouteurConnectionChanged, filtre);

        this.adresseServeur = null;
        this.manager.discoverPeers(this.channel, null);  // Lance la recherche des appareils wifi directs à proximité
    }

    @Override
    protected void onPause() {
        super.onPause();

        unregisterReceiver(this.ecouteurPeersChanged);
        unregisterReceiver(this.ecouteurConnectionChanged);
    }

    public void updateDevicesList() {
        devicesListView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, devicesNames));
    }

}
