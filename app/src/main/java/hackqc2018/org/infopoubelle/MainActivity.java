package hackqc2018.org.infopoubelle;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    final String nomCitoyent = "denis";
    final String mdpCitoyent = "Skynet";
    final String nomVille    = "Mike";
    final String mdpVille    = "Beatrice";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
    }

    public void connexion(View view){
        TextInputEditText nomUsage = findViewById(R.id.nomUsage);
        TextInputEditText mdpUsage = findViewById(R.id.mdpUsage);
        if((nomUsage.getText().toString().equals(nomCitoyent) && mdpUsage.getText().toString().equals(mdpCitoyent))
           || (nomUsage.getText().toString().equals(nomVille) && mdpUsage.getText().toString().equals(mdpVille) )) {
            Intent intent = new Intent(this, AccueilActivity.class);
            startActivity(intent);
        }
    }
}
