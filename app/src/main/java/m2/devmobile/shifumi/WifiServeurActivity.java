package m2.devmobile.shifumi;

import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.widget.Button;

import m2.devmobile.shifumi.ecouteurs.bouton.EcouteurBoutonAnnuler;
import m2.devmobile.shifumi.ecouteurs.bouton.EcouteurBoutonCommencer;
import m2.devmobile.shifumi.ecouteurs.wifi.EcouteurConnectionChanged;
import m2.devmobile.shifumi.ecouteurs.wifi.EcouteurInfoConnection;

public class WifiServeurActivity extends WifiActivity {

    Button btnAnnuler, btnCommencer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serveur);

        this.manager.createGroup(this.channel, null);

        this.ecouteurConnectionChanged = new EcouteurConnectionChanged(this);
        this.ecouteurInfoConnection = new EcouteurInfoConnection(this);

        btnAnnuler = findViewById(R.id.btnAnnuler);
        btnAnnuler.setOnClickListener(new EcouteurBoutonAnnuler(this));

        // btnCommencer = findViewById(R.id.btnCommencer);
        // btnCommencer.setOnClickListener(new EcouteurBoutonCommencer(this));
    }

    @Override
    protected void onResume() {
        super.onResume();

        IntentFilter filtre = new IntentFilter();
        filtre.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);

        registerReceiver(this.ecouteurConnectionChanged, filtre);
    }

    @Override
    protected void onPause() {
        super.onPause();

        unregisterReceiver(this.ecouteurConnectionChanged);
    }
}
