package com.ohgiraffers.MovieApp.aggregate;

import java.io.Serializable;
import java.util.Arrays;

public class Movie implements Serializable {
    private int movieId;
    private String movieTitle;
    private String[] genre;
    private int runningTime;
    private int moviePrice;

    public Movie() {
    }

    public Movie(int movieId, String movieTitle, String[] genre, int runningTime, int moviePrice) {
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.genre = genre;
        this.runningTime = runningTime;
        this.moviePrice = moviePrice;
    }

    public Movie(String movieTitle, String[] genre, int runningTime, int moviePrice) {
        this.movieTitle = movieTitle;
        this.genre = genre;
        this.runningTime = runningTime;
        this.moviePrice = moviePrice;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String[] getGenre() {
        return genre;
    }

    public void setGenre(String[] genre) {
        this.genre = genre;
    }

    public int getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(int runningTime) {
        this.runningTime = runningTime;
    }

    public int getMoviePrice() {
        return moviePrice;
    }

    public void setMoviePrice(int moviePrice) {
        this.moviePrice = moviePrice;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", movieTitle='" + movieTitle + '\'' +
                ", genre=" + Arrays.toString(genre) +
                ", runningTime=" + runningTime +
                ", moviePrice=" + moviePrice +
                '}';
    }
}
