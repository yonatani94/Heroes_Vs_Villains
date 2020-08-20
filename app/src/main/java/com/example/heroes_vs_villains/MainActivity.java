package com.example.heroes_vs_villains;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.autofill.AutofillValue;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;

//
public class MainActivity extends AppCompatActivity {
    private Button main_BTN_AttackHero1;
    private Button main_BTN_AttackHero2;
    private Button main_BTN_AttackHero3;
    private Button main_BTN_AttackVillains1;
    private Button main_BTN_AttackVillains2;
    private Button main_BTN_AttackVillains3;
    private ImageView main_IMG_Hero;
    private ImageView main_IMG_Villains;
    private SeekBar main_SKB_Hero;
    private SeekBar main_SKB_Villains;
    private int life = 300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            main_SKB_Hero.setMax(life);
            main_SKB_Villains.setMax(life);

        findViewById();

        heroAttack();
        disableButtonHero();
        VillainsAttack();
        disableButtonVillains();





 }

    private void disableButtonVillains() {
        main_BTN_AttackVillains1.setEnabled(false);
        main_BTN_AttackVillains2.setEnabled(false);
        main_BTN_AttackVillains3.setEnabled(false);
    }

    void disableButtonHero()
    {
        main_BTN_AttackHero1.setEnabled(false);
        main_BTN_AttackHero2.setEnabled(false);
        main_BTN_AttackHero3.setEnabled(false);

    }
    void changeSeekBar(SeekBar seek, int num)
    {
        seek.setProgress(life-num);
    }
    void heroAttack()
    {
        main_BTN_AttackHero1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeSeekBar(main_SKB_Villains,10);
            }
        });
        main_BTN_AttackHero2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeSeekBar(main_SKB_Villains,30);

            }
        });
        main_BTN_AttackHero3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeSeekBar(main_SKB_Villains,50);

            }
        });
    }

    void VillainsAttack()
    {
        main_BTN_AttackVillains1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeSeekBar(main_SKB_Hero,10);

            }
        });
        main_BTN_AttackVillains2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeSeekBar(main_SKB_Hero,30);

            }
        });
        main_BTN_AttackVillains3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeSeekBar(main_SKB_Hero,50);

            }
        });
    }
    void findViewById()
    {
        main_BTN_AttackHero1=findViewById(R.id.main_BTN_AttackHero1);
        main_BTN_AttackHero2=findViewById(R.id.main_BTN_AttackHero2);
        main_BTN_AttackHero3=findViewById(R.id.main_BTN_AttackHero3);
        main_BTN_AttackVillains1=findViewById(R.id.main_BTN_AttackVillains1);
        main_BTN_AttackVillains2=findViewById(R.id.main_BTN_AttackVillains2);
        main_BTN_AttackVillains3=findViewById(R.id.main_BTN_AttackVillains3);
        main_IMG_Hero=findViewById(R.id.main_IMG_Hero);
         main_IMG_Villains=findViewById(R.id.main_IMG_Villains);
         main_SKB_Hero=findViewById(R.id.main_SKB_Hero);
         main_SKB_Villains= findViewById(R.id.main_SKB_Villains);
    }
}