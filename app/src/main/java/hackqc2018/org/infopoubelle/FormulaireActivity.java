package hackqc2018.org.infopoubelle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class FormulaireActivity extends AppCompatActivity {

    private Spinner spinner;
    private static final String[]paths = {"Déchet","Recyclage","Compost","Autre"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulaire);

        spinner = (Spinner)findViewById(R.id.ddlBac);
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(FormulaireActivity.this,
            R.layout.support_simple_spinner_dropdown_item, paths);

        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

}
