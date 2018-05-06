package hackqc2018.org.infopoubelle;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class AccueilActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private String usage;
    private boolean type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accueil_activity);

        TextView lblWelcome = findViewById(R.id.lblWelcome);

        Intent meSelf = getIntent();
        usage = meSelf.getStringExtra("nom");
        type  = meSelf.getBooleanExtra("type", false);

        lblWelcome.setText("Bonjour " + usage);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_action_menu);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        mDrawerLayout.addDrawerListener(
                new DrawerLayout.DrawerListener() {
                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                        // Respond when the drawer's position changes
                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {
                        // Respond when the drawer is opened
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        // Respond when the drawer is closed
                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {
                        // Respond when the drawer motion state changes

                    }
                }
        );

        NavigationView navigationView = findViewById(R.id.navigation_view);

        if(type == false) {
            Menu nav_menu = navigationView.getMenu();
            nav_menu.findItem(R.id.nav_Formulaire).setVisible(false);
        }

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);

                        switch (menuItem.getItemId())
                        {
                            case R.id.nav_Informations:
                                Intent intentInformations = new Intent(AccueilActivity.this, CollecteActivity.class);
                                startActivity(intentInformations);
                                break;
                            case R.id.nav_Recherche:
                                Intent intentRecherche = new Intent(AccueilActivity.this, TexteRechercheActivity.class);
                                startActivity(intentRecherche);
                                break;
                            case R.id.nav_Formulaire:
                                if(type == true) {
                                    Intent intentFormulaire = new Intent(AccueilActivity.this, FormulaireActivity.class);
                                    startActivity(intentFormulaire);
                                }
                                break;
                            case R.id.nav_Deconnexion:
                                usage = "";
                                type = false;
                                Intent intentDeconnexion = new Intent(AccueilActivity.this, MainActivity.class);
                                startActivity(intentDeconnexion);
                                break;
                        }

                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
