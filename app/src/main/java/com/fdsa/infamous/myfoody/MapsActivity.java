package com.fdsa.infamous.myfoody;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fdsa.infamous.myfoody.util.global.GlobalStaticData;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import static com.fdsa.infamous.myfoody.R.id.map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {



    private LinearLayout back_button_choose_location;
    private TextView text_view_select;
    private TextView text_view_name_location;
    private ProgressBar progress_bar;
    private LinearLayout linear_layout_my_location;


    private GoogleMap mMap;
    private double selectedLat;
    private double selectedLong;
    private LatLng selectedPosition;
    private final float MAP_ZOOM = 15.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(map);

        mapFragment.getMapAsync(this);

        this.selectedLat = getIntent().getDoubleExtra("lat", -1.0d);
        this.selectedLong = getIntent().getDoubleExtra("long", -1.0d);

    }

    private void initView() {
        back_button_choose_location=(LinearLayout)findViewById(R.id.back_button_choose_location);
        linear_layout_my_location=(LinearLayout)findViewById(R.id.linear_layout_my_location);

        text_view_select=(TextView)findViewById(R.id.text_view_select);
        text_view_name_location=(TextView)findViewById(R.id.text_view_name_location);
        progress_bar=(ProgressBar)findViewById(R.id.progress_bar);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mMap = googleMap;

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        }
        this.mMap.getUiSettings().setMyLocationButtonEnabled(false);
        this.mMap.getUiSettings().setZoomControlsEnabled(false);
        this.mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        this.mMap.setOnMapClickListener(onMapClickListener);

        if(selectedLat==-1 || selectedLong==-1){
            markLocation(GlobalStaticData.getDefaultMyLocation().latitude,GlobalStaticData.getDefaultMyLocation().longitude);
        }else{
            markLocation(selectedLat,selectedLong);
        }
    }

    private void markLocation(double lat, double lng) {
        this.selectedLat=lat;
        this.selectedLong=lng;
        this.mMap.clear();
        this.selectedPosition = new LatLng(lat, lng);
        this.mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(selectedPosition, MAP_ZOOM), 100, null);
    }

    GoogleMap.OnMapClickListener onMapClickListener = new GoogleMap.OnMapClickListener() {
        @Override
        public void onMapClick(LatLng latLng) {
            MapsActivity.this.markLocation(latLng.latitude, latLng.longitude);
        }
    };
}
