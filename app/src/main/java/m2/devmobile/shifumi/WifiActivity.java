package m2.devmobile.shifumi;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.net.InetAddress;

import m2.devmobile.shifumi.ecouteurs.wifi.EcouteurConnectionChanged;
import m2.devmobile.shifumi.ecouteurs.wifi.EcouteurInfoConnection;
import m2.devmobile.shifumi.ecouteurs.wifi.EcouteurPeersChanged;

public abstract class WifiActivity extends AppCompatActivity {
    public WifiP2pManager manager;
    public WifiP2pManager.Channel channel;

    public InetAddress adresseServeur = null;

    public EcouteurInfoConnection ecouteurInfoConnection;
    public EcouteurConnectionChanged ecouteurConnectionChanged;
    public EcouteurPeersChanged ecouteurPeersChanged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        manager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
        channel = manager.initialize(this, getMainLooper(), null);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        manager.removeGroup(this.channel, null);
        finish();
    }
}
