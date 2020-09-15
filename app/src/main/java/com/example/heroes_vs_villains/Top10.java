package com.example.heroes_vs_villains;

public class Top10 {
    private String name = "";
    private double lat = 0.0;
    private double lon = 0.0;
    private long time = 0;
    private int numOfMoves = 99;
    final int maxPlayres = 10;

    public interface KEYS {
        int MAX_PLAYERS = 10;
    }

    public Top10() {
    }

    public String getName() {
        return name;
    }

    public Top10(String name, double lat, double lon, long time, int numOfMoves) {
        this.name = name;
        this.lat = lat;
        this.lon = lon;
        this.time = time;
        this.numOfMoves = numOfMoves;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getNumOfMoves() {
        return numOfMoves;
    }

    public void setNumOfMoves(int numOfMoves) {
        this.numOfMoves = numOfMoves;
    }


}
