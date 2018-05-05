package hackqc2018.org.infopoubelle;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class CollecteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collecte_activity);
        Spinner dropdown = findViewById(R.id.droplist);
        String[] items = new String[]{"Laval", "Sherbrooke"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);

        dropdown.setAdapter(adapter);

        init();

    }

    public void init() {
        TableLayout stk = (TableLayout) findViewById(R.id.tabInfo);
        TableRow tbrow0 = new TableRow(this);
        TextView tv0 = new TextView(this);
        tv0.setText(" Sl.No ");
        tv0.setTextColor(Color.BLACK);
        tbrow0.addView(tv0);
        TextView tv1 = new TextView(this);
        tv1.setText(" Product ");
        tv1.setTextColor(Color.BLACK);
        tbrow0.addView(tv1);
        TextView tv2 = new TextView(this);
        tv2.setText(" Unit Price ");
        tv2.setTextColor(Color.BLACK);
        tbrow0.addView(tv2);
        TextView tv3 = new TextView(this);
        tv3.setText(" Stock Remaining ");
        tv3.setTextColor(Color.BLACK);
        tbrow0.addView(tv3);
        stk.addView(tbrow0);
        for (int i = 0; i < 25; i++) {
            TableRow tbrow = new TableRow(this);
            TextView t1v = new TextView(this);
            t1v.setText("" + i);
            t1v.setTextColor(Color.BLACK);
            t1v.setGravity(Gravity.CENTER);
            tbrow.addView(t1v);
            TextView t2v = new TextView(this);
            t2v.setText("Product " + i);
            t2v.setTextColor(Color.BLACK);
            t2v.setGravity(Gravity.CENTER);
            tbrow.addView(t2v);
            TextView t3v = new TextView(this);
            t3v.setText("Rs." + i);
            t3v.setTextColor(Color.BLACK);
            t3v.setGravity(Gravity.CENTER);
            tbrow.addView(t3v);
            TextView t4v = new TextView(this);
            t4v.setText("" + i * 15 / 32 * 10);
            t4v.setTextColor(Color.BLACK);
            t4v.setGravity(Gravity.CENTER);
            tbrow.addView(t4v);
            stk.addView(tbrow);
        }

    }
}
