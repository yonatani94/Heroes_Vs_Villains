package com.example.heroes_vs_villains;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.PorterDuff;
import android.os.Bundle;
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
    private  boolean gameover= true;
    private  boolean firstPlayer= true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        addPIcWithGlide();
        Fighter hero = new Fighter(main_PGB_Hero,"hero",true,0);
        Fighter devil = new Fighter(main_PGB_Villains,"devil",false,0);
        Log.d("johny", "onCreate: "+ main_PGB_Villains.getProgress());
        Log.d("johny", "onCreate: "+ main_PGB_Hero.getProgress());

            startFight(hero,devil);

        Log.d("johny", "Finished!!! ");


            Log.d("pttt", "turn is:  " + playerTurn);


 }

 private View.OnClickListener attackClick = new View.OnClickListener() {
     @Override
     public void onClick(View view) {

         if(winnerIs(main_PGB_Hero,main_PGB_Villains)==1)
         {
             disableButton(main_PGB_Hero);
             disableButton(main_PGB_Villains);
         }

         /*  -----------------player 1 turn ----------------------*/
         if(playerTurn%2==0)
         {
             bottomClickedH(view);

         }
         /*  -----------------player 2 turn ----------------------*/

         else  {
             bottomClickedV(view);
         }
     }
 };
    private void bottomClickedH(View view) {

        if(view.getTag().toString().equals("attackH1"))
        {
            changeProgressBar(main_PGB_Villains,10);
            Toast.makeText(getApplicationContext(),"Sasuke  damage 10 Hp",Toast.LENGTH_SHORT).show();
        }
        else if(view.getTag().toString().equals("attackH2"))
        {
            changeProgressBar(main_PGB_Villains,20);
            Toast.makeText(getApplicationContext(),"Sasuke damage 20 Hp",Toast.LENGTH_SHORT).show();

        }
        else if(view.getTag().toString().equals("attackH3"))
        {
            changeProgressBar(main_PGB_Villains,30);
            Toast.makeText(getApplicationContext(),"Sasuke damage 30 Hp",Toast.LENGTH_SHORT).show();

        }

        Log.d("johny", "bottomClickedH: " + playerTurn);
        playerTurn++;
        switchButton(main_PGB_Hero);
       // disableButton(main_PGB_Hero);
       // enableButton(main_PGB_Villains);

    }

    private void bottomClickedV(View view) {


         if(view.getTag().toString().equals("attackV1"))
        {
            changeProgressBar(main_PGB_Hero,10);
            Toast.makeText(getApplicationContext(),"Naruto damage 10 Hp",Toast.LENGTH_SHORT).show();


        }
        else if(view.getTag().toString().equals("attackV2"))
        {
            changeProgressBar(main_PGB_Hero,20);
            Toast.makeText(getApplicationContext(),"Naruto damage 20 Hp",Toast.LENGTH_SHORT).show();

        }
        else if(view.getTag().toString().equals("attackV3"))
        {
            changeProgressBar(main_PGB_Hero,30);
            Toast.makeText(getApplicationContext(),"Naruto damage 30 Hp",Toast.LENGTH_SHORT).show();

        }
        Log.d("johny", "bottomClickedV: " + playerTurn);
        playerTurn--;
        switchButton(main_PGB_Villains);
        //disableButton(main_PGB_Villains);
        //enableButton(main_PGB_Hero);

    }


    @Override
    protected void onStart() {
        Log.d("jttt", "onStart");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.d("jttt", "onStop");
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
    private void enableButton(ProgressBar pgb) {
        if(pgb.getTag().toString().equals("human"))
        {
            main_BTN_AttackHero1.setEnabled(true);
            main_BTN_AttackHero2.setEnabled(true);
            main_BTN_AttackHero3.setEnabled(true);

        }
        else {
            main_BTN_AttackVillains1.setEnabled(true);
            main_BTN_AttackVillains2.setEnabled(true);
            main_BTN_AttackVillains3.setEnabled(true);

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
                .load(R.drawable.suske)
                .centerCrop()
                .into(main_IMG_Villains);

        Glide
                .with(MainActivity.this)
                .load(R.drawable.back)
                .centerCrop()
                .into(main_IMG_background);

    }

    private int declareWinner(Fighter hero,Fighter devil) {

        if(hero.getLife().getProgress()<=0)
        {
            Toast.makeText(getApplicationContext(),
                    "Hero is winner",
                    Toast.LENGTH_LONG).show();
            Log.d("johny","winner is hero");
            return 1;
        }
        else if(devil.getLife().getProgress()<=0)
        {
           Toast.makeText(getApplicationContext(),"Villains is winner",Toast.LENGTH_SHORT).show();
            Log.d("johny","winner is Villains");

              return 1;

        }
        else return 0;


    }

    int winnerIs(ProgressBar pgbHero,ProgressBar pgbDevil)
    {
        Log.d("johny", "winnerIs: pgH =  " + pgbHero.getProgress());
        Log.d("johny", "winnerIs: pgD =  " + pgbDevil.getProgress());
        if(pgbHero.getProgress()==0)
        {
            Toast.makeText(getApplicationContext(),
                    "Hero is winner!",
                    Toast.LENGTH_SHORT).show();
            Log.d("johny","winner is hero");
            disableButton(pgbHero);
            disableButton(pgbDevil);
            return 1;
        }
        else if(pgbDevil.getProgress()==0)
        {
            Toast.makeText(getApplicationContext(),"Villains is winner!",Toast.LENGTH_SHORT).show();
            Log.d("johny","winner is Villains");
            disableButton(pgbHero);
            disableButton(pgbDevil);
            return 1;

        }
        else return 0;
    }
    void changeProgressBar(ProgressBar seek, int num)
    {

            if(seek.getProgress() <= 0.5*seek.getMax())
            {
                seek.getProgressDrawable().setColorFilter(ContextCompat.getColor(this,R.color.almostDying), PorterDuff.Mode.MULTIPLY);
            }
            else if (seek.getProgress()==0)
            {
                Toast.makeText(getApplicationContext(),
                        "Game Over",
                        Toast.LENGTH_LONG).show();
            }

            seek.setProgress(seek.getProgress()-num);

        Log.d("johny", "changeProgressBar:   " + playerTurn);

    }
    void startFight(Fighter hero,Fighter devil)
    {

        if( hero.isTurn())
        {

            main_BTN_AttackHero1.setOnClickListener(attackClick);
            main_BTN_AttackHero2.setOnClickListener(attackClick);
            main_BTN_AttackHero3.setOnClickListener(attackClick);
            devil.setTurn(true);
            hero.setTurn(false);

        }
       if (devil.isTurn()){

            main_BTN_AttackVillains1.setOnClickListener(attackClick);
            main_BTN_AttackVillains2.setOnClickListener(attackClick);
            main_BTN_AttackVillains3.setOnClickListener(attackClick);
           hero.setTurn(true);
          devil.setTurn(false);

        }
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