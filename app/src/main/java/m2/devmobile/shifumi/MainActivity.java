package m2.devmobile.shifumi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;

import m2.devmobile.shifumi.ecouteurs.bouton.EcouteurBoutonCreer;
import m2.devmobile.shifumi.ecouteurs.bouton.EcouteurBoutonQuitter;
import m2.devmobile.shifumi.ecouteurs.bouton.EcouteurBoutonRejoindre;

public class MainActivity extends AppCompatActivity {

    Button btnCreer, btnRejoindre, btnQuitter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // On a besoin de la localisation fine à partir d'Oreo pour le P2P
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }

        btnCreer = findViewById(R.id.btnCreer);
        btnCreer.setOnClickListener(new EcouteurBoutonCreer(this));

        btnRejoindre = findViewById(R.id.btnRejoindre);
        btnRejoindre.setOnClickListener(new EcouteurBoutonRejoindre(this));

        btnQuitter = findViewById(R.id.btnQuitter);
        btnQuitter.setOnClickListener(new EcouteurBoutonQuitter(this));
    }

}
