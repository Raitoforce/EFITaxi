package com.efitaxi.technous.efitaxi.Activity.Fragment;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.efitaxi.technous.efitaxi.Activity.MainActivity;
import com.efitaxi.technous.efitaxi.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback, View.OnClickListener, GoogleMap.OnMarkerDragListener{

    private View view;
    private GoogleMap gMap;
    private MapView mapView;
    private ImageButton menuButton;
    private DrawerLayout drawer;
    private LocationManager locationM;
    private Location location;

    public MapFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_map, container, false);
        menuButton = (ImageButton) view.findViewById(R.id.menuButton);
        drawer = (DrawerLayout) container.getRootView().findViewById(R.id.drawer_layout);
        menuButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapView = (MapView) view.findViewById(R.id.mapView);
        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        locationM = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 200);
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 201);
            }
        }
        location = locationM.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
        gMap = googleMap;
        double latitude = 0;
        double longitude = 0;
        if (location != null) {
            // Getting latitude of the current location
            latitude = location.getLatitude();

            // Getting longitude of the current location
            longitude = location.getLongitude();
        }
        LatLng myLocation = new LatLng(latitude, longitude);
        gMap.addMarker(new MarkerOptions().position(myLocation).title("Ubicacion Actual")).setDraggable(true);
        gMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation));
        gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(myLocation.latitude, myLocation.longitude), 17.0f), 1500, null);
        gMap.setMaxZoomPreference(20.0f);
        gMap.setMinZoomPreference(15.0f);
        gMap.setMyLocationEnabled(true);
        gMap.getUiSettings().setMyLocationButtonEnabled(true);
        gMap.getUiSettings().setCompassEnabled(false);
    }


    @Override
    public void onClick(View v) {
        if (drawer.isDrawerOpen(GravityCompat.START))
            drawer.closeDrawer(GravityCompat.START);
        else
            drawer.openDrawer(GravityCompat.START);
    }

    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {
        Toast.makeText(getActivity().getApplicationContext(),"Lat: "+String.valueOf(marker.getPosition().latitude)+" Long:"+String.valueOf(marker.getPosition().longitude),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        Toast.makeText(getActivity().getApplicationContext(),"Lat: "+String.valueOf(marker.getPosition().latitude)+" Long:"+String.valueOf(marker.getPosition().longitude),Toast.LENGTH_LONG).show();
    }
}
