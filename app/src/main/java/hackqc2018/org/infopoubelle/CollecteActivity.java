package hackqc2018.org.infopoubelle;

import android.graphics.Color;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

public class CollecteActivity extends AppCompatActivity {

    ArrayList<String> autres = new ArrayList<>();
    ArrayList<String> compost = new ArrayList<>();
    ArrayList<String> dechets = new ArrayList<>();
    ArrayList<String> ecocentre = new ArrayList<>();
    ArrayList<String> recuperation = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collecte_activity);
        Spinner dropdown = findViewById(R.id.droplist);
        String[] items = new String[]{"Laval", "Sherbrooke"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);

        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(new YourItemSelectedListener());
    }
public class YourItemSelectedListener implements AdapterView.OnItemSelectedListener {

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        String selected = parent.getItemAtPosition(pos).toString();
        JSONObject obj;
        if (selected.equals("Laval")) {
            //obj = new JSONObject(loadLaval());
        } else {
            //obj = new JSONObject(loadSherbrooke());
        }
    }

    public void onNothingSelected(AdapterView parent) {
    }

    public String loadLaval()
    {

        String json = null;

        return "";
    }

    public String loadSherbrooke()
    {
        return "";
    }
}



}
