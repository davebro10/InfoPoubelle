package hackqc2018.org.infopoubelle;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    final String nomCitoyent = "denis";
    final String mdpCitoyent = "123";
    final String nomVille    = "Mike";
    final String mdpVille    = "123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView erreur = findViewById(R.id.error35);
        setContentView(R.layout.main_activity);
    }

    public void connexion(View view){
        TextInputEditText nomUsage = findViewById(R.id.nomUsage);
        TextInputEditText mdpUsage = findViewById(R.id.mdpUsage);
        TextView erreur = findViewById(R.id.error35);
        if((nomUsage.getText().toString().equals(nomCitoyent) && mdpUsage.getText().toString().equals(mdpCitoyent))){
            Intent intent = new Intent(this, AccueilActivity.class);
            intent.putExtra("nom", nomUsage.getText().toString());
            intent.putExtra("type", false); //n'est pas un employé de la ville
            erreur.setVisibility(view.GONE);
            startActivity(intent);
        }
        else if(nomUsage.getText().toString().equals(nomVille) && mdpUsage.getText().toString().equals(mdpVille) ){
            Intent intent = new Intent(this, AccueilActivity.class);
            intent.putExtra("nom", nomUsage.getText().toString());
            intent.putExtra("type", true); //est un employé de la ville
            erreur.setVisibility(view.GONE);
            startActivity(intent);
        }
        else{

            erreur.setText("Nom d'usagé ou mot de passe invalide");
            erreur.setVisibility(view.VISIBLE);
        }
    }
}
