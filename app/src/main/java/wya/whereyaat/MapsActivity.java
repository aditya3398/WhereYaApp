package wya.whereyaat;

import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ArrayList<LatLng> listOfLocations = new ArrayList<LatLng>();
    String passedValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        passedValue= getIntent().getStringExtra("EXTRA_TEXT");
        LatLng l = new LatLng(Double.parseDouble(passedValue.substring(0,passedValue.indexOf(" "))),
                Double.parseDouble(passedValue.substring(passedValue.indexOf(" "),passedValue.length())));
        setContentView(R.layout.activity_maps);
        for (double x = 0.0; x<10.5; x++){
            listOfLocations.add(new LatLng(40.0,x+10.0));

        }

        listOfLocations.add(l);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        //dummy variables

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        for (LatLng latAndLng : listOfLocations) {
            mMap.addMarker(new MarkerOptions().position(latAndLng).title("Homies here!"));
        }

    }
}
