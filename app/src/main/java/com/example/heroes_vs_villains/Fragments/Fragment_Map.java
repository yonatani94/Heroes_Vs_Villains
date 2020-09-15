package com.example.heroes_vs_villains.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.heroes_vs_villains.Callback.CallBack_Location;
import com.example.heroes_vs_villains.MYSP;
import com.example.heroes_vs_villains.R;
import com.example.heroes_vs_villains.Top10;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Fragment_Map extends Fragment implements OnMapReadyCallback {
    private static Fragment_Map INSTANCE = null;
    View view;
    GoogleMap map;
    MapView map_MAP_mapview;
    private CallBack_Location callBack_location;
    private ArrayList<Marker> mMarkerArray = new ArrayList<Marker>();
    Marker lastMarkerSelected;

    public Fragment_Map() {

    }

    public static Fragment_Map getINSTANCE() {
        if (INSTANCE == null)
            INSTANCE = new Fragment_Map();
        return INSTANCE;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setLocationCallBack(CallBack_Location callBack_location) {
        this.callBack_location = callBack_location;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_map, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        map_MAP_mapview = view.findViewById(R.id.map_MAP_mapview);
        if (map_MAP_mapview != null) {
            map_MAP_mapview.onCreate(null);
            map_MAP_mapview.onResume();
            map_MAP_mapview.getMapAsync(this);
        }
    }

    private ArrayList<Top10> getListFromSp() {
        Gson gson = new Gson();
        String json = MYSP.getInstance().getString("TOP10", "");
        Type type = new TypeToken<ArrayList<Top10>>() {
        }.getType();
        ArrayList<Top10> arrayList = gson.fromJson(json, type);
        return arrayList;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        map = googleMap;

        ArrayList<Top10> arrayList = getListFromSp();

        if (arrayList != null) {
            int size = arrayList.size();
            if (size > 10) size = 10;
            int i;
            int pos = 1;
            for (i = 0; i < size; i++) {
                LatLng temp = new LatLng(arrayList.get(i).getLat(), arrayList.get(i).getLon());
                Marker lastMarkerSelected = map.addMarker((new MarkerOptions().position(temp).title("Top " + pos)));
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(temp, 10)); // number between 1-20 (20 must zoomed in)
                pos++;

            }
        }


        LatLng ben_gurion = new LatLng(32.006833306, 34.885329792);
        Marker lastMarkerSelected = map.addMarker((new MarkerOptions().position(ben_gurion).title("ben-gurion Airport")));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(ben_gurion, 10)); // number between 1-20 (20 must zoomed in)
    }

    public void markNewPoint(double lat, double lon, String lbl) {
        if (lon == 0.0)
            return; // invalid location
        // got a valid location:
        lastMarkerSelected.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)); // revert color of last marker selected
        LatLng tempPoint = new LatLng(lat, lon);
        lastMarkerSelected = map.addMarker((new MarkerOptions().position(tempPoint).title(lbl))
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))); // change color
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(tempPoint, 10)); // number between 1-20 (20 must zoomed in)
        mMarkerArray.add(lastMarkerSelected); // create arrayList of markers
    }
}
