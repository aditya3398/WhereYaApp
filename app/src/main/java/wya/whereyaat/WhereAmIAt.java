package wya.whereyaat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class WhereAmIAt extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback,GoogleApiClient.OnConnectionFailedListener,View.OnTouchListener {

    private int protectionGame = 0;
    private Location location;
    private GoogleApiClient myGoogleApiClient = null;
    private Button b;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_where_am_iat);

        //Check whether the user is signed in.
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            Intent intent = new Intent(this, OnboardingActivity.class);
            startActivity(intent);
        }

        b = (Button)findViewById(R.id.showLocationButton);
        b.setOnTouchListener(this);
        textView = (TextView)findViewById(R.id.revealLocation);
        if(myGoogleApiClient == null){
            myGoogleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this,this).addApi(LocationServices.API).build();
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},protectionGame);
        }
        else{
            location = LocationServices.FusedLocationApi.getLastLocation(myGoogleApiClient);
        }

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        System.out.print(connectionResult.describeContents());
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            try {
                location = LocationServices.FusedLocationApi.getLastLocation(myGoogleApiClient);
            }
            catch (SecurityException e){
                e.printStackTrace();
            }
            textView.setText("Latitude: "+ location.getLatitude()+"Longitude: " + location.getLongitude());
            Intent intent = new Intent(this, MapsActivity.class);
            String latandlong=location.getLatitude()+" " + location.getLongitude();
            intent.putExtra("EXTRA_TEXT", latandlong);
            startActivity(intent);
        }
        return true;
    }

    private Location getLocationOfUser(){
        if(location != null){
            return location;
        }
        else{
            return null;
        }
    }
}
