package com.example.heroes_vs_villains;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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