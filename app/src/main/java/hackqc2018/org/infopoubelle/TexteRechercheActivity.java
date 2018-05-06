package hackqc2018.org.infopoubelle;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TexteRechercheActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private double longitude;
    private double lattitude;
    private String city;
    private TextView textLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_texte_recherche);

        textLocation = (TextView) findViewById(R.id.textLocalisation);
        locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                lattitude = location.getLatitude();
                longitude = location.getLongitude();
                city = findCity(location);
                textLocation.setText("Ville : " + city);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        };


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        }
    }

    private String findCity(Location location) {

        String city = "";

        try {
            Geocoder geocoder = new Geocoder(this);
            List<Address> addresses = null;
            addresses = geocoder.getFromLocation(lattitude, longitude,1);
            city = addresses.get(0).getLocality();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return city;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

            if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
                }
        }
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }


    protected void find(View view) {

        RequestQueue queue = Volley.newRequestQueue(this);
        EditText edtRecherche = findViewById(R.id.edt_recherche);
        String word = edtRecherche.getText().toString();
        String url = "http://0b56caca.ngrok.io/ResidualMatter/" + word;

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response

                        ImageView imgWaste = findViewById(R.id.imgWaste);
                        ImageView imgCompost = findViewById(R.id.imgCompost);
                        ImageView imgRecycle = findViewById(R.id.imgRecycle);
                        ImageView imgEcocentre = findViewById(R.id.imgEcocenter);

                        TextView textWaste = findViewById(R.id.txt_waste);
                        TextView textCompost = findViewById(R.id.txt_compost);
                        TextView textRecycle = findViewById(R.id.txt_recycle);
                        TextView textEcocentre = findViewById(R.id.txt_ecocenter);
                        TextView textNotFound = findViewById(R.id.txt_notFound);

                        imgWaste.setVisibility(View.INVISIBLE);
                        imgCompost.setVisibility(View.INVISIBLE);
                        imgRecycle.setVisibility(View.INVISIBLE);
                        imgEcocentre.setVisibility(View.INVISIBLE);


                        textWaste.setVisibility(View.INVISIBLE);
                        textCompost.setVisibility(View.INVISIBLE);
                        textRecycle.setVisibility(View.INVISIBLE);
                        textEcocentre.setVisibility(View.INVISIBLE);
                        textNotFound.setVisibility(View.INVISIBLE);

                        if (response.equals("recycle")) {
                            imgRecycle.setVisibility(View.VISIBLE);
                            textRecycle.setVisibility(View.VISIBLE);
                        }
                        else if (response.equals("organic")) {
                            imgCompost.setVisibility(View.VISIBLE);
                            textCompost.setVisibility(View.VISIBLE);
                        }
                        else if (response.equals("ecocenter")) {
                            textEcocentre.setVisibility(View.VISIBLE);
                            imgEcocentre.setVisibility(View.VISIBLE);
                        }
                        else if (response.equals("waste")) {
                            imgWaste.setVisibility(View.VISIBLE);
                            textWaste.setVisibility(View.VISIBLE);
                        }
                        else {
                            textNotFound.setVisibility(View.VISIBLE);
                        }

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", "ddd");
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("city", city);

                return params;
            }
        };
        queue.add(postRequest);

        }

}
