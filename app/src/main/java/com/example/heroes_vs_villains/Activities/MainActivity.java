package com.example.heroes_vs_villains.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PorterDuff;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.heroes_vs_villains.Figther;
import com.example.heroes_vs_villains.MYSP;
import com.example.heroes_vs_villains.R;
import com.example.heroes_vs_villains.Top10;
import com.google.android.gms.location.LocationRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;


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
    private TextView main_TXT_timer;

    private final int MAX = 6;
    private final int MIN = 0;
    private MediaPlayer mPlayer;

    private Figther figther;

    private Figther naruto;
    private Figther suske;
    private int moveH = 0;
    private int moveV = 0;
    private int playerTurn = 0;
    private boolean gameOver = false;

    /*--------------timer----------*/
    private Handler handler = new Handler();
    private Runnable secondlyRun;
    private final int DELAY = 1000;
    private int count = 0;

    /*--------------location-------------*/
    private static final int REQUEST_CODE_LOCATION_PERMISSION = 1;
    private LocationManager lm;
    private Location currenLocation;
    private double lati, longi;
    private final LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            longi = location.getLongitude();
            lati = location.getLatitude();
            Log.d("johny", "onLocationChanged: lati = " + lati + "longi = " + longi);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findView();
        requestPermissionFromUser();
        initFigthers();
        backgroundMusic();
        addPIcWithGlide();
        startPlayer();

        try {
            autoPlay();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


    private void requestPermissionFromUser() {
        if (ContextCompat.checkSelfPermission(
                getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new
                    String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE_LOCATION_PERMISSION);
        } else {
            getCurrentLocation();

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_LOCATION_PERMISSION && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation();
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getCurrentLocation() {

        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        currenLocation = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10, locationListener);


    }


    private void initFigthers() {
        naruto = new Figther("naruto", 0, 0, 0, 0, false, main_PGB_Hero);
        suske = new Figther("suske", 0, 0, 0, 1, false, main_PGB_Villains);
        figther = new Figther();

    }

    private void timeHandler() {
        secondlyRun = new Runnable() {
            public void run() {
                count++;
                main_TXT_timer.setText("" + count);
                if (gameOver) {
                    handler.postDelayed(this, DELAY);
                }
            }
        };
    }


    private void backgroundMusic() {
        mPlayer = MediaPlayer.create(this, R.raw.sound);
        mPlayer.setLooping(true); // Set looping
        mPlayer.setVolume(100, 100);
    }


    private void startPlayer() {
        disableButton(main_PGB_Villains);
        disableButton(main_PGB_Hero);
        int random = randomPlayer(MAX, MIN);
        if (random % 2 == 0) {
            switchButton(main_PGB_Villains);
            Toast.makeText(getApplicationContext(), "num = " + random + " -> Naruto start First", Toast.LENGTH_LONG).show();
        } else {
            switchButton(main_PGB_Hero);
            Toast.makeText(getApplicationContext(), "num = " + random + " -> Suske start First", Toast.LENGTH_LONG).show();
        }
    }

    private void openEndActivity() {
        Intent myIntent = new Intent(MainActivity.this, Activity_End.class);
        String nameWinner = naruto.isWinner() ? "Naruto" : "Suske";
        myIntent.putExtra("winner", nameWinner);
        int countSteps;
        if (naruto.isWinner())
            countSteps = moveH;
        else countSteps = moveV;
        myIntent.putExtra("count", countSteps);
        myIntent.putExtra("time", count);
        MainActivity.this.startActivity(myIntent);
        finish();
    }

    private int randomPlayer(int max, int min) {
        int num;
        num = new Random().nextInt((max - min) + 1) + min;
        return num;
    }

    private void autoPlay() throws InterruptedException {
        gameOver = declareWinner();
        if (gameOver == false) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    int random;
                    if (count == 0) {
                        random = randomPlayer(1, 0);
                        Log.d("johny", "autoPlay: random is " + random);
                        playerTurn = random;
                    }
                    count++;
                    main_TXT_timer.setText("" + count);
                    /*  -----------------player 1 turn ----------------------*/
                    if (playerTurn % 2 == 0) {
                        narutoAttack();

                        try {
                            autoPlay();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    /*  -----------------player 2 turn ----------------------*/
                    else {
                        suskeAttack();
                        try {
                            autoPlay();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }, DELAY);
        } else {

            disableButton(suske.getLife());
            disableButton(naruto.getLife());
            openEndActivity();
        }
    }

    private void suskeAttack() {
        int randomAttack = randomPlayer(2, 0);
        switch (randomAttack) {
            case 0:
                updateProgressBar(naruto.getLife(), 10);
                Toast.makeText(getApplicationContext(), "Naruto damge 10 HP", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                updateProgressBar(naruto.getLife(), 20);
                Toast.makeText(getApplicationContext(), "Naruto damge 20 HP", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                updateProgressBar(naruto.getLife(), 30);
                Toast.makeText(getApplicationContext(), "Naruto damge 30 HP", Toast.LENGTH_SHORT).show();
                break;
        }
        switchButton(suske.getLife());
        playerTurn--;
        moveV++;
    }

    private void narutoAttack() {
        int randomAttack = randomPlayer(2, 0);
        Log.d("johny", "narutoAttack: random " + randomAttack);
        switch (randomAttack) {
            case 0:
                updateProgressBar(suske.getLife(), 10);
                Toast.makeText(getApplicationContext(), "Suske damge 10 HP", Toast.LENGTH_SHORT).show();

                break;
            case 1:
                updateProgressBar(suske.getLife(), 20);
                Toast.makeText(getApplicationContext(), "Suske damge 20 HP", Toast.LENGTH_SHORT).show();

                break;
            case 2:
                updateProgressBar(suske.getLife(), 30);
                Toast.makeText(getApplicationContext(), "Suske damge 30 HP", Toast.LENGTH_SHORT).show();

                break;
        }

        switchButton(naruto.getLife());
        playerTurn++;
        moveH++;

    }

    private boolean declareWinner() {
        if (naruto.getLife().getProgress() == 0) {
            suske.setWinner(true);
            saveHeroInMySp(suske.getName(), moveV);
            return true;
        } else if (suske.getLife().getProgress() == 0) {
            naruto.setWinner(true);
            saveHeroInMySp(naruto.getName(), moveH);
            return true;
        }
        return false;

    }

    public <T> void setList(String key, ArrayList<Top10> list) {
        if (list.size() > 1) {
            Collections.sort(list, new Comparator<Top10>() {
                @Override
                public int compare(Top10 top1, Top10 top2) {
                    return top1.getNumOfMoves() - top2.getNumOfMoves();
                }
            });
        }
        Gson gson = new Gson();
        String json = gson.toJson(list);
        MYSP.getInstance().putString(key, json);
    }

    private void saveHeroInMySp(String name, int move) {
        figther.scores = getArray("TOP10");
        if (figther.getScores() == null) {
            figther.scores = new ArrayList<>();
        }
        figther.scores.add(new Top10(name, lati, longi, count, move));
        setList("TOP10", figther.scores);

    }

    private ArrayList<Top10> getArray(String key) {
        Gson gson = new Gson();
        String json = MYSP.getInstance().getString(key, "");
        Type type = new TypeToken<ArrayList<Top10>>() {
        }.getType();
        figther.scores = gson.fromJson(json, type);
        return figther.scores;
    }


    private View.OnClickListener attackClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            gameOver = Winner(main_PGB_Hero, main_PGB_Villains);
            if (gameOver == false) {
                /*  -----------------player 1 turn ----------------------*/
                if (playerTurn % 2 == 0)
                    bottomClickedH(view);
                    /*  -----------------player 2 turn ----------------------*/
                else
                    bottomClickedV(view);
            } else {
                disableButton(main_PGB_Villains);
                disableButton(main_PGB_Hero);
                openEndActivity();
            }
        }
    };


    private void bottomClickedH(View view) {

        if (view.getTag().toString().equals("attackH1")) {
            updateProgressBar(main_PGB_Villains, 10);
            Toast.makeText(getApplicationContext(), "Suske damge 10 HP", Toast.LENGTH_SHORT).show();
        } else if (view.getTag().toString().equals("attackH2")) {
            updateProgressBar(main_PGB_Villains, 20);
            Toast.makeText(getApplicationContext(), "Suske damge 20 HP", Toast.LENGTH_SHORT).show();

        } else if (view.getTag().toString().equals("attackH3")) {
            updateProgressBar(main_PGB_Villains, 30);
            Toast.makeText(getApplicationContext(), "Suske damge 30 HP", Toast.LENGTH_SHORT).show();

        }
        moveH++;
        playerTurn++;
        switchButton(main_PGB_Hero);
    }

    private void bottomClickedV(View view) {


        if (view.getTag().toString().equals("attackV1")) {
            updateProgressBar(main_PGB_Hero, 10);
            Toast.makeText(getApplicationContext(), "Naruto damge 10 HP", Toast.LENGTH_SHORT).show();

        } else if (view.getTag().toString().equals("attackV2")) {
            updateProgressBar(main_PGB_Hero, 20);
            Toast.makeText(getApplicationContext(), "Naruto damge 20 HP", Toast.LENGTH_SHORT).show();

        } else if (view.getTag().toString().equals("attackV3")) {
            updateProgressBar(main_PGB_Hero, 30);
            Toast.makeText(getApplicationContext(), "Naruto damge 30 HP", Toast.LENGTH_SHORT).show();

        }
        moveV++;
        playerTurn--;
        switchButton(main_PGB_Villains);


    }


    @Override
    protected void onPause() {
        Log.d("johny", "onPause: ");
        if (mPlayer.isPlaying()) {
            mPlayer.pause();
        }
        super.onPause();
        handler.removeCallbacks(secondlyRun);
    }

    @Override
    protected void onResume() {
        Log.d("johny", "onResume: ");
        mPlayer.start();
        super.onResume();
    }


    private void switchButton(ProgressBar pgb) {
        if (pgb.getTag().toString().equals("human")) {
            main_BTN_AttackHero1.setEnabled(false);
            main_BTN_AttackHero2.setEnabled(false);
            main_BTN_AttackHero3.setEnabled(false);

            main_BTN_AttackVillains1.setEnabled(true);
            main_BTN_AttackVillains2.setEnabled(true);
            main_BTN_AttackVillains3.setEnabled(true);

        } else {
            main_BTN_AttackVillains1.setEnabled(false);
            main_BTN_AttackVillains2.setEnabled(false);
            main_BTN_AttackVillains3.setEnabled(false);

            main_BTN_AttackHero1.setEnabled(true);
            main_BTN_AttackHero2.setEnabled(true);
            main_BTN_AttackHero3.setEnabled(true);
        }
    }


    private void disableButton(ProgressBar pgb) {
        if (pgb.getTag().toString().equals("human")) {
            main_BTN_AttackHero1.setEnabled(false);
            main_BTN_AttackHero2.setEnabled(false);
            main_BTN_AttackHero3.setEnabled(false);
        } else {
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


    void updateProgressBar(ProgressBar seek, int num) {
        if (seek.getProgress() > 0) {
            if (seek.getProgress() <= 0.5 * seek.getMax()) {
                seek.getProgressDrawable().setColorFilter(ContextCompat.getColor(this, R.color.almostDying), PorterDuff.Mode.MULTIPLY);
                seek.setProgress(seek.getProgress() - num);
            }

            seek.setProgress(seek.getProgress() - num);

        }


    }

    void startFight() {


        main_BTN_AttackHero1.setOnClickListener(attackClick);
        main_BTN_AttackHero2.setOnClickListener(attackClick);
        main_BTN_AttackHero3.setOnClickListener(attackClick);
        main_BTN_AttackVillains1.setOnClickListener(attackClick);
        main_BTN_AttackVillains2.setOnClickListener(attackClick);
        main_BTN_AttackVillains3.setOnClickListener(attackClick);
    }


    void findView() {
        main_BTN_AttackHero1 = findViewById(R.id.main_BTN_AttackHero1);
        main_BTN_AttackHero2 = findViewById(R.id.main_BTN_AttackHero2);
        main_BTN_AttackHero3 = findViewById(R.id.main_BTN_AttackHero3);
        main_BTN_AttackVillains1 = findViewById(R.id.main_BTN_AttackVillains1);
        main_BTN_AttackVillains2 = findViewById(R.id.main_BTN_AttackVillains2);
        main_BTN_AttackVillains3 = findViewById(R.id.main_BTN_AttackVillains3);
        main_IMG_Hero = findViewById(R.id.main_IMG_Hero);
        main_IMG_Villains = findViewById(R.id.main_IMG_Villains);
        main_PGB_Hero = findViewById(R.id.main_PGB_Hero);
        main_PGB_Villains = findViewById(R.id.main_PGB_Villains);
        main_IMG_background = findViewById(R.id.main_IMG_background);
        main_TXT_timer = findViewById(R.id.main_TXT_timer);
    }

    private void customToast(String str) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast,
                (ViewGroup) findViewById(R.id.toast_layout_root));

        ImageView image = (ImageView) layout.findViewById(R.id.toast_IMG_picture);
        image.setImageResource(R.drawable.over);
        TextView text = (TextView) layout.findViewById(R.id.toast_TXT_text);
        text.setText(str);
        text.setTextSize(22);
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

    private boolean Winner(ProgressBar pgbHero, ProgressBar pgbDevil) {
        if (pgbHero.getProgress() == 0) {
            //customToast("Suske Is Winner!!!");
            MYSP.getInstance().putBoolean("Suske", true);
            MYSP.getInstance().putBoolean("Naruto", false);
            MYSP.getInstance().putInt("MOVEV", moveV);
            return true;
        } else if (pgbDevil.getProgress() == 0) {
            // customToast("Naruto Is Winner!!!");
            MYSP.getInstance().putBoolean("Naruto", true);
            MYSP.getInstance().putBoolean("Suske", false);
            MYSP.getInstance().putInt("MOVEH", moveH);
            return true;
        }
        return false;

    }


}