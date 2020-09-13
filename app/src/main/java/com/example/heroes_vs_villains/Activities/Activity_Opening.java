package com.example.heroes_vs_villains.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.heroes_vs_villains.MYSP;
import com.example.heroes_vs_villains.R;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

public class Activity_Opening extends AppCompatActivity {

    private Button open_BTN_start;
    private Button open_BTN_top10;
    private ImageView open_IMG_background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__opening);
        findViews();
        addPIcWithGlide();
        MYSP.initHelper(this, "MYSP");
        goTopTen();
        startGame();

    }




    private void goTopTen() {
    open_BTN_top10.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            openTop10();
        }
    });
    }

    private void openTop10() {
        Intent myIntent = new Intent(Activity_Opening.this,Activity_Top10.class);
        Activity_Opening.this.startActivity(myIntent);
    }

    private void addPIcWithGlide() {
        Glide
                .with(Activity_Opening.this)
                .load(R.drawable.back)
                .centerCrop()
                .into(open_IMG_background);
    }

    private void startGame() {
    open_BTN_start.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            openNewActivity();
        }
    });
    }

    private void openNewActivity() {
        Intent myIntent = new Intent(Activity_Opening.this,MainActivity.class);
        Activity_Opening.this.startActivity(myIntent);
        finish();
    }

    private void findViews() {
        open_BTN_start=findViewById(R.id.open_BTN_start);
        open_BTN_top10=findViewById(R.id.open_BTN_top10);
        open_IMG_background=findViewById(R.id.open_IMG_background);

    }
}