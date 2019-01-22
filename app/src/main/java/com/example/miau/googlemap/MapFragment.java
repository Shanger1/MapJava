package com.example.miau.googlemap;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>Map activity</h1>
 * Class that contains GoogleMap fragment EditText element and button.
 * It allows to add new places on the map and count distance between them
 *
 * @author Michał Kunda
 * @version alpha
 * @since 16.12.2018
 */
public class MapFragment extends FragmentActivity implements GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMyLocationClickListener,OnMapReadyCallback {

    private GoogleMap mMap;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;
    ImageView imageView;
    List<LatLng> latLngs = new ArrayList<>();
    final List<Address> locations = new ArrayList<>();
    double distance = 0;




    /**
     * This method is executed on the class call.
     * @param savedInstanceState it gets Bundle object
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addLocation();
            }

        });

        checkUserPermissions();
    }

    /**
     * This method checks user permissions to allow user to use map that uses location.
     */
    public void checkUserPermissions(){
        if ( Build.VERSION.SDK_INT >= 23){
                requestPermissions(new String[]{
                                android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.INTERNET},
                        REQUEST_CODE_ASK_PERMISSIONS);
                return ;

        }

    }

    /**
     * This method adds new location. It checks validation if someone doesn't add more than 2 locations.
     * If it passes it draws line between two locations and counts distance between them.
     */
    public void addLocation(){
        EditText edit = (EditText)findViewById(R.id.editText);
        String text = edit.getText().toString();
        try {

            if(locations.size()>=2){
                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(MapFragment.this, android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(MapFragment.this);
                }
                builder.setTitle(R.string.warning)
                        .setMessage(R.string.validate)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            } else if(locations.size()<2){
                Address location = findLocation(text);

                LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());

                latLngs.add(latLng);

                mMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title(location.getLocality())
                        .draggable(true));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,6f));

                locations.add(location);

            }

            drawPrimaryLinePath(locations);

        } catch (Exception e){}

    }

    /**
     * This method uses Google API to find location by address.
     * @param text get address
     */
    public Address findLocation(String text){
        Geocoder coder = new Geocoder(this);
        List<Address> address = new ArrayList<>();
        try{
            address = coder.getFromLocationName(text,5);
        }catch (Exception e){}
        Address location = address.get(0);
        return location;
    }

    /**
     * This method uses Google API to find location by address and count distance between them.
     */
    private void drawPrimaryLinePath( final List<Address> listLocsToDraw )
    {
        if ( mMap == null )
        {
            return;
        }

        if ( listLocsToDraw.size() < 2 )
        {
            return;
        }

        final PolylineOptions options = new PolylineOptions();

        options.color( Color.parseColor( "#CC0000FF" ) );
        options.width( 5 );
        options.visible( true );
        options.clickable(true);

        final TextView textView = (TextView)findViewById(R.id.tooltip);

        mMap.setOnPolylineClickListener(new GoogleMap.OnPolylineClickListener()
        {
            @Override
            public void onPolylineClick(Polyline polyline)
            {
                if(textView.getVisibility() == View.INVISIBLE){
                    textView.setVisibility(View.VISIBLE);
                }else {
                    textView.setVisibility(View.INVISIBLE);
                }
                if(listLocsToDraw.size()>1){
                    Location location = new Location("First");
                    location.setLatitude(listLocsToDraw.get(listLocsToDraw.size()-2).getLatitude());
                    location.setLongitude(listLocsToDraw.get(listLocsToDraw.size()-2).getLongitude());
                    Location location2 = new Location("Second");
                    location2.setLatitude(listLocsToDraw.get(listLocsToDraw.size()-1).getLatitude());
                    location2.setLongitude(listLocsToDraw.get(listLocsToDraw.size()-1).getLongitude());

                    distance = location.distanceTo(location2);
                    distance = Math.round(distance);
                    distance = distance/1000;
                    textView.setText(Double.toString(distance) + " km");
                }
            }
        });

        for ( Address locRecorded : listLocsToDraw ){
            options.add( new LatLng( locRecorded.getLatitude(), locRecorded.getLongitude()));
        }

        mMap.addPolyline( options );

    }

    /**
     * This method inits method to check permissions if they weren't given, add my location button and zoom.
     * @param map get GoogleMap
     */
    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {
            checkUserPermissions();
        }
        else {
            mMap.setMyLocationEnabled(true);
            mMap.setOnMyLocationButtonClickListener(this);
            mMap.setOnMyLocationClickListener(this);
            mMap.getUiSettings().setZoomControlsEnabled(true);
        }

    }

    /**
     * This method shows label on MyLocationClick.
     * @param location get location object
     */
    @Override
    public void onMyLocationClick(@NonNull Location location) {
        Toast.makeText(this, "Current location:\n" + location, Toast.LENGTH_LONG).show();
    }

    /**
     * This method shows label on MyLocationButtonClick.
     */
    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        return false;
    }


}
