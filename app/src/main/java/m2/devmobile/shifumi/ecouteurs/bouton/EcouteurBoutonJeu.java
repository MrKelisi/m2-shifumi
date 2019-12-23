package m2.devmobile.shifumi.ecouteurs.bouton;

import android.view.View;
import android.widget.Button;

import m2.devmobile.shifumi.JeuActivity;

// TODO: pas encore utilisé pour l'instant
public class EcouteurBoutonJeu implements View.OnClickListener {

    JeuActivity activity;

    public EcouteurBoutonJeu(JeuActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {

        String choix = ((Button) v).getText().toString();

        activity.monChoix.setText(choix);
        activity.activationBoutons(false);

        //activity.interlocuteur.fluxSortant.println("i_chose_scissors");
        //interlocuteur.fluxSortant.println("what_did_you_choose");
    }
}
