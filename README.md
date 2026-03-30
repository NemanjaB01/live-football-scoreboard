# Live Football World Cup Scoreboard

This is a simple Java library for managing a live football scoreboard.

## Requirements
- Start a new match with initial score 0-0.
- Update score with absolute scores.
- Finish a match.
- Get summary of ongoing matches ordered by total score descending, ties by most recently started.

## Assumptions
- Teams are identified by unique string names.
- No two matches can have the same pair of teams simultaneously.
- Scores are non-negative integers.
- Start time is recorded when the match is started.
- The library uses in-memory storage with collections.
- Ordering for summary: total score (home + away) descending, then start time descending (most recent first).
- Team names are trimmed and cannot be null, empty, or whitespace-only.
- getSummary returns a list of MatchSummary records, not mutable Match objects.

## Domain Entities
- `Match`: Represents a match with home team, away team, scores, and start time.
- `Scoreboard`: Manages matches and provides operations.
- `MatchSummary`: Immutable record for summary data.

## Data Structure
- In-memory store: `List<Match>` with sorting performed on-demand in `getSummary()` using a comparator for total score descending, then start time descending. A copy is sorted to avoid side effects.

## Validations
- Team names: Non-null, non-empty after trimming.
- Scores: Non-negative integers.
- Matches: Unique team pairs; updating/finishing non-existent matches throws exceptions; teams cannot play in multiple matches simultaneously.

## Usage
```java
Scoreboard scoreboard = new Scoreboard();
scoreboard.startMatch("Mexico", "Canada");
scoreboard.updateScore("Mexico", "Canada", 0, 5);
scoreboard.startMatch("Spain", "Brazil");
scoreboard.updateScore("Spain", "Brazil", 10, 2);
List<MatchSummary> summary = scoreboard.getSummary(); // Returns matches ordered by total score descending
scoreboard.finishMatch("Mexico", "Canada");
```
