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

}
