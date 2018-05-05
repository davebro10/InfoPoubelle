package hackqc2018.org.infopoubelle;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
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
        //TextInputLayout nomUsage = findViewById(R.id.nomUsage);
        //TextInputLayout mdpUsage = findViewById(R.id.mdpUsage);
            Intent intent = new Intent(this, AccueilActivity.class);
            startActivity(intent);
    }
}
