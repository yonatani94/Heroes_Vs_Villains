package com.example.heroes_vs_villains.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.heroes_vs_villains.Adapter.FragmentAdapter;
import com.example.heroes_vs_villains.Callback.CallBack_List;
import com.example.heroes_vs_villains.Callback.CallBack_Location;
import com.example.heroes_vs_villains.Fragments.Fragment_List;
import com.example.heroes_vs_villains.Fragments.Fragment_Map;
import com.example.heroes_vs_villains.MYSP;
import com.example.heroes_vs_villains.R;
import com.example.heroes_vs_villains.Top10;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Activity_Top10 extends AppCompatActivity {
    private ViewPager top10_VPG_view;
    private TabLayout top10_TLB_tab;

    private Fragment_List fragment_list;
    private Fragment_Map fragment_map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__top10);
        top10_VPG_view = findViewById(R.id.top10_VPG_view);
        top10_TLB_tab = findViewById(R.id.top10_TLB_tab);

        //initFragments();
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), this);
        top10_VPG_view.setAdapter(fragmentAdapter);
        top10_TLB_tab.setupWithViewPager(top10_VPG_view);

    }


    private void initFragments() {
        fragment_list = Fragment_List.getINSTANCE();
        fragment_list.setTop10ActivityCallBack(callbackList);
        FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
        //transaction1.replace(R.id.board_LAY_list,fragment_list);
        transaction1.commit();


        fragment_map = Fragment_Map.getINSTANCE();
        fragment_map.setLocationCallBack(callBack_location);
        FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
        //  transaction2.replace(R.id.board_LAY_map,fragment_map);
        transaction2.commit();
    }


    CallBack_List callbackList = new CallBack_List() {
        @Override
        public void listChoosePlayerNumberN(ArrayList<Top10> scoreSelected, int index) {
            showPlayerNInMap(scoreSelected, index);
        }
    };
    CallBack_Location callBack_location = new CallBack_Location() {
        @Override
        public void mapMarkedNewLocation(double lat, double lon) {
            //;
        }
    };

    private void showPlayerNInMap(ArrayList<Top10> scoreSelected, int index) {
        double lat = scoreSelected.get(index).getLat();
        double lon = scoreSelected.get(index).getLon();
        Log.d("johny", "showPlayerNInMap: lati = " + scoreSelected.get(index).getLat() + "longi = " + scoreSelected.get(index).getLon());
        String lblOverMarker = scoreSelected.get(index).getName() + " with " + scoreSelected.get(index).getNumOfMoves() + " moves";
        fragment_map.markNewPoint(lat, lon, lblOverMarker);

    }

}