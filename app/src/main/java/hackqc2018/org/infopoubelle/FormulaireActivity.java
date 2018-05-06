package hackqc2018.org.infopoubelle;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class FormulaireActivity extends AppCompatActivity {

    private Spinner spinner;
    private static final String[]paths = {"Déchet","Recyclage","Compost","Autre"};
    private Button btn;
    private TextView tv;
    private TextInputLayout til;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulaire);

        btn = (Button)findViewById(R.id.btnEnvoyer);
        tv = (TextView)findViewById(R.id.lblMessage);
        spinner = (Spinner)findViewById(R.id.ddlBac);
        til = findViewById(R.id.txtAdresseFormulaire);
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(FormulaireActivity.this,
            R.layout.support_simple_spinner_dropdown_item, paths);

        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        btnClick();
    }

    public void btnClick() {
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                tv.setText("Le formulaire pour l'adresse " + til.getEditText().getText() + ", a bien été reçu. (" + spinner.getSelectedItem().toString() + ")");
                til.getEditText().setText("");
                EditText multiline = (EditText)findViewById(R.id.editText);
                multiline.setText("");
            }
        });
    }
}
