package wya.whereyaat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.PlaceLikelihood;
import com.google.android.gms.location.places.PlaceLikelihoodBuffer;
import com.google.android.gms.location.places.PlacePhotoMetadata;
import com.google.android.gms.location.places.PlacePhotoMetadataBuffer;
import com.google.android.gms.location.places.PlacePhotoMetadataResult;
import com.google.android.gms.location.places.Places;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WhereAmIAt extends Activity implements ActivityCompat.OnRequestPermissionsResultCallback,GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks {

    public final String TAG = "WHEREYAAPP";
    private GoogleApiClient myGoogleApiClient = null;
    RecyclerView rv;
    Location location;
    List<PlaceThing> placesthings = new ArrayList<PlaceThing>();
    //Bitmap image = null;
    String currentDateTimeString;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_where_am_iat);

        user = FirebaseAuth.getInstance().getCurrentUser();

        currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());

        if(myGoogleApiClient == null){
            myGoogleApiClient= new GoogleApiClient
                    .Builder(this)
                    .addApi(Places.GEO_DATA_API)
                    .addApi(Places.PLACE_DETECTION_API)
                    .addApi(LocationServices.API)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .build();

            myGoogleApiClient.connect();
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            Log.d(TAG, "WE IN CHECKPERMISSION");
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
        }

        Log.d(TAG, "WE BOUT TO GETCURRENTPLACE");
        PendingResult<PlaceLikelihoodBuffer> result = Places.PlaceDetectionApi
                .getCurrentPlace(myGoogleApiClient, null);
        Log.d(TAG, "WE BOUT TO SET RESULT CALL BACK METHODS");
        result.setResultCallback(new ResultCallback<PlaceLikelihoodBuffer>() {
            @Override
            public void onResult(PlaceLikelihoodBuffer likelyPlaces) {
                for (PlaceLikelihood placeLikelihood : likelyPlaces) {
                    Log.d(TAG, "WE IN ONRESULT");
                    Log.d(TAG, String.format("Place '%s' has likelihood: %g",
                            placeLikelihood.getPlace().getName(),
                            placeLikelihood.getLikelihood()));

                    /*
                    // Get a PlacePhotoMetadataResult containing metadata for the first 10 photos.
                    PlacePhotoMetadataResult result = Places.GeoDataApi
                            .getPlacePhotos(myGoogleApiClient, placeLikelihood.getPlace().getId()).await();
                    // Get a PhotoMetadataBuffer instance containing a list of photos (PhotoMetadata).
                    if (result != null && result.getStatus().isSuccess()) {
                        PlacePhotoMetadataBuffer photoMetadataBuffer = result.getPhotoMetadata();

                        // Get the first photo in the list.
                        PlacePhotoMetadata photo = photoMetadataBuffer.get(0);
                        // Get a full-size bitmap for the photo.
                        image = photo.getPhoto(myGoogleApiClient).await()
                                .getBitmap();
                    }*/

                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions(getParent(),new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                    }
                    location = LocationServices.FusedLocationApi.getLastLocation(myGoogleApiClient);
                    placesthings.add(new PlaceThing(user.getDisplayName(), placeLikelihood.getPlace().getName().toString(), placeLikelihood.getPlace().getId(), placeLikelihood.getPlace().getAddress().toString(), currentDateTimeString, location.getLongitude(), location.getLatitude()));
                }
                likelyPlaces.release();

                setupCards();
            }
        });
    }

    public void setupCards(){
        rv = (RecyclerView) findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        if(placesthings == null || placesthings.isEmpty()){
            //todo just send the coordinates
        }
        else{
            PlaceThingAdapter pa = new PlaceThingAdapter(placesthings, getApplicationContext()); //todo order by likelihood
            rv.setAdapter(pa);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        myGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        myGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d(TAG, "CONNECTIONFAILED");
        Log.d(TAG, " " + connectionResult.describeContents());
    }

    @Override
    public void onConnected(Bundle connectionHint) {
        Log.d(TAG, "CONNECTED");
        if(connectionHint != null){
            Log.d(TAG, connectionHint.toString());
        }
    }

    @Override
    public void onConnectionSuspended(int cause) {
        Log.d(TAG, "CONNECTIONSUSPENDED");
        Log.d(TAG, " " + cause);
    }
    /*
    private class FetchPlaceImage extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... url){

            return "This is junk....";
        }

        protected void onPostExecute(String result) {
            setupCards();
        }
    }*/
}
