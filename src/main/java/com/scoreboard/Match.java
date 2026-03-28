package com.scoreboard;

import java.time.Instant;

public class Match {
    private String homeTeam;

    private String awayTeam;

    private int homeScore;

    private int awayScore;

    private Instant startTime;

    public Match(String homeTeam, String awayTeam) {
        if(homeTeam == null || homeTeam.isEmpty() || awayTeam == null || awayTeam.isEmpty()) {
            throw new IllegalArgumentException("Team names cannot be null or empty");
        }
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        homeScore = 0;
        awayScore = 0;
        startTime = Instant.now();
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }
}
