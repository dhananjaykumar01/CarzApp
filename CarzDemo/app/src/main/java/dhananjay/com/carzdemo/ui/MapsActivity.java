package dhananjay.com.carzdemo.ui;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.IntentSender;
import android.databinding.ObservableField;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import javax.inject.Inject;

import dhananjay.com.carzdemo.BR;
import dhananjay.com.carzdemo.R;
import dhananjay.com.carzdemo.databinding.ActivityMapsBinding;
import dhananjay.com.carzdemo.repo.local.db.db_utils.CarzDemoDatabase;
import dhananjay.com.carzdemo.repo.local.db.model.LocationModel;
import dhananjay.com.carzdemo.ui.base.BaseActivity;
import dhananjay.com.carzdemo.utils.Constants;


public class MapsActivity extends BaseActivity<ActivityMapsBinding, MapsViewModel>
        implements IMapsNavigator, OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private GoogleMap mMap;

    @Inject
    MapsViewModel mapsViewModel;
    @Inject
    Context context;

    ActivityMapsBinding activityMapsBinding;

    //Define a request code to send to Google Play services
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private double currentLatitude;
    private double currentLongitude;

    private Double sLatitude;
    private Double sLongitude;
    private Double dLatitude;
    private Double dLongitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMapsBinding = getViewDataBinding();
        mapsViewModel.setNavigator(this);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10 * 1000)
                .setFastestInterval(1 * 1000);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    protected void onResume() {
        super.onResume();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(this.getClass().getSimpleName(), "onPause()");
        if (mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
            mGoogleApiClient.disconnect();
        }


    }

    @Override
    public MapsViewModel getViewModel() {
        return mapsViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_maps;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


    }

    @Override
    public void onConnected(Bundle bundle) {
        Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        if (location == null) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);

        } else {
            currentLatitude = location.getLatitude();
            currentLongitude = location.getLongitude();
            LatLng latLng = new LatLng(currentLatitude, currentLongitude);
            mMap.addMarker(new MarkerOptions().position(latLng).title("Current Location").draggable(true));

        }
    }


    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        if (connectionResult.hasResolution()) {
            try {
                connectionResult.startResolutionForResult(this, CONNECTION_FAILURE_RESOLUTION_REQUEST);
            } catch (IntentSender.SendIntentException e) {
                e.printStackTrace();
            }
        } else {
            Log.e("Error", "Location services connection failed with code " + connectionResult.getErrorCode());
        }
    }


    @Override
    public void onLocationChanged(Location location) {
        currentLatitude = location.getLatitude();
        currentLongitude = location.getLongitude();
        mMap.setMyLocationEnabled(true);
    }

    @Override
    public void sendLocation(ObservableField<String> sLat, ObservableField<String> sLng, ObservableField<String> dLat, ObservableField<String> dLng) {
        if (!sLat.get().isEmpty() && !sLng.get().isEmpty() && !dLat.get().isEmpty() && !dLng.get().isEmpty()) {
            sLatitude = Double.valueOf(sLat.get());
            sLongitude = Double.valueOf(sLng.get());
            dLatitude = Double.valueOf(dLat.get());
            dLongitude = Double.valueOf(dLng.get());
            float zoomLevel = 15.0f;

            //Source Location Marker
            LatLng sourcelatLng = new LatLng(sLatitude, sLongitude);
            mMap.addMarker(new MarkerOptions().position(sourcelatLng).title("Source Location"));

            //Destination Location Marker

            LatLng destlatLng = new LatLng(dLatitude, dLongitude);
            mMap.addMarker(new MarkerOptions().position(destlatLng).title("destination Location"));


            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sourcelatLng, zoomLevel));
        } else {
            Toast.makeText(context, "Please enter value", Toast.LENGTH_LONG).show();
        }
        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {

            }

            @Override
            public void onMarkerDrag(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                double source_dist = distance(sLatitude, sLongitude, marker.getPosition().latitude,  marker.getPosition().longitude);
                double destination_dist = distance(dLatitude, dLongitude,  marker.getPosition().latitude,  marker.getPosition().longitude);
                if (source_dist < 500 || destination_dist < 500) {
                    Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                    } else {

                        v.vibrate(500);
                    }
                }

            }
        });
    }

    public double distance(double latitude, double longitude, double e, double f) {
        double d2r = Math.PI / 180;

        double dlong = (longitude - f) * d2r;
        double dlat = (latitude - e) * d2r;
        double a = Math.pow(Math.sin(dlat / 2.0), 2) + Math.cos(e * d2r)
                * Math.cos(latitude * d2r) * Math.pow(Math.sin(dlong / 2.0), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = 6367 * c;
        return d;

    }

}

