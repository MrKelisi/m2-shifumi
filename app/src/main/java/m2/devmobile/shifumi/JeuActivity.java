package m2.devmobile.shifumi;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public abstract class JeuActivity extends AppCompatActivity {

    public String adresseServeur;
    public int port = 8945;

    public TextView monChoix, sonChoix, labelActivite;
    Button btnPierre, btnFeuille, btnCiseaux;

    public void activationBoutons(boolean activation) {
        btnPierre.setEnabled(activation);
        btnFeuille.setEnabled(activation);
        btnCiseaux.setEnabled(activation);
    }

    public abstract void initActivity(Bundle savedInstanceState);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu);

        Bundle extras = getIntent().getExtras();
        adresseServeur = extras.getString("ADDR_SERV");

        monChoix = findViewById(R.id.monChoix);
        sonChoix = findViewById(R.id.sonChoix);
        labelActivite = findViewById(R.id.labelActivite);

        btnPierre  = findViewById(R.id.btnPierre);
        btnFeuille = findViewById(R.id.btnFeuille);
        btnCiseaux = findViewById(R.id.btnCiseaux);

        initActivity(savedInstanceState);
    }

}
