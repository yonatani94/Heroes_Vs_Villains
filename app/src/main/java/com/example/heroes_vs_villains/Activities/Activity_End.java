package com.example.heroes_vs_villains.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.heroes_vs_villains.Figther;
import com.example.heroes_vs_villains.MYSP;
import com.example.heroes_vs_villains.R;
import com.example.heroes_vs_villains.Top10;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Activity_End extends AppCompatActivity {

    private ImageView end_IMG_pic;
    private TextView end_TXT_winner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__end);
        findViews();
        addPicWithGlide();
        showWinner();

    }


    private void showWinner() {

        if (getIntent().getExtras() != null) {
            String name = getIntent().getStringExtra("winner");
            Log.d("johny", "showWinner: winner is + " + name);
            int countSteps = getIntent().getIntExtra("count", 0);
            Log.d("johny", "showWinner: count is + " + countSteps);

            int time = getIntent().getIntExtra("time", 0);
            Log.d("johny", "showWinner: time is + " + time);

            end_TXT_winner.setText("The Winner is " + name + " \nTime Fight =  " +
                    time + " and Number of moves is " + countSteps);
        }

    }


    private void addPicWithGlide() {
        Glide
                .with(Activity_End.this)
                .load(R.drawable.game_over)
                .centerCrop()
                .into(end_IMG_pic);
    }

    private void findViews() {
        end_IMG_pic = findViewById(R.id.end_IMG_pic);
        end_TXT_winner = findViewById(R.id.end_TXT_winner);
    }
}