package com.example.heroes_vs_villains;

import android.widget.ProgressBar;

public class Fighter {

    private ProgressBar life;
    private String name;

    private boolean isTurn = true;
    private  int move=0;

    public Fighter(ProgressBar life, String name, boolean isTurn, int move) {
        this.life = life;
        this.name = name;
        this.isTurn = isTurn;
        this.move = move;
    }

    public ProgressBar getLife() {
        return life;
    }



    public Fighter() {
    }

    public String getName() {
        return name;
    }

    public void setTurn(boolean turn) {
        isTurn = turn;
    }

    public boolean isTurn() {
        return isTurn;
    }

    public void setMove(int move) {
        this.move = move;
    }

    public int getMove() {
        return move;
    }




}
