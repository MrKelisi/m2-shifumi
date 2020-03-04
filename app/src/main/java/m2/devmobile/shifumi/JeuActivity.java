package m2.devmobile.shifumi;

import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public abstract class JeuActivity extends AppCompatActivity {

    public String adresseServeur;
    public int port = 8945;

    public boolean aRepondu = false;
    public TextView labelActivite, resultat;
    public int monChoix, sonChoix, monScore = 0, sonScore = 0;

    private TextView  monScoreTV, sonScoreTV;
    private ImageView monChoixIV, sonChoixIV;
    private ColorMatrixColorFilter cf;

    ImageButton btnPierre, btnFeuille, btnCiseaux;

    public void activationBoutons(boolean activation) {
        btnPierre .setEnabled(activation);
        btnFeuille.setEnabled(activation);
        btnCiseaux.setEnabled(activation);

        btnPierre .setColorFilter(activation ? null : cf);
        btnFeuille.setColorFilter(activation ? null : cf);
        btnCiseaux.setColorFilter(activation ? null : cf);
    }

    public abstract void initActivity(Bundle savedInstanceState);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu);

        Bundle extras = getIntent().getExtras();
        adresseServeur = extras.getString("ADDR_SERV");

        labelActivite = findViewById(R.id.labelActivite);
        resultat = findViewById(R.id.resultat);

        monScoreTV = findViewById(R.id.monScore);
        sonScoreTV = findViewById(R.id.sonScore);
        monChoixIV = findViewById(R.id.monChoix);
        sonChoixIV = findViewById(R.id.sonChoix);

        btnPierre  = findViewById(R.id.btnPierre);
        btnFeuille = findViewById(R.id.btnFeuille);
        btnCiseaux = findViewById(R.id.btnCiseaux);

        ColorMatrix mx = new ColorMatrix();
        mx.setSaturation(0);
        cf = new ColorMatrixColorFilter(mx);

        initActivity(savedInstanceState);
    }

    public void cliquer(int choix) {
        activationBoutons(false);
        aRepondu = true;
        monChoix = choix;
        resultat.setText("En attente de l'adversaire...");
    }

    public void reveler() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                resultat.setText(calculerResultat());
                monChoixIV.setImageResource(choixToDrawable(monChoix));
                sonChoixIV.setImageResource(choixToDrawable(sonChoix));
                switch (calculerResultat()) {
                    case "Gagné":
                        monScoreTV.setText(String.format(Locale.FRANCE, "Vous : %d", ++monScore));
                        break;
                    case "Perdu":
                        sonScoreTV.setText(String.format(Locale.FRANCE, "%d : Adversaire", ++sonScore));
                        break;
                }
            }
        });
    }

    public void rejouer() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                resultat.setText("");
                monChoixIV.setImageResource(0);
                sonChoixIV.setImageResource(0);
                activationBoutons(true);
            }
        });
    }

    // Calcul le résultat en fonction des choix (C'EST DES MATHS !)
    private String calculerResultat() {
        switch(monChoix - sonChoix) {
            case  1:
            case -2:
                return "Gagné";
            case -1:
            case  2:
                return "Perdu";
        }
        return "Egalité";
    }

    // Retourne l'id du drawable en fonction de l'id du choix
    private int choixToDrawable(int nb) {
        switch (nb) {
            case -1: return R.drawable.rock;
            case  0: return R.drawable.paper;
            case  1: return R.drawable.scissors;
        }
        return 0;
    }

}
