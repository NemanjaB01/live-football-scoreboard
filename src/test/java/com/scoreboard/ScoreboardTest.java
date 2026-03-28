package com.scoreboard;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class ScoreboardTest {

    @Test
    public void testStartMatch_initialScoreZeroZero() {
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.startMatch("Mexico", "Canada");

        List<Match> summary = scoreboard.getSummary();
        assertEquals(1, summary.size());
        Match match = summary.get(0);
        assertEquals("Mexico", match.getHomeTeam());
        assertEquals("Canada", match.getAwayTeam());
        assertEquals(0, match.getHomeScore());
        assertEquals(0, match.getAwayScore());
    }

    @Test
    public void testUpdateScore_absoluteScores() {
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.startMatch("Mexico", "Canada");
        scoreboard.updateScore("Mexico", "Canada", 1, 0);

        List<Match> summary = scoreboard.getSummary();
        assertEquals(1, summary.size());
        Match match = summary.get(0);
        assertEquals(1, match.getHomeScore());
        assertEquals(0, match.getAwayScore());
    }

    @Test
    public void testFinishMatch_removesMatchFromScoreboard() {
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.startMatch("Mexico", "Canada");
        scoreboard.finishMatch("Mexico", "Canada");

        List<Match> summary = scoreboard.getSummary();
        assertEquals(0, summary.size());
    }
}
