package com.scoreboard;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class ScoreboardTest {

    @Test
    public void testStartMatch_initialScoreZeroZero() {
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.startMatch("Mexico", "Canada");

        List<MatchSummary> summary = scoreboard.getSummary();
        assertEquals(1, summary.size());
        MatchSummary match = summary.getFirst();
        assertEquals("Mexico", match.homeTeam());
        assertEquals("Canada", match.awayTeam());
        assertEquals(0, match.homeScore());
        assertEquals(0, match.awayScore());
    }

    @Test
    public void testUpdateScore_absoluteScores() {
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.startMatch("Mexico", "Canada");
        scoreboard.updateScore("Mexico", "Canada", 1, 0);

        List<MatchSummary> summary = scoreboard.getSummary();
        assertEquals(1, summary.size());
        MatchSummary match = summary.get(0);
        assertEquals(1, match.homeScore());
        assertEquals(0, match.awayScore());
    }

    @Test
    public void testFinishMatch_removesMatchFromScoreboard() {
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.startMatch("Mexico", "Canada");
        scoreboard.finishMatch("Mexico", "Canada");

        List<MatchSummary> summary = scoreboard.getSummary();
        assertEquals(0, summary.size());
    }

    @Test
    public void testGetSummary_orderedByTotalScoreThenRecentStart() {
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.startMatch("Mexico", "Canada");
        scoreboard.updateScore("Mexico", "Canada", 0, 5);
        scoreboard.startMatch("Spain", "Brazil");
        scoreboard.updateScore("Spain", "Brazil", 10, 2);
        scoreboard.startMatch("Germany", "France");
        scoreboard.updateScore("Germany", "France", 2, 2);
        scoreboard.startMatch("Uruguay", "Italy");
        scoreboard.updateScore("Uruguay", "Italy", 6, 6);
        scoreboard.startMatch("Argentina", "Australia");
        scoreboard.updateScore("Argentina", "Australia", 3, 1);

        List<MatchSummary> summary = scoreboard.getSummary();
        assertEquals(5, summary.size());
        // Expected order: Uruguay (12), Spain (12, but Uruguay more recent), Mexico (5), Argentina (4), Germany (4, but Argentina more recent)
        assertEquals("Uruguay", summary.get(0).homeTeam());
        assertEquals("Spain", summary.get(1).homeTeam());
        assertEquals("Mexico", summary.get(2).homeTeam());
        assertEquals("Argentina", summary.get(3).homeTeam());
        assertEquals("Germany", summary.get(4).homeTeam());
    }

    @Test
    public void testUpdateScore_nonExistentMatch_throwsException() {
        Scoreboard scoreboard = new Scoreboard();
        assertThrows(IllegalArgumentException.class, () -> scoreboard.updateScore("Mexico", "Canada", 1, 0));
    }

    @Test
    public void testStartMatch_teamAlreadyPlaying_throwsException() {
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.startMatch("Mexico", "Canada");
        assertThrows(IllegalArgumentException.class, () -> scoreboard.startMatch("Mexico", "Brazil"));
    }

    @Test
    public void testStartMatch_invalidTeamName_throwsException() {
        Scoreboard scoreboard = new Scoreboard();
        assertThrows(IllegalArgumentException.class, () -> scoreboard.startMatch(null, "Canada"));
        assertThrows(IllegalArgumentException.class, () -> scoreboard.startMatch("Mexico", null));
        assertThrows(IllegalArgumentException.class, () -> scoreboard.startMatch("", "Canada"));
        assertThrows(IllegalArgumentException.class, () -> scoreboard.startMatch("Mexico", ""));
        assertThrows(IllegalArgumentException.class, () -> scoreboard.startMatch("   ", "Canada"));
        assertThrows(IllegalArgumentException.class, () -> scoreboard.startMatch("Mexico", "   "));
    }

    @Test
    public void testUpdateScore_negativeScores_throwsException() {
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.startMatch("Mexico", "Canada");
        assertThrows(IllegalArgumentException.class, () -> scoreboard.updateScore("Mexico", "Canada", -1, 0));
        assertThrows(IllegalArgumentException.class, () -> scoreboard.updateScore("Mexico", "Canada", 0, -1));
    }
}
