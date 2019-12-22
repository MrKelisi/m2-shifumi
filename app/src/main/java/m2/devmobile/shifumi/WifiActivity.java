package m2.devmobile.shifumi;

import android.net.wifi.p2p.WifiP2pManager;

import androidx.appcompat.app.AppCompatActivity;

import java.net.InetAddress;

import m2.devmobile.shifumi.ecouteurs.EcouteurConnectionChanged;
import m2.devmobile.shifumi.ecouteurs.EcouteurInfoConnection;
import m2.devmobile.shifumi.ecouteurs.EcouteurPeersChanged;

public abstract class WifiActivity extends AppCompatActivity {
    public WifiP2pManager manager;
    public WifiP2pManager.Channel channel;

    public InetAddress adresseServeur = null;
    public EcouteurInfoConnection ecouteurInfoConnection;

    public EcouteurConnectionChanged ecouteurConnectionChanged;
    public EcouteurPeersChanged ecouteurPeersChanged;
}
