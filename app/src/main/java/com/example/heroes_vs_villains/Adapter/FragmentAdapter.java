package com.example.heroes_vs_villains.Adapter;

import android.content.Context;
import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.heroes_vs_villains.Fragments.Fragment_List;
import com.example.heroes_vs_villains.Fragments.Fragment_Map;

public class FragmentAdapter extends FragmentPagerAdapter {
    Context context;

    public FragmentAdapter(@NonNull FragmentManager fm, int behavior, Context context) {
        super(fm, behavior);
        this.context = context;
    }
    public FragmentAdapter(@NonNull FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position==0)
            return Fragment_Map.getINSTANCE();
        else if(position==1)
            return Fragment_List.getINSTANCE();
        else
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position)
        {
            case 0 :
                return "Fragment_Map";
            case 1:
                return "Fragment_List";
        }
            return "";
    }
}
