package com.scoreboard;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Scoreboard {

    private List<Match> matches = new ArrayList<>();

    public void startMatch(String home, String away) {
        for(Match match : matches) {
            if (match.getHomeTeam().equals(home) ||  match.getAwayTeam().equals(away)
                || match.getHomeTeam().equals(away) || match.getAwayTeam().equals(home)) {
                throw new IllegalArgumentException("Team " + home + " or " + away + " is already playing");
            }
        }
        Match match = new Match(home, away);
        matches.add(match);
    }

    public List<Match> getSummary() {
        Comparator<Match> comparator = (m1, m2) -> {
            int total1 = m1.getHomeScore() + m1.getAwayScore();
            int total2 = m2.getHomeScore() + m2.getAwayScore();
            if (total1 != total2) {
                return Integer.compare(total2, total1); // total score
            }
            return m2.getStartTime().compareTo(m1.getStartTime()); // start time
        };
        matches.sort(comparator);
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
        throw new IllegalArgumentException("Match not found: " + home + " vs " + away);
    }

    public void finishMatch(String home, String away) {
        boolean removed = matches.removeIf(match-> match.getHomeTeam().equals(home) && match.getAwayTeam().equals(away));
        if(!removed) {
            throw new IllegalArgumentException("Match not found: " + home + " vs " + away);
        }
    }
}
