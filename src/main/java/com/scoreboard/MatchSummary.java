package com.scoreboard;

import java.time.Instant;

public record MatchSummary(String homeTeam, String awayTeam, int homeScore, int awayScore, Instant startTime) {
}
