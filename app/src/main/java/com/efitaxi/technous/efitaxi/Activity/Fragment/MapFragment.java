package com.efitaxi.technous.efitaxi.Activity.Fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.efitaxi.technous.efitaxi.Activity.MainActivity;
import com.efitaxi.technous.efitaxi.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback,View.OnClickListener{

    private View view;
    private GoogleMap gMap;
    private MapView mapView;
    private ImageButton menuButton;
    private DrawerLayout drawer;
    public MapFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_map, container, false);
        menuButton=(ImageButton)view.findViewById(R.id.menuButton);
        drawer=(DrawerLayout)container.getRootView().findViewById(R.id.drawer_layout);
        menuButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapView=(MapView) view.findViewById(R.id.mapView);
        if (mapView!=null){
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap=googleMap;

        LatLng myLocation = new LatLng(13.0810, 80.2740);
        gMap.addMarker(new MarkerOptions().position(myLocation).title("Marker in Sydney"));
        gMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation));
        gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(myLocation.latitude, myLocation.longitude), 11.0f), 1500, null);
    }

    @Override
    public void onClick(View v) {
        if (drawer.isDrawerOpen(GravityCompat.START))
            drawer.closeDrawer(GravityCompat.START);
        else
            drawer.openDrawer(GravityCompat.START);
    }
}
