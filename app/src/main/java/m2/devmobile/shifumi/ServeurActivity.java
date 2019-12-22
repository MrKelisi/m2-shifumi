package m2.devmobile.shifumi;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.net.InetAddress;

import m2.devmobile.shifumi.ecouteurs.EcouteurConnectionChanged;
import m2.devmobile.shifumi.ecouteurs.EcouteurInfoConnection;

public class ServeurActivity extends WifiActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serveur);

        this.ecouteurConnectionChanged = new EcouteurConnectionChanged(this);
        this.ecouteurInfoConnection = new EcouteurInfoConnection(this);

        this.manager = (WifiP2pManager) this.getSystemService(Context.WIFI_P2P_SERVICE);
        this.channel = this.manager.initialize(this,this.getMainLooper(), null);

        this.manager.createGroup(this.channel, null);

        findViewById(R.id.btnAnnuler).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: dire au client de revenir à l'accueil (connexion annulée)

                ServeurActivity.this.manager.removeGroup(ServeurActivity.this.channel, null);
                ServeurActivity.this.finish();
            }
        });

        findViewById(R.id.btnCommencer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: dire au client de lancer l'activité JeuActivity

                Intent jeuIntent = new Intent();
                jeuIntent.setAction("m2.devmobile.shifumi.jeuactivity");
                ServeurActivity.this.startActivityForResult(jeuIntent, 333);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        IntentFilter filtre = new IntentFilter();
        filtre.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);

        this.registerReceiver(this.ecouteurConnectionChanged, filtre);  // le receveur est activé
    }

    @Override
    protected void onPause() {
        super.onPause();

        this.unregisterReceiver(this.ecouteurConnectionChanged);  // le receveur est désactivé
    }
}
