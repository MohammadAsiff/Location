package com.example.sys.location;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LocationListener {

    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AlertDialog.Builder builder=new AlertDialog.Builder( this );
        builder.setCancelable( false );
        builder.setTitle( "Location" );
        builder.setMessage( "To continue,turn on device location,which uses Google's location Service" );
        builder.setPositiveButton( "Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText( getApplicationContext(),"You Clicked Ok",Toast.LENGTH_SHORT ).show();
            }
        } )
                .setNegativeButton( "No,Thanks", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText( getApplicationContext(),"You Not Clicked Ok",Toast.LENGTH_SHORT ).show();
                        finish();
                    }
                } );
        builder.create().show();
        AlertDialog.Builder builder1=new AlertDialog.Builder( this );
        builder1.setCancelable( false );
        builder1.setTitle( "Location" );
        builder1.setMessage( "To continue,turn on Device GPS" );
        builder1.setPositiveButton( "Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));

            }
        } )
                .setNegativeButton( "No,Thanks", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText( getApplicationContext(),"You Not Clicked Ok",Toast.LENGTH_SHORT ).show();
                        finish();
                    }
                } );

        builder1.create().show();
        locationManager = (LocationManager) getSystemService( Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, this);
    }
    @Override
    public void onLocationChanged(Location location) {

        String message = String.format("New Location \n Longitude: %1$s \n Latitude: %2$s", location.getLongitude(), location.getLatitude() );

       double lon = location.getLongitude();
       double lat= location.getLatitude();

        Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
        ExampleDB db=new  ExampleDB(MainActivity.this);
        db.insert( lon,lat );

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
