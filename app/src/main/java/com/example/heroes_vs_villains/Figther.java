package com.example.heroes_vs_villains;

import android.widget.ProgressBar;

import java.util.ArrayList;

public class Figther {
    private String name = "";
    private double lati = 0;
    private double longi = 0;
    private int numOfMoves = 0;
    private int playerTurn = 0;
    private boolean winner = false;
    private ProgressBar life;


    public ArrayList<Top10> scores;

    public Figther(String name, double lati, double longi, int numOfMoves, int playerTurn, boolean winner, ProgressBar life) {
        this.name = name;
        this.lati = lati;
        this.longi = longi;
        this.numOfMoves = numOfMoves;
        this.playerTurn = playerTurn;
        this.winner = winner;
        this.life = life;
    }

    public Figther() {

    }

    public int getPlayerTurn() {
        return playerTurn;
    }

    public Figther setPlayerTurn(int playerTurn) {
        this.playerTurn = playerTurn;
        return this;
    }

    public String getName() {
        return name;
    }

    public Figther setName(String name) {
        this.name = name;
        return this;
    }

    public ProgressBar getLife() {
        return life;
    }

    public Figther setLife(ProgressBar life) {
        this.life = life;
        return this;
    }

    public ArrayList<Top10> getScores() {
        return scores;
    }

    public Figther setScores(ArrayList<Top10> scores) {
        this.scores = scores;
        return this;
    }

    public double getLati() {
        return lati;
    }

    public void setLati(double lati) {
        this.lati = lati;
    }

    public double getLongi() {
        return longi;
    }

    public void setLongi(double longi) {
        this.longi = longi;
    }

    public int getNumOfMoves() {
        return numOfMoves;
    }

    public void setNumOfMoves(int numOfMoves) {
        this.numOfMoves = numOfMoves;
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }
}
