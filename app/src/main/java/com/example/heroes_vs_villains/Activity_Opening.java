package com.example.heroes_vs_villains;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity_Opening extends AppCompatActivity {

    private Button open_BTN_start;
    private Button open_BTN_top10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__opening);
        findViews();
        startGame();

    }

    private void startGame() {
    open_BTN_start.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent =  new Intent("android.intent.action.MAIN");
            startActivity(intent);
        }
    });
    }

    private void findViews() {
        open_BTN_start=findViewById(R.id.open_BTN_start);
        open_BTN_top10=findViewById(R.id.open_BTN_top10);

    }
}