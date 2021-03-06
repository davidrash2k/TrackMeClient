package mobapde.trackme;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SetDestinationActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Button close,setdestination;
    MarkerOptions marker;
    LatLng centerOfMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_destination);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        close = (Button)findViewById(R.id.btnclose);
        setdestination = (Button)findViewById(R.id.btndestination);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        setdestination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                finish();
            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    public Bitmap resizeMapIcons(String iconName, int width, int height){
        Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(),getResources().getIdentifier(iconName, "drawable", getPackageName()));
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, width, height, false);
        return resizedBitmap;
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

        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        1);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
            else {
            mMap.setMyLocationEnabled(true);
        }


        // Add a marker in Sydney and move the camera
        LatLng loc = new LatLng(14.5649, 120.9939);
        marker = new MarkerOptions().position(loc).title("My Destination").icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("logo",200,200)));
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(loc)
                .zoom(16).build();
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.addMarker(marker);
        mMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition position) {

                // Get the center of the Map.
                centerOfMap = mMap.getCameraPosition().target;

                // Update your Marker's position to the center of the Map.

                marker.position(centerOfMap);
                mMap.clear();
                mMap.addMarker(marker).showInfoWindow();
            }
        });

    }




    public class UrlHelper extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            OkHttpClient client = new OkHttpClient();
            String url = MainActivity.URL_PART + "/TrackMeServerV2/ControllerServlet";
            String result = "None";
            String loginDetails[] = new String[2];
            loginDetails = params[0].split(" ");

            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

            //used for sending data to server

                RequestBody requestBody = new FormBody.Builder()
                        .add("servlet", "updateUserLocation")
                        .add("id", sp.getString(User.SP_KEY_ID,null))
                        .add("latitude", String.valueOf(centerOfMap.latitude))
                        .add("longitude",String.valueOf(centerOfMap.longitude)).build();



                Request request = new Request.Builder()
                        .url(url)
                        .post(requestBody)
                        .build();


                try {
                    Response response;
                    response = client.newCall(request).execute();
                    result = response.body().string();
                    System.out.println("result:" + result);
                } catch (IOException e) {
                    e.printStackTrace();
                }




            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

        }
    }

}
