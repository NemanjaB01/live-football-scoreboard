package com.scoreboard;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Scoreboard {

    private List<Match> matches = new ArrayList<>();

    public void startMatch(String home, String away) {
        if(home == null || away == null) {
            throw new IllegalArgumentException("Team names cannot be null");
        }
        String trimmedHome = home.trim();
        String trimmedAway = away.trim();
        for(Match match : matches) {
            if (match.getHomeTeam().equals(trimmedHome) ||  match.getAwayTeam().equals(trimmedAway)
                || match.getHomeTeam().equals(trimmedAway) || match.getAwayTeam().equals(trimmedHome)) {
                throw new IllegalArgumentException("Team " + home + " or " + away + " is already playing");
            }
        }
        Match match = new Match(home, away);
        matches.add(match);
    }

    public List<MatchSummary> getSummary() {
        List<Match> sortedMatches = new ArrayList<>(matches);
        Comparator<Match> comparator = (m1, m2) -> {
            int total1 = m1.getHomeScore() + m1.getAwayScore();
            int total2 = m2.getHomeScore() + m2.getAwayScore();
            if (total1 != total2) {
                return Integer.compare(total2, total1); // total score
            }
            return m2.getStartTime().compareTo(m1.getStartTime()); // start time
        };
        sortedMatches.sort(comparator);
        return sortedMatches.stream()
                .map(match -> new MatchSummary(match.getHomeTeam(), match.getAwayTeam(), match.getHomeScore(), match.getAwayScore(), match.getStartTime()))
                .toList();
    }

    public void updateScore(String home, String away, int homeScore, int awayScore) {
        if(home == null || away == null) {
            throw new IllegalArgumentException("Team names cannot be null");
        }
        if(homeScore < 0 || awayScore < 0) {
            throw new IllegalArgumentException("Scores cannot be negative");
        }
        String trimmedHome = home.trim();
        String trimmedAway = away.trim();
        for(Match match : matches) {
            if(match.getHomeTeam().equals(trimmedHome) && match.getAwayTeam().equals(trimmedAway)) {
                match.setHomeScore(homeScore);
                match.setAwayScore(awayScore);
                return;
            }
        }
        throw new IllegalArgumentException("Match not found: " + home + " vs " + away);
    }

    public void finishMatch(String home, String away) {
        if(home == null || away == null) {
            throw new IllegalArgumentException("Team names cannot be null");
        }
        String trimmedHome = home.trim();
        String trimmedAway = away.trim();
        boolean removed = matches.removeIf(match-> match.getHomeTeam().equals(trimmedHome) && match.getAwayTeam().equals(trimmedAway));
        if(!removed) {
            throw new IllegalArgumentException("Match not found: " + home + " vs " + away);
        }
    }
}
