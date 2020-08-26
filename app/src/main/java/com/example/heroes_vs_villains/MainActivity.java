package com.example.heroes_vs_villains;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;


public class MainActivity extends AppCompatActivity {
    private Button main_BTN_AttackHero1;
    private Button main_BTN_AttackHero2;
    private Button main_BTN_AttackHero3;
    private Button main_BTN_AttackVillains1;
    private Button main_BTN_AttackVillains2;
    private Button main_BTN_AttackVillains3;
    private ImageView main_IMG_Hero;
    private ImageView main_IMG_Villains;
    private ImageView main_IMG_background;
    private ProgressBar main_PGB_Hero;
    private ProgressBar main_PGB_Villains;
    private int playerTurn=0;
    private boolean gameOver = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findView();

        addPIcWithGlide();

        disableButton(main_PGB_Villains); // player 1 play first

        startFight();

 }

 private View.OnClickListener attackClick = new View.OnClickListener() {
     @Override
     public void onClick(View view) {
         if(main_PGB_Hero.getProgress()==0 || main_PGB_Villains.getProgress() ==0)
         {
             if(view.getTag().toString().equals("human"))
             {
                 Toast.makeText(getApplicationContext(),
                         "Suske Is Winner!!!",
                         Toast.LENGTH_LONG).show();
             }
             else {
                 Toast.makeText(getApplicationContext(),
                         "Naruto Is Winner!!!",
                         Toast.LENGTH_LONG).show();
             }
             gameOver = true;
         }

         if(gameOver==false)
         {
             /*  -----------------player 1 turn ----------------------*/
             if(playerTurn%2==0)
                 bottomClickedH(view);
             /*  -----------------player 2 turn ----------------------*/
             else  
                 bottomClickedV(view);
         }
         else
         {
             disableButton(main_PGB_Villains);
             disableButton(main_PGB_Hero);
         }
     }
 };



    private void bottomClickedH(View view) {

        if(view.getTag().toString().equals("attackH1"))
        {
            updateProgressBar(main_PGB_Villains,10);
        }
        else if(view.getTag().toString().equals("attackH2"))
        {
            updateProgressBar(main_PGB_Villains,20);
        }
        else if(view.getTag().toString().equals("attackH3"))
        {
            updateProgressBar(main_PGB_Villains,30);
        }
        playerTurn++;
        switchButton(main_PGB_Hero);
    }

    private void bottomClickedV(View view) {


         if(view.getTag().toString().equals("attackV1"))
        {
            updateProgressBar(main_PGB_Hero,10);
        }
        else if(view.getTag().toString().equals("attackV2"))
        {
            updateProgressBar(main_PGB_Hero,20);
        }
        else if(view.getTag().toString().equals("attackV3"))
        {
            updateProgressBar(main_PGB_Hero,30);
        }
        playerTurn--;
        switchButton(main_PGB_Villains);


    }

    @Override
    protected void onStart() {
        Log.d("johny", "onStart");
        super.onStart();

    }

    @Override
    protected void onStop() {
        Log.d("johny", "onStop");
        super.onStop();
    }
    private void switchButton(ProgressBar pgb)
    {
        if(pgb.getTag().toString().equals("human"))
        {
            main_BTN_AttackHero1.setEnabled(false);
            main_BTN_AttackHero2.setEnabled(false);
            main_BTN_AttackHero3.setEnabled(false);

            main_BTN_AttackVillains1.setEnabled(true);
            main_BTN_AttackVillains2.setEnabled(true);
            main_BTN_AttackVillains3.setEnabled(true);

        }
        else
        {
            main_BTN_AttackVillains1.setEnabled(false);
            main_BTN_AttackVillains2.setEnabled(false);
            main_BTN_AttackVillains3.setEnabled(false);

            main_BTN_AttackHero1.setEnabled(true);
            main_BTN_AttackHero2.setEnabled(true);
            main_BTN_AttackHero3.setEnabled(true);
        }
    }


    private void disableButton(ProgressBar pgb) {
        if(pgb.getTag().toString().equals("human"))
        {
            main_BTN_AttackHero1.setEnabled(false);
            main_BTN_AttackHero2.setEnabled(false);
            main_BTN_AttackHero3.setEnabled(false);
        }
        else {
            main_BTN_AttackVillains1.setEnabled(false);
            main_BTN_AttackVillains2.setEnabled(false);
            main_BTN_AttackVillains3.setEnabled(false);
        }
    }

    private void addPIcWithGlide() {
        Glide
                .with(MainActivity.this)
                .load(R.drawable.naruto)
                .centerCrop()
                .into(main_IMG_Hero);

        Glide
                .with(MainActivity.this)
                .load(R.drawable.img_suske)
                .centerCrop()
                .into(main_IMG_Villains);

        Glide
                .with(MainActivity.this)
                .load(R.drawable.back)
                .centerCrop()
                .into(main_IMG_background);

    }




    void updateProgressBar(ProgressBar seek, int num)
    {
        if(seek.getProgress() > 0)
        {
            if(seek.getProgress() <= 0.5*seek.getMax())
            {
                seek.getProgressDrawable().setColorFilter(ContextCompat.getColor(this,R.color.almostDying), PorterDuff.Mode.MULTIPLY);
                seek.setProgress(seek.getProgress()-num);
            }

            seek.setProgress(seek.getProgress()-num);

        }
        
    }
    void startFight()
    {
        main_BTN_AttackHero1.setOnClickListener(attackClick);
        main_BTN_AttackHero2.setOnClickListener(attackClick);
        main_BTN_AttackHero3.setOnClickListener(attackClick);
        main_BTN_AttackVillains1.setOnClickListener(attackClick);
        main_BTN_AttackVillains2.setOnClickListener(attackClick);
        main_BTN_AttackVillains3.setOnClickListener(attackClick);
    }


    void findView()
    {
        main_BTN_AttackHero1=findViewById(R.id.main_BTN_AttackHero1);
        main_BTN_AttackHero2=findViewById(R.id.main_BTN_AttackHero2);
        main_BTN_AttackHero3=findViewById(R.id.main_BTN_AttackHero3);
        main_BTN_AttackVillains1=findViewById(R.id.main_BTN_AttackVillains1);
        main_BTN_AttackVillains2=findViewById(R.id.main_BTN_AttackVillains2);
        main_BTN_AttackVillains3=findViewById(R.id.main_BTN_AttackVillains3);
        main_IMG_Hero=findViewById(R.id.main_IMG_Hero);
        main_IMG_Villains=findViewById(R.id.main_IMG_Villains);
        main_PGB_Hero=findViewById(R.id.main_PGB_Hero);
        main_PGB_Villains= findViewById(R.id.main_PGB_Villains);
        main_IMG_background=findViewById(R.id.main_IMG_background);
    }



}