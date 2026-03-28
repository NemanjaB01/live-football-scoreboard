package com.scoreboard;

import java.util.ArrayList;
import java.util.List;

public class Scoreboard {

    private List<Match> matches = new ArrayList<>();

    public void startMatch(String home, String away) {
        Match match = new Match(home, away);
        matches.add(match);
    }

    public List<Match> getSummary() {
        return matches;
    }

    public void updateScore(String home, String away, int homeScore, int awayScore) {
        if(homeScore < 0 || awayScore < 0) {
            throw new IllegalArgumentException("Scores cannot be negative");
        }
        for(Match match : matches) {
            if(match.getHomeTeam().equals(home) && match.getAwayTeam().equals(away)) {
                match.setHomeScore(homeScore);
                match.setAwayScore(awayScore);
                return;
            }
        }
    }

    public void finishMatch(String home, String away) {
        boolean removed = matches.removeIf(match-> match.getHomeTeam().equals(home) && match.getAwayTeam().equals(away));
        if(!removed) {
            throw new IllegalArgumentException("Match not found: " + home + " vs " + away);
        }
    }
}
