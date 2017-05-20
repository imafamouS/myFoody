package com.fdsa.infamous.myfoody;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fdsa.infamous.myfoody.common.myinterface.ICallBackAsynsTask;
import com.fdsa.infamous.myfoody.config.AppConfig;
import com.fdsa.infamous.myfoody.ui.menu.activity.BaseSlideActivity;
import com.fdsa.infamous.myfoody.util.asynctask.MyDecodeLocationMethod;
import com.fdsa.infamous.myfoody.util.global.GlobalFunction;
import com.fdsa.infamous.myfoody.util.global.GlobalStaticData;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.VisibleRegion;

import static com.fdsa.infamous.myfoody.R.id.map;

public class MapsActivity extends BaseSlideActivity implements OnMapReadyCallback, View.OnClickListener {


    private final float MAP_ZOOM = 15.0f;
    Location myLocation = null;
    Context context;
    private LinearLayout back_button_choose_location;
    private TextView text_view_select;
    private TextView text_view_name_location;
    private ProgressBar progress_bar;
    private LinearLayout linear_layout_my_location;
    private GoogleMap mMap;
    private double selectedLat;
    private double selectedLong;
    private LatLng selectedPosition;
    //Sự kiện khi click lên map
    GoogleMap.OnMapClickListener onMapClickListener = new GoogleMap.OnMapClickListener() {
        @Override
        public void onMapClick(LatLng latLng) {
            MapsActivity.this.markLocation(latLng.latitude, latLng.longitude);
        }
    };
    private AsyncTask asyncTaskDecodeLocation;

    //hàm xử lí sự kiện khi Activity được khởi tạo (Khởi tạo view)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(map);

        mapFragment.getMapAsync(this);
        context = this.getApplicationContext();
        initView();

        this.selectedLat = getIntent().getDoubleExtra("lat", -1.0d);
        this.selectedLong = getIntent().getDoubleExtra("long", -1.0d);

    }

    private void initView() {
        back_button_choose_location = (LinearLayout) findViewById(R.id.back_button_choose_location);
        linear_layout_my_location = (LinearLayout) findViewById(R.id.linear_layout_my_location);

        text_view_select = (TextView) findViewById(R.id.text_view_select);
        text_view_name_location = (TextView) findViewById(R.id.text_view_name_location);
        progress_bar = (ProgressBar) findViewById(R.id.progress_bar);
        back_button_choose_location.setOnClickListener(this);
        linear_layout_my_location.setOnClickListener(this);
        text_view_select.setOnClickListener(this);

    }
    //Hàm khởi tạo fragment GoogleMap
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
        this.mMap.setOnCameraChangeListener(new onCameraChangeListener(mMap));

        if (selectedLat == -1.0d || selectedLong == -1.0d) {
            markMyLocation();
        } else {
            markLocation(selectedLat, selectedLong);
        }
    }
    //Hàm đánh dấu địa chỉ
    private void markLocation(double lat, double lng) {
        this.selectedLat = lat;
        this.selectedLong = lng;
        this.mMap.clear();
        this.selectedPosition = new LatLng(lat, lng);
        this.mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(selectedPosition, MAP_ZOOM), 100, null);
    }
    //Hàm đánh dấu đia chỉ của mình
    private void markMyLocation() {
        myLocation = getMyLocation();

        if (myLocation == null) {
            markLocation(GlobalStaticData.getDefaultMyLocation().latitude, GlobalStaticData.getDefaultMyLocation().longitude);
        } else {
            markLocation(myLocation.getLatitude(), myLocation.getLongitude());
            GlobalStaticData.setMYLOCATION(myLocation);
        }
    }
    //Hàm lấy vị trí hiện tại
    private Location getMyLocation() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        Location location = null;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
        }
        return location;
    }
    //hàm gửi data về các activity
    private void sendData() {
        Intent intent = new Intent();
        intent.putExtra("lat", GlobalFunction.round(selectedLat, 5));
        intent.putExtra("long", GlobalFunction.round(selectedLong, 5));
        setResult(AppConfig.RESULT_CODE_CHOOSE_LOCATION, intent);
        finish();

    }
    //Hàm lấy tọa độ chính xác ngay giữa map
    private void getCenterLocationMap() {
        if (this.mMap != null) {
            VisibleRegion visibleRegion = this.mMap.getProjection().getVisibleRegion();
            LatLng center = this.mMap.getProjection().fromScreenLocation(new Point(this.mMap.getProjection().toScreenLocation(visibleRegion.farRight).x / 2,
                    this.mMap.getProjection().toScreenLocation(visibleRegion.nearLeft).y / 2));
            this.selectedLat = center.latitude;
            this.selectedLong = center.longitude;
            markLocation(this.selectedLat, this.selectedLong);
        }
    }
    //Sự kiện onClick
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_button_choose_location:

                finish();
                break;
            case R.id.text_view_select:
                getCenterLocationMap();
                sendData();
                break;
            case R.id.linear_layout_my_location:
                mMap.clear();
                markMyLocation();
                break;
            default:
                break;
        }
    }
    //Class thực hiện việc khi thay đổi vị trí trên google map (hiện thông tin tại vị trí đó)
    class onCameraChangeListener implements GoogleMap.OnCameraChangeListener {
        GoogleMap googleMap;

        public onCameraChangeListener(GoogleMap googleMap) {
            this.googleMap = googleMap;
        }

        @Override
        public void onCameraChange(CameraPosition cameraPosition) {
            asyncTaskDecodeLocation = new MyDecodeLocationMethod(context, cameraPosition.target.latitude,
                    cameraPosition.target.longitude, new CallBackAsynTask(cameraPosition)).execute();
            text_view_name_location.setText("");
            progress_bar.setVisibility(View.VISIBLE);
        }

        class CallBackAsynTask implements ICallBackAsynsTask<String> {
            CameraPosition cameraPosition;

            public CallBackAsynTask(CameraPosition cameraPosition) {
                this.cameraPosition = cameraPosition;
            }

            @Override
            public void onSuccess(String str) {
                text_view_name_location.setText(str + "");
                progress_bar.setVisibility(View.GONE);
                selectedLat = cameraPosition.target.latitude;
                selectedLong = cameraPosition.target.longitude;
            }

            @Override
            public void onFail(String str) {
                text_view_name_location.setText(getString(R.string.TEXT_NOT_FOUND_ADDRESS) + "");
                progress_bar.setVisibility(View.GONE);
            }
        }
    }
}
