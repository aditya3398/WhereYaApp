package wya.whereyaat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

public class WhereAmIAt extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback,GoogleApiClient.OnConnectionFailedListener,View.OnTouchListener {

    private int protectionGame = 0;
    private Location location;
    private GoogleApiClient myGoogleApiClient = null;
    private Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_where_am_iat);
        b = (Button)findViewById(R.id.showLocationButton);
        b.setOnTouchListener(this);
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
            b.setText("Latitude: "+ location.getLatitude());
        }
        return true;
    }
    private Location getLocationOfUser(){
        return location;
    }
}
