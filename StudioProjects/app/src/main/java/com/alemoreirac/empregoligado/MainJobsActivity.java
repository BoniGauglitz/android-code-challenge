package com.alemoreirac.empregoligado;

import android.Manifest;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.orm.SugarContext;

import Util.SingleShotLocationProvider;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainJobsActivity extends AppCompatActivity implements OnMapReadyCallback,LocationListener,ListFragment.OnFragmentInteractionListener,MapFragment.OnFragmentInteractionListener {

    @BindView(R.id.btn_Select_Map)Button btnMap;
    @BindView(R.id.btn_Select_List) Button btnList;

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_jobs);

        getSupportActionBar().setTitle("Emprego Ligado");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xff00C662));

        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_content, new ListFragment(),"lista");
        ft.commit();

        SugarContext.init(this);
        ButterKnife.bind(this);


        Drawable imgList = getResources().getDrawable( R.drawable.list);
        imgList.setBounds( 0, 0, 90, 90 );
        btnList.setCompoundDrawables( imgList, null, null, null );

        Drawable imgMap = getResources().getDrawable( R.drawable.map);
        imgMap.setBounds( 0, 0, 90, 90 );
        btnMap.setCompoundDrawables( imgMap, null, null, null );

        btnList.setEnabled(false);

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 getSupportFragmentManager().popBackStackImmediate();
                btnList.setEnabled(false);
                btnMap.setEnabled(true);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_content, new ListFragment(),"lista");
                transaction.commit();
            }
        });
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  getSupportFragmentManager().popBackStackImmediate();
                btnList.setEnabled(true);
                btnMap.setEnabled(false);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_content, new MapFragment(),"mapa");
                transaction.commit();
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},
                    0);
            Intent intent = new Intent(this,MainJobsActivity.class);
            startActivity(intent);
            return;
        }

        mMap.setMyLocationEnabled(true);
        SingleShotLocationProvider.requestSingleUpdate(this,
                new SingleShotLocationProvider.LocationCallback()
                {
                    @Override
                    public void onNewLocationAvailable(Double lat, Double lng)
                    {
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat,lng), 15.0f ));
                    }
                }
        );

    }


    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


}
